//package com.example.myproject;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.myproject.adapter.AdditionalImagesAdapter;
//
//import java.util.ArrayList;
//
//public class DetailsActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.acitivity_details);
//
//        // استقبال البيانات من Intent
//        String placeName = getIntent().getStringExtra("place_name");
//        String countryName = getIntent().getStringExtra("country_name");
//        String price = getIntent().getStringExtra("price");
//        int imageRes = getIntent().getIntExtra("image", R.drawable.bg);
//        ArrayList<Integer> additionalImages = getIntent().getIntegerArrayListExtra("additional_images");
//
//        // تعيين البيانات في الـ TextView و ImageView
//        TextView placeNameTextView = findViewById(R.id.textView6);
//        TextView priceTextView = findViewById(R.id.textView11);
//        ImageView placeImageView = findViewById(R.id.imageView3);
//
//        placeNameTextView.setText(placeName + ", " + countryName);
//        priceTextView.setText(price);
//        placeImageView.setImageResource(imageRes);
//
//        // ✅ إعداد RecyclerView لعرض الصور الإضافية
//        RecyclerView additionalImagesRecycler = findViewById(R.id.recycler_additional_images);
//        additionalImagesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        AdditionalImagesAdapter adapter = new AdditionalImagesAdapter(this, additionalImages);
//        additionalImagesRecycler.setAdapter(adapter);
//
//        // زر الرجوع إلى الصفحة الرئيسية
//        ImageView arrowButton = findViewById(R.id.imageView4);
//        arrowButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                finish(); // ينهي النشاط الحالي ويعود إلى السابق
//            }
//        });
//    }
//}
package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.myproject.adapter.AdditionalImagesAdapter;
import com.example.myproject.utils.ImageLoader;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_details);

        // Retrieve data from Intent
        String placeName = getIntent().getStringExtra("place_name");
        String countryName = getIntent().getStringExtra("country_name");
        String price = getIntent().getStringExtra("price");
        String imageUrl = getIntent().getStringExtra("image"); // Now a URL, not resource ID
        String availableFlight = getIntent().getStringExtra("available_flight");
        String description = getIntent().getStringExtra("description");
        ArrayList<String> additionalImages = getIntent().getStringArrayListExtra("additional_images");

        // Assign data to UI elements
        TextView placeNameTextView = findViewById(R.id.textView6);
        TextView priceTextView = findViewById(R.id.textView11);
        TextView descriptionTextView = findViewById(R.id.textView14);
        ImageView placeImageView = findViewById(R.id.imageView3);

        placeNameTextView.setText(placeName + ", " + countryName);
        priceTextView.setText(price);
        descriptionTextView.setText(description);

        // Load the main image from URL
        if (imageUrl != null) {
            ImageLoader.loadImage(placeImageView, imageUrl);
        }

        // Setup RecyclerView for additional images
        RecyclerView additionalImagesRecycler = findViewById(R.id.recycler_additional_images);
        additionalImagesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if (additionalImages != null && !additionalImages.isEmpty()) {
            AdditionalImagesAdapter adapter = new AdditionalImagesAdapter(this, additionalImages);
            additionalImagesRecycler.setAdapter(adapter);
        }

        // Back button to return to main page
        ImageView arrowButton = findViewById(R.id.imageView4);
        arrowButton.setOnClickListener(v -> finish());
    }
}
