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
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoOpenFalse_Activity extends ListActivityBase {

	public final static String CODIGO_REGISTRO = "codigo_reg";

	private static final int ACCION_CERRAR = 1;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
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
        setContentView(R.layout.compromisoopenfalse_activity);        
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
        processAsync();
        
    }
        
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				
				Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");
				cabecera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(cabecera);
				
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
					showToast("Debe editar valores de la pesta–a inventario.");
					return false;
				}
			}
			if(application.posicionFalseAdapter == null || application.posicionFalseAdapter.posiciones.isEmpty())
			{				
				application.posicionFalseAdapter = new CompromisoPosicionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
				if(posicionAdapterVacio)
				{
					showToast("Debe editar valores de la pesta–a posicion.");
					return false;
				}
			}
			if(application.presentacionFalseAdapter == null || application.presentacionFalseAdapter.detalles.isEmpty())
			{
				application.presentacionFalseAdapter = new CompromisoPresentacionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
				if(presentacionAdapterVacio)
				{
					showToast("Debe editar valores de la pesta–a presentacion.");
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
    		cerrarCompromisoProxy.codigoCabecera = codigoRegistro;
    		UsuarioTO user = application.getUsuarioTO();
    		cerrarCompromisoProxy.codigoUsuario = user.getCodigoSap();
    		cerrarCompromisoProxy.execute();
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
					txtViewFecha.setText(application.dia + "/" + application.mes + "/" + application.anio);
				application.openFalseAdapter = new CompromisoOpenFalse_Activity.EfficientAdapter(this, compromisos);
				setListAdapter(application.openFalseAdapter);
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
       				
       				Intent servicioFoto = new Intent("lindley.desarrolloxcliente.uploadFileService");
       				startService(servicioFoto);
       				
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
   		super.processOk();
   	} 
    
    private void setAdapterApplication() {
		// TODO Auto-generated method stub
    	application.openFalseAdapter = null;
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
    	
//    	   public static EditText txtFecha;
    	   public static CompromisoTO compromisoTO;
    	 
    		 
    		 
    	    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
    			
    			@Override
    			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
    				// TODO Auto-generated method stub

//    					EfficientAdapter.txtFecha.setText(String.valueOf(ActivityUtil.pad(dayOfMonth)) + "/"+ String.valueOf(ActivityUtil.pad(monthOfYear+1)) + "/" + String.valueOf(year));
    		    	  if(EfficientAdapter.compromisoTO!=null){
    		    		  EfficientAdapter.compromisoTO.fechaCompromiso = (String.valueOf(year) + String.valueOf(ActivityUtil.pad(monthOfYear+1)) + String.valueOf(ActivityUtil.pad(dayOfMonth)) );
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
	        convertView = mInflater.inflate(R.layout.compromisoopenfalse_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    		    	
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
	        holder.btnProfit = (ImageButton) convertView.findViewById(R.id.btnProfit);
	        holder.txViewLegacy = (TextView) convertView.findViewById(R.id.txViewLegacy);
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.txViewConcrecion = (TextView) convertView.findViewById(R.id.txViewConcrecion); 
	        holder.txViewConcrecionActual = (TextView) convertView.findViewById(R.id.txViewConcrecionActual); 
	        holder.txViewSOVI =  (TextView) convertView.findViewById(R.id.txViewSOVI);
	        holder.txViewSOVICmp =  (TextView) convertView.findViewById(R.id.txViewSOVICmp);
	        holder.txViewCumPrecio =  (TextView) convertView.findViewById(R.id.txViewCumPrecio);
	        holder.txViewCumPrecioCmp =  (TextView) convertView.findViewById(R.id.txViewCumPrecioCmp);
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);  	    	
	    	holder.txViewSaboresActual = (TextView) convertView.findViewById(R.id.txViewSaboresActual);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);
	    	holder.chkViewConcrecion = (CheckBox) convertView.findViewById(R.id.chkViewConcrecion);
	    	holder.chkViewSOVI = (CheckBox) convertView.findViewById(R.id.chkViewSOVI);
	    	holder.chkViewPrecio = (CheckBox) convertView.findViewById(R.id.chkViewPrecio);
	    	holder.chkViewSabor = (CheckBox) convertView.findViewById(R.id.chkViewSabor);
	    	
	        convertView.setTag(holder);

	        holder.txViewPuntos.setText(compromiso.puntosSugeridos);
		    holder.txViewPro.setText("  " + compromiso.descripcionProducto);
		    holder.txViewLegacy.setText(compromiso.codigoLegacy);
		    holder.txViewConcrecion.setText(compromiso.concrecion);
		    holder.txViewConcrecionActual.setText(compromiso.concrecionActual);
		    holder.txViewSOVI.setText(compromiso.sovi);
		    holder.txViewSOVICmp.setText(compromiso.soviActual);
		    holder.txViewCumPrecio.setText(compromiso.cumplePrecio);
		    holder.txViewCumPrecioCmp.setText(compromiso.cumplePrecioActual);
		    holder.txViewSabores.setText(compromiso.numeroSabores);
		    holder.txViewSaboresActual.setText(compromiso.numeroSaboresActual+"");
		    holder.txViewAccTrade.setText(compromiso.descAccionTrade);
		    
		    if(compromiso.numeroSaboresCumplio.equals("S"))
			{
				holder.chkViewSabor.setChecked(true);
				compromiso.numeroSaboresCumplio = "S";
			}
			else
			{
				holder.chkViewSabor.setChecked(false);
				compromiso.numeroSaboresCumplio = "N";
			}
		    
		    if(compromiso.cumplePrecioCumplio.equals("S"))
			{
				holder.chkViewPrecio.setChecked(true);
				compromiso.cumplePrecioCumplio = "S";
			}
			else
			{
				holder.chkViewPrecio.setChecked(false);
				compromiso.cumplePrecioCumplio = "N";
			}
		    
		    if(compromiso.soviCumplio.equals("S"))
			{
				holder.chkViewSOVI.setChecked(true);
				compromiso.soviCumplio = "S";
			}
			else
			{
				holder.chkViewSOVI.setChecked(false);
				compromiso.soviCumplio = "N";
			}
		    
		    if(compromiso.concrecionCumplio.equals("S"))
			{
				holder.chkViewConcrecion.setChecked(true);
				compromiso.concrecionCumplio = "S";
			}
			else
			{
				holder.chkViewConcrecion.setChecked(false);
				compromiso.concrecionCumplio = "N";
			}
		    
		    String fecha = compromiso.fechaCompromiso;
			int anio;
			int mes;
			int dia;
			if (fecha.length() >= 7) {
				anio = Integer.parseInt(fecha.substring(0, 4));
				mes = Integer.parseInt(fecha.substring(4, 6)) ;
				dia = Integer.parseInt(fecha.substring(6));
				holder.txViewFecha.setText(ActivityUtil.pad(dia)+"/"+ActivityUtil.pad(mes)+"/"+anio);
			}

	        
		    holder.chkViewConcrecion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.concrecionCumplio = "S";
					}else{
						compromiso.concrecionCumplio = "N";
					}
				}
			});
		    
		    holder.chkViewSOVI.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.soviCumplio = "S";
					}else{
						compromiso.soviCumplio = "N";
					}
				}
			});

		    holder.chkViewPrecio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.cumplePrecioCumplio = "S";
					}else{
						compromiso.cumplePrecioCumplio = "N";
					}
				}
			});

		    holder.chkViewSabor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						compromiso.numeroSaboresCumplio = "S";
					}else{
						compromiso.numeroSaboresCumplio = "N";
					}
				}
			});
	      
	      

			holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, application.anio);
					profit.putExtra(VerProfit_Activity.MES, application.mes);
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.getCodigo());
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.descripcionProducto);
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPuntos;
	    	ImageButton btnProfit;
	    	TextView txViewLegacy;
	    	TextView txViewPro;
	    	TextView txViewConcrecion;
	    	TextView txViewConcrecionActual;
	    	TextView txViewSOVI;
	    	TextView txViewSOVICmp;
	    	TextView txViewCumPrecio;
	    	TextView txViewCumPrecioCmp;
	    	TextView txViewSabores; 
	    	TextView txViewSaboresActual;
	    	TextView txViewAccTrade;
	    	TextView txViewFecha;
	    	
	    	CheckBox chkViewConcrecion;
	    	CheckBox chkViewSOVI;
	    	CheckBox chkViewPrecio;
	    	CheckBox chkViewSabor;
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
