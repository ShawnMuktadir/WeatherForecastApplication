package com.example.mdmuktadir.weatherforecastapplication;

public class Weather {

    public String date;
    public String minTemp;
    public String maxTemp;
    public String link;
    public String dayCondition;
    public String nightCondition;

    public Weather() {
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.link = link;
        this.dayCondition = dayCondition;
        this.nightCondition = nightCondition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDayCondition() {
        return dayCondition;
    }

    public void setDayCondition(String dayCondition) {
        this.dayCondition = dayCondition;
    }

    public String getNightCondition() {
        return nightCondition;
    }

    public void setNightCondition(String nightCondition) {
        this.nightCondition = nightCondition;
    }
}
