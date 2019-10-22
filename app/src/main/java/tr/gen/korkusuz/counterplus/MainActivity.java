package tr.gen.korkusuz.counterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    MediaPlayer mediaPlayer;

    Setup setup;

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


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<max)
                {
                    counter++;
                    setCounter();
                }else{
                    Toast.makeText(getApplicationContext(),"Üst limite ulaşıldı.",Toast.LENGTH_SHORT).show();
                    if(setup.isUpperPlaySound()){
                        playSound();
                    }
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter>min) {
                    counter--;
                    setCounter();
                }else{
                    Toast.makeText(getApplicationContext(),"Alt limite ulaşıldı.",Toast.LENGTH_SHORT).show();
                    if(setup.isLowerPlaySound()){
                        playSound();
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

    private void playSound(){
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
}
