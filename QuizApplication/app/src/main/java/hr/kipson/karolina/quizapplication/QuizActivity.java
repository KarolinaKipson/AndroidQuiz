package hr.kipson.karolina.quizapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizActivity extends AppCompatActivity {
    public static final String HIGH_SCORE = "highScore";
    private static final String KEY_SCORE ="keyScore";
    private static final String KEY_QUESTION_NUMBER ="keyQuestionNumber";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private static final String KEY_ANSWERED = "keyAnswered";

    private TextView tvQuestionNumber;
    private TextView tvQuestionScore;
    private TextView tvQuestion;
    private RadioGroup rgAnswers;
    private RadioButton rbAnswer1;
    private RadioButton rbAnswer2;
    private RadioButton rbAnswer3;
    private Button btnConfirmNext;
    private ColorStateList defaultTextColorRb;

    private ArrayList<Question> mQuestionList;
    private int mQuestionCount;
    private int mQuestionTotal;
    private Question mCurrentQuestion;

    private int mScore;
    private boolean mAnswered;

    private long timeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();
        //default text color saved in the variable
        defaultTextColorRb = rbAnswer1.getTextColors();
        if(savedInstanceState == null) {
            //db class with questions
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            mQuestionList = dbHelper.getAllQuestions();
            mQuestionTotal = mQuestionList.size();
            //random order of questions
            Collections.shuffle(mQuestionList);
            nextQuestion();
        }else {
            mQuestionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            mQuestionTotal = mQuestionList.size();
            mQuestionCount = savedInstanceState.getInt(KEY_QUESTION_NUMBER);
            mScore = savedInstanceState.getInt(KEY_SCORE);
            mCurrentQuestion = mQuestionList.get(mQuestionCount -1);//mQuestionCount starts from 1, index from 0
            mAnswered = savedInstanceState.getBoolean(KEY_ANSWERED);
        }

        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAnswered) {
                    if (rbAnswer1.isChecked() || rbAnswer2.isChecked() || rbAnswer3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, R.string.toast_btn_confirm_next, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    nextQuestion();
                }
            }
        });
    }

    public void init() {
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbAnswer1 = findViewById(R.id.rbFirstAnswer);
        rbAnswer2 = findViewById(R.id.rbSecondAnswer);
        rbAnswer3 = findViewById(R.id.rbThirdAnswer);
        btnConfirmNext = findViewById(R.id.btnConfirm);
    }

    public void nextQuestion() {
        rbAnswer1.setTextColor(defaultTextColorRb);
        rbAnswer2.setTextColor(defaultTextColorRb);
        rbAnswer3.setTextColor(defaultTextColorRb);
        rgAnswers.clearCheck();


        //get question from questionList and set in position
        if (mQuestionCount < mQuestionTotal) {
            mCurrentQuestion = mQuestionList.get(mQuestionCount);

            tvQuestion.setText(mCurrentQuestion.getQuestion());
            rbAnswer1.setText(mCurrentQuestion.getFirstAnswer());
            rbAnswer2.setText(mCurrentQuestion.getSecondAnswer());
            rbAnswer3.setText(mCurrentQuestion.getThirdAnswer());

            mQuestionCount++;
            //show current question count
            tvQuestionNumber.setText("Question: " + mQuestionCount + "/" + mQuestionTotal);
            //as long as question is not answered button is set to confirm
            mAnswered = false;
            btnConfirmNext.setText(R.string.button_confirm);
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        mAnswered = true;
        //getting an id of a selected radio button
        RadioButton rbSelected = findViewById(rgAnswers.getCheckedRadioButtonId());
        //comparing id of a selected radio button and the number of correct question
        //index of rb is from 0, and our correct questions from 1
        int answeredNumber = rgAnswers.indexOfChild(rbSelected) + 1;

        if (answeredNumber == mCurrentQuestion.getCorrectAnswer()) {
            mScore++;
            tvQuestionScore.setText("Score " + mScore);
        }
        showSolution();
    }

    private void showSolution() {
        rbAnswer1.setTextColor(getResources().getColor(R.color.wrongAnswerColor));
        rbAnswer2.setTextColor(getResources().getColor(R.color.wrongAnswerColor));
        rbAnswer3.setTextColor(getResources().getColor(R.color.wrongAnswerColor));

        tvQuestion.setText(mCurrentQuestion.getExplanation());

        switch (mCurrentQuestion.getCorrectAnswer()){
            case 1:
                rbAnswer1.setTextColor(getResources().getColor(R.color.correctAnswerColor));
                break;
            case 2:
                rbAnswer2.setTextColor(getResources().getColor(R.color.correctAnswerColor));
                break;
            case 3:
                rbAnswer3.setTextColor(getResources().getColor(R.color.correctAnswerColor));
                break;
        }

        if(mQuestionCount < mQuestionTotal){
            btnConfirmNext.setText(R.string.button_next);
        } else {
            btnConfirmNext.setText(R.string.button_finish);
        }
    }

    public void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(HIGH_SCORE, mScore);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(timeBackPressed + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, R.string.toast_back_btn_pressed, Toast.LENGTH_SHORT).show();
        }
        timeBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, mScore);
        outState.putInt(KEY_QUESTION_NUMBER, mQuestionCount);
        outState.putBoolean(KEY_ANSWERED, mAnswered);
        //to make this work we have to implement parcelable interface into our question class
        outState.putParcelableArrayList(KEY_QUESTION_LIST, mQuestionList);
    }
}
