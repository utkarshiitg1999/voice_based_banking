package com.example.cmbuser.bank;

import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class english extends AppCompatActivity {
    public String user;
    int num = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        checkPermission();
        Bundle bundle=getIntent().getExtras();
        user=bundle.getString("user");
        final EditText editText = findViewById(R.id.editText);
        final TextView textView =findViewById(R.id.textView2);
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                {
                    editText.setText(matches.get(0));

                    String st=matches.get(0);
                    String s[]={"show me the balance","show mini statement","show passbook","transfer money","logout"};
                    if(st.compareToIgnoreCase(s[0])==0)
                    {
                        Intent intent = new Intent(english.this, showbal.class);
                        intent.putExtra("user", user);
                        //intent.putExtra("command",num);
                        startActivity(intent);
                    }
                    else if(st.compareToIgnoreCase(s[1])==0)
                    {
                    num=1;
                        Intent intent = new Intent(english.this, showmini.class);
                        intent.putExtra("user", user);
                        intent.putExtra("command",num);
                        startActivity(intent);
                    }
                    else if(st.compareToIgnoreCase(s[2])==0)
                    {
                    num=2;
                        Intent intent = new Intent(english.this, showmini.class);
                        intent.putExtra("user", user);
                        intent.putExtra("command",num);
                        startActivity(intent);
                    }
                    else if(st.compareToIgnoreCase(s[3])==0) {
                        num = 3;

                        Intent intent = new Intent(english.this, transfermoney.class);
                        intent.putExtra("user", user);
                        //intent.putExtra("command",num);
                        startActivity(intent);
                    }
                    else if(st.compareToIgnoreCase(s[4])==0)

                    {
                        Intent intent = new Intent(english.this, login.class);

                        startActivity(intent);
                    }
                    else
                    {
                    textView.setVisibility(View.VISIBLE);
                    }










                }









            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View view, MotionEvent motionEvent) {
                textView.setVisibility(View.INVISIBLE);
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        editText.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        editText.setText("");
                        editText.setHint("Listening...");
                        break;
                }
                return false;
            }
        });
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
}