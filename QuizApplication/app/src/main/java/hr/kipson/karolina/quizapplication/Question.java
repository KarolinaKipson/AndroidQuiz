package hr.kipson.karolina.quizapplication;

import android.os.Parcel;
import android.os.Parcelable;
//A Parcelable is the Android implementation of the Java Serializable
//Parcelable makes it possible to send objects between classes or in instance state bundles
//custom object to be parsed to another component they need to implement the android.os.Parcelable interface.
// It must also provide a static final method called CREATOR	which must implement the Parcelable.Creator interface.
//Important! When using Parcelable ArrayList must be implemented as ArrayList and not with List interface!
public class Question implements Parcelable {
    private String question;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;
    private int correctAnswer;
    private String explanation;

    public Question() {
    }

    public Question(String question, String firstAnswer, String secondAnswer, String thirdAnswer, int correctAnswer, String explanation) {
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    protected Question(Parcel in) {
        question = in.readString();
        firstAnswer = in.readString();
        secondAnswer = in.readString();
        thirdAnswer = in.readString();
        correctAnswer = in.readInt();
        explanation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(firstAnswer);
        dest.writeString(secondAnswer);
        dest.writeString(thirdAnswer);
        dest.writeInt(correctAnswer);
        dest.writeString(explanation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
