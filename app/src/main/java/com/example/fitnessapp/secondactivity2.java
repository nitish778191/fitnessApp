package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class secondactivity2 extends AppCompatActivity {

    private int[] newArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        newArray =new int[]{
                R.id.boat_pose1, R.id.boat_pose2,   R.id.boat_pose3, R.id.boat_pose4, R.id.boat_pose5,R.id.boat_pose6, R.id.boat_pose7, R.id.boat_pose8, R.id.boat_pose9, R.id.boat_pose10, R.id.boat_pose11, R.id.boat_pose14, R.id.boat_pose13,R.id.boat_pose14,

        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();
        if(id==R.id.id_privacy)
        {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freeprivacypolicy.com/live/452f6114-31b8-430b-ac09-84c4748f8110"));
            startActivity(intent);
            return true;

        }

        if(id==R.id.id_term)
        {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freeprivacypolicy.com/live/452f6114-31b8-430b-ac09-84c4748f8110"));
            startActivity(intent);
            return true;

        }

        if(id==R.id.rate)
        {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+getPackageName())));


            } catch(Exception ex){

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));

            }


            return true;

        }

        if(id==R.id.more)
        {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.fitness&hl=en&gl=US"));
            startActivity(intent);
            return true;

        }
        if(id == R.id.share)
        {
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            myIntent.setType("text/plain");
            String sharebody ="This is the best for yoga \n By this app you strech your body \n this is the free download Now \n"+"https://play.google.com/store/apps/details?id=com.example.fitnessapp&h1=en";
            String sharehub="Fitness App";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,sharehub);
            myIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(myIntent,"share using"));



            return true;

        }





        return true;

    }

    public void Imagebuttonclicked(View view) {

        for(int i=0;i<newArray.length;i++){
            if(view.getId()==newArray[i]){
                int value=i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent =new Intent(secondactivity2.this,thirdactivity2.class);
                intent.putExtra("value",String.valueOf(value));

                startActivity(intent);
            }
        }
    }
}