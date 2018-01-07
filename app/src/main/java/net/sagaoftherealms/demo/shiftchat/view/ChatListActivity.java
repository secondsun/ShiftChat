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

import net.sagaoftherealms.demo.shiftchat.R;
import net.sagaoftherealms.demo.shiftchat.model.Chat;
import net.sagaoftherealms.demo.shiftchat.model.ChatDatabase;
import net.sagaoftherealms.demo.shiftchat.model.util.DBBootstrap;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ChatDatabase chatDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        chatDb = Room.inMemoryDatabaseBuilder(getApplicationContext(), ChatDatabase.class).addCallback(
                new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        //create chat objects
                        DBBootstrap.createChats(db);
                        DBBootstrap.createMembers(db);
                        DBBootstrap.populateChats(db);
                    }
                }
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        List<Chat> chats = chatDb.chatDao().findMostRecentChats();
        recyclerView.setAdapter(new ChatListAdapter(chats));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }


}
