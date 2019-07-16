package com.example.picsearch;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Context mcontext;
    private String category;
    private String image_type;
    private String editor_choice;
    private String orderBy;
    private String query;
    private LiveData<List<Hits>> mList;
    Repository repository;


    public ViewModel(@NonNull Application application, String category, String image_type, String editor_choice, String orderBy,String query) {
        super(application);
        this.mcontext = application;
        this.category = category;
        this.image_type = image_type;
        this.editor_choice = editor_choice;
        this.orderBy = orderBy;
        this.query = query;
        repository = new Repository(mcontext,category,image_type,editor_choice,orderBy,query);

        this.mList = repository.getList();
    }

    LiveData<List<Hits>> getList()
    {
        return mList;
    }
}
