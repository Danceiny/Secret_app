package cc.cannot.secret.activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cc.cannot.secret.Config;
import cc.cannot.secret.R;
import cc.cannot.secret.net.Comment;
import cc.cannot.secret.net.GetComment;
import cc.cannot.secret.net.Message;
import cc.cannot.secret.net.PubComment;
import cc.cannot.secret.utils.MD5Tool;

/**
 * Created by huangzhen on 4/5/2017.
 */

public class atyMessage extends ListActivity {
    private atyMsgCommentListAdapter adapter;
    private TextView tvMessage;
    private EditText etComment;
    private String phone_md5, msg, msgId, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_message);
//
        adapter = new atyMsgCommentListAdapter(this);
        setListAdapter(adapter);

        tvMessage = (TextView) findViewById(R.id.tvMessage);
        etComment = (EditText) findViewById(R.id.etComment);

        Intent data = getIntent();
        phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
        msg = data.getStringExtra(Config.KEY_MSG);
        msgId = data.getStringExtra(Config.KEY_MSG_ID);
        token = data.getStringExtra(Config.KEY_TOKEN);

        tvMessage.setText(msg);

        getComments();

        findViewById(R.id.btnSendComment).setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View v) {
                                                                     if (TextUtils.isEmpty(etComment.getText())) {
                                                                         Toast.makeText(atyMessage.this, R.string.comment_content_can_not_be_empty, Toast.LENGTH_LONG).show();
                                                                         return;
                                                                     }
                                                                     final ProgressDialog pd = ProgressDialog.show(atyMessage.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
                                                                     new PubComment(MD5Tool.md5(Config.getCachedPhoneNumber(atyMessage.this)), token, etComment.getText().toString(), msgId, new PubComment.SuccessCallback() {
                                                                         @Override
                                                                         public void onSuccess() {
                                                                             pd.dismiss();
                                                                             etComment.setText("");
                                                                             getComments();
                                                                         }

                                                                     }, new PubComment.FailCallback() {
                                                                         @Override
                                                                         public void onFail(int errorCode) {
                                                                             pd.dismiss();

                                                                             if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {

                                                                                 startActivity(new Intent(atyMessage.this, atyLogin.class));
                                                                                 finish();
                                                                             } else {
                                                                                 Toast.makeText(atyMessage.this, R.string.fail_to_pub_comment, Toast.LENGTH_LONG).show();
                                                                             }
                                                                         }
                                                                     });
                                                                 }

                                                             }
        );
    }

    private void getComments() {
        final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
        new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
                pd.dismiss();
                adapter.clear();
                adapter.addAll(comments);
            }
        }, new GetComment.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();

                if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
                    startActivity(new Intent(atyMessage.this, atyLogin.class));
                    finish();
                } else {
                    Toast.makeText(atyMessage.this, R.string.fail_to_get_comment, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
