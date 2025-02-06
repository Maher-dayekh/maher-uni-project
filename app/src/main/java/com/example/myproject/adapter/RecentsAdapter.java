package com.example.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.DetailsActivity;
import com.example.myproject.R;
import com.example.myproject.model.Place;
import com.example.myproject.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {
    private Context context;
    private List<Place> placesList;

    public RecentsAdapter(Context context, List<Place> placesList) {
        this.context = context;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_row_item, parent, false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        Place place = placesList.get(position);

        holder.countryName.setText(place.getCountry());
        holder.placeName.setText(place.getName());
        holder.price.setText(place.getStartingPrice());

        // Load image without external libraries
        ImageLoader.loadImage(holder.placeImage, place.getImage());

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, DetailsActivity.class);
            i.putExtra("place_name", place.getName());
            i.putExtra("country_name", place.getCountry());
            i.putExtra("price", place.getStartingPrice());
            i.putExtra("image", place.getImage());
            i.putExtra("available_flight", place.getAvailableFlight());
            i.putExtra("description", place.getDescription());

            // Ensure additional images list is never null
            if (place.getAdditionalImages() != null) {
                i.putStringArrayListExtra("additional_images", new ArrayList<>(place.getAdditionalImages()));
            } else {
                i.putStringArrayListExtra("additional_images", new ArrayList<>()); // Empty list to prevent crash
            }

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImage;
        TextView placeName, countryName, price;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}

//package com.example.myproject.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myproject.DetailsActivity;
//import com.example.myproject.R;
//import com.example.myproject.model.Recents;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {
//
//    Context context;
//    List<Recents> recentsDataList;
//
//    public RecentsAdapter(Context context, List<Recents> recentsDataList) {
//        this.context = context;
//        this.recentsDataList = recentsDataList;
//    }
//
//    @NonNull
//    @Override
//    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.recent_row_item, parent, false);
//
//        return new RecentsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
//
//        Recents place = recentsDataList.get(position);
//
//        holder.countryName.setText(recentsDataList.get(position).getCountryName());
//        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
//        holder.price.setText(recentsDataList.get(position).getPrice());
//        holder.placeImage.setImageResource(recentsDataList.get(position).getImageUrl());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, DetailsActivity.class);
//
//                // تمرير البيانات إلى DetailsActivity
//                i.putExtra("place_name", place.getPlaceName());
//                i.putExtra("country_name", place.getCountryName());
//                i.putExtra("price", place.getPrice());
//                i.putExtra("image", place.getImageUrl());
//                // ✅ تمرير الصور الإضافية كمصفوفة ArrayList<Integer>
//                List<Integer> images = place.getAdditionalImages();
//                if (images == null) {
//                    images = new ArrayList<>(); // ✅ تجنب NullPointerException
//                }
//                i.putIntegerArrayListExtra("additional_images", new ArrayList<>(images));
//
//                context.startActivity(i);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return recentsDataList.size();
//    }
//
//    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView placeImage;
//        TextView placeName, countryName, price;
//
//        public RecentsViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            placeImage = itemView.findViewById(R.id.place_image);
//            placeName = itemView.findViewById(R.id.place_name);
//            countryName = itemView.findViewById(R.id.country_name);
//            price = itemView.findViewById(R.id.price);
//
//        }
//    }
//}
