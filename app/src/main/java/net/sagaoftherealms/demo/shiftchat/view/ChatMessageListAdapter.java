package net.sagaoftherealms.demo.shiftchat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.sagaoftherealms.demo.shiftchat.R;
import net.sagaoftherealms.demo.shiftchat.ShiftChatApplication;
import net.sagaoftherealms.demo.shiftchat.model.ChatMessage;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageListAdapter extends RecyclerView.Adapter<ChatMessageListAdapter.ChatMessageViewHolder> {

    private final List<ChatMessage> messages;
    private final ShiftChatApplication app;

    public ChatMessageListAdapter(List<ChatMessage> messages, ShiftChatApplication app) {
        this.messages = messages;
        this.app = app;
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_item, parent, false);

        ChatMessageViewHolder vh = new ChatMessageViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.message.setText(message.message);
        holder.messageDate.setText(new Date(message.messageDate).toString());
        holder.senderName.setText(message.member.displayName);
        String thumbnail = message.member.pictureUri;
        if (thumbnail != null ) {
            app.getPicasso().load(thumbnail).fit().into(holder.senderIcon);
        } else {
            holder.senderIcon.setImageResource(R.drawable.ic_person);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_icon)
        ImageView senderIcon;
        @BindView(R.id.chat_preview)
        TextView message;
        @BindView(R.id.chat_name_label)
        TextView senderName;
        @BindView(R.id.last_message_date)
        TextView messageDate;

        ChatMessageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
