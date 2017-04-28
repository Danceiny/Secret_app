package cc.cannot.secret.ld;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cc.cannot.secret.Config;
import cc.cannot.secret.utils.MD5Tool;

/**
 * Created by huangzhen on 4/12/2017.
 */

public class MyContacts {
   public static String getContactsJSONString(Context context){
       Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
       String phone_number;
       JSONArray jsonArray = new JSONArray();
       JSONObject jsonObject;
       while (cursor.moveToNext()){
           phone_number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
           if (phone_number.charAt(0)=='+'&&phone_number.charAt(1)=='8'&&phone_number.charAt(2)=='6'){
               phone_number = phone_number.substring(3);
           }
           jsonObject = new JSONObject();
           try {
               jsonObject.put(Config.KEY_PHONE_MD5, MD5Tool.md5(phone_number));
           }catch (JSONException e){
               e.printStackTrace();
           }
           jsonArray.put(jsonObject);
       }
       return jsonArray.toString();
   }
}
