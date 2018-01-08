package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Chat is a collection of chat members and list of chat messages.
 */
@Entity
public class Chat {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd");

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "chat_name")
    public String chatName;

    @Ignore
    public List<ChatMember> members = new ArrayList<>();

    @Ignore
    public List<ChatMessage> messages = new ArrayList<>();

    public String getLastMessageTime() {
        if (messages.isEmpty()) {
            return "";
        } else {
            int size = messages.size();
            return DATE_FORMAT.format(new Date(messages.get(size - 1).messageDate));
        }

    }

    public String getLastMessageText() {
        if (messages.isEmpty()) {
            return "";
        } else {
            int size = messages.size();
            return messages.get(size - 1).message;
        }
    }

    @Nullable
    public Uri getThumbnailUri() {
        if (members.isEmpty()) {
            return null;
        } else {
            return Uri.parse(members.get(0).pictureUri);
        }
    }

}
