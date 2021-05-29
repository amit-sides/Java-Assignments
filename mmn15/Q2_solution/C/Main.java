package C;

public class Main {

    public static void main(String[] args) {
        Data data = new Data(0, 0);
        CreatorThread creatorThreads[] = {
                new CreatorThread("1", data),
                new CreatorThread("2", data),
                new CreatorThread("3", data),
                new CreatorThread("4", data),
        };
        DiffThread diffThreads[] = {
                new DiffThread("1", data),
                new DiffThread("2", data),
                new DiffThread("3", data),
                new DiffThread("4", data),
        };

        for (int i = 0; i < 4; i++) {
            creatorThreads[i].start();
            diffThreads[i].start();
        }

        try {
            for (int i = 0; i < 4; i++) {
                creatorThreads[i].join();
                diffThreads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
