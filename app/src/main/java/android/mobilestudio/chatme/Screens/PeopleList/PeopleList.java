package android.mobilestudio.chatme.Screens.PeopleList;

import android.content.Intent;
import android.mobilestudio.chatme.Adapters.MemberListAdapter;
import android.mobilestudio.chatme.Authentication.Login.LoginActivity;
import android.mobilestudio.chatme.Models.Person;
import android.mobilestudio.chatme.R;
import android.mobilestudio.chatme.Screens.Chat.ChatActivity;
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

import java.util.List;

public class PeopleList extends AppCompatActivity implements PeopleListView {
    RecyclerView mPeopleListRecycler;
    MemberListAdapter adapter ;
    PeopleListPresenter presenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        mPeopleListRecycler = (RecyclerView) findViewById(R.id.list_people);
        mPeopleListRecycler.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        presenter = new PeopleListPresenteImpl(this);
        presenter.onCreate();
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
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setAdapter(final List<Person> list) {
        adapter=new MemberListAdapter(list, new MemberListAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                 Intent intent = new Intent(PeopleList.this, ChatActivity.class);
                 intent.putExtra("person",list.get(position));
                 startActivity(intent);
            }
        });
        mPeopleListRecycler.setAdapter(adapter);
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
}
