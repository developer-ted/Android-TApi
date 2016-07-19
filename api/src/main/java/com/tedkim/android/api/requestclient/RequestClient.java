package com.tedkim.android.api.requestclient;

import android.content.Context;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.tedkim.android.api.manager.NativeManager;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Ted
 */
public class RequestClient {

    public static boolean isLog = false;

    private Context mContext;
    private NativeManager mNativeManager;

    public RequestClient(Context context) {
        mContext = context;
        mNativeManager = NativeManager.getInstance();
    }

    /**
     * Retrofit clients that use a common
     *
     * @param clazz service interface
     * @return Retrofit Client
     */
    public Object getClient(Class clazz) {
        OkHttpClient okClient = new OkHttpClient();
        if (isLog) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);
        }

        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(RequestHeaders.getInstance(mContext).getCommonHeader(chain));
            }
        });

        Retrofit client = new Retrofit.Builder()
                .baseUrl(mNativeManager.getBaseUrl(mContext))
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return client.create(clazz);
    }

    /**
     * Retrofit clients that do not use the token
     *
     * @param clazz service interface
     * @return Retrofit Client
     */
    public Object getNotTokenClient(Class clazz) {
        OkHttpClient okClient = new OkHttpClient();
        if (isLog) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);
        }

        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(RequestHeaders.getInstance(mContext).getNotTokenHeader(chain));
            }
        });

        Retrofit client = new Retrofit.Builder()
                .baseUrl(mNativeManager.getBaseUrl(mContext))
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return client.create(clazz);
    }

    /**
     * Retrofit clients that use the File Upload
     *
     * @param clazz service interface
     * @return Retrofit Client
     */
    public Object getFileUploadClient(Class clazz) {
        OkHttpClient okClient = new OkHttpClient();
        if (isLog) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);
        }

        okClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(RequestHeaders.getInstance(mContext).getFileUploadHeader(chain));
            }
        });

        Retrofit client = new Retrofit.Builder()
                .baseUrl(mNativeManager.getBaseUrl(mContext))
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return client.create(clazz);
    }

}
