package jbu3.campussubleasefinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.ConnectionRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.ReviewsRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.models.User;

public class ProfileActivity extends AppCompatActivity
        implements ConnectionRecyclerViewAdapter.ItemClickListener,
        SubleaseRecyclerViewAdapter.ItemClickListener,
        ReviewsRecyclerViewAdapter.ItemClickListener {

    private SubleaseRecyclerViewAdapter subleasesAdapter;
    private ReviewsRecyclerViewAdapter reviewsAdapter;
    private ConnectionRecyclerViewAdapter connectionsAdapter;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Boolean editable = getIntent().getBooleanExtra("PROFILE_CAN_EDIT", false);
        int userId = getIntent().getIntExtra("PROFILE_ID", 0);
        user = SampleData.findUserByID(userId,true);
        populateText(user);

        if (!editable) {
            // hide password input, import contacts
            TextView passwordTextView = findViewById(R.id.profile_password_text_view);
            EditText passwordEditText = findViewById(R.id.profile_password_edit_text);
            Button importContactsButton = findViewById(R.id.import_contacts_button);
            passwordTextView.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            importContactsButton.setVisibility(View.GONE);

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
        subleasesAdapter = new SubleaseRecyclerViewAdapter(this, user.subleases);
        subleasesAdapter.setClickListener(this);
        subleaseRecyclerView.setAdapter(subleasesAdapter);

        RecyclerView reviewsReyclerView = findViewById(R.id.profile_reviews_recycler_view);
        LinearLayoutManager reviewsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviewsReyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsAdapter = new ReviewsRecyclerViewAdapter(this, user.reviews);
        reviewsAdapter.setClickListener(this);
        reviewsReyclerView.setAdapter(reviewsAdapter);

        RecyclerView connectionsRecyclerView = findViewById(R.id.profile_connections_recycler_view);
        LinearLayoutManager connectionsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        connectionsRecyclerView.setLayoutManager(connectionsLayoutManager);
        connectionsAdapter = new ConnectionRecyclerViewAdapter(this, user.connections);
        connectionsAdapter.setClickListener(this);
        connectionsRecyclerView.setAdapter(connectionsAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void populateText(User user) {
        ((EditText)findViewById(R.id.profile_about_edit_text)).setText(user.about);
        ((EditText)findViewById(R.id.profile_name_edit_text)).setText(user.name);
        ((EditText)findViewById(R.id.profile_email_edit_text)).setText(user.email);
        ((EditText)findViewById(R.id.profile_phone_edit_text)).setText(user.phone);
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
