package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.AccionTradeTO;
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
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import net.msonic.lib.ActivityUtil;
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
				application.openAdapter = new EfficientAdapter(getApplicationContext(), new ArrayList<CompromisoTO>());				
				openAdapterVacio = true;
				if(openAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña inventario.");
					return false;
				}
			}
			if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
			{				
				application.posicionAdapter = new CompromisoPosicionOpen_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
				if(posicionAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña posición.");
					return false;
				}
			}
			if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
			{
				application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
				if(presentacionAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña presentación.");
					return false;
				}
			}
			if(application.informacionAdicional == null)
			{
				showToast("Debe editar valores de la pestaña combos.");
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
    		cerrarCompromisoProxy.codigoCabecera = codigoRegistro;
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
       		actualizarCompromisoProxy.codigoCabecera = codigoRegistro;
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
				{
					if(application.dia == null)
					{
						final Calendar c = Calendar.getInstance();
						txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
					}
					else
						txtViewFecha.setText(application.dia+ "/" + application.mes + "/" + application.anio);
				}
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
    		    		  EfficientAdapter.compromisoTO.fechaCompromiso = String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth));
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
	      
	      holder.txViewPuntos.setText(compromiso.puntosSugeridos);
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.getCodigo());
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.descripcionProducto);
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewPro.setText("    " + compromiso.descripcionProducto);
	      
	      if(compromiso.concrecion.equalsIgnoreCase(SI))
	    	  holder.cboConcrecion.setText(SI_DATO);
	      else
	    	  holder.cboConcrecion.setText(NO_DATO);
	      
	      holder.cboConcrecionCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.concrecionActual = NO;
					}else{
						compromiso.concrecionActual = SI;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	      
	      
	      holder.txViewSOVI.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				compromiso.sovi = holder.txViewSOVI.getText().toString();
			}
		});
	      
	      holder.txViewSOVI.setText(compromiso.sovi);
	      holder.txViewSOVICmp.setText(compromiso.soviActual);
	      
	      holder.txViewSOVICmp.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					compromiso.soviActual = holder.txViewSOVICmp.getText().toString();
				}
			});
	      	      
	      
		  holder.cboCumPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0){
					compromiso.cumplePrecio = NO;
				}else{
					compromiso.cumplePrecio = SI;
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
						compromiso.cumplePrecioActual = NO;
					}else{
						compromiso.cumplePrecioActual = SI;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		  
		  holder.txViewSabores.setText(compromiso.numeroSabores);
		  
		 
		  
		  holder.cboSaboresCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.numeroSaboresActual = 2;
					} else if(arg2 == 1){
						compromiso.numeroSaboresActual = 3;
					} else if(arg2 == 2){
						compromiso.numeroSaboresActual = 4;
					}
					else{
						compromiso.numeroSaboresActual = 0;
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
		  
		  if(compromiso.concrecionActual.equalsIgnoreCase(NO))
	    	  holder.cboConcrecionCmp.setSelection(0);
	      else
	    	  holder.cboConcrecionCmp.setSelection(1);
	      	      
		  if(compromiso.numeroSaboresActual == 2)
			  holder.cboSaboresCmp.setSelection(0);
		  else  if(compromiso.numeroSaboresActual == 3)
			  holder.cboSaboresCmp.setSelection(1);
		  else
			  holder.cboSaboresCmp.setSelection(2);
		  
		  if(compromiso.cumplePrecio.equals(NO))holder.cboCumPrecio.setSelection(0);
		  else holder.cboCumPrecio.setSelection(1);
		  
		  if(compromiso.cumplePrecioActual.equals(SI))
			  holder.cboCumPrecioCmp.setSelection(1);
		  else 
			  holder.cboCumPrecioCmp.setSelection(0);
		  
		  
		  holder.txViewAccTrade.setAdapter(application.getAdapterAccionTrade(compromiso.listaAccionesTrade));

	      if(holder.txViewAccTrade.getCount() > 1)
	    	  holder.txViewAccTrade.setSelection(1);
	      
	      holder.txViewAccTrade.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2 > 0){
						compromiso.descAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getDescripcion());
						compromiso.codigoAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getCodigo());
					}else{
						compromiso.descAccionTrade  = " ";
						compromiso.codigoAccionTrade = "0";
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					compromiso.descAccionTrade  = " ";
					compromiso.codigoAccionTrade = "0";
				}
				
			});
	      
			String fecha = compromiso.fechaCompromiso;
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
	      
	      //holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	     	      
	      holder.btnFecha = (ImageButton)convertView.findViewById(R.id.btnFecha);
	    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = compromiso.fechaCompromiso;
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
	    
	      
	      
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPuntos;
	    	ImageButton btnProfit;	    	
	    	TextView txViewPro;
	    	
	    	TextView cboConcrecion;
	    	Spinner cboConcrecionCmp;
	    	
	    	EditText txViewSOVI;
	    	EditText txViewSOVICmp;
	    	
	    	Spinner cboCumPrecio;
	    	Spinner cboCumPrecioCmp;
	    	
	    	TextView txViewSabores;
	    	Spinner cboSaboresCmp;
	    	   	    	
	    	Spinner txViewAccTrade;    	
	    	
	    	EditText txEditFecha;
	    	ImageButton btnFecha;
	    	
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
