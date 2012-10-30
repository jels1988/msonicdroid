package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.to.CerrarSKUPresentacionTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarPresentacionCompromisoProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoPresentacionOpenFalse_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
		
	private static final int ACCION_CERRAR = 1;
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";

	@Inject	ConsultarPresentacionCompromisoProxy consultarPresentacionProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@InjectView(R.id.txtViewFecha)	TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	ClienteTO cliente;
	public static MyApplication application;
	
	@InjectResource(R.string.btn_cancelar) 				String cancelar;	
	@InjectResource(R.string.confirm_cancelar_title) 	String confirm_cancelar_title;
	@InjectResource(R.string.confirm_cancelar_message)  String confirm_cancelar_message;
	@InjectResource(R.string.confirm_cancelar_yes) 		String confirm_cancelar_yes;
	@InjectResource(R.string.confirm_cancelar_no) 		String confirm_cancelar_no;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultarpresentacioncompromisoopenfalse_activity);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		processAsync();
		
	}

	@Override
	protected void process()  throws Exception{
		consultarPresentacionProxy.setCodigoCliente(cliente.codigo);
		consultarPresentacionProxy.setCodigoRegistro(codigoGestion);
		consultarPresentacionProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPresentacionProxy.isExito();
		if (isExito) {
			int status = consultarPresentacionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PresentacionCompromisoTO> presentaciones = consultarPresentacionProxy
						.getResponse().getListaCompromisos();
				
				application.presentacionFalseAdapter = new CompromisoPresentacionOpenFalse_Activity.EfficientAdapter(this, presentaciones);
				final Calendar c = Calendar.getInstance();
				if (presentaciones.size() > 0)
					txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(application.presentacionFalseAdapter);
			} else {
				showToast(consultarPresentacionProxy.getResponse()
						.getDescripcion());
			}
		} else {
			processError();
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				
				
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcabecera");
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
			}
			
		}, confirm_cancelar_no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
			
		}); 
	}
	
	public void btnCancelar_click(View view)
    {
		onBackPressed();
    }

    public void btnCerrar_click(View view)
    {
    	processAsync(ACCION_CERRAR);
    }

    @Override
    protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean openAdapterVacio = false;
		boolean posicionAdapterVacio = false;
		boolean presentacionAdapterVacio = false;
	
		if(accion == ACCION_CERRAR)
       	{    
			if(application.openFalseAdapter == null || application.openFalseAdapter.detalles.isEmpty())
			{				
				application.openFalseAdapter = new CompromisoOpenFalse_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
				openAdapterVacio = true;
				if(openAdapterVacio)
				{
					showToast("Debe editar valores de la pestana inventario.");
					return false;
				}
			}
			if(application.posicionFalseAdapter == null || application.posicionFalseAdapter.posiciones.isEmpty())
			{				
				application.posicionFalseAdapter = new CompromisoPosicionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
				if(posicionAdapterVacio)
				{
					showToast("Debe editar valores de la pestana posicion.");
					return false;
				}
			}
			if(application.presentacionFalseAdapter == null || application.presentacionFalseAdapter.detalles.isEmpty())
			{
				application.presentacionFalseAdapter = new CompromisoPresentacionOpenFalse_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
				if(presentacionAdapterVacio)
				{
					showToast("Debe editar valores de la pestana presentacion.");
					return false;
				}
			}
				
       	}
		
		if(openAdapterVacio && posicionAdapterVacio && presentacionAdapterVacio)
		{			
			showToast("Debe editar los datos.");
			return false;
		}
		else
		{
			return true;
		}
	}
    
    @Override
    protected void process(int accion)  throws Exception{
    	
    	if(accion == ACCION_CERRAR)
    	{    		
    		List<CerrarInventarioTO> listCerrarCompromisoTO = new ArrayList<CerrarInventarioTO>();
       		for(CompromisoTO compromiso : application.openFalseAdapter.detalles)
    		{
       			CerrarInventarioTO cerrar = new CerrarInventarioTO();
       			cerrar.codigoProducto = compromiso.codigoProducto;
       			cerrar.concrecionCumplio = compromiso.concrecionCumplio;
       			cerrar.soviCumplio = compromiso.soviCumplio;
       			cerrar.cumplePrecioCumplio = compromiso.cumplePrecioCumplio;
       			cerrar.numeroSaboresCumplio = compromiso.numeroSaboresCumplio;
       			
       			listCerrarCompromisoTO.add(cerrar);
    		}
       		
       		List<CerrarPosicionTO> listCerrarPosicionTO = new ArrayList<CerrarPosicionTO>();
       		for(PosicionCompromisoTO posicion : application.posicionFalseAdapter.posiciones)
    		{
       			CerrarPosicionTO cerrar = new CerrarPosicionTO();
       			cerrar.codigoVariable = posicion.codigoVariable;
       			cerrar.cumplio = posicion.cumplio;
       			cerrar.fotoFinal = posicion.fotoFinal;
       			listCerrarPosicionTO.add(cerrar);
    		}
       		
       		List<CerrarPresentacionTO> listCerrarPresentacionTO = new ArrayList<CerrarPresentacionTO>();
    		for(PresentacionCompromisoTO presentacion : application.presentacionFalseAdapter.detalles)
    		{
    			CerrarPresentacionTO cerrar = new CerrarPresentacionTO();
    			cerrar.codigoVariable = presentacion.codigoVariable;
    			cerrar.cumplio = presentacion.cumplio;
    			
    			List<CerrarSKUPresentacionTO> listCerrarSKUPresentacionTO = new ArrayList<CerrarSKUPresentacionTO>();
    			for(SKUPresentacionCompromisoTO sku : presentacion.listaSKU)
    			{
    				CerrarSKUPresentacionTO cerrarSku = new CerrarSKUPresentacionTO();
    				cerrarSku.codigoSKU = sku.codigoSKU;
    				cerrarSku.cumplio = sku.cumplio;
    				listCerrarSKUPresentacionTO.add(cerrarSku);
    			}
    			cerrar.listaSKU = listCerrarSKUPresentacionTO;
    			
    			listCerrarPresentacionTO.add(cerrar);
    		}
    		
    		cerrarCompromisoProxy.listaPosicionCompromiso = listCerrarPosicionTO;
    		cerrarCompromisoProxy.listaPresentacionCompromiso = listCerrarPresentacionTO;
    		cerrarCompromisoProxy.listaInventarioCompromiso = listCerrarCompromisoTO;
    		cerrarCompromisoProxy.codigoCabecera = codigoGestion;
    		UsuarioTO user = application.getUsuarioTO();
    		cerrarCompromisoProxy.codigoUsuario = user.getCodigoSap();
    		cerrarCompromisoProxy.execute();
    	}
    		
	}

	protected void processOk(int accion) {
   		// TODO Auto-generated method stub
    	if(accion == ACCION_CERRAR)
    	{
    		boolean isExito = cerrarCompromisoProxy.isExito();
       		if (isExito) {
       			int status = cerrarCompromisoProxy.getResponse().getStatus();
       			if (status == 0) {
       				setAdapterApplication();
       				
       				Intent intentService = new Intent("lindley.desarrolloxcliente.uploadFileService");
	       			startService(intentService);


       				
       				showToast("Los registros se cerraron satisfactoriamente.");
       				
       				Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");
       				cabecera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(cabecera);
					
					finish();
       			}
       			else  {
       				showToast(cerrarCompromisoProxy.getResponse().getDescripcion());
       			}
       		}
       		else{
       			processError();
       		}
    	}
   		super.processOk();
   	} 
    
	private void setAdapterApplication() {
		// TODO Auto-generated method stub
    	application.openAdapter = null;
    	application.posicionAdapter = null;
    	application.presentacionFalseAdapter = null;
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	public static class EfficientAdapter extends ArrayAdapter<PresentacionCompromisoTO>{
				
		public static EditText txEditFecha;
		public static PresentacionCompromisoTO compromisoTO;

		public EfficientAdapter(Activity context, List<PresentacionCompromisoTO> valores) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			super(context, R.layout.consultarpresentacioncompromisoopen_content, valores);
			this.context = context;
			this.detalles = valores;
		}
		
		DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				// TODO Auto-generated method stub

					EfficientAdapter.txEditFecha.setText(String.valueOf(ActivityUtil.pad(dayOfMonth)) + "/"+ String.valueOf(ActivityUtil.pad(monthOfYear+1)) + "/" + String.valueOf(year));
		    	  if(EfficientAdapter.compromisoTO!=null){
		    		  EfficientAdapter.compromisoTO.fechaCompromiso = (String.valueOf(year) + String.valueOf(ActivityUtil.pad(monthOfYear+1)) + String.valueOf(ActivityUtil.pad(dayOfMonth)) );
		    	  }
			}
		};
		
		private Activity context;
		public List<PresentacionCompromisoTO> detalles;

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		public View getView( int position, View convertView,
				ViewGroup parent) {
			//final PresentacionCompromisoTO presentacionTO = (PresentacionCompromisoTO) getItem(position);
			//final ViewHolder holder;

			View view = null;
			if (convertView == null) {
				
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultarpresentacioncompromisoopenfalse_content, null);
				final ViewHolder viewHolder = new ViewHolder();

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.

				viewHolder.txViewPuntos = (TextView) view
						.findViewById(R.id.txViewPuntos);
				
				viewHolder.btnSKU = (Button) view.findViewById(R.id.btnSKU);

				viewHolder.txViewFecComp = (TextView) view
						.findViewById(R.id.txViewFecComp);

				viewHolder.chkCnfComp = (CheckBox) view
						.findViewById(R.id.chkCnfComp);

				
				viewHolder.chkCnfComp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						PresentacionCompromisoTO compromiso = (PresentacionCompromisoTO) viewHolder.chkCnfComp.getTag();
						if(isChecked){
							compromiso.cumplio = "S";
						}else{
							compromiso.cumplio = "N";
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.chkCnfComp.setTag(this.detalles.get(position));
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				view = convertView;				
				((ViewHolder) view.getTag()).chkCnfComp.setTag(this.detalles.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final PresentacionCompromisoTO presentacionTO = detalles.get(position);
			
//			holder.txViewVariable.setText(presentacionTO
//					.getDescripcionVariable());
			holder.txViewPuntos.setText(presentacionTO.puntosSugeridos);
			
			int mYear, mMonth, mDay;
			String fecha = presentacionTO.fechaCompromiso;
			if (fecha.length() > 7) {
				mYear = Integer.parseInt(fecha.substring(0, 4));
				mMonth = Integer.parseInt(fecha.substring(4, 6));
				mDay = Integer.parseInt(fecha.substring(6));

				holder.txViewFecComp.setText(ActivityUtil.pad(mDay) + "/" + ActivityUtil.pad(mMonth) + "/"
						+ ActivityUtil.pad(mYear));
			} else {
				holder.txViewFecComp.setText("");
			}

			if (presentacionTO.cumplio.equals("S"))
			{
				holder.chkCnfComp.setChecked(true);
				presentacionTO.cumplio = "S";
			}
			else
			{
				holder.chkCnfComp.setChecked(false);
				presentacionTO.cumplio = "N";
			}

		
			 holder.btnSKU.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MyApplication application = (MyApplication) context.getApplicationContext();
						application.listSKUPresentacionCompromiso = presentacionTO.listaSKU;
						Intent skuPresentacion = new Intent(context, SKUPrioritarioCompromisoFalse_Activity.class);
						context.startActivity(skuPresentacion);
					}
				});
			 
			return view;
		}

		static class ViewHolder {
			TextView txViewPuntos;
			Button btnSKU;
			TextView txViewFecComp;
			CheckBox chkCnfComp;
		}

	}

}
