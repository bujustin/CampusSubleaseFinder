package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import jbu3.campussubleasefinder.R;

public class SubleaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sublease);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button reviewButton = findViewById(R.id.sublease_review_button);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeReviewIntent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                startActivity(writeReviewIntent);
            }
        });

        ImageView profileButton = findViewById(R.id.sublease_profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showProfileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                showProfileIntent.putExtra("PROFILE_CAN_EDIT", false);
                startActivity(showProfileIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
