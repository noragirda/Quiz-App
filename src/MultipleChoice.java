import java.io.Serializable;
public class MultipleChoice extends Question implements Serializable
{
    private String[] options;
    private int correctAnswerIndex; // Index of the correct answer in the options array

    public String[] getOptions()
    {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public MultipleChoice(String questionText, String[] options, int correctAnswerIndex) {
        super(questionText);
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
