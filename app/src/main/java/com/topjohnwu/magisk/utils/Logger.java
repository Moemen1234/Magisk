package com.topjohnwu.magisk.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class Logger {

    private static final String LOG_TAG = "Magisk: DH";

    public static void dh(String msg, Object... args) {
        Context context = null;
        try {
            context = getApplicationUsingReflection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (PrefHelper.CheckBool("developer_logging", context)) {
            if (args.length == 1 && args[0] instanceof Throwable) {
                Log.d(LOG_TAG, msg, (Throwable) args[0]);
            } else {
                Log.d(LOG_TAG, String.format(msg, args));
            }
        }
    }

    private static Application getApplicationUsingReflection() throws Exception {
        return (Application) Class.forName("android.app.AppGlobals")
                .getMethod("getInitialApplication").invoke(null, (Object[]) null);
    }
}
