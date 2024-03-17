import java.io.Serializable;
public class TrueOrFalseQuestions extends Question implements Serializable
{
    private boolean correctAnswer;

    public TrueOrFalseQuestions(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
