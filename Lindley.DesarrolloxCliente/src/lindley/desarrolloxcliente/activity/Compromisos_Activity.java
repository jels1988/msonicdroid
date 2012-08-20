package lindley.desarrolloxcliente.activity;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Compromisos_Activity extends ListBaseFragment {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 return inflater.inflate(R.layout.compromisoopen_activity, container,false);
	 }
	 
	 private static int VISTA_CARGADA=0;
	 
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacion;
	 		
	 		EfficientAdapter oportunidades = new EfficientAdapter(getActivity(),evaluacion.oportunidades);
			setListAdapter(oportunidades);
			
         }
         
	 }
	 
	 
	 
	 
	 public static class EfficientAdapter extends ArrayAdapter<OportunidadTO>{

		 private final List<OportunidadTO> detalle;
		 private final Activity context;
		 
		   public EfficientAdapter(Activity context,List<OportunidadTO> detalle){
				super(context, R.layout.compromisoopen_content, detalle);
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
						view = inflator.inflate(R.layout.compromisoopen_content, null);
						
						final ViewHolder holder = new ViewHolder();
						
						holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);	
				        holder.btnProfit = (ImageButton) view.findViewById(R.id.btnProfit);	   
				        holder.txViewLegacy = (TextView) view.findViewById(R.id.txViewLegacy);
				        holder.txViewPro = (TextView) view.findViewById(R.id.txViewPro); 
				        
				        holder.cboConcrecion = (TextView) view.findViewById(R.id.cboConcrecion);
				        holder.cboConcrecionCmp = (Spinner) view.findViewById(R.id.cboConcrecionCmp);
				        
				        holder.txViewSOVI =  (EditText) view.findViewById(R.id.txViewSOVI);
				        holder.txViewSOVICmp =  (EditText) view.findViewById(R.id.txViewSOVICmp);
				        
				        holder.cboCumPrecio =  (Spinner) view.findViewById(R.id.cboCumPrecio);
				        holder.cboCumPrecioCmp =  (Spinner) view.findViewById(R.id.cboCumPrecioCmp);
				        
				        holder.txViewSabores = (TextView) view.findViewById(R.id.txViewSabores);
				        holder.cboSaboresCmp =  (Spinner) view.findViewById(R.id.cboSaboresCmp);	        
				    		    	
				    	holder.txViewAccTrade = (Spinner) view.findViewById(R.id.txViewAccTrade);	
				    	holder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);	    	
				    	holder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
				    	
				    	view.setTag(holder);
				    	holder.txViewPuntos.setTag(this.detalle.get(position));
				    	
					}else{
						
						view = convertView;
						((ViewHolder) view.getTag()).txViewPuntos.setTag(this.detalle.get(position));
					}
			    	 
					
					ViewHolder holder = (ViewHolder) view.getTag();
					
					OportunidadTO oportunidadTO = this.detalle.get(position);
					holder.txViewPuntos.setText(oportunidadTO.puntosCocaCola);
				    holder.txViewPuntos.setText(oportunidadTO.puntosCocaCola);
				    
					return view;
			  }
			  
			  
			  
			  static class ViewHolder {   
			    	TextView txViewPuntos;
			    	ImageButton btnProfit;
			    	TextView txViewLegacy;
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
			  
	 }
	 
	
	    
}
