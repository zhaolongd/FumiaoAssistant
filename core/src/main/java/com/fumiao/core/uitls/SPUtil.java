package com.fumiao.core.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import com.fumiao.core.app.CoreApp;


public class SPUtil {
    public static final String DEFAULT = "sp_info";

    public String preName;


    private SPUtil(String name) {
        preName = name;
    }

    public static SPUtil getInstance() {
        SPUtil utils = new SPUtil(DEFAULT);
        return utils;
    }

    /**
     * <>
     *
     * @param name
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static SPUtil getInstance(String name) {
        SPUtil utils = new SPUtil(name);
        return utils;
    }

    public static SPUtil getInstance(int name) {
        SPUtil utils = new SPUtil(name+"");
        return utils;
    }


    public boolean clean(Context context) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean putString(String key, String value) {
        SharedPreferences settings = CoreApp.getSingle().getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     * name that is not a string
     * @see #getString(Context, String, String)
     */
    public String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public String getString(String key) {
        return getString(CoreApp.getSingle(), key, "");
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a string
     */
    public String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean putInt(String key, int value) {
        SharedPreferences settings = CoreApp.getSingle().getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a int
     * @see #getInt(Context, String, int)
     */
    public int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    public int getInt(String key) {
        return getInt(CoreApp.getSingle(), key, 0);
    }


    /**
     * get int preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a int
     */
    public int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public boolean putLong(String key, long value) {
        SharedPreferences settings = CoreApp.getSingle().getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }


    /**
     * get long preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a long
     * @see #getLong(Context, String, long)
     */
    public long getLong(Context context, String key) {
        return getLong(context, key, 0);
    }

    public long getLong(String key) {
        return getLong(CoreApp.getSingle(), key, 0);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a long
     */
    public long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a float
     * @see #getFloat(Context, String, float)
     */
    public float getFloat(Context context, String key) {
        return getFloat(context, key, 0);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    public float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean putBoolean(String key, boolean value) {
        SharedPreferences settings = CoreApp.getSingle().getSharedPreferences(preName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
     * name that is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }


    public boolean getBoolean(String key) {
        return getBoolean(CoreApp.getSingle(), key, false);
    }


    /**
     * get boolean preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a boolean
     */
    public boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
