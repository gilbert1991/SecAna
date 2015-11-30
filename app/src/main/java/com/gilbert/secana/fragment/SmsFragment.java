package com.gilbert.secana.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gilbert.secana.R;
import com.gilbert.secana.adapter.SmsAdapter;
import com.gilbert.secana.data.Sms;
import com.gilbert.secana.sql.SmsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class SmsFragment extends MyBaseFragment {

    private SmsDAO smsDAO;
    private List<Sms> smsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView emptyText;

    public static SmsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SmsFragment fragment = new SmsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        smsDAO = new SmsDAO(getActivity().getApplicationContext());

        if(smsDAO.getCount() != 0) {
            smsList.addAll(smsDAO.getAll());

            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_sms, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_sms);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        SmsAdapter adapter = new SmsAdapter(smsList);
        recyclerView.setAdapter(adapter);

        emptyText = (TextView) fragmentView.findViewById(R.id.tv_empty);

        return fragmentView;
    }
}
