package dev.paxtan.apptemplate.functions;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GeneralFunctions {
    /** Exits the app.**/
    public static void exitApp(@NonNull Activity activity) {
        activity.moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /** Reloads a fragment. **/
    public static void reloadFragment(@NonNull Fragment target) {
        target.getParentFragmentManager().beginTransaction()
                .detach(target)
                .attach(target).commit();
    }
}
