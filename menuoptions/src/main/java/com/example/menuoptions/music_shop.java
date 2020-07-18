package com.example.menuoptions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class music_shop extends AppCompatActivity {

    Button minus, plus;
    TextView quantity;
    Spinner spinner;
    ArrayList<String> spinnerArrayList = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    private int amount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        quantity = findViewById(R.id.quantity_text);

        spinner = findViewById(R.id.spinner);

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("rock");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount++;
                quantity.setText(String.valueOf(amount));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount--;
                if (amount < 0){
                    amount = 0;
                }
                quantity.setText(String.valueOf(amount));
            }
        });
    }
}