package org.aja.flightmode;

import android.util.Log;

/**
 * Log wrapper to behave according to isLoggable()
 */
public class PLog {

  // any priority
  public static void println(int priority, String tag, String msg) {
    if (Log.isLoggable(tag, priority)) { Log.println(priority, tag, msg); }
  }

  // DEBUG
  public static void d(String tag, String msg) {
    if (Log.isLoggable(tag, Log.DEBUG)) { Log.d(tag, msg); }
  }
  public static void d(String tag, String msg, Throwable tr) {
    if (Log.isLoggable(tag, Log.DEBUG)) { Log.d(tag, msg, tr); }
  }

  // ERROR
  public static void e(String tag, String msg) {
    if (Log.isLoggable(tag, Log.ERROR)) { Log.e(tag, msg); }
  }
  public static void e(String tag, String msg, Throwable tr) {
    if (Log.isLoggable(tag, Log.ERROR)) { Log.e(tag, msg, tr); }
  }

  // INFO
  public static void i(String tag, String msg) {
    if (Log.isLoggable(tag, Log.INFO)) { Log.i(tag, msg); }
  }
  public static void i(String tag, String msg, Throwable tr) {
    if (Log.isLoggable(tag, Log.INFO)) { Log.i(tag, msg, tr); }
  }

  // VERBOSE
  public static void v(String tag, String msg) {
    if (Log.isLoggable(tag, Log.VERBOSE)) { Log.v(tag, msg); }
  }
  public static void v(String tag, String msg, Throwable tr) {
    if (Log.isLoggable(tag, Log.VERBOSE)) { Log.v(tag, msg, tr); }
  }

  // WARN
  public static void w(String tag, String msg) {
    if (Log.isLoggable(tag, Log.WARN)) { Log.w(tag, msg); }
  }
  public static void w(String tag, String msg, Throwable tr) {
    if (Log.isLoggable(tag, Log.WARN)) { Log.w(tag, msg, tr); }
  }
  public static void w(String tag, Throwable tr) {
    if (Log.isLoggable(tag, Log.WARN)) { Log.w(tag, tr); }
  }

  // WTF needs API level 8
//  public static void wtf(String tag, String msg) {
//    if (Log.isLoggable(tag, Log.ASSERT)) { Log.wtf(tag, msg); }
//  }
//  public static void wtf(String tag, String msg, Throwable tr) {
//    if (Log.isLoggable(tag, Log.ASSERT)) { Log.wtf(tag, msg, tr); }
//  }
//  public static void wtf(String tag, Throwable tr) {
//    if (Log.isLoggable(tag, Log.ASSERT)) { Log.wtf(tag, tr); }
//  }

}
