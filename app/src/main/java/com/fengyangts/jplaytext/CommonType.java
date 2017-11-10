package com.fengyangts.jplaytext;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Description: 公共的类型类
 * Created by Xiaoming on 2017/7/10 14:32.
 * Email:xiaoming_huo@163.com
 */

public class CommonType implements Serializable,Parcelable {

    private String name;//名字
    private String firstLetter; //分类
    private String id;

    private boolean selected;


    protected CommonType(Parcel in) {
        name = in.readString();
        firstLetter = in.readString();
        id = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<CommonType> CREATOR = new Creator<CommonType>() {
        @Override
        public CommonType createFromParcel(Parcel in) {
            return new CommonType(in);
        }

        @Override
        public CommonType[] newArray(int size) {
            return new CommonType[size];
        }
    };

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstLetter() {
        return TextUtils.isEmpty(firstLetter)?"#":firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public CommonType() {
    }

    public CommonType(String name, String id) {
        this(name,id,false);
    }

    public CommonType(String name, String id, String firstLetter) {
        this.name = name;
        this.firstLetter = firstLetter;
        this.id = id;
    }

    public CommonType(String name, String id, boolean selected) {
        this.name = name;
        this.id=id;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CommonType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(firstLetter);
        dest.writeString(id);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}
