package com.example.android.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    String s="";String newName;
    String[] words = new String[4];
    int randoms,counter=0,flag=0,j=0;
    int[] a = new int[50];
    TextView hangman,getText,wrongGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        words[0]="ezhils";
        words[1]="kr";
        words[2]="arun";
        words[3] ="hieethere";
        Random rand = new Random();
        randoms = rand.nextInt(4);
        hangman = (TextView)findViewById(R.id.hangman);
        Button check = (Button)findViewById(R.id.check_guess);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textEntered = (EditText) findViewById(R.id.gettext);
                String guesses = textEntered.getText().toString();
                char g = guesses.charAt(0);
                if (guesses.length() == 1){
                    for( int n=0; n<words[randoms].length(); n++ ) {
                        if( words[randoms].charAt(n) == g ) {
                            counter++;
                        }
                    }
                for (int i = 0; i < words[randoms].length(); i++) {
                    if (words[randoms].charAt(i) == g) {
                        a[j] = i;
                        j++;
                    }
                }
                   // char[] sChars = s.toCharArray();
                    if (flag==0) {
                        for (int z = 0; z < counter; z++) {
                            // sChars[2*a[z]] = g;
                            newName = s.substring(0, 2 * a[z]) + g + s.substring(2 * a[z] + 1);

                        }
                        hangman.setText(newName);
                    }
                    else {
                        for (int z = 0; z < counter; z++) {
                            // sChars[2*a[z]] = g;
                            newName = newName.substring(0, 2 * a[z]) + g + newName.substring(2 * a[z] + 1);
                        }
                        hangman.setText(newName);
                    }
                    //newName = String.valueOf(sChars);



                    //String myName = "domanokz";
                    //char[] myNameChars = myName.toCharArray();
                    //myNameChars[4] = 'x';
                    //myName = String.valueOf(myNameChars);
                    //String newName = myName.substring(0,4)+'x'+myName.substring(5);

            }
            else{
                    Toast.makeText(GameActivity.this,
                            "enter only one character", Toast.LENGTH_SHORT).show();}
                            flag++;
            }
        });
        for ( int l=0;l < words[randoms].length();l++) {
            s = s.concat("_ ");
        }
        hangman.setText(s);
    }



}