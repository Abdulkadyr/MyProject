package com.example.musicshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicshop.model.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, View.OnClickListener {



    Button minus, plus, addToCard;
    TextView quantity, priceView;
    ImageView goodImage;
    Spinner spinner;
    ArrayList<String> spinnerArrayList = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    HashMap<String, Double> database;
    String goodName;
    double price;
    EditText userName;
    List<Order> orderList = new ArrayList<>();



    private int amount = 0;
    private static final String TAG = "MyTag";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart){
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            if(!orderList.isEmpty()){
                intent.putExtra("orders", (Serializable) orderList);
                intent.putExtra("status", 1);
            }else{
                intent.putExtra("status", 0);
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//    Variable initialization
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        quantity = findViewById(R.id.quantity_text);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        priceView = findViewById(R.id.price);
        goodImage = findViewById(R.id.imageView2);
        addToCard = findViewById(R.id.addToCard);
        userName = findViewById(R.id.username);


//        ArrayList for Spinner
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("rock");

//        Adapter for Spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

//        DataBase for Spinner
        database = new HashMap<>();
        database.put("guitar", 500.0);
        database.put("keyboard", 1000.0);
        database.put("drums", 700.0);
        database.put("rock", 1500.0);



        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        addToCard.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName = spinner.getSelectedItem().toString();  //guitar
        price = database.get(goodName);//500.0
        priceView.setText(String.valueOf(price));
        amount = 1;
        quantity.setText("1");

        if(goodName.equals("guitar")){
            goodImage.setImageResource(R.drawable.guitar);
        }else if(goodName.equals("keyboard")){
            goodImage.setImageResource(R.drawable.keyboard);
        }else if(goodName.equals("drums")){
            goodImage.setImageResource(R.drawable.drums);
        }else{
            goodImage.setImageResource(R.drawable.rock);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus:
                amount++;
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price*amount));
                break;
            case R.id.minus:
                amount--;
                if (amount < 0){
                    amount = 0;
                }
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price * amount));
                break;
            case R.id.addToCard:
                orderProcess();
                break;

        }
    }

    public void orderProcess(){
        Order order = new Order();

        if(!TextUtils.isEmpty(userName.getText().toString())){
            order.setUserName(userName.getText().toString());
            order.setGoodName(spinner.getSelectedItem().toString());
            order.setGoodPrice(Double.parseDouble( priceView.getText().toString()));
            order.setGoodQuantity(Integer.parseInt(quantity.getText().toString()));

            if(orderList.size() > 0) {

                Order answer = isHas(order.getGoodName(), orderList);

                if (answer != null){
                    answer.setGoodQuantity(answer.getGoodQuantity() + order.getGoodQuantity());
                    answer.setGoodPrice(answer.getGoodPrice() + order.getGoodPrice());
                }
                else{
                    orderList.add(order);
                }
            }else{
                orderList.add(order);
            }

            Log.i(TAG,order.toString());
            Toast.makeText(this, "Вы успешно добавили товар в корзину!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Пожалуйста заполните поле ", Toast.LENGTH_SHORT).show();
        }

    }

    public Order isHas(String goodName, List<Order> orderList){
        Order result = null;
        for (Order order : orderList){
            if (order.getGoodName().equals(goodName)){
                result = order;
            }
        }
        return result;
    }

}