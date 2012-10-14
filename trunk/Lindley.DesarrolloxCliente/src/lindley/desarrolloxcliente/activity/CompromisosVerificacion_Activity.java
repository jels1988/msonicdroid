package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import net.msonic.lib.sherlock.ListBaseFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class CompromisosVerificacion_Activity extends ListBaseFragment {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private EfficientAdapter oportunidades;
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 return inflater.inflate(R.layout.compromisoopen_activity, container,false);
	 }
	 
	 public static int VISTA_CARGADA=0;
	 
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         inicializarRecursos();
         this.validarConexionInternet=false;
         if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacion;
	 		
	 		 processAsync();
			
			
         }
         
	 }
	 
	 
	 

	 @Override
	protected void process() {
		 oportunidades = new EfficientAdapter(getActivity(),evaluacion.oportunidades);
	 }
	 
	 @Override
	 protected void processOk() {
		 setListAdapter(oportunidades);
		 super.processOk();
	 }
	 
	 public static class EfficientAdapter extends ArrayAdapter<OportunidadTO>{

		 private final List<OportunidadTO> detalle;
		 private final Activity context;
		 
		   public EfficientAdapter(Activity context,List<OportunidadTO> detalle){
				super(context, R.layout.compromisoopen_verificacion_content, detalle);
				this.detalle = detalle;
				this.context = context;
			}
		   
		   
			
			public int getCount() {
				// TODO Auto-generated method stub
				if (detalle == null) {
					return 0;
				} else {
					return detalle.size();
				}
			}
			
			public OportunidadTO getItem(int position) {
				// TODO Auto-generated method stub
				return this.detalle.get(position);
			}
			
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
			  public View getView(final int position, View convertView, ViewGroup parent) {
			    
					View view = null;	
					
					if (convertView == null) {
						
					
						
						LayoutInflater inflator = context.getLayoutInflater();
						view = inflator.inflate(R.layout.compromisoopen_verificacion_content, null);
						
						final ViewHolder holder = new ViewHolder();
						
						
						holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);	
				        holder.btnProfit = (ImageButton) view.findViewById(R.id.btnProfit);	   
				        holder.txViewLegacy = (TextView) view.findViewById(R.id.txViewLegacy);
				        holder.txViewPro = (TextView) view.findViewById(R.id.txViewPro); 
				        
				        holder.cboConcrecion = (TextView) view.findViewById(R.id.cboConcrecion);
				        holder.cboConcrecionCmp = (TextView) view.findViewById(R.id.cboConcrecionCmp);
				        
				        holder.txViewSOVI =  (TextView) view.findViewById(R.id.txViewSOVI);
				        holder.txViewSOVICmp =  (TextView) view.findViewById(R.id.txViewSOVICmp);
				        
				        holder.cboCumPrecio =  (TextView) view.findViewById(R.id.cboCumPrecio);
				        holder.cboCumPrecioCmp =  (TextView) view.findViewById(R.id.cboCumPrecioCmp);
				        
				        holder.txViewSabores = (TextView) view.findViewById(R.id.txViewSabores);
				        holder.cboSaboresCmp =  (TextView) view.findViewById(R.id.cboSaboresCmp);	        
				    		    	
				    	holder.txViewAccTrade = (TextView) view.findViewById(R.id.txViewAccTrade);	
				    	holder.txEditFecha = (TextView) view.findViewById(R.id.txEditFecha);	    	
				    	
				    	view.setTag(holder);
				    	holder.txViewPuntos.setTag(this.detalle.get(position));
				    	
					}else{
						
						view = convertView;
						((ViewHolder) view.getTag()).txViewPuntos.setTag(this.detalle.get(position));
					}
			    	 
					
					ViewHolder holder = (ViewHolder) view.getTag();
					
					OportunidadTO oportunidadTO = this.detalle.get(position);
					
					holder.txViewPuntos.setText(oportunidadTO.puntosSugeridos);
					holder.txViewPro.setText(oportunidadTO.descripcionProducto);
					holder.txViewLegacy.setText(oportunidadTO.codigoLegacy);
					
					if(oportunidadTO.concrecion.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI))
				    	  holder.cboConcrecion.setText(ConstantesApp.RESPUESTA_SI_LARGA);
				      else
				    	  holder.cboConcrecion.setText(ConstantesApp.RESPUESTA_NO_LARGA);
					
					 holder.txViewSOVI.setText(oportunidadTO.sovi);
				     holder.txViewSOVICmp.setText(oportunidadTO.soviActual);
				     holder.txViewSabores.setText(oportunidadTO.numeroSabores);
				     holder.txEditFecha.setText(oportunidadTO.fechaOportunidad);
					  
					return view;
			  }
			  
			  
			  
			  
			  static class ViewHolder {   
			    	TextView txViewPuntos;
			    	ImageButton btnProfit;
			    	TextView txViewLegacy;
			    	TextView txViewPro;
			    	
			    	TextView cboConcrecion;
			    	TextView cboConcrecionCmp;
			    	
			    	TextView txViewSOVI;
			    	TextView txViewSOVICmp;
			    	
			    	TextView cboCumPrecio;
			    	TextView cboCumPrecioCmp;
			    	
			    	TextView txViewSabores;
			    	TextView cboSaboresCmp;
			    	   	    	
			    	TextView txViewAccTrade;    	
			    	
			    	TextView txEditFecha;
			    	
			    }
			  
	 }
	 
	
	    
}
