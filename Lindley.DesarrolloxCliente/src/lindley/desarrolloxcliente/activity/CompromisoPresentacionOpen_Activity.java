package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionCompromisoProxy;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoPresentacionOpen_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public final static String FLAG_FECHA = "fecha_flag";
	@InjectExtra(FLAG_FECHA) static String flagFecha;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	private static final int ACCION_CERRAR = 1;
	private static final int ACCION_ACTUALIZAR = 2;
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	
	public static final String NO = "N";

//	@InjectView(R.id.actionBar)	ActionBar mActionBar;
	@Inject	ConsultarPresentacionCompromisoProxy consultarPresentacionProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	//private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha)	TextView txtViewFecha;
	ClienteTO cliente;
	public static MyApplication application;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultarpresentacioncompromisoopen_activity);
//		mActionBar.setTitle(R.string.consultarpresentacion_activity_title);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
//		mActionBar.setSubTitle(cliente.getNombre());
//		mActionBar.setHomeLogo(R.drawable.header_logo);
		processAsync();
	}

	@Override
	protected void process() {
		consultarPresentacionProxy.setCodigoCliente(cliente.getCodigo());
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
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/"
							+ (c.get(Calendar.MONTH)) + "/"
							+ c.get(Calendar.YEAR));
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
	
	public void btnCerrar_click(View view)
    {
    	processAsync(ACCION_CERRAR);
    }

    public void btnGuardar_click(View view)
    {
    	processAsync(ACCION_ACTUALIZAR);
    }
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean tieneError=false;
		if(accion == ACCION_ACTUALIZAR)
       	{      
			if(application.posicionAdapter.posiciones == null)
			{
				showToast("Debe actualizar los datos de la pestaña Posiciones");
				tieneError=true;
			}
			if(application.presentacionAdapter.detalles == null)
			{
				showToast("Debe actualizar los datos de la pestaña Presentacion");
				tieneError=true;
			}
				
       	}
		return !tieneError;
	}

	@Override
   	protected void process(int accion) {
       	if(accion == ACCION_CERRAR)
       	{
       		cerrarCompromisoProxy.setCodigoCabecera(codigoGestion);
       		cerrarCompromisoProxy.execute();
       	}
       	else if(accion == ACCION_ACTUALIZAR)
       	{       		
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
       			update.listCompromisos = posicion.getListCompromisos();
       			update.tipoAgrupacion = TIPO_POSICION;
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
       				showToast("Los registros se actualizaron correctamente.");
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
    
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	public static class EfficientAdapter extends BaseAdapter implements
			Filterable {
				
		public static EditText txEditFecha;
		public static PresentacionCompromisoTO compromisoTO;

		DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				// TODO Auto-generated method stub

					EfficientAdapter.txEditFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
		    	  if(EfficientAdapter.compromisoTO!=null){
		    		  EfficientAdapter.compromisoTO.setFechaCompromiso(String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth)) );
		    	  }
			}
		};
		
		private LayoutInflater mInflater;
		private Context context;
		public List<PresentacionCompromisoTO> detalles;

		public EfficientAdapter(Context context,
				List<PresentacionCompromisoTO> valores) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
			this.detalles = valores;
		}

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final PresentacionCompromisoTO presentacionTO = (PresentacionCompromisoTO) getItem(position);
			final ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.consultarpresentacioncompromisoopen_content, null);

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();

				holder.txViewVariable = (TextView) convertView
						.findViewById(R.id.txViewVariable);
				holder.txViewPuntos = (TextView) convertView
						.findViewById(R.id.txViewPuntos);
				holder.btnSKU = (Button) convertView.findViewById(R.id.btnSKU);

				holder.txEditFecha = (EditText) convertView
						.findViewById(R.id.txEditFecha);
				holder.btnFecha = (ImageButton) convertView
						.findViewById(R.id.btnFecha);
				holder.txViewFecha = (TextView) convertView
						.findViewById(R.id.txViewFecha);

				holder.chkCnfComp = (CheckBox) convertView
						.findViewById(R.id.chkCnfComp);

				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txViewVariable.setText(presentacionTO
					.getDescripcionVariable());
			holder.txViewPuntos.setText(presentacionTO.getPuntosSugeridos());

			holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = presentacionTO.getFechaCompromiso();
					      if(fecha.length() >= 7)
					      {
					    	  anio =  Integer.parseInt(fecha.substring(0, 4));
					    	  mes  =  Integer.parseInt(fecha.substring(4, 6))-1;
					    	 dia  =  Integer.parseInt(fecha.substring(6));
					    	  
					      }else{
					    	  final Calendar c = Calendar.getInstance();        
					    	  anio = c.get(Calendar.YEAR);        
					    	  mes = c.get(Calendar.MONTH)-1;        
					    	  dia = c.get(Calendar.DAY_OF_MONTH); 
					      }
					      
					      
					      EfficientAdapter.txEditFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = presentacionTO;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes, dia);
					      p.show();
				}
			});
			
			int mYear, mMonth, mDay;
			String fecha = presentacionTO.getFechaCompromiso();
			if (fecha.length() > 7) {
				mYear = Integer.parseInt(fecha.substring(0, 4));
				mMonth = Integer.parseInt(fecha.substring(4, 6));
				mDay = Integer.parseInt(fecha.substring(6));

				holder.txViewFecha.setText(pad(mDay) + "/" + pad(mMonth) + "/"
						+ pad(mYear));
			} else {
				holder.txViewFecha.setText("");
			}

			if (presentacionTO.getConfirmacion().equals("S"))
				holder.chkCnfComp.setSelected(true);
			else
				holder.chkCnfComp.setSelected(false);

			if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
		      {
		    	  holder.txEditFecha.setVisibility(View.GONE);	    	  
		    	  holder.btnFecha.setVisibility(View.GONE);
		    	  holder.txViewFecha.setVisibility(View.VISIBLE);
		      }
		      else
		      {
		    	  holder.txEditFecha.setVisibility(View.VISIBLE);	    	  
		    	  holder.btnFecha.setVisibility(View.VISIBLE);
		    	  holder.txViewFecha.setVisibility(View.GONE);
		      }
			 
			 holder.btnSKU.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MyApplication application = (MyApplication) context.getApplicationContext();
						application.listSKUPresentacionCompromiso = presentacionTO.getListaSKU();
						Intent skuPresentacion = new Intent(context, SKUPrioritarioCompromiso_Activity.class);
						skuPresentacion.putExtra(SKUPrioritarioCompromiso_Activity.FLAG_ESTADO, SKUPrioritarioCompromiso_Activity.FLAG_OPEN_ESTADO_ABIERTO);
						context.startActivity(skuPresentacion);
					}
				});
			 
			return convertView;
		}

		static class ViewHolder {
			TextView txViewVariable;
			TextView txViewPuntos;
			Button btnSKU;

			EditText txEditFecha;
			TextView txViewFecha;
			ImageButton btnFecha;
			CheckBox chkCnfComp;
		}

		@Override
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// return data.length;
			if (detalles == null) {
				return 0;
			} else {
				return detalles.size();
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return detalles.get(position);
		}

	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}
