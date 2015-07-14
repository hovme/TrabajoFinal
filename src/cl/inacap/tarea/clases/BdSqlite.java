package cl.inacap.tarea.clases;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BdSqlite {

	BdSqliteHelper bdSqliteHelper;
	SQLiteDatabase db;
	
	public BdSqlite(Context context){
		bdSqliteHelper = new BdSqliteHelper(context);
	}
	public BdSqlite(){}
	public void abrir(){
		  Log.i("SQLite", "Se abre conexion a la base de datos " + bdSqliteHelper.getDatabaseName() );
		  db = bdSqliteHelper.getWritableDatabase(); // instancia D 
    }
	public void cerrar()
	{
	  Log.i("SQLite", "Se cierra conexion a la base de datos " + bdSqliteHelper.getDatabaseName() );
	  bdSqliteHelper.close();  
	}
	
	public Cursor traerRegistrosCliente()
	{
		return db.query("cliente",				
					new String[]{
				bdSqliteHelper.id,
				bdSqliteHelper.nombre,
				bdSqliteHelper.empresa
								  }, 
				null, null, null, null,null);
	}
	public String validaUsuario(String userName)
	{
		Cursor cursor=db.query("login", null, "usuario=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // Usuario no existe
        {
        	cursor.close();
        	return "NO EXISTE";
        }
	    cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("contrasena"));
		cursor.close();
		return password;				
	}
	public String getIdUsuario(String userName)
	{
		Cursor cursor=db.query("login", null, "usuario=?", new String[]{userName}, null, null, null);
	    cursor.moveToFirst();
		String id= cursor.getString(cursor.getColumnIndex("idusuario"));
		cursor.close();
		return id;				
	}
	public ArrayList<String> traerListaClientes(Cursor cursor)
	{
		ArrayList<String> listaDatos = new ArrayList<String>();
		String item = "";
			if( cursor.moveToFirst() )
			{
				do
				{		
					/*item += "id:"+ cursor.getInt(0) +"\r\n";
					item += "nombre:" + cursor.getString(1) + "\r\n";
					item += "empresa:" + cursor.getString(2) + "\r\n";*/
					item += "id:"+ cursor.getInt(0);
					item += ":nombre:" + cursor.getString(1);
					item += ":empresa:" + cursor.getString(2);
					listaDatos.add(item);
					item="";            
				} while ( cursor.moveToNext() );
			}		
		return listaDatos;		
	}
	 public long insertarCliente(int Id, String Nombre,String Empresa)
	 {
		 Log.i("Sqlite","Insertando :");
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("id",Id);
		 contentValues.put("nombre",Nombre);
		 contentValues.put("empresa",Empresa);		 
		 return db.insert("cliente", null,contentValues);
	 }
	 public int actualizarCliente(int id, String nombre, String empresa) {
		  ContentValues cvActualizar = new ContentValues();
		  cvActualizar.put("nombre", nombre);
		  cvActualizar.put("empresa",empresa);
		  int i = db.update("cliente", cvActualizar,
		    "id" + " = " + id, null);
		  return i;
		 }
	 
	 public boolean borrarCliente(int id) {
		    return db.delete("cliente", "id" + "=" + id, null) > 0;
	 }
	 public long registrarPedido(int IdCliente, String producto,int cantidad, String fecha, int precio)
	 {
		 
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("ID_PEDIDO",1);
		 contentValues.put("cliente",IdCliente);
		 contentValues.put("producto",producto);
		 contentValues.put("cantidad",cantidad);
		 contentValues.put("fecha",fecha);
		 contentValues.put("precio",precio);		 
		 long ins = db.insert("pedido", null,contentValues);
		 Log.i("Sqlite","Registrando entrega :"+ins);
		 return 	ins;
	 }
	 public String consultarPedidos(String producto)
		{
		 Cursor saldo = db.rawQuery(
	                "select count(cantidad) from pedido where producto=" +"'"+ producto+"'", null);
		 Cursor totalEntrega = db.rawQuery(
	                "select count(*) from pedido where producto=" +"'"+ producto+"'", null);
		 Cursor totalPedido = db.rawQuery(
	                "select * from pedido where producto=" +"'"+ producto+"'", null);
		 
		 String item1 = "";
		 String item2 = "";
		 String item3 = "";
		 
		 if (saldo.moveToFirst()) {
			 item1 += " El saldo es: "+saldo.getString(0) +"\r\n"; 
	        } else{
	        	return null;
	        }
		 if (totalEntrega.moveToFirst()) {
			 item2 += " El total de entregas es: "+totalEntrega.getString(0) +"\r\n"; 
	        } else{
	        	return null;
	        }
		 if (totalPedido.moveToFirst()) {
			 item3 += " El total de pedidos es: "+totalPedido.getString(0) +"\r\n";
	        } else{
	        	return null;
	        }
		 
		 return item1+item2+item3;
		}
	
}
