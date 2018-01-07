package net.sagaoftherealms.demo.shiftchat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.sagaoftherealms.demo.shiftchat.R;
import net.sagaoftherealms.demo.shiftchat.model.Chat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private final List<Chat> chats;

    public ChatListAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_list_item, viewGroup, false);

        ChatListAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChatListAdapter.ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.chatNameLabel.setText(chat.chatName);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.chat_icon)
        ImageView chatIcon;
        @BindView(R.id.chat_preview)
        TextView chatPreview;
        @BindView(R.id.chat_name_label)
        TextView chatNameLabel;
        @BindView(R.id.last_message_date)
        TextView lastMessageDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
