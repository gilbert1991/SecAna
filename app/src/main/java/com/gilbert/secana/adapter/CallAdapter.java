package com.gilbert.secana.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilbert.secana.R;
import com.gilbert.secana.data.Call;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {

    List<Call> mCallList = new ArrayList<>();

    public CallAdapter(List<Call> CallList) {
        mCallList = CallList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_call, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Call call = mCallList.get(position);
        holder.setHolder(call);
    }

    @Override
    public int getItemCount() {
        return mCallList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberText, dateText, reasonText;
        ImageView levelImage;

        public ViewHolder(View view) {
            super(view);

            numberText = (TextView) view.findViewById(R.id.tv_number);
            dateText = (TextView) view.findViewById(R.id.tv_date);
            reasonText = (TextView) view.findViewById(R.id.tv_reason);
            levelImage = (ImageView) view.findViewById(R.id.iv_level);
        }

        public void setHolder(Call call) {
            numberText.setText(call.number);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
            dateText.setText(formatter.format(new Date(call.date)));

            reasonText.setText(call.reason);

            if(call.level == 0) {
                levelImage.setImageResource(R.drawable.security_checked_100);
            } else if(call.level == 1) {
                levelImage.setImageResource(R.drawable.question_shield_100);
            } else if(call.level == 2) {
                levelImage.setImageResource(R.drawable.warning_shield_100);
            } else if(call.level == 3) {
                levelImage.setImageResource(R.drawable.danger_shield_100);
            }
        }
    }
}

