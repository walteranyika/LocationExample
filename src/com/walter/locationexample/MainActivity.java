package com.walter.locationexample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tv1, tv2, tv3;
	LocationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void start(View v) {

		try {
			LocationListener listener = new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					double lat = location.getLatitude();
					double lon = location.getLongitude();
					double speed = location.getSpeed();
					tv1.setText("Longi : "+lon);
					tv2.setText("Lati  : "+lat);
					tv3.setText("Speed : "+speed);
					// do something with this values
				}

				@Override
				public void onStatusChanged(String provider, int status,Bundle extras) {
					Log.d("DATA", "STATUS CHANGED " + provider);
				}

				@Override
				public void onProviderEnabled(String provider) {
					Log.d("DATA", "PROVIDER ENABLED " + provider);
				}

				@Override
				public void onProviderDisabled(String provider) {
					Log.d("DATA", "PROVIDER DISABLED " + provider);
				}
			};
			String provider = LocationManager.GPS_PROVIDER;
			
			manager.requestLocationUpdates(provider, 3000,2, listener);
		} catch (SecurityException e) {
			Log.e("DATA", "Could Not Fetch The Location");
		}

	}

	public void start_service(View v) {
     Intent service=new Intent(this,LocationService.class);
     startService(service);
	}

	public void check(View v) {
      String data= Utility.readFRomFile(this, "data.txt");
      Toast.makeText(this, data, Toast.LENGTH_LONG).show();
	}	
	
	
	
	
	

}
