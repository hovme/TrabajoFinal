package cl.inacap.tarea;
import cl.inacap.tarea.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MiRutaActivity extends FragmentActivity implements OnMapClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final LatLng PROD_FRESCOS = new LatLng(-33.3801113,-70.5411158);
    private final LatLng Almacen_1 = new LatLng(-33.381706, -70.542736);
    private final LatLng Almacen_2 = new LatLng(-33.382942, -70.535322);
    private final LatLng Almacen_3 = new LatLng(-33.384859, -70.538777);
	public static final PolylineOptions POLILINEA = new PolylineOptions()
							.add(new LatLng(-33.3801113,-70.5411158))
							.add(new LatLng(-33.380604, -70.542736))
						    .add(new LatLng(-33.381142, -70.542414)) 
						    .add(new LatLng(-33.381513, -70.543036))
						    .add(new LatLng(-33.381943, -70.542779))
						    .add(new LatLng(-33.381702, -70.542001))
						    .add(new LatLng(-33.385052, -70.539313))
						    .add(new LatLng(-33.384864, -70.538804))
						    .add(new LatLng(-33.382933, -70.535247));
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ruta);
        setUpMapIfNeeded();
        drawPolilyne(POLILINEA);
        //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PROD_FRESCOS, 15));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa))
                    .getMap();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PROD_FRESCOS, 21));
            mMap.setMyLocationEnabled(true);
            mMap.setIndoorEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.addMarker(new MarkerOptions()
            .position(PROD_FRESCOS)
            .title("Productos frescos")
            .snippet("Casa Matriz de productos frescos S.A.")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            mMap.setOnMapClickListener(this);
            
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions()
        .position(Almacen_1)
        .title("Tai Helao Juan")
        .snippet("Completos y Sanguches artesanales")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        
        mMap.addMarker(new MarkerOptions()
        .position(Almacen_2)
        .title("El Banco")
        .snippet("Bar y Venta de licores")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
       
        mMap.addMarker(new MarkerOptions()
        .position(Almacen_3)
        .title("La biblioteca")
        .snippet("Bar y Cabaret")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));	
    }

    private void drawPolilyne(PolylineOptions options){
        Polyline polyline = mMap.addPolyline(options);	
    }
    
	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}


}