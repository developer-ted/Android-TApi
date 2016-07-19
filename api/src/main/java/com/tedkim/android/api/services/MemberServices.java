package com.tedkim.android.api.services;

import com.tedkim.android.api.config.APIAddress;
import com.tedkim.android.api.config.APIParamValue;
import com.tedkim.android.api.vo.MemberVO;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Ted
 */
public interface MemberServices {

    @GET("/TicTocCrocodile/api/v1" + APIAddress.Member + "/{" + APIParamValue.ID + "}")
    Call<Object> requestGetMember(@Path(APIParamValue.ID) int id);

    @POST(APIAddress.Member)
    Call<Object> requestPostMember(@Body MemberVO vo);

    @PUT(APIAddress.Member + "/{" + APIParamValue.ID + "}")
    Call<Object> requestPutMember(@Path(APIParamValue.ID) int id,
                                  @Body MemberVO vo);

    @DELETE(APIAddress.Member + "/{" + APIParamValue.ID + "}")
    Call<Object> requestDeleteMember(@Path(APIParamValue.ID) int id);
}
