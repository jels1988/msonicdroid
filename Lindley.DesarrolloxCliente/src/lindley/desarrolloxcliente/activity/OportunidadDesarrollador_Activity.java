package lindley.desarrolloxcliente.activity;

//import java.util.ArrayList;
//import java.util.List;
//
//import lindley.desarrolloxcliente.MyApplication;
//import lindley.desarrolloxcliente.R;
//import lindley.desarrolloxcliente.to.ClienteTO;
//import lindley.desarrolloxcliente.to.OportunidadTO;
//import lindley.desarrolloxcliente.ws.service.ConsultarOportunidadProxy;
//import lindley.desarrolloxcliente.ws.service.GuardarDesarrolloProxy;
//import roboguice.inject.InjectView;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.view.View.OnFocusChangeListener;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.AdapterView.OnItemSelectedListener;
//
//import com.google.inject.Inject;
//import com.thira.examples.actionbar.widget.ActionBar;
//
import net.msonic.lib.ListActivityBase;
//import net.msonic.lib.MessageBox;

public class OportunidadDesarrollador_Activity extends ListActivityBase {

//	
//	private static final int ACCION_GUARDAR = 1;
//	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
//	@Inject ConsultarOportunidadProxy consultarOportunidadProxy;
//	@Inject GuardarDesarrolloProxy guardarDesarrolloProxy;
//	private EfficientAdapter adap;
//	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
//	public static ClienteTO cliente;
//	public final String OPORTUNIDAD_DESARROLLADOR = "0";
//	public final String RESPUESTA_SI = "S";
//	public final String RESPUESTA_NO = "N";
//	public  MyApplication application;
//	//public static ArrayList<OportunidadTO> oportunidadesDesarrollador = new ArrayList<OportunidadTO>();
//	
//	/** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//    	inicializarRecursos();
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.oportunidaddesarrollador_activity);        
//        mActionBar.setTitle(R.string.oportunidaddesarrollador_activity_title);
//        application = (MyApplication)getApplicationContext();
//		cliente = application.getClienteTO();
//		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
//        mActionBar.setHomeLogo(R.drawable.header_logo);
//        processAsync(); 
//    }
//    
//    public void btnSiguiente_click(View view)
//    {
//    	ArrayList<OportunidadTO> oportunidadesDesarrollador = application.getOportunidadesDesarrollador();
//    	
//    	if(oportunidadesDesarrollador==null){
//    		oportunidadesDesarrollador = new ArrayList<OportunidadTO>();
//    	}else{
//    		oportunidadesDesarrollador.clear();
//    	}
//    	
//    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();
//
//    	if(adap == null)
//    	{
//	    	adap = new EfficientAdapter(getApplicationContext(), new ArrayList<OportunidadTO>());
//    	}
//    	
//    	for (OportunidadTO oportunidad : adap.detalles) {
//    		if(Integer.parseInt(oportunidad.getPuntosCocaCola())>0){
//    			oportunidadesDesarrollador.add(oportunidad);
//    			//filasSeleccionadas++;
//    		}
//		}
//    	
//    	int maximoValor = 2;
//    	int filasSeleccionadas=oportunidadesDesarrollador.size();
//    	
//    	if(filasSeleccionadas>maximoValor){
//    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede ingresar como m‡ximo " + maximoValor + " acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//				}
//				
//			});	
//    	}else{
//    		final ArrayList<OportunidadTO> oportunidadesDesarrolladorAdd = oportunidadesDesarrollador;
//    		application.setOportunidadesDesarrollador(oportunidadesDesarrolladorAdd); 		
//    		Intent intent = new Intent("lindley.desarrolloxcliente.consultarposicion");
//    		startActivity(intent);	
//    	}
//    	
//    }
//    
//    public void btnGuardar_click(View view)
//    {
//    	ArrayList<OportunidadTO> oportunidadesDesarrollador = application.getOportunidadesDesarrollador();
//    	
//    	if(oportunidadesDesarrollador==null){
//    		oportunidadesDesarrollador = new ArrayList<OportunidadTO>();
//    	}else{
//    		oportunidadesDesarrollador.clear();
//    	}
//    	
//    	
//    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();
//    	
//    	for (OportunidadTO oportunidad : adap.detalles) {
//    		if(Integer.parseInt(oportunidad.getPuntosCocaCola())>0){
//    			oportunidadesDesarrollador.add(oportunidad);
//    			//filasSeleccionadas++;
//    		}
//		}
//    	
//    	int filasSeleccionadas=oportunidadesDesarrollador.size();
//    	
//    	if(filasSeleccionadas>2){
//    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede ingresar como máximo 2 acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//				}
//				
//			});	
//    	}else{
//    		application.setOportunidadesDesarrollador(oportunidadesDesarrollador);
//    		//Intent intent = new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");    		
//    		//startActivity(intent);
//    		processAsync(ACCION_GUARDAR);
//    	}
//    }
//    
//    @Override
//	protected void process(int accion) {
//    	if(accion == ACCION_GUARDAR)
//    	{
//    		List<OportunidadTO> oportunidadSistema = application.getOportunidades();
//    		for(OportunidadTO op : oportunidadSistema)
//    		{    		
//    			op.setListaAccionesTrade(null);
//    		}
//    		List<OportunidadTO> oportunidadDesarrollador = application.getOportunidadesDesarrollador();
//    		for(OportunidadTO op : oportunidadDesarrollador)
//    		{    		
//    			op.setListaAccionesTrade(null);
//    		}		
//    		guardarDesarrolloProxy.setOportunidadSistema(oportunidadSistema);
//    		guardarDesarrolloProxy.setOportunidadDesarrollador(oportunidadDesarrollador);
//    		guardarDesarrolloProxy.setInformacion(application.getInformacionAdicional());
//    		guardarDesarrolloProxy.execute();
//    	}
//	}
//
//	@Override
//	protected void processOk(int accion) {
//		// TODO Auto-generated method stub
//		if(accion == ACCION_GUARDAR)
//    	{
//			boolean isExito = guardarDesarrolloProxy.isExito();
//			if (isExito) {
//				int status = guardarDesarrolloProxy.getResponse().getStatus();
//				if (status == 0) {
//					String idRegistro = guardarDesarrolloProxy.getResponse().getCodCabecera();
//					
//					Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.consultarcompromisoopen");
//					compromisoOpen.putExtra(CompromisoOpen_Activity.CODIGO_REGISTRO, idRegistro);
//					//compromisoOpen.putExtra(CompromisoOpen_Activity.FLAG_FECHA, CompromisoOpen_Activity.FLAG_OPEN_FECHA_ABIERTO);
//					startActivity(compromisoOpen);
//				}
//				else  {
//					showToast(guardarDesarrolloProxy.getResponse().getDescripcion());
//				}
//			}
//			else{
//				processError();
//			}
//			super.processOk();
//    	}
//	}
//
//	@Override
//	protected void processError(int accion) {
//		// TODO Auto-generated method stub
//		super.processError();
//		showToast(error_generico_process);
//	}
//	
//    @Override
//	protected void process() {
//    	consultarOportunidadProxy.setCodigoCliente(cliente.getCodigo());
//    	consultarOportunidadProxy.setTipoOportunidad(OPORTUNIDAD_DESARROLLADOR);
//    	consultarOportunidadProxy.execute();
//	}
//
//    @Override
//	protected void processOk() {
//		// TODO Auto-generated method stub
//		boolean isExito = consultarOportunidadProxy.isExito();
//		if (isExito) {
//			int status = consultarOportunidadProxy.getResponse().getStatus();
//			if (status == 0) {
//				List<OportunidadTO> oportunidades = consultarOportunidadProxy
//						.getResponse().getListaOportunidad();
//				adap = new EfficientAdapter(this, oportunidades);
//				
//				if(oportunidades.size()>0){
//					txtViewFecha.setText(oportunidades.get(0).getFecha());
//				}
//				setListAdapter(adap);
//				//adap.oportunidades=0;
//			}
//			else  {
//				showToast(consultarOportunidadProxy.getResponse().getDescripcion());
//			}
//		}
//		else{
//			processError();
//		}
//		super.processOk();
//	}
//    
//    @Override
//	protected void processError() {
//		// TODO Auto-generated method stub
//		super.processError();
//		showToast(error_generico_process);
//	}
//    
//    public static class EfficientAdapter extends BaseAdapter implements Filterable {
//	    private LayoutInflater mInflater;
//	    private Context context;
//	    public List<OportunidadTO> detalles;
//	    //public int oportunidades=0;
//	    
//	    public EfficientAdapter(Context context, List<OportunidadTO> valores) {
//		      // Cache the LayoutInflate to avoid asking for a new one each time.
//		      mInflater = LayoutInflater.from(context);
//		      this.context = context;
//		      this.detalles = valores;
//		    }
//	    
//
//	    /**
//	     * Make a view to hold each row.
//	     * 
//	     * @see android.widget.ListAdapter#getView(int, android.view.View,
//	     *      android.view.ViewGroup)
//	     */
//	    public View getView(final int position, View convertView, ViewGroup parent) {
//	    	final OportunidadTO oportunidad = (OportunidadTO) getItem(position);
//	    	final ViewHolder holder;
//
//	      if (convertView == null) {
//	        convertView = mInflater.inflate(R.layout.oportunidaddesarrollador_content, null);
//
//	        // Creates a ViewHolder and store references to the two children
//	        // views
//	        // we want to bind data to.
//	        holder = new ViewHolder();
//	        	        	        	    	
//	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
//	        holder.txViewConcrecion = (TextView) convertView.findViewById(R.id.txViewConcrecion);
//	        holder.txViewSOVI =  (TextView) convertView.findViewById(R.id.txViewSOVI);
//	        holder.txViewCumPrecio =  (TextView) convertView.findViewById(R.id.txViewCumPrecio);   
//	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);  	    	
//	    	holder.cboPCoca = (Spinner) convertView.findViewById(R.id.cboPCoca);		    	
//	    	holder.txtAccTrade = (EditText) convertView.findViewById(R.id.txtAccTrade);	          	
//	    	holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);	    	
//	    	holder.txViewPBonus = (TextView) convertView.findViewById(R.id.txViewPBonus);
//	    	holder.btnProfit = (Button) convertView.findViewById(R.id.btnProfit);
//	        
//	        convertView.setTag(holder);
//	      } else {
//	        // Get the ViewHolder back to get fast access to the TextView
//	        // and the ImageView.
//	        holder = (ViewHolder) convertView.getTag();
//	      }
//	      
//	      holder.txViewPro.setText(oportunidad.getDescripcionProducto());
//	      holder.txViewConcrecion.setText(oportunidad.getConcrecion());
//	      holder.txViewSOVI.setText(oportunidad.getSovi());
//	      holder.txViewCumPrecio.setText(oportunidad.getCumplePrecio());
//	      holder.txViewSabores.setText(oportunidad.getNumeroSabores());
//	      //holder.txViewPCoca.setText(oportunidad.getPuntosCocaCola());
//	      
//	      ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter
//					.createFromResource(context.getApplicationContext(),
//							R.array.puntos_desarrollador,
//							android.R.layout.simple_spinner_item);
//			adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//			
//		  holder.cboPCoca.setAdapter(adapterTipo);
//	     	
//	      holder.txViewPBonus.setText(oportunidad.getPuntosBonus());
//	      
//	      
//	      holder.txtAccTrade.setOnFocusChangeListener(new OnFocusChangeListener() {
//				
//					@Override
//					public void onFocusChange(View v, boolean hasFocus) {
//						// TODO Auto-generated method stub
//						oportunidad.setAccioneTrade(holder.txtAccTrade.getText().toString());
//					}
//				});
//		      
//	      
//	      holder.txtAccTrade.setText(oportunidad.getAccioneTrade());
//	     
//	      holder.cboPCoca.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				oportunidad.setPuntosCocaCola(String.valueOf(arg2));
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	      
//
//	      
//
//	      	  
//	      if(oportunidad.getPuntosCocaCola().compareTo("")==0){
//			  holder.cboPCoca.setSelection(0);
//		  }else{
//			  holder.cboPCoca.setSelection(Integer.parseInt(oportunidad.getPuntosCocaCola()));
//		  }
//	      
//	      holder.btnProfit.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Intent profit = new Intent(context, VerProfit_Activity.class);
//					profit.putExtra(VerProfit_Activity.ANIO, "");
//					profit.putExtra(VerProfit_Activity.MES, "");
//					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.getCodigo());
//					profit.putExtra(VerProfit_Activity.ARTICULO, oportunidad.getCodigoProducto());
//					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, oportunidad.getDescripcionProducto());
//					context.startActivity(profit);
//				}
//			});
//	      
//	      return convertView;
//	    }
//
//	    static class ViewHolder {   
//	    	TextView txViewPro;
//	    	TextView txViewConcrecion;
//	    	TextView txViewSOVI;
//	    	TextView txViewCumPrecio;
//	    	TextView txViewSabores;  	    	
//	    	Spinner cboPCoca;    	
//	    	EditText txtAccTrade;    	
//	    	TextView txViewFecha;
//	    	TextView txViewPBonus;
//	    	Button btnProfit;
//	    }
//	    
//	    @Override
//	    public Filter getFilter() {
//	      // TODO Auto-generated method stub
//	      return null;
//	    }
//
//	    @Override
//	    public long getItemId(int position) {
//	      // TODO Auto-generated method stub
//	      return position;
//	    }
//
//	    @Override
//	    public int getCount() {
//	      // TODO Auto-generated method stub
//	      //return data.length;
//	    	if(detalles==null){
//	    		return 0;
//	    	}else{
//	    		return detalles.size();
//	    	}
//	    }
//
//	    @Override
//	    public Object getItem(int position) {
//	      // TODO Auto-generated method stub
//	    	return detalles.get(position);
//	    }
//
//	  }

}