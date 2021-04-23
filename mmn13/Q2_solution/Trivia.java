import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;

public class Trivia implements ActionListener {
    private ArrayList<Question> questions; // Stores the questions loaded from the file
    private int currentQuestion; // An index, in the questions ArrayList, to the current question
    private int score; // The current score of the player
    private final Timer timer; // The timer object that tracks the time left to answer the question
    private int countdown; // The number of second left to answer the question, updated by the timer object
    private final Runnable timerCallback; // A callback given by the GUI class, it is called after each tick of the timer
                                          // so that the GUI class can update the information on the screen accordingly

    public static final int QUESTION_TIMEOUT = 30; // The number of seconds to answer each question
    public static final int CORRECT_ANSWER_REWARD = 10; // The reward for a correct answer
    public static final int WRONG_ANSWER_PENALTY = 5; // The penaly for a wrong answer / timeout
    public static final int TIMER_INTERVAL = 1000; // The timer's intreval = 1000 milliseconds = 1 second

    public Trivia(String questionsDatabase, Runnable timerCallback)
    {
        this.timer = new javax.swing.Timer(TIMER_INTERVAL, this);
        this.timerCallback = timerCallback;

        this.questions = new ArrayList<Question>();
        this.initializeQuestions(questionsDatabase);
        Collections.shuffle(this.questions);
    }

    // This is an internal function that loads the questions from the database file.
    private void initializeQuestions(String questionsDatabase)
    {
        try {
            Scanner database = new Scanner(new File(questionsDatabase));

            // Scan the answers
            boolean question_scanned = true;
            do {
                Question q = new Question();
                question_scanned = q.scanQuestion(database);
                if (question_scanned)
                {
                    this.questions.add(q);
                }
            } while (question_scanned);

            database.close();

        } catch (FileNotFoundException e) {
            JOptionPane optionPane = new JOptionPane("Database file not found! location: " + questionsDatabase, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            System.exit(-1);
        }
    }

    public int getScore()
    {
        return this.score;
    }

    public int getCountdown()
    {
        return this.countdown;
    }

    public int getQuestionsLeft()
    {
        return this.questions.size() - this.currentQuestion;
    }

    // This function is used to start the current question.
    // It resets the countdown and returns the question's object.
    public Question startQuestion() throws IllegalStateException
    {
        if (this.currentQuestion >= this.questions.size())
        {
            throw new IllegalStateException("No more questions available");
        }
        this.countdown = QUESTION_TIMEOUT;
        this.timer.start();
        return this.questions.get(this.currentQuestion);
    }

    // This function ends the game. It stops the timer and returns the final score.
    public int endGame()
    {
        this.timer.stop();
        return this.getScore();
    }

    // This function restarts the game.
    // It shuffles the order of the questions and shuffles the order of the answers in each question.
    public void restart()
    {
        Collections.shuffle(this.questions);
        for(Question q : this.questions)
        {
            q.shuffleOrder();
        }
        this.currentQuestion = 0;
        this.score = 0;
        this.countdown = QUESTION_TIMEOUT;
    }

    public int submitAnswer(String answer)
    {
        return submitAnswer(answer, null);
    }

    // This function is used to submit an answer and advance to the next question.
    // It gets an optional label so that the user can get feedback to it's answer (apart from the score).
    public int submitAnswer(String answer, JLabel feedback)
    {
        if (this.questions.get(this.currentQuestion).getAnswer().equals(answer))
        {
            this.score += CORRECT_ANSWER_REWARD;
            if (feedback != null)
            {
                feedback.setText("Correct!");
            }
        }
        else
        {
            this.score -= WRONG_ANSWER_PENALTY;
            if (feedback != null)
            {
                feedback.setText("Wrong...");
            }
        }
        this.currentQuestion++;
        return this.score;
    }

    // This is the action listener of the timer.
    // It is called for each tick of the timer (once per second).
    @Override
    public void actionPerformed(ActionEvent e) {
        countdown--;
        this.timerCallback.run();
        if (countdown <= 0)
        {
            this.score -= WRONG_ANSWER_PENALTY;
            this.currentQuestion++;
            this.timer.stop();
        }
    }
}
