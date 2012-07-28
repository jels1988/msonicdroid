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
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalCompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdateInformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UpdateInventarioTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
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

public class InformacionAdicional_Activity extends ActivityBase {

//	private final String OPORTUNIDAD_DESARROLLADOR_ABIERTO = "A";
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
	@Inject ConsultarInformacionComboProxy consultarInformacionComboProxy;
	
	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
			
	public static final String ORIGEN = "origen_act";
	@InjectExtra(ORIGEN) String origen_act;
	
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
		mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync();
        
        application.informacionAdicional = new InformacionAdicionalTO();
        
        /*
        txtObs.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				application.informacionAdicional.setObservacion(txtObs.getText().toString());
			}
		});
        
        txtObsSS.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				application.informacionAdicional.setObservacionSS(txtObsSS.getText().toString());
			}
		});
        
        radSSSi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					application.informacionAdicional.setComboSS("S");		
				else
					application.informacionAdicional.setComboSS("N");
			}
		});
        
        radSSNo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					application.informacionAdicional.setComboSS("N");
				else
					application.informacionAdicional.setComboSS("S");
			}
		});
        
        radMSSi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					application.informacionAdicional.setComboMS("S");
				else
					application.informacionAdicional.setComboMS("N");
			}
		});
        
        radMSNo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					application.informacionAdicional.setComboMS("N");
				else
					application.informacionAdicional.setComboMS("S");
			}
		});
        */
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
	
//	public void btnSiguiente_click(View view)
//	{
//		//processAsync();
//		
//		informacion= new InformacionAdicionalTO();
//		String estado = "";
//		if(radSSSi.isChecked())
//			estado = "S";
//		else if(radSSNo.isChecked())
//			estado = "N";
//		informacion.setComboSS(estado);
//		if(radMSSi.isChecked())
//			estado = "S";
//		else if(radMSNo.isChecked())
//			estado = "N";
//		informacion.setComboMS(estado);
//		if(txtObs.getText().toString().equals(""))
//			informacion.setObservacion(" ");
//		else
//			informacion.setObservacion(txtObs.getText().toString());
//		informacion.setObservacionSS(txtObsSS.getText().toString());
//		informacion.setCodigoUsuario(usuario.getCodigoSap());
//		informacion.setCodigoCliente(cliente.getCodigo());
//		informacion.setTipoAgrupacion(AGRUPACION_INVENTARIO);
//		application.informacionAdicional = informacion;
//		
////		Intent intent;
////		String a = "C";
////		if(a.equals(OPORTUNIDAD_DESARROLLADOR_ABIERTO))
////		{
////			intent= new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");		
////			startActivity(intent);
////		}
////		else
////		{
////			intent = new Intent("lindley.desarrolloxcliente.consultarposicion");
////			startActivity(intent);
////		}
//	}
	
