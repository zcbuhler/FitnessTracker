package com.z.buhler.fitnesstracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayUserFragment extends Fragment {
    private String mLoggedInUserPrompt;
    private TextView mUserLoggedInTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display_user_fragment, container, false);

        mLoggedInUserPrompt = "Logged In: " + getString(R.string.login_edit_username);

        mUserLoggedInTextView = (TextView) v.findViewById(R.id.display_user_fragment_text);

        mUserLoggedInTextView.setText(mLoggedInUserPrompt);

        return v;
    }

}
