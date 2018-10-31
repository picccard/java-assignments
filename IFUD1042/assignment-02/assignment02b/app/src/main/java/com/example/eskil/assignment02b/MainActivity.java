package com.example.eskil.assignment02b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/*
    Author:     Eskil Uhlving Larsen
    Date:       26.10.2018
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref the add-button and add an onClickListener
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Retruns null if exception gets thrown by parseInt
                // Retruns array{answer, upperLimit} if successfull
                int[] checkedNumbers = numIsInteger();

                //Shows the error as toast and exits
                if (checkedNumbers == null) {
                    Toast.makeText(getApplicationContext(), "Please check Answer and UpperLimit", Toast.LENGTH_LONG).show();
                    return;
                }

                // Gets some variables
                int answer = checkedNumbers[0];
                int num1 = Integer.parseInt(
                        ((TextView) findViewById(R.id.txt01))
                        .getText().toString());
                int num2 = Integer.parseInt(
                        ((TextView) findViewById(R.id.txt02))
                                .getText().toString());

                if (answer == (num1 + num2)) { // Right answer
                    Toast.makeText(getApplicationContext(), getString(R.string.right), Toast.LENGTH_LONG).show();
                } else { // Wrong answer
                    Toast.makeText(getApplicationContext(), (getString(R.string.wrong) + " " + String.valueOf(num1 + num2)), Toast.LENGTH_LONG).show();
                }

                // Gets the upperLimit and generates the new numbers for the UI
                int limit = checkedNumbers[1];
                generateNewNumbers(limit);

            }
        });

        //Ref the add-button and add an onClickListener
        Button btnMulti = (Button) findViewById(R.id.btnMulti);
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Retruns null if exception gets thrown by parseInt
                // Retruns array{answer, upperLimit} if successfull
                int[] checkedNumbers = numIsInteger();

                //Shows the error as toast and exits
                if (checkedNumbers == null) {
                    Toast.makeText(getApplicationContext(), "Please check Answer and UpperLimit", Toast.LENGTH_LONG).show();
                    return;
                }

                // Gets some variables
                int answer = checkedNumbers[0];

                int num1 = Integer.parseInt(
                        ((TextView) findViewById(R.id.txt01))
                                .getText().toString());
                int num2 = Integer.parseInt(
                        ((TextView) findViewById(R.id.txt02))
                                .getText().toString());

                if (answer == (num1 * num2)) { // Right
                    Toast.makeText(getApplicationContext(), getString(R.string.right), Toast.LENGTH_LONG).show();
                } else { // Wrong
                    Toast.makeText(getApplicationContext(), (getString(R.string.wrong) + " " + String.valueOf(num1 * num2)), Toast.LENGTH_LONG).show();
                }

                // Gets the upperLimit and generates the new numbers for the UI
                int limit = checkedNumbers[1];
                generateNewNumbers(limit);

            }
        });
    }

    private int[] numIsInteger() {
        //Retruns null if exception gets thrown by parseInt
        //Retruns array{answer, upperLimit} if successfull

        EditText editTxtAnswer = (EditText) findViewById(R.id.editTxtAnswer);
        EditText editTxtUpLimit = (EditText) findViewById(R.id.editTxtUpLimit);
        try {
            int answer = Integer.parseInt(editTxtAnswer.getText().toString());
            int upLimit = Integer.parseInt(editTxtUpLimit.getText().toString());
            int[] result = {answer, upLimit};
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    // Generates random numbers within the limit and changes the UI
    private void generateNewNumbers(int upperLimit) {
        Random r = new Random();

        TextView num1 = (TextView) findViewById(R.id.txt01);
        TextView num2 = (TextView) findViewById(R.id.txt02);

        int randomNum1 = r.nextInt(upperLimit + 1);
        int randomNum2 = r.nextInt(upperLimit + 1);

        num1.setText(String.valueOf(randomNum1));
        num2.setText(String.valueOf(randomNum2));
    }
}
