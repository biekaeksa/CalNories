package com.spiderman.calnories.data;

/**
 * Created by Biekaeksa on 4/16/2017.
 */

public class CategoryModel {
    private String kategori;
    private int icon;

    public CategoryModel(String kategori, int icon) {
        this.kategori = kategori;
        this.icon = icon;
    }

    public CategoryModel() {
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
