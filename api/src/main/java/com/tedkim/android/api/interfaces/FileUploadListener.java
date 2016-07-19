package com.tedkim.android.api.interfaces;

/**
 * Created by ted
 */
public interface FileUploadListener {

    void onComplete(String photoType, String pictureUrl);
    void onError(int type);

}
