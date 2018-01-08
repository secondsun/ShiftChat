package net.sagaoftherealms.demo.shiftchat.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChatDao {

    @Query("SELECT DISTINCT chat.* FROM chat INNER JOIN chat_message on chat.id = chat_message.parent_chat_id order by chat_message.message_date desc")
    List<Chat> findMostRecentChats();

    @Query("Select distinct chat_member.* from chat_member inner join chat_message on chat_message.sender_google_id = chat_member.google_id where chat_message.parent_chat_id = :chatId")
    List<ChatMember> findMembersInChat(long chatId);

    @Query("Select distinct chat_message.* from chat_message where chat_message.parent_chat_id = :chatId order by chat_message.message_date")
    List<ChatMessage> findMessagesInChat(long chatId);

    @Insert
    long insertChat(Chat chat);

    @Insert
    long insertChatMessage(ChatMessage chatMessage);

    @Insert
    long insertChatMember(ChatMember chatMember);

    @Query("Select distinct chat_member.* from chat_member where google_id = :senderGoogleId")
    ChatMember findChatMember(String senderGoogleId);
}
