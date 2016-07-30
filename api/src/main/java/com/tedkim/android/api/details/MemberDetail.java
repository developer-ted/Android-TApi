package com.tedkim.android.api.details;

import android.content.Context;

import com.tedkim.android.api.interfaces.APIResponseListener;
import com.tedkim.android.api.requestclient.APIRequestManager;
import com.tedkim.android.api.requestclient.APIRequestVO;
import com.tedkim.android.api.requestclient.RequestClient;
import com.tedkim.android.api.services.MemberServices;
import com.tedkim.android.api.utils.APIUtils;
import com.tedkim.android.api.vo.MemberVO;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * API Collection
 * Created by Ted
 */
public class MemberDetail {

    private static final String TAG = MemberDetail.class.getSimpleName();

    private Context mContext;
    private APIRequestManager mAPIRequestManager = null;
    private APIResponseListener mAPIResponseListener = null;

    private APIRequestVO<Object> mRequestItem;
    private String mUniqueID;

    public MemberDetail(Context context) {
        mContext = context;
        mAPIRequestManager = APIRequestManager.getInstance();
        mUniqueID = APIUtils.getRandomCode();
    }

    /**
     * Get member
     */
    public MemberDetail requestGetMember(int id) {
        mRequestItem = new APIRequestVO<>();
        MemberServices service = (MemberServices) new RequestClient(mContext).getClient(MemberServices.class);
        mRequestItem.setCall(service.requestGetMember(id));
        mRequestItem.setCallback(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MemberVO vo = (MemberVO) APIUtils.toJsonObject(response.body(), MemberVO.class);
                    mAPIRequestManager.successResponse(mUniqueID, vo, mAPIResponseListener);
                } else {
                    APIUtils.errorResponse(mContext, mUniqueID, response, mAPIResponseListener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                APIUtils.errorFailure(mContext, mUniqueID, t, mAPIResponseListener);
            }
        });

        return this;
    }

    /**
     * Post member
     */
    public MemberDetail requestPostMember(MemberVO vo) {
        mRequestItem = new APIRequestVO<>();
        MemberServices service = (MemberServices) new RequestClient(mContext).getClient(MemberServices.class);
        mRequestItem.setCall(service.requestPostMember(vo));
        mRequestItem.setCallback(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MemberVO vo = (MemberVO) APIUtils.toJsonObject(response.body(), MemberVO.class);
                    mAPIRequestManager.successResponse(mUniqueID, vo, mAPIResponseListener);
                } else {
                    APIUtils.errorResponse(mContext, mUniqueID, response, mAPIResponseListener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                APIUtils.errorFailure(mContext, mUniqueID, t, mAPIResponseListener);
            }
        });

        return this;
    }

    /**
     * Put member
     */
    public MemberDetail requestPutMember(int id, MemberVO vo) {
        mRequestItem = new APIRequestVO<>();
        MemberServices service = (MemberServices) new RequestClient(mContext).getClient(MemberServices.class);
        mRequestItem.setCall(service.requestPutMember(id, vo));
        mRequestItem.setCallback(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MemberVO vo = (MemberVO) APIUtils.toJsonObject(response.body(), MemberVO.class);
                    mAPIRequestManager.successResponse(mUniqueID, vo, mAPIResponseListener);
                } else {
                    APIUtils.errorResponse(mContext, mUniqueID, response, mAPIResponseListener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                APIUtils.errorFailure(mContext, mUniqueID, t, mAPIResponseListener);
            }
        });

        return this;
    }

    /**
     * Delete member
     */
    public MemberDetail requestDeleteMember(int id) {
        mRequestItem = new APIRequestVO<>();
        MemberServices service = (MemberServices) new RequestClient(mContext).getClient(MemberServices.class);
        mRequestItem.setCall(service.requestDeleteMember(id));
        mRequestItem.setCallback(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MemberVO vo = (MemberVO) APIUtils.toJsonObject(response.body(), MemberVO.class);
                    mAPIRequestManager.successResponse(mUniqueID, vo, mAPIResponseListener);
                } else {
                    APIUtils.errorResponse(mContext, mUniqueID, response, mAPIResponseListener);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                APIUtils.errorFailure(mContext, mUniqueID, t, mAPIResponseListener);
            }
        });

        return this;
    }

    /**
     * Set api response listener
     * @param listener APIResponseListener
     */
    public MemberDetail setListener(APIResponseListener listener) {
        mAPIResponseListener = listener;
        return this;
    }

    /**
     * API Request build
     */
    public MemberDetail build(){
        mAPIRequestManager.addRequestCall(mUniqueID, mRequestItem);
        return this;
    }
}
