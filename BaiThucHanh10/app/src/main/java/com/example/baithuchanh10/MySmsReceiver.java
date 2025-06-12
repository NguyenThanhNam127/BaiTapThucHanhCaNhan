package com.example.baithuchanh10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.util.Log;


public class MySmsReceiver extends BroadcastReceiver {

    private static final String TAG = "MySmsReceive";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: SMS Received!");
        processReceive(context, intent);
    }

    public void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";

        if (extras != null) {
            Object[] smsExtra = (Object[]) extras.get("pdus");
            if (smsExtra != null) {
                Log.d(TAG, "processReceive: pdus found, count: " + smsExtra.length);
                for (int i = 0; i < smsExtra.length; ++i) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                    body = sms.getMessageBody();
                    address = sms.getOriginatingAddress();
                    message += "Có 1 tin nhắn từ " + address + "\n" + body + " vừa gởi đến\n";
                    Log.d(TAG, "processReceive: From: " + address + ", Body: " + body);
                }
                Toast.makeText(context, message.trim(), Toast.LENGTH_LONG).show();
            } else {
                Log.d(TAG, "processReceive: pdus array is null.");
            }
        } else {
            Log.d(TAG, "processReceive: extras is null.");
        }
    }

}