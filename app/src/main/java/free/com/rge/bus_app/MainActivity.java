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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static int GET_START_LOCATION = 1;
    private final static int GET_DESTINATION_LOCATION = 2;

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
                goMap(GET_START_LOCATION);
            }
        });

        chooseDestination = findViewById(R.id.chooseDestination);
        chooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMap(GET_DESTINATION_LOCATION);
            }
        });

        grantPermission();
    }

    private void goMap(int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, MapActivity.class);
        startActivityForResult(intent, requestCode);
        startActivity(intent);
    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            Toast.makeText(context, requestCode + data.toString(), Toast.LENGTH_SHORT).show();

    }
}
