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
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SubleaseActivity extends AppCompatActivity {
    public static String ARG_SUBLEASE_ID = "subleaseID";
    private Sublease sublease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sublease);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int subleaseID = getIntent().getIntExtra(ARG_SUBLEASE_ID, 0);
        sublease = SampleData.findSubleaseByID(subleaseID);

        Button reviewButton = findViewById(R.id.sublease_review_button);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeReviewIntent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                startActivity(writeReviewIntent);
            }
        });

        Button profileButton = findViewById(R.id.sublease_profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showProfileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                showProfileIntent.putExtra("PROFILE_CAN_EDIT", false);
                startActivity(showProfileIntent);
            }
        });

        TextView address = findViewById(R.id.sublease_address);
        TextView sublessorName = findViewById(R.id.sublessor_name);
        TextView subleaseRent=findViewById(R.id.sublease_price);
        TextView subleaseStartDate= findViewById(R.id.sublease_start_date);
        TextView subleaseEndDate= findViewById(R.id.sublease_end_date);
        TextView subleaseNumBed= findViewById(R.id.sublease_number_of_beds);
        TextView subleaseNumBath= findViewById(R.id.sublease_number_of_baths);
        TextView subleaseDetails = findViewById(R.id.sublease_details);
        ImageView subleasePhoto1= findViewById(R.id.sublease_photo1);
        ImageView subleasePhoto2= findViewById(R.id.sublease_photo2);

        User user = SampleData.findUserByID(sublease.sublessorID);
        Building building = SampleData.findBuildingByID(sublease.buildingID);

        address.setText(building.address);
        sublessorName.setText(user.name);
        subleaseRent.setText(Integer.toString(sublease.price));
        subleaseStartDate.setText(sublease.startDate);
        subleaseEndDate.setText(sublease.endDate);
        subleaseNumBed.setText(Integer.toString(sublease.numBeds));
        subleaseNumBath.setText(Integer.toString(sublease.numBaths));
        subleaseDetails.setText(sublease.details);
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
