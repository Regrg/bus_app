package free.com.rge.bus_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

/**
 * Created by rgegeo on 22/02/2018.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private UiSettings mUiSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mUiSettings = mMap.getUiSettings();

//        // Keep the UI Settings state in sync with the checkboxes.
//        mUiSettings.setZoomControlsEnabled(isChecked(R.id.zoom_buttons_toggle));
//        mUiSettings.setCompassEnabled(isChecked(R.id.compass_toggle));
//        mUiSettings.setMyLocationButtonEnabled(isChecked(R.id.mylocationbutton_toggle));
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(isChecked(R.id.mylocationlayer_toggle));
//        mUiSettings.setScrollGesturesEnabled(isChecked(R.id.scroll_toggle));
//        mUiSettings.setZoomGesturesEnabled(isChecked(R.id.zoom_gestures_toggle));
//        mUiSettings.setTiltGesturesEnabled(isChecked(R.id.tilt_toggle));
//        mUiSettings.setRotateGesturesEnabled(isChecked(R.id.rotate_toggle));
    }
}
