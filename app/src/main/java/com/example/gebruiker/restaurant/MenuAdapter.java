package com.example.gebruiker.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter {

    private ArrayList<MenuItem> menu_items;
    Context context;

    // constructor
    public MenuAdapter( Context context, int resource, ArrayList<MenuItem> menu_items) {
        super(context, resource, menu_items);
        this.menu_items = menu_items;
        this.context = context;
    }

    public View getView(int position, View lv, ViewGroup parent) {

        // loads new items
        if (lv == null) {
            lv = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // sets menuItems in the listview
        MenuItem item = menu_items.get(position);
        ImageView imageView = lv.findViewById(R.id.item_image);
        Picasso.with(context).load(item.getImageUrl()).into(imageView);
        ((TextView) lv.findViewById(R.id.item)).setText(item.getName());
        ((TextView) lv.findViewById(R.id.price)).setText("€ " + item.getPrice() + "0");

        return lv;
    }

}
