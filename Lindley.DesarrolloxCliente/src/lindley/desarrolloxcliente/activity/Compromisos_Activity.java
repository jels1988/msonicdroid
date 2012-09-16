package lindley.desarrolloxcliente.activity;
import java.util.List;


import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Compromisos_Activity extends ListBaseFragment {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private EfficientAdapter oportunidades;
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 return inflater.inflate(R.layout.compromisoopen_activity, container,false);
	 }
	 
	 private static int VISTA_CARGADA=0;
	 
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
				    	
				    	
				    	MyApplication app = (MyApplication)context.getApplication();
				    	
				    	holder.txViewAccTrade.setAdapter(app.getAdapterAccionTrade(this.detalle.get(position).listaAccionesTrade));

					      if(holder.txViewAccTrade.getCount() > 1)
					    	  holder.txViewAccTrade.setSelection(1);
					      
						 
					      holder.txViewAccTrade.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									
									// TODO Auto-generated method stub
									if(arg2 > 0){
										oportunidadTO.descAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getDescripcion());
										oportunidadTO.codigoAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getCodigo());
									}else{
										oportunidadTO.descAccionTrade  = "";
										oportunidadTO.codigoAccionTrade = "";
									}
								}

								@Override
								public void onNothingSelected(AdapterView<?> arg0) {
									// TODO Auto-generated method stub
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									oportunidadTO.descAccionTrade  = "";
									oportunidadTO.codigoAccionTrade = "";
								}
								
							});
					      
						  holder.cboConcrecionCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
									// TODO Auto-generated method stub
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									
									if(arg2==0){
										oportunidadTO.concrecionActual = ConstantesApp.RESPUESTA_NO;
									}else{
										oportunidadTO.concrecionActual = ConstantesApp.RESPUESTA_SI;
									}
								}

								@Override
								public void onNothingSelected(AdapterView<?> arg0) {
									// TODO Auto-generated method stub
									
								}
							});
						  
						  holder.txViewSOVICmp.setOnFocusChangeListener(new OnFocusChangeListener() {
								
								@Override
								public void onFocusChange(View v, boolean hasFocus) {
									// TODO Auto-generated method stub
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									oportunidadTO.soviActual = holder.txViewSOVICmp.getText().toString();
								}
							});
						  
						    
						  holder.cboCumPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
								// TODO Auto-generated method stub
								OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
								if(arg2==0){
									oportunidadTO.cumplePrecio = ConstantesApp.RESPUESTA_NO;
								}else{
									oportunidadTO.cumplePrecio = ConstantesApp.RESPUESTA_SI;
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
						});
						  
						  holder.cboCumPrecioCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
									// TODO Auto-generated method stub
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									
									if(arg2==0){
										oportunidadTO.cumplePrecioActual = ConstantesApp.RESPUESTA_NO;
									}else{
										oportunidadTO.cumplePrecioActual = ConstantesApp.RESPUESTA_SI;
									}
								}

								@Override
								public void onNothingSelected(AdapterView<?> arg0) {
									// TODO Auto-generated method stub
									
								}
							});
						  
						  holder.cboSaboresCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0, View arg1,
										int arg2, long arg3) {
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									
									// TODO Auto-generated method stub
									if(arg2==0){
										oportunidadTO.numeroSaboresActual = 2;
									} else if(arg2 == 1){
										oportunidadTO.numeroSaboresActual = 3;
									} else if(arg2 == 2){
										oportunidadTO.numeroSaboresActual = 4;
									}
									else{
										oportunidadTO.numeroSaboresActual = 0;
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
									OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
									 int[] factores = ConstantesApp.getFechaFactores(oportunidadTO.fechaOportunidad);
									 
									 DatePickerDialog picker = new DatePickerDialog(context, new OnDateSetListener() {
										
										@Override
										public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
											// TODO Auto-generated method stub
											OportunidadTO oportunidadTO = (OportunidadTO) holder.txViewPuntos.getTag();
											String mes = ConstantesApp.RPad(String.valueOf(monthOfYear+1),2,'0');
											String dia = ConstantesApp.RPad(String.valueOf(dayOfMonth),2,'0');
											String fecha = String.format("%s/%s/%s", dia,mes, year);
											oportunidadTO.fechaOportunidad=fecha;
											holder.txEditFecha.setText(oportunidadTO.fechaOportunidad);
										}
									}, factores[0],factores[1], factores[2]);
									 
									 picker.show();
								}
							});
						    
						  ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this.context.getApplicationContext(),R.array.confirmacion,android.R.layout.simple_spinner_item);
						  adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
						  holder.cboCumPrecio.setAdapter(adap);
						  holder.cboCumPrecioCmp.setAdapter(adap);
						  holder.cboConcrecionCmp.setAdapter(adap);
						  
						  ArrayAdapter<CharSequence> adapSabores = ArrayAdapter.createFromResource(this.context.getApplicationContext(),R.array.numero_sabores,android.R.layout.simple_spinner_item);
						  adapSabores.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
						  holder.cboSaboresCmp.setAdapter(adapSabores);

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
