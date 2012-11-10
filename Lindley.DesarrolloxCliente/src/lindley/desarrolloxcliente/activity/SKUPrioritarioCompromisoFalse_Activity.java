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
	
	/*public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
		this.validarConexionInternet=false;
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
		setTitle(R.string.skuprioritario_activity_title);
		
		application = (MyApplication) getApplicationContext();
		
		cliente = application.cliente;
		evaluacion = application.evaluacionActual;
		
		setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		 adap = new EfficientAdapter(this, evaluacion.skus);
		 setListAdapter(adap);
	}
	
	public void btnOK_click(View view)
	{
		finish();
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
						SkuTO skuPresentacion = (SkuTO) viewHolder.chkValConf.getTag();
						if(isChecked){
							skuPresentacion.marcaCumplio = ConstantesApp.RESPUESTA_SI;
						}else{
							skuPresentacion.marcaCumplio = ConstantesApp.RESPUESTA_NO;
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.txViewSKU.setTag(this.skuPresentaciones.get(position));
				//viewHolder.chkValComp.setTag(this.skuPresentaciones.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewSKU.setTag(this.skuPresentaciones.get(position));
				
				//((ViewHolder) view.getTag()).chkValConf.setTag(this.skuPresentaciones.get(position));
				//((ViewHolder) view.getTag()).chkValComp.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			SkuTO skuTO = this.skuPresentaciones.get(position);
			
			holder.txViewSKU.setText(skuTO.descripcionSKU);
			
			if (skuTO.marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_NO)) {
				holder.chkValActual.setText(ConstantesApp.RESPUESTA_NO_LARGA);
			} if (skuTO.marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI)) {
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
			
			
			
			holder.chkValConf.setChecked(skuTO.marcaCumplio.equals(ConstantesApp.RESPUESTA_SI));
			
			
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
