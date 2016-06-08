package com.example.user.pushtest;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import static com.example.user.pushtest.BasicInfo.PROJECT_ID;
import static com.example.user.pushtest.BasicInfo.TOAST_MESSAGE_ACTION;


public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	
	/**
	 * Constructor
	 */
    public GCMIntentService() {
        super(PROJECT_ID);

        Log.d(TAG, "GCMIntentService() called.");
    }


    private static void generateNotification(Context context, String message) {

        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();


        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(icon, message, when);

        String title = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);

        // notification.setLatestEventInfo(context, title, message, intent);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(intent)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTicker("Correct your poture!");
        notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

    }

    @Override
    public void onRegistered(Context context, String registrationId) {
    	Log.d(TAG, "onRegistered called : " + registrationId);

    	BasicInfo.RegistrationId = registrationId;

    	sendToastMessage(context, "단말이 등록되어 등록 ID를 받았습니다.");
    }

    @Override
    public void onUnregistered(Context context, String registrationId) {
    	Log.d(TAG, "onUnregistered called.");

    	sendToastMessage(context, "등록해지되었습니다.");
    }

    @Override
    public void onError(Context context, String errorId) {
    	Log.d(TAG, "onError called.");

    	sendToastMessage(context, "에러입니다 : : " + errorId);
    }

    @Override
	protected void onDeletedMessages(Context context, int total) {
    	Log.d(TAG, "onDeletedMessages called.");
    	
    	super.onDeletedMessages(context, total);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		Log.d(TAG, "onRecoverableError called.");
		
		return super.onRecoverableError(context, errorId);
	}

	@Override
    public void onMessage(Context context, Intent intent) {
    	Log.d(TAG, "onMessage called.");

        Bundle extras = intent.getExtras();
        if (extras != null) {
            String msg = (String) extras.get("msg");
            String from = (String) extras.get("from");
            String action = (String) extras.get("action");

            Log.d(TAG, "DATA : " + from + ", " + action + ", " + msg);
            Log.d(TAG, "[" + from + "]로부터 수신한 메세지 : " + msg);

            Intent newIntent = new Intent(getBaseContext(), MainActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
            newIntent.putExtra("msg", msg);
            newIntent.putExtra("from", from);
            newIntent.putExtra("action", action);
            context.startActivity(newIntent);

            sendToastMessage(context, "서버로부터 메시지를 받았습니다.");
            generateNotification(context,msg);
        }

    }

	/**
	 * Send status messages for toast display
	 * 
	 * @param context
	 * @param message
	 */
	static void sendToastMessage(Context context, String message) {
        Intent intent = new Intent(TOAST_MESSAGE_ACTION);
        intent.putExtra("message", message);
        context.sendBroadcast(intent);
    }
    
}