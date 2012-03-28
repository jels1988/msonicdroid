package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import net.msonic.lib.ListActivityBase;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.thira.examples.actionbar.widget.ActionBar;

public class VerCompromisosClose_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.vercompromisosclose_activity);    
		 mActionBar.setTitle(R.string.compromiso_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
		 mActionBar.setHomeLogo(R.drawable.header_logo);

		 if(application.listCompromiso == null)
			 application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
		 adap = new EfficientAdapter(this, application.listCompromiso);
		 setListAdapter(adap);
	}
	
	public void btnGuardar_click(View view)
	{
		finish();
	}
	
	public void btnAgregar_click(View view)
	{
		CompromisoPosicionTO compromiso = new CompromisoPosicionTO();
		compromiso.setDescripcion("");
		application.listCompromiso.add(compromiso);
		adap.notifyDataSetChanged();
	}
	
	public static class EfficientAdapter extends ArrayAdapter<CompromisoPosicionTO> {
    	
		private Activity context;
		public List<CompromisoPosicionTO> compromisos;
		
		public EfficientAdapter(Activity context,List<CompromisoPosicionTO> compromisos ){
			super(context, R.layout.vercompromisosclose_content, compromisos);
			this.context=context;
			this.compromisos = compromisos;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.vercompromisosclose_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewComp = (TextView) view.findViewById(R.id.txViewComp);				
				view.setTag(viewHolder);
				viewHolder.txViewComp.setTag(this.compromisos.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewComp.setTag(this.compromisos.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final CompromisoPosicionTO compromisoTO = compromisos.get(position);
			
			holder.txViewComp.setText(compromisoTO.getDescripcion());
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewComp;
	    }
	    
	  }
	
}
