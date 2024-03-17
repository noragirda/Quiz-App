import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Creator extends User
{
    public Creator(String name, int age)
    {
        super(name, age);
    }
    public void createNewQuiz()
    {
        Quiz quiz = new Quiz();
        beforeCreatingQuiz(quiz);
        //System.out.println("Quiz created successfully!");

    }

    public void beforeCreatingQuiz(Quiz quiz)
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900); // Adjust the size as needed

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JTextArea messageTextArea = new JTextArea("Hurray! You are now creating a quiz!\n" +
                "I am pleased to see you want to help others learn by creating new quizzes!\n" +
                "Keep in mind that all these quizzes created here will be taken by others!\n" +
                "Choose an interesting topic and a suggestive name for the quiz you are creating!\n" +
                "Every time you want to add a new question, press \"Add Questions\" button\n" +
                "\n" +
                "\n" +
                "Once you started creating a quiz, there is no going back!HAVE FUNNN!");
        messageTextArea.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);
        messageTextArea.setBackground(new Color(255, 179, 255));
        messageTextArea.setForeground(Color.WHITE);
        Dimension textAreaSize = messageTextArea.getPreferredSize();
        messageTextArea.setPreferredSize(new Dimension(textAreaSize.width, textAreaSize.height));
        mainPanel.add(messageTextArea, BorderLayout.CENTER);

        Color buttonColor = new Color(204, 153, 255);
        RoundedButton startCreatingButton = new RoundedButton("Start Creating", 30);
        startCreatingButton.setBackground(buttonColor);
        startCreatingButton.setForeground(Color.WHITE);
        startCreatingButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        // Adding button to the south of BorderLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(startCreatingButton);
        buttonPanel.setBackground(new Color(191, 255, 198));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        startCreatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createQuizGUI(quiz);
                frame.dispose();
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void createQuizGUI(Quiz quiz)
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(191, 255, 198));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Quiz Name:");
        JTextField nameField = new JTextField(20);
        JLabel topicLabel = new JLabel("Quiz Topic:");
        JTextField topicField = new JTextField(20);
        JLabel difficultyLabel = new JLabel("Difficulty Level:");
        String[] difficultyLevels = {"Easy", "Medium", "Difficult"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficultyLevels);

        formPanel.add(nameLabel, gbc);
        formPanel.add(nameField, gbc);
        formPanel.add(topicLabel, gbc);
        formPanel.add(topicField, gbc);
        formPanel.add(difficultyLabel, gbc);
        formPanel.add(difficultyComboBox, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        Color buttonColor = new Color(204, 153, 255);
        RoundedButton addQuestionButton = new RoundedButton("Add Questions", 30);
        addQuestionButton.setBackground(buttonColor);
        addQuestionButton.setForeground(Color.WHITE);
        addQuestionButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        // Adding button to the south of BorderLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addQuestionButton);
        buttonPanel.setBackground(new Color(191, 255, 198));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = nameField.getText();
                String topic = topicField.getText();
                String difficulty = (String) difficultyComboBox.getSelectedItem();

                if (name.isEmpty() || topic.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else
                {
                    quiz.setName(name);
                    quiz.setQuizTopic(topic);
                    quiz.setDifficultyLevel(difficulty);
                    addQuestion(quiz);
                }
                //here I need to add questions

            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void addQuestion(Quiz quiz) {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);
        Color buttonColor = new Color(204, 153, 255);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198));

        RoundedButton addButton = new RoundedButton("Add Question", 30);
        addButton.setBackground(buttonColor);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 22));

        RoundedButton doneButton = new RoundedButton("Done", 30);
        doneButton.setBackground(buttonColor);
        doneButton.setForeground(Color.WHITE);
        doneButton.setFont(new Font("Arial", Font.BOLD, 22));
        ArrayList<Question> questions= new ArrayList<>();
        // If player button pressed then this
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] questionTypes = {"Multiple Choice", "True/False"};
                String selectedQuestionType = (String) JOptionPane.showInputDialog(
                        frame, "Choose the question type:", "Add Question",
                        JOptionPane.QUESTION_MESSAGE, null, questionTypes, questionTypes[0]);

                if (selectedQuestionType != null)
                {
                    if (selectedQuestionType.equals("Multiple Choice"))
                    {
                        // Show a dialog to input multiple-choice question and options
                        String questionText = JOptionPane.showInputDialog(frame, "Enter the question text:");
                        if (questionText != null && !questionText.isEmpty())
                        {
                            String[] options = new String[4];
                            for (int i = 0; i < 4; i++) {
                                String option = JOptionPane.showInputDialog(frame, "Enter option " + (i + 1) + ":");
                                if (option == null) {
                                    break;
                                }
                                options[i] = option;
                            }
                            if (options[0] != null)
                            {
                                int correctIndex = Integer.parseInt(JOptionPane.showInputDialog(
                                        frame, "Enter the index of the correct answer (1-4):")) - 1;
                                questions.add(new MultipleChoice(questionText, options, correctIndex));
                                JOptionPane.showMessageDialog(frame, "Multiple-choice question added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    else
                    if (selectedQuestionType.equals("True/False"))
                    {
                        // Show a dialog to input true/false question
                        String questionText = JOptionPane.showInputDialog(frame, "Enter the True/False question text:");
                        if (questionText != null && !questionText.isEmpty()) {
                            int option = JOptionPane.showOptionDialog(frame,
                                    "Is the statement true or false?",
                                    "True/False",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    new String[]{"True", "False"},
                                    "True");

                            if (option != JOptionPane.CLOSED_OPTION)
                            {
                                boolean correctAnswer = (option == JOptionPane.YES_OPTION);

                                questions.add(new TrueOrFalseQuestions(questionText, correctAnswer));
                                JOptionPane.showMessageDialog(frame, "True/False question added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //succesfully created your quiz, it gets saved in memory. Now you can play.
                quiz.setQuestions(questions);
                QuizManager quizManager = QuizManager.getInstance();
                quizManager.addQuiz(quiz);
                afterFinishingTheCreationOfTheQuiz();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(doneButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);



        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void lableInitializer(JLabel detailLable)
    {
        detailLable.setFont(new Font("Arial", Font.BOLD, 26));
        detailLable.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color detailColor = new Color(255, 179, 255);
        detailLable.setForeground(detailColor);
    }
    private void addDetailLabel(JPanel panel, String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        lableInitializer(label);
        gbc.gridy++; // Increment the gridy to move to the next row
        panel.add(label, gbc);
    }
    public void afterFinishingTheCreationOfTheQuiz()
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBackground(new Color(191, 255, 198));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        addDetailLabel(detailPanel, "Hurray! You have finished creating the quiz!", gbc);
        addDetailLabel(detailPanel, "Nice of you to challange other users' knowledge!" , gbc);
        addDetailLabel(detailPanel, "Now it is time for you to challange your friends to hop on this QUIZZING experience!" , gbc);



        addDetailLabel(detailPanel, "\n" , gbc);
        addDetailLabel(detailPanel, "Now you can either exit the app or create another quiz!" , gbc);
        mainPanel.add(detailPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198)); // Same background as mainPanel
        Color buttonColor = new Color(204, 153, 255);

        RoundedButton playButton = new RoundedButton("Play!", 30);
        playButton.setBackground(buttonColor);
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 22));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                QuizManager quizManager=QuizManager.getInstance();
                Player player=new Player(Creator.super.getName(), Creator.super.getAge(), quizManager);
                player.choosingQuiz();

            }
        });
        RoundedButton createButton = new RoundedButton("Create!", 30);
        createButton.setBackground(buttonColor);
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 22));
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                Quiz quiz=new Quiz();
                beforeCreatingQuiz(quiz);
            }
        });
        RoundedButton exitButton = new RoundedButton("Exit!", 30);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                //JOptionPane.showMessageDialog(null, "I need to display score");
                System.exit(0);
            }
        });
        buttonPanel.add(playButton);
        buttonPanel.add(createButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

