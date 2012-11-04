package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SKUPrioritarioCompromiso_Activity extends  net.msonic.lib.sherlock.ListActivityBase  {

	
	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;

	private EfficientAdapter adap;
	
	/*
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	public static final String DATO_SI = "SI";
	public static final String DATO_NO = "NO";
	public static final String DATO_NO_TIENE = "NV";
	*/
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		this.validarConexionInternet=false;
		
		setContentView(R.layout.skuprioritariocompromiso_activity);
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
		setTitle(R.string.skuprioritario_activity_title);
		
		application = (MyApplication) getApplicationContext();
		
		cliente = application.cliente;
		evaluacion = application.evaluacion;
		
		setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		processAsync();
	}
	/*
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		 return inflater.inflate(R.layout.skuprioritariocompromiso_activity, container,false);
		   
	 }
	
	
	@Override public void onActivityCreated(Bundle savedInstanceState) {
		 inicializarRecursos(); 
	       this.validarConexionInternet=false;
	       
		super.onActivityCreated(savedInstanceState);
      
        if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 	
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacion;
	 		cliente = application.cliente;
	 		
	 		//processAsync();
			
        }
        
	 }*/
	
	
	 @Override
	 protected void process() {
		 adap = new EfficientAdapter(this, evaluacion.skuPresentacion);
		 
	 
	 }
	
	 @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		 setListAdapter(adap);
		 super.processOk();
	}
	
	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		 this.validarConexionInternet=false;
		 
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritariocompromiso_activity);    
		 mActionBar.setTitle(R.string.skuprioritario_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		 
		 evaluacion.skuPresentacion
		 
		 adap = new EfficientAdapter(this, application.listSKUPresentacionCompromiso);
		 setListAdapter(adap);
	}*/
	
	public void btnOK_click(View view)
	{
		finish();
	}
	
	public static class EfficientAdapter extends ArrayAdapter<SKUPresentacionTO> {
    	private Activity context;
		private List<SKUPresentacionTO> skuPresentaciones;

		public EfficientAdapter(Activity context, List<SKUPresentacionTO> skuPresentaciones ){
			super(context, R.layout.skuprioritariocompromiso_content, skuPresentaciones);
			this.context=context;
			this.skuPresentaciones = skuPresentaciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.skuprioritariocompromiso_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (TextView) view.findViewById(R.id.chkValActual);
				viewHolder.chkValComp = (Spinner) view.findViewById(R.id.chkValComp);
			
				
				
				viewHolder.chkValComp.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						SKUPresentacionTO skuPresentacion = (SKUPresentacionTO) viewHolder.chkValComp.getTag();
						if(arg2==0){
							skuPresentacion.compromiso = ConstantesApp.RESPUESTA_NO;
						}else{
							skuPresentacion.compromiso = ConstantesApp.RESPUESTA_SI;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
				ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(context.getApplicationContext(),
																					R.array.confirmacion,
																					android.R.layout.simple_spinner_item);
				adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
				viewHolder.chkValComp.setAdapter(adap);
				
				
								
				view.setTag(viewHolder);
				viewHolder.chkValComp.setTag(this.skuPresentaciones.get(position));
			}else{
				view = convertView;				
				((ViewHolder) view.getTag()).chkValComp.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			
			
			 
			holder.txViewSKU.setText(this.skuPresentaciones.get(position).descripcionSKU);
			
					
			if(this.skuPresentaciones.get(position).valorActual.compareToIgnoreCase(ConstantesApp.RESPUESTA_SI) == 0)
			{
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_SI_LARGA);
			}
			else if(this.skuPresentaciones.get(position).valorActual.compareToIgnoreCase(ConstantesApp.RESPUESTA_NO) == 0)
			{
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_NO_LARGA);
			}
			else
			{
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_NO_TIENE_LARGA);
			}
			
			
			if(this.skuPresentaciones.get(position).compromiso.compareToIgnoreCase(ConstantesApp.RESPUESTA_SI) == 0)
			{
				holder.chkValComp.setSelection(1);
			}
			else
			{
				holder.chkValComp.setSelection(0);
			}
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewSKU;
	    	TextView chkValActual;
	    	Spinner chkValComp;
	    }
	    
	  }    
}
