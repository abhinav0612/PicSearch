package com.example.picsearch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class MyViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    /**
     * Creates a {@code AndroidViewModelFactory}
     **/

    private Application context;
    private String category;
    private String image_type;
    private String editor_choice;
    private String orderBy;
    private String query;
    public MyViewModelFactory(@NonNull Application application,String category, String image_type,
                              String editor, String orderBy, String  query) {
        super(application);
        this.category = category;
        this.image_type = image_type;
        this.editor_choice = editor;
        this.orderBy = orderBy;
        this.query = query;
        this.context=application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new com.example.picsearch.ViewModel(context,category,image_type
                            ,editor_choice,orderBy,query);
    }
}
