package com.example.dawid.dietalpha.model;

/**
 * Created by Dawid on 2015-09-04.
 */
public class GroupJSONData {
    private String gname;
    private String gid;

    public GroupJSONData(String gname, String gid) {
        this.gname = gname;
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "|" + gname + " : " + gid + "|";
    }
}
