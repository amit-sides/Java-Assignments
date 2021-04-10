import javax.swing.*;
import java.awt.*;

public class HangManDrawing extends JPanel {
    private int lineCount;

    public HangManDrawing()
    {
        super();
        reset();
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        ((Graphics2D)graphics).setStroke(new BasicStroke(2));

        if (lineCount >= 1)
        {
            graphics.drawLine(100, 500, 700, 500);
        }
        if (lineCount >= 2)
        {
            graphics.drawLine(550, 500, 550, 50);
        }
        if (lineCount >= 3)
        {
            graphics.drawLine(550, 50, 350, 50);
        }
        if (lineCount >= 4)
        {
            graphics.drawLine(350, 50, 350, 120);
        }
        if (lineCount >= 5)
        {
            graphics.drawOval(350-80/2, 120, 80, 80);
        }
        if (lineCount >= 6)
        {
            graphics.drawLine(350, 120+80, 350, 340);
        }
        if (lineCount >= 7)
        {
            graphics.drawLine(350, 340, 380, 390);
        }
        if (lineCount >= 8)
        {
            graphics.drawLine(350, 340, 320, 390);
        }
        if (lineCount >= 9)
        {
            graphics.drawLine(350, 260, 380, 300);
        }
        if (lineCount >= 10)
        {
            graphics.drawLine(350, 260, 320, 300);
        }

    }

    public void reset()
    {
        lineCount = 0;
        this.repaint();
    }

    public void addLine()
    {
        lineCount++;
        this.repaint();
    }
}
