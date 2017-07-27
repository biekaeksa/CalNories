package com.spiderman.calnories.data;

import java.util.List;

/**
 * Created by Biekaeksa on 7/2/2017.
 */

public class SportsModel {
    private String id_activity;
    private String activity_name;
    private float calories_burn;
    private int duration;
    private String photo_url;

    public String getId_activity() {
        return id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public float getCalories_burn() {
        return calories_burn;
    }

    public void setCalories_burn(float calories_burn) {
        this.calories_burn = calories_burn;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public class SportsDataModel extends BaseModel{
        private List<SportsModel> result;

        public List<SportsModel> getResult() {
            return result;
        }

        public void setResult(List<SportsModel> result) {
            this.result = result;
        }
    }

    public class SportsDataaModel extends BaseModel{
        private SportsModel result;

        public SportsModel getResult() {
            return result;
        }

        public void setResult(SportsModel result) {
            this.result = result;
        }
    }
}
