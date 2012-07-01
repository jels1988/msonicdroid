package lindley.desarrolloxcliente.activity;

//import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ConsultarClienteProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarEvaluacionesAbiertasProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
/*
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
*/
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.JSONHelper;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;

public class ConsultarCliente_Activity extends ListActivityBase {

	@InjectExtra(value="CODIGO_CLIENTE",optional=true) String codigoCliente;
	@InjectExtra(value="USUARIO",optional=true) String usuario;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarClienteProxy consultarClienteProxy;
	@InjectView(R.id.txtCodigo)TextView txtCodigo;
	@InjectView(R.id.txtRuc)TextView txtRuc;
	@InjectView(R.id.txtDni)TextView txtDni;
	@InjectView(R.id.txtRazonSocial)TextView txtRazonSocial;
	
	@InjectResource(R.string.consultarcliente_activity_empty) String parametros_empty;
	@InjectResource(R.string.confirm_exit_title) 	String confirm_exit_title;
	@InjectResource(R.string.confirm_exit_message)  String confirm_exit_message;
	@InjectResource(R.string.confirm_exit_yes) 		String confirm_exit_yes;
	@InjectResource(R.string.confirm_exit_no) 		String confirm_exit_no;
	
	@Inject ConsultarEvaluacionesAbiertasProxy consultarEvaluacionesAbiertasProxy;
	public static final int ACCION_CONSULTAR = 2;
	
	private EfficientAdapter adap;
	
	//public static String file_name="";
	//private static final int TAKE_PHOTO_CODE = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarcliente_activity);
        
        mActionBar.setTitle(R.string.consultarcliente_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo); 
        
        txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
		txtRuc.addTextChangedListener(txtRucTextWatcher);
		txtDni.addTextChangedListener(txtDniTextWatcher);
		txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);
		
		MyApplication application = (MyApplication)contextProvider.get().getApplicationContext();
		
		if(codigoCliente!=null){
			
			application.codigoCliente = codigoCliente;
			UsuarioTO usuarioTO = JSONHelper.desSerializar(usuario, UsuarioTO.class);
			application.setUsuarioTO(usuarioTO);
		
			txtCodigo.setText(codigoCliente);
			processAsync();
		}
		else
		{
			if(application.cliente != null)
			{
				txtCodigo.setText(application.cliente.getCodigo());
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
				txtCodigo.setText(application.codigoCliente);
				processAsync();
			}
		}		
    }
    
	private TextWatcher txtRucTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtRuc.getText() != null) {

				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);
				txtRazonSocial.removeTextChangedListener(txtRazonSocialTextWatcher);

				txtCodigo.setText(null);
				txtDni.setText(null);
				txtRazonSocial.setText(null);

				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
				txtDni.addTextChangedListener(txtDniTextWatcher);
				txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	private TextWatcher txtDniTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtDni.getText() != null) {
				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtRuc.removeTextChangedListener(txtRucTextWatcher);
				txtRazonSocial.removeTextChangedListener(txtRazonSocialTextWatcher);

				txtCodigo.setText(null);
				txtRuc.setText(null);
				txtRazonSocial.setText(null);
				
				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
				txtRuc.addTextChangedListener(txtRucTextWatcher);
				txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);

			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	private TextWatcher txtCodigoTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtCodigo.getText() != null) {

				txtRuc.removeTextChangedListener(txtRucTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);
				txtRazonSocial.removeTextChangedListener(txtRazonSocialTextWatcher);

				txtRuc.setText(null);
				txtDni.setText(null);
				txtRazonSocial.setText(null);

				txtRuc.addTextChangedListener(txtRucTextWatcher);
				txtDni.addTextChangedListener(txtDniTextWatcher);
				txtRazonSocial.addTextChangedListener(txtRazonSocialTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	private TextWatcher txtRazonSocialTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtCodigo.getText() != null) {

				txtRuc.removeTextChangedListener(txtRucTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);
				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);

				txtRuc.setText(null);
				txtDni.setText(null);
				txtCodigo.setText(null);

				txtRuc.addTextChangedListener(txtRucTextWatcher);
				txtDni.addTextChangedListener(txtDniTextWatcher);
				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
    
    public void btnbuscar_onclick(View view){
    	//processAsync();
    }
    
    @Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean isCodigo = txtCodigo.getText().toString().trim().equalsIgnoreCase("");
		boolean isRuc = txtRuc.getText().toString().trim().equalsIgnoreCase("");
		boolean isDni = txtDni.getText().toString().trim().equalsIgnoreCase("");
		boolean isRazonSocial = txtRazonSocial.getText().toString().trim().equalsIgnoreCase("");

		if (isCodigo && isRuc && isDni && isRazonSocial) {
			txtCodigo.setError(parametros_empty);
			txtRuc.setError(parametros_empty);
			txtDni.setError(parametros_empty);
			txtRazonSocial.setError(parametros_empty);
			return false;
		} else {
			txtCodigo.setError(null);
			txtRuc.setError(null);
			txtDni.setError(null);
			txtRazonSocial.setError(null);
		}

		return true;
	}
    
    @Override
	protected void process() {
		// TODO Auto-generated method stub
		String codigo = txtCodigo.getText().toString();
		String ruc = txtRuc.getText().toString();
		String dni = txtDni.getText().toString();
		String razonSocial = txtRazonSocial.getText().toString();

		consultarClienteProxy.setCodigo(codigo);
		consultarClienteProxy.setRuc(ruc);
		consultarClienteProxy.setDni(dni);
		consultarClienteProxy.setRazonSocial(razonSocial);

		consultarClienteProxy.execute();
	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
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
    
    private int registros_abiertos=-1;
    
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_CONSULTAR){
			consultarEvaluacionesAbiertasProxy.codigoCliente = codigoCliente;
			consultarEvaluacionesAbiertasProxy.execute();
		}
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==ACCION_CONSULTAR){
			boolean isExito = consultarEvaluacionesAbiertasProxy.isExito();
			if (isExito) {
				int status = consultarEvaluacionesAbiertasProxy.getResponse().getStatus();
				if (status == 0) {
					registros_abiertos=consultarEvaluacionesAbiertasProxy.getResponse().EvaluacionesAbiertas;
					
					super.processOk();
					
					if(registros_abiertos<=0){
						Intent oportunidad = new Intent(this, ConsultarOportunidad_Activity.class);
						startActivity(oportunidad);
					}else{
						showToast("El cliente tiene una evaluaci˜n abierta.");
					}
					
				}else{
					super.processError();
				}
			}
		}
	}
	
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	MessageBox.showConfirmDialog(this, confirm_exit_title, confirm_exit_message, confirm_exit_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				finish();
//				int pid = android.os.Process.myPid(); 
//				android.os.Process.killProcess(pid);
//				onDestroy();
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
    
	public static class EfficientAdapter extends BaseAdapter implements
			Filterable {
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
				
				holder.txtFecha = (TextView) convertView
						.findViewById(R.id.txtFecha);
				holder.txtCodigo = (TextView) convertView
						.findViewById(R.id.txtCodigo);
				holder.txtNombre = (TextView) convertView
						.findViewById(R.id.txtNombre);
				holder.txtFrecuencia = (TextView) convertView
						.findViewById(R.id.txtFrecuencia);
				holder.txtAlcance = (TextView) convertView
						.findViewById(R.id.txtAlcance);
				holder.txtFalta = (TextView) convertView
						.findViewById(R.id.txtFalta);
				holder.txtCluster = (TextView) convertView
						.findViewById(R.id.txtCluster);
				holder.txtMC = (TextView) convertView
						.findViewById(R.id.txtMC);
				holder.txtNPuntos = (TextView) convertView
						.findViewById(R.id.txtNPuntos);
				holder.txtSigNivel = (TextView) convertView
						.findViewById(R.id.txtSigNivel);
				
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
			holder.txtFecha.setText(cliente.getFecha());
			holder.txtCodigo.setText(cliente.getCodigo());
			holder.txtNombre.setText(cliente.getNombre());
			holder.txtFrecuencia.setText(cliente.getFrecuencia());
			holder.txtAlcance.setText(cliente.getAlcance() + " %");
			if(cliente.getFalta().equals(""))cliente.setFalta("0");
			holder.txtFalta.setText(cliente.getFalta() + " CU");
			holder.txtCluster.setText(cliente.getCluster());
			holder.txtMC.setText(cliente.getMc());
			holder.txtNPuntos.setText(cliente.getNroPuntos());
			holder.txtSigNivel.setText(cliente.getNivelCanje());
			
			double lat = cliente.getLatitud();
			double lng = cliente.getLongitud();

			if (lat != 0 && lng != 0) {
				holder.imgDireccion.setVisibility(View.VISIBLE);
			}

			holder.imgDireccion.setOnClickListener(new OnClickListener() {
				ClienteTO clienteTemporal = (ClienteTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent direccionCliente = new Intent(context, lindley.desarrolloxcliente.DireccionActivity.class);
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.LATITUD_KEY,clienteTemporal.getLatitud());
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.LONGITUD_KEY,clienteTemporal.getLongitud());
					direccionCliente.putExtra(lindley.desarrolloxcliente.DireccionActivity.DIRECCION_KEY,clienteTemporal.getDireccion());
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
					context.processAsync(ACCION_CONSULTAR);
					
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
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
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
	
	
	/*
	public void takePhoto(){
    	file_name = String.format("%d.jpg", System.currentTimeMillis());
    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
    	startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

	 private File getTempFile(Context context){
		    
		   final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
		   
		    if(!path.exists()){
		    	path.mkdir();
		    }
		    
		    
		    return new File(path, file_name); 
		    }
	 
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	    		switch(requestCode){
	    			case TAKE_PHOTO_CODE:{
	    				savePhoto();
	    				//processAsync();
	    				break;
	    			}
	    		}

	    }
	  }
	 
	public void savePhoto(){
		//documentoTO.setNombreArchivo(file_name);
		//documentoTO.setEsLocal(DocumentoTO.LOCAL);
		//clienteBLL.guardarDocumento(clienteId, documentoTO);
	}*/

}