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
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoOpen_Activity extends ListActivityBase {

	public final static String CODIGO_REGISTRO = "codigo_reg";
//	public final static String FLAG_FECHA = "fecha_flag";

	private static final int ACCION_CERRAR = 1;
	private static final int ACCION_ACTUALIZAR = 2;

	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
	public static final String SI = "S";
	
	public static final String NO_DATO = "N0";
	public static final String SI_DATO = "SI";
	
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
		
	@InjectResource(R.string.btn_cancelar) 				String cancelar;	
	@InjectResource(R.string.confirm_cancelar_title) 	String confirm_cancelar_title;
	@InjectResource(R.string.confirm_cancelar_message)  String confirm_cancelar_message;
	@InjectResource(R.string.confirm_cancelar_yes) 		String confirm_cancelar_yes;
	@InjectResource(R.string.confirm_cancelar_no) 		String confirm_cancelar_no;
	
	public static ClienteTO cliente;
	public static MyApplication application;
	List<CompromisoTO> compromisos;
	

	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
    	compromisos = new ArrayList<CompromisoTO>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoopen_activity);        
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
        processAsync();        
    }
    
    
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
    
    public void btnCancelar_click(View view)
    {
    	//processAsync(ACCION_CERRAR);
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
				application.openAdapter = new EfficientAdapter(getApplicationContext(), new ArrayList<CompromisoTO>());
				for(CompromisoTO comp : application.openAdapter.detalles)
				{
					if(Integer.parseInt(comp.getSovi())<=0 && Integer.parseInt(comp.getSoviCompromiso())<=0)
					{
						showToast("Los valores de SOVI deben ser mayores a 0");
						return false;
					}
				}
				openAdapterVacio = true;
			}
			if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
			{				
				application.posicionAdapter = new CompromisoPosicionOpen_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
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
   			update.codigoRegistro = codigoRegistro; 
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
		
    	if(accion == ACCION_CERRAR)
    	{    		
    		cerrarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
    		cerrarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
    		cerrarCompromisoProxy.setCompromisos(application.openAdapter.detalles);
    		cerrarCompromisoProxy.setCodigoCabecera(codigoRegistro);
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
	        	        	    		    	
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);	
	        holder.btnProfit = (ImageButton) convertView.findViewById(R.id.btnProfit);
	        
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.cboConcrecion = (TextView) convertView.findViewById(R.id.cboConcrecion);
	        holder.cboConcrecionCmp = (Spinner) convertView.findViewById(R.id.cboConcrecionCmp);
	        
	        holder.txViewSOVI =  (EditText) convertView.findViewById(R.id.txViewSOVI);
	        holder.txViewSOVICmp =  (EditText) convertView.findViewById(R.id.txViewSOVICmp);
	        holder.cboCumPrecio =  (Spinner) convertView.findViewById(R.id.cboCumPrecio);
	        holder.cboCumPrecioCmp =  (Spinner) convertView.findViewById(R.id.cboCumPrecioCmp);
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);
	        holder.cboSaboresCmp =  (Spinner) convertView.findViewById(R.id.cboSaboresCmp);
	        
	    		    	
	    	holder.txViewAccTrade = (Spinner) convertView.findViewById(R.id.txViewAccTrade);	
	    	holder.txEditFecha = (EditText) convertView.findViewById(R.id.txEditFecha);	    	
	    	holder.btnFecha = (ImageButton) convertView.findViewById(R.id.btnFecha);
	    	
	        
	        convertView.setTag(holder);
	      
	      holder.txViewPro.setText(compromiso.getDescripcionProducto());
	      
	      if(compromiso.getConcrecion().equalsIgnoreCase(SI))
	    	  holder.cboConcrecion.setText(SI_DATO);
	      else
	    	  holder.cboConcrecion.setText(NO_DATO);
		  
		  	      
	      holder.txViewSOVI.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				compromiso.setSovi(holder.txViewSOVI.getText().toString());
			}
		});
	      
	      holder.txViewSOVI.setText(compromiso.getSovi());
	      holder.txViewSOVICmp.setText(compromiso.getSoviCompromiso());
	      
	      holder.txViewSOVICmp.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					compromiso.setSoviCompromiso(holder.txViewSOVICmp.getText().toString());
				}
			});
	      	      
	      
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
		  
		  holder.cboCumPrecioCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.setCumplePrecioCompromiso("S");
					}else{
						compromiso.setCumplePrecioCompromiso("N");
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});

	      ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.confirmacion,android.R.layout.simple_spinner_item);
		  adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	      holder.cboCumPrecio.setAdapter(adap);
	      holder.cboCumPrecioCmp.setAdapter(adap);
	      holder.cboConcrecionCmp.setAdapter(adap);
	      
	      ArrayAdapter<CharSequence> adapSabores = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.numero_sabores,android.R.layout.simple_spinner_item);
	      adapSabores.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		  holder.cboSaboresCmp.setAdapter(adapSabores);
	      	      
		  if(compromiso.getCumplePrecio().equals("S"))holder.cboCumPrecio.setSelection(0);
		  else holder.cboCumPrecio.setSelection(1);
		  
		  if(compromiso.getCumplePrecioCompromiso().equals("S"))holder.cboCumPrecioCmp.setSelection(0);
		  else holder.cboCumPrecioCmp.setSelection(1);
	      
	      holder.txViewSabores.setText(compromiso.getNumeroSabores());
	      
	      holder.txViewPuntos.setText(compromiso.getPuntosBonus());
	      //holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	     	      
	      holder.btnFecha = (ImageButton)convertView.findViewById(R.id.btnFecha);
	    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = compromiso.getFechaCompromiso();
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
					      
					      
					      EfficientAdapter.txtFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = compromiso;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes--, dia);
					      p.show();
				}
			});	    	
	    
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.getCodigo());
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.getCodigoProducto());
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.getDescripcionProducto());
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPro;
	    	TextView cboConcrecion;
	    	Spinner cboConcrecionCmp;
	    	EditText txViewSOVI;
	    	EditText txViewSOVICmp;
	    	Spinner cboCumPrecio;
	    	Spinner cboCumPrecioCmp;
	    	TextView txViewSabores;
	    	Spinner cboSaboresCmp;
	    	TextView txViewPuntos;   	    	
	    	Spinner txViewAccTrade;    	
	    	EditText txEditFecha;
	    	ImageButton btnFecha;
	    	ImageButton btnProfit;
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
