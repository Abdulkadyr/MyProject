package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        EditText input = findViewById(R.id.input);
        TextView result = findViewById(R.id.result);
        String text = input.getText().toString(); // 150
        double number = 0;
        try {
            number = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Enter number", Toast.LENGTH_SHORT).show();
        }
        double resultValue = number * 73;
        String push = "$" + number + " = " + resultValue + " KGS";
        result.setText(push);

        Log.i("MyTag", "Button is clicked");
        Log.i("MyTag", "Button is clicked");
        Log.i("MyTag", "$"+ number + "is" + result + "KGS");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}