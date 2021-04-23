import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class HangManGame {
    private int                     guessesLeft; // The number of guesses left before the game is over
    private String                  currentWord; // The current word that's being guessed
    private boolean[]               lettersFound; // An array of the word's letters, if a letter was guessed, it's value is true
    private int                     wordsCount; // The number of words in the database

    private final static int        INITIAL_GUESSES = 10;
    private final static Random     RAND = new Random(); // The random object used for the entire game
    private final static String     FILEPATH = "words.txt";

    public HangManGame() {
        // Counts the number of words from the database
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
        // Randomly chooses a word from the database and reads it from the database by scanning it line-by-line
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

    // Receives a letter to guess and checks if the letter exist in the word.
    // It also updates the array of the found letters accordingly
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

    // Checks if the game is finished, either by discovering all the letters or by using all available guesses.
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
