package cc.cannot.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import cc.cannot.secret.Config;

/**
 * Created by huangzhen on 4/12/2017.
 */

public class UploadContacts {
    public UploadContacts(String phone_md5, String token, String contacts, final SuccessCallback successCallback, final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                //TODO
                try{
                    JSONObject jsonObject = new JSONObject(result);
                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if(failCallback!=null) {

                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                //TODO
            }
        },Config.KEY_ACTION, Config.ACTION_UPLOAD_CONTACTS,Config.KEY_PHONE_MD5);
    }


    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}

