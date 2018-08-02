package com.example.nikoapps.brainteaser;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainTeaser extends AppCompatActivity {

    TextView timerView;
    TextView questionView;
    TextView scoreView;
    TextView resultView;

    TableLayout answerPanels;

    Button startBtn;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;

    Boolean gameActive = false;

    CountDownTimer countDownTimer;

    int addend1;
    int addend2;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions;


    public void toggleVisibility() {

        if (gameActive) {

            timerView.setVisibility(View.INVISIBLE);
            questionView.setVisibility(View.INVISIBLE);
            scoreView.setVisibility(View.INVISIBLE);
            resultView.setVisibility(View.INVISIBLE);
            answerPanels.setVisibility(View.INVISIBLE);
            startBtn.setVisibility(View.VISIBLE);

        } else {

            timerView.setVisibility(View.VISIBLE);
            questionView.setVisibility(View.VISIBLE);
            scoreView.setVisibility(View.VISIBLE);
            resultView.setVisibility(View.VISIBLE);
            answerPanels.setVisibility(View.VISIBLE);
            startBtn.setVisibility(View.INVISIBLE);

            resultView.setText("");

        }

    }

    public void updateTimerText(int secsLeft){

        int mins = (int) (secsLeft/60);
        int secs = secsLeft - (mins*60);

        String secondString = Integer.toString(secs);
        if (secondString.equals("0")){
            secondString = "00";
        }

        String displayText = Integer.toString(mins) + ":" + secondString;

        timerView.setText(displayText);

        Log.i("Timer Update",displayText);

    }

    public void generateQuestion() {

        Random rand = new Random();
        addend1 = rand.nextInt(21);
        addend2 = rand.nextInt(21);

        // clear answers array
        answers.clear();

        locationOfCorrectAnswer = rand.nextInt(4);

        questionView.setText(Integer.toString(addend1) + " + " + Integer.toString(addend2));

        int incorrectAnswer;

        for (int pos = 0; pos < 4 ; pos++){
            if (pos == locationOfCorrectAnswer){
                // array length is undefined so use add not =
                answers.add(addend1 + addend2);
            } else{
                // check if incorrect answer generated is = to answer and replace
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == (addend1 + addend2)){
                    incorrectAnswer = rand.nextInt(41);
                }
                // array length is undefined so use add not =
                answers.add(incorrectAnswer);
            }
        }


        ans1.setText(Integer.toString(answers.get(0)));
        ans2.setText(Integer.toString(answers.get(1)));
        ans3.setText(Integer.toString(answers.get(2)));
        ans4.setText(Integer.toString(answers.get(3)));

        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

    }

    public void selectAns(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Log.i("tag", (String) view.getTag());
            score++;
            resultView.setText("Correct!");

        } else {
            resultView.setText("Wrong!");
        }
        numberOfQuestions++;
        generateQuestion();
    }

    public void startGame(View view) {
        toggleVisibility();
        gameActive = true;

        score = 0;
        numberOfQuestions = 0;

        generateQuestion();

        countDownTimer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                updateTimerText((int) l/1000);
            }

            @Override
            public void onFinish() {
                toggleVisibility();
                updateTimerText((int) 0);
                gameActive = false;

                resultView.setText("Final score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                resultView.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain_teaser);

        timerView = (TextView) findViewById(R.id.timerView);
        questionView = (TextView) findViewById(R.id.questionView);
        scoreView = (TextView) findViewById(R.id.scoreView);
        resultView = (TextView) findViewById(R.id.resultView);

        answerPanels = (TableLayout) findViewById(R.id.answerPanels);

        startBtn = (Button) findViewById(R.id.startBtn);
        ans1 = (Button) findViewById(R.id.ans1);
        ans2 = (Button) findViewById(R.id.ans2);
        ans3 = (Button) findViewById(R.id.ans3);
        ans4 = (Button) findViewById(R.id.ans4);


    }
}
