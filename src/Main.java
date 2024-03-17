import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Main
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Color mintGreen = new Color(145, 255, 164);
        Color teaGreen = new Color(191, 255, 198);
        mainPanel.setBackground(teaGreen);

        try {
            String imagePath = "foxie.png";
            Image originalImage = ImageIO.read(new File(imagePath));
            int newWidth = 200;
            int newHeight = 150;
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(imageLabel);


            JLabel titleLabel = new JLabel("Test Yourself While Having Fun");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            Color titleColor = new Color(255, 179, 255);
            titleLabel.setForeground(titleColor);

            mainPanel.add(Box.createVerticalStrut(20));
            mainPanel.add(titleLabel);

            Color buttonColor = new Color(204, 153, 255);
            RoundedButton readyButton = new RoundedButton("Press if you are ready!", 30); // Replace with your desired text
            readyButton.setBackground(buttonColor);
            readyButton.setForeground(Color.WHITE);
            readyButton.setFont(new Font("Arial", Font.BOLD, 22));

            readyButton.setPreferredSize(new Dimension(200, 50)); // Adjust the width and height as needed
            readyButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the button horizontally

            readyButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(frame, "Quiz is starting!");
                    User user=new User();
                    user.logInAndChooseUser();
                    frame.dispose();
                }
            });
            mainPanel.add(Box.createVerticalStrut(20));
            mainPanel.add(readyButton);
            frame.getContentPane().add(mainPanel);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}
