package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoOpen_Activity extends ListActivityBase {

	public final static String CODIGO_REGISTRO = "codigo_reg";
	public final static String FLAG_FECHA = "fecha_flag";

	private static final int ACCION_CERRAR = 1;

	private static final int ACCION_ACTUALIZAR = 2;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";

	public static final String NO = "N";
	
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@InjectExtra(FLAG_FECHA) static String flagFecha;
//	@InjectView(R.id.actionBar)   ActionBar 	mActionBar;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	//private EfficientAdapter adap;
	ClienteTO cliente;
	public static MyApplication application;
	List<CompromisoTO> compromisos;
	

	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
    	compromisos = new ArrayList<CompromisoTO>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoopen_activity);        
//        mActionBar.setTitle(R.string.compromiso_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
//        mActionBar.setSubTitle(cliente.getNombre());
//        mActionBar.setHomeLogo(R.drawable.header_logo);        
        
        processAsync();
    }
    
    
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
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
			if(application.posicionAdapter.posiciones.isEmpty() || application.posicionAdapter.posiciones == null)
			{
				showToast("Debe actualizar los datos de la pestaña Posiciones");
				tieneError=true;
			}
			if(application.presentacionAdapter.detalles.isEmpty() || application.presentacionAdapter.detalles == null)
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
    		cerrarCompromisoProxy.setCodigoCabecera(codigoRegistro);
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
       			update.codigoRegistro = codigoRegistro; 
       			update.codigoVariable = posicion.getCodigoVariable();
       			update.confirmacion = posicion.getConfirmacion();
       			update.fechaCompromiso = posicion.getFechaCompromiso();
       			update.listCompromisos = posicion.getListCompromisos();
       			update.tipoAgrupacion = TIPO_POSICION;
       			update.fotoInicial = posicion.getFotoInicial();
       			update.fotoFinal = posicion.getFotoFinal();
       			listUpdatePosicionTO.add(update);
    		}
       		
       		List<UpdatePresentacionTO> listUpdatePresentacionTO = new ArrayList<UpdatePresentacionTO>();
    		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
    		{
    			UpdatePresentacionTO update = new UpdatePresentacionTO();
    			update.codigoRegistro = codigoRegistro;
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
    
    @Override
	protected void process() {
    	consultarCompromisoProxy.setCodigoCliente(cliente.getCodigo());
    	consultarCompromisoProxy.setCodigoRegistro(codigoRegistro);
    	consultarCompromisoProxy.execute();
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				compromisos = consultarCompromisoProxy
						.getResponse().getListaCompromiso();
				if(compromisos.size()>0)
					txtViewFecha.setText(compromisos.get(0).getFecha());
				application.openAdapter = new EfficientAdapter(this, compromisos);
				setListAdapter(application.openAdapter);
			}
			else  {
				showToast(consultarCompromisoProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		super.processOk();
	}
    
    @Override
   	protected void processOk(int accion) {
   		// TODO Auto-generated method stub
    	if(accion == ACCION_CERRAR)
    	{
    		boolean isExito = cerrarCompromisoProxy.isExito();
       		if (isExito) {
       			int status = cerrarCompromisoProxy.getResponse().getStatus();
       			if (status == 0) {
       				showToast("Los registros se cerrar—n satisfactoriamente.");
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
    
    @Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    @Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    public static class EfficientAdapter extends BaseAdapter{
    	
    	   public static EditText txtFecha;
    	   public static CompromisoTO compromisoTO;
    	 
    		 
    		 
    	    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
    			
    			@Override
    			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
    				// TODO Auto-generated method stub

    					EfficientAdapter.txtFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
    		    	  if(EfficientAdapter.compromisoTO!=null){
    		    		  EfficientAdapter.compromisoTO.setFechaCompromiso(String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth)) );
    		    	  }
    			}
    		};
    		
	    private LayoutInflater mInflater;
	    private Context context;
	    public List<CompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<CompromisoTO> valores) {
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
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final CompromisoTO compromiso = (CompromisoTO) getItem(position);
	    	final ViewHolder holder;

	      //if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.compromisoopen_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    		    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.cboConcrecion = (TextView) convertView.findViewById(R.id.cboConcrecion);
	        holder.chkConcrecion = (CheckBox) convertView.findViewById(R.id.chkConcrecion);
	        holder.txViewSOVI =  (EditText) convertView.findViewById(R.id.txViewSOVI);
	        holder.chkSOVI = (CheckBox) convertView.findViewById(R.id.chkSOVI);
	        holder.cboCumPrecio =  (Spinner) convertView.findViewById(R.id.cboCumPrecio);
	        holder.chkPrecio = (CheckBox) convertView.findViewById(R.id.chkPrecio);
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);
	        holder.chkSabores = (CheckBox) convertView.findViewById(R.id.chkSabores);
	        
	        
	    	holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);
	    	holder.txEditFecha = (EditText) convertView.findViewById(R.id.txEditFecha);	    	
	    	holder.btnFecha = (ImageButton) convertView.findViewById(R.id.btnFecha);
	    	holder.chkCumplio = (CheckBox) convertView.findViewById(R.id.chkCumplio);
	    	holder.btnProfit = (Button) convertView.findViewById(R.id.btnProfit);
	        
	        convertView.setTag(holder);
	     // } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	      //  holder = (ViewHolder) convertView.getTag();
	     // }
	      
	      holder.txViewPro.setText(compromiso.getDescripcionProducto());
	      //holder.txViewConcrecion.setText(compromiso.getConcrecion());
	      ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.confirmacion,android.R.layout.simple_spinner_item);
			adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		  //holder.cboConcrecion.setAdapter(adapterTipo);
			holder.cboConcrecion.setText(compromiso.getConcrecion());
		  
		  /*holder.cboConcrecion.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0){
					compromiso.setConcrecion("S");
				}else{
					compromiso.setConcrecion("N");
				}
				
				//notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		  
		  
		  if(compromiso.getConcrecion().equals("S"))
			  holder.cboConcrecion.setSelection(0);
		  else 
			  holder.cboConcrecion.setSelection(1);
	      */
		  
		  holder.chkConcrecion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					compromiso.setConfirmacionConcrecion("S");
				}else{
					compromiso.setConfirmacionConcrecion("N");
				}
			}
		});
		  
		  
	      if(compromiso.getConfirmacionConcrecion().equals("S")) holder.chkConcrecion.setChecked(true);
	      else holder.chkConcrecion.setChecked(false);
	      
	      
	      holder.txViewSOVI.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				compromiso.setSovi(holder.txViewSOVI.getText().toString());
			}
		});
	      
	      holder.txViewSOVI.setText(compromiso.getSovi());
	      
	      
	      holder.chkSOVI.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.setConfirmacionSovi("S");
					}else{
						compromiso.setConfirmacionSovi("N");
					}
				}
			});
	      
	      if(compromiso.getConfirmacionSovi().equals("S")) holder.chkSOVI.setChecked(true);
	      else holder.chkSOVI.setChecked(false);
	      
	      
		  holder.cboCumPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0){
					compromiso.setCumplePrecio("S");
				}else{
					compromiso.setCumplePrecio("N");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		  
			  
	      holder.cboCumPrecio.setAdapter(adapterTipo);
		  if(compromiso.getCumplePrecio().equals("S"))holder.cboCumPrecio.setSelection(0);
		  else holder.cboCumPrecio.setSelection(1);
	      
		  holder.chkPrecio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.setConfirmacionCumplePrecio("S");
					}else{
						compromiso.setConfirmacionCumplePrecio("N");
					}
				}
			});
		  
		  
	      if(compromiso.getConfirmacionCumplePrecio().equals("S")) holder.chkPrecio.setChecked(true);
	      else holder.chkPrecio.setChecked(false);
	      
	      //===============================
	      
	      /*holder.txViewSabores.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					compromiso.setNumeroSabores(holder.txViewSabores.getText().toString());
				}
			});*/
	      holder.txViewSabores.setText(compromiso.getNumeroSabores());
	      
	      //=================================
	      
	      holder.chkSabores.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.setConfirmacionNumeroSabores("S");
					}else{
						compromiso.setConfirmacionNumeroSabores("N");
					}
				}
			});
	      
	      if(compromiso.getConfirmacionNumeroSabores().equals("S")) holder.chkSabores.setChecked(true);
	      else holder.chkSabores.setChecked(false);
	      
	    //=================================
	      
	      holder.txViewPuntos.setText(compromiso.getPuntosBonus());
	      holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	      
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
	      
	      holder.btnFecha = (ImageButton)convertView.findViewById(R.id.btnFecha);
	    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = compromiso.getFechaCompromiso();
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
					      
					      
					      EfficientAdapter.txtFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = compromiso;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes, dia);
					      p.show();
				}
			});
	    	
	    	
	      int mYear,mMonth,mDay;
	      String fecha = compromiso.getFechaCompromiso();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  
	    	
	          
	    	  holder.txViewFecha.setText(pad(mDay)+"/"+ pad(mMonth)+"/"+pad(mYear));
	     }
	      else{
	    	  
	    	  holder.txViewFecha.setText("");
	      }
	      
	      //============================
	      
	      holder.chkCumplio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.setCumplio("S");
					}else{
						compromiso.setCumplio("N");
					}
				}
			});
	      
	      
	      if(compromiso.getCumplio().equals("S")) holder.chkCumplio.setChecked(true);
	      else holder.chkCumplio.setChecked(false);
	      
	    //============================
	      
	      /*holder.cboConcrecion.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0) 
						compromiso.setConcrecion("S");
					else
						compromiso.setConcrecion("N");
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});*/
	      
	      /*
	      holder.txViewProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarVendedorActivity.class);
					profit.putExtra(MostrarVendedorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarVendedorActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarVendedorActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarVendedorActivity.NOMBRE_SUPERVISOR, supervisorTemporal.getNombre());
					context.startActivity(profit);
				}
			});*/
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPro;
	    	TextView cboConcrecion;
	    	CheckBox chkConcrecion;
	    	EditText txViewSOVI;
	    	CheckBox chkSOVI;
	    	Spinner cboCumPrecio;
	    	CheckBox chkPrecio;
	    	TextView txViewSabores;
	    	CheckBox chkSabores;
	    	TextView txViewPuntos;   	    	
	    	TextView txViewAccTrade;    	
	    	EditText txEditFecha;
	    	TextView txViewFecha;
	    	ImageButton btnFecha;
	    	CheckBox chkCumplio;
	    	Button btnProfit;
	    }
	    
	   
	    @Override
	    public long getItemId(int position) {
	      // TODO Auto-generated method stub
	      return position;
	    }

	    @Override
	    public int getCount() {
	      // TODO Auto-generated method stub
	      //return data.length;
	    	if(detalles==null){
	    		return 0;
	    	}else{
	    		return detalles.size();
	    	}
	    }

	    @Override
	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	    	return detalles.get(position);
	    }

	  }
}
