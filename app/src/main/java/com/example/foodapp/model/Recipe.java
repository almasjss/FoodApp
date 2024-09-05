package com.example.foodapp.model;

import java.util.List;

public class Recipe {
    private String title;
    private List<Ingredient> ingredientList;

    public Recipe(String title, List<Ingredient> ingredientList) {
        this.title = title;
        this.ingredientList = ingredientList;
    }

    public void addIngredient(Ingredient ingredient){
        ingredientList.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredientList.remove(ingredient);
    }

    public List<Ingredient> getIngredients(){
        return ingredientList;
    }
}
