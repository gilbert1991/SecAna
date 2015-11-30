package com.gilbert.secana.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.gilbert.secana.R;
import com.gilbert.secana.activity.MainActivity;
import com.gilbert.secana.data.Call;
import com.gilbert.secana.filter.SpamFilter;
import com.gilbert.secana.sql.CallDAO;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class IncomeCall extends BroadcastReceiver {

    static MyPhoneStateListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if(listener == null) {
                listener = new MyPhoneStateListener(context);
                TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
            }

        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        private Context mContext;
        public MyPhoneStateListener(Context context) {
            mContext = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            if (state == TelephonyManager.CALL_STATE_RINGING) {
                Call call = new Call();
                call.number = incomingNumber;
                call.date = Calendar.getInstance(Locale.US).getTimeInMillis();

                call.level = SpamFilter.CallFilter(call);
                CallDAO callDAO = new CallDAO(mContext.getApplicationContext());
                callDAO.insert(call);
                callDAO.close();

                NotificationCompat.Builder builder = initNotification(mContext, call);
                ((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, builder.build());
            }
        }
    }

    public static NotificationCompat.Builder initNotification(Context context, Call call) {
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
                        .setContentTitle(call.number)
                        .setContentText(call.reason)
                        .setContentIntent(pendingIntent);

        return nBuilder;
    }
}
