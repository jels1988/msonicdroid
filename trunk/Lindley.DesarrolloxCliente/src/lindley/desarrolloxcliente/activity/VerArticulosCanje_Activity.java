package lindley.desarrolloxcliente.activity;

import java.util.List;

import com.google.inject.Inject;

import roboguice.inject.InjectExtra;

import net.msonic.lib.sherlock.ListActivityBase;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.download.ArticuloTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class VerArticulosCanje_Activity extends ListActivityBase {
	
	
	public static final String CODIGO_CLIENTE_KEY="CODIGO_CLIENTE_KEY";
	public static final String NOMBRE_CLIENTE_KEY="NOMBRE_CLIENTE_KEY";
	public static final String PUNTOS_CLIENTE_KEY="PUNTOS_CLIENTE_KEY";
	
	
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	@InjectExtra(NOMBRE_CLIENTE_KEY) String nombreCliente;
	@InjectExtra(PUNTOS_CLIENTE_KEY) int puntosCliente;
	@Inject EvaluacionBLL evaluacionBLL;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.verarticuloscanje_activity);    
		 setTitle(R.string.verproductoscanje_activity_title);
		 setSubTitle(String.format("%s - %s", codigoCliente ,nombreCliente));
		 processAsync();
	}
	


	EfficientAdapter adapter = null;
	@Override
	protected void process()  throws Exception{
		List<ArticuloTO> articulos = evaluacionBLL.consultarArticulos(puntosCliente);
		adapter = new EfficientAdapter(this, articulos);
	}
	
	@Override
	protected void processOk() {
		setListAdapter(adapter);
		super.processOk();

	}


	public static class EfficientAdapter extends ArrayAdapter<ArticuloTO> {
    	
		private Activity context;
		public List<ArticuloTO> listArticuloCanje;
		
		public EfficientAdapter(Activity context,List<ArticuloTO> listArticuloCanje ){
			super(context, R.layout.verarticuloscanje_content, listArticuloCanje);
			this.context=context;
			this.listArticuloCanje = listArticuloCanje;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.verarticuloscanje_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewArticulo = (TextView) view.findViewById(R.id.txViewArticulo);	
				viewHolder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);	
				
				view.setTag(viewHolder);
				viewHolder.txViewArticulo.setTag(this.listArticuloCanje.get(position));
				
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewArticulo.setTag(this.listArticuloCanje.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			
			final ArticuloTO articuloCanje = this.listArticuloCanje.get(position);
			
			holder.txViewArticulo.setText(articuloCanje.descripcionArticulo);
			holder.txViewPuntos.setText(articuloCanje.puntosCanje);
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txViewArticulo;
	    	TextView txViewPuntos;
	    }
	    
	  }
}
