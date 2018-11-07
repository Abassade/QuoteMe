package com.example.abass.quoteme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_LENGTH_NAME = 20;
    private static final int MAX_LENGTH_QUOTE = 250;
    Button save;
     LinearLayout parent_view;
     EditText quote, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        quote = findViewById(R.id.quote);
        name = findViewById(R.id.name);
        parent_view = findViewById(R.id.parent_view);

        name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH_NAME)});
        quote.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH_QUOTE)});


        hideKeyboard();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote_txt = quote.getText().toString();
                String name_txt = name.getText().toString();

                if(quote_txt.trim().equals("") || name_txt.trim().equals("")){

                    Toast.makeText(getBaseContext(), "Ensure filling out the above fields", Toast.LENGTH_SHORT).show();

                }
                else{

                Intent intent = new Intent(getApplicationContext(), QuoteActivity.class);
                intent.putExtra("quote", quote_txt);
                intent.putExtra("name", name_txt);
                startActivity(intent);
                }
            }
        });
    }

    public void hideKeyboard(){

        parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager methodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                assert methodManager != null;
                methodManager.hideSoftInputFromWindow(quote.getWindowToken(), 0);
                methodManager.hideSoftInputFromWindow(name.getWindowToken(), 1);
            }
        });
    }

    long back_pressed=0;
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
        {
           Toast.makeText(getBaseContext(), "Double tap to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
