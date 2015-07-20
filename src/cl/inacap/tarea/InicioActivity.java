package cl.inacap.tarea;

import java.util.Timer;
import java.util.TimerTask;


import cl.inacap.tarea.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;

public class InicioActivity extends Activity {
	private ProgressBar spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		spinner=(ProgressBar)findViewById(R.id.progressBar1);
		
		setContentView(R.layout.activity_inicio);
		TimerTask task = new TimerTask()
		{
			@Override
			public void run(){
				
				Intent intent = new Intent().setClass(InicioActivity.this,MainActivity.class);
				InicioActivity.this.startActivity(intent);
				finish();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task,6000);	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
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
}
