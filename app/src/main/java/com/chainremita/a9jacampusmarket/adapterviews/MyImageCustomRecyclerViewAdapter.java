package com.chainremita.a9jacampusmarket.adapterviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chainremita.a9jacampusmarket.Fragments.PhotoViewDialogFragment;
import com.chainremita.a9jacampusmarket.R;

import java.util.List;

public class MyImageCustomRecyclerViewAdapter extends RecyclerView.Adapter<MyImageCustomRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<String> images;
    private String imageId;
    private FragmentManager fragmentManager;

    public MyImageCustomRecyclerViewAdapter(Context context, List<String> images, String id, FragmentManager fragmentManager) {
        this.context = context;
        this.images = images;
        this.imageId = id;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_view_item_detail, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context)
                .load("https://images.9jacampusmarket.com/image.php?id=" + images.get(position))
                .centerCrop()
                .placeholder(R.drawable.ic_image_white_24dp)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoViewDialogFragment(images.get(position)).show(fragmentManager, "PhotoViewFragmentDialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyc_main_image_view);
        }
    }
}
