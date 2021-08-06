package com.supremvanam.supremvanam_comp304sec002_lab6_ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

// Team Members:
// Suprem Gnanadeep Stitha Pragna Vanam (301177430)
// Rushitaben Shaileshbhai Patel (301178024)

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = "SMS from ";
        //---get the SMS message passed in---
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage message : smsMessages) {
                // Do whatever you want to do with SMS.
                //---get the sender address/phone number---
                str += message.getOriginatingAddress();
                str += ": ";
                //---get the message body---
                str += message.getMessageBody().toString();

            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }

        Log.d("SMSReceiver", str);

        //---stop the SMS message from broadcasting---
        this.abortBroadcast();

        //---send a broadcast intent to update the SMS received in the activity---
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("SMS_RECEIVED_ACTION");
        broadcastIntent.putExtra("sms", str);
        context.sendBroadcast(broadcastIntent);

    }
}