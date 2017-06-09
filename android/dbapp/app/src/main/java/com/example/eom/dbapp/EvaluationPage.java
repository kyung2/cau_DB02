package com.example.eom.dbapp;

import android.media.Rating;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class EvaluationPage extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {
public RatingBar ratingBar;
    Button button;
    TextView resultTV;
    TextView nameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_page);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Replace actiong", Snackbar.LENGTH_LONG).setAction("Action",null).show();


            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });

        resultTV = (TextView) findViewById(R.id.resultText);
        float rate = Float.valueOf(getIntent().getExtras().get("edtRating").toString());
        Toast.makeText(getApplicationContext(),String.valueOf(rate),Toast.LENGTH_SHORT).show();

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(rate);

        final int numStars = ratingBar.getNumStars();
        resultTV.setText(rate + "/" + numStars + " " );
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser ){


        final int numStart2 = ratingBar.getNumStars();
        resultTV.setText(rating + "/" + numStart2 + " " );
        ratingBar.setNumStars(numStart2);
        ratingBar.setRating(rating);


    }

}
