package free.com.rge.bus_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by rgegeo on 02/03/2018.
 */

public class SearchResultActivity extends AppCompatActivity {
    private LatLng startLatLng, destinationLatLng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() != null) {
            startLatLng = getIntent().getExtras().getParcelable(getString(R.string.startLatLng));
            destinationLatLng = getIntent().getExtras().getParcelable(getString(R.string.destinationLatLng));

            System.out.println(startLatLng.toString() + destinationLatLng.toString());
        }
    }
}
