package cc.cannot.secret.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import cc.cannot.secret.Config;
import cc.cannot.secret.R;

/**
 * Created by huangzhen on 4/5/2017.
 */

public class atyMessage extends ListActivity {
    private atyMessageCommentListAdapter adapter;
    private TextView tvMessage;
    private EditText etComment;
    private String phone_md5,msg,msgId,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.aty_message);
//
//        adapter = new atyMessageCommentListAdapter(this);
//        setListAdapter(adapter);
//
//        tvMessage = (TextView)findViewById(R.id.txMessage);
//        etComment = (EditText)findViewById(R.id.etComment);

        Intent data = getIntent();
        phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
    }
}
