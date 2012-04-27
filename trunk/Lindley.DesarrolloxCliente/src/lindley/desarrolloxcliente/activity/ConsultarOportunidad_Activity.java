package lindley.desarrolloxcliente.activity;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.GuardarOportunidadTO;
import lindley.desarrolloxcliente.to.NuevaOportunidadTO;
import lindley.desarrolloxcliente.ws.service.ConsultarNuevaOportunidadProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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

public class ConsultarOportunidad_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarNuevaOportunidadProxy consultarOportunidadProxy;
	private EfficientAdapter adap;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	public static ClienteTO cliente;
	public final String OPORTUNIDAD_SISTEMA = "1";
	private final String OPORTUNIDAD_DESARROLLADOR_ABIERTO = "A";
	private MyApplication application;
	
	@InjectResource(R.string.confirm_atras_title) 	String confirm_atras_title;
	@InjectResource(R.string.confirm_atras_message) String confirm_atras_message;
	@InjectResource(R.string.confirm_atras_yes) 	String confirm_atras_yes;
	@InjectResource(R.string.confirm_atras_no) 		String confirm_atras_no;
	
	 @Override
	 public void onBackPressed() {
		 // TODO Auto-generated method stub
		 MessageBox.showConfirmDialog(this, confirm_atras_title, confirm_atras_message, confirm_atras_yes, new android.content.DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub	
					Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
					startActivity(intent);
				}
				
			}, confirm_atras_no, new android.content.DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});   
    }
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultaroportunidad_activity);        
        mActionBar.setTitle(R.string.oportunidad_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
    
	public void btnCancelar_click(View view)
	{
		onBackPressed();
	}
	
    public void btnSiguiente_click(View view)
    {
//    	ArrayList<OportunidadTO> oportunidades = application.getOportunidades();
//    	
//    	if(oportunidades==null){
//    		oportunidades = new ArrayList<OportunidadTO>();
//    	}else{
//    		oportunidades.clear();
//    	}
//    	
//    	
//    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();
//
//    	if(adap == null)
//    	{
//	    	adap = new EfficientAdapter(getApplicationContext(), new ArrayList<OportunidadTO>());
//    	}
//    	
//    	for (OportunidadTO oportunidad : adap.detalles) {
//    		if(oportunidad.isSeleccionado()){    			    			
//    			oportunidades.add(oportunidad);
//    		}
//		}
    	
    	ArrayList<GuardarOportunidadTO> oportunidades = application.guardarOportunidades;
    	
    	if(oportunidades==null){
    		oportunidades = new ArrayList<GuardarOportunidadTO>();
    	}else{
    		oportunidades.clear();
    	}
    	
    	
    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();

    	if(adap == null)
    	{
	    	adap = new EfficientAdapter(getApplicationContext(), new ArrayList<NuevaOportunidadTO>());
    	}
    	
    	for (NuevaOportunidadTO oportunidad : adap.detalles) {
    		if(oportunidad.seleccionado){    
    			GuardarOportunidadTO guardar = new GuardarOportunidadTO();
    			guardar.codigoProducto = oportunidad.codigoProducto;
    			oportunidades.add(guardar);
    		}
		}
    	application.guardarOportunidades = oportunidades;

    	int maximoValor = 2;
    	int filasSeleccionadas=oportunidades.size();
    	
    	if(filasSeleccionadas == 0){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Debe seleccionar como m’nimo una acci—n.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
				
			});
    	}
    	else if(filasSeleccionadas>maximoValor){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede seleccionar como m‡ximo " + maximoValor +" acciones.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
				
			});
    	}else{
    		Intent intent;
    		String a = "C";
    		finish();
    		if(a.equals(OPORTUNIDAD_DESARROLLADOR_ABIERTO))
    		{
    			intent= new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");		
    			startActivity(intent);
    		}
    		else
    		{
    			intent = new Intent("lindley.desarrolloxcliente.skuprioritario");
    			startActivity(intent);
    		}
    	}
    }
    
    @Override
	protected void process() {
    	consultarOportunidadProxy.setCodigoCliente(cliente.getCodigo());
    	consultarOportunidadProxy.setTipoOportunidad(OPORTUNIDAD_SISTEMA);
    	consultarOportunidadProxy.execute();
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarOportunidadProxy.isExito();
		if (isExito) {
			int status = consultarOportunidadProxy.getResponse().getStatus();
			if (status == 0) {
				List<NuevaOportunidadTO> oportunidades = consultarOportunidadProxy
						.getResponse().listaNuevaOportunidad;
				adap = new EfficientAdapter(this, oportunidades);
				final Calendar c = Calendar.getInstance();
				if(oportunidades.size()>0)
					txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarOportunidadProxy.getResponse().getDescripcion());
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
	    private Context context;
	    private List<NuevaOportunidadTO> detalles;
//	    private MyApplication application;
	    
	    public EfficientAdapter(Context context, List<NuevaOportunidadTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.context = context;
		      this.detalles = valores;
//		      this.application = (MyApplication)context.getApplicationContext();
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final NuevaOportunidadTO oportunidad = (NuevaOportunidadTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultaroportunidad_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	    	holder.btnProfit = (Button) convertView.findViewById(R.id.btnProfit);
	    	holder.txViewLegacy = (TextView) convertView.findViewById(R.id.txViewLegacy);
	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(" "+oportunidad.descripcionProducto);
	      holder.txViewLegacy.setText(oportunidad.codigoLegacy);
	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						oportunidad.seleccionado = true;
					}else{
						oportunidad.seleccionado = false;
					}
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
					profit.putExtra(VerProfit_Activity.ARTICULO, oportunidad.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, oportunidad.descripcionProducto);
					context.startActivity(profit);
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewPro;
	    	TextView txViewLegacy;
	    	Button btnProfit;
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
