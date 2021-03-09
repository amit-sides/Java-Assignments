import java.util.Random;

public class GameOfLife {
    // `sites` represents the matrix of sites - True represents a living site, False represents dead site.
    private final boolean[][] sites;
    public final int width;
    public final int height;

    public GameOfLife(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.sites = new boolean[this.width][this.height];

        // Randomize the starting sites status
        Random rand = new Random();
        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                this.sites[i][j] = rand.nextBoolean();
            }
        }
    }

    public boolean[][] getSites()
    {
        boolean[][] sitesCopy = new boolean[this.width][this.height];

        // Copy sites to array
        for (int i = 0; i < this.width; i++)
        {
            sitesCopy[i] = this.sites[i].clone();
        }
        return sitesCopy;
    }

    private int countLivingNeighbors(int row, int column)
    {
        int sum = 0;

        for(int i = row-1; i <= row+1; i++)
        {
            for (int j = column-1; j <= column+1; j++)
            {
                // Skip same square
                if (i == row && j == column)
                {
                    continue;
                }

                // Check for boundaries
                if (i < 0 || i >= this.width || j < 0 || j >= this.height)
                {
                    continue;
                }

                // Check neighbor value & Add neighbor to sum
                if (this.sites[i][j])
                {
                    sum++;
                }
            }
        }

        return sum;
    }

    public void computeNextGeneration()
    {
        boolean[][] nextGeneration = new boolean[this.width][this.height];

        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                int livingNeighbors = countLivingNeighbors(i, j);
                if (this.sites[i][j])
                {
                    // This site is alive
                    nextGeneration[i][j] = (2 <= livingNeighbors) && (livingNeighbors <= 3);
                }
                else
                {
                    // This site is dead
                    nextGeneration[i][j] = livingNeighbors == 3;
                }
            }
        }

        // Copy next generation to sites matrix
        for (int i = 0; i < this.width; i++)
        {
            this.sites[i] = nextGeneration[i].clone();
        }
    }
}
