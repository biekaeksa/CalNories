package com.spiderman.calnories.data;

/**
 * Created by Biekaeksa on 4/16/2017.
 */

public class DummyModel {
    private String gridDummy;
    private int gridCalorie;

    public DummyModel(String gridDummy, int gridCalorie) {
        this.gridDummy = gridDummy;
        this.gridCalorie = gridCalorie;
    }

    public DummyModel() {
    }

    public String getGridDummy() {
        return gridDummy;
    }

    public void setGridDummy(String gridDummy) {
        this.gridDummy = gridDummy;
    }

    public int getGridCalorie() {
        return gridCalorie;
    }

    public void setGridCalorie(int gridCalorie) {
        this.gridCalorie = gridCalorie;
    }
}
