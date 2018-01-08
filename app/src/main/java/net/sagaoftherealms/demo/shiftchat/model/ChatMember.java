package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "chat_member")
public class ChatMember {
    @ColumnInfo(name = "display_name")
    public String displayName;
    @ColumnInfo(name = "about_me")
    public String aboutMe;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "email_address")
    public String emailAddress;
    @ColumnInfo(name = "picture_uri")
    public String pictureUri;

    @PrimaryKey
    @ColumnInfo(name = "google_id")
    @NonNull
    public String googleId;

}
