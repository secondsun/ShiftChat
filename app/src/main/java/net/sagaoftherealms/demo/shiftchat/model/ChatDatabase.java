package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Chat.class, ChatMessage.class, ChatMember.class}, version = 1, exportSchema = false)
public abstract class ChatDatabase extends RoomDatabase {

    abstract public ChatDao chatDao();

}
