package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thirdactivity extends AppCompatActivity {

     String buttonvalue;
     Button startButton;
     private CountDownTimer countdowntimer;
     TextView mtextview;
     private boolean MTimeRunning;
     private long MTimeLeftinmills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdactivity);

        Intent intent =getIntent();
        buttonvalue=intent.getStringExtra("value");


        int intvalue = Integer.valueOf(buttonvalue);

        switch(intvalue){

            case 1:
                setContentView(R.layout.activity_1);
                break;

            case 2:
                setContentView(R.layout.activity_2);
                break;

            case 3:
                setContentView(R.layout.activity_3);
                break;

            case 4:
                setContentView(R.layout.activity_4);
                break;

            case 5:
                setContentView(R.layout.activity_5);
                break;

            case 6:
                setContentView(R.layout.activity_6);
                break;

            case 7:
                setContentView(R.layout.activity_7);
                break;


            case 8:
                setContentView(R.layout.activity_8);
                break;

            case 9:
                setContentView(R.layout.activity_9);
                break;

            case 10:
                setContentView(R.layout.activity_10);
                break;

            case 11:
                setContentView(R.layout.activity_11);
                break;

            case 12:
                setContentView(R.layout.activity_12);
                break;

            case 13:
                setContentView(R.layout.activity_13);
                break;

            case 14:
                setContentView(R.layout.activity_14);
                break;


        }


        startButton =findViewById(R.id.startbutton);
        mtextview =findViewById(R.id.time);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MTimeRunning){

                    stopTimer();
                }

                else
                {
                    startTimer();
                }
            }
        });






    }
    private  void stopTimer(){
        countdowntimer.cancel();
        MTimeRunning=false;
        startButton.setText("START");

    }


    private void startTimer(){
        final CharSequence value1=mtextview.getText();
        String num1=value1.toString();
        String num2=num1.substring(0,2);
        String num3 =num1.substring(3,5);

        final int number=Integer.valueOf(num2)*60+Integer.valueOf(num3);
        MTimeLeftinmills=number*1000;


        countdowntimer=new CountDownTimer(MTimeLeftinmills,1000) {
            @Override
            public void onTick(long l) {
                MTimeLeftinmills=l;
                upadteTimer();


            }

            @Override
            public void onFinish() {

                int newvalue=Integer.valueOf(buttonvalue)+1;
                if(newvalue<= 7){
                    Intent intent=new Intent(thirdactivity.this,thirdactivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }

                else{
                    newvalue=1;
                    Intent intent=new Intent(thirdactivity.this,thirdactivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }

            }
        }.start();
        startButton.setText("PAUSE");
        MTimeRunning=true;


    }


    private void upadteTimer()
    {

        int minutes=(int)MTimeLeftinmills/60000;
        int seconds = (int)MTimeLeftinmills%60000/1000;


        String timeLeftText="";
        if(minutes<10) {
            timeLeftText = "0";
        }
            timeLeftText=timeLeftText+minutes+":";

            if(seconds<10){
                timeLeftText+="0";
            }


            timeLeftText+=seconds;

            mtextview.setText(timeLeftText);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}