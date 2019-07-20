package com.example.timerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timeseekbar;
    TextView textView;
    Button controlButton;
    Boolean TimerIsActive= false;
    CountDownTimer countDownTimer;
public  void resetTimer()
{
    countDownTimer.cancel ();
    timeseekbar.setProgress ( 30 );
    timeseekbar.setEnabled ( true );
    textView.setText ( "0:30" );
    controlButton.setText ( "GO" );
    TimerIsActive=false;
}
    public void updateTimer(int update) {

            int min = ( int ) update / 60;
            int sec = update - min * 60; //all of this update timer vakue first is in onProcessChnage method

            String secound = Integer.toString ( sec );

            if (sec <= 9) {
                secound = "0" + sec;
            }


            textView.setText ( min + ":" + secound );  // Integer.toString (sec)
        }

    public void start(View view)
    {
        if (TimerIsActive == false)
        {
            TimerIsActive=true;
            timeseekbar.setEnabled ( false );
            controlButton.setText ( "stop" );

         countDownTimer  = new CountDownTimer ( timeseekbar.getProgress () * 1000 + 100 , 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer ( ( int ) millisUntilFinished / 1000 );

            }

            @Override
            public void onFinish() {
                resetTimer ();
                MediaPlayer mediaPlayer = MediaPlayer.create ( getApplicationContext () , R.raw.sound );
                mediaPlayer.start ();
            }
        }.start ();
    }
        else {
            resetTimer ();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

    controlButton=(Button )findViewById ( R.id.btn );
    textView=  (TextView )findViewById ( R.id.tv );
      timeseekbar  =(SeekBar)findViewById ( R.id. seekbar);

        timeseekbar.setMax ( 600 );  // this is 10 min......10*60sec = 600 sec mens 10 min
        timeseekbar.setProgress ( 30 ); //30 sec

        timeseekbar.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar , int progress , boolean fromUser)
            {

                   updateTimer ( progress );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

    }

}
