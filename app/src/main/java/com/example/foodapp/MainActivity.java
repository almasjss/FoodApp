package com.example.foodapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.adapter.IngredientAdapter;
import com.example.foodapp.helper.DatabaseHelper;
import com.example.foodapp.model.Ingredient;
import com.example.foodapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ListView listView;
    private List<Ingredient> ingredientList;
    private IngredientAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView_ingredients);
        databaseHelper = new DatabaseHelper(this);

        ingredientList = new ArrayList<>();
        ingredientAdapter=new IngredientAdapter(this,ingredientList);

        listView.setAdapter(ingredientAdapter);
        EditText nameInput = findViewById(R.id.editText_recipe_name);
        EditText quantityInput = findViewById(R.id.ed_quantity);
        EditText unitInput = findViewById(R.id.ed_unit);
        Button btn = findViewById(R.id.add_ingredient);

        List<Ingredient> ingredients = loadData();
        btn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String quantityText = quantityInput.getText().toString();
            String unitText = unitInput.getText().toString();

            if (!name.isEmpty() && !quantityText.isEmpty() && !unitText.isEmpty()){
                int quantity = Integer.parseInt(quantityText);

                Ingredient ingredient = new Ingredient(quantity,name,unitText);
                ingredientList.add(ingredient);
                saveRecipe(ingredientList);
                ingredientList.clear();
                ingredientAdapter.notifyDataSetChanged();

            }else{
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveRecipe(List<Ingredient> ingredients){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for (Ingredient ingredient: ingredients){
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put(DatabaseHelper.COLUMN_NAME,ingredient.getName());
            ingredientValues.put(DatabaseHelper.COLUMN_QUANTITY,ingredient.getQuantity());
            ingredientValues.put(DatabaseHelper.COLUMN_UNIT,ingredient.getUnit());
            ingredientValues.put(DatabaseHelper.COLUMN_IMAGE_RESOURCE_ID,ingredient.getImageResourceId());
            db.insert(DatabaseHelper.TABLE_INGREDIENTS,null,ingredientValues);
        }
    }

    public List<Ingredient> loadData(long recipeId){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_INGREDIENTS,null,"_id = ?",
                new String[]{String.valueOf(recipeId)},
                null,null,null);

        List<Ingredient> ingredientList = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                String name =
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                int quantity =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY));
                String unit =
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_UNIT));

                ingredientList.add(new Ingredient((quantity,name,unit));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return ingredientList;
    }
}