package comepqtesting.wix.socot.epqtesting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmClock extends AppCompatActivity {

    // to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        this.context = this ;

        //initialise our alarm manager
         alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // initialise our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        //initialise text update box
        update_text = (TextView) findViewById(R.id.update_text);

        //create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        //create an intent to the Alarm Receiver Class
        final Intent my_intent = new Intent (this.context, Alarm_Reciever.class);

        //initialise start buttons
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        //Create onClick listener to start alarm
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // setting calendar instance with the hour and minute that we picked
                //on the time picker
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // get the string values of the hour and minute
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                //convert the int values to Strings
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);


                if (hour > 12) {
                    //convert 24h time to 12h time
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    //05:5 --> 05:05
                    minute_string = "0" + String.valueOf(minute);
                }




                //method that changes the update text
                set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);

                //put in extra string into my_intent
                //tells the clock I pressed "alarm on" button
                my_intent.putExtra("extra", "alarm on");





                //create a pending intent that delays the intent until specified calendar time
                pending_intent = PendingIntent.getBroadcast(AlarmClock.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);




            }
        });





        //initialise stop button
        Button alarm_off =(Button) findViewById(R.id.alarm_off);
        //create onClick listener to stop alarm

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method that changes the update text
                set_alarm_text("Alarm off!");
                //cancels the alarm
                alarm_manager.cancel(pending_intent);

                //put extra string into my_intent
                //tells clock I pressed "alarm off" button
                my_intent.putExtra("extra", "alarm off");


                //stop the ringtone
                sendBroadcast(my_intent);
            }
        });



    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
