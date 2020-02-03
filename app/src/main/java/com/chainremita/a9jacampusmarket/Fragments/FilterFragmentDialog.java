package com.chainremita.a9jacampusmarket.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chainremita.a9jacampusmarket.R;
import com.chainremita.a9jacampusmarket.api.CampusMarketService;
import com.chainremita.a9jacampusmarket.api.filterobject.FCObject;
import com.chainremita.a9jacampusmarket.api.filterobject.FCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FLObject;
import com.chainremita.a9jacampusmarket.api.filterobject.FLocation;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterCategory;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterLocation;
import com.chainremita.a9jacampusmarket.api.filterobject.FilterObject;
import com.chainremita.a9jacampusmarket.api.regularitems.Item;
import com.chainremita.a9jacampusmarket.api.regularitems.ItemData;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterFragmentDialog extends DialogFragment implements Validator.ValidationListener {
    @NotEmpty(trim = true)
    private EditText startDateEditText, endDateEditText, startPriceEditText, endPriceEditText, onlyFromEditText;
    @Select
    private Spinner locationSpinner, categorySpinner;
    private DatePickerDialog datePickerDialog;
    private Validator validator;
    private Retrofit retrofit;
    private CampusMarketService campusMarketService;

    private String reuseLoc;
    private String reuseCat;
    private  Context context;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_fragment_dialog_layout, container);
        return v;
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_fragment_dialog_layout, null);
        builder.setView(view);


        validator = new Validator(this);
        validator.setValidationListener(this);

        startDateEditText = view.findViewById(R.id.filter_start_date);
        endDateEditText = view.findViewById(R.id.filter_end_date);
        startPriceEditText = view.findViewById(R.id.filter_edittext_min_price);
        endPriceEditText = view.findViewById(R.id.filter_edittext_max_price);
        onlyFromEditText = view.findViewById(R.id.filter_only_from_field);
        locationSpinner = view.findViewById(R.id.filter_location_spinner);
        categorySpinner = view.findViewById(R.id.filter_category_spinner);

        updateLocation();
        updateCategory();

        startDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            startDateEditText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        endDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            endDateEditText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Accepted", Toast.LENGTH_SHORT).show();
                applyFilter();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

    @Override
    public void onValidationSucceeded() {

    }

    public void updateLocation() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NAIJA", Context.MODE_PRIVATE);
        String jsonLocation = sharedPreferences.getString("loc", "");
        List<FilterLocation> filterLocationList = gson.fromJson(jsonLocation, new TypeToken<List<FilterLocation>>(){}.getType());
        List<String> stringOfLoc = new ArrayList<>();
        stringOfLoc.add("select a location");
        for (FilterLocation fLoc: filterLocationList) {
            stringOfLoc.add(fLoc.getName());
        }
        reuseLoc = jsonLocation;
        locationSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stringOfLoc));
    }

    public void updateCategory() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NAIJA", Context.MODE_PRIVATE);
        String jsonCategory = sharedPreferences.getString("cat", "");
        List<FilterCategory> filterCategoryList = gson.fromJson(jsonCategory, new TypeToken<List<FilterCategory>>(){}.getType());
        List<String> stringOfCat = new ArrayList<>();
        stringOfCat.add("select a category");
        for (FilterCategory fCat: filterCategoryList) {
            stringOfCat.add(fCat.getName());
        }
        reuseCat = jsonCategory;
        categorySpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stringOfCat));
    }

    public void applyFilter() {
        String date1 = startDateEditText.getText().toString();
        String date2 = endDateEditText.getText().toString();
        String price1 = startPriceEditText.getText().toString().trim();
        String price2 = endPriceEditText.getText().toString().trim();
        String location = ((TextView) locationSpinner.getSelectedView()).getText().toString();
        String category = ((TextView) categorySpinner.getSelectedView()).getText().toString();
        String username = onlyFromEditText.getText().toString().trim();

        List<FilterCategory> filterCategoryList = new Gson().fromJson(reuseCat, new TypeToken<List<FilterCategory>>(){}.getType());
        List<FilterLocation> filterLocationList = new Gson().fromJson(reuseLoc, new TypeToken<List<FilterLocation>>(){}.getType());

        for (FilterLocation floc: filterLocationList) {
            if (floc.getName().equals(location)) {
                location = floc.getAbbr();
            }
        }

        if (location.equals("select a location")) {
            location = "";
        }

        for (FilterCategory fcat: filterCategoryList) {
            if (fcat.getName().equals(category)) {
                category = fcat.getId();
            }
        }

        if (category.equals("select a category")) {
            category = "";
        }
        FilterObject filterObject = new FilterObject(price1, price2, location, category, date1, date2, username);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.9jacampusmarket.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        campusMarketService = retrofit.create(CampusMarketService.class);

        campusMarketService.filter(filterObject).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                //Log.d("FilterRes", "Status: "+response.body().getData().get(0).getCaption());
                Gson gson = new Gson();
                List<ItemData> itemList = response.body().getData();
                String jsonItemData = gson.toJson(itemList, new TypeToken<List<ItemData>>(){}.getType());
                context.sendBroadcast(new Intent("filtered").putExtra("data", jsonItemData));
                Log.d("end", "finished filter");
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                FilterFragmentDialog.this.dismiss();
                Log.d("FilterErr", "Error: "+t.getLocalizedMessage());
                //Snackbar.make(getActivity().findViewById(R.id.main_content), "Filter failed: Network Error", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError err: errors) {
            Toast.makeText(getActivity(), err.getCollatedErrorMessage(getActivity()), Toast.LENGTH_LONG).show();
        }
    }
}
