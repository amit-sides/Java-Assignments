import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;

public class ReminderPanel extends JPanel {
    private JLabel lblDateSelection;
    private JComboBox selectDay, selectMonth, selectYear;
    private JTextArea txtData;
    private JButton cmdSubmit, cmdRetrieval;
    private JScrollPane txtJScrollPane;
    private HashMap<MyDate, String> dateReminder;
    private File currentDir;
    private String fileName;
    private File f;
    private char[] readedData;

    public ReminderPanel(){
        lblDateSelection = new JLabel("Select Date: ");
        selectDay = new JComboBox();
        selectMonth = new JComboBox();
        selectYear = new JComboBox();
        txtData = new JTextArea(5, 20 );
        txtData.setFont(new java.awt.Font("Courier New", 0, 24));
        cmdSubmit = new JButton("Submit");
        cmdRetrieval = new JButton("Retrieval");
        dateReminder = new HashMap<MyDate, String>();
        currentDir = new File("C:\\");
        Calendar today = Calendar.getInstance();

        // JComboFiller, year, month, day
        // by default show today's date
        for(int i = 0; i <= 20; i++){ //change me with current date
            selectYear.addItem(today.get(Calendar.YEAR) - 10 + i); // Current year +- 10 years
        }
        selectYear.setSelectedItem(today.get(Calendar.YEAR));

        for(int i = 1; i <= 12; i++){ // Months
            selectMonth.addItem(i);
        }
        selectMonth.setSelectedItem(today.get(Calendar.MONTH));

        for(int i = 1; i <= 31; i++){ // Set 31 days, only for the first display, later changes with setDaysInMonth method
            selectDay.addItem(i);
        }
        selectDay.setSelectedItem(today.get(Calendar.DAY_OF_MONTH));

        // Date Selection
        JPanel dateSelection = new JPanel();
        dateSelection.setLayout(new GridLayout(1,4, 5, 5));
        dateSelection.add(lblDateSelection);
        dateSelection.add(selectDay);
        dateSelection.add(selectMonth);
        dateSelection.add(selectYear);

        // Buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2, 20, 20));
        buttons.add(cmdSubmit);
        buttons.add(cmdRetrieval);

        // Text display
        txtJScrollPane = new JScrollPane(txtData);
        txtJScrollPane.setViewportView(txtData);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(dateSelection);
        add(Box.createVerticalGlue());
        add(buttons);
        add(Box.createVerticalGlue());
        add(txtJScrollPane);

        // Listeners
        ButtonsListener listener = new ButtonsListener();
        cmdSubmit.addActionListener(listener);
        cmdRetrieval.addActionListener(listener);
        selectDay.addActionListener(listener);
        selectMonth.addActionListener(listener);
        selectYear.addActionListener(listener);

        // First interface, ask the user for data source (exist file or creating file)
        int res = JOptionPane.showOptionDialog(new JFrame(), "Would you like to load your data from exist file or" +
                        " create a new reminder calender?", "Reminder",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Load", "Create New"}, JOptionPane.YES_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            f = getFile();
            String result = "";
            try {
                result = readFromFile(f);
                mappingFromFile();
            }
            catch(IOException ex)
            {
                System.out.print("Error in Reading from File");
            }
        }
        else {
             fileName = (String)JOptionPane.showInputDialog("File Path + Name: ");
            if (fileName != null){
                try {
                    createFile(fileName);
                }
                catch(IOException ex)
                {
                    System.out.print("Error in creating the file");
                }
            }
        }
    }


    private class ButtonsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == cmdSubmit)
                submit();
            else if(e.getSource() == cmdRetrieval)
                retrieval();
            else if(e.getSource() == selectMonth){
                setDaysInMonth();
            }
        }
    }

    // Save the reminder with the chosen date
    public void submit(){
        int day = (int)selectDay.getSelectedItem();
        int month = (int)selectMonth.getSelectedItem();
        int year = (int)selectYear.getSelectedItem();
        MyDate dateToSave = new MyDate(day, month, year); // Creating date object to pass the hashcode
        dateReminder.put(dateToSave, txtData.getText()); // Coding and saving the data
        try {
            writeToFile(f);
        }
        catch(IOException ex)
        {
            System.out.print("Error in Writing to File");
        }
        txtData.setText(null); // Clear the box
    }

    // Extract the reminder from the hashmap and show the message
    public void retrieval(){
        int day = (int)selectDay.getSelectedItem();
        int month = (int)selectMonth.getSelectedItem();
        int year = (int)selectYear.getSelectedItem();
        MyDate dateToExtract = new MyDate(day, month, year);
        txtData.setText(dateReminder.get(dateToExtract));
    }

    // Set the right num of day for each month
    public void setDaysInMonth(){
        switch (selectMonth.getSelectedIndex() + 1){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                for(int i = 1; i <= 31; i++){
                    selectDay.addItem(i);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                for(int i = 1; i <= 30; i++){
                    selectDay.addItem(i);
                }
                break;
            case 2:
                for(int i = 1; i <=28; i++){
                    selectDay.addItem(i);
                }
                break;
        }
    }

    // FILE Methods
    public void createFile(String fileName) throws IOException
    {
        f = new File(fileName);
        f.createNewFile();
    }

    public void writeToFile(File f) throws IOException
    {
        int day = (int)selectDay.getSelectedItem();
        int month = (int)selectMonth.getSelectedItem();
        int year = (int)selectYear.getSelectedItem();
        MyDate dateToWrite = new MyDate(day, month, year);
        FileWriter fw = new FileWriter(f, true);
        fw.write(day + "/" + month + "/" + year); // First line be the date
        fw.write("\r\n");
        fw.write(dateReminder.get(dateToWrite)); // Second line be the value of Date HashCode
        fw.write("\r\n");
        fw.close();
    }

    public String readFromFile(File f) throws IOException
    {
        FileReader fr = new FileReader(f);
        int size = (int) f.length();
        readedData = new char[size];
        fr.read(readedData);
        return new String(readedData);
    }

    public File getFile()
    {
        JFileChooser fc = new JFileChooser(currentDir);
        fc.showOpenDialog(null);
        currentDir = fc.getSelectedFile();
        return fc.getSelectedFile();
    }

    // Organize the data and mapping the message with the correct date
    // works only for constant template - date separated by "/" and \r and \n in the end of every line
    public void mappingFromFile(){
        int i = 0;
        String tmp = "";
        int day, month, year;
        while(i != readedData.length){
                while (readedData[i] != '/'){
                    tmp += readedData[i];
                    i++;
                }
                day = Integer.parseInt(tmp);
                tmp = "";
                i++;

                while(readedData[i] != '/'){
                    tmp += readedData[i];
                    i++;
                }
                month = Integer.parseInt(tmp);
                tmp = "";
                i++;

                while(readedData[i] != '\r'){
                    tmp += readedData[i];
                    i++;
                }
                year = Integer.parseInt(tmp);
                tmp = "";
                i+=2;

                while(readedData[i] != '\n'){
                    tmp += readedData[i];
                    i++;
                }
                MyDate dateToMap = new MyDate(day, month, year);
                dateReminder.put(dateToMap, tmp);
                tmp = "";
                i++;
        }
    }

}
