package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "chat_member")
public class ChatMember {
    @ColumnInfo(name = "display_name")
    String displayName;
    @ColumnInfo(name = "about_me")
    String aboutMe;
    @ColumnInfo(name = "country")
    String country;
    @ColumnInfo(name = "city")
    String city;
    @ColumnInfo(name = "email_address")
    String emailAddress;
    @ColumnInfo(name = "picture_uri")
    String pictureUri;

    @PrimaryKey
    @ColumnInfo(name = "google_id")
    @NonNull
    String googleId;

}
