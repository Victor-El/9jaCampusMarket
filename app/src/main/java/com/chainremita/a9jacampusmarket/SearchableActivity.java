package com.chainremita.a9jacampusmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chainremita.a9jacampusmarket.adapterviews.MyItemRecyclerAdapter;
import com.chainremita.a9jacampusmarket.adapterviews.MySearchRecyclerAdapter;
import com.chainremita.a9jacampusmarket.api.CampusMarketService;
import com.chainremita.a9jacampusmarket.api.SearchQuery;
import com.chainremita.a9jacampusmarket.api.regularitems.Item;
import com.chainremita.a9jacampusmarket.api.regularitems.ItemData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchableActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    //StaggeredGridLayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView notFoundTextView;
    private String queryText;
    private List<ItemData> dataList;
    private String searchResult;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        constraintLayout = findViewById(R.id.searchable_constraint_root);
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.hasFixedSize();
        //layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)/*new LinearLayoutManager(this)*/;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.search_swipe_refresh);
        notFoundTextView = findViewById(R.id.search_not_found_tv);
        notFoundTextView.setVisibility(View.GONE);
        dataList = new ArrayList<>();

        if (savedInstanceState != null) {
            List<ItemData> restoredList = new ArrayList<>();
            searchResult = savedInstanceState.getString("search-result");
            restoredList = new Gson().fromJson(searchResult, new TypeToken<List<ItemData>>(){}.getType());
            handleIntentOffline(restoredList);
        } else {
            handleIntent(getIntent());
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handleIntent(getIntent());
            }
        });

        if (savedInstanceState != null) {
            constraintLayout.setBackgroundResource(0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", queryText);
        searchResult = new Gson().toJson(dataList, new TypeToken<List<ItemData>>(){}.getType());
        outState.putString("search-result", searchResult);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        constraintLayout.setBackgroundResource(R.drawable.off);
        swipeRefreshLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.9jacampusmarket.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CampusMarketService campusMarketService = retrofit.create(CampusMarketService.class);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            queryText = query;

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            Call<Item> search = campusMarketService.search(new SearchQuery(query));
            search.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    List<ItemData> itemDataList = response.body().getData();
                    dataList.addAll(itemDataList);
                    adapter = new MySearchRecyclerAdapter(getApplicationContext(), itemDataList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                    if (itemDataList.size() < 1) {
                        recyclerView.setVisibility(View.INVISIBLE);
                        notFoundTextView.setVisibility(View.VISIBLE);
                    }
                    constraintLayout.setBackgroundResource(0);
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    constraintLayout.setBackgroundResource(0);
                    Toast.makeText(SearchableActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                    Log.d("search", t.getLocalizedMessage());
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.INVISIBLE);
                    notFoundTextView.setVisibility(View.VISIBLE);
                    notFoundTextView.setText(t.getLocalizedMessage());
                }
            });

            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
        }


    }

    private void handleIntentOffline(List<ItemData> data) {
        dataList = new ArrayList<>();
        swipeRefreshLayout.setRefreshing(true);
        List<ItemData> itemDataL = data;
        dataList.addAll(itemDataL);
        adapter = new MySearchRecyclerAdapter(getApplicationContext(), itemDataL);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

        if (itemDataL.size() < 1) {
            recyclerView.setVisibility(View.INVISIBLE);
            notFoundTextView.setVisibility(View.VISIBLE);
        }
    }
}
