package com.example.doncrazy.myapplication;

import android.content.Intent;
import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity {
    public static final String EXTRA_INDEX_OF_QUESTION = "com.example.doncrazy.myapplication.index_of_question";
    public static final String EXTRA_HELP_WAS_USED = "com.example.doncrazy.myapplication.help_was_used";
    private static final String KEY_USED_HELP = "HelpUsed";
    private int mIndex;
    private  boolean HelpUsed;
    private TextView mHelpText;
    private Button mHelpButton;
    private static final String TAG = "HelpActivity";

    private int[] mHelpArray = {
            R.string.help_one,
            R.string.help_two,
            R.string.help_three,
            R.string.help_four,
            R.string.help_five,
            R.string.help_six
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        mIndex = getIntent().getIntExtra(EXTRA_INDEX_OF_QUESTION, 0);
        mHelpText = (TextView) findViewById(R.id.helpTextView);
        mHelpButton = (Button) findViewById(R.id.showHelpButton);
        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelpText.setText(mHelpArray[mIndex]);
                HelpUsed = true;
                Intent data = new Intent(HelpActivity.this, MainActivity.class);
                data.putExtra(MainActivity.EXTRA_HELP_WAS_USED, HelpUsed);
                Log.i(TAG, "put flag");
                setResult(RESULT_OK, data);
            }
        });
       if(savedInstanceState!=null){
          HelpUsed = savedInstanceState.getBoolean(KEY_USED_HELP, false);
           Log.i(TAG, "flag null");
       }
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Save flag");
       savedInstanceState.putBoolean(KEY_USED_HELP, HelpUsed);
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
}
