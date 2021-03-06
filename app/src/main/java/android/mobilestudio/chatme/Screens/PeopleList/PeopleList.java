package android.mobilestudio.chatme.screens.peopleList;

import android.content.Intent;
import android.mobilestudio.chatme.adapters.MemberListAdapter;
import android.mobilestudio.chatme.authentication.login.LoginActivity;
import android.mobilestudio.chatme.models.Person;
import android.mobilestudio.chatme.R;
import android.mobilestudio.chatme.screens.chat.ChatActivity;
import android.mobilestudio.chatme.screens.Profile;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class PeopleList extends AppCompatActivity implements PeopleListView, MemberListAdapter.RecyclerViewClickListener {
    RecyclerView mPeopleListRecycler;
    MemberListAdapter adapter;
    PeopleListPresenter presenter;
    List<Person> AllUser, OnlineUser, list;
    private InterstitialAd mInterstitialAd;
    private AdView mBannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        mPeopleListRecycler = (RecyclerView) findViewById(R.id.list_people);
        mPeopleListRecycler.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        presenter = new PeopleListPresenteImpl(this);
        presenter.onCreate();
        AllUser = new ArrayList<>();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.all_users)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.online_users)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    list.addAll(AllUser);
                    AllUser.clear();
                } else if (tab.getPosition() == 1) {
                    list = filterAlluser(list);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        MobileAds.initialize(this, getResources().getString(R.string.ad_id_bannar));
        mBannerAd = (AdView) findViewById(R.id.banner_AdView);
        showBannerAd();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.ad_id_bannar));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });

    }

    private void showBannerAd() {

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mBannerAd.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_peoplelist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            presenter.logoutCurrentUser();
        } else if (id == R.id.profile) {
            presenter.moveToProfile();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAdapter(final List<Person> list) {
        this.list = list;
        OnlineUser = filterAlluser(list);
        adapter = new MemberListAdapter(this.list, this);
        mPeopleListRecycler.setAdapter(adapter);
    }

    private List<Person> filterAlluser(List<Person> list) {

        for (int i = 0; i < list.size(); i++) {
            Log.v("iiii", String.valueOf(list.get(i).getActive()));
            if (!list.get(i).getActive()) {
                AllUser.add(list.get(i));
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnToLoginScreen() {
        startActivity(new Intent(PeopleList.this, LoginActivity.class));
        finish();
    }

    @Override
    public void toProfile(Person user) {
        startActivity(new Intent(this, Profile.class).putExtra(getString(R.string.cur_user), user));
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        if (position > -1 && position < list.size()) {
            Intent intent = new Intent(PeopleList.this, ChatActivity.class);
            intent.putExtra("person", list.get(position));
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.ops, Toast.LENGTH_SHORT).show();
        }

    }
}
