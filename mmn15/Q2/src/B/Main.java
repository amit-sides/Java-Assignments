package B;

public class Main {

    public static void main(String[] args) {
        Data data = new Data(0, 0);
        CreatorThread creatorThread = new CreatorThread(data);
        DiffThread diffThread = new DiffThread(data);

        creatorThread.start();
        diffThread.start();

        try {
            creatorThread.join();
            diffThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
