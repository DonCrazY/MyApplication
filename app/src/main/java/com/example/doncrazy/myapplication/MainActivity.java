package com.example.doncrazy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private Button mHelpButton;
    private TextView questionText;
    private TextView answerText;
    private Button Developers;
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_MISHELP = "mIsHelp";
    public static final String EXTRA_HELP_WAS_USED = "com.example.doncrazy.myapplication.help_was_used";

    private Question[] mQuestionArray = new Question[] {
            new Question(R.string.qst1, false),
            new Question(R.string.qst2, true),
            new Question(R.string.qst3, false),
            new Question(R.string.qst4, false),
            new Question(R.string.qst5, true),
            new Question(R.string.qst6, true)
    };

    private int index=0;
    private boolean mIsHelp;

    private void UpdateQuestion(){
        int question = mQuestionArray[index].getQuestionId();
        questionText.setText(question);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode,Intent data){
        if(data==null){
            return;
        }
        mIsHelp = data.getBooleanExtra(HelpActivity.EXTRA_HELP_WAS_USED, false);
    }

    private void checkAnswer(boolean ans){
        boolean trueAnswer = mQuestionArray[index].isAnswer();
        int messageId = 0;
        int messageResId = 0;
        if (ans == trueAnswer){
            messageId = R.string.trueAsnwer;
            if(mIsHelp){
                messageResId = R.string.advice1;
                Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
            }
        }
        else {
            messageId = R.string.falseAnswer;
            if(mIsHelp){
                messageResId = R.string.advice2;
                Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
            }
        }
        answerText.setText(messageId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        questionText = (TextView) findViewById(R.id.question_text_view);
        answerText = (TextView) findViewById(R.id.answer);
        UpdateQuestion();

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        Developers = (Button)findViewById(R.id.Developers);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index+1)%mQuestionArray.length;
                mIsHelp = false;
               UpdateQuestion();
                answerText.setText("");
            }
        });


        Developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.Developers,
                        Toast.LENGTH_LONG).show();
            }
        });
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        UpdateQuestion();
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        UpdateQuestion();
        mHelpButton = (Button) findViewById (R.id.help_button);
        mHelpButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v){
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                i.putExtra(HelpActivity.EXTRA_INDEX_OF_QUESTION, index);
                startActivityForResult(i, 0);

            }
        });
        UpdateQuestion();


        if(savedInstanceState!=null)
        {
            index = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsHelp = savedInstanceState.getBoolean(KEY_MISHELP,false);
        }
        UpdateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, index);
        savedInstanceState.putBoolean(KEY_MISHELP,mIsHelp);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
