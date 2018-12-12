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

    private ArrayAdapter<String> category_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest category = new CategoriesRequest(this);
        category.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // set the categories in the listview
        category_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView lv = findViewById(R.id.categories_view);
        lv.setAdapter(category_adapter);

        // collects the necessary info of the clicked category and sends it to MenuActivity
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
