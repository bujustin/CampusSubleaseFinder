package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Sublease;

public class SubleaseActivity extends AppCompatActivity {

    private Sublease sublease;

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

        TextView address = findViewById(R.id.sublease_address);
        TextView sublessor_name = findViewById(R.id.sublessor_name);
        TextView sublease_rent=findViewById(R.id.sublease_price);
        TextView sublease_start_date= findViewById(R.id.sublease_start_date);
        TextView sublease_end_date= findViewById(R.id.sublease_end_date);
        TextView sublease_number_of_beds= findViewById(R.id.sublease_number_of_beds);
        TextView sublease_number_of_baths= findViewById(R.id.sublease_number_of_baths);
        TextView sublease_details = findViewById(R.id.sublease_details);
        ImageView sublease_photo1= findViewById(R.id.sublease_photo1);
        ImageView sublease_photo2= findViewById(R.id.sublease_photo2);

        sublease_rent.setText(sublease.price);
        sublease_start_date.setText(sublease.startDate);
        sublease_end_date.setText(sublease.endDate);
        sublease_number_of_beds.setText(sublease.numBeds);
        sublease_number_of_baths.setText(sublease.numBaths);
        sublease_details.setText(sublease.details);

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
