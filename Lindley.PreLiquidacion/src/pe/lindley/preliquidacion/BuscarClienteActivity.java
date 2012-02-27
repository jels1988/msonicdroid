package pe.lindley.preliquidacion;

import pe.lindley.preliquidacion.negocio.DocumentoBLL;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import net.msonic.lib.ActivityBase;

public class BuscarClienteActivity extends ActivityBase {

	@Inject DocumentoBLL documentoBLL;
	@InjectView(R.id.txtCodigo) EditText txtCodigoCliente;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.buscarcliente_activity);
		

		mActionBar.setTitle(R.string.login_activity_sub_title);
        //mActionBar.setSubTitle(getString(R.string.login_activity_sub_title));
        mActionBar.setHomeLogo(R.drawable.header_logo);
	}

	public void btnBuscar_onlick(View view){
		
		
		String codigo = txtCodigoCliente.getText().toString();
		if(codigo!=null){
			
			Intent i =new Intent(this,DocumentoActivity.class);
			i.putExtra(DocumentoActivity.CODIGO_CLIENTE_KEY, codigo);
			startActivity(i);
			
		}else{
			
		}
		
	}
}
