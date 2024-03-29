package lindley.desarrolloxcliente.activity;

import java.util.List;

import roboguice.inject.InjectView;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.PresentacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Presentacion_Activity extends ListBaseFragment {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;
	public static int VISTA_CARGADA=0;
	
	@Inject PresentacionBLL presentacionBLL;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	
	EfficientAdapter presentaciones;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		 return inflater.inflate(R.layout.consultarpresentacioncompromisoopen_activity, container,false);
		   
	 }
	
	
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
        inicializarRecursos(); 
        this.validarConexionInternet=false;
        //if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 	
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacionActual;
	 		cliente = application.cliente;
	 		txtViewFecha.setText(ConstantesApp.formatFecha(evaluacion.fechaCreacion));
	 		processAsync();
			
         //}
         
	 }
	 
	 
	 @Override
	protected void process() {
		// TODO Auto-generated method stub
		 presentaciones = new EfficientAdapter(this.getActivity(),cliente,evaluacion.presentaciones);
	}
	 
	 
	 @Override
		protected void processOk() {
			// TODO Auto-generated method stub
			setListAdapter(presentaciones);
			super.processOk();
		}
	 public static class EfficientAdapter extends ArrayAdapter<PresentacionTO>{
		 private final List<PresentacionTO> detalle;
		 private final Activity context;
		 
		 public EfficientAdapter(Activity context,ClienteTO cliente,List<PresentacionTO> detalle){
				super(context, R.layout.consultarpresentacioncompromisoopen_content, detalle);
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
			
			public PresentacionTO getItem(int position) {
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
					view = inflator.inflate(R.layout.consultarpresentacioncompromisoopen_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
					holder.btnSKU = (Button) view.findViewById(R.id.btnSKU);
					holder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);
					holder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
					holder.spTipoMarcado =(Spinner) view.findViewById(R.id.spTipoMarcado);
					
					holder.btnSKU.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent skuPresentacion = new Intent(context, SKUPrioritarioCompromiso_Activity.class);
							context.startActivity(skuPresentacion);
						}});
					
					
					holder.spTipoMarcado.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							PresentacionTO oportunidadTO = (PresentacionTO) holder.txViewPuntos.getTag();
							if(arg2==0){
								oportunidadTO.tipoMarcado=ConstantesApp.TIPO_MARCADO_NORMAL;
							}else{
								oportunidadTO.tipoMarcado=ConstantesApp.TIPO_MARCADO_FRIO;
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
					});
					
					  holder.btnFecha.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								PresentacionTO oportunidadTO = (PresentacionTO) holder.txViewPuntos.getTag();
								String[] factores = ConstantesApp.getFechaFactoresAS400(oportunidadTO.fechaCompromiso);
			
								 DatePickerDialog picker = new DatePickerDialog(context, new OnDateSetListener() {

									@Override
									public void onDateSet(DatePicker view,int year, int monthOfYear,int dayOfMonth) {
										// TODO Auto-generated method stub
										PresentacionTO presentacionCompromisoTO = (PresentacionTO)holder.txViewPuntos.getTag();
										
										String mes = ConstantesApp.RPad(String.valueOf(monthOfYear+1),2,'0');
										String dia = ConstantesApp.RPad(String.valueOf(dayOfMonth),2,'0');
										String fecha = String.format("%s%s%s", year,mes,dia);
										presentacionCompromisoTO.fechaCompromiso = fecha;
										holder.txEditFecha.setText(ConstantesApp.formatFecha(presentacionCompromisoTO.fechaCompromiso));
									}
									
									
								}, Integer.parseInt(factores[0]) ,Integer.parseInt(factores[1])-1, Integer.parseInt(factores[2]));
								 
								 picker.show();
							}
						});
					    
					  
					view.setTag(holder);
					
			    	holder.txViewPuntos.setTag(this.detalle.get(position));
			    	
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txViewPuntos.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				PresentacionTO presentacionCompromisoTO = getItem(position);
				holder.txViewPuntos.setText(presentacionCompromisoTO.puntosSugeridos);
				holder.txEditFecha.setText(ConstantesApp.formatFecha(presentacionCompromisoTO.fechaCompromiso));
				
				if(presentacionCompromisoTO.tipoMarcado.compareTo(ConstantesApp.TIPO_MARCADO_NORMAL)==0){
					holder.spTipoMarcado.setSelection(0);
				}else{
					holder.spTipoMarcado.setSelection(1);
				}
				
				return view;
				
			}
		 static class ViewHolder {
				TextView txViewPuntos;
				Button btnSKU;
				EditText txEditFecha;
				ImageButton btnFecha;
				Spinner spTipoMarcado;
			}

	 }
}
