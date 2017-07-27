package com.spiderman.calnories.data;

import java.util.List;

/**
 * Created by Biekaeksa on 5/29/2017.
 */

public class ProfileModel {
    private float kkal;
    private String category;

    public float getKkal() {
        return kkal;
    }

    public void setKkal(float kkal) {
        this.kkal = kkal;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public class ProfileDataModel extends BaseProfileModel{
        private List<ProfileModel> results;

        public List<ProfileModel> getResults() {
            return results;
        }

        public void setResults(List<ProfileModel> results) {
            this.results = results;
        }
    }
}
