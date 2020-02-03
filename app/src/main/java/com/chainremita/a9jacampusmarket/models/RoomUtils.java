package com.chainremita.a9jacampusmarket.models;

import android.content.Context;

import androidx.room.Room;

public class RoomUtils {
    private static AppDatabase db;
    private RoomUtils() {

    }

    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "campus-market-db").build();
            return db;

        } else {
            return db;
        }
    }
}
