package com.example.foodapp.model;

import java.util.List;

public class Recipe {
    public String title;
    private List<Ingredient> ingredientList;

    public Recipe(String title, List<Ingredient> ingredientList) {
        this.title = title;
        this.ingredientList = ingredientList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
