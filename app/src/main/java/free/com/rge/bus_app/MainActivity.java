package free.com.rge.bus_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    private final static int GET_START_LOCATION = 1;
    private final static int GET_DESTINATION_LOCATION = 2;

    private Context context;
    private Button chooseStart, chooseDestination, goSearch;
    private TextView startLocationText, destinationLocationText;
    private LatLng startLatLng, destinationLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLayoutResource();
        setListener();
        context = this;

        grantPermission();
    }

    private void setLayoutResource() {
        chooseStart = findViewById(R.id.chooseStart);
        chooseDestination = findViewById(R.id.chooseDestination);
        goSearch = findViewById(R.id.searchButton);

        startLocationText = findViewById(R.id.startLocationText);
        destinationLocationText = findViewById(R.id.destinationText);
    }

    private void setListener() {
        chooseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMap(GET_START_LOCATION);
            }
        });

        chooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMap(GET_DESTINATION_LOCATION);
            }
        });

        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchPage();
            }
        });
    }

    private void goMap(int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, MapActivity.class);
        startActivityForResult(intent, requestCode);
    }

    private void goSearchPage() {
        if(startLatLng != null && destinationLatLng != null) {
            Intent intent = new Intent();
            intent.putExtra(getString(R.string.startLatLng), startLatLng);
            intent.putExtra(getString(R.string.destinationLatLng), destinationLatLng);
            intent.setClass(this, SearchResultActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(context, getString(R.string.start_or_destination_notChoose_error), Toast.LENGTH_SHORT).show();
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
        if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            String address = data.getExtras().getString(getString(R.string.address));
            LatLng latLng = data.getExtras().getParcelable(getString(R.string.latLng));

            if (requestCode == GET_START_LOCATION) {
                startLatLng = latLng;
                startLocationText.setText(address);
            } else if (requestCode == GET_DESTINATION_LOCATION) {
                destinationLatLng = latLng;
                destinationLocationText.setText(address);
            }

//            Toast.makeText(context, requestCode + address + " " + latLng, Toast.LENGTH_SHORT).show();
        }

    }
}
