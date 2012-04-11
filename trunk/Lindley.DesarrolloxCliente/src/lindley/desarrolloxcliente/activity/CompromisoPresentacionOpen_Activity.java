package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionCompromisoProxy;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoPresentacionOpen_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public final static String FLAG_FECHA = "fecha_flag";
	@InjectExtra(FLAG_FECHA) public static String flagFecha;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
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
	
	@InjectView(R.id.btnGuardar) Button btnGuardar;
	@InjectView(R.id.btnCerrar) Button btnCerrar;
	
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
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
		processAsync();
		
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
	
	public void btnCerrar_click(View view)
    {
//    	processAsync(ACCION_CERRAR);
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
				application.posicionAdapter = new CompromisoPosicionOpen_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
			}
			if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
			{
				application.presentacionAdapter = new EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
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
		    		  EfficientAdapter.compromisoTO.setFechaCompromiso(String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth)) );
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
				viewHolder.txViewFecha = (TextView) view
						.findViewById(R.id.txViewFecha);

				viewHolder.chkCnfComp = (CheckBox) view
						.findViewById(R.id.chkCnfComp);

				viewHolder.chkCnfComp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						PresentacionCompromisoTO compromiso = (PresentacionCompromisoTO) viewHolder.chkCnfComp.getTag();
						if(isChecked){
							compromiso.setConfirmacion("S");
						}else{
							compromiso.setConfirmacion("N");
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.chkCnfComp.setTag(this.detalles.get(position));
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				view = convertView;				
				((ViewHolder) view.getTag()).chkCnfComp.setTag(this.detalles.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final PresentacionCompromisoTO presentacionTO = detalles.get(position);
			
//			holder.txViewVariable.setText(presentacionTO
//					.getDescripcionVariable());
			holder.txViewPuntos.setText(presentacionTO.getPuntosSugeridos());

			
			holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = presentacionTO.getFechaCompromiso();
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
				holder.chkCnfComp.setChecked(true);
			else
				holder.chkCnfComp.setChecked(false);

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
						skuPresentacion.putExtra(SKUPrioritarioCompromiso_Activity.FLAG_FECHA, flagFecha);
						context.startActivity(skuPresentacion);
					}
				});
			 
			return view;
		}

		static class ViewHolder {
//			TextView txViewVariable;
			TextView txViewPuntos;
			Button btnSKU;

			EditText txEditFecha;
			TextView txViewFecha;
			ImageButton btnFecha;
			CheckBox chkCnfComp;
		}

	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}
