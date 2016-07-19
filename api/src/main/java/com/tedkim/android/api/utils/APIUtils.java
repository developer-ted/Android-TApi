package com.tedkim.android.api.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.tedkim.android.api.config.ErrorType;
import com.tedkim.android.api.interfaces.APIResponseListener;
import com.tedkim.android.api.requestclient.APIRequestManager;
import com.tedkim.android.api.requestclient.RequestClient;
import com.tedkim.android.api.vo.ErrorVO;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import retrofit.Response;

/**
 * API Utils
 * Created by Ted
 */
public class APIUtils {

    public static final String TAG = APIUtils.class.getSimpleName();

    /**
     * API request error
     * response received but request not successful (like 400,401,403,404 etc)
     *
     * @param context  context
     * @param uniqueID API unique ID
     * @param obj      response object
     * @param listener APIResponseListener
     */
    public static void errorResponse(Context context, String uniqueID, Response<Object> obj, APIResponseListener listener) {
        try {
            ErrorVO vo = (ErrorVO) toJsonString(obj.errorBody().string(), ErrorVO.class);
            if (vo != null) {
                if (RequestClient.isLog)
                    Log.e(TAG, "Error code : " + vo.getError().getCode() + ", message : " + vo.getError().getMessage() + " type : " + vo.getError().getType());
                switch (vo.getError().getType()) {
                    // 토큰 만료 오류 시
                    case ErrorType.TOKEN_ACCESS_EXPIRED:
                    case ErrorType.TOKEN_NOT_EXIST:
                        APIRequestManager.getInstance().cancelAllRequest(false);
                        APIRequestManager.getInstance().responseTokenError();
                        // AuthDetail.requestRefreshToken(context);
                        break;

                    default:
                        APIRequestManager.getInstance().removeRequestCall(uniqueID);
                        if (listener != null)
                            listener.getError(vo);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * API request failure
     *
     * @param context  context
     * @param t        error object
     * @param listener api response listener
     */
    public static void errorFailure(Context context, String uniqueID, Throwable t, APIResponseListener listener) {
        if (RequestClient.isLog) Log.e(TAG, "[errorFailure]" + t.getMessage());
        APIRequestManager.getInstance().removeRequestCall(uniqueID);
    }

    /**
     * Get random code
     *
     * @return random code
     */
    public static String getRandomCode() {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < 20; i++) {
            if (rnd.nextBoolean()) {
                buf.append((char) (rnd.nextInt(26) + 97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }

        return String.valueOf(buf);
    }

    /**
     * Get device country
     *
     * @param context context
     * @return country code
     */
    public static String getDeviceCountryCode(Context context) {
        return String.valueOf(context.getResources().getConfiguration().locale.getCountry());
    }

    /**
     * Get device language
     *
     * @param context context
     * @return language code
     */
    public static String getDeviceLanguage(Context context) {
        return String.valueOf(context.getResources().getConfiguration().locale.toString());
    }

    /**
     * Get device timezone offset
     *
     * @return timezone offest
     */
    public static String getCurrentTimezoneOffset() {
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = -mTimeZone.getRawOffset();

        return String.valueOf(TimeUnit.MINUTES.convert(mGMTOffset, TimeUnit.MILLISECONDS));
    }

    /**
     * Get app version code
     *
     * @param context     context
     * @param packageName app package name
     * @return version code
     */
    public static int getVersionCode(Context context, String packageName) {
        int v = 0;
        try {
            v = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Make a vo to json string
     *
     * @param json  json string
     * @param clazz convert class
     * @return vo object
     */
    public static Object toJsonString(String json, Class clazz) {
        Gson gson = new Gson();
        TypeAdapter adapter = gson.getAdapter(clazz);
        try {
            return adapter.fromJson(json);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Make a vo to json object
     *
     * @param object json object
     * @param clazz  convert class
     * @return vo object
     */
    public static Object toJsonObject(Object object, Class clazz) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        return gson.fromJson(jsonObject, clazz);
    }

    /**
     * Make to json
     *
     * @param obj vo object
     * @return Json String
     */
    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
