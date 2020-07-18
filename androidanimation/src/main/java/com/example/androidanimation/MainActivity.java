package com.example.androidanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity {

    ImageView bart, homer;

    boolean bartPlus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bart = findViewById(R.id.bart);
        homer = findViewById(R.id.homer);


//        bart.animate().rotation(360).setStartDelay(2000);
        //rotation = повороты от 0 до 360
        //scaleX = увеличение или уменьшение = принимает float 0.5f
        //translation = передвижение объекта

        bart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bartPlus){
                    bart.animate().alpha(0).setDuration(2000).rotation(bart.getRotation() + 1800).scaleX(0).scaleY(0);
                    homer.animate().alpha(1).setDuration(2000).rotation(homer.getRotation() + 1800).scaleX(1).scaleY(1);
                    bartPlus = false;
                }else{
                    bart.animate().alpha(1).setDuration(2000).rotation( bart.getRotation() - 1800).scaleY(1).scaleX(1);
                    homer.animate().alpha(0).setDuration(2000).rotation(homer.getRotation() - 1800).scaleY(0).scaleX(0);
                    bartPlus = true;
                }




            }
        });
    }
}
