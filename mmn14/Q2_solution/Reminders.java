import java.io.*;
import java.util.HashMap;

public class Reminders implements Serializable {
    private transient String databaseFilepath;
    private HashMap<MyDate, String> reminders;

    public Reminders(String databaseFilepath)
    {
        this.databaseFilepath = databaseFilepath;
        this.reminders = new HashMap<>();
    }

    public void open() throws IOException, ClassNotFoundException
    {
        FileInputStream databaseFile = new FileInputStream(this.databaseFilepath);
        ObjectInputStream databaseStream = new ObjectInputStream(databaseFile);
        this.reminders = (HashMap<MyDate, String>) databaseStream.readObject();
        databaseStream.close();
        databaseFile.close();
    }

    public void save() throws IOException
    {
        FileOutputStream databaseFile = new FileOutputStream(this.databaseFilepath);
        ObjectOutputStream databaseStream = new ObjectOutputStream(databaseFile);
        databaseStream.writeObject(this.reminders);
        databaseStream.close();
        databaseFile.close();
    }

    public String getDatabaseFilepath() {
        return databaseFilepath;
    }

    public void setDatabaseFilepath(String databaseFilepath)
    {
        this.databaseFilepath = databaseFilepath;
    }

    public void setReminder(int day, int month, int year, String reminder)
    {
        MyDate d = new MyDate(day, month, year);
        this.reminders.put(d, reminder);
    }

    public String getReminder(int day, int month, int year)
    {
        MyDate d = new MyDate(day, month, year);
        return this.reminders.getOrDefault(d, "");
    }

    @Override
    public String toString() {
        return "Reminders[databaseFilepath='" + databaseFilepath + "', remindersCount=" + reminders.size() + ']';
    }
}
