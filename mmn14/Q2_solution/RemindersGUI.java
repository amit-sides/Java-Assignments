import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RemindersGUI extends JFrame implements ActionListener {
    private static final int FRAME_EXTRA_HEIGHT = 39;
    private static final int FRAME_EXTRA_WIDTH = 16;
    private static final int JAVA_YEAR_FILTER = 1900;

    private Reminders           reminders = null;
    private JButton             newButton;
    private JButton             openButton;
    private JButton             saveButton;
    private JButton             closeButton;
    private JComboBox<Integer>  dayCombobox;
    private JComboBox<Integer>  monthCombobox;
    private JComboBox<Integer>  yearCombobox;
    private JButton             selectButton;
    private JButton setReminderButton;
    private JTextArea           textArea;
    private final int           FRAME_WIDTH = 500;
    private final int           FRAME_HEIGHT = 200;
    private final int           TEXT_AREA_ROWS = 5;
    private final int           TEXT_AREA_COLUMNS = 20;
    private final int           START_YEAR = 1980;
    private final String        DATABASE_EXTENSION = "serdb";

    public RemindersGUI()
    {
        // Panels
        this.setLayout(new BorderLayout());
        JPanel menuPanel = new JPanel();
        JPanel actionsPanel = new JPanel();
        JPanel textPanel = new JPanel();

        // Menu Panel
        menuPanel.setLayout(new GridLayout(1, 4));
        menuPanel.setBounds(0, 0, FRAME_WIDTH, 100);
        newButton = new JButton("New");
        newButton.addActionListener(this);
        menuPanel.add(newButton);
        openButton = new JButton("Open");
        openButton.addActionListener(this);
        menuPanel.add(openButton);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        menuPanel.add(saveButton);
        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        menuPanel.add(closeButton);

        // Actions Panel
        createDatesComboboxes();
        actionsPanel.setLayout(new GridLayout(2, 4));
        actionsPanel.setBounds(0, 100, FRAME_WIDTH, 100);
        // First row
        actionsPanel.add(new JLabel("Day"));
        actionsPanel.add(new JLabel("Month"));
        actionsPanel.add(new JLabel("Year"));
        setReminderButton = new JButton("Set Reminder");
        setReminderButton.addActionListener(this);
        actionsPanel.add(setReminderButton);
        // Second row
        actionsPanel.add(dayCombobox);
        actionsPanel.add(monthCombobox);
        monthCombobox.addActionListener(this);
        actionsPanel.add(yearCombobox);
        yearCombobox.addActionListener(this);
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        actionsPanel.add(selectButton);

        // Text Panel
        textPanel.setLayout(new GridLayout(1, 1));
        textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        textPanel.add(textArea);

        // Frame
        this.add(menuPanel, BorderLayout.SOUTH);
        this.add(textPanel, BorderLayout.CENTER);
        this.add(actionsPanel, BorderLayout.NORTH);

        reset();
        this.setSize(FRAME_WIDTH + FRAME_EXTRA_WIDTH, FRAME_HEIGHT + FRAME_EXTRA_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Reminders");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void reset()
    {
        this.reminders = null;
        yearCombobox.setEnabled(false);
        yearCombobox.setSelectedIndex(0);
        monthCombobox.setEnabled(false);
        monthCombobox.setSelectedIndex(0);
        dayCombobox.setEnabled(false);
        dayCombobox.removeAllItems();
        setReminderButton.setEnabled(false);
        selectButton.setEnabled(false);
        textArea.setEnabled(false);
        textArea.setText("");
        saveButton.setEnabled(false);
        closeButton.setEnabled(false);
        newButton.setEnabled(true);
        openButton.setEnabled(true);
    }

    private void openNewReminders(String databaseFilepath)
    {
        this.reminders = new Reminders(databaseFilepath);
        if (!databaseFilepath.equals(""))
        {
            try
            {
                this.reminders.open();
            }
            catch (IOException | ClassNotFoundException err)
            {
                JOptionPane optionPane = new JOptionPane("Failed to load reminders from " + databaseFilepath, JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                return;
            }
        }

        newButton.setEnabled(false);
        openButton.setEnabled(false);
        saveButton.setEnabled(true);
        closeButton.setEnabled(true);
        monthCombobox.setEnabled(true);
        yearCombobox.setEnabled(true);
        updateDayCombobox();
        dayCombobox.setEnabled(true);
        dayCombobox.setSelectedIndex(0);
        selectButton.setEnabled(true);
    }

    private void createDatesComboboxes()
    {
        Date d = new Date();

        monthCombobox = new JComboBox<>();
        for (int i = 1; i <= 12 ; i++) {
            monthCombobox.addItem(i);
        }

        yearCombobox = new JComboBox<>();
        for (int i = d.getYear() + JAVA_YEAR_FILTER; i >= START_YEAR; i--) {
            yearCombobox.addItem(i);
        }

        dayCombobox = new JComboBox<>();
        dayCombobox.setEnabled(false);
    }

    private void updateDayCombobox()
    {
        Date d = new Date();

        int currentlySelected = dayCombobox.getSelectedIndex();

        int month = (Integer) monthCombobox.getSelectedItem();
        int year = (Integer) yearCombobox.getSelectedItem();
        int numberOfDays = MyDate.numberOfDays(month, year);
        dayCombobox.removeAllItems();
        for (int i = 1; i <= numberOfDays; i++) {
            dayCombobox.addItem(i);
        }

        try
        {
            dayCombobox.setSelectedIndex(currentlySelected);
        }
        catch (IndexOutOfBoundsException e)
        {
            dayCombobox.setSelectedIndex(0);
        }
    }

    private String chooseFile(boolean should_save)
    {
        int returnVal = -1;
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Serialized Reminders Database", DATABASE_EXTENSION);
        fc.setFileFilter(filter);

        if (should_save)
        {
            returnVal = fc.showSaveDialog(this);
        }
        else
        {
            returnVal = fc.showOpenDialog(this);
        }

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith("." + DATABASE_EXTENSION))
            {
                path += "." + DATABASE_EXTENSION;
            }
            return path;
        } else {
            return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton)
        {
            openNewReminders("");
        }
        else if (e.getSource() == openButton)
        {
            String databaseFilepath = chooseFile(false);
            if (databaseFilepath.equals(""))
            {
                JOptionPane optionPane = new JOptionPane("No file was selected!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                return;
            }
            openNewReminders(databaseFilepath);
        }
        else if (e.getSource() == saveButton)
        {
            // Check if a database path was already given
            if (this.reminders.getDatabaseFilepath().equals(""))
            {
                String databaseFilepath = chooseFile(true);
                if (databaseFilepath.equals(""))
                {
                    JOptionPane optionPane = new JOptionPane("No file was selected!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    return;
                }
                this.reminders.setDatabaseFilepath(databaseFilepath);
            }

            // Saves the reminders to the database
            try
            {
                System.out.println("Saving... " + reminders.getDatabaseFilepath());
                reminders.save();
            } catch (IOException err)
            {
                JOptionPane optionPane = new JOptionPane("Failed to save reminders at " + reminders.getDatabaseFilepath(), JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }
        else if (e.getSource() == closeButton)
        {
            reset();
        }
        else if (e.getSource() == monthCombobox || e.getSource() == yearCombobox)
        {
            updateDayCombobox();
        }
        else if (e.getSource() == selectButton)
        {
            int day = (Integer) dayCombobox.getSelectedItem();
            int month = (Integer) monthCombobox.getSelectedItem();
            int year = (Integer) yearCombobox.getSelectedItem();
            String reminder = this.reminders.getReminder(day, month, year);
            this.textArea.setText(reminder);
            this.textArea.setEnabled(true);
            this.setReminderButton.setEnabled(true);
        }
        else if (e.getSource() == setReminderButton)
        {
            int day = (Integer) dayCombobox.getSelectedItem();
            int month = (Integer) monthCombobox.getSelectedItem();
            int year = (Integer) yearCombobox.getSelectedItem();
            this.reminders.setReminder(day, month, year, this.textArea.getText());
        }
    }
}
