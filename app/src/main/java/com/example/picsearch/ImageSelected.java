package com.example.picsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ImageSelected extends AppCompatActivity {
    ImageButton downloadButton,sharButton;
    TextView downlaods,likes;
    ImageView imageView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 )
        {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selected);
        sharButton = findViewById(R.id.shareButton);
        imageView = findViewById(R.id.imageViewLarge);
        likes=findViewById(R.id.likes);
        downlaods=findViewById(R.id.downloads);
        downloadButton=findViewById(R.id.downloaButton);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        /////
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        Integer downloads=intent.getIntExtra("downloads",0);
        Integer likes_count=intent.getIntExtra("likes",0);
        final String showUrl = intent.getStringExtra("showUl");


        downlaods.setText("Downloads : "+downloads);
        likes.setText("Likes : " + likes_count);
        Picasso.get().load(url).into(imageView);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ImageSelected.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(ImageSelected.this,"You need to grant Permission",Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    return;
                }
                else
                {
                    String fileName = UUID.randomUUID().toString()+".jpg";
                    Picasso.get().load(url).into(new DonwloadHelper(ImageSelected.this,fileName,
                            "This is the image",getApplicationContext().getContentResolver()));
                   Toast.makeText(ImageSelected.this,"Image Downloaded",Toast.LENGTH_SHORT).show();

                }

            }
        });
        sharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });

    }
    void shareImage() {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Uri uri =  getImageUri(this,bitmap);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        startActivity(Intent.createChooser(intent, "Sharing to..."));
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}

