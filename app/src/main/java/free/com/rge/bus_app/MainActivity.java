package free.com.rge.bus_app;

import android.content.Context;
import android.content.Intent;
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
    }

    private void goMap() {
        Intent intent = new Intent();
        intent.setClass(context, MapActivity.class);
        startActivity(intent);
    }
}
