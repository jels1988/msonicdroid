package lindley.desarrolloxcliente.activity;

import java.util.List;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import com.thira.examples.actionbar.widget.ActionBar;
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
import net.msonic.lib.ListActivityBase;

public class SKUPrioritarioCompromisoFalse_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	public final static String FLAG_ESTADO = "estado_flag";
	
	public final static String FLAG_FECHA = "fecha_flag";
	@InjectExtra(FLAG_FECHA) static String flagFecha;
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritariocompromisofalse_activity);    
		 mActionBar.setTitle(R.string.skuprioritario_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		 
		 adap = new EfficientAdapter(this, application.listSKUPresentacionCompromiso);
		 setListAdapter(adap);
	}
	
	public void btnOK_click(View view)
	{
		finish();
	}
	
	public static class EfficientAdapter extends ArrayAdapter<SKUPresentacionCompromisoTO> {
    	private Activity context;
		private List<SKUPresentacionCompromisoTO> skuPresentaciones;

		public EfficientAdapter(Activity context, List<SKUPresentacionCompromisoTO> skuPresentaciones ){
			super(context, R.layout.skuprioritariocompromiso_content, skuPresentaciones);
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
						SKUPresentacionCompromisoTO skuPresentacion = (SKUPresentacionCompromisoTO) viewHolder.chkValConf.getTag();
						if(isChecked){
							skuPresentacion.cumplio = "S";
						}else{
							skuPresentacion.cumplio = "N";
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.chkValConf.setTag(this.skuPresentaciones.get(position));
				viewHolder.chkValComp.setTag(this.skuPresentaciones.get(position));
			}else{
				view = convertView;				
				((ViewHolder) view.getTag()).chkValConf.setTag(this.skuPresentaciones.get(position));
				((ViewHolder) view.getTag()).chkValComp.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
						
			holder.txViewSKU.setText(this.skuPresentaciones.get(position).descripcionSKU);
			
			if (this.skuPresentaciones.get(position).actual.equalsIgnoreCase(RESPUESTA_NO)) {
				holder.chkValActual.setText("NO");
			} else if (this.skuPresentaciones.get(position).actual.equalsIgnoreCase(RESPUESTA_SI)) {
				holder.chkValActual.setText("SI");
			}else {
				holder.chkValActual.setText("NV");
			}
			
			if (this.skuPresentaciones.get(position).compromiso.equalsIgnoreCase(RESPUESTA_NO)) {
				holder.chkValComp.setText("NO");
			} else if (this.skuPresentaciones.get(position).compromiso.equalsIgnoreCase(RESPUESTA_SI)) {
				holder.chkValComp.setText("SI");
			}
			
			if(this.skuPresentaciones.get(position).cumplio.compareToIgnoreCase("S") == 0)
			{
				holder.chkValConf.setChecked(true);
			}
			else
			{
				holder.chkValConf.setChecked(false);
			}		
			
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
