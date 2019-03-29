package hr.kipson.karolina.quizapplication;

import android.provider.BaseColumns;

public final class DatabaseTable {

    //constructor here so we can never use this class as anything else but
    //container for constants
    private DatabaseTable(){}

    //BaseColumns interface provides id for the table
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_FIRST_ANSWER = "first_answer";
        public static final String COLUMN_SECOND_ANSWER = "second_answer";
        public static final String COLUMN_THIRD_ANSWER = "third_answer";
        public static final String COLUMN_CORRECT_ANSWER_NUMBER = "correct_answer_number";
        public static final String COLUMN_EXPLANATION = "explanation";
    }
}
