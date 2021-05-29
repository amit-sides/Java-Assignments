package A;

public class Data{
    private int x = 0;
    private int y = 0;
    public Data (int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getDiff(){
        return (Math.abs(x-y));
    }
    public void update(int dx, int dy){
        x = x + dx;
        y = y + dy;
    }
}