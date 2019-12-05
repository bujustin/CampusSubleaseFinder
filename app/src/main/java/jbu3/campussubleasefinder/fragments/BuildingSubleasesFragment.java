package jbu3.campussubleasefinder.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import jbu3.campussubleasefinder.SampleData;
import jbu3.campussubleasefinder.activities.BuildingActivity;
import jbu3.campussubleasefinder.activities.PostSubleaseActivity;
import jbu3.campussubleasefinder.activities.SubleaseActivity;
import jbu3.campussubleasefinder.adapters.SubleaseRecyclerViewAdapter;
import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.R;
import jbu3.campussubleasefinder.models.Sublease;


public class BuildingSubleasesFragment extends Fragment implements SubleaseRecyclerViewAdapter.ItemClickListener {
    private Building building;

    private SubleaseRecyclerViewAdapter subleaseAdapter;

    private OnFragmentInteractionListener mListener;

    public BuildingSubleasesFragment() {
        // Required empty public constructor
    }

    public static BuildingSubleasesFragment newInstance(int buildingIdx) {
        BuildingSubleasesFragment fragment = new BuildingSubleasesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.building_subleases, container, false);
        RecyclerView subleaseRecyclerView = view.findViewById(R.id.building_subleases_recycler_view);
        subleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        subleaseAdapter = new SubleaseRecyclerViewAdapter(getContext(), building.subleases);
        subleaseAdapter.setClickListener(this);
        subleaseRecyclerView.setAdapter(subleaseAdapter);

        Button postSubleaseButton = view.findViewById(R.id.building_subleases_post_button);
        postSubleaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showPostSubleaseIntent = new Intent(getContext(), PostSubleaseActivity.class);
                startActivity(showPostSubleaseIntent);
            }
        });
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
        Intent showSubleaseIntent = new Intent(getActivity(), SubleaseActivity.class);
        startActivity(showSubleaseIntent);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
