package cl.inacap.tarea;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cl.inacap.tarea.clases.BdSqlite;
import cl.inacap.tarea.R;
import cl.inacap.tarea.R.id;
import cl.inacap.tarea.R.layout;
import cl.inacap.tarea.R.menu;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EntregaPedidoActivity extends Activity {

	EditText etFecha;
	private DatePickerDialog fecha2;	
	private SimpleDateFormat formatoFecha;
	
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
		etFecha.setInputType(InputType.TYPE_NULL);
		etFecha.requestFocus();
		

		//setDateTimeField();

		//final NumberFormat nf_es = NumberFormat.getCurrencyInstance();
		//etPrecio.setText(nf_es.format(value));
		
	
		
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
			
			
			if(String.valueOf(Locale.getISOCountries())=="US"){
				formatoFecha = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
			}else{
				formatoFecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			}
			//formatoFecha = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
			Calendar newCalendar = Calendar.getInstance();
			fecha2 = new DatePickerDialog(this, new OnDateSetListener() {

		        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		            Calendar newDate = Calendar.getInstance();
		            newDate.set(year, monthOfYear, dayOfMonth);
		            etFecha.setText(formatoFecha.format(newDate.getTime()));
		        }

		    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
			
			
			etFecha.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					fecha2.show();
					//Toast.makeText(EntregaPedidoActivity.this,Locale.getDefault(),Toast.LENGTH_SHORT).show();	

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
