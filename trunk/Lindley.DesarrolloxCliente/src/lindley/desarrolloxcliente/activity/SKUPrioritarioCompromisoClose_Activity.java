package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thira.examples.actionbar.widget.ActionBar;

public class SKUPrioritarioCompromisoClose_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritariocompromisoclose_activity);    
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
			super(context, R.layout.skuprioritariocompromisofalse_content, skuPresentaciones);
			this.context=context;
			this.skuPresentaciones = skuPresentaciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.skuprioritariocompromisoclose_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (TextView) view.findViewById(R.id.chkValActual);
				viewHolder.chkValComp = (TextView) view.findViewById(R.id.chkValComp);
				viewHolder.chkValConf = (TextView) view.findViewById(R.id.chkValConf);
																
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
			
			if (this.skuPresentaciones.get(position).cumplio.equalsIgnoreCase(RESPUESTA_NO)) {
				holder.chkValConf.setText("NO");
			} else if (this.skuPresentaciones.get(position).cumplio.equalsIgnoreCase(RESPUESTA_SI)) {
				holder.chkValConf.setText("SI");
			}
			
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewSKU;
	    	TextView chkValActual;
	    	TextView chkValComp;
	    	TextView chkValConf;
	    }
	    
	  }    
}
