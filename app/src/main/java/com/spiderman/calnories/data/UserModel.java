package com.spiderman.calnories.data;

import java.util.List;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public class UserModel {
    private String idUser;
    private String displayName;
    private String email;
    private String photoUrl;
    private int weight;
    private int height;
    private int age;
    private int calories_target;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCalories_target() {
        return calories_target;
    }

    public void setCalories_target(int calories_target) {
        this.calories_target = calories_target;
    }

    public class UserDataModel extends BaseModel {
        private UserModel result;

        public UserModel getResult(){
            return result;
        }

        public void setResult(UserModel result){
            this.result = result;
        }
    }
}
