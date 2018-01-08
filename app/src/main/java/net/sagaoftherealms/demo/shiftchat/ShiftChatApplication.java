package net.sagaoftherealms.demo.shiftchat;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.picasso.Picasso;

import net.sagaoftherealms.demo.shiftchat.model.ChatDatabase;
import net.sagaoftherealms.demo.shiftchat.model.util.DBBootstrap;
import net.sagaoftherealms.demo.shiftchat.view.ChatMessageActivity;

public class ShiftChatApplication extends Application {

    private ChatDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.inMemoryDatabaseBuilder(this, ChatDatabase.class).addCallback(
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



    }

    public Picasso getPicasso() {

        Picasso picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                if (exception != null) {
                    Log.e("PICASSO", exception.getMessage(), exception);
                }
            }
        }).build();


        return picasso;
    }

    public ChatDatabase getDB() {
        return db;
    }

    public void startChatMessagesActivity(Long id) {
        Intent chatIntent = new Intent(this, ChatMessageActivity.class);
        chatIntent.putExtra(ChatMessageActivity.CHAT_ID, id);
        startActivity(chatIntent);
    }
}
