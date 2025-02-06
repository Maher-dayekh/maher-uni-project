package com.example.myproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.LoginActivity;
import com.example.myproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {
    private TextView userName, userEmail;
    private ImageView userProfileImage, backArrow;
    private Button editProfileButton, logoutButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userProfileImage = findViewById(R.id.user_profile_image);
        editProfileButton = findViewById(R.id.edit_profile_button);
        logoutButton = findViewById(R.id.logout_button);
        backArrow = findViewById(R.id.backArrow);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            userEmail.setText(user.getEmail());
            userName.setText(user.getDisplayName() != null ? user.getDisplayName() : "User");

            // Load profile picture if available
            if (user.getPhotoUrl() != null) {
                new LoadProfileImage(userProfileImage).execute(user.getPhotoUrl().toString());
            } else {
                userProfileImage.setImageResource(R.drawable.bg); // Default profile picture
            }
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        }

        // Logout Button Click
        logoutButton.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(ProfileActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });

        // Edit Profile Button (Optional - Not Implemented Yet)
        editProfileButton.setOnClickListener(view -> {
            Toast.makeText(ProfileActivity.this, "Edit Profile Coming Soon!", Toast.LENGTH_SHORT).show();
        });

        backArrow.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
    }

    // AsyncTask to load profile image from URL
    private static class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadProfileImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            } else {
                imageView.setImageResource(R.drawable.bg); // Default image if loading fails
            }
        }
    }
}
