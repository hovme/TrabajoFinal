package cl.inacap.tarea;

import cl.inacap.tarea.R;
import cl.inacap.tarea.R.id;
import cl.inacap.tarea.R.layout;
import cl.inacap.tarea.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button btAdminCliente = (Button)findViewById(R.id.btn_adminCliente);
		Button btEntregaPedido = (Button)findViewById(R.id.btn_entregaPedido);
		Button btResumCaja =(Button)findViewById(R.id.btn_resumenCaja);
		Button btUbica =(Button)findViewById(R.id.btnUbicacion);
		Button btMiRuta =(Button)findViewById(R.id.btnMiRuta);
		
		
		btUbica.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent irUbica = new Intent(MenuActivity.this,UbicacionActivity.class);
				MenuActivity.this.startActivity(irUbica);
				
			}
		});

		btMiRuta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent irRuta = new Intent(MenuActivity.this,MiRutaActivity.class);
				MenuActivity.this.startActivity(irRuta);
				
			}
		});

		
		btAdminCliente.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent irAdmin = new Intent(MenuActivity.this,AdminClienteActivity.class);
				MenuActivity.this.startActivity(irAdmin);
				
			}
		});
		btEntregaPedido.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent irPedido = new Intent(MenuActivity.this,EntregaPedidoActivity.class);
				MenuActivity.this.startActivity(irPedido);
				
			}
		});
		btResumCaja.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent irResumen = new Intent(MenuActivity.this,ResumenCajaActivity.class);
				MenuActivity.this.startActivity(irResumen);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
}
