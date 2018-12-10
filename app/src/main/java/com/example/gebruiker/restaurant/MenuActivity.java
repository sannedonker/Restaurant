package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    MenuItem item;
    String clicked_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        clicked_category = intent.getStringExtra("clicked_category");

        MenuRequest test = new MenuRequest(this);
        test.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> item) {

        // select only the items of the clicked category
        ArrayList<MenuItem> category_items = new ArrayList<>();
        for (int position = 0; position < item.size(); position++) {
            if (item.get(position).getCategory().equals(clicked_category)) {
                category_items.add(item.get(position));
                Log.d("adapter", "pleaaseeee NIEUW");
            }
        }


        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, category_items);
        Log.d("test", "gotMenu: hoi");
        ListView lv = findViewById(R.id.menu_view);
        lv.setAdapter(adapter);
        // TODO onitemclicklistener


    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}

