package com.example.picsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    RecyclerView recyclerView;
    TextView currentSearch;
    API_Interface api_interface;
    ArrayList<Hits> mHits = new ArrayList<>();
    final String API = "12961328-7a7e65a6d263338514a2f3f47";
    ArrayList<Hits> sampleHits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        currentSearch = findViewById(R.id.tv_currentsearch);
        recyclerView = findViewById(R.id.recyclerview);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_interface = retrofit.create(API_Interface.class);
        Intent intent = getIntent();
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
        MyasyncTask myasyncTask = new MyasyncTask();
        myasyncTask.execute();



    }
    private class MyasyncTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter = new RecyclerViewAdapter(SearchActivity.this,sampleHits);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<Pixabay> call = api_interface.getImages(map);
            call.enqueue(new Callback<Pixabay>() {
                @Override
                public void onResponse(Call<Pixabay> call, Response<Pixabay> response) {
                    if (!response.isSuccessful())
                    {
                        Log.d("_____________","Error : " + response.code());
                    }
                    mHits = response.body().getHits();
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
}
