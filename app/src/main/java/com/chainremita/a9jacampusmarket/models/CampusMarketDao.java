package com.chainremita.a9jacampusmarket.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CampusMarketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItem(AppItemData itemData);

    @Query("SELECT caption FROM AppItemData WHERE id = :id")
    public String getCaption(String id);

    @Query("SELECT image, image1, image2, image3, image4 FROM AppItemData WHERE id = :id")
    public ImageO getImages(String id);

    @Query("SELECT category FROM AppItemData WHERE id = :id")
    public String getCategory(String id);

    @Query("SELECT phone FROM AppItemData WHERE id = :id")
    public String getPhone(String id);

    @Query("SELECT seller_username FROM AppItemData WHERE id = :id")
    public String getSellerUserName(String id);

    @Query("SELECT seller_rating FROM AppItemData WHERE id = :id")
    public String getRating(String id);

    @Query("SELECT location_name FROM AppItemData WHERE id = :id")
    public String getLongLocation(String id);

    @Query("SELECT location_abbr FROM AppItemData WHERE id = :id")
    public String getShortLocation(String id);

    @Query("SELECT description FROM AppItemData WHERE id = :id")
    public String getDesc(String id);

    @Query("SELECT price FROM AppItemData WHERE id = :id")
    public String getPrice(String id);

    @Query("SELECT class FROM AppItemData WHERE id = :id")
    public String getKlass(String id);

    @Query("SELECT negotiable FROM AppItemData WHERE id = :id")
    public String getNegotiable(String id);
}
