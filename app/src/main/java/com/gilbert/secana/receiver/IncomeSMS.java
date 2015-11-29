package com.gilbert.secana.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.gilbert.secana.data.Sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class IncomeSMS extends BroadcastReceiver {

    final String TAG = "SmsReceiver";

    final SmsManager manager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        try {

            if(bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
//                String format = bundle.getString("format");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    Sms sms = new Sms();
                    sms.number = currentMessage.getDisplayOriginatingAddress();
                    sms.content = currentMessage.getMessageBody();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    sms.date = formatter.format(new Date(currentMessage.getTimestampMillis()));

//                    Log.d(TAG, "Number: " + phoneNumber + "\nMessage: " + textBody);
//
//                    Toast.makeText(context, phoneNumber + "\n" + textBody, Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


}
