package com.example.fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Recycleview extends RecyclerView.Adapter<Adapter_Recycleview.ViewHolder> {
    private boolean favorite = false;
    private OnClickListener onClickListener;
    private ArrayList<Songs> songs_list;

    public Adapter_Recycleview(ArrayList<Songs> songs_list) {
        this.songs_list = songs_list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout fragmentContainer;
        public simpleFragment itemFragment;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Define Click event
            textView = itemView.findViewById(R.id.playlist_name);
            itemView.setOnClickListener(view -> {
                if(onClickListener != null) {
                    int i = 0;
                    for(; songs_list.get(i).getName() != textView.getText(); i++);
                    onClickListener.onClick(getAdapterPosition(), songs_list.get(i), this);
                }
            });

        }
        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Define view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_name, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Replace content
        holder.getTextView().setText(songs_list.get(position).getName());

        holder.itemView.setOnClickListener(view -> {
            if(onClickListener != null) {
                onClickListener.onClick(position, songs_list.get(position), holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs_list.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Songs songs, ViewHolder holder);
    }

}
