package pe.lavisa.tomadorpedidos;

import roboguice.inject.InjectExtra;
import android.os.Bundle;

public class ProductoEstrategiaActivity extends net.msonic.lib.sherlock.ActivityBase {

	public static final String PRODUCTO_ID_KEY="producto_id_key";
	public static final String PRODUCTO_DESCRIPCION_KEY="producto_descripcion_key";
	
	

	@InjectExtra(value=PRODUCTO_ID_KEY)private int producto_id;
	@InjectExtra(value=PRODUCTO_DESCRIPCION_KEY)private String producto_descripcion;
	

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.productoestrategia_activity);
	        
	        setTitle(R.string.productoestrategia_activity_title);
	        
	        setSubTitle(producto_descripcion);
	    }
}
