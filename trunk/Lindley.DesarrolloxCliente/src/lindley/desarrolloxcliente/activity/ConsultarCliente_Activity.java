package lindley.desarrolloxcliente.activity;

//import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.ClienteBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
/*
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

import net.msonic.lib.JSONHelper;
//import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;

public class ConsultarCliente_Activity extends net.msonic.lib.sherlock.ListActivityBase  {

	public static final String CODIGO_CLIENTE_KEY="CODIGO_CLIENTE";
	public static final String USUARIO_KEY="USUARIO";
	
	@InjectExtra(value=CODIGO_CLIENTE_KEY,optional=true) String codigoCliente;
	@InjectExtra(value=USUARIO_KEY,optional=true) String usuario;
	
	@Inject ClienteBLL clienteBLL;
		
	@InjectResource(R.string.consultarcliente_activity_empty) String parametros_empty;
	@InjectResource(R.string.confirm_exit_title) 	String confirm_exit_title;
	@InjectResource(R.string.confirm_exit_message)  String confirm_exit_message;
	@InjectResource(R.string.confirm_exit_yes) 		String confirm_exit_yes;
	@InjectResource(R.string.confirm_exit_no) 		String confirm_exit_no;
	
	
	private EfficientAdapter adap;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
    
    	
        super.onCreate(savedInstanceState);
    	this.validarConexionInternet=false;
        
        
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        
        setContentView(R.layout.consultarcliente_activity);
        
        setTitle(R.string.consultarcliente_activity_title);
        
       
		MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
		
		
		if(codigoCliente!=null){
			application.codigoCliente = codigoCliente;
			UsuarioTO usuarioTO = JSONHelper.desSerializar(usuario, UsuarioTO.class);
			application.setUsuarioTO(usuarioTO);
		
			processAsync();
		}
		else
		{
			if(application.cliente != null)
			{
				List<ClienteTO> clientes = new ArrayList<ClienteTO>();
				clientes.add(application.cliente);
				adap = new EfficientAdapter(this, clientes);
				setListAdapter(adap);
			}
			else
			{				
				UsuarioTO usuarioTO = JSONHelper.desSerializar(usuario, UsuarioTO.class);
				application.setUsuarioTO(usuarioTO);
				codigoCliente = application.codigoCliente;
				processAsync();
			}
		}		
    }
    
   
    public void btnbuscar_onclick(View view){
    	
    	processAsync();
    }
    
   
    
    @Override
	protected void process() {
		// TODO Auto-generated method stub
    	adap = new EfficientAdapter(this,clienteBLL.listarByCodigo(codigoCliente));
    	/*
		consultarClienteProxy.setCodigo(codigoCliente);
		consultarClienteProxy.execute();
		*/
	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
    	/*
		boolean isExito = consultarClienteProxy.isExito();
		if (isExito) {
			int status = consultarClienteProxy.getResponse().getStatus();
			if (status == 0) {
				ClienteTO cliente = consultarClienteProxy.getResponse().getClienteTO();
				MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
				application.setClienteTO(cliente);
				List<ClienteTO> clientes = new ArrayList<ClienteTO>();
				clientes.add(cliente);
				application.cliente = cliente;
				adap = new EfficientAdapter(this, clientes);
				setListAdapter(adap);
			}
			else
			{
				showToast(consultarClienteProxy.getResponse().getDescripcion());
			}
		}
		*/
    	setListAdapter(adap);
		super.processOk();
	}
    
    @Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub    	
    	super.onDestroy();
    	System.exit(1);
    }
    
	
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	MessageBox.showConfirmDialog(this, confirm_exit_title, confirm_exit_message, confirm_exit_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				finish();
			}
			
		}, confirm_exit_no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
			
		});  
    }
    
    public void btnSalir_click(View view)
    {
    	MessageBox.showConfirmDialog(this, confirm_exit_title, confirm_exit_message, confirm_exit_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
//				int pid = android.os.Process.myPid(); 
//				android.os.Process.killProcess(pid);
				onDestroy();			
//				System.exit(0);

//				MyApplication application = (MyApplication)getApplicationContext();
//				application.cliente = new ClienteTO();
			}
			
		}, confirm_exit_no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
			
		});    	

    }
    
	public static class EfficientAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private ConsultarCliente_Activity context;
		private List<ClienteTO> clientes;

		public EfficientAdapter(ConsultarCliente_Activity context, List<ClienteTO> clientes) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
			this.clientes = clientes;
		}

		public EfficientAdapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			//this.context = context;
		}

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ClienteTO cliente = (ClienteTO) getItem(position);
			ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.consultarcliente_content, null);

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();
				
				holder.txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
				holder.txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigo);
				holder.txtNombre = (TextView) convertView.findViewById(R.id.txtNombre);
				holder.txtFrecuencia = (TextView) convertView.findViewById(R.id.txtFrecuencia);
				holder.txtAlcance = (TextView) convertView.findViewById(R.id.txtAlcance);
				holder.txtFalta = (TextView) convertView.findViewById(R.id.txtFalta);
				holder.txtCluster = (TextView) convertView.findViewById(R.id.txtCluster);
				holder.txtMC = (TextView) convertView.findViewById(R.id.txtMC);
				holder.txtNPuntos = (TextView) convertView.findViewById(R.id.txtNPuntos);
				holder.txtSigNivel = (TextView) convertView.findViewById(R.id.txtSigNivel);
				holder.imgDireccion = (ImageButton) convertView.findViewById(R.id.btn_direccion);
				holder.imgCabecera = (ImageButton) convertView.findViewById(R.id.btn_cabecera);
				holder.imgNuevo = (ImageButton) convertView.findViewById(R.id.btn_nuevo);
				holder.imgArticulo = (ImageButton) convertView.findViewById(R.id.btn_articulo);
					
				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txtFecha.setText(cliente.fecha);
			holder.txtCodigo.setText(cliente.codigo);
			holder.txtNombre.setText(cliente.nombre);
			
			holder.txtFrecuencia.setText(cliente.frecuencia);
			holder.txtAlcance.setText(ConstantesApp.formatPorcentaje(cliente.alcance,2));
			
			//holder.txtAlcance.setText(cliente.getAlcance() + " %");
			//if(cliente.getFalta().equals(""))cliente.setFalta("0");
			
			
	
			holder.txtFalta.setText(String.format("%s CU", cliente.falta));
			holder.txtCluster.setText(cliente.cluster);
			holder.txtMC.setText(cliente.mc);
			holder.txtNPuntos.setText(String.format("%s", cliente.nroPuntos));
			holder.txtSigNivel.setText(String.format("%s", cliente.nivelCanje));
			
			double lat = cliente.latitud;
			double lng = cliente.longitud;

			if (lat != 0 && lng != 0) {
				holder.imgDireccion.setVisibility(View.VISIBLE);
			}

			holder.imgDireccion.setOnClickListener(new OnClickListener() {
				ClienteTO clienteTemporal = (ClienteTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent direccionCliente = new Intent(context, lindley.desarrolloxcliente.DireccionActivity.class);
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.LATITUD_KEY,clienteTemporal.latitud);
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.LONGITUD_KEY,clienteTemporal.longitud);
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.DIRECCION_KEY,clienteTemporal.direccion);
					context.startActivity(direccionCliente);
				}
			});
			
			holder.imgCabecera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent cabecera = new Intent(context, ConsultarCabecera_Activity.class);
					//cabecera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(cabecera);
									
				}
			});
			
			holder.imgNuevo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ClienteTO clienteTemporal = (ClienteTO) getItem(position);
					if(clienteTemporal.evaluacionesAbiertas>0){
						context.showToast("El cliente tiene una evaluaci—n abierta.");
					}else{
						MyApplication app = (MyApplication)context.getApplication();
						app.cliente=clienteTemporal;
						Intent oportunidad = new Intent(context, ConsultarOportunidad_Activity.class);
						context.startActivity(oportunidad);
					}
					
				}
			});
			
			holder.imgArticulo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent("lindley.desarrolloxcliente.verarticuloscanje");
					context.startActivity(intent);					
				}
			});
			
			return convertView;
		}

		static class ViewHolder {
			TextView txtFecha;
			TextView txtCodigo;
			TextView txtNombre;
			TextView txtFrecuencia;
			TextView txtAlcance;
			TextView txtFalta;
			TextView txtCluster;
			TextView txtMC;
			TextView txtNPuntos;
			TextView txtSigNivel;
			ImageButton imgDireccion;
			ImageButton imgCabecera;
			ImageButton imgNuevo;
			ImageButton imgArticulo;
		}

		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// return data.length;
			if (clientes == null) {
				return 0;
			} else {
				return clientes.size();
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return clientes.get(position);
		}

	}
	
	


}