import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class User
{
    private String name;
    private int age;
    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    public User()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void logInAndChooseUser()
    {
        initializeGUI();
    }
    public void initializeGUI() {
        JFrame frame = createMainFrame();
        JPanel mainPanel = createMainPanel();
        mainPanel.setLayout(new BorderLayout());
        addImageLabel(mainPanel, 200, 150);

        addMessageLabels(mainPanel);

        Color buttonColor = new Color(204, 153, 255);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198)); // Same background as mainPanel

        RoundedButton logButton = new RoundedButton("Go ahead and log in!", 30);
        logButton.setBackground(buttonColor);
        logButton.setForeground(Color.WHITE);
        logButton.setFont(new Font("Arial", Font.BOLD, 22));

        buttonPanel.add(logButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        logButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                logInGUI();
                frame.dispose();
            }
        });
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

    public void addImageLabel(JPanel mainPanel,int newWidth, int newHeight)
    {
        try {
            String imagePath = "foxie.png";
            Image originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(imageLabel, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addMessageLabels(JPanel mainPanel)
    {
        JPanel textPanel=new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(191, 255, 198));

        JLabel messageLabel1 = new JLabel("Hello there! Nice to see you are eager to learn something new! This will be fun!");
        JLabel messageLabel2 = new JLabel("Keep in mind that your login info will not be retained, so there will be no record of quizzes you took");
        JLabel messageLabel3 = new JLabel("or scores that you had before. So no worries, this app is more about learning than winning!");
        JLabel messageLabel4 = new JLabel("This is a one-time use app!");

        messageLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel4.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Set font and size
        Font font = new Font("Arial", Font.CENTER_BASELINE, 22);
        messageLabel1.setFont(font);
        messageLabel2.setFont(font);
        messageLabel3.setFont(font);
        messageLabel4.setFont(font);

        Color messageColor = new Color(255, 179, 255);
        messageLabel1.setForeground(messageColor);
        messageLabel2.setForeground(messageColor);
        messageLabel3.setForeground(messageColor);
        messageLabel4.setForeground(messageColor);

        textPanel.add(messageLabel1);
        textPanel.add(messageLabel2);
        textPanel.add(messageLabel3);
        textPanel.add(messageLabel4);

        mainPanel.add(textPanel, BorderLayout.WEST);

    }
    public void logInGUI()
    {
        JFrame frame = createMainFrame();
        JPanel mainPanel = createMainPanel();
        addImageLabel(mainPanel, 200, 150);

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
        ageLabel.setForeground(textColor);

        JTextField ageTextField = new JTextField(5);
        ageTextField.setPreferredSize(new Dimension(50, 30));
        ageTextField.setBackground(fieldColor);
        ageTextField.setFont(new Font("Arial", Font.CENTER_BASELINE, 26));
        ageTextField.setForeground(Color.WHITE);
        mainPanel.add(ageLabel);
        mainPanel.add(ageTextField);

        Color buttonColor = new Color(204, 153, 255);
        RoundedButton submitButton = new RoundedButton("Submit", 30);
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 22));

        mainPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener()
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
                        createOrPlay(name, age);
                        frame.dispose();
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

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);

    }
    public void createOrPlay(String name, int age)
    {
        JFrame frame = createMainFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));

        addImageLabel(mainPanel, 200, 150);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(191, 255, 198));

        JLabel messageLabel1 = new JLabel("Now my fellow learner, it's up to you if you will take a quiz that is already available ");
        JLabel messageLabel2 = new JLabel("or create one that you can play later");
        messageLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        Font font = new Font("Arial", Font.CENTER_BASELINE, 22);
        messageLabel1.setFont(font);
        messageLabel2.setFont(font);
        Color messageColor = new Color(255, 179, 255);
        messageLabel1.setForeground(messageColor);
        messageLabel2.setForeground(messageColor);
        textPanel.add(messageLabel1);
        textPanel.add(messageLabel2);

        mainPanel.add(textPanel, BorderLayout.CENTER);
        Color buttonColor = new Color(204, 153, 255);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(191, 255, 198));

        RoundedButton playerButton = new RoundedButton("I want to play!", 30);
        playerButton.setBackground(buttonColor);
        playerButton.setForeground(Color.WHITE);
        playerButton.setFont(new Font("Arial", Font.BOLD, 22));

        RoundedButton creatorButton = new RoundedButton("I want to create!", 30);
        creatorButton.setBackground(buttonColor);
        creatorButton.setForeground(Color.WHITE);
        creatorButton.setFont(new Font("Arial", Font.BOLD, 22));

        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                QuizManager quizManager=QuizManager.getInstance();
                Player player=new Player(name, age, quizManager);
                player.choosingQuiz();
                frame.dispose();
            }
        });

        creatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Creator creator=new Creator(name, age);
                Quiz quiz=new Quiz();
                creator.beforeCreatingQuiz(quiz);
                frame.dispose();
            }
        });

        buttonPanel.add(playerButton);
        buttonPanel.add(creatorButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }


}
