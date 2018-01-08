package net.sagaoftherealms.demo.shiftchat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import net.sagaoftherealms.demo.shiftchat.R;
import net.sagaoftherealms.demo.shiftchat.ShiftChatApplication;
import net.sagaoftherealms.demo.shiftchat.model.ChatMessage;
import net.sagaoftherealms.demo.shiftchat.presenter.ChatMessagesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageActivity extends AppCompatActivity {

    public static final String CHAT_ID = "ChatID";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView;

    private Long chatId;
    private ChatMessagesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        ButterKnife.bind(this);
        chatId = getIntent().getLongExtra(CHAT_ID, -1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Test");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new ChatMessagesPresenter();
        presenter.attachChatActivity(this, chatId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachChatActivity();
    }

    public void displayChatMessages(List<ChatMessage> messages) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChatMessageListAdapter(messages, (ShiftChatApplication) getApplication()));
        recyclerView.scrollToPosition(messages.size()-1);
    }
}
