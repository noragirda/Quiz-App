import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private List<Quiz> quizzes;
    private static QuizManager instance;
    private static final String QUIZ_FILE = "quizzesFin.ser";
    public static synchronized QuizManager getInstance() {
        if (instance == null) {
            instance = new QuizManager();
        }
        return instance;
    }
    public QuizManager() {
        this.quizzes = new ArrayList<>();
        loadQuizzes();
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        saveQuizzes();
    }

    public void saveQuizzes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(QUIZ_FILE))) {
            oos.writeObject(quizzes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuizzes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(QUIZ_FILE))) {
            quizzes = (List<Quiz>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            quizzes = new ArrayList<>(); // If no quizzes are found or an error occurs
        }
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
            mainPanel.add(imageLabel, BorderLayout.SOUTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displayQuizzesGUI(Player player)
    {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(191, 255, 198));
        addImageLabel(mainPanel, 200, 150);

        Color titleColor = new Color(255, 179, 255);
        JLabel titleLabel = new JLabel("Available Quizzes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(titleColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel quizListPanel = new JPanel();
        quizListPanel.setLayout(new BoxLayout(quizListPanel, BoxLayout.Y_AXIS));
        quizListPanel.setBackground(titleColor);

        JComboBox<String> quizList = placingQuizzezInList();
        quizList.setMaximumSize(new Dimension(200, 30));
        quizList.setAlignmentX(Component.CENTER_ALIGNMENT);

        quizList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // getting the selected item (quiz name) from the JComboBox
                int selectedQuizIndex = quizList.getSelectedIndex();
                if(selectedQuizIndex!=-1)
                {
                    //this gives me the index of the selected quiz
                    Quiz selectedQuiz=quizzes.get(selectedQuizIndex);
                    player.createSession(selectedQuiz);
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frame, "No quiz selected or quiz list is empty.");
                //JOptionPane.showMessageDialog(frame, "All good!");
            }
        });
        Color buttonColor = new Color(204, 153, 255);
        RoundedButton startButton = new RoundedButton("Choose Quiz", 30);
        startButton.setBackground(buttonColor);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 26));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        quizListPanel.add(Box.createVerticalStrut(20));
        quizListPanel.add(quizList);
        quizListPanel.add(Box.createVerticalStrut(20));
        quizListPanel.add(startButton);

        mainPanel.add(quizListPanel, BorderLayout.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public JComboBox<String> placingQuizzezInList() {
        JComboBox<String> quizList = new JComboBox<>();
        for (int i = 0; i < quizzes.size(); i++) {
            quizList.addItem(quizzes.get(i).getName());
        }
        return quizList;
    }

}
