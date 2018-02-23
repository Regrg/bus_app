package free.com.rge.bus_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button chooseStart;
    private Button chooseDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        chooseStart = findViewById(R.id.chooseStart);
        chooseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMap();
            }
        });

        chooseDestination = findViewById(R.id.chooseDestination);
        chooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMap();
            }
        });

        grantPermission();
    }

    private void goMap() {
        Intent intent = new Intent();
        intent.setClass(context, MapActivity.class);
        startActivity(intent);
    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
//            mMap.setMyLocationEnabled(true);
            // Permission has already been granted
        }
    }
}
