package com.example.android.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    String s="";String newName="_",w="";
    String[] words = new String[4];
    int randoms,counter=0,flag=0,sum=0,score=0,highScore=0;
    int[] a = new int[50];
    TextView hangman,getText,wrongGuesses,wrongGuessCounter,scoreView,highScoreView;
    ImageView hangImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        words[0]="brick";
        words[1]="jump";
        words[2]="despicable";
        words[3] ="extras";
        Random rand = new Random();
        randoms = rand.nextInt(4);

        SharedPreferences myscore = getSharedPreferences("scoreInfo", MODE_PRIVATE);
        score=myscore.getInt("score",0);

        hangman = (TextView)findViewById(R.id.hangman);
        wrongGuesses=(TextView)findViewById(R.id.wrong_guesses);
        wrongGuessCounter=(TextView)findViewById(R.id.counter);
        scoreView=(TextView)findViewById(R.id.score_text_view);
        highScoreView=(TextView)findViewById(R.id.high_score);
        final Button check = (Button)findViewById(R.id.check_guess);

        //SharedPreferences myscore=this.getSharedPreferences("My awesome score ",Context.MODE_PRIVATE);
        //        highScore = myscore.getInt("highscore",0);

        hangImage=(ImageView)findViewById(R.id.image);
        hangImage.setImageResource(R.drawable.zero);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textEntered = (EditText) findViewById(R.id.gettext);
                String guesses = textEntered.getText().toString();
                if (guesses.length()==0)Toast.makeText(GameActivity.this,
                        "enter a character", Toast.LENGTH_SHORT).show();
               else if (guesses.length() == 1){
                    char g = guesses.charAt(0);
                    for( int n=0; n<words[randoms].length(); n++ ) {
                        if( words[randoms].charAt(n) == g ) {
                            counter++;
                        }
                    }
                    if (words[randoms].indexOf(g)<0 && w.indexOf(g)<0){
                        w+=guesses+" ";
                        sum++;
                        setimage(sum);
                        wrongGuesses.setText("INCORRECT GUESSES: "+w);
                        wrongGuessCounter.setText("no of incorrect guesses: "+sum);
                        if (sum==7) {
                            scoreView.setText("SCORE: 0");
                            check.setEnabled(false);
                            Toast.makeText(GameActivity.this,
                                    "GAME OVER,YOU LOST", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    for (int i = 0,j=0; i < words[randoms].length(); i++) {
                    if (words[randoms].charAt(i) == g) {
                       a[j]=i;
                       if (j<counter) {
                           for (int z = 0; z <= j; z++) {
                               //myName.setCharAt(4, 'x');
                               // sChars[2*a[z]] = g;
                               if (flag == 0){
                                   newName = s.substring(0, 2 * a[z]) + g + s.substring(2 * a[z] + 1);
                                   flag++;
                               }
                               else
                                   newName = newName.substring(0, 2 * a[z]) + g + newName.substring(2 * a[z] + 1);
                           }
                           hangman.setText(newName);
                       }
                       else j++;

                    }
                }
                    if (newName.indexOf('_')<0 ){
                        Toast.makeText(GameActivity.this,
                                "CONGRATULATIONS,YOU WON", Toast.LENGTH_LONG).show();
                        hangImage.setImageResource(R.drawable.youwon);
                        scoreView.setText("SCORE: "+ (7-sum));
                        check.setEnabled(false);
                        if ((7-sum)>score){
                            score=7-sum;
                            SharedPreferences myscore = getSharedPreferences("score info", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = myscore.edit();
                            editor.putInt("score",score);
                            editor.apply();
                            highScoreView.setText("HIGH SCORE: "+ score);
                            }
                    }

                   // char[] sChars = s.toCharArray();




                                // sChars[2*a[z]] = g;
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
            }

        });
        for ( int l=0;l < words[randoms].length();l++) {
            s = s.concat("_ ");
        }
        hangman.setText(s);

    }
public void setimage(int sum){
        switch (sum){
            case 0:
                hangImage.setImageResource(R.drawable.zero);
                break;
            case 1:
                hangImage.setImageResource(R.drawable.one);
                break;
            case 2:
                hangImage.setImageResource(R.drawable.two);
                break;
            case 3:
                hangImage.setImageResource(R.drawable.three);
                break;
            case 4:
                hangImage.setImageResource(R.drawable.four);
                break;
            case 5:
                hangImage.setImageResource(R.drawable.five);
                break;
            case 6:
                hangImage.setImageResource(R.drawable.six);
                break;
            case 7:
                hangImage.setImageResource(R.drawable.seven);
                break;
                default:hangImage.setImageResource(R.drawable.zero);

        }
}
    private void saveInfo() {
        SharedPreferences sharedpref = getSharedPreferences("scoreInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        if(highScore>score);
        else
        {editor.putInt("highscore",score);
            highScore=score;}
        editor.apply();
    }

    private void loadInfo() {
        SharedPreferences sharedpref = getSharedPreferences("scoreInfo", MODE_PRIVATE);
       score=sharedpref.getInt("highscore",0);


    }


}
