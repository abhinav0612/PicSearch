package com.example.picsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    Retrofit retrofit;
    RecyclerViewAdapter adapter;
    Map map;
    Button filter_button;
    RecyclerView recyclerView;
    EditText currentSearch;
    API_Interface api_interface;
    ImageButton imageButton;
    ArrayList<Hits> mHits = new ArrayList<>();
    final String API = "12961328-7a7e65a6d263338514a2f3f47";
    ArrayList<Hits> sampleHits = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        filter_button = findViewById(R.id.button_filter);
        imageButton = findViewById(R.id.imageButton_search);
        currentSearch = findViewById(R.id.et_currentsearch);
        recyclerView = findViewById(R.id.recyclerview);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_interface = retrofit.create(API_Interface.class);
        final Intent intent = getIntent();
        String category =  intent.getStringExtra("category");
        String order =  intent.getStringExtra("order");
        String query =  intent.getStringExtra("searchQuery");
        String editor_choice =  intent.getStringExtra("editor");
        String type =  intent.getStringExtra("type");
        currentSearch.setText(query);
        map = new HashMap();
        map.put("key",API);
        map.put("q",query);
        map.put("order",order);
        map.put("category",category);
        map.put("image_type",type);
        map.put("editors_choice",editor_choice);
        map.put("per_page","100");
        MyasyncTask myasyncTask = new MyasyncTask();
        myasyncTask.execute(map);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSearch();
            }
        });

        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SearchActivity.this,MainActivity.class);
                intent1.putExtra("query",currentSearch.getText().toString().trim());
                startActivity(intent1);
                UIUtil.hideKeyboard(getApplicationContext(),currentSearch);
            // UIUtil.hideKeyboard(SearchActivity.this,currentSearch);
            }
        });

    }
    private class MyasyncTask extends AsyncTask<Map,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter = new RecyclerViewAdapter(SearchActivity.this,sampleHits);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
            recyclerView.setAdapter(adapter);


        }


        @Override
        protected Void doInBackground(Map... maps) {
            Call<Pixabay> call = api_interface.getImages(maps[0]);
            call.enqueue(new Callback<Pixabay>() {
                @Override
                public void onResponse(Call<Pixabay> call, Response<Pixabay> response) {
                    if (!response.isSuccessful())
                    {
                        Log.d("_____________","Error : " + response.code());
                    }
                    mHits=response.body().getHits();
                    adapter = new RecyclerViewAdapter(SearchActivity.this,mHits);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    Log.d("_____","likes : " + mHits.get(0).getLikes()+mHits.get(2).getPreviewURL());
                }

                @Override
                public void onFailure(Call<Pixabay> call, Throwable t) {

                }
            });

            return null;
        }


    }
    void ImageSearch()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_interface = retrofit.create(API_Interface.class);
        map.clear();
        map.put("key",API);
        map.put("q",currentSearch.getText().toString().trim());
        map.put("category","all");
        map.put("image_type","photo");
        map.put("per_page","100");
        MyasyncTask task = new MyasyncTask();
        task.execute(map);

    }

}
