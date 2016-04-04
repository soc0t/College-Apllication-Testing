package comepqtesting.wix.socot.epqtesting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Scott on 3/30/2016.
 */
public class Alarm_Reciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver.", "Yay!");

        // fetch extra strings from the intent
        String get_your_string = intent.getExtras().getString("extra");
        Log.e("What is the key?", get_your_string);


        //create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from AlarmClock to RingtonePlayingService
        service_intent.putExtra("extra", get_your_string);
        //start the ringtone service
        context.startService(service_intent);

    }
}
