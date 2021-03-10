import javax.swing.*;
import java.awt.*;

/**
 * Implements a GUI to display the Game Of Life.
 */
public class GameOfLifeGUI extends JPanel
{
    public static final int BAR_HEIGHT = 39;
    public static final int BAR_WIDTH = 16;
    public static final int RATIO = 7;
    public static final int SQUARE_COUNT = 10;
    public static final int SQUARE_SIZE = 10;
    public static GameOfLife gameOfLife;

    /**
     * Updates the frame and paints the current state of the game on it.
     */
    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        // Draw sites
        boolean[][] currentSites = gameOfLife.getSites();
        for (int i = 0; i < gameOfLife.width; i++)
        {
            for (int j = 0; j < gameOfLife.height; j++)
            {
                Color siteColor = currentSites[i][j] ? Color.DARK_GRAY : Color.LIGHT_GRAY;
                graphics.setColor(siteColor);
                graphics.fillRect(i * SQUARE_SIZE * RATIO, j * SQUARE_SIZE * RATIO,
                                    SQUARE_SIZE * RATIO, SQUARE_SIZE * RATIO);
            }
        }

        // Draw lines
        graphics.setColor(Color.BLACK);
        ((Graphics2D)graphics).setStroke(new BasicStroke(2));
        for(int i = 1; i < SQUARE_COUNT; i++)
        {
            graphics.drawLine(i * SQUARE_SIZE * RATIO, 0, i * SQUARE_SIZE * RATIO, this.getHeight());
        }
        for(int j = 0; j < SQUARE_COUNT; j++)
        {
            graphics.drawLine(0, j * SQUARE_SIZE * RATIO, this.getWidth(), j * SQUARE_SIZE * RATIO);
        }
    }

    /**
     * Main function of the program.
     */
    public static void main(String[] args) {
        // Creating frame
        JFrame frame = new JFrame("Game Of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(SQUARE_SIZE * SQUARE_COUNT * RATIO + BAR_WIDTH, SQUARE_SIZE * SQUARE_COUNT * RATIO + BAR_HEIGHT);
        frame.setVisible(true);

        // Creating panel
        GameOfLifeGUI gameOfLifeGUI = new GameOfLifeGUI();
        frame.add(gameOfLifeGUI);

        // Creating game object
        gameOfLife = new GameOfLife(SQUARE_COUNT, SQUARE_COUNT);

        // Running the game
        while(true)
        {
            frame.repaint();
            int answer = JOptionPane.showConfirmDialog(null, "Compute next generation?", "Game Of Life", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION)
            {
                gameOfLife.computeNextGeneration();
            }
            else
            {
                break;
            }
        }
    }
}
