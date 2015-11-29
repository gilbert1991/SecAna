package com.gilbert.secana.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilbert.secana.R;
import com.gilbert.secana.data.Sms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewHolder> {

    List<Sms> mSmsList = new ArrayList<>();

    public SmsAdapter(List<Sms> SmsList) {
        mSmsList = SmsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sms, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sms sms = mSmsList.get(position);
        sms.content = position + "";
        holder.setHolder(sms);
    }

    @Override
    public int getItemCount() {
        return mSmsList.size();
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

        public void setHolder(Sms sms) {
            numberText.setText(sms.number);
            dateText.setText(sms.date);
            reasonText.setText(sms.reason);
            levelImage.setImageResource(R.drawable.side_nav_bar);
        }
    }
}
