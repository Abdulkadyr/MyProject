package com.example.musicshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musicshop.adapters.OrderAdapter;
import com.example.musicshop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    List<Order> orderList = new ArrayList<>();

    List<Order> messageList = new ArrayList<>();

    ListView listView;
    TextView textCart;
    Button proceedBtn;
    private int status;
    String[] addresses = {"abuhanifa1199@gmail.com", "osh.itacademy@gmail.com"};
    String subject = "Music shop. Android Testing";

//    ArrayAdapter<String> orderAdapter;
//    ArrayList<String> orderArrayList = new ArrayList<>();

    public static final String TAG = "SecondTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //variable initialization
        listView = findViewById(R.id.ListView);
        textCart = findViewById(R.id.emptyCart);
        proceedBtn = findViewById(R.id.proceed);

        Intent intent = getIntent();

        status = intent.getIntExtra("status", 0);

        messageList = (List<Order>) intent.getSerializableExtra("orders");


        final StringBuilder  message = new StringBuilder();

        double counter = 0;
        for (Order order : messageList){
            counter = counter + order.getGoodPrice();
            message.append("Customer name: " + order.getUserName() + "\n" +
                    "Goods name: " + order.getGoodName() + "\n"+
                    "Quantity: " + order.getGoodQuantity() + " \n"+
                    "Order price: " + order.getGoodPrice() + "\n\n");
        }


        message.append(" \n \n Итоговая сумма: " + counter);


        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(message));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        if(status == 1){
            try{
                orderList = (List<Order>) intent.getSerializableExtra("orders");
                OrderAdapter adapter = new OrderAdapter(this,R.layout.list_item, orderList);
                listView.setAdapter(adapter);
            } catch (NullPointerException e){}
        }else{
            listView.setVisibility(View.GONE);
            textCart.setVisibility(View.VISIBLE);
            proceedBtn.setVisibility(View.GONE);
        }

















//        for(Order order : orderList){
//            orderArrayList.add(order.getGoodName());
//        }
//
//        for(Order order : orderList){
//            Log.i(TAG,order.toString());
//        }
//
//
//        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderArrayList);
//        listView.setAdapter(orderAdapter);




    }
}