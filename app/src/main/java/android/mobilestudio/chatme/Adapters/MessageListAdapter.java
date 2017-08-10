package android.mobilestudio.chatme.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.mobilestudio.chatme.models.message.parent.Message;
import android.mobilestudio.chatme.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pisoo on 7/30/2017.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageHolder>{

    List<Message> messages;
     static Context mContext ;
    public MessageListAdapter(Context context , List<Message> messages) {
        this.messages = messages;
        this.mContext = context ;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  ;

        if (viewType==0)
            itemView =LayoutInflater.from(parent.getContext()).
                inflate(R.layout.message_item_right, parent, false);
        else
            itemView =   LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.message_item_left, parent, false);
        return new MessageListAdapter.MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        holder.mcontent.setText(messages.get(position).getContent());
        if (messages.get(position).getIt_is_myMessage()) {
             holder.mcontent.setBackgroundResource(R.drawable.custom_textview_blue);
        }
        else {
            holder.mcontent.setBackgroundResource(R.drawable.custom_textview_white);
        }
    }

    @Override
    public int getItemViewType(int position) {
         if (messages.get(position).getIt_is_myMessage())
            return 0;
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {
        TextView mcontent;

        public MessageHolder(View itemView) {
            super(itemView);
            mcontent = (TextView) itemView.findViewById(R.id.tv_content);
            Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "Fonts/Gabriola.ttf");
            mcontent.setTypeface(tf);
        }


    }
}
