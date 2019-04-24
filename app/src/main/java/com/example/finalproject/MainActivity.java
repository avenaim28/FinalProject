package com.example.finalproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Set up handlers for each button in our UI. These run when the buttons are clicked.
         */
        final ImageButton openFile = findViewById(R.id.openFile);
        openFile.setOnClickListener(v -> {
            Log.d(TAG, "Open file button clicked");
            startOpenFile();
        });
        final ImageButton takePhoto = findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(v -> {
            Log.d(TAG, "Take photo button clicked");
            startTakePhoto();
        });
        final ImageButton downloadFile = findViewById(R.id.downloadFile);
        downloadFile.setOnClickListener(v -> {
            Log.d(TAG, "Download file button click");
            startDownloadFile();
        });
        final ImageButton rotateLeft = findViewById(R.id.rotateLeft);
        rotateLeft.setOnClickListener(v -> {
            Log.d(TAG, "Rotate left button clicked");
            rotateLeft();
        });
        final ImageButton processImage = findViewById(R.id.processImage);
        processImage.setOnClickListener(v -> {
            Log.d(TAG, "Process image button clicked");
            startProcessImage();
        });

        // We also want to make sure that our progress bar isn't spinning, and style it a bit
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(getResources()
                        .getColor(R.color.colorPrimaryDark, getTheme()), PorterDuff.Mode.SRC_IN);
    }
}