package lindley.desarrolloxcliente.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import lindley.desarrolloxcliente.dao.ClienteDAO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import net.msonic.lib.DBHelper;
import net.msonic.lib.JSONHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;
	@Inject private Context context; 
	
	public List<ClienteTO> listarByCodigo(String codigo){
		List<ClienteTO> clienteTO=null;
		
		try{
			dbHelper.openDataBase();
			clienteTO = clienteDAO.listarByCodigo(codigo);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "listarByCodigo", ex);
		} finally {
			dbHelper.close();
		}
		
		return clienteTO;
		
	}
	
	

	public void compartirClienteXX(ClienteTO clienteTO){
		Context contextLanzadorAplicaciones = null;
		
		try {
			contextLanzadorAplicaciones = context.createPackageContext("pe.lindley.lanzadorapp",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG_LOG, "consultarCliente", e);
		}
		
		SharedPreferences settings = contextLanzadorAplicaciones.getSharedPreferences("LANZADOR_APP_DATA", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = settings.edit();
		String clienteSeleccionado = JSONHelper.serializar(clienteTO);
		Log.d(TAG_LOG,clienteSeleccionado);
		 editor.putString("LANZADOR_APP_CLIENTE_SELECCIONADO",clienteSeleccionado );
		 editor.commit();
		 
		 
	
	}
	
	public lindley.desarrolloxcliente.lazanadorapp.to.ClienteTO consultarCliente(){
		/*
		Log.d(TAG_LOG, "consultarCliente");
		Context contextLanzadorAplicaciones = null;
		
		
		try {
			contextLanzadorAplicaciones = context.createPackageContext("pe.lindley.lanzadorapp",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG_LOG, "consultarCliente", e);
		}
		
		SharedPreferences settings = contextLanzadorAplicaciones.getSharedPreferences("LANZADOR_APP_DATA", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		String clienteJSON = settings.getString("LANZADOR_APP_CLIENTE_SELECCIONADO", null);
		lindley.desarrolloxcliente.lazanadorapp.to.ClienteTO clienteTO;
		if(clienteJSON==null){
			Log.d(TAG_LOG, "Clientes no existe");
			clienteTO = null;
		}else{
			Log.d(TAG_LOG, clienteJSON);
			clienteTO = JSONHelper.desSerializar(clienteJSON, lindley.desarrolloxcliente.lazanadorapp.to.ClienteTO.class);
			
			
			clienteTO.codigoCliente =  String.valueOf(Integer.valueOf(clienteTO.codigoCliente));
		}
		return clienteTO;*/
		
		Log.d(TAG_LOG, "consultarCliente");
		
		lindley.desarrolloxcliente.lazanadorapp.to.ClienteTO clienteTO=null;
		 File root = Environment.getExternalStorageDirectory();
		//Get the text file
		 File file = new File(root,"LANZADOR_APP_CLIENTE_SELECCIONADO");
		 StringBuilder text = new StringBuilder();
		 BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			 String line;
			    while ((line = br.readLine()) != null) {
			        text.append(line);
			    }
			    
			    String clienteJSON = text.toString();
			    
			    if(clienteJSON==null){
					Log.d(TAG_LOG, "Clientes no existe");
					clienteTO = null;
				}else{
					Log.d(TAG_LOG, clienteJSON);
					clienteTO = JSONHelper.desSerializar(clienteJSON, lindley.desarrolloxcliente.lazanadorapp.to.ClienteTO.class);
				}    
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){ 
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 return clienteTO;
			
	}
	
	public UsuarioTO consultarUsuario(){
		Log.d(TAG_LOG, "consultarUsuario");
		Context contextLanzadorAplicaciones = null;
		
		try {
			contextLanzadorAplicaciones = context.createPackageContext("pe.lindley.lanzadorapp",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG_LOG, "consultarCliente", e);
		}
		
		SharedPreferences settings = contextLanzadorAplicaciones.getSharedPreferences("LANZADOR_APP_DATA", 0);
		String usuarioJSON = settings.getString("LANZADOR_APP_USUARIO_LOGEADO", null);
		UsuarioTO usuarioTO;
		if(usuarioJSON==null){
			Log.d(TAG_LOG, "Clientes no existe");
			usuarioTO = null;
		}else{
			Log.d(TAG_LOG, usuarioJSON);
			usuarioTO = JSONHelper.desSerializar(usuarioJSON, UsuarioTO.class);
		}
		return usuarioTO;
			
	}
	
}
