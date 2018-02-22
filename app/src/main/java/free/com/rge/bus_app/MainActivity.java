package free.com.rge.bus_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=站牌");
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=101+main+street");
//        Uri gmmIntentUri = Uri.parse("google.navigation:q=taipei+101&mode=d");
//
//
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        if (mapIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(mapIntent);
//        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView webView = new WebView(context);
                webView.loadUrl("https://www.google.com/maps/dir/?api=1");
                webView.setWebChromeClient(new WebChromeClient());
                webView.getSettings().setJavaScriptEnabled(true);
                setContentView(webView);
//                Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
            }
        });
    }
}
