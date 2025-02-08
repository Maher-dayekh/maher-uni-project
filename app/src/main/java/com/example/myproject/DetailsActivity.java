////package com.example.myproject;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.ImageView;
////import android.widget.TextView;
////
////import com.example.myproject.adapter.AdditionalImagesAdapter;
////
////import java.util.ArrayList;
////
////public class DetailsActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.acitivity_details);
////
////        // استقبال البيانات من Intent
////        String placeName = getIntent().getStringExtra("place_name");
////        String countryName = getIntent().getStringExtra("country_name");
////        String price = getIntent().getStringExtra("price");
////        int imageRes = getIntent().getIntExtra("image", R.drawable.bg);
////        ArrayList<Integer> additionalImages = getIntent().getIntegerArrayListExtra("additional_images");
////
////        // تعيين البيانات في الـ TextView و ImageView
////        TextView placeNameTextView = findViewById(R.id.textView6);
////        TextView priceTextView = findViewById(R.id.textView11);
////        ImageView placeImageView = findViewById(R.id.imageView3);
////
////        placeNameTextView.setText(placeName + ", " + countryName);
////        priceTextView.setText(price);
////        placeImageView.setImageResource(imageRes);
////
////        // ✅ إعداد RecyclerView لعرض الصور الإضافية
////        RecyclerView additionalImagesRecycler = findViewById(R.id.recycler_additional_images);
////        additionalImagesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
////        AdditionalImagesAdapter adapter = new AdditionalImagesAdapter(this, additionalImages);
////        additionalImagesRecycler.setAdapter(adapter);
////
////        // زر الرجوع إلى الصفحة الرئيسية
////        ImageView arrowButton = findViewById(R.id.imageView4);
////        arrowButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                finish(); // ينهي النشاط الحالي ويعود إلى السابق
////            }
////        });
////    }
////}
//package com.example.myproject;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//import java.util.ArrayList;
//import com.example.myproject.adapter.AdditionalImagesAdapter;
//import com.example.myproject.utils.ImageLoader;
//
//public class DetailsActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.acitivity_details);
//
//        // Retrieve data from Intent
//        String placeName = getIntent().getStringExtra("place_name");
//        String countryName = getIntent().getStringExtra("country_name");
//        String price = getIntent().getStringExtra("price");
//        String imageUrl = getIntent().getStringExtra("image"); // Now a URL, not resource ID
//        String availableFlight = getIntent().getStringExtra("available_flight");
//        String description = getIntent().getStringExtra("description");
//        ArrayList<String> additionalImages = getIntent().getStringArrayListExtra("additional_images");
//
//        // Assign data to UI elements
//        TextView placeNameTextView = findViewById(R.id.textView6);
//        TextView priceTextView = findViewById(R.id.textView11);
//        TextView descriptionTextView = findViewById(R.id.textView14);
//        ImageView placeImageView = findViewById(R.id.imageView3);
//
//        placeNameTextView.setText(placeName + ", " + countryName);
//        priceTextView.setText(price);
//        descriptionTextView.setText(description);
//
//        // Load the main image from URL
//        if (imageUrl != null) {
//            ImageLoader.loadImage(placeImageView, imageUrl);
//        }
//
//        // Setup RecyclerView for additional images
//        RecyclerView additionalImagesRecycler = findViewById(R.id.recycler_additional_images);
//        additionalImagesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        if (additionalImages != null && !additionalImages.isEmpty()) {
//            AdditionalImagesAdapter adapter = new AdditionalImagesAdapter(this, additionalImages);
//            additionalImagesRecycler.setAdapter(adapter);
//        }
//
//        // Back button to return to main page
//        ImageView arrowButton = findViewById(R.id.imageView4);
//        arrowButton.setOnClickListener(v -> finish());
//    }
//}
package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.adapter.AdditionalImagesAdapter;
import com.example.myproject.utils.ImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    private TextView placeNameTextView, priceTextView, descriptionTextView;
    private ImageView placeImageView;
    private RecyclerView additionalImagesRecycler;
    private Button bookButton;

    private String placeId, placeName, country, price, availableFlight, description, imageUrl;
    private ArrayList<String> additionalImages;

    private FirebaseAuth mAuth;
    private DatabaseReference bookingsRef;
    private boolean isBooked = false; // Track booking state
    private ImageView favoriteButton;
    private DatabaseReference favoritesRef;
    private boolean isFavorite = false; // Tracks favorite state


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_details);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        bookingsRef = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("bookings");
        favoritesRef = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("favorites");


        // Retrieve data from Intent
        placeId = getIntent().getStringExtra("place_id");
        placeName = getIntent().getStringExtra("place_name");
        country = getIntent().getStringExtra("country_name");
        price = getIntent().getStringExtra("price");
        imageUrl = getIntent().getStringExtra("image");
        availableFlight = getIntent().getStringExtra("available_flight");
        description = getIntent().getStringExtra("description");
        additionalImages = getIntent().getStringArrayListExtra("additional_images");

        // Assign data to UI elements
        placeNameTextView = findViewById(R.id.textView6);
        priceTextView = findViewById(R.id.textView11);
        descriptionTextView = findViewById(R.id.textView14);
        placeImageView = findViewById(R.id.imageView3);
        bookButton = findViewById(R.id.button);

        placeNameTextView.setText(placeName + ", " + country);
        priceTextView.setText(price);
        descriptionTextView.setText(description);

        if (imageUrl != null) {
            ImageLoader.loadImage(placeImageView, imageUrl);
        }

        // Setup RecyclerView for additional images
        additionalImagesRecycler = findViewById(R.id.recycler_additional_images);
        additionalImagesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if (additionalImages != null && !additionalImages.isEmpty()) {
            AdditionalImagesAdapter adapter = new AdditionalImagesAdapter(this, additionalImages);
            additionalImagesRecycler.setAdapter(adapter);
        }

        // Check if user has already booked this trip
        checkBookingStatus();

        // Back button to return to main page
        ImageView arrowButton = findViewById(R.id.imageView4);
        arrowButton.setOnClickListener(v -> finish());

        // Handle booking
        bookButton.setOnClickListener(v -> {
            if (isBooked) {
                showCancelBookingDialog();
            } else {
                showBookingDialog();
            }
        });

        favoriteButton = findViewById(R.id.favorite_button);
        checkFavoriteStatus();

        // Handle Favorite Button Click
        favoriteButton.setOnClickListener(v -> {
            if (isFavorite) {
                removeFromFavorites();
            } else {
                addToFavorites();
            }
        });

    }

    // Check if the user has already booked this trip
    private void checkBookingStatus() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return;

        bookingsRef.child(user.getUid()).child(placeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    isBooked = true;
                    bookButton.setText("Cancel Booking");
                } else {
                    isBooked = false;
                    bookButton.setText("Start Booking Your Trip");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    // Show booking confirmation dialog
    private void showBookingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Booking");
        builder.setMessage("Are you sure you want to book this trip on " + availableFlight + " for " + price + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> bookTrip());
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Show cancel booking confirmation dialog
    private void showCancelBookingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel Booking");
        builder.setMessage("Are you sure you want to cancel this booking?");
        builder.setPositiveButton("Yes", (dialog, which) -> cancelBooking());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Book the trip and save to Firebase
    private void bookTrip() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("placeName", placeName);
        bookingData.put("country", country);
        bookingData.put("price", price);
        bookingData.put("availableFlight", availableFlight);

        bookingsRef.child(user.getUid()).child(placeId).setValue(bookingData)
                .addOnSuccessListener(aVoid -> {
                    isBooked = true;
                    bookButton.setText("Cancel Booking");
                    Toast.makeText(DetailsActivity.this, "Trip booked successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(DetailsActivity.this, "Failed to book trip", Toast.LENGTH_SHORT).show());
    }

    // Cancel the booking and remove from Firebase
    private void cancelBooking() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return;

        bookingsRef.child(user.getUid()).child(placeId).removeValue()
                .addOnSuccessListener(aVoid -> {
                    isBooked = false;
                    bookButton.setText("Start Booking Your Trip");
                    Toast.makeText(DetailsActivity.this, "Booking cancelled!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(DetailsActivity.this, "Failed to cancel booking", Toast.LENGTH_SHORT).show());
    }

    private void checkFavoriteStatus() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return;

        favoritesRef.child(user.getUid()).child(placeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    isFavorite = true;
                    favoriteButton.setImageResource(R.drawable.liked); // Set to red heart
                } else {
                    isFavorite = false;
                    favoriteButton.setImageResource(R.drawable.like); // Set to empty heart
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void addToFavorites() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> favoriteData = new HashMap<>();
        favoriteData.put("placeName", placeName);
        favoriteData.put("country", country);
        favoriteData.put("price", price);
        favoriteData.put("imageUrl", imageUrl);

        favoritesRef.child(user.getUid()).child(placeId).setValue(favoriteData)
                .addOnSuccessListener(aVoid -> {
                    isFavorite = true;
                    favoriteButton.setImageResource(R.drawable.liked);
                    Toast.makeText(DetailsActivity.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(DetailsActivity.this, "Failed to add favorite", Toast.LENGTH_SHORT).show());
    }

    private void removeFromFavorites() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return;

        favoritesRef.child(user.getUid()).child(placeId).removeValue()
                .addOnSuccessListener(aVoid -> {
                    isFavorite = false;
                    favoriteButton.setImageResource(R.drawable.like);
                    Toast.makeText(DetailsActivity.this, "Removed from favorites!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(DetailsActivity.this, "Failed to remove favorite", Toast.LENGTH_SHORT).show());
    }


}
