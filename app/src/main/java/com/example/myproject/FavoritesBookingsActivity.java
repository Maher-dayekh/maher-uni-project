package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import com.example.myproject.adapter.FavoritesBookingsAdapter;
import com.example.myproject.model.Place;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class FavoritesBookingsActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView, bookingsRecyclerView;
    private FavoritesBookingsAdapter favoritesAdapter, bookingsAdapter;
    private List<Place> favoritesList, bookingsList;
    private FirebaseAuth mAuth;
    private DatabaseReference favoritesRef, bookingsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_favorites_bookings);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            finish();
            return;
        }

        // Get Firebase references
        favoritesRef = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("favorites").child(user.getUid());
        bookingsRef = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("bookings").child(user.getUid());

        // Initialize RecyclerViews
        favoritesRecyclerView = findViewById(R.id.favorites_recycler);
        bookingsRecyclerView = findViewById(R.id.bookings_recycler);

        favoritesList = new ArrayList<>();
        bookingsList = new ArrayList<>();

        favoritesAdapter = new FavoritesBookingsAdapter(this, favoritesList);
        bookingsAdapter = new FavoritesBookingsAdapter(this, bookingsList);

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoritesRecyclerView.setAdapter(favoritesAdapter);
        bookingsRecyclerView.setAdapter(bookingsAdapter);

        // Load Data
        loadFavorites();
        loadBookings();

        System.out.println("favs"+ favoritesList);
        System.out.println("bookings"+ bookingsList);
        // Handle tab switching
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    favoritesRecyclerView.setVisibility(View.VISIBLE);
                    bookingsRecyclerView.setVisibility(View.GONE);
                } else {
                    favoritesRecyclerView.setVisibility(View.GONE);
                    bookingsRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        ImageView profileImage = findViewById(R.id.navigate_to_profile);
        profileImage.setOnClickListener(v -> {
            startActivity(new Intent(FavoritesBookingsActivity.this, ProfileActivity.class));
        });

        ImageView allPlaces = findViewById(R.id.navigate_to_places);
        allPlaces.setOnClickListener(v -> {
            startActivity(new Intent(FavoritesBookingsActivity.this, AllPlacesActivity.class));
        });

        ImageView home = findViewById(R.id.navigate_to_main);
        home.setOnClickListener(v -> {
            startActivity(new Intent(FavoritesBookingsActivity.this, MainActivity.class));
        });
    }

    private void loadFavorites() {
        favoritesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoritesList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Place place = dataSnapshot.getValue(Place.class);
                    if (place != null) {
                        favoritesList.add(place);
                    }
                }
                favoritesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


    private void loadBookings() {
        bookingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingsList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Place place = dataSnapshot.getValue(Place.class);
                    if (place != null) {
                        bookingsList.add(place);
                    }
                }
                bookingsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


}
