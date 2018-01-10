package net.sagaoftherealms.demo.shiftchat.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import net.sagaoftherealms.demo.shiftchat.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelcomeActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0x123;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_chat);
        findViewById(R.id.sign_in_button).setOnClickListener((view) -> {
            startExchange();
//            Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
//            startActivity(profileIntent);
//            finish();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                exchangeTokens(account);
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private void startExchange() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestScopes(new Scope(Scopes.PROFILE), new Scope(Scopes.PLUS_ME), new Scope(Scopes.EMAIL))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        return;

    }


    private void exchangeTokens(GoogleSignInAccount account) {
        try {
            String token = account.getIdToken();
            Log.i("TOKEN", token);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();


            FormBody requestBody = new FormBody.Builder()
                    .add("client_id", "android-app")
                    .add("audience", "android-app")
                    .add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange")
                    .add("subject_token", token)
                    .add("subject_token_type", "urn:ietf:params:oauth:token-type:jwt")
                    .add("requested_token_type", "urn:ietf:params:oauth:token-type:refresh_token")
                    .build();

            Request request = new Request.Builder()
                    .url("http://keycloak-myproject.192.168.37.1.nip.io/auth/realms/shiftchat/protocol/openid-connect/token")
                    .post(requestBody)
                    .build();

            AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void[] objects) {
                    try {
                        Response result = client.newCall(request).execute();
                        String body = result.body().string();
                        result.body().close();
                        return body;
                    } catch (IOException e) {
                        Log.e("ERROR", e.getMessage(), e);
                        return null;
                    }


                }

                @Override
                protected void onPostExecute(String o) {
                    super.onPostExecute(o);

                    if (o != null) {
                        Toast.makeText(WelcomeActivity.this, o, Toast.LENGTH_LONG).show();
                        Log.i("RESULT", o);

                    }

                }
            }.

                    execute((Void) null);


        } catch (
                Exception e)

        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.getMessage(), e);
        }
    }

}
