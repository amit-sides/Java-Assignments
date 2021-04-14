import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;

public class Trivia implements ActionListener {
    private ArrayList<Question> questions;
    private int currentQuestion;
    private int score;
    private final Timer timer;
    private int countdown;
    private final Runnable timerCallback;

    public static final int QUESTION_TIMEOUT = 30;
    public static final int CORRECT_ANSWER_REWARD = 10;
    public static final int WRONG_ANSWER_PENALTY = 5;
    public static final int TIMER_INTERVAL = 1000;

    public Trivia(String questionsDatabase, Runnable timerCallback)
    {
        this.timer = new javax.swing.Timer(TIMER_INTERVAL, this);
        this.timerCallback = timerCallback;

        this.questions = new ArrayList<Question>();
        this.initializeQuestions(questionsDatabase);
        Collections.shuffle(this.questions);
    }

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

    public int endGame()
    {
        this.timer.stop();
        return this.getScore();
    }

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
