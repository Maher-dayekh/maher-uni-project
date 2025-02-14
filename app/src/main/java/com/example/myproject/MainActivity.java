//package com.example.myproject;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//
//
//import com.example.myproject.adapter.RecentsAdapter;
//import com.example.myproject.adapter.TopPlacesAdapter;
//import com.example.myproject.model.Place;
//import com.example.myproject.model.Recents;
//import com.example.myproject.model.TopPlaces;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    RecyclerView recentRecycler, topPlacesRecycler;
//    RecentsAdapter recentsAdapter;
//    TopPlacesAdapter topPlacesAdapter;
//    private List<Place> placeList;
//    private RecyclerView recyclerView;
//
//    private DatabaseReference databaseReference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        recentRecycler = findViewById(R.id.recent_recycler);
//        topPlacesRecycler = findViewById(R.id.top_places_recycler);
//
//        List<Recents> recentsDataList = new ArrayList<>();
//        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//
////        setRecentRecycler(recentsDataList);
//
//        List<TopPlaces> topPlacesDataList = new ArrayList<>();
//        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces, List.of(R.drawable.recentimage1, R.drawable.recentimage2)));
//
////        setTopPlacesRecycler(topPlacesDataList);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        recentRecycler.setLayoutManager(layoutManager);
//
//        placeList = new ArrayList<>();
//        recentsAdapter = new RecentsAdapter(this, placeList);
//        recyclerView.setAdapter(recentsAdapter);
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("places");
//
//        fetchPlaces();
//
//        ImageView profileImage = findViewById(R.id.navigate_to_profile);
//        profileImage.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//        });
//
//    }
//
//    private void fetchPlaces() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                placeList.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Place place = dataSnapshot.getValue(Place.class);
//                    if (place != null) {
//                        placeList.add(place);
//                    }
//                }
//                recentsAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("Firebase"+ "Error fetching data"+ error.toException());
//            }
//        });
//    }
//
////    private void setRecentRecycler(List<Recents> recentsDataList){
////
////        recentRecycler = findViewById(R.id.recent_recycler);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
////        recentRecycler.setLayoutManager(layoutManager);
////        recentsAdapter = new RecentsAdapter(this, recentsDataList);
////        recentRecycler.setAdapter(recentsAdapter);
////
////    }
////
////    private void setTopPlacesRecycler(List<TopPlaces> topPlacesDataList){
////
////        topPlacesRecycler = findViewById(R.id.top_places_recycler);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
////        topPlacesRecycler.setLayoutManager(layoutManager);
////        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
////        topPlacesRecycler.setAdapter(topPlacesAdapter);
////
////    }
//
//}
package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.adapter.RecentsAdapter;
import com.example.myproject.adapter.TopPlacesAdapter;
import com.example.myproject.model.Place;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recentRecycler, topPlacesRecycler;
    private RecentsAdapter recentsAdapter;
    private TopPlacesAdapter topPlacesAdapter;
    private List<Place> recentsList, topPlacesList;
    private DatabaseReference databaseReference;
    private EditText searchEditText; // Add this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentRecycler = findViewById(R.id.recent_recycler);
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        databaseReference = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("places");

        recentsList = new ArrayList<>();
        topPlacesList = new ArrayList<>();

        // Set layout managers
        recentRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        topPlacesRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        fetchPlaces();

        ImageView profileImage = findViewById(R.id.navigate_to_profile);
        profileImage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });

        ImageView allPlaces = findViewById(R.id.navigate_to_places);
        allPlaces.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AllPlacesActivity.class));
        });

        ImageView favoritesBookings = findViewById(R.id.navigate_to_favorites_bookings);
        favoritesBookings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FavoritesBookingsActivity.class));
        });

        searchEditText = findViewById(R.id.editText);

        // Placeholder disappears when clicked
        searchEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                searchEditText.setHint("");
            } else if (searchEditText.getText().toString().trim().isEmpty()) {
                searchEditText.setHint("Search Destinations");
            }
        });

        // Handle Enter key press (Search action)
        searchEditText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, AllPlacesActivity.class);
                    intent.putExtra("search_query", query);
                    startActivity(intent);
                }
                return true;
            }
            return false;
        });
//        // Handle Enter key press
//        searchEditText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
//            if (event != null && event.getAction() == KeyEvent.ACTION_DOWN && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_TAB)) {
//                String query = searchEditText.getText().toString().trim();
//                if (!query.isEmpty()) {
//                    Intent intent = new Intent(MainActivity.this, AllPlacesActivity.class);
//                    intent.putExtra("search_query", query);
//                    startActivity(intent);
//                }
//                return true;
//            }
//            return false;
//        });
    }

    private void fetchPlaces() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Place> allPlaces = new ArrayList<>();

                // Step 1: Collect all places
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Place place = dataSnapshot.getValue(Place.class);
                    if (place != null) {
                        allPlaces.add(place);
                    }
                }

                // Step 2: Sort & limit recentsList (Earliest availableFlight)
                Collections.sort(allPlaces, new Comparator<Place>() {
                    @Override
                    public int compare(Place p1, Place p2) {
                        return p1.getAvailableFlight().compareTo(p2.getAvailableFlight());
                    }
                });

                recentsList.clear();
                for (int i = 0; i < Math.min(5, allPlaces.size()); i++) {
                    recentsList.add(allPlaces.get(i));
                }

// Step 3: Sort & limit topPlacesList (Highest startingPrice)
                Collections.sort(allPlaces, new Comparator<Place>() {
                    @Override
                    public int compare(Place p1, Place p2) {
                        double price1 = Double.parseDouble(p1.getStartingPrice().replace("$", ""));
                        double price2 = Double.parseDouble(p2.getStartingPrice().replace("$", ""));
                        return Double.compare(price2, price1); // Descending order
                    }
                });

                topPlacesList.clear();
                for (int i = 0; i < Math.min(5, allPlaces.size()); i++) {
                    topPlacesList.add(allPlaces.get(i));
                }


                // Step 4: Attach adapters AFTER filtering
                recentsAdapter = new RecentsAdapter(MainActivity.this, recentsList);
                recentRecycler.setAdapter(recentsAdapter);
                recentsAdapter.notifyDataSetChanged();

                topPlacesAdapter = new TopPlacesAdapter(MainActivity.this, topPlacesList);
                topPlacesRecycler.setAdapter(topPlacesAdapter);
                topPlacesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Firebase Error: " + error.getMessage());
            }
        });
    }

}
