package com.example.picsearch;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class DonwloadHelper implements Target {
    private Context context;
    private String name;
    private String description;
    private WeakReference<ContentResolver> contentResolverWeakReference;

    public DonwloadHelper(Context context, String name, String description, ContentResolver contentResolver) {
        this.context = context;
        this.name = name;
        this.description = description;
        this.contentResolverWeakReference = new WeakReference<ContentResolver>(contentResolver);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
    ContentResolver resolver = contentResolverWeakReference.get();
    if (resolver!=null)
    {
        MediaStore.Images.Media.insertImage(resolver,bitmap,name,description);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }

    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
