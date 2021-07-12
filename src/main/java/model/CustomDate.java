package model;

public class CustomDate {
    private static final int minYear = 1300;
    private static final int maxYear = 1500;

    private int year;
    private int month;
    private int day;

    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

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
