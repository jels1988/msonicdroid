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
	public static MyApplication application;
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
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	public void btnGuardar_click(View view)
	{
		processAsync();
	}
	
	@Override
	protected void process() {
		informacion= new InformacionAdicionalTO();
		String estado = "";
		if(radSSSi.isChecked())
			estado = "1";
		else if(radSSNo.isChecked())
			estado = "0";
		informacion.setComboSS(estado);
		if(radMSSi.isChecked())
			estado = "1";
		else if(radMSNo.isChecked())
			estado = "0";
		informacion.setComboMS(estado);
		informacion.setObservacion(txtObs.getText().toString());
		informacion.setCodigoUsuario(usuario.getCodigoSap());
		informacion.setCodigoCliente(cliente.getCodigo());
		informacion.setTipoAgrupacion(AGRUPACION_INVENTARIO);
		
		guardarDesarrolloProxy.setOportunidadSistema(ConsultarOportunidad_Activity.finalOportunidades);
		guardarDesarrolloProxy.setOportunidadDesarrollador(OportunidadDesarrollador_Activity.oportunidadesDesarrollador);
		guardarDesarrolloProxy.setInformacion(informacion);
		guardarDesarrolloProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = guardarDesarrolloProxy.isExito();
		if (isExito) {
			int status = guardarDesarrolloProxy.getResponse().getStatus();
			if (status == 0) {
				String idRegistro = guardarDesarrolloProxy.getResponse().getCodCabecera();
				System.out.println(idRegistro);
				Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.consultarcompromisoopen");
				compromisoOpen.putExtra(CompromisoOpen_Activity.CODIGO_REGISTRO, idRegistro);
				startActivity(compromisoOpen);
			}
			else  {
				showToast(guardarDesarrolloProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
}
