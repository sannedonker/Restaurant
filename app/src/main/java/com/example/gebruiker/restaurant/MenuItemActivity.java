package com.example.gebruiker.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    MenuItem clicked_item;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // collects the received info and sets the text and image views
        Intent intent = getIntent();
        clicked_item = (MenuItem) intent.getSerializableExtra("clicked_item");
        ((TextView)findViewById(R.id.detail_name)).setText(clicked_item.getName());
        ((TextView)findViewById(R.id.detail_description)).setText(clicked_item.getDescription());
        ((TextView)findViewById(R.id.detail_price)).setText("€ " + clicked_item.getPrice() + "0");
        ImageView imageView = findViewById(R.id.detail_image);
        Picasso.with(context).load(clicked_item.getImageUrl()).into(imageView);
    }

}
