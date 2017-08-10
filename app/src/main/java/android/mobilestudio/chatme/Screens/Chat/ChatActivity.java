package android.mobilestudio.chatme.screens.chat;

import android.content.Context;
import android.mobilestudio.chatme.adapters.MessageListAdapter;
import android.mobilestudio.chatme.models.message.parent.Message;
import android.mobilestudio.chatme.models.Person;
import android.mobilestudio.chatme.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements ChatView, View.OnClickListener {
    RecyclerView Messages_recyclerView;
    ImageView sentButton;
    EditText mContentMessage;
    MessageListAdapter adapter;
    ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Messages_recyclerView = (RecyclerView) findViewById(R.id.rv_MessagesOfChat);
        sentButton = (ImageView) findViewById(R.id.iv_sentButton);
        sentButton.setOnClickListener(this);
        mContentMessage = (EditText) findViewById(R.id.ed_Message);
        Messages_recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        presenter = new ChatPresenterImpl(this);
        presenter.onCreate((Person) getIntent().getExtras().get(getString(R.string.person)));
    }

    @Override
    public void setAdapter(List<Message> list) {
        adapter = new MessageListAdapter(this, list);
        Messages_recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void erase_EditTexy_Message() {
        mContentMessage.setText("");
    }

    @Override
    public void setTitleOfToolbar(String name) {
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == sentButton.getId()) {
            presenter.addTextMessage(mContentMessage.getText().toString());
        }
    }
}
