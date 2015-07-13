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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EntregaPedidoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrega_pedido);
		
		final EditText etCliente = (EditText)findViewById(R.id.etEnCliente);
		final Spinner spProducto = (Spinner)findViewById(R.id.spEnProducto);
		final EditText etCantidad = (EditText)findViewById(R.id.etEnCantidad);
		final EditText etFecha = (EditText)findViewById(R.id.etEnFecha);
		final EditText etPrecio = (EditText)findViewById(R.id.etEnPrecio);
		Button btn_registro = (Button)findViewById(R.id.btnRegistrarEntrega);
		
		String []productos={"Arroz","Azucar","Fideos","Sal","Aceite"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, productos);
		
		spProducto.setAdapter(adapter);
		

		
			btn_registro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BdSqlite conn = new BdSqlite(EntregaPedidoActivity.this);
				conn.abrir();

					try {
						String selec=spProducto.getSelectedItem().toString();
						String sCliente = etCliente.getText().toString();
						String sCantidad = etCantidad.getText().toString();
						String sFecha = etFecha.getText().toString();
					    String sPrecio = etPrecio.getText().toString();
					    
						conn.registrarPedido(Integer.parseInt(sCliente),selec,Integer.parseInt(sCantidad),sFecha,Integer.parseInt(sPrecio));
						
						Toast.makeText(EntregaPedidoActivity.this,"Pedido ingresado correctamente",Toast.LENGTH_SHORT).show();	

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				conn.cerrar();
				Intent volver = new Intent(EntregaPedidoActivity.this,MenuActivity.class);		
				EntregaPedidoActivity.this.startActivity(volver);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrega_pedido, menu);
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
