package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.activity.CompromisoPosicionOpen_Activity.EfficientAdapter;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import net.msonic.lib.ActivityBase;
import net.msonic.lib.MessageBox;

public class InformacionAdicional_Activity extends ActivityBase {

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
	private final int ACCION_ACTUALIZAR = 2;
	
	@Inject CerrarCompromisoProxy 	  cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	
	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public final static String FLAG_FECHA = "fecha_flag";
	@InjectExtra(FLAG_FECHA) static String flagFecha;
	
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
	
	@InjectView(R.id.btnGuardar) Button btnGuardar;
	@InjectView(R.id.btnCerrar) Button btnCerrar;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
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
        
        if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
	    {
        	btnGuardar.setVisibility(View.GONE);
	    }
        else
        {
//        	btnCerrar.setVisibility(View.GONE);
        	btnCerrar.setText(cancelar);
        }
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
		informacion.setObservacionSS(txtObsSS.getText().toString());
		informacion.setCodigoUsuario(usuario.getCodigoSap());
		informacion.setCodigoCliente(cliente.getCodigo());
		informacion.setTipoAgrupacion(AGRUPACION_INVENTARIO);
		application.setInformacionAdicional(informacion);
		
//		Intent intent;
//		String a = "C";
//		if(a.equals(OPORTUNIDAD_DESARROLLADOR_ABIERTO))
//		{
//			intent= new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");		
//			startActivity(intent);
//		}
//		else
//		{
//			intent = new Intent("lindley.desarrolloxcliente.consultarposicion");
//			startActivity(intent);
//		}
	}
	
	
	
	
	 public void btnCerrar_click(View view)
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

	    public void btnGuardar_click(View view)
	    {
	    	processAsync(ACCION_ACTUALIZAR);
	    }
		
	    @Override
		protected boolean executeAsyncPre(int accion) {
			// TODO Auto-generated method stub
			boolean openAdapterVacio = false;
			boolean posicionAdapterVacio = false;
			boolean presentacionAdapterVacio = false;
			if(accion == ACCION_ACTUALIZAR || accion == ACCION_CERRAR)
	       	{    
				if(application.openAdapter == null || application.openAdapter.detalles.isEmpty())
				{				
					application.openAdapter = new CompromisoOpen_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
					openAdapterVacio = true;
				}
				if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
				{				
					application.posicionAdapter = new EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
					posicionAdapterVacio = true;
				}
				if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
				{
					application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
					presentacionAdapterVacio = true;
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
			List<UpdatePosicionTO> listUpdatePosicionTO = new ArrayList<UpdatePosicionTO>();
	   		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
			{
	   			UpdatePosicionTO update = new UpdatePosicionTO();
	   			update.accionCompromiso = posicion.getAccionCompromiso();
	   			if(update.accionCompromiso.equalsIgnoreCase("")) update.accionCompromiso = " ";
	   			update.codigoRegistro = codigoGestion; 
	   			update.codigoVariable = posicion.getCodigoVariable();
	   			update.confirmacion = posicion.getConfirmacion();
	   			update.fechaCompromiso = posicion.getFechaCompromiso();
	   			if(application.listCompromiso == null)
	   				application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
	      		update.listCompromisos = application.listCompromiso;
	   			update.tipoAgrupacion = TIPO_POSICION;
	   			update.fotoInicial = posicion.getFotoInicial();
	   			update.fotoFinal = posicion.getFotoFinal();
	   			listUpdatePosicionTO.add(update);
			}
	   		
	   		List<UpdatePresentacionTO> listUpdatePresentacionTO = new ArrayList<UpdatePresentacionTO>();
			for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
			{
				UpdatePresentacionTO update = new UpdatePresentacionTO();
				update.codigoRegistro = codigoGestion;
				update.tipoAgrupacion = TIPO_PRESENTACION;
				update.codigoVariable = presentacion.getCodigoVariable();
				update.confirmacion = presentacion.getConfirmacion();
				update.fechaCompromiso = presentacion.getFechaCompromiso();
				List<UpdateSKUPresentacionTO> skucompromisos = new ArrayList<UpdateSKUPresentacionTO>();
				for(SKUPresentacionCompromisoTO skupresentacionCompromisoTO :  presentacion.getListaSKU())
				{
					UpdateSKUPresentacionTO updateSKUPresentacionTO = new UpdateSKUPresentacionTO();
					updateSKUPresentacionTO.codigoSKU = skupresentacionCompromisoTO.getCodigoSKU();
					updateSKUPresentacionTO.compromiso = skupresentacionCompromisoTO.getCompromiso();
					if(updateSKUPresentacionTO.compromiso.equalsIgnoreCase(" ")) updateSKUPresentacionTO.compromiso = NO;
					updateSKUPresentacionTO.confirmacion = skupresentacionCompromisoTO.getConfirmacion();
					if(updateSKUPresentacionTO.confirmacion.equalsIgnoreCase(" ")) updateSKUPresentacionTO.confirmacion = NO;
					skucompromisos.add(updateSKUPresentacionTO);
				}
				update.listaSKU = skucompromisos;    			
				listUpdatePresentacionTO.add(update);
			}
					
	       	if(accion == ACCION_CERRAR)
	       	{       		
	    		cerrarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
	    		cerrarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
	    		cerrarCompromisoProxy.setCompromisos(application.openAdapter.detalles);
	       		cerrarCompromisoProxy.setCodigoCabecera(codigoGestion);
	       		UsuarioTO user = application.getUsuarioTO();
	    		cerrarCompromisoProxy.codigoUsuario = user.getCodigoSap();
	       		cerrarCompromisoProxy.execute();
	       	}
	       	else if(accion == ACCION_ACTUALIZAR)
	       	{    		
	    		actualizarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
	    		actualizarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
	       		actualizarCompromisoProxy.setCompromisos(application.openAdapter.detalles);
	       		actualizarCompromisoProxy.execute();
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
	    	else if(accion == ACCION_ACTUALIZAR)
	    	{
	    		boolean isExito = actualizarCompromisoProxy.isExito();
	       		if (isExito) {
	       			int status = actualizarCompromisoProxy.getResponse().getStatus();
	       			if (status == 0) {
	       				setAdapterApplication();
	       				showToast("Los registros se actualizarón correctamente.");
	       				
	       				Intent intentService = new Intent("lindley.desarrolloxcliente.uploadFileService");
	       				startService(intentService);
	       				
	       				Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.consultarcabecera");
						startActivity(compromisoOpen);
	       			}
	       			else  {
	       				showToast(actualizarCompromisoProxy.getResponse().getDescripcion());
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
