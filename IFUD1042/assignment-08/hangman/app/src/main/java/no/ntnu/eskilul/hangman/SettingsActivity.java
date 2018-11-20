package no.ntnu.eskilul.hangman;

/*
    Author:     Eskil Uhlving Larsen
    Date:       08.11.2018
    Title:      SettingsActivity.java
*/

// https://storiesandroid.wordpress.com/2015/10/06/android-settings-using-preference-fragments/


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;

public class SettingsActivity extends AppCompatActivity {
    public static String TAG = "SettingsActiviy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // https://stackoverflow.com/a/37185334
        // Set a back-button in the action-bar, the parent activity is declared in the metadata for the activity in the manifest
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // .hide();
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        public static final String TAG = "SettingsFragment"; // SettingsFragment.class.getSimpleName();

        SharedPreferences sharedPreferences;

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            // add the pref from the xml file
            addPreferencesFromResource(R.xml.pref);
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            //onSharedPreferenceChanged(sharedPreferences, getString(R.string.language_key));
            onSharedPreferenceChanged(sharedPreferences, getString(R.string.difficulty_key));
            onSharedPreferenceChanged(sharedPreferences, getString(R.string.word_category_key));
        }

        @Override
        public void onResume() {
            super.onResume();
            // register the prefChange listener
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // unregister the prefChange listener
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            // Get the preference that changed
            Preference preference = findPreference(key);
            if (preference instanceof ListPreference) { // if the preference is a ListPreference
                ListPreference listPreference = (ListPreference) preference; // cast it to a ListPreference
                int prefIndex = listPreference.findIndexOfValue(sharedPreferences.getString(key, "")); // The index of the key that changed
                // now update the changed value's summary
                preference.setSummary(prefIndex >= 0
                        ? listPreference.getEntries()[prefIndex]
                        : null);
            } else { // Not a ListPreference, update the summary directly
                preference.setSummary(sharedPreferences.getString(key, ""));
            }
        }
    }
}

