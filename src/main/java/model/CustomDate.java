package model;

import views.MainPage;

public class CustomDate {
    private static final int minYear = 1300;
    private static final int maxYear = 1500;

    private int year;
    private int month;
    private int day;

    // constructors
    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // getters
    public Integer getYear() {
        return year;
    }
    public Integer getMonth() {
        return month;
    }
    public Integer getDay() {
        return day;
    }

    // static methods
    public static CustomDate intToDate(int intDate) {
        int date = intDate;
        int day = date % 100;
        date /= 100;
        int month = date % 100;
        date /= 100;
        if (date > maxYear || date < minYear) {
            System.out.println("Bad Int Date!");
        }
        return new CustomDate(date, month, day);
    }
    public static Integer dateStringToInt(String date) throws Exception {
        int c = 0;
        String yearStr = "";
        String monthStr = "";
        String dayStr = "";
        int year;
        int month;
        int day;

        while (date.charAt(c) != '/') {
            yearStr += date.charAt(c);
            c++;
        }
        c++;
        while (date.charAt(c) != '/') {
            monthStr += date.charAt(c);
            c++;
        }
        c++;
        while (c != date.length()) {
            dayStr += date.charAt(c);
            c++;
        }

        try {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
        } catch (Exception exception) {
            throw new Exception("Date Is Not Valid!");
        }

        CustomDate customDate = new CustomDate(year, month, day);
        if (!customDate.isValid()) {
            throw new Exception("Enter A Valid Date");
        }

        return customDate.toInt();
    }

    // methods
    public int toInt() {
        return this.year * 10000 + this.month * 100 + this.day;
    }
    public boolean isValid() {
        if (this.year < minYear || this.year > maxYear) {
            return false;
        }
        if (this.month < 1 || this.month > 12) {
            return false;
        }
        if (this.day < 1 || this.day > 31) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.year + '/' + this.month + '/' + this.day;
    }
}
