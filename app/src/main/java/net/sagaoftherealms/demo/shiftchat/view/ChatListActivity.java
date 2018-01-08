package net.sagaoftherealms.demo.shiftchat.view;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;

import net.sagaoftherealms.demo.shiftchat.R;
import net.sagaoftherealms.demo.shiftchat.ShiftChatApplication;
import net.sagaoftherealms.demo.shiftchat.model.Chat;
import net.sagaoftherealms.demo.shiftchat.model.ChatDatabase;
import net.sagaoftherealms.demo.shiftchat.model.util.DBBootstrap;
import net.sagaoftherealms.demo.shiftchat.presenter.ChatListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ChatDatabase chatDb;
    private ChatListPresenter presenter = new ChatListPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachChatActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachChatActivity();
    }

    public void displayChats(List<Chat> chats) {
        recyclerView.setAdapter(new ChatListAdapter(chats, (ShiftChatApplication) getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
