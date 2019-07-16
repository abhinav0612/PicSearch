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
    private MutableLiveData<List<Hits>> mList = new MutableLiveData<>();
    private Context context;

    final private String API = "12961328-7a7e65a6d263338514a2f3f47";


    public Repository(Context context) {
       this.context=context;
    }


  void retrofitCall(Map map){

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
  LiveData<List<Hits>> getList(String category, String image_type, String editor_choice, String orderBy,String query)
  {
      Map map = new HashMap();
      map.put("key",API);
      map.put("q",query);
      map.put("order",orderBy);
      map.put("category",category);
      map.put("image_type",image_type);
      map.put("editors_choice",editor_choice);
      map.put("per_page","100");

      MyAsyncTask task = new MyAsyncTask();
      task.execute(map);
      return mList;
  }
  class MyAsyncTask extends AsyncTask<Map, Void,Void>
  {
      @Override
      protected Void doInBackground(Map... maps) {
          retrofitCall(maps[0]);
          return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
          super.onPostExecute(aVoid);
          Log.d("______","Inside Post Execute"+ mList );
      }
  }




}
