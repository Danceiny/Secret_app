package cc.cannot.secret.activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import cc.cannot.secret.Config;
import cc.cannot.secret.R;
import cc.cannot.secret.ld.MyContacts;
import cc.cannot.secret.net.UploadContacts;

/**
 * Created by huangzhen on 4/5/2017.
 */

public class atyTimeline extends ListActivity {
    private String phone_number;
    private String token;
    private String phone_md5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_timeline);
        phone_number = getIntent().getStringExtra(Config.KEY_PHONE_NUMBER);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = getIntent().getStringExtra(Config.KEY_PHONE_MD5);

        final ProgressDialog pd = ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));

        new UploadContacts(phone_md5,token, MyContacts.getContactsJSONString(this),new UploadContacts.SuccessCallback(){
            @Override
            public void onSuccess(){
                loadMessage();
                pd.dismiss();
            }
        },new UploadContacts.FailCallback(){
            @Override
            public void onFail(int errorCode){
                pd.dismiss();
                if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(atyTimeline.this,atyLogin.class));
                    finish();
                }else{
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage() {
        final ProgressDialog pd = ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
        //new Timeline(phone_md5,token,1,20,new Timeline.Su)
        System.out.println("loadMessage...........");
    }


}
