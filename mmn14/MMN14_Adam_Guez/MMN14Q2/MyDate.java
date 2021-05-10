import java.io.Serializable;
import java.util.Objects;

public class MyDate implements Serializable {
    private int day;
    private int month;
    private int year;

    public MyDate(int _day, int _month, int _year){
        this.day = _day;
        this.month = _month;
        this.year = _year;
    }

    // Display
    public String toString(){
        String date;
        return date = day+"/"+month+"/"+year;
    }

    // Using objects hash function to code
    public int hashCode(){
        return Objects.hash(day,month,year);
    }

    public boolean equals(Object o) {
        if (o instanceof MyDate) {
            MyDate comDate = (MyDate) (o); // Date to compare
            return (comDate.day == this.day && comDate.month == this.month && comDate.year == this.year);
        }
        return false;
    }

    // Getters
    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }

    // Setters
    public void setDay(int d){
        this.day = d;
    }

    public void setMonth(int m){
        this.month = m;
    }

    public void setYear(int y){
        this.year = y;
    }
}
