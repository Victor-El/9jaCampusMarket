package com.chainremita.a9jacampusmarket.adapterviews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
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
import com.chainremita.a9jacampusmarket.ItemViewActivity;
import com.chainremita.a9jacampusmarket.R;
import com.chainremita.a9jacampusmarket.SearchableActivity;
import com.chainremita.a9jacampusmarket.api.regularitems.ItemData;
import com.chainremita.a9jacampusmarket.models.AppDatabase;
import com.chainremita.a9jacampusmarket.models.AppItemData;
import com.chainremita.a9jacampusmarket.models.RoomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MyItemRecyclerAdapter extends RecyclerView.Adapter<MyItemRecyclerAdapter.MyViewHolder> {

    private List<ItemData> mItemData;
    private Context context;

    public MyItemRecyclerAdapter(List<ItemData> mItemData, Context context) {

        this.mItemData = mItemData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carded_item_layout_structure, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.usernameTextView.setText(mItemData.get(position).getUserData().getUsername());
        holder.locationTextView.setText(mItemData.get(position).getLocation().getAbbr());
        holder.likesTextView.setText(mItemData.get(position).getLikeNo() == null ? "0" : mItemData.get(position).getLikeNo().getNumberOfLikes());
        holder.captionTextView.setText(mItemData.get(position).getCaption());
        holder.discountTextView.setText("NGN " + String.format("%,.2f", (Float.parseFloat(mItemData.get(position).getPrice()) - Float.parseFloat(mItemData.get(position).getDiscount()))));
        holder.priceTextView.setText("NGN " + String.format("%,.2f", Float.parseFloat(mItemData.get(position).getPrice())));
        holder.priceTextView.setPaintFlags(holder.priceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.ratingBar.setRating(Float.parseFloat(mItemData.get(position).getUserRating().getRating() == null ? "0" : String.format("%.1f", Float.parseFloat(mItemData.get(position).getUserRating().getRating()) / 2)));
        holder.ratingBar.setFocusable(false);
        holder.ratingBar.setIsIndicator(true);
        holder.categoryTextView.setText(mItemData.get(position).getCategory().getName());
        if ((Float.parseFloat(mItemData.get(position).getDeposit()) - Float.parseFloat(mItemData.get(position).getConsumed())) >= 5) {
            holder.promotedView.setVisibility(View.VISIBLE);
        } else {
            holder.promotedView.setVisibility(View.INVISIBLE);
        }
        Glide
                .with(context)
                .load("https://images.9jacampusmarket.com/image.php?id=" + mItemData.get(position).getImageServer().getServerId())
                .centerCrop()
                .placeholder(R.drawable.ic_image_white_24dp)
                .into(holder.imageView);

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ItemViewActivity.class).putExtra("caption", mItemData.get(position).getCaption())
                .putExtra("id", mItemData.get(position).getId()));
            }
        });

        final AppDatabase db = RoomUtils.getInstance(context);
        final AppItemData appItemData = new AppItemData(Integer.parseInt(mItemData.get(position).getId()),
                mItemData.get(position).getCaption(), mItemData.get(position).getCategory().getName(),
                mItemData.get(position).getUserData().getPhone(), mItemData.get(position).getNeww().equals("0") ? "Fairly Used" : "New",
                mItemData.get(position).getDiscount().equals("0") ? "No" : "Yes", mItemData.get(position).getPrice(),
                mItemData.get(position).getUserData().getUsername(), String.format("%.1f", Float.parseFloat(mItemData.get(position).getUserRating().getRating()) / 2),
                mItemData.get(position).getLocation().getName(), mItemData.get(position).getLocation().getAbbr(), mItemData.get(position).getDescription(),
                mItemData.get(position).getImageServer().getServerId(), mItemData.get(position).getImageServer1() == null ? "null" :mItemData.get(position).getImageServer1().getServerId(),
                mItemData.get(position).getImageServer2() == null ? null : mItemData.get(position).getImageServer2().getServerId(), mItemData.get(position).getImageServer3() == null ? null : mItemData.get(position).getImageServer3().getServerId(),
                mItemData.get(position).getImageServer4() == null ? null : mItemData.get(position).getImageServer4().getServerId());
        new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        }.execute(new Runnable() {
            @Override
            public void run() {
                db.campusMarketDao().insertItem(appItemData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;
        public TextView locationTextView;
        public TextView likesTextView;
        public TextView captionTextView;
        public TextView discountTextView;
        public TextView priceTextView;
        public RatingBar ratingBar;
        public ImageView imageView;
        public Button viewButton;
        public TextView promotedView;
        public TextView categoryTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTextView = itemView.findViewById(R.id.campus_market_item_username);
            locationTextView = itemView.findViewById(R.id.campus_market_item_location);
            likesTextView = itemView.findViewById(R.id.campus_market_item_likes);
            captionTextView = itemView.findViewById(R.id.campus_market_item_caption);
            discountTextView = itemView.findViewById(R.id.campus_market_item_discount);
            priceTextView = itemView.findViewById(R.id.campus_market_item_price);
            ratingBar = itemView.findViewById(R.id.campus_market_item_rating);
            imageView = itemView.findViewById(R.id.campus_market_item_itemimage);
            viewButton = itemView.findViewById(R.id.campus_market_item_buttonview);
            promotedView = itemView.findViewById(R.id.promote_view);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
        }
    }
}
