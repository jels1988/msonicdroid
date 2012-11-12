package lindley.desarrolloxcliente.activity;

import java.util.List;

import roboguice.inject.InjectExtra;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class VerCompromisosOpen_Activity extends net.msonic.lib.sherlock.ListActivityBase {

	public static final String POSICION_KEY="POSICION_KEY";
	
	private EfficientAdapter adap;
	private MyApplication application;
	private ClienteTO cliente;
	private EvaluacionTO evaluacion;
	@InjectExtra(value=POSICION_KEY) private int posicion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.vercompromisosopen_activity);   
		 this.validarConexionInternet=false;
		 
		 setTitle(R.string.compromiso_title);
		 application = (MyApplication)getApplicationContext();
 		 evaluacion = application.evaluacionActual;
 		 cliente = application.cliente;
		 
		 setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 processAsync();
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
	
	public void btnAdd_click(View view)
	{
		PosicionCompromisoTO compromiso = new PosicionCompromisoTO();
		compromiso.observacion="";
		compromiso.estado = ConstantesApp.OPORTUNIDAD_ABIERTA;
		compromiso.codigoVariable = ConstantesApp.VARIABLE_RED_ESTANDAR_ANAQUEL;
		compromiso.tipoAgrupacion = ConstantesApp.TIPO_AGRUPRACION_POSICION;
		compromiso.orden = evaluacion.posiciones.get(posicion).compromisos.size();
		evaluacion.posiciones.get(posicion).compromisos.add(compromiso);
		adap.notifyDataSetChanged();
	}
	
	public void btnQuitar_click(View view)
	{
		if(!evaluacion.posiciones.get(posicion).compromisos.isEmpty()){
			evaluacion.posiciones.get(posicion).compromisos.remove(evaluacion.posiciones.get(posicion).compromisos.size()-1);
			adap.notifyDataSetChanged();
		}
	}
	
	public static class EfficientAdapter extends ArrayAdapter<PosicionCompromisoTO> {
    	
		private Activity context;
		public List<PosicionCompromisoTO> compromisos;
		
		public EfficientAdapter(Activity context,List<PosicionCompromisoTO> compromisos ){
			super(context, R.layout.vercompromisosopen_content, compromisos);
			this.context=context;
			this.compromisos = compromisos;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.vercompromisosopen_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewComp = (EditText) view.findViewById(R.id.txViewComp);
				viewHolder.txViewComp.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.txViewComp.getTag();
						
						if(compromiso!=null){
						compromiso.observacion = s.toString();
						}
					}
				});

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
	    	EditText txViewComp;
	    }
	    
	  }
	
}
