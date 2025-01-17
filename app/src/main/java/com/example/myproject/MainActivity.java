package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.myproject.adapter.RecentsAdapter;
import com.example.myproject.adapter.TopPlacesAdapter;
import com.example.myproject.model.Recents;
import com.example.myproject.model.TopPlaces;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Recents> recentsDataList = new ArrayList<>();
        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2));
        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2));
        recentsDataList.add(new Recents("AM Lake","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new Recents("Nilgiri Hills","India","From $300",R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);

        List<TopPlaces> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlaces("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));

        setTopPlacesRecycler(topPlacesDataList);
    }

    private void setRecentRecycler(List<Recents> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private void setTopPlacesRecycler(List<TopPlaces> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }

}
