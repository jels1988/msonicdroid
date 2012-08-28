package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
//import net.msonic.lib.ListActivityBase;
//import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

//import com.thira.examples.actionbar.widget.ActionBar;

public class VerCompromisosOpen_Activity extends net.msonic.lib.sherlock.ListActivityBase {

	//@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	private int posicion=-1;
	private EvaluacionTO evaluacion;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.vercompromisosopen_activity);   
		 this.validarConexionInternet=false;
		 setTitle(R.string.compromiso_title);
		 application = (MyApplication)getApplicationContext();
		 
 		 evaluacion = application.evaluacion;
 		 cliente = application.cliente;
		 posicion = application.compromisoPosicion;
		 
		 setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 

		 if(evaluacion.posiciones.get(posicion)==null){
			 evaluacion.posiciones.get(posicion).listCompromisos = new ArrayList<CompromisoPosicionTO>();
		 }
		 
		 adap = new EfficientAdapter(this, evaluacion.posiciones.get(posicion).listCompromisos);
		 setListAdapter(adap);
	}
	
	public void btnGuardar_click(View view)
	{
		/*for(CompromisoPosicionTO comp : application.evaluacion.posiciones.get(posicion).listCompromisos)
		{
			if(comp.getDescripcion().equals(""))
				comp.setDescripcion("  ");
			
		}*/
		finish();
	}
	
	public void btnAgregar_click(View view)
	{
		CompromisoPosicionTO compromiso = new CompromisoPosicionTO();
		compromiso.setDescripcion("");
		evaluacion.posiciones.get(posicion).listCompromisos.add(compromiso);
		adap.notifyDataSetChanged();
	}
	
	public void btnQuitar_click(View view)
	{
		if(!evaluacion.posiciones.get(posicion).listCompromisos.isEmpty())
			evaluacion.posiciones.get(posicion).listCompromisos.remove(evaluacion.posiciones.get(posicion).listCompromisos.size()-1);
		adap.notifyDataSetChanged();
	}
	
	public static class EfficientAdapter extends ArrayAdapter<CompromisoPosicionTO> {
    	
		private Activity context;
		public List<CompromisoPosicionTO> compromisos;
		
		public EfficientAdapter(Activity context,List<CompromisoPosicionTO> compromisos ){
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
						    	
				viewHolder.txViewComp.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						CompromisoPosicionTO compromiso = (CompromisoPosicionTO) viewHolder.txViewComp.getTag();
						compromiso.setDescripcion(viewHolder.txViewComp.getText().toString());
					}
				});
				
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
	    	EditText txViewComp;
	    }
	    
	  }
	
}
