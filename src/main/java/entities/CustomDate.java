package entities;

public class CustomDate {
    private int year;
    private int month;
    private int day;

    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static CustomDate createDate() {
        return new CustomDate(-1,-1,-1);
    }

    public static CustomDate intToDate(int intDate) {
        int date = intDate;
        int day = date % 100;
        date /= 100;
        int month = intDate % 100;
        date /= 100;
        if (date > 1500 || date < 1360) {
            System.out.println("Bad Int Date!");
        }
        return new CustomDate(date, month, day);
    }

    public int toInt() {
        return this.year * 10000 + this.month * 100 + this.day;
    }

    @Override
    public String toString() {
        return "" + this.year + '/' + this.month + '/' + this.day;
    }
}
