package com.example.aufgabe_zdf;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<Item>mItemList;

    public ItemAdapter(Context context, ArrayList<Item>itemList) {
        mContext = context;
        mItemList = itemList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        Item currentItem = mItemList.get(position);

        if(currentItem.getHeadline() == "")
            itemViewHolder.mHeadline.setVisibility(View.GONE);
        else itemViewHolder.mHeadline.setText(currentItem.getHeadline());

        if(currentItem.getTitel() == "")
            itemViewHolder.mTitel.setVisibility(View.GONE);
        else itemViewHolder.mTitel.setText(currentItem.getTitel());

        if(currentItem.getBeschreibung() == "")
            itemViewHolder.mBeschreibung.setVisibility(View.GONE);
        else itemViewHolder.mBeschreibung.setText(currentItem.getBeschreibung());

        if(currentItem.getImageUrl() == "")
            itemViewHolder.mImageView.setVisibility(View.GONE);
        else Picasso.with(mContext).load(currentItem.getImageUrl()).fit().centerCrop().into(itemViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    // Define View Holder class
    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mHeadline;
        public TextView mTitel;
        public TextView mBeschreibung;

        // Constructor
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_teaser);
            mHeadline = itemView.findViewById(R.id.text_view_headline);
            mTitel = itemView.findViewById(R.id.text_view_titel);
            mBeschreibung = itemView.findViewById(R.id.text_view_beschreibung);
        }
    }
}