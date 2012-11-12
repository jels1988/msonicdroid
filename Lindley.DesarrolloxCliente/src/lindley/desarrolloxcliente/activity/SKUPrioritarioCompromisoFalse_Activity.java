package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.SkuTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SKUPrioritarioCompromisoFalse_Activity extends net.msonic.lib.sherlock.ListActivityBase  {

private EfficientAdapter adap;
	
	private  MyApplication application;
	private ClienteTO cliente;
	private EvaluacionTO evaluacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
		this.validarConexionInternet=false;
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
		setTitle(R.string.skuprioritario_activity_title);
		setContentView(R.layout.skuprioritariocompromisofalse_activity);
		
		application = (MyApplication) getApplicationContext();
		
		cliente = application.cliente;
		evaluacion = application.evaluacionActual;
		
		setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		 adap = new EfficientAdapter(this, evaluacion.skus);
		 setListAdapter(adap);
	}
	
	public void btnOK_click(View view)
	{
		asignarConfirmacion();
		finish();
	}
	
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		asignarConfirmacion();
		super.onBackPressed();
	}

	private  void asignarConfirmacion(){
		int compromisos=0;
		int compromisosCumple=0;
		if(adap!=null){
			for(SkuTO sku:adap.skuPresentaciones){
				if(ConstantesApp.isSI(sku.marcaCompromiso)){
					compromisos++;
					if(ConstantesApp.isSI(sku.marcaCumplio)){
						compromisosCumple++;
					}
				}
			}
			if(compromisos>0){
				if(compromisosCumple>=compromisos){
					evaluacion.presentaciones.get(0).confirmacion=ConstantesApp.RESPUESTA_SI;
				}else{
					evaluacion.presentaciones.get(0).confirmacion=ConstantesApp.RESPUESTA_NO;
				}
			}else{
				evaluacion.presentaciones.get(0).confirmacion=ConstantesApp.RESPUESTA_NO;
			}
		}
	}
	public static class EfficientAdapter extends ArrayAdapter<SkuTO> {
    	private Activity context;
		private List<SkuTO> skuPresentaciones;

		public EfficientAdapter(Activity context, List<SkuTO> skuPresentaciones ){
			super(context, R.layout.skuprioritariocompromisofalse_content, skuPresentaciones);
			this.context=context;
			this.skuPresentaciones = skuPresentaciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.skuprioritariocompromisofalse_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (TextView) view.findViewById(R.id.chkValActual);
				viewHolder.chkValComp = (TextView) view.findViewById(R.id.chkValComp);
				viewHolder.chkValConf = (CheckBox) view.findViewById(R.id.chkValConf);
													
				viewHolder.chkValConf.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						SkuTO skuPresentacion = (SkuTO) viewHolder.txViewSKU.getTag();
						if(isChecked){
							skuPresentacion.marcaCumplio = ConstantesApp.RESPUESTA_SI;
						}else{
							skuPresentacion.marcaCumplio = ConstantesApp.RESPUESTA_NO;
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.txViewSKU.setTag(this.skuPresentaciones.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewSKU.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			SkuTO skuTO = this.skuPresentaciones.get(position);
			
			holder.txViewSKU.setText(skuTO.descripcionSKU);
			
			
			if (skuTO.marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_NO)) {
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_NO_LARGA);
			} else if (skuTO.marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI)) {
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_SI_LARGA);
			}else {
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_NO_TIENE_LARGA);
			}
			
			
			if (skuTO.marcaCompromiso.equalsIgnoreCase(ConstantesApp.RESPUESTA_NO)) {
				holder.chkValComp.setText(ConstantesApp.RESPUESTA_NO_LARGA);
			} else if (skuTO.marcaCompromiso.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI)) {
				holder.chkValComp.setText(ConstantesApp.RESPUESTA_SI_LARGA);
			}else {
				holder.chkValComp.setText(ConstantesApp.RESPUESTA_NO_TIENE_LARGA);
			}
			
			
			
			holder.chkValConf.setChecked(ConstantesApp.isSI(skuTO.marcaCumplio));
			
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewSKU;
	    	TextView chkValActual;
	    	TextView chkValComp;
	    	CheckBox chkValConf;
	    }
	    
	  }    
}
