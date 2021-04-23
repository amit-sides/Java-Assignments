import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TriviaGUI extends JFrame implements ActionListener {
    private static final int FRAME_EXTRA_HEIGHT = 39;
    private static final int FRAME_EXTRA_WIDTH = 16;

    private Trivia game; // The game's object
    private JRadioButton[] answers; // Radio buttons for the available answers
    private ButtonGroup answersGroup; // The group of the answer's radio button - so only one answer can be selected at a time
    private JButton newGame; // Button to start a new game
    private JButton submit; // Button to submit the answer
    private JButton end; // Button to end the game
    private JLabel score; // Displays the current score
    private JLabel timerCountdown; // Displays the amount of seconds left to answer
    private JLabel question; // Displays the question
    private JLabel feedback; // Displays a feedback for the answer after it was submitted - Correct / Wrong
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 450;
    private static final int FEEDBACK_TIMEOUT = 3; // The number of seconds the feedback will be visible after submission.
    private static final String QUESTIONS_DATABASE = "trivia.txt";

    public TriviaGUI() {
        // Game
        game = new Trivia(QUESTIONS_DATABASE, this::timerAction);

        // Panels
        setLayout(null);
        JPanel menuPanel = new JPanel();
        JPanel questionPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel answersPanel = new JPanel();
        JPanel actionsPanel = new JPanel();

        // Menu Panel
        menuPanel.setLayout(new GridLayout(1, 3));
        menuPanel.setBounds(0, 0, FRAME_WIDTH, 50);
        newGame = new JButton("New game");
        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.addActionListener(this);
        score = new JLabel("Score: 0");
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setFont(new Font(score.getName(), Font.PLAIN, 20));
        timerCountdown = new JLabel("Seconds: 0");
        timerCountdown.setHorizontalAlignment(JLabel.CENTER);
        timerCountdown.setFont(new Font(score.getName(), Font.PLAIN, 20));
        menuPanel.add(newGame);
        menuPanel.add(timerCountdown);
        menuPanel.add(score);

        // Question Panel
        questionPanel.setLayout(new GridLayout(1, 1));
        questionPanel.setBounds(0, 50, FRAME_WIDTH, 100);
        question = new JLabel("");
        question.setFont(new Font(question.getName(), Font.PLAIN, 20));
        question.setHorizontalAlignment(JLabel.CENTER);
        questionPanel.add(question);

        // Bottom Panel
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setBounds(0, 150, FRAME_WIDTH, 300);

        // Answers Panel
        answersPanel.setLayout(new GridLayout(Question.ANSWERS_COUNT + 1, 1));
        answers = new JRadioButton[Question.ANSWERS_COUNT];
        answersGroup = new ButtonGroup();
        for (int i = 0; i < Question.ANSWERS_COUNT; i++) {
            answers[i] = new JRadioButton("");
            answers[i].setMnemonic(KeyEvent.VK_1 + i);
            answers[i].addActionListener(this);
            answers[i].setEnabled(false);
            answersGroup.add(answers[i]);
            answersPanel.add(answers[i]);
        }


        // Actions Panel
        actionsPanel.setLayout(new BorderLayout());
        feedback = new JLabel("");
        feedback.setFont(new Font(feedback.getName(), Font.PLAIN, 20));
        submit = new JButton("Submit Answer");
        submit.setMnemonic(KeyEvent.VK_S);
        submit.addActionListener(this);
        submit.setEnabled(false);
        end = new JButton("End Game");
        end.setMnemonic(KeyEvent.VK_E);
        end.addActionListener(this);
        end.setEnabled(false);
        actionsPanel.add(submit, BorderLayout.WEST);
        actionsPanel.add(feedback, BorderLayout.CENTER);
        actionsPanel.add(end, BorderLayout.EAST);
        answersPanel.add(actionsPanel);

        // Frame
        bottomPanel.add(answersPanel);
        this.add(menuPanel);
        this.add(questionPanel);
        this.add(bottomPanel);

        this.setTitle("Ultimate Trivia");
        this.setSize(FRAME_WIDTH + FRAME_EXTRA_WIDTH, FRAME_HEIGHT + FRAME_EXTRA_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // This is a callback function that is called by the timer in the game's object.
    // It doesn't implement any logic of the game, but updates the user's information on the screen
    // after the game's object processed the user input and after each tick of the timer reduces the amount of seconds left.
    private void timerAction()
    {
        this.score.setText("Score: " + game.getScore());
        this.timerCountdown.setText("Seconds: " + game.getCountdown());
        if (this.game.getCountdown() <= Trivia.QUESTION_TIMEOUT - FEEDBACK_TIMEOUT)
        {
            this.feedback.setText("");
        }
        if (this.game.getCountdown() <= 0)
        {
            // Timeout has passed
            if (answersGroup.getSelection() != null)
            {
                String submittedAnswer = answersGroup.getSelection().getActionCommand();
                this.score.setText("Score: " + this.game.submitAnswer(submittedAnswer, feedback));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No answer was given :(\nContinuing to next question...", "Timeout", JOptionPane.PLAIN_MESSAGE);
                this.score.setText("Score: " + this.game.submitAnswer(null));
            }

            this.presentQuestion();
        }
    }

    // This function is an internal function that is used to end the game and display the final score.
    // It also resets every label or button on the screen to restore it's state to the initial state before starting a game.
    private void endGame()
    {
        int score = game.endGame();
        game.restart();
        JOptionPane.showMessageDialog(this, "Your game has ended!\n Your final score is " + score, "Game Finished", JOptionPane.PLAIN_MESSAGE);

        this.score.setText("Score: 0");
        this.timerCountdown.setText("Seconds: 0");
        this.question.setText("");
        this.newGame.setEnabled(true);
        this.submit.setEnabled(false);
        this.end.setEnabled(false);
        this.feedback.setText("");
        for (JRadioButton answer : this.answers) {
            answer.setEnabled(false);
            answer.setText("");
        }
        this.answersGroup.clearSelection();
    }

    // This function is an internal function that is used to present a new question.
    // If no question is available anymore, it ends the game using the endGame function.
    private void presentQuestion()
    {
        if (game.getQuestionsLeft() <= 0)
        {
            // Game was ended
            endGame();
            return;
        }
        Question q = game.startQuestion();
        this.question.setText("<html>"+ q.getQuestion() +"</html>"); // The html is added for word-wrapping
        String[] answers = q.getAnswerOptions();
        for (int i = 0; i < Question.ANSWERS_COUNT; i++) {
            this.answers[i].setText(answers[i]);
            this.answers[i].setEnabled(true);
            this.answers[i].setActionCommand(answers[i]);
        }
        answersGroup.clearSelection();
        this.submit.setEnabled(false);
        this.timerCountdown.setText("Seconds: " + game.getCountdown());
    }

    // This is an action listener that processes the input of the following buttons:
    // 1. New game button - when pressed, a new game is starting and the button is disabled.
    // 2. Submit button - when pressed, the selected answer is submitted and a new question is presented.
    // 3. End game button - when pressed, the game is over and the user is prompt with it's final score.
    //                      The screen is restored to it's initial state.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame)
        {
            // Set new game
            this.presentQuestion();
            this.end.setEnabled(true);
            this.newGame.setEnabled(false);
        }
        else if (e.getSource() == submit)
        {
            // Submit answer
            String submittedAnswer = answersGroup.getSelection().getActionCommand();
            this.score.setText("Score: " + this.game.submitAnswer(submittedAnswer, feedback));

            // Display next question
            this.presentQuestion();
        }
        else if (e.getSource() == end)
        {
            // End the game
            endGame();
        }
        else
        {
            // Answer selected
            submit.setEnabled(true);
        }
    }
}