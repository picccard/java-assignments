package com.example.eskil.assignment07;


import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;

/*
    Author:     Eskil Uhlving Larsen
    Date:       31.10.2018
    Title:      SettingsActivity.java
*/

// https://www.androidhive.info/2017/07/android-implementing-preferences-settings-screen/
// https://www.youtube.com/watch?v=wY64_9Gp8_0

// more @ https://www.youtube.com/watch?v=jzpI9vdeV1A



public class SettingsActivity extends PreferenceActivity {

    private final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Old fragmentManager
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new MainSettingsFragment()).commit();

    }


    public static class MainSettingsFragment extends PreferenceFragment { // Old PreferenceFragment
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Gets the existing preferences
            addPreferencesFromResource(R.xml.pref);
            // ListView settings
            bindSummaryValue(findPreference("key_listview_bgcolor"));

            // Other settings
            bindSummaryValue(findPreference("key_full_name"));
            bindSummaryValue(findPreference("key_email"));
            bindSummaryValue(findPreference("key_sleep_timer"));
            bindSummaryValue(findPreference("key_notification_ringtone"));
        }
    }

    // Update the setting summary when the setting changes
    private static void bindSummaryValue(Preference preference) {
        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

            // ListPreference
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                preference.setSummary(index >= 0
                        ? listPreference.getEntries()[index]
                        : null);

            // EditTextPreference
            } else if (preference instanceof EditTextPreference) {
                preference.setSummary(stringValue);

            // RingtonePreference
            } else if (preference instanceof RingtonePreference) {
                if (TextUtils.isEmpty(stringValue)) {
                    preference.setSummary("Silent");
                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue));
                    if (ringtone == null) {
                        // Clear the summary
                        preference.setSummary("Choose different ringtone");
                    } else {
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }
            }
            return true;
        }
    };
}
