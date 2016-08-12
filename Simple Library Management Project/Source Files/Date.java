
public class Date {
    public int day,month,year;
    public Date(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean equals(Date date) {
        if (day == date.day && (month == date.month && year == date.year))
        return true;
        else return false;
    }
 }