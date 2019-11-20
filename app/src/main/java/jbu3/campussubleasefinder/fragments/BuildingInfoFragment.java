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

import java.util.ArrayList;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.activities.BuildingActivity;
import jbu3.campussubleasefinder.adapters.ReviewsRecyclerViewAdapter;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Review;


public class BuildingInfoFragment extends Fragment implements ReviewsRecyclerViewAdapter.ItemClickListener {
    private int buildingIdx;
    private Building building;
    private ArrayList<Integer> buildingReviewInds = new ArrayList<>();
    private ArrayList<Review> buildingReviews = new ArrayList<>();

    private ReviewsRecyclerViewAdapter reviewsAdapter;

    private OnFragmentInteractionListener mListener;

    public BuildingInfoFragment() {
        // Required empty public constructor
    }

    public static BuildingInfoFragment newInstance(int buildingIdx) {
        BuildingInfoFragment fragment = new BuildingInfoFragment();
        Bundle args = new Bundle();
        args.putInt(BuildingActivity.ARG_BUILDING_IDX, buildingIdx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            buildingIdx = getArguments().getInt(BuildingActivity.ARG_BUILDING_IDX);
            building = SampleData.buildings.get(buildingIdx);

            buildingReviewInds.clear();
            buildingReviews.clear();
            for (int i = 0; i < SampleData.reviews.size(); ++i) {
                if (SampleData.reviews.get(i).buildingIdx == buildingIdx) {
                    buildingReviewInds.add(i);
                    buildingReviews.add(SampleData.reviews.get(i));
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.building_info, container, false);
        RecyclerView reviewsRecyclerView = view.findViewById(R.id.building_info_reviews_recycler_view);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsAdapter = new ReviewsRecyclerViewAdapter(getContext(), buildingReviews);
        reviewsAdapter.setClickListener(this);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
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
