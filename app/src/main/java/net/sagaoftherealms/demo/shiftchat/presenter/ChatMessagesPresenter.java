package net.sagaoftherealms.demo.shiftchat.presenter;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import net.sagaoftherealms.demo.shiftchat.ShiftChatApplication;
import net.sagaoftherealms.demo.shiftchat.model.Chat;
import net.sagaoftherealms.demo.shiftchat.model.ChatDatabase;
import net.sagaoftherealms.demo.shiftchat.model.ChatMessage;
import net.sagaoftherealms.demo.shiftchat.model.util.DBBootstrap;
import net.sagaoftherealms.demo.shiftchat.view.ChatListActivity;
import net.sagaoftherealms.demo.shiftchat.view.ChatMessageActivity;

import java.util.List;

public class ChatMessagesPresenter {

    private ChatMessageActivity activity;
    private ChatDatabase chatDb;

    public void attachChatActivity(ChatMessageActivity activity, Long chatId) {
        this.activity = activity;
        beginLoadChatMessages(chatId);
    }

    private void beginLoadChatMessages(Long chatId) {

        if (activity != null) {

            chatDb= ((ShiftChatApplication)activity.getApplication()).getDB();

            List<ChatMessage> chats = chatDb.chatDao().findMessagesInChat(chatId);
            for (ChatMessage chatMessage : chats) {
                chatMessage.member = chatDb.chatDao().findChatMember(chatMessage.senderGoogleId);
            }
            activity.displayChatMessages(chats);
        }
    }

    public void detachChatActivity() {
        this.activity = null;
    }

}
