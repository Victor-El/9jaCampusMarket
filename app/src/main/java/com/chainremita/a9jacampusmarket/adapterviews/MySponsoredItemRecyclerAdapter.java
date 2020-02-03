package com.chainremita.a9jacampusmarket.adapterviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chainremita.a9jacampusmarket.R;
import com.chainremita.a9jacampusmarket.api.sponsored.SponsoredItemData;

import java.util.List;

public class MySponsoredItemRecyclerAdapter extends RecyclerView.Adapter<MySponsoredItemRecyclerAdapter.MyViewHolder> {
    List<SponsoredItemData> sponsoredItemDataList;
    Context context;

    public MySponsoredItemRecyclerAdapter(List<SponsoredItemData> sponsoredItemDataList, Context context) {
        this.sponsoredItemDataList = sponsoredItemDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.carded_sponsored_item_layout_structure, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sponsoredItemUsername.setText(sponsoredItemDataList.get(position).getUserData().getUsername());
        holder.sponsoredItemLocation.setText(sponsoredItemDataList.get(position).getLocation().getAbbr());
        holder.sponsoredItemPromoted.setVisibility(Float.parseFloat(sponsoredItemDataList.get(position).getDeposit()) - Float.parseFloat(sponsoredItemDataList.get(position).getConsumed()) >= 5 ? View.VISIBLE : View.INVISIBLE);
        holder.sponsoredItemLikes.setText(sponsoredItemDataList.get(position).getLikeNo().getNumberOfLikes());
        holder.sponsoredItemCaption.setText(sponsoredItemDataList.get(position).getCaption());
        holder.sponsoredItemRating.setRating(Float.parseFloat(sponsoredItemDataList.get(position).getUserRating().getRating() == null ? "0" : sponsoredItemDataList.get(position).getUserRating().getRating()));
        holder.sponsoredItemRating.setFocusable(false);
        holder.sponsoredItemRating.setIsIndicator(true);

        Glide
                .with(context)
                .load("https://images.9jacampusmarket.com/image.php?id=" + sponsoredItemDataList.get(position).getImageServer().getServerId())
                .centerCrop()
                .placeholder(R.drawable.ic_image_white_24dp)
                .into(holder.sponsoredItemImage);
    }

    @Override
    public int getItemCount() {
        return sponsoredItemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sponsoredItemUsername;
        public TextView sponsoredItemLocation;
        public TextView sponsoredItemPromoted;
        public TextView sponsoredItemCaption;
        public TextView sponsoredItemLikes;
        public Button sponsoredItemViewButton;
        public RatingBar sponsoredItemRating;
        public ImageView sponsoredItemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sponsoredItemUsername = itemView.findViewById(R.id.campus_market_sponsored_item_username);
            sponsoredItemLocation = itemView.findViewById(R.id.campus_market_sponsored_item_location);
            sponsoredItemPromoted = itemView.findViewById(R.id.sponsored_promote_view);
            sponsoredItemCaption = itemView.findViewById(R.id.campus_market_sponsored_item_caption);
            sponsoredItemLikes = itemView.findViewById(R.id.campus_market_sponsored_item_likes);
            sponsoredItemViewButton = itemView.findViewById(R.id.campus_market_sponsored_item_buttonview);
            sponsoredItemRating = itemView.findViewById(R.id.campus_market_sponsored_item_rating);
            sponsoredItemImage = itemView.findViewById(R.id.campus_market_sponsored_item_itemimage);
        }
    }
}
