package com.tedkim.android.api.manager;

import android.content.Context;

import com.tedkim.android.api.R;

/**
 * Import a class is API Config
 * Created by ted
 */
public class NativeManager {

    private static NativeManager mInstance;

    public static NativeManager getInstance() {
        if (mInstance == null) {
            mInstance = new NativeManager();
        }
        return mInstance;
    }

    /**
     * Get base api url
     *
     * @return base api rul
     */
    public String getBaseUrl(Context context) {
        return context.getString(R.string.api_base_url);
    }

    /**
     * Get api client id
     *
     * @return client id key
     */
    public String getClientID(Context context) {
        return context.getString(R.string.api_client_id);
    }

    /**
     * Get api client secret
     *
     * @return client secret key
     */
    public String getClientSecret(Context context) {
        return context.getString(R.string.api_client_secret);
    }

}