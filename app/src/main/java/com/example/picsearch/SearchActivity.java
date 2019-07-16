package com.example.picsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerViewAdapter adapter;
    Button filter_button;
    RecyclerView recyclerView;
    EditText currentSearch;
    ImageButton imageButton;
    List<Hits> mHits = new ArrayList<>();
    String category;
    String order;
    String query;
    String editor_choice;
    String type;
    ViewModel model;
    LiveData<List<Hits>> myHits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        filter_button = findViewById(R.id.button_filter);
        imageButton = findViewById(R.id.imageButton_search);
        currentSearch = findViewById(R.id.et_currentsearch);
        recyclerView = findViewById(R.id.recyclerview);


        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        final Intent intent = getIntent();
        category = intent.getStringExtra("category");
        order = intent.getStringExtra("order");
        query = intent.getStringExtra("searchQuery");
        editor_choice = intent.getStringExtra("editor");
        type = intent.getStringExtra("type");
        currentSearch.setText(query);

        recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(this, mHits);
        recyclerView.setAdapter(adapter);
        //if using ViewModelFactory
        /*model = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication()
                , category, type, editor_choice, order, query)).get(ViewModel.class);*/
        model = ViewModelProviders.of(this).get(ViewModel.class);


        myHits = model.getList(category,type,editor_choice,order,query);
        myHits.observe(this, new Observer<List<Hits>>() {
            @Override
            public void onChanged(List<Hits> hits) {
                adapter = new RecyclerViewAdapter(SearchActivity.this,hits);
                recyclerView.setAdapter(adapter);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearch();
            }
        });

        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SearchActivity.this, MainActivity.class);
                intent1.putExtra("query", currentSearch.getText().toString().trim());
                startActivity(intent1);
            }
        });
    }

    void newSearch() {
        String newSearch = currentSearch.getText().toString();
        myHits = model.getList(category,type,editor_choice,order,newSearch);

    }

}
