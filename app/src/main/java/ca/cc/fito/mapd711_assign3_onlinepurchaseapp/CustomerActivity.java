package ca.cc.fito.mapd711_assign3_onlinepurchaseapp;

/* MAPD 711 - Assignment 3 - Online Purchase App */
/* 12/16/2017                                    */
/* Fernando Ito - 300960367                      */
/* Santhosh Damodharan - 300964037               */

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import java.util.List;

public class CustomerActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.preferences, false);
            // Load the preference from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
