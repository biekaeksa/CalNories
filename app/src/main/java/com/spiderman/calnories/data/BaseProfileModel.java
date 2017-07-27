package com.spiderman.calnories.data;

/**
 * Created by Biekaeksa on 5/29/2017.
 */

public class BaseProfileModel {
    private int status;
    private String message;
    private float calorieTarget;
    private float calorieDaily;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getCalorieTarget() {
        return calorieTarget;
    }

    public void setCalorieTarget(float calorieTarget) {
        this.calorieTarget = calorieTarget;
    }

    public float getCalorieDaily() {
        return calorieDaily;
    }

    public void setCalorieDaily(float calorieDaily) {
        this.calorieDaily = calorieDaily;
    }
}
