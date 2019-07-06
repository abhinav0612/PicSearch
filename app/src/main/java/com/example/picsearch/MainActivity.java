package com.example.picsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1,spinner2,spinner3,spinner4;
    Button btn;
    EditText searchText;
    String category,order,type,editor_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        searchText = findViewById(R.id.et_search);
        spinner1 = findViewById(R.id.spinner1);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner2 = findViewById(R.id.spinner2);
        spinnerAdapter();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchText.getText().toString().trim();
                searchQuery = searchQuery.replaceAll(" ","+");
                Log.e("__________","quest : " + searchQuery);
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("searchQuery",searchQuery);
                intent.putExtra("type",type);
                intent.putExtra("order",order);
                intent.putExtra("editor",editor_choice);
                startActivity(intent);
            }
        });

    }



    void spinnerAdapter(){
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.category,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category= spinner1.getItemAtPosition(position).toString().toLowerCase();
                Log.e("________","category :  " + category);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.image_type,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type= spinner2.getItemAtPosition(position).toString().toLowerCase();
                Log.e("________","type : " + type);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.order_list,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                order= spinner3.getItemAtPosition(position).toString().toLowerCase();
                Log.e("________","order : " +order);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.editor_choice,
                android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor_choice= spinner4.getItemAtPosition(position).toString().toLowerCase();
                Log.e("________","editor choice : " + editor_choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
