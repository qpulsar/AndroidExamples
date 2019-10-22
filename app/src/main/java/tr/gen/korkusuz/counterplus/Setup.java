package tr.gen.korkusuz.counterplus;

import android.content.Context;
import android.content.SharedPreferences;

public class Setup {

    int maxLimit = 10;
    int minLimit = 0;
    int currentCount = 0;

    boolean upperVibration = true;
    boolean upperPlaySound = true;
    boolean lowerVibration = true;
    boolean lowerPlaySound = true;

    final String CURRENT_COUNT = "currentCount";
    final String MAX_LIMIT = "maxLimit";
    final String MIN_LIMIT = "minLimit";
    final String UPPER_VIBRATION = "upperVibration";
    final String UPPER_PLAYSOUND = "upperPlaySound";
    final String LOWER_VIBRATION = "lowerVibration";
    final String LOWER_PLAYSOUND = "lowerPlaySound";


    static Setup setup = null;

    Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Setup(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Setup", 0);
        editor = sharedPreferences.edit();
    }

    public static Setup getInstance(Context context) {
        if (setup == null) {
            setup = new Setup(context);
        }
        return setup;
    }

    public void saveValues() {
        editor.putInt(CURRENT_COUNT, getCurrentCount());
        editor.putInt(MAX_LIMIT, getMaxLimit());
        editor.putInt(MIN_LIMIT, getMinLimit());
        editor.putBoolean(UPPER_VIBRATION, isUpperVibration());
        editor.putBoolean(UPPER_PLAYSOUND, isUpperPlaySound());
        editor.putBoolean(LOWER_VIBRATION, isLowerVibration());
        editor.putBoolean(LOWER_PLAYSOUND, isLowerPlaySound());
        editor.commit();
    }

    public void readValues() {
        setCurrentCount(sharedPreferences.getInt(CURRENT_COUNT, 0));
        setMaxLimit(sharedPreferences.getInt(MAX_LIMIT, 0));
        setMinLimit(sharedPreferences.getInt(MIN_LIMIT, 0));
        setUpperVibration(sharedPreferences.getBoolean(UPPER_VIBRATION, true));
        setUpperPlaySound(sharedPreferences.getBoolean(UPPER_PLAYSOUND, true));
        setLowerVibration(sharedPreferences.getBoolean(LOWER_VIBRATION, true));
        setLowerPlaySound(sharedPreferences.getBoolean(LOWER_PLAYSOUND, true));
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        if (maxLimit > minLimit)
            this.maxLimit = maxLimit;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        if (minLimit < maxLimit)
            this.minLimit = minLimit;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public boolean isUpperVibration() {
        return upperVibration;
    }

    public void setUpperVibration(boolean upperVibration) {
        this.upperVibration = upperVibration;
    }

    public boolean isUpperPlaySound() {
        return upperPlaySound;
    }

    public void setUpperPlaySound(boolean upperPlaySound) {
        this.upperPlaySound = upperPlaySound;
    }

    public boolean isLowerVibration() {
        return lowerVibration;
    }

    public void setLowerVibration(boolean lowerVibration) {
        this.lowerVibration = lowerVibration;
    }

    public boolean isLowerPlaySound() {
        return lowerPlaySound;
    }

    public void setLowerPlaySound(boolean lowerPlaySound) {
        this.lowerPlaySound = lowerPlaySound;
    }
}
