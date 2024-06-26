package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity implements simpleFragment.SimpleFragmentListener{

    private boolean favorite = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_activity);
        Bundle extra = getIntent().getExtras();
        //Set context for the view

        displayDetailsItem(extra);

        Initialize_But();

        FragmentManager fragmentManager = getSupportFragmentManager();
        simpleFragment fragment = (simpleFragment) fragmentManager.findFragmentById(R.id.Detail_fragment);
        Bundle arg = fragment.getArguments();
        if(arg != null) {
            favorite = arg.getBoolean("Favorite");
            fragment.setFavorite(favorite);
        }
        else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragment = simpleFragment.newInstance(favorite);

            fragmentTransaction.replace(R.id.Detail_fragment, fragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onFavoriteStateChanged(boolean isFavorite) {
        favorite = isFavorite;
    }

    private void displayDetailsItem(Bundle extra) {
        String Song_name = "";
        String Song_Description = "";

        if(extra != null) {
            Song_name = (String) extra.getString("Details_name");
            Song_Description = (String) extra.getString("Details_des");
        }
        else {
            Print("Error!!!");
            Intent backIntent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(backIntent);
            return;
        }

        if(!Song_name.isEmpty()) {
            TextView item_name = findViewById(R.id.Title);
            item_name.setText(Song_name);
        }

        if(!Song_Description.isEmpty()) {
            TextView item_des = findViewById(R.id.description);
            item_des.setText(Song_Description);
            item_des.setMovementMethod(new ScrollingMovementMethod());
        }

    }

    private void Print(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private void Initialize_But() {
        Button backBut = findViewById(R.id.BackBut);
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                simpleFragment fragment = (simpleFragment) fragmentManager.findFragmentById(R.id.Detail_fragment);
                Bundle arg = fragment.getArguments();
                if(arg != null) {
                    favorite = arg.getBoolean("Favorite");
                }

                Intent backIntent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }


}
