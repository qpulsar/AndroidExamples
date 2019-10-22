package tr.gen.korkusuz.counterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CaptureResult;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnSetup;
    Button btnMinus;
    Button btnPlus;
    TextView tvCounter;
    int min;
    int max;
    long counter = 0;

    Vibrator vibrator = null;

    MediaPlayer mediaPlayer;

    Setup setup;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup = Setup.getInstance(getApplicationContext());
        btnSetup = findViewById(R.id.btn_setup);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        tvCounter = findViewById(R.id.tv_counter);

        setup.readValues();
        tvCounter.setText(String.valueOf(setup.getCurrentCount()));
        min = setup.getMinLimit();
        max = setup.getMaxLimit();

        vibrator = (Vibrator)getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < max) {
                    counter++;
                    setCounter();
                } else {
                    Toast.makeText(getApplicationContext(), "Üst limite ulaşıldı.", Toast.LENGTH_SHORT).show();
                    if (setup.isUpperPlaySound()) {
                        playSound();
                    }
                    if(setup.isUpperVibration()){
                        vibrate();
                    }
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter > min) {
                    counter--;
                    setCounter();
                } else {
                    Toast.makeText(getApplicationContext(), "Alt limite ulaşıldı.", Toast.LENGTH_SHORT).show();
                    if (setup.isLowerPlaySound()) {
                        playSound();
                    }
                    if(setup.isLowerVibration()) {
                        vibrate();
                    }
                }
            }
        });

        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(i);
            }
        });

    }

    private void setCounter() {
        tvCounter.setText(String.valueOf(counter));
    }

    private void playSound() {
        stopSound();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ding);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSound();
            }
        });
        mediaPlayer.start();
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    if (counter < setup.getMaxLimit()) {
                        counter++;
                        setCounter();
                    }
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    if (counter > setup.getMinLimit()) {
                        counter--;
                        setCounter();
                    }
                }
                break;
        }
        //ses düğmelerini işlevini korusun
        //return super.dispatchKeyEvent(event);
        //ses düğmesi artık çalışmasın
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        setup.readValues();
        counter = setup.getCurrentCount();
        tvCounter.setText(String.valueOf(counter));
        min = setup.getMinLimit();
        max = setup.getMaxLimit();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        setup.setCurrentCount((int)counter); //bug
        setup.saveValues();
        super.onPause();
    }

    private void vibrate(){
        if(vibrator.hasVibrator()){
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(100,10));
            }else{
                vibrator.vibrate(250);
            }

        }
    }
}
