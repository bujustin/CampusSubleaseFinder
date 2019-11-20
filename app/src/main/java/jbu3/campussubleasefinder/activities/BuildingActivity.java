package jbu3.campussubleasefinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.adapters.BuildingRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.fragments.BuildingInfoFragment;
import jbu3.campussubleasefinder.fragments.BuildingSubleasesFragment;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Sublease;

import static jbu3.campussubleasefinder.SampleData.buildings;

public class BuildingActivity extends AppCompatActivity implements BuildingInfoFragment.OnFragmentInteractionListener, BuildingSubleasesFragment.OnFragmentInteractionListener {
    public static final String ARG_BUILDING_IDX = "buildingIdx";

    private int buildingIdx;
    private Building building;

    private TextView addressText;
    private BottomNavigationView bottomNav;

    Fragment buildingInfo;
    Fragment buildingSubleases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buildingIdx = getIntent().getIntExtra(ARG_BUILDING_IDX, 0);
        building = SampleData.buildings.get(buildingIdx);

        addressText = findViewById(R.id.building_address);
        bottomNav = findViewById(R.id.building_bottom_nav);

        buildingInfo = BuildingInfoFragment.newInstance(buildingIdx);
        buildingSubleases = BuildingSubleasesFragment.newInstance(buildingIdx);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.add(R.id.building_frame, buildingInfo);
        t.commit();

        bottomNav.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    switch (item.getItemId()) {
                        case R.id.action_info:
                            t.replace(R.id.building_frame, buildingInfo);
                            t.commit();
                            item.setChecked(true);
                            break;
                        case R.id.action_subleases:
                            t.replace(R.id.building_frame, buildingSubleases);
                            t.commit();
                            item.setChecked(true);
                            break;
                    }
                    return false;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
