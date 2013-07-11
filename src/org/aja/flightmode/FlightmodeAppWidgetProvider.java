package org.aja.flightmode;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.RemoteViews;

public class FlightmodeAppWidgetProvider extends AppWidgetProvider {
  static final String TAG = "org.aja.flightmode";
  static void d(String logstring) { PLog.d(TAG, logstring); };

  static final String TOGGLE_REQUEST = "org.aja.flightmode.ACTION_TOGGLE_REQUEST";


  /**
   * Receives and processes a button pressed intent or state change.
   */
  @Override
    public void onReceive(Context cntxt, Intent ntnt) {
      d("onReceive(cntxt, ntnt)  (ntnt action is " + ntnt.getAction() + ")");

      super.onReceive(cntxt, ntnt);
      if (ntnt.getAction().equals(TOGGLE_REQUEST)) {
        toggleFlightmode(cntxt);
      } 
      else if (ntnt.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
        updateWidgets(cntxt); 
      }

    }


  /**
   * Toggles flighmode by changing the corresponding system setting.
   * (No update of the widgets done here: we wait for te broadcast intent
   *  to trigger our onReceive().)
   */
  private void toggleFlightmode(Context cntxt) {
    d("toggleFlightmode(cntxt)");

    // toggle the system setting
    int oldvalue = Settings.System.getInt( cntxt.getContentResolver(),
        Settings.System.AIRPLANE_MODE_ON, 0);
    int newvalue = oldvalue == 1 ? 0 : 1 ;
    Settings.System.putInt( cntxt.getContentResolver(),
        Settings.System.AIRPLANE_MODE_ON, 
        newvalue );

    // broadcast the fact
    Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    intent.putExtra("state", newvalue);
    cntxt.sendBroadcast(intent);

  }


  /**
   * Initialize the widgets. 
   * Called only when instantiating a new widget, as updateMillis="0".
   */
  @Override
    public void onUpdate(Context cntxt, AppWidgetManager a_mgr, int[] ids) {
      d("onUpdate(cntxt, a_mgr, ids)");
      super.onUpdate(cntxt, a_mgr, ids);
      updateWidgets(cntxt);
    }


  /**
   * Update all widgets belonging to this provider.
   */
  public static void updateWidgets(Context cntxt) {
    d("updateWidgets(cntxt)");

    // get the RemoteViews object containing the view objects in the widget layout
    RemoteViews rviews = new RemoteViews(cntxt.getPackageName(), R.layout.main);

    // link a pending intent to the button view
    Intent ntnt = new Intent(TOGGLE_REQUEST);
    PendingIntent pi = PendingIntent.getBroadcast(cntxt,0,ntnt,0);
    rviews.setOnClickPendingIntent(R.id.btn_flightmode, pi);

    // set the right looks for the button
    int status = Settings.System.getInt( cntxt.getContentResolver(),
        Settings.System.AIRPLANE_MODE_ON, 0); 
    int drwbl = R.drawable.ic_flightmode_off;
    if ( status == 1 ) {
      drwbl = R.drawable.ic_flightmode_on;
    };
    rviews.setImageViewResource(R.id.img_flightmode, drwbl);

    // Use rviews to be the RemoteViews object in all AppWidget instances for this provider
    final AppWidgetManager a_mgr = AppWidgetManager.getInstance(cntxt);
    final ComponentName cname = new ComponentName( 
        "org.aja.flightmode",
        "org.aja.flightmode.FlightmodeAppWidgetProvider");
    a_mgr.updateAppWidget(cname, rviews);

  }

}
