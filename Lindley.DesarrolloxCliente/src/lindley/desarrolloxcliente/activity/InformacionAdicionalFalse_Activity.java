package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpen_Activity.EfficientAdapter;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.to.CerrarSKUPresentacionTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
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
	private final String AGRUPACION_INVENTARIO = "1";
	private final String OPORTUNIDAD_DESARROLLADOR_ABIERTO = "A";
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
		super.process();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		super.processOk();
	}
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	 public void btnCancelar_click(View view)
	    {
//	    	processAsync(ACCION_CERRAR);
	    	MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub	
					Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
					startActivity(intent);
				}
				
			}, confirm_cancelar_no, new android.content.DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});  
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
				if(application.openAdapter == null || application.openAdapter.detalles.isEmpty())
				{				
					application.openAdapter = new CompromisoOpen_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
					for(CompromisoTO comp : application.openAdapter.detalles)
					{
						if(Integer.parseInt(comp.sovi)<=0 && Integer.parseInt(comp.soviActual)<=0)
						{
							showToast("Los valores de SOVI deben ser mayores a 0");
							return false;
						}
					}
					openAdapterVacio = true;
					if(openAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña inventario.");
						return true;
					}
				}
				if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
				{				
					application.posicionAdapter = new EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
					posicionAdapterVacio = true;
					if(posicionAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña posición.");
						return true;
					}
				}
				if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
				{
					application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
					presentacionAdapterVacio = true;
					if(presentacionAdapterVacio)
					{
						showToast("Debe editar valores de la pestaña presentación.");
						return true;
					}
				}
				if(application.informacionAdicional == null)
				{
					showToast("Debe editar valores de la pestaña combos.");
					return true;
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
	       		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
	    		{
	       			CerrarPosicionTO cerrar = new CerrarPosicionTO();
	       			cerrar.codigoVariable = posicion.codigoVariable;
	       			cerrar.cumplio = posicion.cumplio;
	       			listCerrarPosicionTO.add(cerrar);
	    		}
	       		
	       		List<CerrarPresentacionTO> listCerrarPresentacionTO = new ArrayList<CerrarPresentacionTO>();
	    		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
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
