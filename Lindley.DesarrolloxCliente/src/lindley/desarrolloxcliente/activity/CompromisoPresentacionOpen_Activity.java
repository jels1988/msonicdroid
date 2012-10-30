package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.to.CerrarSKUPresentacionTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
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
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionCompromisoProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoPresentacionOpen_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
		
	private static final int ACCION_CERRAR = 1;
	private static final int ACCION_ACTUALIZAR = 2;
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";

	@Inject	ConsultarPresentacionCompromisoProxy consultarPresentacionProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@InjectView(R.id.txtViewFecha)	TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	ClienteTO cliente;
	public static MyApplication application;
	
	@InjectResource(R.string.btn_cancelar) 				String cancelar;	
	@InjectResource(R.string.confirm_cancelar_title) 	String confirm_cancelar_title;
	@InjectResource(R.string.confirm_cancelar_message)  String confirm_cancelar_message;
	@InjectResource(R.string.confirm_cancelar_yes) 		String confirm_cancelar_yes;
	@InjectResource(R.string.confirm_cancelar_no) 		String confirm_cancelar_no;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultarpresentacioncompromisoopen_activity);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		processAsync();		
	}

	@Override
	protected void process()  throws Exception{
		consultarPresentacionProxy.setCodigoCliente(cliente.codigo);
		consultarPresentacionProxy.setCodigoRegistro(codigoGestion);
		consultarPresentacionProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPresentacionProxy.isExito();
		if (isExito) {
			int status = consultarPresentacionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PresentacionCompromisoTO> presentaciones = consultarPresentacionProxy
						.getResponse().getListaCompromisos();
				
				application.presentacionAdapter = new EfficientAdapter(this, presentaciones);
				final Calendar c = Calendar.getInstance();
				if (presentaciones.size() > 0)
					txtViewFecha.setText(pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(application.presentacionAdapter);
			} else {
				showToast(consultarPresentacionProxy.getResponse()
						.getDescripcion());
			}
		} else {
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
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//finish();
				
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
					showToast("Los valores de SOVI deben ser mayores a 0");
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
					return false;
				}
			}
			if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
			{				
				application.posicionAdapter = new CompromisoPosicionOpen_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
				if(posicionAdapterVacio)
				{
					showToast("Debe editar valores de la pesta–a posici—n.");
					return false;
				}
			}
			if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
			{
				application.presentacionAdapter = new EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
				if(presentacionAdapterVacio)
				{
					showToast("Debe editar valores de la pesta–a presentaci—n.");
					return false;
				}
			}
			if(application.informacionAdicional == null)
			{
				showToast("Debe editar valores de la pesta–a combos.");
				return false;
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
	
    protected void process(int accion)  throws Exception{
		
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
       				showToast("Los registros se actualizaron correctamente.");
       				
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
		if(accion == ACCION_ACTUALIZAR || accion == ACCION_CERRAR)
    	{
			showToast(error_generico_process);
    	}
	}
	
	public static class EfficientAdapter extends ArrayAdapter<PresentacionCompromisoTO>{
				
		public static EditText txEditFecha;
		public static PresentacionCompromisoTO compromisoTO;

		public EfficientAdapter(Activity context, List<PresentacionCompromisoTO> valores) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			super(context, R.layout.consultarpresentacioncompromisoopen_content, valores);
			this.context = context;
			this.detalles = valores;
		}
		
		DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				// TODO Auto-generated method stub

					EfficientAdapter.txEditFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
		    	  if(EfficientAdapter.compromisoTO!=null){
		    		  EfficientAdapter.compromisoTO.fechaCompromiso = (String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth)) );
		    	  }
			}
		};
		
		private Activity context;
		public List<PresentacionCompromisoTO> detalles;

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		public View getView( int position, View convertView,
				ViewGroup parent) {
			//final PresentacionCompromisoTO presentacionTO = (PresentacionCompromisoTO) getItem(position);
			//final ViewHolder holder;

			View view = null;
			if (convertView == null) {
				
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultarpresentacioncompromisoopen_content, null);
				final ViewHolder viewHolder = new ViewHolder();

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.

				viewHolder.txViewPuntos = (TextView) view
						.findViewById(R.id.txViewPuntos);
//				viewHolder.txViewVariable = (TextView) view
//						.findViewById(R.id.txViewVariable);
				
				viewHolder.btnSKU = (Button) view.findViewById(R.id.btnSKU);

				viewHolder.txEditFecha = (EditText) view
						.findViewById(R.id.txEditFecha);
				viewHolder.btnFecha = (ImageButton) view
						.findViewById(R.id.btnFecha);			
				
				view.setTag(viewHolder);
				viewHolder.btnFecha.setTag(this.detalles.get(position));
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				view = convertView;				
				((ViewHolder) view.getTag()).btnFecha.setTag(this.detalles.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final PresentacionCompromisoTO presentacionTO = detalles.get(position);
			
//			holder.txViewVariable.setText(presentacionTO
//					.getDescripcionVariable());
			holder.txViewPuntos.setText(presentacionTO.puntosSugeridos);

			String fecha = presentacionTO.fechaCompromiso;
			int anio;
			int mes;
			int dia;
			final Calendar c = Calendar.getInstance();
			if (fecha.length() >= 7) {
				anio = Integer.parseInt(fecha.substring(0, 4));
				mes = Integer.parseInt(fecha.substring(4, 6)) - 1;
				dia = Integer.parseInt(fecha.substring(6));
				c.set(anio, mes, dia);
				if (dia >= 30 && mes == 1)
					dia = c.get(Calendar.DAY_OF_MONTH);
				else if (dia >= 30)
					dia = c.get(Calendar.DAY_OF_MONTH);
				holder.txEditFecha.setText(ActivityUtil.pad(dia)+"/"+ActivityUtil.pad(mes+1)+"/"+anio);
			}
			
			holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = presentacionTO.fechaCompromiso;
					   final Calendar c = Calendar.getInstance();
					   if(fecha.length() >= 7)
					      {
					    	  anio =  Integer.parseInt(fecha.substring(0, 4));
					    	  mes  =  Integer.parseInt(fecha.substring(4, 6))-1;
					    	  dia  =  Integer.parseInt(fecha.substring(6));
					    	  c.set(anio, mes, dia);					    	  
					    	  if (dia>=30 && mes == 1)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  else if (dia>=30)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  
					      }else{				    	          
					    	  anio = c.get(Calendar.YEAR);        
					    	  mes = c.get(Calendar.MONTH);        
					    	  dia = c.get(Calendar.DAY_OF_MONTH); 
					      }
					      
					      
					      EfficientAdapter.txEditFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = presentacionTO;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes--, dia);
					      p.show();
				}
			});
			
					 
			 holder.btnSKU.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MyApplication application = (MyApplication) context.getApplicationContext();
						application.listSKUPresentacionCompromiso = presentacionTO.listaSKU;
						Intent skuPresentacion = new Intent(context, SKUPrioritarioCompromiso_Activity.class);
						context.startActivity(skuPresentacion);
					}
				});
			 
			return view;
		}

		static class ViewHolder {
			TextView txViewPuntos;
			Button btnSKU;
			EditText txEditFecha;
			ImageButton btnFecha;
		}

	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}
