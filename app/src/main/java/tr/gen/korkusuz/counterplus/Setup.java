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


    static Setup setup = null;

    Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Setup(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Setup",0);
        editor = sharedPreferences.edit();
    }

    public static Setup getInstance(Context context){
        if (setup == null){
            setup = new Setup(context);
        }
        return  setup;
    }

    public void saveValues(){
        editor.putInt("currentCount", getCurrentCount());
        editor.putInt("maxLimit",getMaxLimit());
        editor.putInt("minLimit",getMinLimit());
        editor.putBoolean("upperVibration",isUpperVibration());
        editor.putBoolean("upperPlaySound",isUpperPlaySound());
        editor.putBoolean("lowerVibration",isLowerVibration());
        editor.putBoolean("lowerPlaySound",isLowerPlaySound());
        editor.commit();
    }
    public void readValues(){
        setCurrentCount(sharedPreferences.getInt("currentCount",0));
        setMaxLimit(sharedPreferences.getInt("maxLimit",0));
        setMinLimit(sharedPreferences.getInt("minLimit",0));
        setUpperVibration(sharedPreferences.getBoolean("upperVibration",true));
        setUpperPlaySound(sharedPreferences.getBoolean("upperPlaySound",true));
        setLowerVibration(sharedPreferences.getBoolean("lowerVibration",true));
        setLowerPlaySound(sharedPreferences.getBoolean("lowerPlaySound",true));
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
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
