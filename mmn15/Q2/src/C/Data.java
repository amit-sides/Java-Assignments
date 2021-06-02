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

    private synchronized void acquireReadLock()
    {
        while (this.readers < 0)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        this.readers++;
    }

    private synchronized void releaseReadLock()
    {
        this.readers--;
        notifyAll();
    }

    private synchronized void acquireWriteLock()
    {
        while(this.readers != 0)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        this.readers--;
    }

    private synchronized void releaseWriteLock()
    {
        this.readers++;
        notifyAll();
    }

    public int getDiff()
    {
        acquireReadLock();
        int diff = Math.abs(x-y);
        releaseReadLock();
        return diff;
    }
    public void update(int dx, int dy)
    {
        acquireWriteLock();
        x = x + dx;
        y = y + dy;
        releaseWriteLock();
    }
}