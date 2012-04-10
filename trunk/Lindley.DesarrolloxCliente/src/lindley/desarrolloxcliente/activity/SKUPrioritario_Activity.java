package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.activity.ConsultarPresentacion_Activity.EfficientAdapter;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PresentacionTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.ws.service.ConsultarSKUPrioritarioProxy;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
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

public class SKUPrioritario_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	@Inject ConsultarSKUPrioritarioProxy consultarSKUPrioritarioProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritario_activity);    
		 mActionBar.setTitle(R.string.skuprioritario_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		 processAsync(); 
	}
	
	@Override
   	protected void process() {
		consultarSKUPrioritarioProxy.codigoCluster = cliente.getCluster();
		consultarSKUPrioritarioProxy.execute();
   	}
	
	 @Override
		protected void processOk() {
			// TODO Auto-generated method stub
			boolean isExito = consultarSKUPrioritarioProxy.isExito();
			if (isExito) {
				int status = consultarSKUPrioritarioProxy.getResponse().getStatus();
				if (status == 0) {
					List<SKUPresentacionTO> listaSKUPresentacion = consultarSKUPrioritarioProxy
							.getResponse().listaSKUPresentacion;	
					adap = new EfficientAdapter(this, listaSKUPresentacion);				
					final Calendar c = Calendar.getInstance();      
					if(listaSKUPresentacion.size()>0)
						txtViewFecha.setText(pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
					setListAdapter(adap);
				}
				else  {
					showToast(consultarSKUPrioritarioProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError();
			}
			super.processOk();
		}
	    
	    @Override
		protected void processError() {
			// TODO Auto-generated method stub
			super.processError();
			showToast(error_generico_process);
		}
	    
	public void btnOK_click(View view)
	{
		finish();
	}
	
	public static class EfficientAdapter extends ArrayAdapter<SKUPresentacionTO> {
    	private Activity context;
		private List<SKUPresentacionTO> skuPresentaciones;

		public EfficientAdapter(Activity context, List<SKUPresentacionTO> skuPresentaciones ){
			super(context, R.layout.skuprioritario_content, skuPresentaciones);
			this.context=context;
			this.skuPresentaciones = skuPresentaciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.skuprioritario_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (CheckBox) view.findViewById(R.id.chkValActual);
						    	
				viewHolder.chkValActual.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						SKUPresentacionTO skuPresentacion = (SKUPresentacionTO) viewHolder.chkValActual.getTag();
						skuPresentacion.seleccionado = isChecked;
					}
				});

				view.setTag(viewHolder);
				viewHolder.chkValActual.setTag(this.skuPresentaciones.get(position));
				
			}else{
				view = convertView;				
				((ViewHolder) view.getTag()).chkValActual.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			SKUPresentacionTO skuPresentacion = skuPresentaciones.get(position);
			
			holder.txViewSKU.setText(skuPresentacion.getDescripcionSKU());
			if(this.skuPresentaciones.get(position).seleccionado)
			{
				holder.chkValActual.setChecked(true);
			}
			else
			{
				holder.chkValActual.setChecked(false);
			}
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewSKU;
	    	CheckBox chkValActual;
	    }
	    
	  }    
}
