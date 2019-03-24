package com.helpsend.app4;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

    private int id;
    private String name;
    private String contact;
    private String level;
    private String subject;
    private String location;
    private String timedate;

    public Request() {

    }

    public Request(String iName,
                   String iContact,
                   String iLevel,
                   String iSubject,
                   String iLocation,
                   String iTimedate) {

        this.name = iName;
        this.contact = iContact;
        this.level = iLevel;
        this.subject = iSubject;
        this.location = iLocation;
        this.timedate = iTimedate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    @Override
    public String toString() {
        return name + " \n" + level + " \n" + subject + " \n" + location;
    }

    protected Request(Parcel in) {
        id = in.readInt();
        name = in.readString();
        contact = in.readString();
        level = in.readString();
        subject = in.readString();
        location = in.readString();
        timedate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(contact);
        dest.writeString(level);
        dest.writeString(subject);
        dest.writeString(location);
        dest.writeString(timedate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}