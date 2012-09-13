package pe.lavisa.tomadorpedidos;

import roboguice.inject.InjectExtra;
import android.os.Bundle;

public class PedidoActivity extends net.msonic.lib.sherlock.ActivityBase  {
	
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	
	@InjectExtra(value=CLIENTE_CODIGO_KEY)private String cliente_codigo;
	@InjectExtra(value=CLIENTE_DESCRIPCION_KEY)private String cliente_descripcion;
	

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.pedido_activity);
	        setTitle(R.string.pedido_activity_title);
	        
	        setSubTitle(cliente_descripcion);
	        
	    }
}
