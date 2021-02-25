package com.wc.metrorailsheba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProceedToPay extends AppCompatActivity {

    TextView station, price, priceDouble, priceTotal, priceUnit, passengerUnit, date;
    CardView single, Double, Price;
    Spinner passenger;
    Map<String, Integer> priceMap = new HashMap<String, Integer>() {
        {
            put("Uttara North", 0);
            put("Uttara Center", 5);
            put("Uttara South", 10);
            put("Pallabi", 15);
            put("Mirpur-11", 20);
            put("Mirpur-10", 25);
            put("KaziPara", 30);
            put("ShewraPara", 35);
            put("Agargaon", 40);
            put("Bijoy Sarani", 45);
            put("Farmgate", 50);
            put("Karwan Bazar", 55);
            put("Shahbag", 60);
            put("Dhaka University", 65);
            put("BD-Secretariat", 70);
            put("Motijheel", 75);
            put("Kamalapur", 80);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_to_pay);

        station = findViewById(R.id.textStationDetail);
        price = findViewById(R.id.textPrice);
        priceDouble = findViewById(R.id.textPriceDouble);
        priceTotal = findViewById(R.id.textPriceTotal);
        priceUnit = findViewById(R.id.textPriceUnit);
        passengerUnit = findViewById(R.id.textPassengerUnit);
        date = findViewById(R.id.textDate);
        single = findViewById(R.id.card_single);
        Double = findViewById(R.id.card_double);
        Price = findViewById(R.id.card_price);




        passenger = findViewById(R.id.spinnerPassengerNumber);

        String dt = getIntent().getExtras().getString("date", "" );
        String from = getIntent().getExtras().getString("from", "Uttara North");
        String to = getIntent().getExtras().getString("to", "Kamalapur");

        final int prc = Math.abs(priceMap.get(to) - priceMap.get(from));

        station.setText(from+" --- "+to);
        price.setText(String.valueOf(prc));
        priceDouble.setText(String.valueOf(prc*2));

        date.setText(dt);

        String np = passenger.getSelectedItem().toString();
        final int pNumber = Integer.parseInt(np);

        priceTotal.setText(String.valueOf(prc*pNumber));
        priceUnit.setText(String.valueOf(prc));
        passengerUnit.setText("*  "+String.valueOf(pNumber));

        passenger.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nop = passenger.getSelectedItem().toString();
                int passengerNumber = Integer.parseInt(nop);

                priceTotal.setText(String.valueOf(prc*passengerNumber));
                priceUnit.setText(String.valueOf(prc));
                passengerUnit.setText("*  "+String.valueOf(passengerNumber));
                Price.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Price.setVisibility(View.GONE);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Price.setVisibility(View.VISIBLE);
                String nop = passenger.getSelectedItem().toString();
                int passengerNumber = Integer.parseInt(nop);

                priceTotal.setText(String.valueOf(prc*passengerNumber));
                priceUnit.setText(String.valueOf(prc));
                passengerUnit.setText("*  "+String.valueOf(passengerNumber));
            }
        });

        Double.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Price.setVisibility(View.VISIBLE);
                String nop = passenger.getSelectedItem().toString();
                int passengerNumber = Integer.parseInt(nop);

                priceTotal.setText(String.valueOf(prc*2*passengerNumber));
                priceUnit.setText(String.valueOf(prc*2));
                passengerUnit.setText("*  "+String.valueOf(passengerNumber));
            }
        });
    }
}
