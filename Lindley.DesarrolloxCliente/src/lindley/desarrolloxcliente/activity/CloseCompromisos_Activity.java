package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.OportunidadTO;
import net.msonic.lib.sherlock.ListBaseFragment;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class CloseCompromisos_Activity extends ListBaseFragment {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private EfficientAdapter oportunidades;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 return inflater.inflate(R.layout.revisioncompromisos_activity, container,false);
	 }
	 
	 public static int VISTA_CARGADA=0;
	 
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         inicializarRecursos();
         this.validarConexionInternet=false;
         //if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacionActual;
	 		txtViewFecha.setText(ConstantesApp.formatFecha(evaluacion.fechaCreacion));
	 		processAsync();
         //}
         
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
				super(context, R.layout.closecompromisos_content, detalle);
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
						view = inflator.inflate(R.layout.closecompromisos_content, null);
						
						final ViewHolder holder = new ViewHolder();
						
						
						holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);	
				        holder.btnProfit = (ImageButton) view.findViewById(R.id.btnProfit);	   
				        holder.txViewLegacy = (TextView) view.findViewById(R.id.txViewLegacy);
				        holder.txViewPro = (TextView) view.findViewById(R.id.txViewPro); 
				        
				        holder.cboConcrecion = (TextView) view.findViewById(R.id.cboConcrecion);
				        holder.cboConcrecionCmp = (TextView) view.findViewById(R.id.cboConcrecionCmp);
				        holder.chkViewConcrecion = (TextView) view.findViewById(R.id.chkViewConcrecion);
				        
				        /*holder.chkViewConcrecion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
								if(isChecked){
									oportunidadTO.concrecionCumple  = ConstantesApp.RESPUESTA_SI;
								}else{
									oportunidadTO.concrecionCumple  = ConstantesApp.RESPUESTA_NO;
								}
							}
						});*/
				        
				        
				        holder.txViewSOVI =  (TextView) view.findViewById(R.id.txViewSOVI);
				        holder.txViewSOVICmp =  (TextView) view.findViewById(R.id.txViewSOVICmp);
				        holder.chkViewSOVI = (TextView) view.findViewById(R.id.chkViewSOVI);
				       /* holder.chkViewSOVI.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
								if(isChecked){
									oportunidadTO.soviCumple  = ConstantesApp.RESPUESTA_SI;
								}else{
									oportunidadTO.soviCumple  = ConstantesApp.RESPUESTA_NO;
								}
							}
						});*/
				        
				        holder.cboCumPrecio =  (TextView) view.findViewById(R.id.cboCumPrecio);
				        holder.cboCumPrecioCmp =  (TextView) view.findViewById(R.id.cboCumPrecioCmp);
				        holder.chkViewPrecio = (TextView) view.findViewById(R.id.chkViewPrecio);
				        /*holder.chkViewPrecio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
								if(isChecked){
									oportunidadTO.respetoPrecioCumple  = ConstantesApp.RESPUESTA_SI;
								}else{
									oportunidadTO.respetoPrecioCumple  = ConstantesApp.RESPUESTA_NO;
								}
							}
						});*/
				        
				        holder.txViewSabores = (TextView) view.findViewById(R.id.txViewSabores);
				        holder.cboSaboresCmp =  (TextView) view.findViewById(R.id.cboSaboresCmp);	        
				        holder.chkViewSabor = (TextView) view.findViewById(R.id.chkViewSabor);
				        /*holder.chkViewSabor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
								if(isChecked){
									oportunidadTO.numeroSaboresCumple = ConstantesApp.RESPUESTA_SI;
								}else{
									oportunidadTO.numeroSaboresCumple  = ConstantesApp.RESPUESTA_NO;
								}
							}
						});*/
				        
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
					holder.txViewPro.setText(oportunidadTO.articulo);
					holder.txViewLegacy.setText(oportunidadTO.legacy);
					
					if(oportunidadTO.concrecion.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI))
				    	  holder.cboConcrecion.setText(ConstantesApp.RESPUESTA_SI_LARGA);
				      else
				    	  holder.cboConcrecion.setText(ConstantesApp.RESPUESTA_NO_LARGA);
					
					if(oportunidadTO.concrecionActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI))
				    	  holder.cboConcrecionCmp.setText(ConstantesApp.RESPUESTA_SI_LARGA);
				      else
				    	  holder.cboConcrecionCmp.setText(ConstantesApp.RESPUESTA_NO_LARGA);
					
					if(ConstantesApp.isSI(oportunidadTO.concrecionCumple)){
						holder.chkViewConcrecion.setText(ConstantesApp.RESPUESTA_SI_LARGA);
					}else{
						holder.chkViewConcrecion.setText(ConstantesApp.RESPUESTA_NO_LARGA);
					}
					
					 holder.txViewSOVI.setText(oportunidadTO.soviActual);
				     holder.txViewSOVICmp.setText(oportunidadTO.sovi);
				     //holder.chkViewSOVI.setChecked(ConstantesApp.isSI(oportunidadTO.soviCumple));
				     
				     
				     if(ConstantesApp.isSI(oportunidadTO.soviCumple)){
							holder.chkViewSOVI.setText(ConstantesApp.RESPUESTA_SI_LARGA);
						}else{
							holder.chkViewSOVI.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						}
				     
				 	
						if(oportunidadTO.respetoPrecio.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI))
					    	  holder.cboCumPrecio.setText(ConstantesApp.RESPUESTA_SI_LARGA);
					      else
					    	  holder.cboCumPrecio.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						
						if(oportunidadTO.respetoPrecioActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI))
					    	  holder.cboCumPrecioCmp.setText(ConstantesApp.RESPUESTA_SI_LARGA);
					      else
					    	  holder.cboCumPrecioCmp.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						
						//holder.chkViewSOVI.setChecked(ConstantesApp.isSI(oportunidadTO.respetoPrecioCumple));
						
						 if(ConstantesApp.isSI(oportunidadTO.respetoPrecioCumple)){
								holder.chkViewPrecio.setText(ConstantesApp.RESPUESTA_SI_LARGA);
							}else{
								holder.chkViewPrecio.setText(ConstantesApp.RESPUESTA_NO_LARGA);
							}
					     
						
					 holder.cboSaboresCmp.setText(oportunidadTO.numeroSaboresActual);
				     holder.txViewSabores.setText(oportunidadTO.numeroSabores);
				 	
					 if(ConstantesApp.isSI(oportunidadTO.numeroSaboresCumple)){
							holder.chkViewSabor.setText(ConstantesApp.RESPUESTA_SI_LARGA);
						}else{
							holder.chkViewSabor.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						}
					 
					
				     holder.txEditFecha.setText(ConstantesApp.formatFecha(oportunidadTO.fechaCompromiso));
				     holder.txViewAccTrade.setText(oportunidadTO.accionTrade);
				     
					  
					  
					return view;
			  }
			  
			  
			  
			  
			  static class ViewHolder {   
			    	TextView txViewPuntos;
			    	ImageButton btnProfit;
			    	TextView txViewLegacy;
			    	TextView txViewPro;
			    	
			    	TextView cboConcrecion;
			    	TextView cboConcrecionCmp;
			    	TextView chkViewConcrecion;
			    	
			    	TextView txViewSOVI;
			    	TextView txViewSOVICmp;
			    	TextView chkViewSOVI;
			    	
			    	
			    	TextView cboCumPrecio;
			    	TextView cboCumPrecioCmp;
			    	TextView chkViewPrecio;
			    	
			    	TextView txViewSabores;
			    	TextView cboSaboresCmp;
			    	TextView chkViewSabor;
			    	   	    	
			    	TextView txViewAccTrade;    	
			    	
			    	TextView txEditFecha;
			    	
			    }
			  
	 }
	 
	
	    
}
