package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.ConnectionRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.ReviewsRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;

public class ProfileActivity extends AppCompatActivity
        implements ConnectionRecyclerViewAdapter.ItemClickListener,
        SubleaseRecyclerViewAdapter.ItemClickListener,
        ReviewsRecyclerViewAdapter.ItemClickListener {

    private SubleaseRecyclerViewAdapter subleasesAdapter;
    private ReviewsRecyclerViewAdapter reviewsAdapter;
    private ConnectionRecyclerViewAdapter connectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Boolean editable = getIntent().getExtras().getBoolean("PROFILE_CAN_EDIT");
        if (!editable) {
            // hide password input
            TextView passwordTextView = findViewById(R.id.profile_password_text_view);
            EditText passwordEditText = findViewById(R.id.profile_password_edit_text);
            ((ViewGroup) passwordTextView.getParent()).removeView(passwordTextView);
            ((ViewGroup) passwordEditText.getParent()).removeView(passwordEditText);

            // set inputs disabled
            EditText aboutEditText = findViewById(R.id.profile_about_edit_text);
            aboutEditText.setEnabled(false);
            EditText nameEditText = findViewById(R.id.profile_name_edit_text);
            nameEditText.setEnabled(false);
            EditText emailEditText = findViewById(R.id.profile_email_edit_text);
            emailEditText.setEnabled(false);
            EditText phoneEditText = findViewById(R.id.profile_phone_edit_text);
            phoneEditText.setEnabled(false);
        }

        RecyclerView subleaseRecyclerView = findViewById(R.id.profile_subleases_recycler_view);
        LinearLayoutManager subleaseLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        subleaseRecyclerView.setLayoutManager(subleaseLayoutManager);
        subleasesAdapter = new SubleaseRecyclerViewAdapter(this, SampleData.subleases);
        subleasesAdapter.setClickListener(this);
        subleaseRecyclerView.setAdapter(subleasesAdapter);

        RecyclerView reviewsReyclerView = findViewById(R.id.profile_reviews_recycler_view);
        LinearLayoutManager reviewsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviewsReyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsAdapter = new ReviewsRecyclerViewAdapter(this, SampleData.reviews);
        reviewsAdapter.setClickListener(this);
        reviewsReyclerView.setAdapter(reviewsAdapter);

        RecyclerView connectionsRecyclerView = findViewById(R.id.profile_connections_recycler_view);
        LinearLayoutManager connectionsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        connectionsRecyclerView.setLayoutManager(connectionsLayoutManager);
        connectionsAdapter = new ConnectionRecyclerViewAdapter(this, SampleData.users);
        connectionsAdapter.setClickListener(this);
        connectionsRecyclerView.setAdapter(connectionsAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onItemClick(View view, int position) {
        // start user intent
    }
}
