package cc.cannot.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by huangzhen on 4/5/2017.
 */

public class Config {
    public static final String APP_ID = "cc.cannot.secret";
    public static final String CHARSET = "utf-8";
    public static final String SERVER_URL = "192.168.136.2:3333/secret/api/v1/";

    /* ----------key begin ----------- */
    public static final String KEY_TOKEN = "token";
    public static final String KEY_PHONE_NUMBER = "phone";
    public static final String KEY_PHONE_MD5 = "phone_md5";
    public static final String KEY_STATUS = "status";
    public static final String KEY_CODE = "code";
    public static final String KEY_CONTACTS = "contatcs";
    public static final String KEY_PAGE = "page";
    public static final String KEY_PERPAGE = "perpage";
    public static final String KEY_TIMELINE = "items";
    public static final String KEY_MSG_ID = "msgId";
    public static final String KEY_MSG = "msg";
    public static final String KEY_COMMENTS = "items";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_ACTION = "action" ;
    /* ----------key begin ----------- */


    /* -----------------result status begin-------------*/
    public static final int RESULT_STATUS_FAIL = 0;
    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUS_INVALID_TOKEN = 2;
    /* -----------------result status end-------------*/



    public static final String ACTION_GET_CODE = "send_pass" ;
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_UPLOAD_CONTACTS = "upload_contacts";
    public static final String ACTION_TIMELINE = "timeline";
    public static final String ACTION_GET_COMMENT = "get_comment";
    public static final String ACTION_PUB_COMMENT = "pub_comment";
    public static final String ACTION_PUBLISH = "publish";
    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context, String token) {
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN,token);
        e.commit();
    }


    public static String getCachedPhoneNumber(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_PHONE_NUMBER,null);
    }


}
