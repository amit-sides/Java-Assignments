import java.security.InvalidParameterException;
import java.util.*;

public class Question {
    private String question; // The question
    private String[] answers; // An array of all the available answers
    private int correctAnswer; // The index of the correct answer in the answers array.

    public static final int ANSWERS_COUNT = 4; // The number of answers for each question

    public Question()
    {
        this.question = "";
        this.answers = new String[ANSWERS_COUNT];
        for (int i = 0; i < ANSWERS_COUNT; i++) {
            this.answers[i] = null;
        }
        this.correctAnswer = -1;
    }

    public Question(String question, String[] answers, int correctAnswer) throws IllegalArgumentException
    {
        if (answers.length != ANSWERS_COUNT)
        {
            throw new IllegalArgumentException("Invalid answers count");
        }

        this.question = question;
        this.answers = new String[ANSWERS_COUNT];
        System.arraycopy(answers, 0, this.answers, 0, ANSWERS_COUNT);
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion()
    {
        return this.question;
    }

    public String getAnswer()
    {
        if (this.correctAnswer == -1)
        {
            return "";
        }
        return this.answers[this.correctAnswer];
    }

    public String[] getAnswerOptions()
    {
        return this.answers;
    }

    // This function shuffles the order of the answers
    public void shuffleOrder()
    {
        String correctAnswer = this.answers[this.correctAnswer];
        this.answers[this.correctAnswer] = null;

        // Randomize answers' order
        List<String> wrongAnswers = Arrays.asList(this.answers);
        Collections.shuffle(wrongAnswers);
        wrongAnswers.toArray(this.answers);
        int i = 0;
        while(this.answers[i] != null) {
            i++;
        }
        this.answers[i] = correctAnswer;
        this.correctAnswer = i;
    }

    // This function parses the question from the scanner.
    public boolean scanQuestion(Scanner scanner)
    {
        String line = "";
        String answer = "";

        try {
            // Scan question
            line = scanner.nextLine();
            if (line.equals(""))
            {
                return false;
            }
            this.question = line;

            // Scan answers
            for (int i = 0; i < ANSWERS_COUNT; i++) {
                line = scanner.nextLine();
                if (line.equals(""))
                {
                    return false;
                }
                this.answers[i] = line;
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            return false;
        }

        // Randomize answers' order
        this.correctAnswer = 0;
        shuffleOrder();
        return true;
    }
}
