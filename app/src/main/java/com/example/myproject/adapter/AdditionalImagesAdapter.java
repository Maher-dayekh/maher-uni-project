//package com.example.myproject.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.myproject.R;
//import java.util.List;
//
//public class AdditionalImagesAdapter extends RecyclerView.Adapter<AdditionalImagesAdapter.ViewHolder> {
//
//    private Context context;
//    private List<Integer> imagesList;
//
//    public AdditionalImagesAdapter(Context context, List<Integer> imagesList) {
//        this.context = context;
//        this.imagesList = imagesList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.additional_image_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView.setImageResource(imagesList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return imagesList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.additional_image);
//        }
//    }
//}

package com.example.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.R;
import com.example.myproject.utils.ImageLoader;
import java.util.List;

public class AdditionalImagesAdapter extends RecyclerView.Adapter<AdditionalImagesAdapter.ViewHolder> {
    private Context context;
    private List<String> imagesList;

    public AdditionalImagesAdapter(Context context, List<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.additional_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Load images from URL instead of drawable resources
        ImageLoader.loadImage(context, holder.imageView, imagesList.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.additional_image);
        }
    }
}
