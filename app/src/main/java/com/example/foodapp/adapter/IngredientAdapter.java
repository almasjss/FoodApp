package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends BaseAdapter {
    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.ingredient_item, parent,false);
        }
        Ingredient currentIng = (Ingredient) getItem(position);
        TextView name = convertView.findViewById(R.id.ingredient_name);
        TextView quantity = convertView.findViewById(R.id.quantity);
        TextView unit = convertView.findViewById(R.id.unit);
        ImageView imageView = convertView.findViewById(R.id.ingredient_image);

        name.setText(currentIng.getName());
        quantity.setText(String.valueOf(currentIng.getQuantity()));
        unit.setText(currentIng.getUnit());
        imageView.setImageResource(currentIng.getImageResourceId());

        return convertView;

    }
}
