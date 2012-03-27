package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import net.msonic.lib.ActivityBase;

public class InformacionAdicional_Activity extends ActivityBase {

	private static final String AGRUPACION_INVENTARIO = "1";
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	ClienteTO cliente;
	UsuarioTO usuario;
	private  MyApplication application;
	InformacionAdicionalTO informacion;
	@InjectView(R.id.txtObs) EditText txtObs;
	@InjectView(R.id.radSSSi) RadioButton radSSSi;
	@InjectView(R.id.radSSNo) RadioButton radSSNo;
	@InjectView(R.id.radMSSi) RadioButton radMSSi;
	@InjectView(R.id.radMSNo) RadioButton radMSNo;
	@Inject GuardarDesarrolloProxy guardarDesarrolloProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacionadicional_activity);
		mActionBar.setTitle(R.string.informacionadicional_activity_title);
		application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		usuario = application.getUsuarioTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	public void btnSiguiente_click(View view)
	{
		//processAsync();
		
		informacion= new InformacionAdicionalTO();
		String estado = "";
		if(radSSSi.isChecked())
			estado = "S";
		else if(radSSNo.isChecked())
			estado = "N";
		informacion.setComboSS(estado);
		if(radMSSi.isChecked())
			estado = "S";
		else if(radMSNo.isChecked())
			estado = "N";
		informacion.setComboMS(estado);
		if(txtObs.getText().toString().equals(""))
			informacion.setObservacion(" ");
		else
			informacion.setObservacion(txtObs.getText().toString());
		informacion.setCodigoUsuario(usuario.getCodigoSap());
		informacion.setCodigoCliente(cliente.getCodigo());
		informacion.setTipoAgrupacion(AGRUPACION_INVENTARIO);
		application.setInformacionAdicional(informacion);
		
		Intent intent = new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");
		startActivity(intent);
	}
}
