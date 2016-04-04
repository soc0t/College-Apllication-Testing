package comepqtesting.wix.socot.epqtesting;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;


public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

      //fetch the extra string values
      String state = intent.getExtras().getString("extra");
      //this converts the extra strings from the intent
      //to start IDs, values 0 or 1
      Log.e("Ringtone extra is", state);


      // notification
      //set up the notification service
      NotificationManager notify_manager = (NotificationManager)
              getSystemService(NOTIFICATION_SERVICE);

              //set up an intent that goes to the AlarmClock activity
      Intent intent_alarm_clock = new Intent(this.getApplicationContext(), AlarmClock.class);
      // set up a pending intent
      PendingIntent pending_intent_alarm_clock = PendingIntent.getActivity(this, 0,
              intent_alarm_clock, 0);

      //make the notification parameters

      Notification notification_popup = new Notification.Builder (this)
              .setContentTitle("Wake up!")
              .setContentText("Click me!")
              .setContentIntent(pending_intent_alarm_clock)
              .setSmallIcon(R.drawable.notification_icon)
              .setAutoCancel(true)
              .build();

      //set up notification start command
      notify_manager.notify(0, notification_popup);


      //this converts the extra strings from the intent
      //to start IDs, values 0 or 1
      assert state != null;
      switch (state) {
          case "alarm on":
              startId = 1;
              break;
          case "alarm off":
              startId = 0;
              break;
          default:
              startId = 0;

              break;
      }


      //if else statements

      //if there is no music playing, and the user pressed "alarm on"
      //music should start playing
      if (!this.isRunning && startId == 1) {
          Log.e("there is no music, ", "and you want start");
          //create an instance of the media player
          media_song = MediaPlayer.create(this, R.raw.iphone_alarm);
          //start the ringtone
          media_song.start();

          this.isRunning = true;
          this.startId = 0;


      }

      //if there is music playing, and user pressed "alarm off"
      //music should stop playing
      else if(this.isRunning && startId == 0){
          Log.e("there is music, ", "and you want end");

          //stop the ringtone
          media_song.stop();;
          media_song.reset();

          this.isRunning = false;
          this.startId = 0;


      }

      //if the user pressed random buttons
      //just to bug-proof app
      //if there is no music playing, and user pressed "alarm off"
      //do nothing
      else if (!this.isRunning && startId == 0){
          Log.e("there is no music, ", "and you want end");

          this.isRunning = false;
          this.startId = 0;

      }

      //if there is music playing and user pressed "alarm on"
      //do nothing
      else if(this.isRunning && startId == 1){
          Log.e("there is music, ", "and you want end");
          this.isRunning = true;
                  this.startId =1;

      }

      //can't think of anything else, just to catch odd event
      else {
          Log.e("else", "somehow you reach this");

      }




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
    // Tell the user we stopped.

        Log.e("on Destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;


    }





}
