package com.example.chandana.mydbtwo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chandana on 02-05-2017.
 */

public class Employee implements Parcelable {

    private int id;
    private String name;
    private String empId;
    private String mobileNumber;
    private String email;
    private String state;
    private String city;

    public Employee() {
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        name = in.readString();
        empId = in.readString();
        mobileNumber = in.readString();
        email = in.readString();
        state = in.readString();
        city = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(empId);
        dest.writeString(mobileNumber);
        dest.writeString(email);
        dest.writeString(state);
        dest.writeString(city);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
}
