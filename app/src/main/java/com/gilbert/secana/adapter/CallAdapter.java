package com.gilbert.secana.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilbert.secana.R;
import com.gilbert.secana.data.Call;

import java.util.ArrayList;
import java.util.List;

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
            numberText.setText(call.number + "8");
            dateText.setText(call.date);
            reasonText.setText(call.reason);
            levelImage.setImageResource(R.drawable.side_nav_bar);
        }
    }
}

