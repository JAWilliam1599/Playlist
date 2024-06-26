package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class simpleFragment extends Fragment {

    private VectorDrawable starDrawable;
    private boolean favorite = true;

    public simpleFragment() {};

    public static simpleFragment newInstance(boolean favorite) {
        simpleFragment fragment = new simpleFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean("Favorite", favorite);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            favorite = getArguments().getBoolean("Favorite");
        }
    }

    public boolean getFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    private VectorDrawable changeColor(VectorDrawable sample, ImageView image_sample) {
        sample.mutate();
        int newColor;
        if (!favorite) newColor = Color.parseColor("#FF0000");
        else newColor = Color.parseColor("#000000");

        sample.setTint(newColor);
        image_sample.setImageDrawable(starDrawable);
        favorite = !favorite;
        return sample;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View Rootview = inflater.inflate(R.layout.fragment_simple, container, false);
        final ImageView star = (ImageView) Rootview.findViewById(R.id.star);
        starDrawable = (VectorDrawable) star.getBackground();


        //Check for arguments
        starDrawable = changeColor(starDrawable, star);
        //Click event
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (starDrawable != null) {
                    starDrawable = changeColor(starDrawable, star);
                    Bundle arg = getArguments();
                    if(arg != null) {
                        arg.putBoolean("Favorite", favorite);
                        setArguments(arg);
                    }

                } else {
                    Log.w("simple_fragment", "Star not found!!!");
                }
            }

        });
        return Rootview;
    }

    public interface SimpleFragmentListener {
        void onFavoriteStateChanged(boolean isFavorite);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("Favorite", favorite);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            favorite = savedInstanceState.getBoolean("Favorite"); // Default value
        }
        // Update UI or logic based on favorite
    }
}
