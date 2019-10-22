package tr.gen.korkusuz.counterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetupActivity extends AppCompatActivity {

    Setup setup = null;

    public static String TAG = "SETUPACTIVITY";

    // Arayüz deklarasyonları
    Button upMinus;
    Button upPlus;
    EditText upText;
    CheckBox upSound;
    CheckBox upVib;

    Button lowMinus;
    Button lowPlus;
    EditText lowText;
    CheckBox lowSound;
    CheckBox lowVib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setup = Setup.getInstance(getApplicationContext());

        upMinus = findViewById(R.id.btn_ust_minus);
        upPlus = findViewById(R.id.btn_ust_plus);
        upText = findViewById(R.id.et_ust);
        upSound = findViewById(R.id.cb_ust_ses);
        upVib = findViewById(R.id.cb_ust_titresim);
        lowMinus = findViewById(R.id.btn_alt_minus);
        lowPlus = findViewById(R.id.btn_alt_plus);
        lowText = findViewById(R.id.et_alt);
        lowSound = findViewById(R.id.cb_alt_ses);
        lowVib = findViewById(R.id.cb_alt_titresim);

        setup.readValues();
        setVariables();

        upPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = Integer.valueOf(upText.getText().toString());
                v++;
                upText.setText(String.valueOf(v));
            }
        });

        lowMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = Integer.valueOf(lowText.getText().toString());
                v--;
                lowText.setText(String.valueOf(v));
            }
        });

        lowPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = Integer.valueOf(lowText.getText().toString());
                v++;
                if (v < Integer.valueOf(upText.getText().toString())) {
                    lowText.setText(String.valueOf(v));
                } else {
                    Toast.makeText(getApplicationContext(), "Maximum değerden büyük olamaz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        upMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = Integer.valueOf(upText.getText().toString());
                v--;
                if (v > Integer.valueOf(lowText.getText().toString())) {
                    upText.setText(String.valueOf(v));
                } else {
                    Toast.makeText(getApplicationContext(), "Minimum değerden küçük olamaz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setVariables() {
        upText.setText(String.valueOf(setup.getMaxLimit()));
        lowText.setText(String.valueOf(setup.getMinLimit()));
        upSound.setChecked(setup.isUpperPlaySound());
        upVib.setChecked(setup.isUpperVibration());
        lowSound.setChecked(setup.isLowerPlaySound());
        lowVib.setChecked(setup.isLowerVibration());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup.readValues();
        Log.d(TAG, "onResume: read values");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: save values");
        setup.setMaxLimit(Integer.valueOf(upText.getText().toString()));
        setup.setMinLimit(Integer.valueOf(lowText.getText().toString()));
        setup.setUpperPlaySound(upSound.isChecked());
        setup.setUpperVibration(upVib.isChecked());
        setup.setLowerPlaySound(lowSound.isChecked());
        setup.setLowerVibration(lowVib.isChecked());

        setup.saveValues();
    }
}
