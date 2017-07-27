package com.spiderman.calnories.data;

import java.util.List;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class FoodModel {
    private String id_food;
    private String food_name;
    private String photo_url;
    private float calories_food;
    private String category;
    private String receipt;
    private String how_to_make;
    private String messege;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getId_food() {
        return id_food;
    }

    public void setId_food(String id_food) {
        this.id_food = id_food;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public float getCalories_food() {
        return calories_food;
    }

    public void setCalories_food(float calories_food) {
        this.calories_food = calories_food;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getHow_to_make() {
        return how_to_make;
    }

    public void setHow_to_make(String how_to_make) {
        this.how_to_make = how_to_make;
    }

    public class FoodDataModel extends BaseModel{
        private List<FoodModel> result;

        public List<FoodModel> getResult() {
            return result;
        }

        public void setResult(List<FoodModel> result) {
            this.result = result;
        }
    }

}
