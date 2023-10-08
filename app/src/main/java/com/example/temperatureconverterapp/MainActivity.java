package com.example.temperatureconverterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    TextView convert_from, convert_to, answer;
    EditText amount;
    ArrayList<String> arrayList;
    Dialog fromDialog;
    Dialog toDialog;
    Button convert;
    String convertFromValue, convertToValue, conversionValue;
    String[] temperature={"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Newton", "Reaumur", "Romer", "Delisle"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.BLACK);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        convert_from = findViewById(R.id.convert_from);
        convert_to = findViewById(R.id.convert_to);
        convert = findViewById(R.id.convert);
        answer = findViewById(R.id.answer);
        amount = findViewById(R.id.amount);

        arrayList = new ArrayList<>();

        Collections.addAll(arrayList, temperature);

        convert_from.setOnClickListener(v -> {
            fromDialog = new Dialog(MainActivity.this);
            fromDialog.setContentView(R.layout.from_spinner);
            fromDialog.getWindow().setLayout(650,800);
            fromDialog.show();

            EditText editText = fromDialog.findViewById(R.id.fedittext);
            ListView listView = fromDialog.findViewById(R.id.flistview);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            listView.setOnItemClickListener((parent, view, position, id) -> {
                convert_from.setText(adapter.getItem(position));
                fromDialog.dismiss();
                convertFromValue = adapter.getItem(position);
            });

        });

        convert_to.setOnClickListener(v -> {
            toDialog = new Dialog(MainActivity.this);
            toDialog.setContentView(R.layout.to_spinner);
            toDialog.getWindow().setLayout(650,800);
            toDialog.show();

            EditText editText = toDialog.findViewById(R.id.tedittext);
            ListView listView = toDialog.findViewById(R.id.tlistview);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            listView.setOnItemClickListener((parent, view, position, id) -> {
                convert_to.setText(adapter.getItem(position));
                toDialog.dismiss();
                convertToValue = adapter.getItem(position);
            });

        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double amount = Double.valueOf(MainActivity.this.amount.getText().toString());
                    answer.setText(getConversion(convertFromValue,convertToValue,amount));
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getConversion(String convertFrom, String convertTo, Double amount){

        String answer="0";
        // Celsius, Fahrenheit , Kelvin , Rankine , Newton , Reaumur , Romer , Delisle
        //Celsius
        if(convertFrom.contains("Celsius") && convertTo.contains("Celsius")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Fahrenheit")){
            //Formula: °F = (°C × 9/5) + 32
            answer = String.valueOf((amount * 9 / 5) + 32);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Kelvin")){
            //Formula: K = °C + 273.15
            answer = String.valueOf(amount + 273.15);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Rankine")){
            //Formula: °R = (°C + 273.15) × 9/5
            answer = String.valueOf((amount + 273.15) * 9/5);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Newton")){
            //Formula: °N = °C × 33/100
            answer = String.valueOf(amount * 33 / 100);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Reaumur")){
            //Formula: °Re = °C × 4/5
            answer = String.valueOf(amount * 4 / 5);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Romer")){
            //Formula: °Rø = (°C × 21/40) + 7.5
            answer = String.valueOf((amount * 21 / 40) + 7.5);
        }
        else if(convertFrom.contains("Celsius") && convertTo.contains("Delisle")){
            //Formula: °De = (100 - °C) × 3/2
            answer = String.valueOf((100 - amount) * 3/2);
        }

        //Fahrenheit
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Fahrenheit")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Celsius")){
            //Formula: °C = (°F - 32) × 5/9
            answer = String.valueOf((amount - 32) * 5 / 9);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Kelvin")){
            //Formula: K = (°F - 32) × 5/9 + 273.15
            answer = String.valueOf((amount - 32) * 5 / 9 + 273.15);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Rankine")){
            //Formula: °R = °F + 459.67
            answer = String.valueOf(amount + 459.67);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Newton")){
            //Formula: °N = (°F - 32) × 11/60
            answer = String.valueOf((amount - 32) * 11 / 60);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Reaumur")){
            //Formula: °Re = (°F - 32) × 4/9
            answer = String.valueOf((amount - 32) * 4 / 9);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Romer")){
            //Formula: °Rø = ((°F - 32) × 7/24) + 7.5
            answer = String.valueOf(((amount - 32) * 7 / 24) + 7.5);
        }
        else if(convertFrom.contains("Fahrenheit") && convertTo.contains("Delisle")){
            //Formula: °De = ((212 - °F) × 5/6)
            answer = String.valueOf(((212 - amount) * 5 / 6));
        }

        //Kelvin
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Kelvin")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Celsius")){
            //Formula: °C = K - 273.15
            answer = String.valueOf(amount - 273.15);
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Fahrenheit")){
            //Formula: °F = (K - 273.15) × 9/5 + 32
            answer = String.valueOf((amount - 273.15) * 9 / 5 + 32);
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Rankine")){
            //Formula: °R = K × 9/5
            answer = String.valueOf(amount * 9 / 5);
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Newton")){
            //Formula: °N = ((K - 273.15) × 33/100)
            answer = String.valueOf(((amount - 273.15) * 33 / 100));
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Reaumur")){
            //Formula: °Re = ((K - 273.15) × 4/5)
            answer = String.valueOf(((amount - 273.15) * 4 / 5));
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Romer")){
            //Formula: °Rø = (((K - 273.15) × 21/40) + 7.5)
            answer = String.valueOf((((amount - 273.15) * 21 / 40) + 7.5));
        }
        else if(convertFrom.contains("Kelvin") && convertTo.contains("Delisle")){
            //Formula: °De = ((373.15 - K) × 3/2)
            answer = String.valueOf(((373.15 - amount) * 3 / 2));
        }

        //Rankine
        else if(convertFrom.contains("Rankine") && convertTo.contains("Rankine")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Celsius")){
            //Formula: °C = (°R - 491.67) × 5/9
            answer = String.valueOf((amount - 491.67) * 5 / 9);
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Fahrenheit")){
            //Formula: °F = °R - 459.67
            answer = String.valueOf(amount - 459.67);
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Kelvin")){
            //Formula: K = °R × 5/9
            answer = String.valueOf(amount * 5 / 9);
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Newton")){
            //Formula: °N = ((°R - 491.67) × 11/60)
            answer = String.valueOf(((amount - 491.67) * 11 / 60));
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Reaumur")){
            //Formula: °Re = ((°R - 491.67) × 4/9)
            answer = String.valueOf(((amount - 491.67) * 4 / 9));
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Romer")){
            //Formula: °Rø = (((°R - 491.67) × 7/24) + 7.5)
            answer = String.valueOf((((amount - 491.67) * 7 / 24) + 7.5));
        }
        else if(convertFrom.contains("Rankine") && convertTo.contains("Delisle")){
            //Formula: °De = ((671.67 - °R) × 5/6)
            answer = String.valueOf(((671.67 - amount) * 5 / 6));
        }

        //Newton
        else if(convertFrom.contains("Newton") && convertTo.contains("Newton")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Celsius")){
            //Formula: °C = (°N × 100/33)
            answer = String.valueOf((amount * 100) / 33);
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Fahrenheit")){
            //Formula: °F = ((°N × 60/11) + 32)
            answer = String.valueOf(((amount * 60 / 11) + 32));
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Kelvin")){
            //Formula: K = (((°N × 100/33) + 273.15))
            answer = String.valueOf((((amount * 100 / 33) + 273.15)));
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Rankine")){
            //Formula: °R = (((°N × 60/11) + 491.67))
            answer = String.valueOf((((amount * 60 / 11) + 491.67)));
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Reaumur")){
            //Formula: °Re = ((°N × 80/33))
            answer = String.valueOf(((amount * 80 / 33)));
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Romer")){
            //Formula: °Rø = (((°N × 35/22) + 7.5))
            answer = String.valueOf((((amount * 35 / 22) + 7.5)));
        }
        else if(convertFrom.contains("Newton") && convertTo.contains("Delisle")){
            //Formula: °De = ((33.33 - °N × 20/11))
            answer = String.valueOf(((33.33 - amount * 20 / 11)));
        }

        //Reaumur
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Reaumur")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Celsius")){
            //Formula: °C = (°Re × 5/4)
            answer = String.valueOf((amount * 5 / 4));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Fahrenheit")){
            //Formula: °F = ((°Re × 9/4) + 32)
            answer = String.valueOf(((amount * 9 / 4) + 32));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Kelvin")){
            //Formula: K = (((°Re × 5/4) + 273.15))
            answer = String.valueOf((((amount * 5 / 4) + 273.15)));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Rankine")){
            //Formula: °R = (((°Re × 9/4) + 491.67))
            answer = String.valueOf((((amount * 9 / 4) + 491.67)));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Newton")){
            //Formula: °N = ((°Re × 33/80))
            answer = String.valueOf(((amount * 33 / 80)));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Romer")){
            //Formula: °Rø = (((°Re × 32/15) + 7.5))
            answer = String.valueOf((((amount * 32 / 15) + 7.5)));
        }
        else if(convertFrom.contains("Reaumur") && convertTo.contains("Delisle")){
            //Formula: °De = ((60 - °Re × 9/8))
            answer = String.valueOf(((60 - amount * 9 / 8)));
        }

        //Romer
        else if(convertFrom.contains("Romer") && convertTo.contains("Romer")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Celsius")){
            //Formula: °C = (((°Rø - 7.5) × 40/21))
            answer = String.valueOf((((amount - 7.5) * 40 / 21)));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Fahrenheit")){
            //Formula: °F = ((((°Rø - 7.5) × 24/7) + 32))
            answer = String.valueOf(((((amount - 7.5) * 24 / 7) + 32)));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Kelvin")){
            //Formula: K = (((°Rø - 7.5) × 40/21) + 273.15)
            answer = String.valueOf((((amount - 7.5) * 40 / 21) + 273.15));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Rankine")){
            //Formula: °R = ((((°Rø - 7.5) × 24/7) + 491.67))
            answer = String.valueOf(((((amount - 7.5) * 24 / 7) + 491.67)));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Newton")){
            //Formula: °N = (((°Rø - 7.5) × 22/35))
            answer = String.valueOf((((amount - 7.5) * 22 / 35)));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Reaumur")){
            //Formula: °Re = (((°Rø - 7.5) × 15/32))
            answer = String.valueOf((((amount - 7.5) * 15 / 32)));
        }
        else if(convertFrom.contains("Romer") && convertTo.contains("Delisle")){
            //Formula: °De = (((60 - (°Rø - 7.5) × 8/5)))
            answer = String.valueOf((((60 - (amount - 7.5) * 8 / 5))));
        }

        //Delisle
        else if(convertFrom.contains("Delisle") && convertTo.contains("Delisle")){
            //amount
            answer = String.valueOf(amount);
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Celsius")){
            //Formula: °C = (100 - °De) × 2/3
            answer = String.valueOf((100 - amount) * 2 / 3);
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Fahrenheit")){
            //Formula: °F = (212 - (°De × 6/5))
            answer = String.valueOf((212 - (amount * 6 / 5)));
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Kelvin")){
            //Formula: K = (373.15 - °De × 2/3)
            answer = String.valueOf((373.15 - amount * 2 / 3));
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Rankine")){
            //Formula: °R = (671.67 - °De × 5/6)
            answer = String.valueOf((671.67 - amount * 5 /6));
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Newton")){
            //Formula: °N = (33.33 - °De × 20/11)
            answer = String.valueOf((33.33 - amount * 20 / 11));
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Reaumur")){
            //Formula: °Re = (80 - °De × 8/9)
            answer = String.valueOf((80 - amount * 8 / 9));
        }
        else if(convertFrom.contains("Delisle") && convertTo.contains("Romer")){
            //Formula: °Rø = (60 - (°De × 8/5) + 7.5)
            answer = String.valueOf((60 - (amount * 8 / 5) + 7.5));
        }

        return answer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.closeapp) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert!");
            builder.setMessage("Do you want to exit?");
            builder.setCancelable(false);
            builder.setPositiveButton("YES",(dialog, which) -> finish());
            builder.setNegativeButton("NO",(dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}