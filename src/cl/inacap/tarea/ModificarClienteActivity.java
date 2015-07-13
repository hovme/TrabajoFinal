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

public class ModificarClienteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_cliente);
		
		final EditText mIdCliente =(EditText)findViewById(R.id.etCliente_id);
		final EditText mNoCliente =(EditText)findViewById(R.id.etCliente_nombre);
		final EditText mEmCliente =(EditText)findViewById(R.id.etCliente_empresa);
		Button btnModificar =(Button)findViewById(R.id.btn_modificarCli);
		Button btnEliminar =(Button)findViewById(R.id.btn_eliminarCli);
		
		Bundle traer = getIntent().getExtras();
		mIdCliente.setText(traer.getString("id"));
		mNoCliente.setText(traer.getString("nombre"));
		mEmCliente.setText(traer.getString("empresa"));
		
		
		btnModificar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BdSqlite conn = new BdSqlite(ModificarClienteActivity.this);
				conn.abrir();

					try {
						int sIdCliente = Integer.parseInt(mIdCliente.getText().toString());
						String sNoCliente= mNoCliente.getText().toString();
						String sEmCliente= mEmCliente.getText().toString();
						conn.actualizarCliente(sIdCliente,sNoCliente,sEmCliente);
						Toast.makeText(ModificarClienteActivity.this,"Cliente actualizado correctamente",Toast.LENGTH_SHORT).show();	

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				conn.cerrar();
				Intent volver = new Intent(ModificarClienteActivity.this,AdminClienteActivity.class);		
				ModificarClienteActivity.this.startActivity(volver);
			}
		});
			btnEliminar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BdSqlite conn = new BdSqlite(ModificarClienteActivity.this);
				conn.abrir();

					try {
						int sIdCliente = Integer.parseInt(mIdCliente.getText().toString());
						conn.borrarCliente(sIdCliente);
						Toast.makeText(ModificarClienteActivity.this,"Cliente eliminado correctamente",Toast.LENGTH_SHORT).show();	

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				conn.cerrar();
				Intent volver = new Intent(ModificarClienteActivity.this,AdminClienteActivity.class);		
				ModificarClienteActivity.this.startActivity(volver);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modificar_cliente, menu);
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
