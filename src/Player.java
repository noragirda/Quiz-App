import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Player extends User
{
    private QuizManager quizManager;
    private int score=0;
    public Player(String name, int age, QuizManager quizManager) {
        super(name, age);
        this.quizManager = quizManager;
    }
    public Player()
    {
        this.quizManager=new QuizManager();
    }
    public void updateScore()
    {
        this.score++;
    }
    public void resetScore(){this.score=0;}
    public int getScore()
    {
        return this.score;
    }
    public void choosingQuiz()
    {
        quizManager.displayQuizzesGUI(this);//this one dosplays the quizzes and chooses the quiz to play
    }
    public void createSession(Quiz quiz)
    {
        //initializing the player set
        Player[] playerSet=new Player[30];
        for(int i=1;i<30;i++)
        {
            playerSet[i]=new Player();
        }
        playerSet[0]=this;
        QuizSession session=new QuizSession(playerSet, quiz);//Creating the session, main player is me
        session.playQuizGUI();
    }



}
