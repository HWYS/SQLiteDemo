package com.hwys.sqlitedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GenreModel implements Parcelable {
    private int gId;
    private String gName;

    public GenreModel(int gId, String gName) {
        this.gId = gId;
        this.gName = gName;
    }

    public int getgId() {
        return gId;
    }

    public String getgName() {
        return gName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gId);
        dest.writeString(this.gName);
    }

    protected GenreModel(Parcel in) {
        this.gId = in.readInt();
        this.gName = in.readString();
    }

    public static final Parcelable.Creator<GenreModel> CREATOR = new Parcelable.Creator<GenreModel>() {
        @Override
        public GenreModel createFromParcel(Parcel source) {
            return new GenreModel(source);
        }

        @Override
        public GenreModel[] newArray(int size) {
            return new GenreModel[size];
        }
    };
}
