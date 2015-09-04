package com.example.dawid.dietalpha.model;

/**
 * Created by Dawid on 2015-09-04.
 */
public class ItemData {
    private String name;
    private String weigth;
    private String carbo;
    private String fat;
    private String cal;

    public ItemData(String name, String weigth, String carbo, String fat, String cal) {
        this.name = name;
        this.weigth = weigth;
        this.carbo = carbo;
        this.fat = fat;
        this.cal = cal;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }

    public String getCarbo() {
        return carbo;
    }

    public void setCarbo(String carbo) {
        this.carbo = carbo;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }
}
