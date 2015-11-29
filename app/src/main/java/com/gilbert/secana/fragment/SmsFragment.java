package com.gilbert.secana.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public static SmsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SmsFragment fragment = new SmsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        smsDAO = new SmsDAO(getActivity().getApplicationContext());
        setSmsList();

        if(smsDAO.getCount() != 0) {
            smsList = smsDAO.getAll();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_sms, container, false);

        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_sms);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        SmsAdapter adapter = new SmsAdapter(smsList);
        recyclerView.setAdapter(adapter);

        return fragmentView;
    }

    private void setSmsList() {
//        for (int i = 0; i < 10; i++) {
//            Sms sms = new Sms();
//            sms.number = 919932;
//            sms.date = "2/11/2015";
//            sms.reason = "fdsgagfdsafhjdsahlfhdjsalghjdlsafhdjsalgasd";
//
//            smsList.add(sms);
//        }

        Sms sms1 = new Sms();
        sms1.number = "919292";
        sms1.date = "28/11015";
        sms1.content = "weishenme";
        sms1.reason = "fdaaaaad";

        Sms sms2 = new Sms();
        sms2.number = "917929932";
        sms2.date = "28/11/015";
        sms2.content = "weisdsdse";
        sms2.reason = "fddddddgasd";

        Sms sms3 = new Sms();
        sms3.number = "7929932";
        sms3.date = "28/11/215";
        sms3.content = "keyima";
        sms3.reason = "fdsglghjdlsafhdjsalgasd";

        smsDAO.insert(sms1);
        smsDAO.insert(sms2);
        smsDAO.insert(sms3);
    }
}
