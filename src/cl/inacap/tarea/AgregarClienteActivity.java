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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarClienteActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_cliente);
		Button btn_agregar = (Button)findViewById(R.id.btn_agregarCl);
		final EditText id_cliente = (EditText)findViewById(R.id.cliente_id);
		final EditText nombre_cliente = (EditText)findViewById(R.id.cliente_nombre);
		final EditText empresa_cliente = (EditText)findViewById(R.id.cliente_empresa);

		btn_agregar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BdSqlite conn = new BdSqlite(AgregarClienteActivity.this);
				conn.abrir();

					try {
						conn.insertarCliente(Integer.parseInt(id_cliente.getText().toString()),nombre_cliente.getText().toString(), empresa_cliente.getText().toString());
						Toast.makeText(AgregarClienteActivity.this,"Cliente ingresado correctamente",Toast.LENGTH_SHORT).show();	

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				conn.cerrar();
				Intent volver = new Intent(AgregarClienteActivity.this,AdminClienteActivity.class);		
				AgregarClienteActivity.this.startActivity(volver);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_cliente, menu);
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
