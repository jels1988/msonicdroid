package lindley.desarrolloxcliente.activity;

import java.util.List;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import roboguice.inject.InjectView;
import com.thira.examples.actionbar.widget.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import net.msonic.lib.ListActivityBase;

public class SKUPrioritarioCompromiso_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritariocompromiso_activity);    
		 mActionBar.setTitle(R.string.skuprioritario_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(cliente.getNombre());
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
				view = inflator.inflate(R.layout.skuprioritariocompromiso_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (CheckBox) view.findViewById(R.id.chkValActual);
				viewHolder.chkValComp = (CheckBox) view.findViewById(R.id.chkValComp);
				viewHolder.chkValConf = (CheckBox) view.findViewById(R.id.chkValConf);
						    	
				view.setTag(viewHolder);
				
			}else{
				view = convertView;				
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			final SKUPresentacionCompromisoTO skuPresentacion = skuPresentaciones.get(position);
			
			holder.txViewSKU.setText(skuPresentacion.getDescripcionSKU());
			if(skuPresentacion.getActual().compareToIgnoreCase("S") == 0)
			{
				holder.chkValActual.setChecked(true);
			}
			else
			{
				holder.chkValActual.setChecked(false);
			}
			
			if(skuPresentacion.getCompromiso().compareToIgnoreCase("S") == 0)
			{
				holder.chkValComp.setChecked(true);
			}
			else
			{
				holder.chkValComp.setChecked(false);
			}
			
			if(skuPresentacion.getConfirmacion().compareToIgnoreCase("S") == 0)
			{
				holder.chkValConf.setChecked(true);
			}
			else
			{
				holder.chkValConf.setChecked(false);
			}

			holder.chkValActual.setEnabled(false);
			holder.chkValComp.setEnabled(false);
			holder.chkValConf.setEnabled(false);
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewSKU;
	    	CheckBox chkValActual;
	    	CheckBox chkValComp;
	    	CheckBox chkValConf;
	    }
	    
	  }    
}
