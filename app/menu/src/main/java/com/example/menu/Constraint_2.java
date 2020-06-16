package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Constraint_2 extends AppCompatActivity implements View.OnClickListener {

    EditText field1;
    EditText field2;
    TextView result;
    Button btnPlus;
    Button btnMinus;
    Button btnMult;
    Button btnDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_2);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        result = findViewById(R.id.result);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String field1Str = field1.getText().toString();
        String field2Str = field2.getText().toString();
        double resultValue;
        double field1Double;
        double field2Double;
        field1Double = Double.parseDouble(field1Str);
        field2Double = Double.parseDouble(field2Str);

        switch (v.getId()){
            case R.id.btnPlus:
                resultValue = field1Double + field2Double;
                result.setText(Double.toString(resultValue));
                break;
            case R.id.btnMinus:
                resultValue = field1Double - field2Double;
                result.setText(Double.toString(resultValue));
                break;
            case R.id.btnMult:
                resultValue = field1Double * field2Double;
                result.setText(Double.toString(resultValue));
                break;
            case R.id.btnDiv:
                resultValue = field1Double / field2Double;
                result.setText(Double.toString(resultValue));
                break;
        }
    }

}