package com.gilbert.secana.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gilbert.secana.R;
import com.gilbert.secana.adapter.CallAdapter;
import com.gilbert.secana.data.Call;
import com.gilbert.secana.sql.CallDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class CallFragment extends MyBaseFragment {

    List<Call> callList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView emptyText;

    public static CallFragment newInstance() {

        Bundle args = new Bundle();

        CallFragment fragment = new CallFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_call, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_call);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        CallAdapter adapter = new CallAdapter(callList);
        recyclerView.setAdapter(adapter);

        emptyText = (TextView) fragmentView.findViewById(R.id.tv_empty);

        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        CallDAO callDAO = new CallDAO(getActivity().getApplicationContext());

        if(callDAO.getCount() != 0) {
            callList.addAll(callDAO.getAll());

            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }
}
