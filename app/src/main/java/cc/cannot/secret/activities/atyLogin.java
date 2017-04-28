package cc.cannot.secret.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import cc.cannot.secret.R;
import cc.cannot.secret.net.GetCode;
import cc.cannot.secret.net.NetConnection;

import static android.R.attr.onClick;

/**
 * Created by huangzhen on 4/5/2017.
 */

public class atyLogin extends Activity {
    private EditText etPhone;
    private EditText etCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_login);
        etPhone = (EditText)findViewById(R.id.etPhoneNumber);
        etCode = (EditText)findViewById(R.id.etCode);

        findViewById(R.id.btnGetCode).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etPhone.getText())){
                    Toast.makeText(atyLogin.this, getString(R.string.phone_number_cannot_be_empty),Toast.LENGTH_LONG).show();
                    return;
                }
                final ProgressDialog pd = ProgressDialog.show(atyLogin.this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
                new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(atyLogin.this, R.string.succeeded_to_get_code, Toast.LENGTH_LONG).show();
                    }
                }, new GetCode.FailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(atyLogin.this, R.string.failed_to_get_code, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        findViewById(R.id.btnLogin).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etPhone.getText())){
                    Toast.makeText(atyLogin.this, R.string.phone_number_cannot_be_empty,Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText())){
                    Toast.makeText(atyLogin.this, R.string.code_can_not_be_empty,Toast.LENGTH_LONG).show();
                }
            }
        });

    }



}
