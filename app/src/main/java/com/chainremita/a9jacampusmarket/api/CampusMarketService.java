package com.chainremita.a9jacampusmarket.api;

import com.chainremita.a9jacampusmarket.api.filterobject.FCObject;
import com.chainremita.a9jacampusmarket.api.filterobject.FCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FLObject;
import com.chainremita.a9jacampusmarket.api.filterobject.FLocation;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterLocation;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterObject;
import com.chainremita.a9jacampusmarket.api.regularitems.Item;
import com.chainremita.a9jacampusmarket.api.sponsored.SponsoredItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CampusMarketService {

    @Headers({
            "Authorization: Bearer 123456789",
            "Cache-Control: max-age=640000"
    })
    @POST("list_items")
    Call<Item> getItem();

    @Headers({
            "Authorization: Bearer 123456789",
            "Cache-Control: max-age=640000"
    })
    @POST("list_sponsored")
    Call<SponsoredItem> getSponsoredItem();

    @Headers("Authorization: Bearer 123456789")
    @POST("locations")
    Call<FLocation> getCountries();

    @Headers("Authorization: Bearer 123456789")
    @POST("categories")
    Call<FCategory> getCategories();

    @Headers("Authorization: Bearer 123456789")
    @POST("list_items")
    Call<Item> filter(@Body FilterObject filterObject);

    @Headers("Authorization: Bearer 123456789")
    @POST("list_items")
    Call<Item> search(@Body SearchQuery searchQuery);
}
