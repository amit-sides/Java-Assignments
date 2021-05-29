package C;

public class Data{
    private int x = 0;
    private int y = 0;
    private int readers; // Count the number of readers.
    // When readers is -1, it means that someone is writing to the object, and no else may enter the critical section.

    public Data (int x, int y){
        this.x = x;
        this.y = y;
        this.readers = 0;
    }
    public synchronized int getDiff(){
        while (this.readers < 0)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        this.readers++;
        int diff = Math.abs(x-y);
        this.readers--;
        notifyAll();

        return diff;
    }
    public synchronized void update(int dx, int dy){
        while(this.readers != 0)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        this.readers--;
        x = x + dx;
        y = y + dy;
        this.readers++;
        notifyAll();
    }
}