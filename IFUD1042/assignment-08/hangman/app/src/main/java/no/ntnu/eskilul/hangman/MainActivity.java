package no.ntnu.eskilul.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/*
    Author:     Eskil Uhlving Larsen
    Date:       08.11.2018
    Title:      SettingsActivity.java
*/


public class MainActivity extends AppCompatActivity {

    public static String TAG = "HANGMAN_MAIN";
    Locale locale;

    //Word categories
    String[] cities;
    String[] countries;
    String[] carbrands;

    String[] currentCategory;
    ArrayList<String> wordHistory = new ArrayList<String>();


    TextView lblGuess;
    TextView lblGamesWon;
    TextView lblGamesLost;
    TextView lblGamesPlayed;
    Random rand = new Random();
    LinearLayout mainContainer;
    ArrayList<TextView> qwerty = new ArrayList<TextView>();
    String solution;
    String currentGuessString;
    ImageView hangmanImage;
    private int INCREMENT_GUESS;
    private int wrongGuesses;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;


    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // https://stackoverflow.com/a/6680630
        // ids.xml see also onOptionsItemSelected
        menu.add(Menu.NONE, R.id.menu_newGame, Menu.NONE, R.string.menu_newGame).setIcon(R.drawable.ic_new_game) // Title won't show :(
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        //menu.add(Menu.NONE, R.id.menu_show_solutions, Menu.NONE, R.string.menu_show_solution).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add(Menu.NONE, R.id.menu_reset_stats, Menu.NONE, R.string.menu_reset_stats).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add(Menu.NONE, R.id.menu_settings, Menu.NONE, R.string.menu_settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add(Menu.NONE, R.id.menu_about, Menu.NONE, R.string.menu_about).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add(Menu.NONE, R.id.menu_quit, Menu.NONE, R.string.menu_quit).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_newGame:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog
                        .setMessage(R.string.newGameAlert)
                        .setNegativeButton(R.string.negativeResponse, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.positiveResponse, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startNewGame();
                            }
                        }).show();
                return true;
            /*case R.id.menu_show_solutions:
                TextView lblSolution = findViewById(R.id.lblSolution);
                lblSolution.setText(solution);
                if (lblSolution.getVisibility() == View.GONE) {
                    lblSolution.setVisibility(View.VISIBLE);
                } else {
                    lblSolution.setVisibility(View.GONE);
                }
                return true;*/
            case R.id.menu_reset_stats:
                gamesPlayed = 0;
                gamesWon = 0;
                gamesLost = 0;
                wordHistory = new ArrayList<String>(); // also clear the wordhistory
                startNewGame();
                return true;
            case R.id.menu_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.menu_about:
                AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
                aboutDialog
                        .setMessage(R.string.about)
                        .setPositiveButton("OK", null).show();
                return true;
            case R.id.menu_quit:
                // https://stackoverflow.com/a/10407660 | or

                //https://stackoverflow.com/a/32203016
                finishAffinity();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources r = getResources();

        lblGuess = (TextView) findViewById(R.id.lblGuess);
        lblGamesWon = (TextView) findViewById(R.id.lblGamesWon);
        lblGamesLost = (TextView) findViewById(R.id.lblGamesLost);
        lblGamesPlayed = (TextView) findViewById(R.id.lblGamesPlayed);
        mainContainer = (LinearLayout) findViewById(R.id.mainContainer);
        hangmanImage = (ImageView) findViewById(R.id.imageView);

        locale = r.getConfiguration().locale;

        cities = r.getStringArray(R.array.cities);
        countries = r.getStringArray(R.array.countries);
        carbrands = r.getStringArray(R.array.carbrands);

        updatePref(); // initiate the currentCategory, INCREMENT_GUESS

        wrongGuesses = 0;
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;

        addLettersToLetterLayout(getString(R.string.alphabet));

        startNewGame();
    }

    @Override
    protected void onResume() {
        // will run when returning from settings
        // starts a new word but gamesplayed-counter will not increase
        super.onResume();
        updatePref();
        gamesPlayed--; // startNewGame will do ++, so this is a workaround
        wordHistory.remove(wordHistory.size() - 1);
        // startNewGame will get a new word, even though our current word is not done
        // so remove it from the history so it can be played later
        startNewGame();
    }

    public void startNewGame() {
        gamesPlayed++;
        // get a word, this is the solution
        solution = getNewWord();
        //solution = "mitt-ord123her"; // uncomment to test words with special chars
        // clear any old guessString
        currentGuessString = "";
        // enter a underscore for every char in the solution
        for (int i = 0; i < solution.length(); i++) {
            if ( Character.isLetter(solution.charAt(i)) ) { //only add a "_" if the char is a letter
                currentGuessString += "_";
            } else {
                currentGuessString += solution.charAt(i);
            }
        }
        // update the textview with the currentGuessString
        updateLblGuess();
        updateLblStats();
        wrongGuesses = 0;
        setHangmanImage(wrongGuesses);
        resetQwerty();
    }

    private String getNewWord() {
        String temp;
        boolean loopAgain = false;


        /* gets a random word from the category and checks if the word is in the history
        if every word in the category is previous played then this will loop forever
        do {
            temp = currentCategory[rand.nextInt(currentCategory.length)].toLowerCase();
            for (String s : wordHistory) {
                if (s.toLowerCase().equals(temp.toLowerCase())) tempIsNewWord = false;break;
            }
        } while (!tempIsNewWord);*/



        // only checks the previous 5 words
        List<String> previous5words = wordHistory.subList(Math.max(wordHistory.size() - 5, 0), wordHistory.size());
        do {
            temp = currentCategory[rand.nextInt(currentCategory.length)].toLowerCase();
            for (String s : previous5words) {
                if (s.toLowerCase().equals(temp.toLowerCase())) {
                    Log.i("haha", temp + " er blant de siste 5");
                    loopAgain = true;
                    break;
                } else {
                    loopAgain = false;
                }
            }
        } while (loopAgain);

        wordHistory.add(temp);
        return temp;
    }

    private void updateGame() {
        setHangmanImage(wrongGuesses);
        if (wrongGuesses >= 10) {
            gameOver();
        } else if (currentGuessString.toLowerCase().equals(solution.toLowerCase())) { // compare both as lower
            gameWon();
        }
        Log.i(TAG, currentGuessString);
        updateLblStats();
    }

    private void resetQwerty() {
        for (TextView letter : qwerty) {
            letter.setBackgroundColor(Color.WHITE);
            letter.setClickable(true);
        }
    }

    private void disableQwerty() {
        for (TextView letter : qwerty) {
            letter.setClickable(false);
        }
    }

    private void setHangmanImage(int stepsToDraw) {
        Drawable hangmanSketch;
        Resources r = getResources();
        if (stepsToDraw>10) stepsToDraw = 10;
        switch (stepsToDraw) {
            case 0: hangmanSketch = r.getDrawable(R.drawable.hang00);break;
            case 1: hangmanSketch = r.getDrawable(R.drawable.hang01);break;
            case 2: hangmanSketch = r.getDrawable(R.drawable.hang02);break;
            case 3: hangmanSketch = r.getDrawable(R.drawable.hang03);break;
            case 4: hangmanSketch = r.getDrawable(R.drawable.hang04);break;
            case 5: hangmanSketch = r.getDrawable(R.drawable.hang05);break;
            case 6: hangmanSketch = r.getDrawable(R.drawable.hang06);break;
            case 7: hangmanSketch = r.getDrawable(R.drawable.hang07);break;
            case 8: hangmanSketch = r.getDrawable(R.drawable.hang08);break;
            case 9: hangmanSketch = r.getDrawable(R.drawable.hang09);break;
            case 10: hangmanSketch = r.getDrawable(R.drawable.hang10);break;
            default: throw new IllegalArgumentException("Can only draw images from 0-10, this is illegal: " + stepsToDraw);
        }
        hangmanImage.setImageDrawable(hangmanSketch);
    }

    private void gameOver() {
        gamesLost += 1;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog//.setTitle( "Alert" ).setIcon(R.drawable.ic_new_game_dark) // no need for title and icon
                .setMessage(getString(R.string.lostAlert01) + " " + solution + ".\n" + getString(R.string.lostAlert02))
                .setNegativeButton(R.string.negativeResponse, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(R.string.positiveResponse, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startNewGame();
                    }
                }).show();
        disableQwerty(); // if a user clicks outside the dialog the onClick-method will not run, put code outside the dialog
    }

    private void gameWon() {
        gamesWon += 1;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog//.setTitle( "Alert" ).setIcon(R.drawable.ic_new_game_dark) // no need for title and icon
                .setMessage(R.string.winAlert)
                .setNegativeButton(R.string.negativeResponse, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(R.string.positiveResponse, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startNewGame();
                    }
                }).show();
        disableQwerty(); // if a user clicks outside the dialog the onClick-method will not run, put code outside the dialog
    }


    public boolean guessLetter(char c) {
        StringBuilder newGuessString = new StringBuilder(currentGuessString);
        boolean found = false;
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == Character.toLowerCase(c)) { // if the letter was right, replace the _ with the char
                newGuessString.setCharAt(i, c);
                found = true;
            }
        }
        currentGuessString = newGuessString.toString();
        updateLblGuess();
        return found;
    }

    private void updateLblGuess() {
        lblGuess.setText(addSpaceBetweenChar(currentGuessString));
    }

    private void updateLblStats() {
        lblGamesPlayed.setText(getString(R.string.gamesPlayed) + gamesPlayed);
        lblGamesWon.setText(getString(R.string.gamesWon) + gamesWon);
        lblGamesLost.setText(getString(R.string.gamesLost) + gamesLost);
    }

    private String addSpaceBetweenChar(String word) {
        StringBuilder wordWithSpaces = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            wordWithSpaces.append(word.charAt(i)); // append each letter
            wordWithSpaces.append(" "); // add a space between them
        }
        return wordWithSpaces.toString().trim(); // convert to string, remove the last space, return it
    }

    private void addLettersToLetterLayout(String lettersToAdd) {
        // For each word in the alphabet
        LinearLayout row = null;
        int[] rowAfterThisIndex;

        if (locale.getCountry().toLowerCase().equals("no")) {rowAfterThisIndex = new int[]{0, 11, 22}; // norwegian qwerty layout
        } else {rowAfterThisIndex = new int[]{0, 10, 19};} // english qwerty layout

        for (int i = 0; i < lettersToAdd.length(); i++) {
            if (Arrays.binarySearch(rowAfterThisIndex, i) >= 0) { // checks if this index is the start of a new row
                row = new LinearLayout(this);
                row.setOrientation(LinearLayout.HORIZONTAL);
                row.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, // raden tar hele plassen i bredden
                                LinearLayout.LayoutParams.WRAP_CONTENT) // wrapper content i høyden
                );
                row.setGravity(Gravity.CENTER); // Sentrerer raden
                row.setWeightSum(1); // Plasserer raden på bunnen av siden

                mainContainer.addView(row);
            }

            char charToAdd = lettersToAdd.charAt(i);
            TextView tvToAdd = new TextView(this); //new TextView(getApplicationContext());
            tvToAdd.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
            );
            tvToAdd.setText(String.valueOf(charToAdd));
            tvToAdd.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            tvToAdd.setGravity(Gravity.CENTER); // tvToAdd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER); // api 17
            int paddingDp = 5;
            float density = getApplicationContext().getResources().getDisplayMetrics().density;
            int paddingPixel = (int)(paddingDp * density);
            tvToAdd.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel); //left, top, right, bottom

            // set onclick listener
            tvToAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) v; // Convert view to TextView
                    String letterToGuess = tv.getText().toString(); // Get the letter inside the TextView
                    if (guessLetter(letterToGuess.charAt(0))) { // Guess the letter and return if currect char
                        tv.setBackgroundColor(Color.GREEN);
                    } else {
                        tv.setBackgroundColor(Color.RED);
                        wrongGuesses += INCREMENT_GUESS;
                    }

                    tv.setClickable(false);
                    updateGame();
                }
            });
            qwerty.add(tvToAdd);
            row.addView(tvToAdd);
        }
    }

    public void updatePref() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Get all pref-keys and default values
        String language_key = getString(R.string.language_key);
        String default_language = getString(R.string.default_language);

        String difficulty_key = getString(R.string.difficulty_key);
        String default_difficulty = getString(R.string.default_difficulty);

        String word_category_key = getString(R.string.word_category_key);
        String default_word_category = getString(R.string.default_word_category);

        // Get the values from the SharedPreferences-object pref
        String language = pref.getString(language_key, default_language);
        //if (language.equals("english")) //set english
        //else if (language.equals("norwegian")) // set norwegian

        String difficulty = pref.getString(difficulty_key, default_difficulty);
        if (difficulty.equals("easy")) INCREMENT_GUESS = 1; // max 10 wrongGuesses
        else if (difficulty.equals("medium")) INCREMENT_GUESS = 2; // max 5 wrongGuesses
        else if (difficulty.equals("hard")) INCREMENT_GUESS = 4; // max 3 wrongGuesses

        String word_category = pref.getString(word_category_key, default_word_category);
        if (word_category.equals("carbrands")) currentCategory = carbrands;
        else if (word_category.equals("countries")) currentCategory = countries;
        else if (word_category.equals("cities")) currentCategory = cities;

        // Logger alle settings
        // Log.i("teste", "Språk: " + language + "\n" + "Vanskegrad: " + difficulty + "\n" + "Kategori: " + word_category);
    }
}
