package android.mobilestudio.chatme;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.mobilestudio.chatme.authentication.splash.SplashScreen;

import android.widget.RemoteViews;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

public class Widget extends AppWidgetProvider implements GetStateOfPerson {

    RemoteViews views;
    Context context;
    int online = 0, offline = 0;
    String OnlineStr = "", OfflineStr = "Offline ";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.context = context;
        for (int i = 0; i < appWidgetIds.length; i++) {
            int currentWidgetId = appWidgetIds[i];
            views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
            Intent intent = new Intent(context, SplashScreen.class);

            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);

            views.setOnClickPendingIntent(R.id.activity_widget, pending);
            appWidgetManager.updateAppWidget(currentWidgetId, views);

            MyJobService.stateOfPerson = this;

            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
            Job myJob = dispatcher.newJobBuilder()
                    .setService(MyJobService.class)
                    .setTag("my-unique-tag")
                    .build();
            dispatcher.mustSchedule(myJob);
        }
    }

    @Override
    public void onGetState(Boolean b) {
        if (b) {
            online++;
            OnlineStr = online + "  Users are  ";
        } else {
            offline++;
            OfflineStr = offline + "  Users are ";
        }
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
        views.setTextViewText(R.id.tv_offline, OfflineStr);
        views.setTextViewText(R.id.tv_online, OnlineStr);
        ComponentName thisWidget = new ComponentName(context, Widget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thisWidget, views);

    }
}
