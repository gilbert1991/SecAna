package com.gilbert.secana.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gilbert.secana.R;
import com.gilbert.secana.adapter.CallAdapter;
import com.gilbert.secana.data.Call;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class CallFragment extends MyBaseFragment {

    List<Call> callList = new ArrayList<>();

    public static CallFragment newInstance() {

        Bundle args = new Bundle();

        CallFragment fragment = new CallFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_call, container, false);

        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_call);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        CallAdapter adapter = new CallAdapter(callList);
        recyclerView.setAdapter(adapter);

        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCallList();
    }

    private void setCallList() {
        for (int i = 0; i < 10; i++) {
            Call call = new Call();
            call.number = 917929123;
            call.date = "29/11/2015";
            call.reason = "fdsgagfdlsafhdjsalgasd";

            callList.add(call);
        }
    }
}
