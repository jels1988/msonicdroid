package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PosicionTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionProxy;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class ConsultarPosicion_Activity extends ListActivityBase {

	//public static final String RESPUESTA = "rspta";
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPosicionProxy consultarPosicionProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	ClienteTO cliente;
	private MyApplication application;
	//@InjectExtra(RESPUESTA) String respuesta;
	String respuesta;

	public final String RESPUESTA_SI = "S";
	public final String RESPUESTA_NO = "N";
	
	public void btnSiguiente_click(View view)
    {
    	ArrayList<PosicionCompromisoTO> posiciones = application.listPosicionCompromiso;
    	
    	if(posiciones==null){
    		posiciones = new ArrayList<PosicionCompromisoTO>();
    	}else{
    		posiciones.clear();
    	}
    	
    	
    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();

    	if(adap == null)
    	{
	    	adap = new EfficientAdapter(getApplicationContext(), new ArrayList<PosicionTO>());
    	}
    	
    	for (PosicionTO posicion : adap.detalles) {
    		
    		if(posicion.isSeleccionado()){
    			PosicionCompromisoTO compromiso = new PosicionCompromisoTO();
    			
//    			compromiso.codigoVariable = (posicion.getCodigoVariable());
//    			compromiso.red = (posicion.getRed());
//    			compromiso.ptoMaximo = (posicion.getPtoMaximo());    			
//    			compromiso.puntosSugeridos = (posicion.getPuntosSugeridos());
//    			compromiso.respuesta = respuesta;
//    			
//    			compromiso.fechaCompromiso = ("0");
//    			compromiso.cumplio = ("N");
//    			compromiso.observacion = (" ");
    			
    			posiciones.add(compromiso);
    		}
		}

    	/*int filasSeleccionadas=oportunidades.size();
    	if(filasSeleccionadas>3){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede seleccionar como m�ximo 3 acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
				
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
    	
    	application.listPosicionCompromiso = posiciones;   		
		Intent intent = new Intent("lindley.desarrolloxcliente.consultarpresentacion");
		startActivity(intent);
    }
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarposicion_activity);        
        mActionBar.setTitle(R.string.consultarposicion_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	MessageBox.showConfirmDialog(this, "Posici�n-Activos", "�Tiene activos de la empresa ?", "SI", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				respuesta = RESPUESTA_SI;
		        processAsync(); 
			}
			
		}, "NO", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				respuesta = RESPUESTA_NO;
		        processAsync(); 
			}
			
		});    	
    }
    
    @Override
   	protected void process()  throws Exception{
    	consultarPosicionProxy.setCodigoCliente(cliente.codigo);
    	consultarPosicionProxy.setRespuesta(respuesta);       	
    	consultarPosicionProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPosicionProxy.isExito();
		System.out.println(isExito);
		if (isExito) {
			int status = consultarPosicionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PosicionTO> posiciones = consultarPosicionProxy
						.getResponse().getListaPosicion();
				adap = new EfficientAdapter(this, posiciones);				
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPosicionProxy.getResponse().getDescripcion());
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
    
    public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    //private Context context;
	    private List<PosicionTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PosicionTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      //this.context = context;
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final PosicionTO posicionTO = (PosicionTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarposicion_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
//	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
	        holder.txViewRed = (TextView) convertView.findViewById(R.id.txViewRed);
	        holder.txViewMaximo = (TextView) convertView.findViewById(R.id.txViewMaximo);
	        holder.txViewDiferencia = (TextView) convertView.findViewById(R.id.txViewDiferencia);
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);	        
	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewVariable.setText(posicionTO.getDescripcionVariable());
	      holder.txViewRed.setText(posicionTO.getRed());
	      holder.txViewMaximo.setText(posicionTO.getPtoMaximo());
	      holder.txViewDiferencia.setText(posicionTO.getDiferencia());
	      holder.txViewPuntos.setText(posicionTO.getPuntosSugeridos());
	      
//	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					// TODO Auto-generated method stub
//					if(isChecked){
//						posicionTO.setSeleccionado(true);
//					}else{
//						posicionTO.setSeleccionado(false);
//					}
//				}
//			});
//	      	      
	      return convertView;
	    }

	    static class ViewHolder {   
//	    	CheckBox chkSeleccion;
	    	TextView txViewVariable;
	    	TextView txViewRed;
	    	TextView txViewMaximo;
	    	TextView txViewDiferencia;
	    	TextView txViewPuntos;  	
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
    
    private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}
