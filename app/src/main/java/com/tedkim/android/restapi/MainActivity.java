package com.tedkim.android.restapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tedkim.android.api.details.MemberDetail;
import com.tedkim.android.api.interfaces.APIResponseListener;
import com.tedkim.android.api.requestclient.RequestClient;
import com.tedkim.android.api.utils.APIUtils;
import com.tedkim.android.api.vo.ErrorVO;
import com.tedkim.android.api.vo.MemberVO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // true : show log, false : hide log
        RequestClient.isLog = true;

        findViewById(R.id.btnGet).setOnClickListener(this);
        findViewById(R.id.btnPost).setOnClickListener(this);
        findViewById(R.id.btnPut).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGet:
                requestGetMember(1);
                break;

            case R.id.btnPost:
                requestPostMember();
                break;

            case R.id.btnPut:
                requestPutMember(1);
                break;

            case R.id.btnDelete:
                requestDeleteMember(1);
                break;
        }
    }

    private void requestGetMember(int memberID) {
        new MemberDetail(this)
                .requestGetMember(memberID)
                .setListener(new APIResponseListener() {
                    @Override
                    public void getData(Object obj) {
                        MemberVO vo = (MemberVO) obj;
                        Log.d(TAG, "success : " + APIUtils.toJson(vo));
                    }

                    @Override
                    public void getError(ErrorVO errorVO) {
                        Log.d(TAG, "error : " + APIUtils.toJson(errorVO));
                    }
                }).build();
    }

    private void requestPostMember() {
        MemberVO vo = new MemberVO();
        vo.setName("ted kim");
        vo.setAge(27);
        vo.setGender("men");

        new MemberDetail(this)
                .requestPostMember(vo)
                .setListener(new APIResponseListener() {
                    @Override
                    public void getData(Object obj) {
                        MemberVO vo = (MemberVO) obj;
                        Log.d(TAG, "success : " + APIUtils.toJson(vo));
                    }

                    @Override
                    public void getError(ErrorVO errorVO) {
                        Log.d(TAG, "error : " + APIUtils.toJson(errorVO));
                    }
                }).build();
    }

    private void requestPutMember(int memberID) {
        MemberVO vo = new MemberVO();
        vo.setName("ted kim");
        vo.setAge(27);
        vo.setGender("men");

        new MemberDetail(this)
                .requestPutMember(memberID, vo)
                .setListener(new APIResponseListener() {
                    @Override
                    public void getData(Object obj) {
                        MemberVO vo = (MemberVO) obj;
                        Log.d(TAG, "success : " + APIUtils.toJson(vo));
                    }

                    @Override
                    public void getError(ErrorVO errorVO) {
                        Log.d(TAG, "error : " + APIUtils.toJson(errorVO));
                    }
                }).build();

    }

    private void requestDeleteMember(int memberID) {
        new MemberDetail(this)
                .requestDeleteMember(memberID)
                .setListener(new APIResponseListener() {
                    @Override
                    public void getData(Object obj) {
                        MemberVO vo = (MemberVO) obj;
                        Log.d(TAG, "success : " + APIUtils.toJson(vo));
                    }

                    @Override
                    public void getError(ErrorVO errorVO) {
                        Log.d(TAG, "error : " + APIUtils.toJson(errorVO));
                    }
                }).build();
    }
}
