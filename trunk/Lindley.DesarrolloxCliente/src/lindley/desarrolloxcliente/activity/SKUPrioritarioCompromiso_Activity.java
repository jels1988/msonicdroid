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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.thira.examples.actionbar.widget.ActionBar;

public class SKUPrioritarioCompromiso_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	public static MyApplication application;
	ClienteTO cliente;
		
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	public static final String DATO_SI = "SI";
	public static final String DATO_NO = "NO";
	public static final String DATO_NO_TIENE = "NV";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.skuprioritariocompromiso_activity);    
		 mActionBar.setTitle(R.string.skuprioritario_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
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
				viewHolder.chkValActual = (TextView) view.findViewById(R.id.chkValActual);
				viewHolder.chkValComp = (Spinner) view.findViewById(R.id.chkValComp);
				
				ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.confirmacion,android.R.layout.simple_spinner_item);
				adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
				viewHolder.chkValComp.setAdapter(adap);
				
				
						    	
				viewHolder.chkValComp.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						SKUPresentacionCompromisoTO skuPresentacion = (SKUPresentacionCompromisoTO) viewHolder.chkValComp.getTag();
						if(arg2==0){
							skuPresentacion.compromiso = RESPUESTA_NO;
						}else{
							skuPresentacion.compromiso = RESPUESTA_SI;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
								
				view.setTag(viewHolder);
				viewHolder.chkValComp.setTag(this.skuPresentaciones.get(position));
			}else{
				view = convertView;				
				((ViewHolder) view.getTag()).chkValComp.setTag(this.skuPresentaciones.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			
						 
			holder.txViewSKU.setText(this.skuPresentaciones.get(position).descripcionSKU);
						
			
			if(this.skuPresentaciones.get(position).actual.compareToIgnoreCase(RESPUESTA_SI) == 0)
			{
				holder.chkValActual.setText(DATO_SI);
			}
			else if(this.skuPresentaciones.get(position).actual.compareToIgnoreCase(RESPUESTA_NO) == 0)
			{
				holder.chkValActual.setText(DATO_NO);
			}
			else
			{
				holder.chkValActual.setText(DATO_NO_TIENE);
			}
			
			
			if(this.skuPresentaciones.get(position).compromiso.compareToIgnoreCase(RESPUESTA_SI) == 0)
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
