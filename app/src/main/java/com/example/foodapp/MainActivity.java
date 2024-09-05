package com.example.foodapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.helper.DatabaseHelper;
import com.example.foodapp.model.Ingredient;
import com.example.foodapp.model.Recipe;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.
                getReadableDatabase();
        String[] columns = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_QUANTITY,
                DatabaseHelper.COLUMN_UNIT,
                DatabaseHelper.COLUMN_IMAGE_RESOURCE_ID
        };
        int[] viewIds = {
                R.id.ingredient_name,
                R.id.quantity,
                R.id.unit,
                R.id.ingredient_image
        };

        Cursor cursor = database.query(DatabaseHelper.TABLE_INGREDIENTS,
                columns, null, null, null, null, null);

        simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.ingredient_item,cursor,columns,viewIds,0);

        listView = findViewById(R.id.listView_ingredients);
        listView.setAdapter(simpleCursorAdapter);

    }
    public void saveRecipe(Recipe recipe){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues recipeValues = new ContentValues();
        recipeValues.put("recipe_name",recipe.getTitle());
        long recipeId = db.insert("recipes",null,recipeValues);

        for (Ingredient ingredient: recipe.getIngredients()){
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put("recipe_id",recipeId);
            ingredientValues.put("name",ingredient.getName());
            ingredientValues.put("quantity",ingredient.getQuantity());
            ingredientValues.put("unit",ingredient.getUnit());
            db.insert("ingredients",null,ingredientValues);
        }
    }

}