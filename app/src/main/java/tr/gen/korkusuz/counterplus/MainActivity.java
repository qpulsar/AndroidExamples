package tr.gen.korkusuz.counterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSetup;
    Button btnMinus;
    Button btnPlus;
    TextView tvCounter;
    long counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetup = (Button)findViewById(R.id.btn_setup);
        btnMinus = (Button)findViewById(R.id.btn_minus);
        btnPlus = (Button)findViewById(R.id.btn_plus);
        tvCounter = (TextView)findViewById(R.id.tv_counter);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                setCounter();
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                setCounter();
            }
        });

    }
    private void setCounter(){
        tvCounter.setText(String.valueOf(counter));
    }
}
