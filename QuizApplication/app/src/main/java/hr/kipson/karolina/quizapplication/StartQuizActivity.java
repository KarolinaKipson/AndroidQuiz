package hr.kipson.karolina.quizapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartQuizActivity extends AppCompatActivity {

    private static final int REQUEST_QUIZ_ACTIVITY = 101;
    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String KEY_HIGHSCORE ="keyHighscore";

    private TextView tvHighscore;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        tvHighscore = findViewById(R.id.tvHighscore);
        displayHighscore();

        Button btnStartQuiz = findViewById(R.id.btnStartApp);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent i = new Intent(StartQuizActivity.this, QuizActivity.class);
        //startActivity(i)-> connecting activities but not transferring data
        startActivityForResult(i, REQUEST_QUIZ_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request code is our constant, result code was set to RESULT_OK, and data is in resultIntent
        //->setResult()
        if(requestCode == REQUEST_QUIZ_ACTIVITY) {
            if(resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.HIGH_SCORE, 0);
                if(score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void updateHighscore(int newHighscore) {
        highscore = newHighscore;

        tvHighscore.setText("Highscore: " + highscore);

        //Interface for accessing and modifying preference data returned by Context.getSharedPreferences(String, int).
        // For any particular set of preferences, there is a single instance of this class that all clients share.
        // Modifications to the preferences must go through an SharedPreferences.Editor object to ensure the preference
        // values remain in a consistent state and control when they are committed to storage.
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putInt(KEY_HIGHSCORE, highscore);//we store our highscore
        e.apply(); //apply() to save it
    }

    //displaying highscore from SharedPreferences
    private void displayHighscore(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        highscore = sp.getInt(KEY_HIGHSCORE, 0);//default value if we do not have highscore is 0
        tvHighscore.setText("Highscore: " + highscore);
    }
}
