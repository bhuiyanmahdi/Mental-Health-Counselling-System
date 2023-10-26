package com.example.mentalhealthcounselling1;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ChatRVAdapter extends RecyclerView.Adapter{

    private ArrayList<ChatsModal> chatsModalArrayList;
    private Context context;

    public ChatRVAdapter(ArrayList<ChatsModal> chatsModalArrayList, Context context) {
        this.chatsModalArrayList = chatsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item2,parent,false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item2,parent,false);
                return new BotViewHolder(view);
            // for only message
            /*case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item_only_message,parent,false);
                return new BotViewHolder_only_message(view);*/
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatsModal chatsModal = chatsModalArrayList.get(position);
        switch (chatsModal.getSender()){
            case "user":
                ((UserViewHolder)holder).userTV.setText(chatsModal.getMessage());
                break;
            // below portion has been modified
            case "bot":
                if(chatsModal.getVideo().equals("null")){

                    ((BotViewHolder)holder).botMsgTV.setText(chatsModal.getMessage());
                    ((BotViewHolder)holder).webView.setVisibility(View.GONE);
                    }
                else{
                    ((BotViewHolder)holder).botMsgTV.setText(chatsModal.getMessage());
                    ((BotViewHolder)holder).webView.loadData(chatsModal.getVideo(), "text/html", "utf-8");
                    //((BotViewHolder)holder).webView.setVisibility(View.GONE);
                    //Toast.makeText(context,chatsModal.getMessage(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context,chatsModal.getVideo(), Toast.LENGTH_SHORT).show();

                }

                //((BotViewHolder)holder).botMsgTV.setText(chatsModal.getMessage());
                //((BotViewHolder)holder).webView.loadData(chatsModal.getVideo(), "text/html", "utf-8");

                break;
                // till this
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatsModalArrayList.get(position).getSender())
        {
            case "user":
                return 0;

            case "bot":
                return 1;

            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatsModalArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView userTV;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        WebView webView;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot);
            webView = itemView.findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());

        }
    }

    //extra part for without video , only message
    /*public static class BotViewHolder_only_message extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder_only_message(@NonNull View itemView) {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot_only_message);
        }
    }*/
}
