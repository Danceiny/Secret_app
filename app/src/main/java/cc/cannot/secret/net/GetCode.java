package cc.cannot.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import cc.cannot.secret.Config;

/**
 * Created by huangzhen on 4/6/2017.
 */

public class GetCode {

    public GetCode(String phone, SuccessCallback successCallback, final FailCallback failCallback){

        // TODO
        NetConnection netConnection = new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    switch (jsonObject.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            break;
                        default:
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback(){
            @Override
            public void onFail() {
                if(failCallback!=null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUMBER,phone);
    }

    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail();
    }
}
