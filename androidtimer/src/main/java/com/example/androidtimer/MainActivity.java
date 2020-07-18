package com.example.androidtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    TextView textView;
    Button btn;
    ImageView img;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.timeText);
        btn = findViewById(R.id.button);
        img = findViewById(R.id.lamp);

        player = MediaPlayer.create(this, R.raw.alarm_bell);

        final SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int time = progress;
                int min = time /60;
                int sec = time%60;


                String timeView;


                if(min > 9){
                    timeView = min + ":" + "0"+sec;
                    textView.setText( timeView );
                } else if(min < 10 || sec < 10){
                    if(sec < 10){
                        timeView = "0"+min + ":" + "0"+sec;
                        textView.setText( timeView );
                    }

                    else if(min < 10){
                        timeView = "0"+min + ":" +sec;
                        textView.setText( timeView );
                    }
                }
//                else{
//                    timeView = "0"+min + ":" + sec;
//                    textView.setText( timeView );
//                }




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.bulb_off);
                new CountDownTimer(seekBar.getProgress()*1000, 1000) {


                    public void onTick(long sec) {

                        btn.setEnabled(false);
                        int time = (int) (sec/1000);
                        int minutes = time / 60;
                        int seconds = time % 60;

                        String timeView;
                        if(minutes < 10 || seconds < 10){
                            if(seconds < 10){
                                timeView = "0"+minutes + ":" + "0"+seconds;
                                textView.setText( timeView );
                            }

                            else if(minutes < 10){
                                timeView = "0"+minutes + ":" +seconds;
                                textView.setText( timeView );
                            }

                        }
                        else{
                            timeView = "0"+minutes + ":" + seconds;
                            textView.setText( timeView );
                        }




                    }

                    public void onFinish() {
                        img.setImageResource(R.drawable.bulb_on);
                        player.start();
                        btn.setEnabled(true);

                    }


                }.start();


            }
        });


    }

    public void timeFormat(long sec){}



}
