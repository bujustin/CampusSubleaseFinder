package jbu3.campussubleasefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BuildingRecyclerViewAdapter.ItemClickListener {

    private ArrayList<Building> buildings = new ArrayList<Building>() {{
        add(new Building("123 Example St.", 4, 2, 4.5));
    }};

    private BuildingRecyclerViewAdapter buildingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_account_circle_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView recyclerView = findViewById(R.id.main_building_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buildingAdapter = new BuildingRecyclerViewAdapter(this, buildings);
        buildingAdapter.setClickListener(this);
        recyclerView.setAdapter(buildingAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.filters:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
