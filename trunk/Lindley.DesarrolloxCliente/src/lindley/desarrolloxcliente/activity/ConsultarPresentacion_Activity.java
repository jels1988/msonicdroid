package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class ConsultarPresentacion_Activity extends ListActivityBase {

	@Inject GuardarDesarrolloProxy guardarDesarrolloProxy;
	private static final int ACCION_GUARDAR = 1;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPresentacionProxy consultarPresentacionProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private MyApplication application;
	
	public void btnGuardar_click(View view)
    {
		ArrayList<PresentacionCompromisoTO> presentaciones = application.listPresentacionCompromiso;
    	
    	if(presentaciones==null){
    		presentaciones = new ArrayList<PresentacionCompromisoTO>();
    	}else{
    		presentaciones.clear();
    	}
    	
    	
    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();
    	
    	for (PresentacionTO presentacion : adap.detalles) {
    		/*if(oportunidad.getAccioneTrade().compareTo("")!=0){
    			oportunidades.add(oportunidad);
    		}*/
    		if(presentacion.isSeleccionado()){
    			PresentacionCompromisoTO compromiso = new PresentacionCompromisoTO();
    			
    			compromiso.setCodigoVariable(presentacion.getCodigoVariable());
    			compromiso.setPuntosSugeridos(presentacion.getPuntosSugeridos());
    			compromiso.setDescripcionVariable(" ");
    			compromiso.setCodigoFDE(presentacion.getCodigoFDE());
    			compromiso.setFechaCompromiso("0");
    			compromiso.setConfirmacion("N");    	
    			ArrayList<SKUPresentacionCompromisoTO> listSku = new ArrayList<SKUPresentacionCompromisoTO>();
    			for(SKUPresentacionTO sku : presentacion.getListaSKU())
    			{
    				if(!sku.seleccionado)
    				{
	    				SKUPresentacionCompromisoTO compromisoSKU = new SKUPresentacionCompromisoTO();
	    				compromisoSKU.setCodigoSKU(sku.getCodigoSKU());
	    				compromisoSKU.setDescripcionSKU(" ");
	    				compromisoSKU.setActual("N");
	//    				compromisoSKU.setActual("S");
	//    				compromisoSKU.setCompromiso("S");
	//    				compromisoSKU.setConfirmacion("S");
	    				listSku.add(compromisoSKU);
    				}
    			}
    			compromiso.setListaSKU(listSku);
    			
    			presentaciones.add(compromiso);
    		}
		}
    	
    	/*   	
    	if(filasSeleccionadas>2){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede ingresar como m‡ximo 2 acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
				
			});	
    	}else{
    		application.setOportunidadesDesarrollador(oportunidadesDesarrollador);
    		//Intent intent = new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");    		
    		//startActivity(intent);
    		processAsync(ACCION_GUARDAR);
    	}*/
    	application.listPresentacionCompromiso = presentaciones;
    	processAsync(ACCION_GUARDAR);
    }
    	
	public void btnSiguiente_click(View view)
    {
    	ArrayList<PresentacionCompromisoTO> presentaciones = application.listPresentacionCompromiso;
    	
    	if(presentaciones==null){
    		presentaciones = new ArrayList<PresentacionCompromisoTO>();
    	}else{
    		presentaciones.clear();
    	}
    	
    	
    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();
    	
    	for (PresentacionTO presentacion : adap.detalles) {
    		/*if(oportunidad.getAccioneTrade().compareTo("")!=0){
    			oportunidades.add(oportunidad);
    		}*/
    		if(presentacion.isSeleccionado()){
    			PresentacionCompromisoTO compromiso = new PresentacionCompromisoTO();
    			
    			compromiso.setCodigoVariable(presentacion.getCodigoVariable());
    			compromiso.setCodigoFDE(presentacion.getCodigoFDE());
    			compromiso.setPuntosSugeridos(presentacion.getPuntosSugeridos());
    			    			
    			presentaciones.add(compromiso);
    		}
		}
    	

    	/*int filasSeleccionadas=oportunidades.size();
    	if(filasSeleccionadas>3){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede seleccionar como m‡ximo 3 acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
				
			});
    	}else{
    		application.setOportunidades(oportunidades);    		
    		Intent intent = new Intent("lindley.desarrolloxcliente.informacionadicional");
    		startActivity(intent);
    	}*/
    	
    	application.listPresentacionCompromiso = presentaciones;   		
		Intent intent = new Intent("lindley.desarrolloxcliente.informacionadicional");
		startActivity(intent);
    }
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarpresentacion_activity);        
        mActionBar.setTitle(R.string.consultarpresentacion_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
	
    @Override
   	protected void process() {
    	consultarPresentacionProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPresentacionProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPresentacionProxy.isExito();
		if (isExito) {
			int status = consultarPresentacionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PresentacionTO> presentaciones = consultarPresentacionProxy
						.getResponse().getListaPresentacion();			
				adap = new EfficientAdapter(this, presentaciones);				
				final Calendar c = Calendar.getInstance();      
				if(presentaciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPresentacionProxy.getResponse().getDescripcion());
			}
		}
		else{
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
    protected void process(int accion) {
    	// TODO Auto-generated method stub
    	if(accion == ACCION_GUARDAR)
    	{
    		List<OportunidadTO> oportunidadSistema = application.getOportunidades();
    		for(OportunidadTO op : oportunidadSistema)
    		{    		
    			op.setListaAccionesTrade(null);
    		}
    		List<OportunidadTO> oportunidadDesarrollador = application.getOportunidadesDesarrollador();
    		for(OportunidadTO op : oportunidadDesarrollador)
    		{    		
    			op.setListaAccionesTrade(null);
    		}		
    		guardarDesarrolloProxy.listPosicion = application.listPosicionCompromiso;
    		guardarDesarrolloProxy.listPresentacion = application.listPresentacionCompromiso;
    		guardarDesarrolloProxy.setOportunidadSistema(oportunidadSistema);
    		guardarDesarrolloProxy.setOportunidadDesarrollador(oportunidadDesarrollador);
    		guardarDesarrolloProxy.setInformacion(application.getInformacionAdicional());
    		guardarDesarrolloProxy.execute();
    	}
    }
    
    @Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
    	{
			boolean isExito = guardarDesarrolloProxy.isExito();
			if (isExito) {
				int status = guardarDesarrolloProxy.getResponse().getStatus();
				if (status == 0) {
					String idRegistro = guardarDesarrolloProxy.getResponse().getCodCabecera();
					
					Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.compromisoprincipalopen");
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, idRegistro);
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.FLAG_FECHA, CompromisoPrincipalOpen_Resumen.FLAG_OPEN_FECHA_ABIERTO);
					startActivity(compromisoOpen);
				}
				else  {
					showToast(guardarDesarrolloProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError();
			}
			super.processOk();
    	}
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	
    public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<PresentacionTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PresentacionTO> valores) {
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
	    	final PresentacionTO presentacionTO = (PresentacionTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarpresentacion_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
	        holder.btnSKU = (Button) convertView.findViewById(R.id.btnSKU);        
	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewVariable.setText(presentacionTO.getDescripcionVariable());
	      holder.txViewPuntos.setText(presentacionTO.getPuntosSugeridos());
	      
	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						presentacionTO.setSeleccionado(true);
					}else{
						presentacionTO.setSeleccionado(false);
					}
				}
			});
	      
	      holder.btnSKU.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MyApplication application = (MyApplication) context.getApplicationContext();
					application.listSKUPresentacion = presentacionTO.getListaSKU();
					Intent skuPresentacion = new Intent(context, SKUPrioritario_Activity.class);					
					context.startActivity(skuPresentacion);
				}
			});
	      	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewVariable;
	    	TextView txViewPuntos;  	
	    	Button   btnSKU;
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
