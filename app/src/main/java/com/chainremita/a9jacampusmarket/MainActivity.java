package com.chainremita.a9jacampusmarket;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.chainremita.a9jacampusmarket.Fragments.FilterFragmentDialog;
import com.chainremita.a9jacampusmarket.adapterviews.MyItemRecyclerAdapter;
import com.chainremita.a9jacampusmarket.adapterviews.MySponsoredItemRecyclerAdapter;
import com.chainremita.a9jacampusmarket.api.CampusMarketService;
import com.chainremita.a9jacampusmarket.api.filterobject.FCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FLocation;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterLocation;
import com.chainremita.a9jacampusmarket.api.regularitems.Item;
import com.chainremita.a9jacampusmarket.api.regularitems.ItemData;
import com.chainremita.a9jacampusmarket.api.sponsored.SponsoredItem;
import com.chainremita.a9jacampusmarket.api.sponsored.SponsoredItemData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RecyclerView recyclerViewOld;
    private RecyclerView.LayoutManager layoutManagerOld;
    private RecyclerView.Adapter adapterOld;

    private RecyclerView recyclerViewSponsored;
    private RecyclerView.LayoutManager layoutManagerSponsored;
    private RecyclerView.Adapter adapterSponsored;

    private ProgressDialog pd;

    private ProgressBar progressBar;

    private EditText editText;

    private List<List<ItemData>> glob;

    Bundle newBundle;

    BroadcastReceiver filterBroadcastReceiver;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ConstraintLayout constraintLayout;
    private CampusMarketService campusMarketService;
    private Retrofit retrofit;

    private TextView textViewForNewItems, textViewForOldItems, textViewForSponsored;
    private TextView newItemsNotFound, fairlyUsedItemsNotFound, sponsoredItemsNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        constraintLayout = findViewById(R.id.linearLayout2);
        newItemsNotFound = findViewById(R.id.new_items_not_found);
        fairlyUsedItemsNotFound = findViewById(R.id.fairly_used_items_not_found);
        sponsoredItemsNotFound = findViewById(R.id.sponsored_items_not_found);

        progressBar = findViewById(R.id.progressbar);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewOld = findViewById(R.id.my_old_recycler_view);
        recyclerViewOld.hasFixedSize();
        layoutManagerOld = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOld.setLayoutManager(layoutManagerOld);

        recyclerViewSponsored = findViewById(R.id.my_sponsored_recycler_view);
        recyclerView.hasFixedSize();
        layoutManagerSponsored = new LinearLayoutManager(this);
        recyclerViewSponsored.setLayoutManager(layoutManagerSponsored);

        textViewForNewItems = findViewById(R.id.for_new_items);
        textViewForOldItems = findViewById(R.id.for_old_items);
        textViewForSponsored = findViewById(R.id.for_sponsored_items);

        glob = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.9jacampusmarket.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        campusMarketService = retrofit.create(CampusMarketService.class);
        if (savedInstanceState != null) {
            newBundle = savedInstanceState;
            Gson gson = new Gson();
            List<List<ItemData>> fromJson = new ArrayList<>();
            List<ItemData> retrievedData0 = new ArrayList<>();//gson.fromJson(savedInstanceState.getString("list-data0"), new TypeToken<List<ItemData>>(){}.getType());
            retrievedData0.addAll((List<ItemData>) gson.fromJson(savedInstanceState.getString("list-data0"), new TypeToken<List<ItemData>>(){}.getType()));
            List<ItemData> retrievedData1 = new ArrayList<>();//gson.fromJson(savedInstanceState.getString("list-data1"), new TypeToken<List<ItemData>>(){}.getType());
            retrievedData1.addAll((List<ItemData>) gson.fromJson(savedInstanceState.getString("list-data1"), new TypeToken<List<ItemData>>(){}.getType()));
            fromJson.add(retrievedData0);
            fromJson.add(retrievedData1);
            loadData(fromJson);

            glob.addAll(fromJson);
            //Toast.makeText(this, retrievedData1.get(0).getCaption(), Toast.LENGTH_LONG).show();
        } else {
            setUpData();
            getAndPersistLocationAndCategories();
        }

        if (newBundle == null) {
            textViewForNewItems.setVisibility(View.INVISIBLE);
            textViewForOldItems.setVisibility(View.INVISIBLE);
            textViewForSponsored.setVisibility(View.INVISIBLE);
        }

        newItemsNotFound.setVisibility(View.GONE);
        fairlyUsedItemsNotFound.setVisibility(View.GONE);
        sponsoredItemsNotFound.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        filterBroadcastReceiver = new FilterBroadcastReciever();
        registerReceiver(filterBroadcastReceiver, new IntentFilter("filtered"));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newItemsNotFound.setVisibility(View.GONE);
                fairlyUsedItemsNotFound.setVisibility(View.GONE);
                sponsoredItemsNotFound.setVisibility(View.GONE);
                constraintLayout.setBackgroundResource(R.drawable.off);
                textViewForNewItems.setVisibility(View.INVISIBLE);
                textViewForOldItems.setVisibility(View.INVISIBLE);
                textViewForSponsored.setVisibility(View.INVISIBLE);
                setUpData();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        unregisterReceiver(filterBroadcastReceiver);
        Gson gson = new Gson();
        List<List<ItemData>> toJson = new ArrayList<>();
        toJson.addAll(glob);
        String json0 = gson.toJson(toJson.get(0), new TypeToken<List<ItemData>>(){}.getType());
        //Toast.makeText(this, json0, Toast.LENGTH_LONG).show();
        outState.putString("list-data0", json0);

        String json1 = gson.toJson(toJson.get(1), new TypeToken<List<ItemData>>(){}.getType());
        outState.putString("list-data1", json1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_filter) {
            FilterFragmentDialog filterFragmentDialog = new FilterFragmentDialog();
            filterFragmentDialog.show(getSupportFragmentManager(), "FilterFragmentDialog");
            return true;
        }

        if (id == R.id.action_refresh) {
            newItemsNotFound.setVisibility(View.GONE);
            fairlyUsedItemsNotFound.setVisibility(View.GONE);
            sponsoredItemsNotFound.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(true);
            constraintLayout.setBackgroundResource(R.drawable.off);
            textViewForNewItems.setVisibility(View.INVISIBLE);
            textViewForOldItems.setVisibility(View.INVISIBLE);
            textViewForSponsored.setVisibility(View.INVISIBLE);
            setUpData();
        }

        if (id == R.id.action_clear_search) {
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.clearHistory();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpData() {
        newItemsNotFound.setVisibility(View.GONE);
        fairlyUsedItemsNotFound.setVisibility(View.GONE);
        sponsoredItemsNotFound.setVisibility(View.GONE);
        loadData(getData());
        runSponsored(getSponsored());
        newItemsNotFound.setVisibility(View.GONE);
        fairlyUsedItemsNotFound.setVisibility(View.GONE);
        sponsoredItemsNotFound.setVisibility(View.GONE);
    }

    public void runSponsored(List<SponsoredItemData> sponsoredItemData) {
        adapterSponsored = new MySponsoredItemRecyclerAdapter(sponsoredItemData, this);
        recyclerViewSponsored.setAdapter(adapterSponsored);
        adapterSponsored.notifyDataSetChanged();
        if (sponsoredItemData.size() < 1) {
            textViewForSponsored.setVisibility(View.INVISIBLE);
            sponsoredItemsNotFound.setVisibility(View.VISIBLE);
        }
    }

    public List<SponsoredItemData> getSponsored() {
        Call<SponsoredItem> call = campusMarketService.getSponsoredItem();

        final List<SponsoredItemData> sponsoredItems = new ArrayList<>();

        call.enqueue(new Callback<SponsoredItem>() {
            @Override
            public void onResponse(Call<SponsoredItem> call, Response<SponsoredItem> response) {
                if (response.isSuccessful()) {

                    sponsoredItems.addAll(response.body().getData());
                    adapterSponsored.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                    Log.d("SponsoredSuccessErrRes", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SponsoredItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load \"Contents for you\"", Toast.LENGTH_SHORT).show();
                Log.d("SponsoredResponseError", "Failed to load \"Contents for you\" " + t.getMessage());
            }
        });
        return sponsoredItems;
    }

    public List<List<ItemData>> getData() {
        progressBar.setVisibility(View.VISIBLE);
        final List<ItemData> listOfItemData = new ArrayList<>();
        final List<ItemData> listOfItemData1 = new ArrayList<>();


        Call<Item> call = campusMarketService.getItem();

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.d("CodeEnzyme", "Success "+response.body().toString());

                if (response.isSuccessful()) {
                    constraintLayout.setBackgroundResource(0);
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    //listOfItemData.addAll(response.body().getData());

                    for (ItemData datum: response.body().getData()) {
                        if (datum.getNeww().equals("0")) {
                            listOfItemData.add(datum);
                        } else {
                            listOfItemData1.add(datum);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    adapterOld.notifyDataSetChanged();
                }

                //pd.dismiss();
                progressBar.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                textViewForNewItems.setVisibility(View.VISIBLE);
                textViewForOldItems.setVisibility(View.VISIBLE);
                textViewForSponsored.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                //pd.dismiss();
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("CodeEnzyme", "Error "+t.getMessage());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Error");
                builder.setMessage("Check your internet connection");

                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadData(getData());
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        List<List<ItemData>> lisOfListOfItemData = new ArrayList<>();
        lisOfListOfItemData.add(listOfItemData);
        lisOfListOfItemData.add(listOfItemData1);

        glob.clear();
        glob.add(listOfItemData);
        glob.add(listOfItemData1);

        return lisOfListOfItemData;
    }

    public boolean loadData(List<List<ItemData>> data) {
        progressBar.setVisibility(View.VISIBLE);
//        pd = new ProgressDialog(MainActivity.this);
//        pd.setTitle("Please wait ...");
//        pd.setMessage("Getting Items ...");
//        pd.setCancelable(false);
//        pd.show();

        progressBar.setVisibility(View.VISIBLE);

        adapter = new MyItemRecyclerAdapter(data.get(1), this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (data.get(1).size() < 1) {
            textViewForNewItems.setVisibility(View.INVISIBLE);
            newItemsNotFound.setVisibility(View.VISIBLE);
        }

        adapterOld = new MyItemRecyclerAdapter(data.get(0), this);
        recyclerViewOld.setAdapter(adapterOld);
        adapterOld.notifyDataSetChanged();
        if (data.get(0).size() < 1) {
            textViewForOldItems.setVisibility(View.INVISIBLE);
            fairlyUsedItemsNotFound.setVisibility(View.VISIBLE);
        }

        if (newBundle != null) {
            progressBar.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setRefreshing(false);
            constraintLayout.setBackgroundResource(0);
            runSponsored(getSponsored());
        }

        return true;

    }

    public void getAndPersistLocationAndCategories() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.9jacampusmarket.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        campusMarketService = retrofit.create(CampusMarketService.class);
        Call<FCategory> listCallCategory = campusMarketService.getCategories();
        Call<FLocation> listCallLocation = campusMarketService.getCountries();

        final Gson gson = new Gson();
        listCallCategory.enqueue(new Callback<FCategory>() {
            @Override
            public void onResponse(Call<FCategory> call, Response<FCategory> response) {
                SharedPreferences.Editor editor = getSharedPreferences("NAIJA", MODE_PRIVATE).edit();
                List<FilterCategory> fCategoryList = response.body().getFilterCategoryList();
                String categoryList = gson.toJson(fCategoryList, new TypeToken<List<FilterCategory>>(){}.getType());
                editor.putString("cat", categoryList);
                editor.apply();
            }

            @Override
            public void onFailure(Call<FCategory> call, Throwable t) {

            }
        });

        listCallLocation.enqueue(new Callback<FLocation>() {
            @Override
            public void onResponse(Call<FLocation> call, Response<FLocation> response) {
                SharedPreferences.Editor editor = getSharedPreferences("NAIJA", MODE_PRIVATE).edit();
                List<FilterLocation> fLocationList = response.body().getFilterLocationList();
                String locationList = gson.toJson(fLocationList, new TypeToken<List<FilterLocation>>(){}.getType());
                editor.putString("loc", locationList);
                editor.apply();
            }

            @Override
            public void onFailure(Call<FLocation> call, Throwable t) {

            }
        });
    }

    public void doFilter(List<List<ItemData>> itemDataLists) {
        swipeRefreshLayout.setRefreshing(true);
        Toast.makeText(this, "Filtered List of items", Toast.LENGTH_LONG).show();
        boolean didFilter = loadData(itemDataLists);
        if (didFilter) {
            progressBar.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    class FilterBroadcastReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("filtered")) {
                Toast.makeText(context, "Broadcast Recieved", Toast.LENGTH_SHORT).show();
                Log.d("broad", intent.getStringExtra("data"));
                String data = intent.getStringExtra("data");
                List<ItemData> listOfItemData = new Gson().fromJson(data, new TypeToken<List<ItemData>>(){}.getType());
                List<List<ItemData>> listOfListOfItemData = getListOfItemDatalist(listOfItemData);
                doFilter(listOfListOfItemData);
            }
        }

        public List<List<ItemData>> getListOfItemDatalist(List<ItemData> dataList) {
            List<List<ItemData>> datalists = new ArrayList<>();
            List<ItemData> itemDataList = new ArrayList<>();
            List<ItemData> itemDataList1 = new ArrayList<>();
            for (ItemData itemData: dataList) {
                if (itemData.getNeww().equals("0")) {
                    itemDataList.add(itemData);
                } else if (itemData.getNeww().equals("1")) {
                    itemDataList1.add(itemData);
                }
            }
            datalists.add(itemDataList);
            datalists.add(itemDataList1);
            return datalists;
        }
    }
}
