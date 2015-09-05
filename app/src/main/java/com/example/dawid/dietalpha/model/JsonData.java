package com.example.dawid.dietalpha.model;

/**
 * Created by Dawid on 2015-09-04.
 */
public class JsonData {
    private String name;
    private String id;

    public JsonData(String gname, String gid) {
        this.name = gname;
        this.id = gid;
    }

    public String getGname() {
        return name;
    }

    public void setGname(String gname) {
        this.name = gname;
    }

    public String getGid() {
        return id;
    }

    public void setGid(String gid) {
        this.id = gid;
    }

    @Override
    public String toString() {
        return "|" + name + " : " + id + "|";
    }
}
