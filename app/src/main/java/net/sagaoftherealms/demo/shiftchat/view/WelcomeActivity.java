package net.sagaoftherealms.demo.shiftchat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.sagaoftherealms.demo.shiftchat.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_chat);
        findViewById(R.id.sign_in_button).setOnClickListener((view) -> {
            Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(profileIntent);
            finish();
        });
    }
}
