package net.sagaoftherealms.demo.shiftchat;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.Picasso;

public class ShiftChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Picasso getPicasso() {

        Picasso picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                if (exception != null) {
                    Log.e("PICASSO", exception.getMessage(), exception);
                }
            }
        }).build();


        picasso.setLoggingEnabled(true);
        return picasso;
    }

}
