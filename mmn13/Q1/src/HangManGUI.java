import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManGUI extends JFrame implements ActionListener {
    private static final int FRAME_EXTRA_HEIGHT = 39;
    private static final int FRAME_EXTRA_WIDTH = 16;

    private HangManGame     game;
    private JButton[]       alphabet;
    private JLabel[]        word;
    private JButton         newGame;
    private JLabel          counter;
    private HangManDrawing  paintingPanel;
    private JPanel          wordPanel;
    private final int       FRAME_WIDTH = 800;
    private final int       FRAME_HEIGHT = 800;
    private final int       ALPHABET_ROWS = 2;
    private final int       ALPHABET_COLUMNS = 13;

    public HangManGUI() {
        // Panels
        setLayout(null);
        JPanel menuPanel = new JPanel();
        paintingPanel = new HangManDrawing();
        JPanel alphabetPanel = new JPanel();

        // Game
        game = new HangManGame();

        // Menu Panel
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBounds(0, 0, FRAME_WIDTH, 50);
        newGame = new JButton("New game");
        newGame.addActionListener(this);
        counter = new JLabel(game.getGuessesLeft() + "");
        counter.setFont(new Font(counter.getName(), Font.PLAIN, 20));
        menuPanel.add(newGame, BorderLayout.WEST);
        menuPanel.add(counter, BorderLayout.EAST);
        initializeWord(true);

        // Painting Panel
        paintingPanel.setBounds(0, 50, FRAME_WIDTH, 550);

        // Alphabet Panel
        alphabetPanel.setLayout(new GridLayout(ALPHABET_ROWS, ALPHABET_COLUMNS));
        alphabetPanel.setBounds(0, 700, FRAME_WIDTH, 100);
        alphabet = new JButton['Z' - 'A' + 1];
        int i = 0;
        for (char letter = 'A'; letter <= 'Z' ; letter++)
        {
            alphabet[i] = new JButton(letter + "");
            alphabet[i].addActionListener(this);
            alphabetPanel.add(alphabet[i++]);
        }

        // Frame
        this.add(menuPanel);
        this.add(paintingPanel);
        this.add(wordPanel);
        this.add(alphabetPanel);

        setSize(FRAME_WIDTH + FRAME_EXTRA_WIDTH, FRAME_HEIGHT + FRAME_EXTRA_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeWord(boolean firstTime)
    {
        if (!firstTime)
        {
            wordPanel.setVisible(false);
            this.remove(wordPanel);
        }
        wordPanel = new JPanel();
        wordPanel.setBounds(0, 600, FRAME_WIDTH, 100);
        counter.setText(game.getGuessesLeft() + "");
        wordPanel.setLayout(new GridLayout(1, game.getCurrentWord().length()));
        word = new JLabel[game.getCurrentWord().length()];
        for (int i = 0; i < game.getCurrentWord().length(); i++)
        {
            word[i] = new JLabel("_", SwingConstants.CENTER);
            word[i].setFont(new Font(word[i].getName(), Font.PLAIN, 30));
            wordPanel.add(word[i]);
        }
        this.add(wordPanel);
    }

    private void guessLetter(JButton letterButton)
    {
        letterButton.setEnabled(false);
        char letter = letterButton.getText().toLowerCase().charAt(0);
        if (game.takeGuess(letter))
        {
            // Guess is correct
            String gameWord = game.getCurrentWord().toLowerCase();
            int i = gameWord.indexOf(letter);
            while(i != -1)
            {
                word[i].setText(letter + "");
                i = gameWord.indexOf(letter, i + 1);
            }
            // Check for victory
            checkGameFinished(true);
        }
        else
        {
            // Guess is incorrect
            paintingPanel.addLine();
            counter.setText(game.getGuessesLeft() + "");
            // Check for Game over
            checkGameFinished(false);
        }
    }

    private void checkGameFinished(boolean won)
    {
        if (!game.isFinished())
        {
            return;
        }
        for (JButton button: alphabet) {
            button.setEnabled(false);
        }

        if (won)
        {
            JOptionPane.showMessageDialog(this, "You have won!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "You have lost :(", "Game Over...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame)
        {
            // Set new game
            game.startNewGame();
            paintingPanel.reset();
            for (JButton button: alphabet) {
                button.setEnabled(true);
            }
            initializeWord(false);
        }
        else
        {
            guessLetter((JButton)e.getSource());
        }
    }

}
