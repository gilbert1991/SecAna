package com.gilbert.secana.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.gilbert.secana.R;
import com.gilbert.secana.activity.MainActivity;
import com.gilbert.secana.data.Sms;
import com.gilbert.secana.filter.SpamFilter;
import com.gilbert.secana.sql.SmsDAO;

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
                    sms.date = currentMessage.getTimestampMillis();

                    sms.level = SpamFilter.SmsFilter(sms);

                    SmsDAO smsDAO = new SmsDAO(context.getApplicationContext());
                    smsDAO.insert(sms);
                    smsDAO.close();

                    NotificationCompat.Builder builder = initNotification(context, sms);
                    ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, builder.build());
                }
            }

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    public static NotificationCompat.Builder initNotification(Context context, Sms sms) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        new Intent(context, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(sms.number)
                        .setContentText(sms.content)
                        .setContentIntent(pendingIntent);

        return nBuilder;
    }
}
