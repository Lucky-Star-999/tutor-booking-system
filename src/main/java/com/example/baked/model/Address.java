package com.example.baked.model;

public class Address {
    private String province_city;
    private String ward_district;
    private String home_number;


    public Address() {
    }


    public Address(String province_city, String ward_district, String home_number) {
        this.province_city = province_city;
        this.ward_district = ward_district;
        this.home_number = home_number;
    }



    public String getProvince_city() {
        return this.province_city;
    }

    public void setProvince_city(String province_city) {
        this.province_city = province_city;
    }

    public String getWard_district() {
        return this.ward_district;
    }

    public void setWard_district(String ward_district) {
        this.ward_district = ward_district;
    }

    public String getHome_number() {
        return this.home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number = home_number;
    }


    @Override
    public String toString() {
        return
            getProvince_city() + ", " + getWard_district() + ", " + getHome_number();
    }

}
