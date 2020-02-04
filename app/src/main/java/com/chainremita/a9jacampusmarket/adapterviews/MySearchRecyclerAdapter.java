package com.chainremita.a9jacampusmarket.adapterviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chainremita.a9jacampusmarket.ItemViewActivity;
import com.chainremita.a9jacampusmarket.R;
import com.chainremita.a9jacampusmarket.api.regularitems.ItemData;
import com.chainremita.a9jacampusmarket.models.AppDatabase;
import com.chainremita.a9jacampusmarket.models.AppItemData;
import com.chainremita.a9jacampusmarket.models.RoomUtils;

import java.util.List;
import java.util.concurrent.Executor;

public class MySearchRecyclerAdapter extends RecyclerView.Adapter<MySearchRecyclerAdapter.MyViewHolder> {
    Context context;
    List<ItemData> dataList;

    public MySearchRecyclerAdapter(Context context, List<ItemData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.searchCaptionTv.setText(dataList.get(position).getCaption());
        holder.usernameTv.setText("@" + dataList.get(position).getUserData().getUsername());
        holder.descriptionTv.setText(dataList.get(position).getCategory().getName());
        holder.ratingTv.setText(dataList.get(position).getUserRating().getRating() == null ? "0" : String.format("%.1f", Float.parseFloat(dataList.get(position).getUserRating().getRating()) / 2));
        holder.priceTv.setText("NGN " + String.format("%,.2f", Float.parseFloat(dataList.get(position).getPrice())));

        Glide
                .with(context)
                .load("https://images.9jacampusmarket.com/image.php?id=" + dataList.get(position).getImageServer().getServerId())
                .centerCrop()
                .placeholder(R.drawable.ic_image_white_24dp)
                //.override(300, 400)
                .into(holder.imageView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ItemViewActivity.class).putExtra("caption", dataList.get(position).getCaption())
                        .putExtra("id", dataList.get(position).getId()));
            }
        });

        final AppDatabase db = RoomUtils.getInstance(context);
        final AppItemData appItemData = new AppItemData(Integer.parseInt(dataList.get(position).getId()),
                dataList.get(position).getCaption(), dataList.get(position).getCategory().getName(),
                dataList.get(position).getUserData().getPhone(), dataList.get(position).getNeww().equals("0") ? "Fairly Used" : "New",
                dataList.get(position).getDiscount().equals("0") ? "No" : "Yes", dataList.get(position).getPrice(),
                dataList.get(position).getUserData().getUsername(), String.format("%.1f", Float.parseFloat(dataList.get(position).getUserRating().getRating()) / 2),
                dataList.get(position).getLocation().getName(), dataList.get(position).getLocation().getAbbr(), dataList.get(position).getDescription(),
                dataList.get(position).getImageServer().getServerId(), dataList.get(position).getImageServer1() == null ? "null" :dataList.get(position).getImageServer1().getServerId(),
                dataList.get(position).getImageServer2() == null ? null : dataList.get(position).getImageServer2().getServerId(), dataList.get(position).getImageServer3() == null ? null : dataList.get(position).getImageServer3().getServerId(),
                dataList.get(position).getImageServer4() == null ? null : dataList.get(position).getImageServer4().getServerId());
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
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView searchCaptionTv, descriptionTv, usernameTv,ratingTv, priceTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.search_image_view);
            searchCaptionTv = itemView.findViewById(R.id.search_caption_tv);
            descriptionTv = itemView.findViewById(R.id.search_desc_tv);
            usernameTv = itemView.findViewById(R.id.search_username_tv);
            ratingTv = itemView.findViewById(R.id.search_username_rating);
            priceTv = itemView.findViewById(R.id.search_price_tv);
        }
    }
}
