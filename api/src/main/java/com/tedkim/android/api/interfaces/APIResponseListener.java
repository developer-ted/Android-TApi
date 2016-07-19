package com.tedkim.android.api.interfaces;


import com.tedkim.android.api.vo.ErrorVO;

/**
 * API response listener
 * Created by Ted
 */
public interface APIResponseListener {

    void getData(String type, Object obj);
    void getError(ErrorVO errorVO);

}
