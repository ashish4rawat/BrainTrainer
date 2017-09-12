package com.techincalrj.braintrainer;

import android.graphics.Color;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

        CountDownTimer myCountDowntimer;
        Button playAgainButton, goButton;
        TextView timerText, questionText, scoreText, reviewText,adviceText;
        Button button1, button2, button3, button4;
        int x;
        int score=0,totalQuestion=0;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                playAgainButton = (Button) findViewById(R.id.playAgainButton);
                timerText = (TextView) findViewById(R.id.timerText);
                reviewText = (TextView)findViewById(R.id.reviewText);
                scoreText = (TextView)findViewById(R.id.scoreText);

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


        public void goClicked(View view) {


                newGame();


        }

        public void playAgainButton(View v) {

                Log.i("Info", "Play Again Button called");
                newGame();

        }

        public void newGame() {

                totalQuestion=0;
                score=0;
                setContentView(R.layout.new_layout);
                timerText = (TextView) findViewById(R.id.timerText);
                playAgainButton = (Button) findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.INVISIBLE);


                //Setting the timer
                generateQuestions();

                myCountDowntimer = new CountDownTimer(30000, 1000) {
                        @Override
                        public void onTick(long l) {


                                timerText.setText(String.valueOf(l / 1000) +"s");


                        }

                        @Override
                        public void onFinish() {

                                playAgainButton.setVisibility(View.VISIBLE);
                                disableButtons();
                                showScore();



                        }
                }.start();


                //Setting up the scores and questions


        }

        public void generateQuestions() {

                ++totalQuestion;

                button1 = (Button) findViewById(R.id.button1);
                button2 = (Button) findViewById(R.id.button2);
                button3 = (Button) findViewById(R.id.button3);
                button4 = (Button) findViewById(R.id.button4);

                questionText = (TextView) findViewById(R.id.questionText);
                Random r = new Random();
                int a = r.nextInt(25) + 1;
                int b = r.nextInt(25) + 1;

                questionText.setText(String.valueOf(a) + " + " + String.valueOf(b));


                button1.setText(String.valueOf(r.nextInt(25) + 1));
                button2.setText(String.valueOf(r.nextInt(25) + 1));
                button3.setText(String.valueOf(r.nextInt(25) + 1));
                button4.setText(String.valueOf(r.nextInt(25) + 1));

                 x = r.nextInt(4) + 1;

                if (x == 1)
                        button1.setText(String.valueOf(a + b));
                if (x == 2)
                        button2.setText(String.valueOf(a + b));
                if (x == 3)
                        button3.setText(String.valueOf(a + b));
                if (x == 4)
                        button4.setText(String.valueOf(a + b));




        }

        public void checkAnswer(View vi){



                reviewText = (TextView)findViewById(R.id.reviewText);

                if(vi.getId()==R.id.button1  ){

                        if(x==1)
                        {
                                reviewText.setText("Correct");
                                reviewText.setTextColor(Color.GREEN);
                                ++score;
                        }
                        else
                        {
                                reviewText.setText("Wrong");
                                reviewText.setTextColor(Color.RED);
                                --score;
                        }
                }
                if(vi.getId()==R.id.button2  ){

                        if(x==2)
                        {
                                reviewText.setText("Correct");
                                reviewText.setTextColor(Color.GREEN);
                                ++score;
                        }
                        else
                        {
                                reviewText.setText("Wrong");
                                reviewText.setTextColor(Color.RED);
                                --score;
                        }
                }
                if(vi.getId()==R.id.button3  ){

                        if(x==3)
                        {
                                reviewText.setText("Correct");
                                reviewText.setTextColor(Color.GREEN);
                                ++score;
                        }
                        else
                        {
                                reviewText.setText("Wrong");
                                reviewText.setTextColor(Color.RED);
                                --score;
                        }
                }
                if(vi.getId()==R.id.button4  ){

                        if(x==4)
                        {
                                reviewText.setText("Correct");
                                reviewText.setTextColor(Color.GREEN);

                                ++score;
                        }
                        else
                        {
                                reviewText.setText("Wrong");
                                reviewText.setTextColor(Color.RED);
                                --score;
                        }
                }

                updateScore();
                generateQuestions();

        }

        public void disableButtons(){

                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);

        }

        public void updateScore(){

                scoreText = (TextView)findViewById(R.id.scoreText);
                scoreText.setText(String.valueOf(score)+"/"+String.valueOf(totalQuestion));


        }

        public void showScore(){

                reviewText.setText("Your Score : "+scoreText.getText().toString());
                reviewText.setTextColor(Color.BLACK);
                reviewText.setTextSize(30.0f);

                adviceText = (TextView)findViewById(R.id.adviceText);

                String str="";
                if(score<=0){
                        str= "Need Some Practice";
                }
                if(score>0 && score< 5)
                {
                        str="Not Bad";
                }
                if(score>=5 && score<10)
                {
                        str="Very Good";
                }
                if(score>=10&&score<17)
                {
                        str="Excellent";
                }
                if(score>17){
                        str="OUTSTANDING";
                }


                adviceText.setText("REVIEW : "+str);

        }










}
