
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;
public class Quiz implements Serializable {
    private String name;
    private String quizTopic;
    private String difficultyLevel;
    private Creator creator;
    private transient Scanner scanner; // Transient as Scanner is not serializable
    private ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Quiz(String name, String quizTopic, String difficultyLevel) {
        this.name = name;
        this.quizTopic = quizTopic;
        this.difficultyLevel = difficultyLevel;
    }
    public Quiz() {
        this.scanner = new Scanner(System.in);
        this.questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizTopic() {
        return quizTopic;
    }

    public void setQuizTopic(String quizTopic) {
        this.quizTopic = quizTopic;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }


}
