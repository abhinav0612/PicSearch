package com.example.picsearch;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Context mcontext;
    private LiveData<List<Hits>> mList;
    Repository repository;


    public ViewModel(@NonNull Application application ) {
        super(application);
        this.mcontext = application;
        repository = new Repository(mcontext);
    }

    LiveData<List<Hits>> getList(String category, String image_type, String editor_choice, String orderBy,String query)
    {
        mList = repository.getList(category,image_type,editor_choice,orderBy,query);
        return mList;
    }
}
