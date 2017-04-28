package cc.cannot.secret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.cannot.secret.activities.atyLogin;
import cc.cannot.secret.activities.atyTimeline;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To do Config.
        String token = Config.getCachedToken(this);
        String phone_number = Config.getCachedPhoneNumber(this);

        if(null!=token && null!=phone_number) {
            Intent launchIntent = new Intent(this, atyTimeline.class);
            launchIntent.putExtra(Config.KEY_TOKEN, token);
            launchIntent.putExtra(Config.KEY_PHONE_NUMBER, phone_number);
            startActivity(launchIntent);
        }
        else{
            startActivity(new Intent(this, atyLogin.class));
        }
        setContentView(R.layout.activity_main);
    }
}
