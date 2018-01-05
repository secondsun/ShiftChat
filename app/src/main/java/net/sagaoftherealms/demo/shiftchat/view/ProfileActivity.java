package net.sagaoftherealms.demo.shiftchat.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.sagaoftherealms.demo.shiftchat.R;

/**
 * Created by summers on 1/4/18.
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        findViewById(R.id.save_button).setOnClickListener((view)->{
            Intent chatList = new Intent(getApplicationContext(), ChatListActivity.class);
            startActivity(chatList);
            finish();
        });
    }
}
