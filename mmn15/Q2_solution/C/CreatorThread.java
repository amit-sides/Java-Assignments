package C;

import java.util.Random;

public class CreatorThread extends Thread {
    private Data data;

    public CreatorThread(Data data)
    {
        super("CreatorThread");
        this.data = data;
    }

    public CreatorThread(String name, Data data)
    {
        super("CreatorThread-" + name);
        this.data = data;
    }

    @Override
    public void run() {
        Random rng = new Random();

        for (int i = 0; i < 10; i++) {
            int x = rng.nextInt();
            int y = rng.nextInt();
            data.update(x, y);
            System.out.println(this.getName() + ":\t Numbers updated by: x=" + x + ", y=" + y);
            try {
                sleep(200);
            } catch (InterruptedException e) { }
        }
    }
}
