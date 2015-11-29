package com.gilbert.secana.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gilbert.secana.R;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class AboutFragment extends MyBaseFragment {

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);

        return fragmentView;
    }
}
