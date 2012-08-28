package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.FotoClusterBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.FotoExitoTO;
//import lindley.desarrolloxcliente.ws.service.ConsultarFotoExitoProxy;
import roboguice.inject.InjectExtra;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
//import com.thira.examples.actionbar.widget.ActionBar;

public class ListarFotoExito_Activity extends net.msonic.lib.sherlock.ListActivityBase {

	//@InjectView(R.id.actionBar) ActionBar mActionBar;
	//@Inject ConsultarFotoExitoProxy consultarFotoExitoProxy;
	public static final String ID_CLUSTER = "clusterId";
	@InjectExtra(ID_CLUSTER) String cluster; 
	@Inject FotoClusterBLL fotoClusterBLL;
	
	
	private ClienteTO cliente;
	private MyApplication application;
	private EfficientAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listarfotoexito_ativity);
		this.validarConexionInternet=false;
		
		application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		
		 setTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		 
        //mActionBar.setTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
        //mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync();
	}
	
	@Override
	protected void process() {
		List<FotoExitoTO> listado = fotoClusterBLL.listar(cluster);
		adapter = new EfficientAdapter(this, listado);
	}

	@Override
	protected void processOk() {
		setListAdapter(adapter);
		super.processOk();
		
		/*
		boolean isExito = consultarFotoExitoProxy.isExito();
		if (isExito) {
			int status = consultarFotoExitoProxy.getResponse().getStatus();
			if (status == 0) {
				List<FotoExitoTO> fotoExito = consultarFotoExitoProxy.getResponse().listFotoExito;
				EfficientAdapter adapter = new EfficientAdapter(this, fotoExito);
				setListAdapter(adapter);
				super.processOk();
			} else {
				processError();
			}

		} else {
			processError();
		}*/

	}

	/*
	@Override
	protected void processError() {
		String message;
		if (consultarFotoExitoProxy.getResponse() != null) {
			message = consultarFotoExitoProxy.getResponse().getDescripcion();
		} else {
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}*/
	

	public static class EfficientAdapter extends ArrayAdapter<FotoExitoTO> {
    	
		private Activity context;
		public List<FotoExitoTO> listFotoExito;
		
		public EfficientAdapter(Activity context,List<FotoExitoTO> listFotoExito ){
			super(context, R.layout.listarfotoexito_content, listFotoExito);
			this.context=context;
			this.listFotoExito = listFotoExito;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.listarfotoexito_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);	
				viewHolder.btnVerFotoExito = (Button) view.findViewById(R.id.btnVerFotoExito);	
				
				view.setTag(viewHolder);
				viewHolder.txtTitulo.setTag(this.listFotoExito.get(position));
				
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txtTitulo.setTag(this.listFotoExito.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			
			final FotoExitoTO fotoExito = this.listFotoExito.get(position);
			
			holder.txtTitulo.setText(fotoExito.titulo);
			
			holder.btnVerFotoExito.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*
					Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
					intent.putExtra(WebViewVerFoto_Activity.TITULO_FOTO, fotoExito.titulo);
					intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, fotoExito.nombre);
					*/
					
					Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
					intent.putExtra(VerFoto_Activity.FILE_NAME, fotoExito.nombre);
					
					context.startActivity(intent);	
				}
			});
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView txtTitulo;
	    	Button   btnVerFotoExito;
	    }
	    
	  }
}
