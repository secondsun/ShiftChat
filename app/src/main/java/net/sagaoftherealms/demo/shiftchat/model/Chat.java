package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * A Chat is a collection of chat members and list of chat messages.
 */
@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo(name = "chat_name")
    String chatName;
}
