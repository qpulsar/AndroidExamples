package tr.gen.korkusuz.counterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SetupActivity extends AppCompatActivity {

    Setup setup = null;

    public static String TAG = "SETUPACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setup= Setup.getInstance(getApplicationContext());
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
        setup.saveValues();
    }
}