	@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
		MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcabecera");
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
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
				for(CompromisoTO comp : application.openAdapter.detalles)
				{
					if(Integer.parseInt(comp.sovi)<=0 || Integer.parseInt(comp.soviActual)<=0)
					{
						showToast("Los valores de SOVI deben ser mayores a 0.");
						return false;
					}
				}
				if(application.openAdapter == null || application.openAdapter.detalles.isEmpty())
				{				
					application.openAdapter = new CompromisoOpen_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
					openAdapterVacio = true;
					if(openAdapterVacio)
					{
						showToast("Debe editar valores de la pesta–a inventario.");
						return true;
					}
				}
				if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
				{				
					application.posicionAdapter = new EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
					posicionAdapterVacio = true;
					if(posicionAdapterVacio)
					{
						showToast("Debe editar valores de la pesta–a posicion.");
						return true;
					}
				}
				if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
				{
					application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
					presentacionAdapterVacio = true;
					if(presentacionAdapterVacio)
					{
						showToast("Debe editar valores de la pesta–a presentacion.");
						return true;
					}
				}
				if(application.informacionAdicional == null)
				{
					showToast("Debe editar valores de la pestana combos.");
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
			
	    	if(radMSNo.isChecked())
    			application.informacionAdicional.setComboMS("N");
    		else
    			application.informacionAdicional.setComboMS("S");
    		
    		
    		if(radSSNo.isChecked())
    			application.informacionAdicional.setComboSS("N");
    		else
    			application.informacionAdicional.setComboSS("S");
    		
    		
    		application.informacionAdicional.setObservacion(txtObs.getText().toString());
    		application.informacionAdicional.setObservacionSS(txtObsSS.getText().toString());
    		
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
	    	else if(accion == ACCION_ACTUALIZAR)
	    	{    		
	    		
	    		
	    		List<UpdateInventarioTO> listUpdateCompromisoTO = new ArrayList<UpdateInventarioTO>();
	       		for(CompromisoTO compromiso : application.openAdapter.detalles)
	    		{
	       			UpdateInventarioTO update = new UpdateInventarioTO();
	       			update.codigoProducto = compromiso.codigoProducto;
	       			update.concrecionActual = compromiso.concrecionActual;
	       			update.sovi = compromiso.sovi;
	       			update.soviActual = compromiso.soviActual;
	       			update.cumplePrecio = compromiso.cumplePrecio;
	       			update.cumplePrecioActual = compromiso.cumplePrecioActual;
	       			update.numeroSaboresActual = compromiso.numeroSaboresActual;
	       			update.fechaCompromiso = compromiso.fechaCompromiso;
	       			update.codigoAccionTrade = compromiso.codigoAccionTrade;
	       			update.descAccionTrade = compromiso.descAccionTrade;
	       			listUpdateCompromisoTO.add(update);
	    		}
	       		
	       		List<UpdatePosicionTO> listUpdatePosicionTO = new ArrayList<UpdatePosicionTO>();
	       		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
	    		{
	       			UpdatePosicionTO update = new UpdatePosicionTO();
	       			update.accionCompromiso = posicion.observacion;
	       			update.codigoVariable = posicion.codigoVariable;
	       			update.fechaCompromiso = posicion.fechaCompromiso;
	       			if(application.listCompromiso == null)
	       			 application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
	       			update.listCompromisos = application.listCompromiso;
	       			update.fotoInicial = posicion.fotoInicial;
	       			listUpdatePosicionTO.add(update);
	    		}
	       		
	       		List<UpdatePresentacionTO> listUpdatePresentacionTO = new ArrayList<UpdatePresentacionTO>();
	    		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
	    		{
	    			UpdatePresentacionTO update = new UpdatePresentacionTO();
	    			update.codigoVariable = presentacion.codigoVariable;
	    			update.fechaCompromiso = presentacion.fechaCompromiso;
	    			
	    			List<UpdateSKUPresentacionTO> skucompromisos = new ArrayList<UpdateSKUPresentacionTO>();
	    			for(SKUPresentacionCompromisoTO skupresentacionCompromisoTO :  presentacion.listaSKU)
	    			{
	    				UpdateSKUPresentacionTO updateSKUPresentacionTO = new UpdateSKUPresentacionTO();
	    				updateSKUPresentacionTO.codigoSKU = skupresentacionCompromisoTO.codigoSKU;
	    				    				
	    				updateSKUPresentacionTO.compromiso = skupresentacionCompromisoTO.compromiso;
	    				if(updateSKUPresentacionTO.compromiso.equalsIgnoreCase(" ")) updateSKUPresentacionTO.compromiso = NO;
	    				
	    				skucompromisos.add(updateSKUPresentacionTO);
	    			}
	    			update.listaSKU = skucompromisos;    			
	    			listUpdatePresentacionTO.add(update);
	    		}
	    		
	    		InformacionAdicionalTO informacion = application.informacionAdicional;
	    		UpdateInformacionAdicionalTO update = new UpdateInformacionAdicionalTO();
	    		update.comboMS = informacion.getComboMS();
	    		update.comboSS = informacion.getComboSS();
	    		update.observacion = informacion.getObservacion();
	    		update.observacionSS = informacion.getObservacionSS();
	    		
	    		actualizarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
	    		actualizarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
	       		actualizarCompromisoProxy.listaInventarioCompromiso = listUpdateCompromisoTO;
	       		actualizarCompromisoProxy.updateInformacionAdicionalTO = update;
	       		actualizarCompromisoProxy.codigoCabecera = codigoGestion;
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
	       				showToast("Los registros se cerraron satisfactoriamente.");
	       				
	       				this.finish();
	       				
//	       				Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");					
//						startActivity(cabecera);
//	       				finish();
						if(origen_act!=null)
	       				{
	       					if(origen_act.equals("1"))
	       					{
	       						Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");					
	       						startActivity(cabecera);
	       					}
	       					else
	       					{
	       						this.finish();
	       					}
	       					
	       				}
	       				else
	       				{
	       					this.finish();
	       				}
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
	       				showToast("Los registros se actualizaron correctamente.");
	       				
	       				this.finish();
	       				Intent intentService = new Intent("lindley.desarrolloxcliente.uploadFileService");
	       				startService(intentService);
	       				
	       				this.finish();
	       				if(origen_act!=null)
	       				{
	       					if(origen_act.equals("N"))
	       					{
	       						Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");					
	       						startActivity(cabecera);
	       					}
	       					else if(origen_act.equals("A"))
	       					{
	       						finish();	
	       					}
	       					else
	       					{
	       						finish();
	       					}
	       					
	       				}
	       				else
	       				{
	       					this.finish();
	       				}
//	       				finish();
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
