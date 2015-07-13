package cl.inacap.tarea;

import cl.inacap.tarea.clases.BdSqlite;

import com.example.tarea.u3.herman.vargas.R;
import com.example.tarea.u3.herman.vargas.R.id;
import com.example.tarea.u3.herman.vargas.R.layout;
import com.example.tarea.u3.herman.vargas.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ResumenCajaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resumen_caja);
		
		final Spinner spproducto = (Spinner)findViewById(R.id.spProducto);
		final TextView sResultado = (TextView)findViewById(R.id.tvResultado);
		
		Button btnConsulta = (Button)findViewById(R.id.btnConsultaP);
		
		String []productos={"Arroz","Azucar","Fideos","Sal","Aceite"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, productos);
		
		spproducto.setAdapter(adapter);
		
		
	
		btnConsulta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BdSqlite conn = new BdSqlite(ResumenCajaActivity.this);
				conn.abrir();

					try {
						String selec=spproducto.getSelectedItem().toString();
    
						String resultado = conn.consultarPedidos(selec);
						
						sResultado.setText(resultado);
						//Toast.makeText(ResumenCajaActivity.this,"Pedido ingresado correctamente",Toast.LENGTH_SHORT).show();	

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				conn.cerrar();
				//Intent volver = new Intent(ResumenCajaActivity.this,MenuActivity.class);		
				//ResumenCajaActivity.this.startActivity(volver);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resumen_caja, menu);
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
