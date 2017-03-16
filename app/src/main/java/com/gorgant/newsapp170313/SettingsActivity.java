package com.gorgant.newsapp170313;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static android.R.attr.value;

/**
 * Created by Ludeyu on 3/14/2017.
 */

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class NewsArticlePreferenceFragment extends PreferenceFragment implements
            Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Load settings content from the settings_main xml, which designates the various prefs
            addPreferencesFromResource(R.xml.settings_main);

            //Stores the section preference
            Preference section = findPreference(getString(R.string.settings_section_key));
            //Binds the value for display in the preference screen (I think)
            bindPreferenceSummaryToValue(section);

            //Stores the orderBy preference
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            //Binds the value for display in the preference screen (I think)
            bindPreferenceSummaryToValue(orderBy);


        }

        /**
         * Called when a Preference has been changed by the user. This is
         * called before the state of the Preference is about to be updated and
         * before the state is persisted.
         *
         * @param preference The changed Preference.
         * @param newValue   The new value of the Preference.
         * @return True to update the state of the Preference with the new value.
         */
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            //This pulls from the settings_main preferences xml, which in turn pulls from the arrays,
            //in order to display the preference summary on the preference screen (I think)
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        /**A helper method by Udacity that allows the preference value to be displayed in the
         * settings_main screen
         *
         * @param preference The preference for which a value will be displayed
         */
        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}
