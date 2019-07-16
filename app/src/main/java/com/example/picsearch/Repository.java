package com.example.picsearch;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private String category;
    private String image_type;
    private String editor_choice;
    private String orderBy;
    private MutableLiveData<List<Hits>> mList = new MutableLiveData<>();
    private Context context;

    private String query;
    final private String API = "12961328-7a7e65a6d263338514a2f3f47";


    public Repository(Context context, String category, String image_type, String editor, String orderBy, String  query) {
        this.category = category;
        this.image_type = image_type;
        this.editor_choice = editor;
        this.orderBy = orderBy;
        this.query = query;
        this.context=context;
    }


  void insert()
  {
    /*  map = new HashMap();
      map.put("key",API);
      map.put("q",query);
      map.put("order",orderBy);
      map.put("category",category);
      map.put("image_type",image_type);
      map.put("editors_choice",editor_choice);
      map.put("per_page","100");
      Log.d("______","Inside insert()"+ query+" "+image_type);*/
  }

  void retrofitCall(){

        //insert();
     Map map = new HashMap();
      map.put("key",API);
      map.put("q",query);
      map.put("order",orderBy);
      map.put("category",category);
      map.put("image_type",image_type);
      map.put("editors_choice",editor_choice);
      map.put("per_page","100");
      Log.d("___","Map : " + map);
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("https://pixabay.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      API_Interface api_interface = retrofit.create(API_Interface.class);

      Call<Pixabay> call = api_interface.getImages(map);
      call.enqueue(new Callback<Pixabay>() {
          @Override
          public void onResponse(Call<Pixabay> call, Response<Pixabay> response) {
              if (!response.isSuccessful())
              {
                  Log.d("____","Error Code : "+response.code());
                  return;
              }
              Log.d("____","Response : " + response.body());
              mList.setValue(response.body().getHits());
              Log.d("___","Inside call list : "+mList);
          }

          @Override
          public void onFailure(Call<Pixabay> call, Throwable t) {
              Log.d("____","error : " +t.toString());
          }
      });
  }
  LiveData<List<Hits>> getList()
  {

      Log.d("______","Inside getList(Before)"+ mList );
      MyAsyncTask task = new MyAsyncTask();
      task.execute();
      Log.d("______","Inside getList(After)"+ mList );
      return mList;
  }
  class MyAsyncTask extends AsyncTask<Void, Void,Void>
  {
      @Override
      protected Void doInBackground(Void... voids) {
          retrofitCall();
          return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
          super.onPostExecute(aVoid);
          Log.d("______","Inside Post Execute"+ mList );
      }
  }




}
