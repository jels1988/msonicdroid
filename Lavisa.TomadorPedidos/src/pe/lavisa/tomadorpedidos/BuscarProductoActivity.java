package pe.lavisa.tomadorpedidos;

import android.os.Bundle;
import android.view.View;

public class BuscarProductoActivity extends net.msonic.lib.sherlock.ActivityBase  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buscarproducto_activity);
	}

	public void btnbuscar_onclick(View v){
		
	}
	public void btnAceptar_onclick(View v){
		setResult(RESULT_OK, null);
		finish();
	}
	
	public void btnCancelar_onclick(View v){
		setResult(RESULT_CANCELED, null);
		finish();
	}
	
}
