package mattborle.youtubedownloader;

import android.app.Application;
import android.content.Context;


// This whole class' only purpose is to circumvent Android's dimwitted incompatibilities with Java.
// This makes it possible to call context statically by calling MyApplication.getAppContext().
//TODO: This is janky as hell. Please never do this, just use C.
public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}