package lindley.desarrolloxcliente.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Posicion_Activity extends ListBaseFragment  {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	@Inject PosicionBLL posicionBLL;
	//posicionBLL.consultarOportunidadesPoscion(evaluacion.codigoCliente);	
	
	EfficientAdapter oportunidades;
	
	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			 return inflater.inflate(R.layout.consultarposicioncompromisoopen_activity, container,false);
		 }
		 
		 private static int VISTA_CARGADA=0;
		 
		 @Override public void onActivityCreated(Bundle savedInstanceState) {
	         super.onActivityCreated(savedInstanceState);
	         
	         if(VISTA_CARGADA==0){
		 		VISTA_CARGADA=1;
		 		application = (MyApplication) getActivity().getApplicationContext();
		 		evaluacion = application.evaluacion;
		 		
		 		processAsync();
				
	         }
		 }
		 
		 
		 
		 @Override
		protected void process() {
			// TODO Auto-generated method stub
			 List<PosicionCompromisoTO> detalle = posicionBLL.consultarOportunidadesPoscion(evaluacion.codigoCliente);
			 oportunidades = new EfficientAdapter(getActivity(), detalle);
		}

		

		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			setListAdapter(oportunidades);
			super.processOk();
		}



		public static class EfficientAdapter extends ArrayAdapter<PosicionCompromisoTO>{

			 private final List<PosicionCompromisoTO> detalle;
			 private final Activity context;
			 
			  public EfficientAdapter(Activity context,List<PosicionCompromisoTO> detalle){
					super(context, R.layout.consultarposicioncompromisoopen_content, detalle);
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
				
				public PosicionCompromisoTO getItem(int position) {
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
							view = inflator.inflate(R.layout.consultarposicioncompromisoopen_content, null);
							
							
							final ViewHolder holder = new ViewHolder();
							
							holder.TextViewRpsta = (TextView) view.findViewById(R.id.TextViewRpsta);
							holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
							
							holder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
							holder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
							
							holder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
							holder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);				
							
							holder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
							
							holder.txViewAccComp = (EditText) view.findViewById(R.id.txViewAccComp);
							holder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);
							
							view.setTag(holder);
					    	holder.TextViewRpsta.setTag(this.detalle.get(position));
							
						}else{
							view = convertView;
							((ViewHolder) view.getTag()).TextViewRpsta.setTag(this.detalle.get(position));
						}
						
						ViewHolder holder = (ViewHolder) view.getTag();
						
						PosicionCompromisoTO posicionTO = getItem(position);
						
						holder.txViewAccComp.setText(posicionTO.observacion);
						holder.txViewRed.setText(posicionTO.red);
						holder.txViewMaximo.setText(posicionTO.ptoMaximo);
						holder.txViewPuntos.setText(posicionTO.puntosSugeridos);
						
						
						return view;
				  }
				  
				  
				   static class ViewHolder {   
				    	TextView TextViewRpsta;
				    	TextView txViewRed;
				    	TextView txViewMaximo;
				    	TextView txViewPuntos;  	
				    	Button   btnFotoInicial;
				    	Button 	 btnFotoExito;
				    	EditText txViewAccComp;
				    	EditText txEditFecha;
				    	ImageButton 	 btnFecha;
				    }
			  
		 }
}
