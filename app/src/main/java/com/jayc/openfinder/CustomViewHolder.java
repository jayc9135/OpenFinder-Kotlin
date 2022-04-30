package com.jayc.openfinder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView newsHeadlineTextview;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        newsHeadlineTextview = itemView.findViewById(R.id.news_headline_textview);

    }
}
