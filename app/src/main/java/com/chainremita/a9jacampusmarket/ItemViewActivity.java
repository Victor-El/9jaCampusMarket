package com.chainremita.a9jacampusmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chainremita.a9jacampusmarket.adapterviews.MyImageCustomRecyclerViewAdapter;
import com.chainremita.a9jacampusmarket.models.AppDatabase;
import com.chainremita.a9jacampusmarket.models.ImageO;
import com.chainremita.a9jacampusmarket.models.RoomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ItemViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter adapter;
    TextView captionTextView;
    Button callButton;
    Button chatButton;
    TextView sellerUsernameTextView;
    RatingBar ratingBar;
    TextView longLocationTextView;
    TextView shortLocationTextView;
    TextView descTextView;
    TextView phoneTextView;
    Button reviewButton;
    TextView classTextView;
    TextView priceTextView;
    TextView negotiableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        captionTextView = findViewById(R.id.item_view_title_caption_category);
        callButton = findViewById(R.id.item_view_call_button);
        chatButton = findViewById(R.id.item_view_chat_button);
        sellerUsernameTextView = findViewById(R.id.item_view_username_value);
        ratingBar = findViewById(R.id.item_view_user_rating_value);
        longLocationTextView = findViewById(R.id.item_view_location_name);
        shortLocationTextView = findViewById(R.id.item_view_location_abbr);
        descTextView = findViewById(R.id.item_view_item_description);
        phoneTextView = findViewById(R.id.item_view_phone);
        reviewButton = findViewById(R.id.item_view_review_product_button);
        classTextView = findViewById(R.id.item_view_class_text_view_value);
        priceTextView = findViewById(R.id.item_view_price_text_view_value);
        priceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceTextView.setSelected(true);
            }
        });
        negotiableTextView = findViewById(R.id.item_view_negotiable_text_view_value);
        recyclerView = findViewById(R.id.image_recyc_view_main);
        recyclerView.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this, getIntent().getStringExtra("caption"), Toast.LENGTH_LONG).show();
        final String id = getIntent().getStringExtra("id");
        new Executor() {
            @Override
            public void execute(Runnable command) {
                Log.d("Roomz", "Starting Room trans");
                new Thread(command).start();
            }
        }.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = RoomUtils.getInstance(getApplicationContext());
                String caption = db.campusMarketDao().getCaption(id);
                String category = db.campusMarketDao().getCategory(id);
                String telephone = db.campusMarketDao().getPhone(id);
                String sellerUsername = db.campusMarketDao().getSellerUserName(id);
                String rating = db.campusMarketDao().getRating(id);
                String longLocation = db.campusMarketDao().getLongLocation(id);
                String shortLocation = db.campusMarketDao().getShortLocation(id);
                String description = db.campusMarketDao().getDesc(id);
                String classValue = db.campusMarketDao().getKlass(id);
                String price = db.campusMarketDao().getPrice(id);
                String negotiable = db.campusMarketDao().getNegotiable(id);
                ImageO imageO = db.campusMarketDao().getImages(id);
                List<String> image = new ArrayList<>();
                image.add(imageO.getImage());
                image.add(imageO.getImage1());
                image.add(imageO.getImage2());
                image.add(imageO.getImage3());
                image.add(imageO.getImage4());
                complete(caption, image, category, telephone, sellerUsername, rating, longLocation, shortLocation, description,
                        classValue, price, negotiable);
            }

            public void complete(final String cap, final List<String> images, final String cat,
                                 final String tel, final String username, final String rat,
                                 final String longLocation, final String shortLocation, final String desc,
                                 final String classVal, final String priceVal, final String negoVal) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        captionTextView.setText(cap + "(" + cat + ")");
                        sellerUsernameTextView.setText("@" + username);
                        ratingBar.setRating(Float.parseFloat(rat));
                        longLocationTextView.setText(longLocation);
                        shortLocationTextView.setText("(" + shortLocation + ")");
                        descTextView.setText(desc);
                        phoneTextView.setText(tel);
                        classTextView.setText(classVal);
                        priceTextView.setText("NGN " + String.format("%,.2f", Float.parseFloat(priceVal)));
                        negotiableTextView.setText(negoVal);
                        chatButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.9jacampusmarket.com/chats")));
                                startActivity(new Intent(ItemViewActivity.this, WebActivity.class)
                                        .putExtra("url", "https://web.9jacampusmarket.com/chats"));
                            }
                        });
                        reviewButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(ItemViewActivity.this, WebActivity.class)
                                        .putExtra("url", "https://web.9jacampusmarket.com/product/" + cap.replaceAll(" ", "-") + "/" + id));
                            }
                        });
                        callButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null)));
                            }
                        });
                        Toast.makeText(ItemViewActivity.this, cap, Toast.LENGTH_SHORT).show();
                        Log.d("Roomz", cap);
                        adapter = new MyImageCustomRecyclerViewAdapter(getApplicationContext(), images, id, getSupportFragmentManager());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
