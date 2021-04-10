import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class HangManGame {
    private int                     guessesLeft;
    private String                  currentWord;
    private boolean[]               lettersFound;
    private int                     wordsCount;

    private final static int        INITIAL_GUESSES = 10;
    private final static Random     RAND = new Random();
    private final static String     FILEPATH = "words.txt";

    public HangManGame() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
            wordsCount = 0;
            String line = reader.readLine();
            while(line != null)
            {
                if (!line.equals(""))
                {
                    wordsCount++;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e)
        {
            JOptionPane optionPane = new JOptionPane("Words file not found! location: " + FILEPATH, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            System.exit(-1);
        } catch (IOException e) {
            JOptionPane optionPane = new JOptionPane("Failed to read word from " + FILEPATH, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            System.exit(-2);
        }
        startNewGame();
    }

    private String chooseWord()
    {
        int word_index = RAND.nextInt(wordsCount);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
            String line = reader.readLine();
            for (int i = 0; i < wordsCount && line != null; i++) {
                if (word_index == i)
                {
                    return line;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e)
        {
            JOptionPane optionPane = new JOptionPane("Words file not found! location: " + FILEPATH, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            System.exit(-1);
        } catch (IOException e) {
            JOptionPane optionPane = new JOptionPane("Failed to read word from " + FILEPATH, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            System.exit(-2);
        }
        return "";
    }

    public void startNewGame() {
        this.guessesLeft = INITIAL_GUESSES;
        this.currentWord = chooseWord();
        this.lettersFound = new boolean[this.currentWord.length()];
        Arrays.fill(this.lettersFound, false);
    }

    public boolean takeGuess(char letter)
    {
        letter = (letter + "").toLowerCase().charAt(0);
        String word = this.currentWord.toLowerCase();
        int i = word.indexOf(letter);
        if (i == -1)
        {
            // Incorrect guess
            this.guessesLeft--;
            return false;
        }

        while(i != -1)
        {
            this.lettersFound[i] = true;
            i = word.indexOf(letter, i + 1);
        }
        return true;
    }

    public boolean isFinished()
    {
        if (this.guessesLeft <=0)
        {
            return true;
        }

        for (boolean b : this.lettersFound) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

    public final String getCurrentWord() {
        return currentWord;
    }
}
