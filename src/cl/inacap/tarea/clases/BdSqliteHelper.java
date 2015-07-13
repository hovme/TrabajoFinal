package cl.inacap.tarea.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BdSqliteHelper extends SQLiteOpenHelper {
	
	 //nombre de la base de datos
	 private static final String database = "bd_productos_frescos";
	// tabla cliente
	 public final String tabla = "cliente";
	 public final String id = "id"; 
	 public final String nombre = "nombre";
	 public final String empresa = "empresa";
	 //version de la base de datos
	 private static final int version = 1;
	//Query SQL para crear las tablas
     private String sql = "CREATE TABLE cliente ( " +id+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+nombre+" TEXT,"+empresa+" TEXT)";
     private String pedido ="create table PEDIDO (ID_PEDIDO integer primary key autoincrement,cliente integer,producto text,cantidad integer,fecha text, precio integer)";

     private final String INSERT_DB2 = "INSERT INTO CLIENTE(id,nombre,empresa) VALUES(1,'Maria Vidal','La cosita');";
 	 private final String INSERT_DB3 = "INSERT INTO CLIENTE(id,nombre,empresa) VALUES(2,'Juan Gonzalez','Tai helao Juan');";
 	 private final String INSERT_DB4 = "INSERT INTO CLIENTE(id,nombre,empresa) VALUES(3,'Pedro Urdemales','Mijita');";
	 private final String INSERT_DB5 = "INSERT INTO CLIENTE(id,nombre,empresa) VALUES(4,'Rosa La Chora','Ta linda la Rosa');";
 	 
  // tabla login
  	 public final String tabla2 = "login";
  	 public final String idusuario = "idusuario"; 
  	 public final String usuario = "usuario";
  	 public final String contrasena = "contrasena";
  
  	//Query SQL para crear las tablas
      private String sql2 = "CREATE TABLE login ( " +idusuario+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+usuario+" TEXT,"+contrasena+" TEXT)";

      private final String INSERT_DB = "INSERT INTO LOGIN(idusuario, usuario, contrasena) VALUES(1,'herman','1234');";
   

	public BdSqliteHelper(Context context) {
		super(context, database, null, version);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
		db.execSQL(pedido);
		db.execSQL(sql2);
		db.execSQL(INSERT_DB);
		db.execSQL(INSERT_DB2);
		db.execSQL(INSERT_DB3);
		db.execSQL(INSERT_DB4);
		db.execSQL(INSERT_DB5);
		Log.i("SQLite","Se creo BD "+ database + " version "+ version);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.i("SQLite", "Control de version: Old Version=" + oldVersion + " New Version= " + newVersion  );
		  if ( newVersion > oldVersion )
		  {
		   //eliminamos la tabla, si existe
		   db.execSQL( "DROP TABLE IF EXISTS "+ tabla);
		   // secrea la nueva tabla
		   db.execSQL(sql); 
		   db.execSQL(pedido);
		   db.execSQL(sql2);
		   Log.i("SQLite", " Se actualiza version, New version= " + newVersion  );
		  }
		
	}

}
