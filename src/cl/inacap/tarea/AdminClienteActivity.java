package cl.inacap.tarea;

import java.util.ArrayList;

import cl.inacap.tarea.clases.BdSqlite;
import cl.inacap.tarea.clases.BdSqliteHelper;

import com.example.tarea.u3.herman.vargas.R;
import com.example.tarea.u3.herman.vargas.R.id;
import com.example.tarea.u3.herman.vargas.R.layout;
import com.example.tarea.u3.herman.vargas.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AdminClienteActivity extends Activity implements OnItemClickListener {

	private ArrayAdapter<String> adaptador;
	ListView lvListaCliente;
	
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_cliente);
			
		
		Button btAgregarCliente = (Button)findViewById(R.id.btn_agregarCl);
		lvListaCliente = (ListView)findViewById(R.id.lv_listado);
		
		BdSqlite conn = new BdSqlite(AdminClienteActivity.this);
		conn.abrir();
		Cursor cursor = conn.traerRegistrosCliente();
		
		ArrayList<String> listaDatos = conn.traerListaClientes(cursor);
		
		adaptador = new ArrayAdapter<String>(AdminClienteActivity.this,android.R.layout.simple_list_item_1, listaDatos);
		
		
		
		lvListaCliente.setAdapter(adaptador);
		
		
		lvListaCliente.setOnItemClickListener(this);
		adaptador.notifyDataSetChanged();
		
		if(listaDatos.size()== 0)
		{
			Toast.makeText(getBaseContext(), "No hay registros"  ,Toast.LENGTH_SHORT).show();
		}
		conn.cerrar();
		
		//acción del boton agregar cliente
		btAgregarCliente.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent iagregar = new Intent(AdminClienteActivity.this, AgregarClienteActivity.class);
	            startActivity(iagregar);
	        }
	    });	
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_cliente, menu);
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
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //String idCliente = lvListaCliente.getItemAtPosition(1).toString();
       // String arrCliente = lvListaCliente.getSelectedItem().toString();
        //Object obj = lvListaCliente.getItemAtPosition(position);
        //Object Cliente2 = lvListaCliente.getSelectedItemPosition();
        //	Object Cliente2 = lvListaCliente.getSelectedItemPosition();
        	 String cliente = lvListaCliente.getItemAtPosition(position).toString();
        	 String arrCliente[] = cliente.split(":");
        	 
        String idCliente = arrCliente[1];
        
        String nombreCliente =arrCliente[3];
        String empresaCliente =arrCliente[5];
        	
       

            Intent modifica = new Intent(getApplicationContext(), ModificarClienteActivity.class);
            modifica.putExtra("id", idCliente);
            modifica.putExtra("nombre", nombreCliente);
            modifica.putExtra("empresa", empresaCliente);
            startActivity(modifica);
        };
    

	
}


	
