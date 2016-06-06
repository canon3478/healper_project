package com.example.user.pushtest;

public class BasicInfo {
    /**
     * Project Id registered to use GCM.
     * 단말 등록을 위한 필요한 ID
     */
    public static final String PROJECT_ID = "129474333111";

    /**
     * Google API Key generated for service access
     * 서버 : 푸시 메시지 전송을 위해 필요한 KEY
     */
    public static final String GOOGLE_API_KEY = "AIzaSyB8NoBqD_n9_rKXo4X6UWDfH-FNIKv6ni4";



    /**
     * Registration ID for this device
     * 단말 등록 후 수신한 등록 ID
     */
    public static String RegistrationId = "APA91bGD8-zNdk0giA1_nDCEZaLFottU4acwbwBmpCvBdk4a24fC80l-CVcCuPRbfGU2dGBl6ILY1_NEox_OmvA5ZR3Lo-7Y42V_cCuTYDzx622zN_LGw8UFDsbAVfLuitfFs3fZLFH0";

    /**
     * 서비스에서 액티비티로 토스트 메시지 전송하기 위한 액션 정의
     */
    public static String TOAST_MESSAGE_ACTION = "com.example.user.pushtest.TOAST_MESSAGE";
    
}
