package com.chainremita.a9jacampusmarket.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.chainremita.a9jacampusmarket.R;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewDialogFragment extends DialogFragment {
    private String imageId;

    public PhotoViewDialogFragment(String id) {
        this.imageId = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.photo_view_dialog_fragment, null);
        builder.setView(v);
        ImageView closeBtn = v.findViewById(R.id.close_button);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewDialogFragment.this.dismiss();
            }
        });
        PhotoView photoView = v.findViewById(R.id.photo_view);
        Glide.with(getContext())
                .load("https://images.9jacampusmarket.com/image.php?id=" + imageId)
                .centerCrop()
                .placeholder(R.drawable.ic_image_white_24dp)
                .override(width, width)
                .into(photoView);
        return builder.create();
    }
}
