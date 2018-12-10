package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback  {

    private ArrayList<String> categories = new ArrayList<>();
    private ArrayAdapter<String> category_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

//        // instantiate the adapter and attach the adapter to the gridview
//        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.activity_categories, categories);
//        ListView lv = findViewById(R.id.categories_view);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new ListItemClickListener());

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this,categories.get(0),Toast.LENGTH_LONG).show();

        category_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView lv = findViewById(R.id.categories_view);
        lv.setAdapter(category_adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                intent.putExtra("clicked_category", category);
                startActivity(intent);
            }
        });
    }


    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}