package hunag.xanagi.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapView = (MapView) findViewById(R.id.map);
        mapView.setMultiTouchControls(true);
        mapView.setBuiltInZoomControls(true);
        new AsyncMap(this).execute(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mapCreation(ArrayList<GeoPoint> arrayList){

        for (int i=0; i<arrayList.size(); i++) {
            Marker temp = new Marker(mapView);
            temp.setPosition(arrayList.get(i));
            temp.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM);
            temp.setAlpha(0.50f);
            temp.setTitle(String.valueOf(i));
            mapView.getOverlays().add(temp);
        }
        IMapController controller = mapView.getController();
        controller.setZoom(14);
        controller.setCenter(arrayList.get(0));
        mapView.invalidate();
    }

}
