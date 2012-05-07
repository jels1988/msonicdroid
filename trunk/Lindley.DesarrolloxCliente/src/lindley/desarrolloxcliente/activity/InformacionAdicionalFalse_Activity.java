package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.to.CerrarSKUPresentacionTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalCompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarInformacionComboProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import net.msonic.lib.ActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class InformacionAdicionalFalse_Activity extends ActivityBase {
	
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
	
	private final int ACCION_CERRAR = 1;
	
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
		setContentView(R.layout.informacionadicional_activityfalse);
		mActionBar.setTitle(R.string.informacionadicional_activity_title);
		application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		usuario = application.getUsuarioTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
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
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				/*
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
				startActivity(intent);
				*/
				finish();
			}
			
		}, confirm_cancelar_no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub				
			}
			
		});
	}
	
	 public void btnCancelar_click(View view)
	 {
		 onBackPressed();
	 }

	    public void btnCerrar_click(View view)
	    {
	    	processAsync(ACCION_CERRAR);
	    }
		
	    @Override
	    protected boolean executeAsyncPre(int accion) {
			// TODO Auto-generated method stub
			boolean openAdapterVacio = false;
			boolean posicionAdapterVacio = false;
			boolean presentacionAdapterVacio = false;
		
			if(accion == ACCION_CERRAR)
	       	{    
				if(application.openFalseAdapter == null || application.openFalseAdapter.detalles.isEmpty())
				{				
					application.openFalseAdapter = new CompromisoOpenFalse_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
					openAdapterVacio = true;
					if(openAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña inventario.");
						return false;
					}
				}
				if(application.posicionFalseAdapter == null || application.posicionFalseAdapter.posiciones.isEmpty())
				{				
					application.posicionFalseAdapter = new CompromisoPosicionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
					posicionAdapterVacio = true;
					if(posicionAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña posición.");
						return false;
					}
				}
				if(application.presentacionFalseAdapter == null || application.presentacionFalseAdapter.detalles.isEmpty())
				{
					application.presentacionFalseAdapter = new CompromisoPresentacionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
					presentacionAdapterVacio = true;
					if(presentacionAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña presentación.");
						return false;
					}
				}
					
	       	}
			
			if(openAdapterVacio && posicionAdapterVacio && presentacionAdapterVacio)
			{			
				showToast("Debe editar los datos.");
				return false;
			}
			else
			{
				return true;
			}
		}
	    
	    @Override
	    protected void process(int accion) {
	    	
	    	if(accion == ACCION_CERRAR)
	    	{    		
	    		List<CerrarInventarioTO> listCerrarCompromisoTO = new ArrayList<CerrarInventarioTO>();
	       		for(CompromisoTO compromiso : application.openFalseAdapter.detalles)
	    		{
	       			CerrarInventarioTO cerrar = new CerrarInventarioTO();
	       			cerrar.codigoProducto = compromiso.codigoProducto;
	       			cerrar.concrecionCumplio = compromiso.concrecionCumplio;
	       			cerrar.soviCumplio = compromiso.soviCumplio;
	       			cerrar.cumplePrecioCumplio = compromiso.cumplePrecioCumplio;
	       			cerrar.numeroSaboresCumplio = compromiso.numeroSaboresCumplio;
	       			
	       			listCerrarCompromisoTO.add(cerrar);
	    		}
	       		
	       		List<CerrarPosicionTO> listCerrarPosicionTO = new ArrayList<CerrarPosicionTO>();
	       		for(PosicionCompromisoTO posicion : application.posicionFalseAdapter.posiciones)
	    		{
	       			CerrarPosicionTO cerrar = new CerrarPosicionTO();
	       			cerrar.codigoVariable = posicion.codigoVariable;
	       			cerrar.cumplio = posicion.cumplio;
	       			cerrar.fotoFinal = posicion.fotoFinal;
	       			listCerrarPosicionTO.add(cerrar);
	    		}
	       		
	       		List<CerrarPresentacionTO> listCerrarPresentacionTO = new ArrayList<CerrarPresentacionTO>();
	    		for(PresentacionCompromisoTO presentacion : application.presentacionFalseAdapter.detalles)
	    		{
	    			CerrarPresentacionTO cerrar = new CerrarPresentacionTO();
	    			cerrar.codigoVariable = presentacion.codigoVariable;
	    			cerrar.cumplio = presentacion.cumplio;
	    			
	    			List<CerrarSKUPresentacionTO> listCerrarSKUPresentacionTO = new ArrayList<CerrarSKUPresentacionTO>();
	    			for(SKUPresentacionCompromisoTO sku : presentacion.listaSKU)
	    			{
	    				CerrarSKUPresentacionTO cerrarSku = new CerrarSKUPresentacionTO();
	    				cerrarSku.codigoSKU = sku.codigoSKU;
	    				cerrarSku.cumplio = sku.cumplio;
	    				listCerrarSKUPresentacionTO.add(cerrarSku);
	    			}
	    			cerrar.listaSKU = listCerrarSKUPresentacionTO;
	    			
	    			listCerrarPresentacionTO.add(cerrar);
	    		}
	    		
	    		cerrarCompromisoProxy.listaPosicionCompromiso = listCerrarPosicionTO;
	    		cerrarCompromisoProxy.listaPresentacionCompromiso = listCerrarPresentacionTO;
	    		cerrarCompromisoProxy.listaInventarioCompromiso = listCerrarCompromisoTO;
	    		cerrarCompromisoProxy.codigoCabecera = codigoGestion;
	    		UsuarioTO user = application.getUsuarioTO();
	    		cerrarCompromisoProxy.codigoUsuario = user.getCodigoSap();
	    		cerrarCompromisoProxy.execute();
	    	}
	    		
		}
	    
	    protected void processOk(int accion) {
	   		// TODO Auto-generated method stub
	    	if(accion == ACCION_CERRAR)
	    	{
	    		boolean isExito = cerrarCompromisoProxy.isExito();
	       		if (isExito) {
	       			int status = cerrarCompromisoProxy.getResponse().getStatus();
	       			if (status == 0) {
	       				setAdapterApplication();
	       				showToast("Los registros se cerrarón satisfactoriamente.");
	       				Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");					
						startActivity(cabecera);
	       			}
	       			else  {
	       				showToast(cerrarCompromisoProxy.getResponse().getDescripcion());
	       			}
	       		}
	       		else{
	       			processError();
	       		}
	    	}
	   		super.processOk();
	   	} 
	    
	    private void setAdapterApplication() {
			// TODO Auto-generated method stub
	    	application.openAdapter = null;
	    	application.posicionAdapter = null;
	    	application.presentacionAdapter = null;
		}
	    
	    @Override
		protected void processError(int accion) {
			// TODO Auto-generated method stub
			super.processError();
			showToast(error_generico_process);
		}
}
