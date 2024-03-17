import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class QuizSession
{
    Player[] playerSet;
    Quiz currentQuiz;
    public QuizSession(Player[] playerSet, Quiz currentQuiz)
    {
        this.playerSet = playerSet;
        this.currentQuiz = currentQuiz;
    }
    public void addImageLabel(JPanel mainPanel,int newWidth, int newHeight)
    {
        try {
            String imagePath = "foxie.png";
            Image originalImage = ImageIO.read(new File(imagePath));
            //int newWidth = 200;
            //int newHeight = 150;
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            mainPanel.add(imageLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addImageLabel1(JPanel mainPanel,int newWidth, int newHeight)
    {
        try {
            String imagePath = "foxie.png";
            Image originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            mainPanel.add(imageLabel, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playQuizGUI()//til here I have the chosen quiz, the player set initiated, player 0 is me
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);
        addImageLabel1(mainPanel, 200, 150);
        //title
        Color titleColor = new Color(255, 179, 255);
        JLabel titleLabel = new JLabel("You are almost set to start!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(titleColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        //message text area now
        JTextArea messageTextArea = new JTextArea("Hurray! Almost there!\n" +
                "Now all you need to do is decide if you will play by yourself\n" +
                "or you want to have some friend join you!\n" +
                "Anyways, it's more about knowledge than getting the right answers!");
        messageTextArea.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);
        messageTextArea.setBackground(new Color(255, 179, 255));
        messageTextArea.setForeground(Color.WHITE);
        Dimension textAreaSize = messageTextArea.getPreferredSize();
        messageTextArea.setPreferredSize(new Dimension(textAreaSize.width, textAreaSize.height));
        mainPanel.add(messageTextArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198)); // Same background as mainPanel
        Color buttonColor = new Color(204, 153, 255);

        RoundedButton singlePlayerButton = new RoundedButton("One Man Show!", 30);
        singlePlayerButton.setBackground(buttonColor);
        singlePlayerButton.setForeground(Color.WHITE);
        singlePlayerButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        RoundedButton multiPlayerButton = new RoundedButton("I'll have some company!", 30); // Replace with your desired text
        multiPlayerButton.setBackground(buttonColor);
        multiPlayerButton.setForeground(Color.WHITE);
        multiPlayerButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                oneManShowDisplay();
                frame.dispose();
            }
        });

        multiPlayerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                choseMultiPlayer();
                frame.dispose();
            }
        });

        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(multiPlayerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void oneManShowDisplay()
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);
        JTextArea messageTextArea = new JTextArea("Hurray! Almost there!\n" +
                "I am pleased to see how keen you are to test your knowledge!\n" +
                "\n" +
                "\n" +
                "Press the button when you are ready to start! Have fun! ");
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
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198)); // Same background as mainPanel

        RoundedButton startGameButton = new RoundedButton("Press to take the quiz!", 30);
        startGameButton.setBackground(buttonColor);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playThisQuizMultiple( 1,0, playerSet,currentQuiz);
                frame.dispose();
            }
        });
        buttonPanel.add(startGameButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void choseMultiPlayer()//here I need to play the quiz for all player sets
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900); // Adjust the size as needed

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JTextArea messageTextArea = new JTextArea("Now that some fellow learners have joined you\n" +
                "you need to know how the game works when there is more than one player.\n" +
                "You will start by adding the players into the system.\n" +
                "When you are ready there will be a button that will take you to the quiz taking window."+
                "You will play on the same device. The name of the player whose turn is will be on the top.\n"+
                "After he finished the test, a new name will appear and so on, playing one by one.\n"+
                "At the end, a leader board will be displayed\n"+
                "As I said before, this is about making learning more enjoyable!\n"+
                "\n"+
                "\n"+
                "Now you need to input the name and age of your friends!\n");
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198)); // Same background as mainPanel

        RoundedButton addPlayerButton = new RoundedButton("Press to add players!", 30);
        addPlayerButton.setBackground(buttonColor);
        addPlayerButton.setForeground(Color.WHITE);
        addPlayerButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerLogInGUI();
                frame.dispose();
            }
        });

        buttonPanel.add(addPlayerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
    public JFrame createMainFrame() {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);
        return frame;
    }

    public JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Color mintGreen = new Color(145, 255, 164);
        Color teaGreen = new Color(191, 255, 198);
        mainPanel.setBackground(teaGreen);
        return mainPanel;
    }
    public void playerLogInGUI()
    {
        JFrame frame = createMainFrame();
        JPanel mainPanel = createMainPanel();
        addImageLabel(mainPanel, 200, 150);

        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Integer> playerAges = new ArrayList<>();

        JLabel nameLabel = new JLabel("Fellow learner, what is your name?");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        Color textColor = new Color(255, 179, 255);
        nameLabel.setForeground(textColor); // Set text color

        JTextField nameTextField = new JTextField(5);
        nameTextField.setPreferredSize(new Dimension(50, 30));
        Color fieldColor = new Color(204, 153, 255);
        nameTextField.setBackground(fieldColor);
        nameTextField.setFont(new Font("Arial", Font.CENTER_BASELINE, 26));
        nameTextField.setForeground(Color.WHITE);
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);

        JLabel ageLabel = new JLabel("What is your age? (You should be at least 16 years old)");
        ageLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 26));
        ageLabel.setForeground(textColor); // Set text color

        JTextField ageTextField = new JTextField(5);
        ageTextField.setPreferredSize(new Dimension(50, 30));
        ageTextField.setBackground(fieldColor);
        ageTextField.setFont(new Font("Arial", Font.CENTER_BASELINE, 26));
        ageTextField.setForeground(Color.WHITE);
        mainPanel.add(ageLabel);
        mainPanel.add(ageTextField);
        Color buttonColor = new Color(204, 153, 255);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198));

        RoundedButton addMoreButton = new RoundedButton("Submit + add more!", 30);
        addMoreButton.setBackground(buttonColor);
        addMoreButton.setForeground(Color.WHITE);
        addMoreButton.setFont(new Font("Arial", Font.BOLD, 22));

        RoundedButton doneButton = new RoundedButton("Done!", 30);
        doneButton.setBackground(buttonColor);
        doneButton.setForeground(Color.WHITE);
        doneButton.setFont(new Font("Arial", Font.BOLD, 22));

        addMoreButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = nameTextField.getText();
                String ageText = ageTextField.getText();
                try
                {
                    int age = Integer.parseInt(ageText);
                    if (age >= 16 && age <= 120)
                    {
                        playerNames.add(name);
                        playerAges.add(age);
                        nameTextField.setText("");
                        ageTextField.setText("");

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Age must be between 16 and 120.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid age.");
                }

            }
        });

        doneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)

            {
                for(int i=0;i<playerNames.size();i++)
                {
                    if(playerSet!=null)
                    {
                        playerSet[i+1].setAge(playerAges.get(i));//i+1 because I am playerSet[0]
                        playerSet[i+1].setName(playerNames.get(i));
                    }
                }

                frame.dispose();
                playQuizMul(playerNames.size()+1, 0, playerSet, currentQuiz);

            }

        });

        buttonPanel.add(addMoreButton);
        buttonPanel.add(doneButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
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
        gbc.gridy++;
        panel.add(label, gbc);
    }
    public void playQuizMul(int nrPlayers,int ind, Player[] playerSet, Quiz quiz)
    {
        playThisQuizMultiple( nrPlayers,ind, playerSet,quiz);
    }
    public void playThisQuizMultiple(int nrPlayers, int ind, Player[] playerSet, Quiz quiz)
    {
        if(nrPlayers>1) {
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

            addDetailLabel(detailPanel, "It is " + playerSet[ind].getName() + "'s time to shine!", gbc);
            addDetailLabel(detailPanel, "You will be playing this quiz:", gbc);
            addDetailLabel(detailPanel, "Quiz Name: " + quiz.getName(), gbc);
            addDetailLabel(detailPanel, "Quiz Topic: " + quiz.getQuizTopic(), gbc);
            addDetailLabel(detailPanel, "Difficulty Level: " + quiz.getDifficultyLevel(), gbc);

            mainPanel.add(detailPanel, BorderLayout.CENTER);


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.setBackground(new Color(191, 255, 198));
            Color buttonColor = new Color(204, 153, 255);

            RoundedButton takeButton = new RoundedButton("Take quiz!", 30);
            takeButton.setBackground(buttonColor);
            takeButton.setForeground(Color.WHITE);
            takeButton.setFont(new Font("Arial", Font.BOLD, 22));
            takeButton.setEnabled(true);
            takeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    takeButton.setEnabled(false);
                    ArrayList<Question> questions = quiz.getQuestions();
                    playQuestionMul(questions, 0, ind, playerSet, quiz, nrPlayers);
                }
            });
            RoundedButton nextButton = new RoundedButton("Next Player's Turn!", 30);
            nextButton.setBackground(buttonColor);
            nextButton.setForeground(Color.WHITE);
            nextButton.setFont(new Font("Arial", Font.BOLD, 22));
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ind + 1 < nrPlayers) {
                        playQuizMul(nrPlayers, ind + 1, playerSet, quiz);
                    } else {
                        showLeaderBoard(nrPlayers, playerSet, quiz);
                    }
                    frame.dispose();
                }
            });
            buttonPanel.add(nextButton);
            buttonPanel.add(takeButton);


            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            frame.add(mainPanel);
            frame.setVisible(true);
        }
        else
        {
            JFrame frame = new JFrame("Quiz Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 900); // Adjust the size as needed

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(191, 255, 198));
            addImageLabel(mainPanel, 200, 150);

            JPanel detailPanel = new JPanel(new GridBagLayout());
            detailPanel.setBackground(new Color(191, 255, 198));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            addDetailLabel(detailPanel, "It is " + playerSet[0].getName() + "'s time to shine!", gbc);
            addDetailLabel(detailPanel, "You will be playing this quiz:", gbc);
            addDetailLabel(detailPanel, "Quiz Name: " + quiz.getName(), gbc);
            addDetailLabel(detailPanel, "Quiz Topic: " + quiz.getQuizTopic(), gbc);
            addDetailLabel(detailPanel, "Difficulty Level: " + quiz.getDifficultyLevel(), gbc);

            mainPanel.add(detailPanel, BorderLayout.CENTER);


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.setBackground(new Color(191, 255, 198));
            Color buttonColor = new Color(204, 153, 255);

            RoundedButton takeButton = new RoundedButton("Take quiz!", 30);
            takeButton.setBackground(buttonColor);
            takeButton.setForeground(Color.WHITE);
            takeButton.setFont(new Font("Arial", Font.BOLD, 22));
            takeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Question> questions = quiz.getQuestions();
                    playQuestionMul(questions, 0, 0, playerSet, quiz, nrPlayers);
                    frame.dispose();

                }
            });

            buttonPanel.add(takeButton);

            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            frame.add(mainPanel);
            frame.setVisible(true);
        }
    }
    public void showLeaderBoard(int nrPlayers, Player[] playerSet, Quiz quiz)
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
        addDetailLabel(detailPanel, "Hurray! Quiz completed! Good job to ypu all! ", gbc);
        addDetailLabel(detailPanel, "The Leaderboard is: ", gbc);
        addDetailLabel(detailPanel, "\n", gbc);
        Player mainPlayer=playerSet[0];
        Arrays.sort(playerSet, 0, nrPlayers , Comparator.comparingInt(Player::getScore).reversed());
        for(int i=0;i<nrPlayers;i++)
            addDetailLabel(detailPanel, (i+1) + ". " + playerSet[i].getName() + " - Score: " + playerSet[i].getScore(), gbc);

        addDetailLabel(detailPanel, "\n" , gbc);
        addDetailLabel(detailPanel, "Now you can either exit the app or play another quiz!" , gbc);
        mainPanel.add(detailPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198));
        Color buttonColor = new Color(204, 153, 255);

        RoundedButton playButton = new RoundedButton("Play!", 30);
        playButton.setBackground(buttonColor);
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 22));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPlayer.resetScore();
                mainPlayer.choosingQuiz();

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

                System.exit(0);
            }
        });
        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    private void playQuestionMul(ArrayList<Question> questions, int questionIndex, int ind, Player[] playerSet, Quiz quiz, int nrPlayers) {
        if (questionIndex < questions.size())
        {
            Question question = questions.get(questionIndex);
            if (question instanceof MultipleChoice)
            {
                displayQuestionGUIMCMul((MultipleChoice) question, questions, questionIndex, ind, playerSet, quiz, nrPlayers);
            }
            if (question instanceof TrueOrFalseQuestions)
            {
                displayQuestionGUITFMul((TrueOrFalseQuestions) question, questions, questionIndex, ind, playerSet, quiz, nrPlayers);
            }
        }
        else
        {
            if(nrPlayers==1)
                singlePlayerFinalDisplay();
        }
    }
    public void displayQuestionGUIMCMul(MultipleChoice question, ArrayList<Question> questions, int questionIndex, int ind, Player[] playerSet, Quiz quiz, int nrPlayers)
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JTextArea questionTextArea = new JTextArea(question.getQuestionText());
        questionTextArea.setFont(new Font("Arial", Font.PLAIN, 30));
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setLineWrap(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(new Color(204, 153, 255));
        questionTextArea.setForeground(Color.WHITE);
        questionTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(questionTextArea, BorderLayout.NORTH);

        String[] options = question.getOptions();
        JPanel optionsPanel = new JPanel(new GridLayout(options.length, 1));
        optionsPanel.setBackground(new Color(191, 255, 198));

        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i = 0; i < options.length; i++) {
            JRadioButton radioButton = new JRadioButton(options[i]);
            radioButton.setFont(new Font("Arial", Font.PLAIN, 30));
            radioButton.setForeground(new Color(204, 153, 255)); // Set text color
            radioButton.setBackground(new Color(191, 255, 198));
            radioButton.setActionCommand(Integer.toString(i));
            buttonGroup.add(radioButton);
            optionsPanel.add(radioButton);
        }

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        Color buttonColor = new Color(204, 153, 255);
        RoundedButton submitButton = new RoundedButton("Submit", 30);
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedOption = buttonGroup.getSelection().getActionCommand();
                int selectedOptionIndex = Integer.parseInt(selectedOption);

                if (selectedOptionIndex == (question.getCorrectAnswerIndex()))
                {
                    JOptionPane.showMessageDialog(null, "Correct!");
                    playerSet[ind].updateScore();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is: " + options[(question.getCorrectAnswerIndex())]);
                }
                playQuestionMul(questions, questionIndex + 1, ind, playerSet, quiz , nrPlayers);
                frame.dispose();
            }
        });
        frame.add(mainPanel);
        frame.setVisible(true);

    }
    public void displayQuestionGUITFMul(TrueOrFalseQuestions question, ArrayList<Question> questions, int questionIndex,int  ind, Player[] playerSet, Quiz quiz, int nrPlayers)
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        JTextArea questionTextArea = new JTextArea(question.getQuestionText());
        questionTextArea.setLayout(new BoxLayout(questionTextArea, BoxLayout.Y_AXIS));
        questionTextArea.setFont(new Font("Arial", Font.PLAIN, 30));
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setLineWrap(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(new Color(204, 153, 255));
        questionTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTextArea.setForeground(Color.WHITE);

        mainPanel.add(questionTextArea, BorderLayout.CENTER);

        Color buttonColor = new Color(204, 153, 255);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198));

        RoundedButton trueButton = new RoundedButton("True!", 30);
        trueButton.setBackground(buttonColor);
        trueButton.setForeground(Color.WHITE);
        trueButton.setFont(new Font("Arial", Font.BOLD, 22));
        RoundedButton falseButton = new RoundedButton("False!", 30);
        falseButton.setBackground(buttonColor);
        falseButton.setForeground(Color.WHITE);
        falseButton.setFont(new Font("Arial", Font.BOLD, 22)); // Set font

        trueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean ans=true;
                if(ans==question.isCorrectAnswer())
                {
                    JOptionPane.showMessageDialog(null, "Correct!");
                    playerSet[ind].updateScore();
                }
                else
                    JOptionPane.showMessageDialog(null, "Incorrect. The statement is actually false!");
                playQuestionMul(questions, questionIndex + 1, ind, playerSet, quiz, nrPlayers );
                frame.dispose();
            }

        });

        falseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean ans=false;
                if(ans==question.isCorrectAnswer())
                {
                    JOptionPane.showMessageDialog(null, "Correct!");
                    playerSet[ind].updateScore();
                }
                else
                    JOptionPane.showMessageDialog(null, "Incorrect. The statement is actually true!");
                // Close the current question window
                playQuestionMul(questions, questionIndex + 1, ind, playerSet, quiz, nrPlayers );
                frame.dispose();
            }
        });

        buttonPanel.add(trueButton);
        buttonPanel.add(falseButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void singlePlayerFinalDisplay()
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        addImageLabel(mainPanel, 200, 150);

        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBackground(new Color(191, 255, 198));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        addDetailLabel(detailPanel, "Hurray! Quiz completed! ", gbc);
        addDetailLabel(detailPanel, "Your score is: " + playerSet[0].getScore(), gbc);
        addDetailLabel(detailPanel, "No matter the score, what you didn't know and now you do is what matters" , gbc);
        addDetailLabel(detailPanel, "\n" , gbc);
        addDetailLabel(detailPanel, "Now you can either exit the app or play another quiz!" , gbc);
        addDetailLabel(detailPanel, "Maybe this time some friend will join you!", gbc);
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
                playerSet[0].resetScore();
                playerSet[0].choosingQuiz();
                frame.dispose();
            }
        });
        RoundedButton exitButton = new RoundedButton("Exit", 30);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);


    }
}
