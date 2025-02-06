package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.adapter.AllPlacesAdapter;
import com.example.myproject.model.Place;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class AllPlacesActivity extends AppCompatActivity {
    private RecyclerView allPlacesRecycler;
    private AllPlacesAdapter adapter;
    private List<Place> placeList, filteredList;
    private DatabaseReference databaseReference;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_places);

        allPlacesRecycler = findViewById(R.id.all_places_recycler);
        searchBar = findViewById(R.id.search_bar);

        // Set Grid Layout with 2 columns
        allPlacesRecycler.setLayoutManager(new GridLayoutManager(this, 2));

        placeList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new AllPlacesAdapter(this, filteredList);
        allPlacesRecycler.setAdapter(adapter);

        // Fetch data from Firebase
        databaseReference = FirebaseDatabase.getInstance("https://maher-uni-project-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("places");

        fetchPlaces();

        // Search feature
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterPlaces(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void fetchPlaces() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                placeList.clear();
                filteredList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Place place = dataSnapshot.getValue(Place.class);
                    if (place != null) {
                        placeList.add(place);
                    }
                }
                filteredList.addAll(placeList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void filterPlaces(String query) {
        filteredList.clear();
        for (Place place : placeList) {
            if (place.getName().toLowerCase().contains(query.toLowerCase()) ||
                    place.getCountry().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(place);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
