package cl.inacap.tarea;


import cl.inacap.tarea.clases.BdSqlite;

import cl.inacap.tarea.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {


	private ProgressBar pbarProgreso;
	private MiTareaAsincronaDialog tarea2;
	private ProgressDialog pDialog;
	final BdSqlite conn = new BdSqlite(MainActivity.this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acceso_login);
		
		
		
		// creamos una instancia de SQL lite
		//Ab = new AdaptadorBase(this);
		//Ab = Ab.open();
		final BdSqlite conn = new BdSqlite(MainActivity.this);
		conn.abrir();
		
		
		final EditText _etUsuario = (EditText)findViewById(R.id.etUsuario);
		final EditText _etContrasena = (EditText)findViewById(R.id.etContrasena);
		Button _btnConectar = (Button)findViewById(R.id.btn_login);
		
		_btnConectar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				String _usuario = _etUsuario.getText().toString();
				String _pass = _etContrasena.getText().toString();
				
				// se trae la pass desde la base de datos
				String contrase = conn.validaUsuario(_usuario);
				if(_pass.equals(contrase)){
					
					pDialog = new ProgressDialog(MainActivity.this);
					pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					pDialog.setMessage("Autenticando...");
					pDialog.setCancelable(true);
					pDialog.setMax(100);
					
					tarea2 = new MiTareaAsincronaDialog();
					tarea2.execute();
					
					//Toast.makeText(MainActivity.this, "Autenticado correctamente", Toast.LENGTH_LONG).show();
					
				}else{
					Toast.makeText(MainActivity.this, "Usuario o password incorrecto", Toast.LENGTH_LONG).show();
					
				}
				
			}
		});
	}
	
		

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.cliente_apellido) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	// metodo que logra la demora
	private void tareaLarga()
    {
    	try { 
    		Thread.sleep(1000); 
    	} catch(InterruptedException e) {}
    }
	// Metodo para generar el hilo y hacer la espera... de 10 en 10
	private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

    	@Override
    	protected Boolean doInBackground(Void... params) {
    		
    		for(int i=1; i<=10; i++) {
				tareaLarga();
				
				publishProgress(i*10);
				
				if(isCancelled())
					break;
			}
    		
    		return true;
    	}
    	
    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		int progreso = values[0].intValue();
    		
    		pDialog.setProgress(progreso);
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		
    		pDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					MiTareaAsincronaDialog.this.cancel(true);
				}
			});
    		
    		pDialog.setProgress(0);
    		pDialog.show();
    	}
    	
    	@Override
    	protected void onPostExecute(Boolean result) {
    		if(result)
    		{
    			pDialog.dismiss();
    			Toast.makeText(MainActivity.this, "Usuario autenticado", Toast.LENGTH_SHORT).show();
    			Intent i = new Intent(MainActivity.this,MenuActivity.class);
    			MainActivity.this.startActivity(i);
    			
    		}
    	}
    	
    	@Override
    	protected void onCancelled() {
    		Toast.makeText(MainActivity.this, "Usuario incorrecto!", Toast.LENGTH_SHORT).show();
    	}
    }
}
