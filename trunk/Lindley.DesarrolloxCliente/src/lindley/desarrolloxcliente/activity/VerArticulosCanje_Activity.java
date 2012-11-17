package lindley.desarrolloxcliente.activity;

import java.util.List;

import net.msonic.lib.sherlock.ListActivityBase;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ArticuloCanjeTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.ws.service.ConsultarArticulosCanjeProxy;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;

public class VerArticulosCanje_Activity extends ListActivityBase {
	
	private MyApplication application;
	ClienteTO cliente;
	@Inject ConsultarArticulosCanjeProxy consultarArticulosCanjeProxy;
	int puntos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.verarticuloscanje_activity);    
		 //mActionBar.setTitle(R.string.verproductoscanje_activity_title);
		 application = (MyApplication)getApplicationContext();
		 cliente = application.getClienteTO();
		 //mActionBar.setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 //mActionBar.setHomeLogo(R.drawable.header_logo);
		 puntos = cliente.nroPuntos;
		 processAsync();
	}
	
	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean tieneError=false;
		
		if( puntos == 0 ){		
			showToast("El cliente no cuenta con puntos.");			
			tieneError=true;
			finish();
		}
		
		return !tieneError;
	}

	@Override
	protected void process()  throws Exception{
		consultarArticulosCanjeProxy.puntos = this.puntos;
		consultarArticulosCanjeProxy.execute();
	}
	
	@Override
	protected void processOk() {
		boolean isExito = consultarArticulosCanjeProxy.isExito();

		if (isExito) {
			int status = consultarArticulosCanjeProxy.getResponse().getStatus();
			if (status == 0) {
				List<ArticuloCanjeTO> listArticuloCanjeTO = consultarArticulosCanjeProxy.getResponse().listArticuloCanje;
				EfficientAdapter adapter = new EfficientAdapter(this, listArticuloCanjeTO);
				setListAdapter(adapter);
				super.processOk();
			} else {
				processError();
			}

		} else {
			processError();
		}

	}

	@Override
	protected void processError() {
		String message;
		if (consultarArticulosCanjeProxy.getResponse() != null) {
			message = consultarArticulosCanjeProxy.getResponse().getDescripcion();
		} else {
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}
	
	public static class EfficientAdapter extends ArrayAdapter<ArticuloCanjeTO> {
    	
		private Activity context;
		public List<ArticuloCanjeTO> listArticuloCanje;
		
		public EfficientAdapter(Activity context,List<ArticuloCanjeTO> listArticuloCanje ){
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
			
			final ArticuloCanjeTO articuloCanje = this.listArticuloCanje.get(position);
			
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
