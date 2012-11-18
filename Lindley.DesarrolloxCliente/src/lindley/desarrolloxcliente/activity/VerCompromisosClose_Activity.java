package lindley.desarrolloxcliente.activity;

import java.util.List;

import roboguice.inject.InjectExtra;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class VerCompromisosClose_Activity  extends net.msonic.lib.sherlock.ListActivityBase {

	private EfficientAdapter adap;
	private MyApplication application;
	private ClienteTO cliente;
	private EvaluacionTO evaluacion;
	
	public static final String POSICION_KEY="POSICION_KEY";
	@InjectExtra(value=POSICION_KEY) private int posicion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vercompromisosclose_activity);    
		this.validarConexionInternet=false;
		 
		
		 setTitle(R.string.compromiso_title);
		 application = (MyApplication)getApplicationContext();
 		 evaluacion = application.evaluacionActual;
 		 cliente = application.cliente;
		 
		 setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 processAsync();
		 
		 /*
		 mActionBar.setTitle(R.string.compromiso_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 mActionBar.setHomeLogo(R.drawable.header_logo);

		 if(application.listCompromiso == null)
			 application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
		 adap = new EfficientAdapter(this, application.listCompromiso);
		 setListAdapter(adap);*/
	}
	
	@Override
	protected void process() throws Exception {
		// TODO Auto-generated method stub
		adap = new EfficientAdapter(this, evaluacion.posiciones.get(posicion).compromisos);
		super.process();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		setListAdapter(adap);
		super.processOk();
	}
	
	public void btnGuardar_click(View view)
	{
		finish();
	}
		
	public static class EfficientAdapter extends ArrayAdapter<PosicionCompromisoTO> {
    	
		private Activity context;
		public List<PosicionCompromisoTO> compromisos;
		
		public EfficientAdapter(Activity context,List<PosicionCompromisoTO> compromisos ){
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
			final PosicionCompromisoTO compromisoTO = compromisos.get(position);
			
			holder.txViewComp.setText(compromisoTO.observacion);
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewComp;
	    }
	    
	  }
	
}
