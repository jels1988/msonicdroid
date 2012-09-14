package pe.lavisa.tomadorpedidos;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PedidoActivity extends net.msonic.lib.sherlock.ActivityBase  {
	
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	
	@InjectExtra(value=CLIENTE_CODIGO_KEY)private String cliente_codigo;
	@InjectExtra(value=CLIENTE_DESCRIPCION_KEY)private String cliente_descripcion;
	@InjectView(R.id.txtProducto) private TextView txtProducto;

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.pedido_activity);
	        setTitle(R.string.pedido_activity_title);
	        
	        setSubTitle(cliente_descripcion);
	        
	     
	        
	    }
	 
	 
	 public void btnBuscar_onlick(View v){
		   Intent intent = new Intent(this,BuscarProductoActivity.class);
	        startActivityForResult(intent, 0);
	 }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//Log.d(PedidoActivity.class.getSimpleName(), String.valueOf(resultCode));
		if(resultCode==Activity.RESULT_OK){
			String producto = data.getStringExtra(BuscarProductoActivity.PRODUCTO_DESCRIPCION_KEY);
			int producto_id = data.getIntExtra(BuscarProductoActivity.PRODUCTO_ID_KEY,0);
			txtProducto.setText(producto);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	 
	 
}
