package jbu3.campussubleasefinder.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Predicate;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.activities.BuildingActivity;
import jbu3.campussubleasefinder.adapters.ReviewsRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;


public class BuildingInfoFragment extends Fragment implements ReviewsRecyclerViewAdapter.ItemClickListener {
    private Building building;

    private ReviewsRecyclerViewAdapter reviewsAdapter;

    private OnFragmentInteractionListener mListener;

    private String priceRange = "";
    private int numSubleases = 0;
    private String numMonthsRange = "";
    private boolean petsAllowed = false;

    public BuildingInfoFragment() {
        // Required empty public constructor
    }

    public static BuildingInfoFragment newInstance(int buildingIdx) {
        BuildingInfoFragment fragment = new BuildingInfoFragment();
        Bundle args = new Bundle();
        args.putInt(BuildingActivity.ARG_BUILDING_ID, buildingIdx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int buildingID = getArguments().getInt(BuildingActivity.ARG_BUILDING_ID);
            building = SampleData.findBuildingByID(buildingID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.building_info, container, false);
        RecyclerView reviewsRecyclerView = view.findViewById(R.id.building_info_reviews_recycler_view);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsAdapter = new ReviewsRecyclerViewAdapter(getContext(), building.reviews);
        reviewsAdapter.setClickListener(this);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        TextView price = view.findViewById(R.id.building_info_price);
        TextView numSubleases = view.findViewById(R.id.building_info_num_subleases);
        TextView numBedrooms = view.findViewById(R.id.building_info_num_beds);
        TextView petsAllowed = view.findViewById(R.id.building_info_pets_allowed);

        price.setText(building.priceRange);
        numSubleases.setText(Integer.toString(building.subleases.size()));
        numBedrooms.setText(building.numBedroomRange);
        if (building.pets) {
            petsAllowed.setText("Pets allowed");
        } else {
            petsAllowed.setText("No pets allowed");
        }
//        price.setText();
        
        ((TextView)view.findViewById(R.id.building_ratings_text_view)).setText(String.format("%.2f", building.getAvgRating()));
        ((TextView)view.findViewById(R.id.building_num_reviews_text_view)).setText("("+Integer.toString(building.reviews.size())+")");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
