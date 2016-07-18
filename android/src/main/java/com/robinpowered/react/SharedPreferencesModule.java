package com.robinpowered.react;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;

public class SharedPreferencesModule extends ReactContextBaseJavaModule {
  SharedPreferences preferences;

  public SharedPreferencesModule(ReactApplicationContext reactApplicationContext) {
    super(reactApplicationContext);
    preferences = reactApplicationContext.getApplicationContext().getSharedPreferences("location", Context.MODE_PRIVATE);
    System.out.println("SharedPreferencesModule HAS INITIALIZED");
  }

  @Override
  public String getName() {
    return "SharedPreferencesAndroid";
  }

  @ReactMethod
  public void getString(String key, Callback callback) {
    Object value = preferences.getAll().get(key);
    if (value != null) {
      callback.invoke(value.toString());
    } else {
      callback.invoke("");
    }
  }

  @ReactMethod
  public void setString(String key, String value, Promise promise) {
    SharedPreferences.Editor editor = preferences.edit();
    if (value == null) {
      editor.remove(key);
    } else {
      editor.putString(key, value);
    }
    editor.commit();
    promise.resolve("");
  }
}
