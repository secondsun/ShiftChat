package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = ChatMember.class,
                    parentColumns = "google_id",
                    childColumns = "sender_google_id")
        },
        indices = {@Index(value = {"parent_chat_id", "message_date"}),
                   @Index(value = {"sender_google_id", "parent_chat_id"}),},
        tableName = "chat_message")
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo(name = "parent_chat_id")
    Long parentChatId;

    String message;

    @ColumnInfo(name = "message_date")
    Long messageDate;

    @ColumnInfo(name = "sender_google_id")
    String senderGoogleId;

}
