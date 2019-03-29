package hr.kipson.karolina.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizApplication.db3";
    private static final int DATABASE_VERSION = 1;

    //holds a reference to an actual database so we can use it in onCreate()
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creates db
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + DatabaseTable.QuestionsTable.TABLE_NAME +
                " ( " + DatabaseTable.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseTable.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                DatabaseTable.QuestionsTable.COLUMN_FIRST_ANSWER + " TEXT, " +
                DatabaseTable.QuestionsTable.COLUMN_SECOND_ANSWER + " TEXT, " +
                DatabaseTable.QuestionsTable.COLUMN_THIRD_ANSWER + " TEXT, " +
                DatabaseTable.QuestionsTable.COLUMN_CORRECT_ANSWER_NUMBER + " INTEGER, " +
                DatabaseTable.QuestionsTable.COLUMN_EXPLANATION  + " TEXT " + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    //manipulates db (ALL CHANGES AFTER the initial creation of db)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTable.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    //testing questions
    private void fillQuestionsTable() {
        Question q1 = new Question("Which battle of 1571 marked the end of the Ottoman naval supremacy in the Mediterranean?", "Battle of Preveza", "Battle of Djerba", "The Battle of Lepanto", 3, "The Battle of Lepanto was a naval engagement that took place on 7 October 1571 when a fleet of the Holy League, led by the Venetian Republic and the Spanish Empire, inflicted a major defeat on the fleet of the Ottoman Empire in the Gulf of Patras.");
        addQuestion(q1);
        Question q2 = new Question("Holy Roman Emperor Charles VI created which principality in 1719?", "Lichtenstein", "Saarland", "Gersau", 1, "The Holy Roman Emperor Charles VI united Vaduz and Schellenberg in 1719, and then elevated the territory to the rank of Reichsfürstentum (Imperial Principality).");
        addQuestion(q2);
        Question q3 = new Question("Saloth Sar, born 19 May 1925, is better known by what name?", "Phan Boi Chau", "Son Sen,", "Pol Pot", 3,"Pol Pot(born Saloth Sar) was a Cambodian revolutionary and politician who served as the general secretary of the Communist Party of Kampuchea from 1963 to 1981.");
        addQuestion(q3);
        Question q4 = new Question("What tax was introduced in England and Wales in 1696 and repealed in 1851?", "Land tax", "Window tax", "Pitt's income tax", 2,"The window tax was a property tax based on the number of windows in a house. In England and Wales it was introduced in 1696 and was repealed in 1851.");
        addQuestion(q4);
        Question q5 = new Question("Southern Rhodesia became what country in 1980?", "Zambia", "Malawi", "Zimbabwe", 3,"On 18 April 1980, Southern Rhodesia became the independent Republic of Zimbabwe.");
        addQuestion(q5);
        Question q6 = new Question("Who became president after the assassination of Abraham Lincoln?", "Andrew Johnson", "Ulysses S. Grant", "James Buchanan", 1,"Johnson assumed the presidency as he was vice president of the United States at the time of the assassination of Abraham Lincoln.");
        addQuestion(q6);
        Question q7 = new Question("In which year was the death of Queen Elizabeth I?", "1609", "1603", "1613", 2,"Queen Elizabeth I died on 24 March 1603 at Richmond Palace, between two and three in the morning.");
        addQuestion(q7);
        Question q8 = new Question("In which war was The Battle of Agincourt?", "Thirteen Years’ War", "Hundred Years War", "Mad War", 2,"During the Hundred Years’ War between England and France, Henry V leads his forces to victory at the Battle of Agincourt in northern France.");
        addQuestion(q8);
        Question q9 = new Question("Which country was first to operate an old age pension scheme?", "Ireland", "United Kingdom", "Germany", 3,"As part of Otto von Bismarck's social legislation (Old Age and Disability Insurance Bill in 1889), the Old Age Pension program, financed by a tax on workers, was originally designed to provide a pension annuity for workers who reached the age of 70 years.");
        addQuestion(q9);
        Question q10 = new Question("In which European city did composer Richard Wagner die in 1883?", "Venice", "Bayreuth", "Munich", 1,"Wagner died of a heart attack at the age of 69 on 13 February 1883 at Ca' Vendramin Calergi, a 16th-century palazzo on the Grand Canal in Venice.");
        addQuestion(q10);
        Question q11 = new Question("In which year did the Titanic sink?", "1912", "1909", "1903", 1,"RMS Titanic sank in the early morning of 15 April 1912 in the North Atlantic Ocean.");
        addQuestion(q11);
        Question q12 = new Question("What Apollo 13 astronaut contacted Mission Control with the words, “Houston, we’ve had a problem here..” ? ", "James A. Lovell", "John L. \"Jack\" Swigert", "Fred W. Haise", 2,"The words actually spoken, initially by Jack Swigert, were \"Okay, Houston, we've had a problem here.\"");
        addQuestion(q12);
        Question q13 = new Question("What was the name of the pandemic which killed over 1% of the world’s population in 1918?", "Cholera", "Smallpox", "Spanish Flu", 3,"The 1918 influenza pandemic (Spanish flu) was an unusually deadly influenza pandemic, the first of the two pandemics involving H1N1 influenza virus. It infected 500 million people around the world and resulted in the deaths of 50 to 100 million (3-5 % of the world's population).");
        addQuestion(q13);
        Question q14 = new Question("In which year was the Wall Street Crash?", "1931", "1930", "1929", 3,"The Wall Street Crash was in 1929.");
        addQuestion(q14);
        Question q15 = new Question("In which French city was Joan of Arc put to death?", "Compiègne", "Rouen", "Rheims", 2,"Joan of Arc was burned at the stake on 30 May 1431 in Rouen, dying at about nineteen years of age.");
        addQuestion(q15);
        Question q16 = new Question("In which century was The Black Death?", "Fifteenth", "Fourteenth", "Thirteenth", 2,"The Black Death, also known as the Great Plague, the Black Plague, or simply the Plague, was one of the most devastating pandemics in human history, resulting in the deaths of an estimated 75 to 200 million people in Eurasia and peaking in Europe from 1347 to 1351.");
        addQuestion(q16);
        Question q17 = new Question("Who invented the thermometer in 1593?", "Galileo Galilei", "Evangelista Torricelli", "Otto von Guericke", 1,"Galileo Galilei invented a rudimentary water thermometer in 1593 which, for the first time, allowed temperature variations to be measured.");
        addQuestion(q17);
        Question q18 = new Question("In which century did Italian friar Thomas Aquinas live?", "Thirteenth", "Fourteenth", "Fifteenth", 1,"St. Thomas Aquinas, also called Aquinas was born 1224/25 in Roccasecca, near Aquino, Terra di Lavoro, Kingdom of Sicily, and died March 7, 1274 in Fossanova, near Terracina, Latium, Papal States.");
        addQuestion(q18);
        Question q19 = new Question("Who was the ruler of the USSR from 1917-24?", "Leon Trotsky", "Vladimir Lenin", "Alexei Rykov", 2,"Lenin was leader of the Russian Soviet Federative Socialist Republic (RSFSR) from 1917 and leader of the Union of Soviet Socialist Republics (USSR) from 1922 until his death in 1924.");
        addQuestion(q19);
        Question q20 = new Question("The Wars of the Roses (1455-85) were fought between which two houses of England?", "Tudor and Lancaster", "Tudor and York", "York and Lancaster", 3,"The Wars of the Roses were a series of English civil wars for control of the throne of England fought between supporters of the royal House of Plantagenet: the House of Lancaster(symbol was red rose) and the House of York (symbol was white rose).");
        addQuestion(q20);
        Question q21 = new Question("In which year did the UK hand over Hong Kong sovereignty to China? ", "1995", "1997", "1991", 2,"Hong Kong was transferred to China on 1 July 1997, after 156 years of British rule.");
        addQuestion(q21);
        Question q22 = new Question("Which German city endured the worst bombing of World War Two in February 1945?", "Berlin", "Dresden", "Hamburg", 2,"In four raids between 13 and 15 February 1945, RAF and US planes dropped more than 3,900 tons of high-explosive bombs and incendiary devices on Dresden. The bombing and the resulting firestorm destroyed over 1,600 acres (6.5 km2) of the city centre.");
        addQuestion(q22);
        Question q23 = new Question("What was discovered in 1799 by Pierre-François Bouchard, a Napoleonic soldier?", "The Shabaka Stone", "The Benben Stone", "The Rosetta Stone", 3,"Lieutenant Pierre-François Bouchard spotted a slab with inscriptions on one side of the wall that the soldiers had uncovered.It was later know as The Rosseta stone because it was found near the town of Rashid(Rosseta).");
        addQuestion(q23);
        Question q24 = new Question("Which Portuguese-born navigator was the first European to cross the Pacific Ocean?", "Vasco da Gama", "João Vaz Corte-Real", "Ferdinand Magellan", 3,"Ferdinand Magellan was a Portuguese explorer who organised the Spanish expedition to the East Indies from 1519 to 1522, resulting in the first circumnavigation of the Earth, completed by Juan Sebastián Elcano.");
        addQuestion(q24);
        Question q25 = new Question("Which country declared war on birds in 1932?", "Australia", "New Zealand", "Philippines", 1,"The Great Emu War of Australia was in 1932 when emus were causing chaos in the Western Australia district of Campion and the military were sent to the region with machine guns to kill them.");
        addQuestion(q25);
        Question q26 = new Question("Which killer was also known as ‘The Whitechapel Murderer’? ", "Jack the Ripper", "H. H. Holmes", "Joseph Vacher", 1,"Jack the Ripper is the best-known name for an unidentified serial killer generally believed to have been active in the largely impoverished areas in and around the Whitechapel district of London in 1888. The killer was called the Whitechapel Murderer and Leather Apron.");
        addQuestion(q26);
        Question q27 = new Question("Who did Adolf Hitler dictate Mein Kampf to while in prison?", "Max Amann", "Josef Berg", "Rudolf Hess", 3,"Hitler began Mein Kampf while imprisoned following his failed Putsch in Munich in November 1923. The book was edited by Hitler's deputy Rudolf Hess.");
        addQuestion(q27);
        Question q28 = new Question("Which American outlaw was the most famous member of the James-Younger Gang?", "Butch Cassidy", "Sundance Kid", "Jesse James", 3,"James-Younger Gang included the Younger Brothers (Cole, Jim, John, and Bob), the James Brothers (Jesse and Frank), Clell Miller, Arthur McCoy, Charlie Pitts, John Jarrette, Bill Chadwell (alias Bill Stiles), and Matthew \"Ace\" Nelson.");
        addQuestion(q28);
        Question q29 = new Question("What year did the Dow Jones Industrial Average break both the 4000 and 5000 marks?", "1991", "1995", "1996", 2,"Dow Jones crossed 4,000 for the first time in February of 1995, then crossed 5,000 for the first time in November of that same year.");
        addQuestion(q29);
        Question q30 = new Question("What position was Eileen Collins the first female to hold on a space shuttle mission?", "Mission Specialist", "Commander", "Payload Specialist", 2,"Eileen Marie Collins was the first female pilot and first female commander of a Space Shuttle.");
        addQuestion(q30);
    }

    //id will automatically insert into table(autoincrement)
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTable.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(DatabaseTable.QuestionsTable.COLUMN_FIRST_ANSWER, question.getFirstAnswer());
        cv.put(DatabaseTable.QuestionsTable.COLUMN_SECOND_ANSWER, question.getSecondAnswer());
        cv.put(DatabaseTable.QuestionsTable.COLUMN_THIRD_ANSWER, question.getThirdAnswer());
        cv.put(DatabaseTable.QuestionsTable.COLUMN_CORRECT_ANSWER_NUMBER, question.getCorrectAnswer());
        cv.put(DatabaseTable.QuestionsTable.COLUMN_EXPLANATION, question.getExplanation());

        //nullColumnHack:SQL doesn't allow inserting a completely empty row without naming at least one column name.
        // If your provided values are empty, no column names are known and an empty row can't be inserted.
        // If not set to null, the nullColumnHack parameter provides the name of nullable column name to
        // explicitly insert a NULL into in the case where your values are empty.
        db.insert(DatabaseTable.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        //getReadableDatabase() will call onCreate() and create a db
        db = getReadableDatabase();
        //cursor to query db, selectionArgs to get specific results(placeholder)
        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseTable.QuestionsTable.TABLE_NAME, null);

        //moving cursor to the first entry, if there is an entry do
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_QUESTION)));
                question.setFirstAnswer(c.getString(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_FIRST_ANSWER)));
                question.setSecondAnswer(c.getString(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_SECOND_ANSWER)));
                question.setThirdAnswer(c.getString(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_THIRD_ANSWER)));
                question.setCorrectAnswer(c.getInt(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_CORRECT_ANSWER_NUMBER)));
                question.setExplanation(c.getString(c.getColumnIndex(DatabaseTable.QuestionsTable.COLUMN_EXPLANATION)));

                questionList.add(question);
                }while(c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
