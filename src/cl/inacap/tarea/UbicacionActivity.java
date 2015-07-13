package cl.inacap.tarea;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.example.tarea.u3.herman.vargas.R;
import com.example.tarea.u3.herman.vargas.R.id;
import com.example.tarea.u3.herman.vargas.R.layout;
import com.example.tarea.u3.herman.vargas.R.menu;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UbicacionActivity extends Activity implements LocationListener {
	
	private static final long MIN_DISTANCE = 5; // distancia mínima
    private static final long MIN_TIME = 180 * 1000; // 3 minutos
    private TextView mTextView; // texview para visualizar
    private LocationManager mLocationManager; // objeto location
    private String mProvider; // variable para proveedor
	public String TAG="IP";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubicacion);
		mTextView = (TextView) findViewById(R.id.locationText);		
	 	Criteria criteria = new Criteria(); // se crea un obj criteria para determinar citerios
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // se asigna tolerancia
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE); // se crea un location manager
        mProvider = mLocationManager.getBestProvider(criteria,true); // se le indica el criterio

        Location localization = mLocationManager.getLastKnownLocation(mProvider); // le asignamos un proveedor al obj location
	}

 	@Override
    protected void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(mProvider, MIN_TIME, MIN_DISTANCE, this); // actualizamos la ubicacion
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(this); // se remueve la localizacion
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubicacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// cuando cambia la localización se ejecuta y muestra por pantalla en el textview
		mTextView.append("Ubicación completa: "+location.toString() + "\n" + "Latitud: "+ location.getLatitude()+"\n"+ "Longitud: "+ location.getLongitude()+"\n" + "IP: "+getLocalIpAddress());

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	 public String getLocalIpAddress() {
		    try {
		      for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
		         NetworkInterface intf = en.nextElement();
		         for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		           InetAddress inetAddress = enumIpAddr.nextElement();
		           if (!inetAddress.isLoopbackAddress()) {
		             String ip = Formatter.formatIpAddress(inetAddress.hashCode());
		             return ip;
		           }
		         }
		      }
		    } catch (SocketException ex) {
		      // 
		    }
		    return null;
		  }
	 public String traerLocalIpAddress() {
	        try {
	            for (Enumeration<NetworkInterface> en = NetworkInterface
	                    .getNetworkInterfaces(); en.hasMoreElements();) {
	                NetworkInterface intf = en.nextElement();
	                for (Enumeration<InetAddress> enumIpAddr = intf
	                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                    InetAddress inetAddress = enumIpAddr.nextElement();
	                    if (!inetAddress.isLoopbackAddress()) {
	                        return inetAddress.getHostAddress().toString();
	                    }
	                }
	            }
	        } catch (SocketException ex) {
	            Log.e(TAG, ex.toString());
	        }
	        return "";
	    }
}
