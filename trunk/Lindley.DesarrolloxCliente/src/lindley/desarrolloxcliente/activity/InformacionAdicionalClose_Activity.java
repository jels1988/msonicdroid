package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalCompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarInformacionComboProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import net.msonic.lib.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class InformacionAdicionalClose_Activity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	ClienteTO cliente;
	UsuarioTO usuario;
	private  MyApplication application;
	InformacionAdicionalTO informacion;
	@InjectView(R.id.txtObs) EditText txtObs;
	@InjectView(R.id.txtObsSS) EditText txtObsSS;
	@InjectView(R.id.radSSSi) RadioButton radSSSi;
	@InjectView(R.id.radSSNo) RadioButton radSSNo;
	@InjectView(R.id.radMSSi) RadioButton radMSSi;
	@InjectView(R.id.radMSNo) RadioButton radMSNo;
	@Inject GuardarDesarrolloProxy guardarDesarrolloProxy;
	
	@InjectResource(R.string.btn_cancelar) 				String cancelar;	
	@InjectResource(R.string.confirm_cancelar_title) 	String confirm_cancelar_title;
	@InjectResource(R.string.confirm_cancelar_message)  String confirm_cancelar_message;
	@InjectResource(R.string.confirm_cancelar_yes) 		String confirm_cancelar_yes;
	@InjectResource(R.string.confirm_cancelar_no) 		String confirm_cancelar_no;
	
	@Inject CerrarCompromisoProxy 	  cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@Inject ConsultarInformacionComboProxy consultarInformacionComboProxy;
	
	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacionadicionalclose_activity);
		mActionBar.setTitle(R.string.informacionadicional_activity_title);
		application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		usuario = application.getUsuarioTO();
		mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        mActionBar.setHomeLogo(R.drawable.header_logo);    
        processAsync();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		consultarInformacionComboProxy.codigoRegistro = codigoGestion;
		consultarInformacionComboProxy.execute();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarInformacionComboProxy.isExito();
		if (isExito) {
			int status = consultarInformacionComboProxy.getResponse().getStatus();
			if (status == 0) {
				InformacionAdicionalCompromisoTO informacion = consultarInformacionComboProxy.getResponse().informacion;
				txtObs.setText(informacion.observacion);
				txtObsSS.setText(informacion.observacionSS);
				if(informacion.comboSS.equalsIgnoreCase("S"))
				{
					radSSSi.setChecked(true);
				}
				else
				{
					radSSNo.setChecked(true);
				}
				if(informacion.comboMS.equalsIgnoreCase("S"))
				{
					radMSSi.setChecked(true);
				}
				else
				{
					radMSNo.setChecked(true);
				}
			}
			else
			{
				showToast(consultarInformacionComboProxy.getResponse().getDescripcion());
			}
		}
		super.processOk();
	}
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		showToast(error_generico_process);
		super.processError();
	}
}
