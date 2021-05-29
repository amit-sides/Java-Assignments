package B;

public class Data{
    private int x = 0;
    private int y = 0;
    private boolean needToUpdate;

    public Data (int x, int y){
        this.x = x;
        this.y = y;
        this.needToUpdate = true;
    }
    public synchronized int getDiff(){
        while (needToUpdate)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        int diff = Math.abs(x-y);
        needToUpdate = true;
        notifyAll();
        return diff;
    }
    public synchronized void update(int dx, int dy){
        while(!needToUpdate)
        {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        x = x + dx;
        y = y + dy;
        needToUpdate = false;
        notifyAll();
    }
}