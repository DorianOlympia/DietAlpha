package com.example.dawid.dietalpha.model;

/**
 * Created by Dawid on 2015-09-04.
 */
public class ItemData {
    private String name;
    private float weigth;
    private float carbo;
    private float fat;
    private float cal;
    private float pro;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeigth() {
        return weigth;
    }

    public void setWeigth(float weigth) {
        this.weigth = weigth;
    }

    public float getCarbo() {
        return carbo;
    }

    public void setCarbo(float carbo) {
        this.carbo = carbo;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getPro() {
        return pro;
    }

    public void setPro(float pro) {
        this.pro = pro;
    }

    public float getCal() {
        return cal;
    }

    public void setCal(float cal) {
        this.cal = cal;
    }

    public ItemData(String name, float weigth, float carbo, float fat, float cal, float pro) {
        this.name = name;
        this.weigth = weigth;
        this.carbo = carbo;
        this.fat = fat;
        this.cal = cal;
        this.pro = pro;
    }

    public void setAmountToFulfilProteins(float proteins){
        float k = proteins/pro;
        pro = proteins;
        carbo *= k;
        weigth *= k;
        cal *= k;
        fat *= k;

    }
}
