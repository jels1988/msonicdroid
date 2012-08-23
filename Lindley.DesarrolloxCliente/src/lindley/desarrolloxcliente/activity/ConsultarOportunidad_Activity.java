package lindley.desarrolloxcliente.activity;


import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.AccionTradeBLL;
import lindley.desarrolloxcliente.negocio.OportunidadBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.ws.service.ConsultarNuevaOportunidadProxy;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
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
import android.widget.TextView;

import com.google.inject.Inject;

public class ConsultarOportunidad_Activity extends net.msonic.lib.sherlock.ListActivityBase {

	
	@Inject ConsultarNuevaOportunidadProxy consultarOportunidadProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@Inject OportunidadBLL oportunidadBLL;
	ArrayList<OportunidadTO> nuevasOportunidades;
	
	
	private EfficientAdapter 	adap;
	private ClienteTO 			cliente;
	private EvaluacionTO 		evaluacion;
	private MyApplication 		application;
	@Inject private AccionTradeBLL 		accionTradeBLL;
	
	@InjectResource(R.string.confirm_atras_title) 	String confirm_atras_title;
	@InjectResource(R.string.confirm_atras_message) String confirm_atras_message;
	@InjectResource(R.string.confirm_atras_yes) 	String confirm_atras_yes;
	@InjectResource(R.string.confirm_atras_no) 		String confirm_atras_no;
	
	 @Override
	 public void onBackPressed() {
		 // TODO Auto-generated method stub
		 MessageBox.showConfirmDialog(this, confirm_atras_title, confirm_atras_message, confirm_atras_yes, new android.content.DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					finish();
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
    	this.validarConexionInternet=false;
        
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        setContentView(R.layout.consultaroportunidad_activity);
        
        application = (MyApplication)getApplicationContext();
		cliente = application.cliente;
		
		//CREAMOS UNA NUEVA EVALUACION
		application.evaluacion = new EvaluacionTO();
		evaluacion = application.evaluacion;
		
		
		setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        
		txtViewFecha.setText(ConstantesApp.getFechaSistema());
		
        processAsync(); 
    }
    
	public void btnCancelar_click(View view)
	{
		onBackPressed();
	}
	
    public void btnSiguiente_click(View view)
    {
    
    	evaluacion.oportunidades.clear();
    	
    	EfficientAdapter adap = (EfficientAdapter)getListAdapter();

    	for (OportunidadTO oportunidad : adap.detalles) {
    		if(oportunidad.seleccionado){    
    			evaluacion.oportunidades.add(oportunidad);
    			oportunidad.listaAccionesTrade = accionTradeBLL.listarByProducto(oportunidad.codigoProducto);
    		}
		}
    	

    	int maximoValor = 2;
    	int filasSeleccionadas=evaluacion.oportunidades.size();
    	
    	if(filasSeleccionadas == 0){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Debe seleccionar como m’nimo una acci—n.", "Aceptar", null);
    	}
    	else if(filasSeleccionadas>maximoValor){
    		MessageBox.showSimpleDialog(this, "Mensaje", "Solo puede seleccionar como m‡ximo " + maximoValor +" acciones.", "Aceptar",null);
    	}else{
    		
    		
    		Intent intent = new Intent("lindley.desarrolloxcliente.skuprioritario");
    		startActivity(intent);
    		
    		/*
    		Intent intent;
    		String a = "C";
    		finish();
    		
    		if(a.equals(ConstantesApp.OPORTUNIDAD_DESARROLLADOR_ABIERTO))
    		{
    			intent= new Intent("lindley.desarrolloxcliente.oportunidaddesarrollador");		
    			startActivity(intent);
    		}
    		else
    		{
    			intent = new Intent("lindley.desarrolloxcliente.skuprioritario");
    			startActivity(intent);
    		}
    		*/
    	}
    }
    
    @Override
	protected void process() {
    	
    	nuevasOportunidades = oportunidadBLL.consultarNuevasOportunidades(cliente.codigo);
    	adap = new EfficientAdapter(this,cliente.codigo, nuevasOportunidades);
    	
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
    	setListAdapter(adap);
    	super.processOk();

	}
    
    @Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    public static class EfficientAdapter extends ArrayAdapter<OportunidadTO> {
	    private Activity 				context;
	    private List<OportunidadTO> detalles;
	    private String codigoCliente;
	    
	    
	    public EfficientAdapter(Activity context,String codigoCliente, List<OportunidadTO> valores) {
	    	super(context, R.layout.consultaroportunidad_content, valores);
		      this.context = context;
		      this.detalles = valores;
		      this.codigoCliente = codigoCliente;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	
	    	
	    	View view = null;
			if (convertView == null) {
				
				
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultaroportunidad_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.chkSeleccion = (CheckBox) view.findViewById(R.id.chkSeleccion);
				viewHolder.txViewPro = (TextView) view.findViewById(R.id.txViewPro); 
				viewHolder.btnProfit = (Button) view.findViewById(R.id.btnProfit);
				viewHolder.txViewLegacy = (TextView) view.findViewById(R.id.txViewLegacy);
				
				view.setTag(viewHolder);
				
				viewHolder.chkSeleccion.setTag(this.detalles.get(position));
				
				viewHolder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						OportunidadTO oportunidadTO = (OportunidadTO) viewHolder.chkSeleccion.getTag();
						oportunidadTO.seleccionado = isChecked;
					}
				});
			   
				
				viewHolder.btnProfit.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							OportunidadTO oportunidadTO = (OportunidadTO) viewHolder.chkSeleccion.getTag();
							Intent profit = new Intent(context, VerProfit_Activity.class);
							profit.putExtra(VerProfit_Activity.ANIO, "");
							profit.putExtra(VerProfit_Activity.MES, "");
							profit.putExtra(VerProfit_Activity.CLIENTE, codigoCliente);
							profit.putExtra(VerProfit_Activity.ARTICULO, oportunidadTO.codigoProducto);
							profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, oportunidadTO.descripcionProducto);
							context.startActivity(profit);
						}
					});
					
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).chkSeleccion.setTag(this.detalles.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			OportunidadTO oportunidadTO = detalles.get(position);
			
			holder.txViewPro.setText(oportunidadTO.descripcionProducto);
			holder.txViewLegacy.setText(oportunidadTO.codigoLegacy);
			holder.chkSeleccion.setChecked(oportunidadTO.seleccionado);
			
			return view;
			
			/*
	    	final OportunidadTO oportunidad = (OportunidadTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultaroportunidad_content, null);
	        
	        holder = new ViewHolder();
	        	        	        	    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	    	holder.btnProfit = (Button) convertView.findViewById(R.id.btnProfit);
	    	holder.txViewLegacy = (TextView) convertView.findViewById(R.id.txViewLegacy);
	        holder.chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
	        	    	
	        convertView.setTag(holder);
	        
	      } else {
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(" "+oportunidad.descripcionProducto);
	      holder.txViewLegacy.setText(oportunidad.codigoLegacy);
	  
	      holder.chkSeleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
					oportunidad.seleccionado = isChecked;
				}
			});
	      
	      
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, codigoCliente);
					profit.putExtra(VerProfit_Activity.ARTICULO, oportunidad.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, oportunidad.descripcionProducto);
					context.startActivity(profit);
				}
			});*/
	      
	      
	    }

	    static class ViewHolder {   
	    	CheckBox chkSeleccion;
	    	TextView txViewPro;
	    	TextView txViewLegacy;
	    	Button btnProfit;
	    }
	   
	    

	  }

}
