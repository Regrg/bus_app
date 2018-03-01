package free.com.rge.bus_app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by rgegeo on 22/02/2018.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Context context;
    private GoogleMap mMap;
    private UiSettings mUiSettings;
    private EditText mSearchLocation;
    private ImageButton mSearchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        context = this;
        mSearchLocation = findViewById(R.id.searchLocation);
        mSearchButton = findViewById(R.id.searchButton);
        setButtonListener();
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mUiSettings = mMap.getUiSettings();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Map permission isn\'t granted", Toast.LENGTH_LONG).show();
            return;
        }
        mMap.setMyLocationEnabled(true);

//        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
//        mUiSettings.setTiltGesturesEnabled(isChecked(R.id.tilt_toggle));
//        mUiSettings.setRotateGesturesEnabled(isChecked(R.id.rotate_toggle));

        /** ya, addmarker code */
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addSingleMarker(latLng);
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(context, "marker position: " + marker.getPosition().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setButtonListener() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMapSearch();
            }
        });
        mSearchLocation.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (event.getKeyCode())
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            onMapSearch();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    public void onMapSearch() {
        if (!mSearchLocation.getText().toString().isEmpty()) {
            String location = mSearchLocation.getText().toString();

            Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> addressList = geocoder.getFromLocationName(location, 1);
                if(!addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    addSingleMarker(latLng);
                } else {
                    Toast.makeText(context, "No Location Found", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addSingleMarker(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
