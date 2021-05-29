package C;

public class DiffThread extends Thread {
    private Data data;

    public DiffThread(Data data)
    {
        super("DiffThread");
        this.data = data;
    }
    public DiffThread(String name, Data data)
    {
        super("DiffThread-"+name);
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + ":\t\t The current diff is " + data.getDiff());
            try {
                sleep(200);
            } catch (InterruptedException e) { }
        }
    }
}
