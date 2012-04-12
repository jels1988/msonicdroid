package lindley.desarrolloxcliente.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.to.CerrarSKUPresentacionTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdateInformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UpdateInventarioTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionCompromisoProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import net.msonic.lib.UploadFileUtil;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

public class CompromisoPosicionOpen_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
		
	private static final int ACCION_CERRAR = 1;
	private static final int ACCION_ACTUALIZAR = 2;
	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
	public static final String ESTANDAR_ANAQUEL = "03";
	
	@Inject ConsultarPosicionCompromisoProxy consultarPosicionProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@Inject FotoBLL fotoBLL;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	public static ClienteTO cliente;
	public static MyApplication application;
		
	@InjectResource(R.string.btn_cancelar) 				String cancelar;	
	@InjectResource(R.string.confirm_cancelar_title) 	String confirm_cancelar_title;
	@InjectResource(R.string.confirm_cancelar_message)  String confirm_cancelar_message;
	@InjectResource(R.string.confirm_cancelar_yes) 		String confirm_cancelar_yes;
	@InjectResource(R.string.confirm_cancelar_no) 		String confirm_cancelar_no;
	
	public static String file_name="";
	private static final int TAKE_PHOTO_INICIAL_CODE = 1;
	private static final int TAKE_PHOTO_FINAL_CODE = 2;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
    	super.onCreate(savedInstanceState);    	 
        setContentView(R.layout.consultarposicioncompromisoopen_activity);        
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
        processAsync(); 
        
    }
    
    @Override
   	protected void process() {
    	consultarPosicionProxy.setCodigoCliente(cliente.getCodigo());
    	consultarPosicionProxy.setCodigoGestion(codigoGestion);    	
    	consultarPosicionProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPosicionProxy.isExito();
		if (isExito) {
			int status = consultarPosicionProxy.getResponse().getStatus();
			if (status == 0) {
				List<PosicionCompromisoTO> posiciones = consultarPosicionProxy
						.getResponse().getListaCompromisos();	
				application.posicionAdapter =new EfficientAdapter(this, posiciones);
				final Calendar c = Calendar.getInstance();
				if(posiciones.size()>0)
				{
					if(application.dia == null)
						txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
					else
						txtViewFecha.setText(application.dia+ "/" + application.mes + "/" + application.anio);
				}
				setListAdapter(application.posicionAdapter);
			}
			else  {
				showToast(consultarPosicionProxy.getResponse().getDescripcion());
			}
		}
		else{
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
    
    public void btnCancelar_click(View view)
    {
    	MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
				startActivity(intent);
			}
			
		}, confirm_cancelar_no, new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
			
		});  
    }

    public void btnGuardar_click(View view)
    {
    	processAsync(ACCION_ACTUALIZAR);
    }
	
    @Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		boolean openAdapterVacio = false;
		boolean posicionAdapterVacio = false;
		boolean presentacionAdapterVacio = false;
		if(accion == ACCION_ACTUALIZAR || accion == ACCION_CERRAR)
       	{    
			if(application.openAdapter == null || application.openAdapter.detalles.isEmpty())
			{				
				application.openAdapter = new CompromisoOpen_Activity.EfficientAdapter(this, new ArrayList<CompromisoTO>());
				for(CompromisoTO comp : application.openAdapter.detalles)
				{
					if(Integer.parseInt(comp.sovi)<=0 || Integer.parseInt(comp.soviActual)<=0)
					{
						showToast("Los valores de SOVI deben ser mayores a 0");
						return false;
					}
				}
				openAdapterVacio = true;
				if(openAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña inventario.");
					return false;
				}
			}
			if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
			{				
				application.posicionAdapter = new EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
				if(posicionAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña posición.");
					return false;
				}
			}
			if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
			{
				application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
				if(presentacionAdapterVacio)
				{
					showToast("Debe editar valores de la pestaña presentación.");
					return false;
				}
			}
			if(application.informacionAdicional == null)
			{
				showToast("Debe editar valores de la pestaña combos.");
				return false;
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
   	protected void process(int accion) {
    			
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
       		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
    		{
       			CerrarPosicionTO cerrar = new CerrarPosicionTO();
       			cerrar.codigoVariable = posicion.codigoVariable;
       			cerrar.cumplio = posicion.cumplio;
       			listCerrarPosicionTO.add(cerrar);
    		}
       		
       		List<CerrarPresentacionTO> listCerrarPresentacionTO = new ArrayList<CerrarPresentacionTO>();
    		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
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
    	else if(accion == ACCION_ACTUALIZAR)
    	{    		
    		
    		List<UpdateInventarioTO> listUpdateCompromisoTO = new ArrayList<UpdateInventarioTO>();
       		for(CompromisoTO compromiso : application.openAdapter.detalles)
    		{
       			UpdateInventarioTO update = new UpdateInventarioTO();
       			update.codigoProducto = compromiso.codigoProducto;
       			update.concrecionActual = compromiso.concrecionActual;
       			update.sovi = compromiso.sovi;
       			update.soviActual = compromiso.soviActual;
       			update.cumplePrecio = compromiso.cumplePrecio;
       			update.cumplePrecioActual = compromiso.cumplePrecioActual;
       			update.numeroSaboresActual = compromiso.numeroSaboresActual;
       			update.fechaCompromiso = compromiso.fechaCompromiso;
       			update.codigoAccionTrade = compromiso.codigoAccionTrade;
       			update.descAccionTrade = compromiso.descAccionTrade;
       			listUpdateCompromisoTO.add(update);
    		}
       		
       		List<UpdatePosicionTO> listUpdatePosicionTO = new ArrayList<UpdatePosicionTO>();
       		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
    		{
       			UpdatePosicionTO update = new UpdatePosicionTO();
       			update.accionCompromiso = posicion.observacion;
       			update.codigoVariable = posicion.codigoVariable;
       			update.fechaCompromiso = posicion.fechaCompromiso;
       			if(application.listCompromiso == null)
       			 application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
       			update.listCompromisos = application.listCompromiso;
       			update.fotoInicial = posicion.fotoInicial;
       			listUpdatePosicionTO.add(update);
    		}
       		
       		List<UpdatePresentacionTO> listUpdatePresentacionTO = new ArrayList<UpdatePresentacionTO>();
    		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
    		{
    			UpdatePresentacionTO update = new UpdatePresentacionTO();
    			update.codigoVariable = presentacion.codigoVariable;
    			update.fechaCompromiso = presentacion.fechaCompromiso;
    			
    			List<UpdateSKUPresentacionTO> skucompromisos = new ArrayList<UpdateSKUPresentacionTO>();
    			for(SKUPresentacionCompromisoTO skupresentacionCompromisoTO :  presentacion.listaSKU)
    			{
    				UpdateSKUPresentacionTO updateSKUPresentacionTO = new UpdateSKUPresentacionTO();
    				updateSKUPresentacionTO.codigoSKU = skupresentacionCompromisoTO.codigoSKU;
    				    				
    				updateSKUPresentacionTO.compromiso = skupresentacionCompromisoTO.compromiso;
    				if(updateSKUPresentacionTO.compromiso.equalsIgnoreCase(" ")) updateSKUPresentacionTO.compromiso = NO;
    				
    				skucompromisos.add(updateSKUPresentacionTO);
    			}
    			update.listaSKU = skucompromisos;    			
    			listUpdatePresentacionTO.add(update);
    		}
    		
    		InformacionAdicionalTO informacion = application.informacionAdicional;
    		UpdateInformacionAdicionalTO update = new UpdateInformacionAdicionalTO();
    		update.comboMS = informacion.getComboMS();
    		update.comboSS = informacion.getComboSS();
    		update.observacion = informacion.getObservacion();
    		update.observacionSS = informacion.getObservacionSS();
    		
    		actualizarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
    		actualizarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
       		actualizarCompromisoProxy.listaInventarioCompromiso = listUpdateCompromisoTO;
       		actualizarCompromisoProxy.updateInformacionAdicionalTO = update;
       		actualizarCompromisoProxy.codigoCabecera = codigoGestion;
       		actualizarCompromisoProxy.execute();
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
       				showToast("Los registros se cerrarón satisfactoriamente.");
       				Intent cabecera = new Intent("lindley.desarrolloxcliente.consultarcabecera");					
					startActivity(cabecera);
       			}
       			else  {
       				showToast(cerrarCompromisoProxy.getResponse().getDescripcion());
       			}
       		}
       		else{
       			processError();
       		}
    	}
    	else if(accion == ACCION_ACTUALIZAR)
    	{
    		boolean isExito = actualizarCompromisoProxy.isExito();
       		if (isExito) {
       			int status = actualizarCompromisoProxy.getResponse().getStatus();
       			if (status == 0) {
       				setAdapterApplication();
       				showToast("Los registros se actualizarón correctamente.");
       				
       				Intent intentService = new Intent("lindley.desarrolloxcliente.uploadFileService");
       				startService(intentService);
       				
       				Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.consultarcabecera");
					startActivity(compromisoOpen);
       			}
       			else  {
       				showToast(actualizarCompromisoProxy.getResponse().getDescripcion());
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
    	application.presentacionAdapter = null;
	}
    
    @Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
     PosicionCompromisoTO posicionTO;
    
    public void takePhoto(int accion,PosicionCompromisoTO posicionTO ){
    	
    	this.posicionTO = posicionTO;
    	file_name = UploadFileUtil.GenerarFileName(12,"jpg");
    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
    	startActivityForResult(intent, accion);
    	
    	
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
	    			case TAKE_PHOTO_INICIAL_CODE:{
	    				savePhoto(TAKE_PHOTO_INICIAL_CODE);
	    				break;
	    			}
	    			case TAKE_PHOTO_FINAL_CODE:{
	    				savePhoto(TAKE_PHOTO_FINAL_CODE);
	    				break;
	    			}
	    		}

	    }
	  }
	 
	public void savePhoto(int accion){
		if(TAKE_PHOTO_INICIAL_CODE==accion)
		{
			this.posicionTO.fotoInicial = file_name;
			fotoBLL.save(file_name);
		}
		else
		{
			this.posicionTO.fotoInicial = file_name;
			fotoBLL.save(file_name);
		}
	}
	
    public static class EfficientAdapter extends ArrayAdapter<PosicionCompromisoTO> {
    	
    	public static EditText txEditFecha;
		public static PosicionCompromisoTO compromisoTO;

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
		public List<PosicionCompromisoTO> posiciones;
		
		public EfficientAdapter(Activity context,List<PosicionCompromisoTO> posiciones ){
			super(context, R.layout.consultarposicioncompromisoopen_content, posiciones);
			this.context=context;
			this.posiciones=posiciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultarposicioncompromisoopen_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.TextViewRpsta = (TextView) view.findViewById(R.id.TextViewRpsta);
				viewHolder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
				
				viewHolder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
				viewHolder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
				
				viewHolder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
				viewHolder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);				
				
				viewHolder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
				
				viewHolder.txViewAccComp = (EditText) view.findViewById(R.id.txViewAccComp);
				viewHolder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);
				
				viewHolder.txViewAccComp.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.txViewAccComp.getTag();
						compromiso.observacion = viewHolder.txViewAccComp.getText().toString();
					}
				});
				
				
				view.setTag(viewHolder);
				viewHolder.txViewAccComp.setTag(this.posiciones.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewAccComp.setTag(this.posiciones.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final PosicionCompromisoTO posicionTO = posiciones.get(position);
			
			if(posicionTO.respuesta.equals("S"))
		    	  holder.TextViewRpsta.setText("SI");
		      else
		    	  holder.TextViewRpsta.setText("NO");
			holder.txViewAccComp.setText(posicionTO.observacion);
			holder.txViewRed.setText(posicionTO.red);
			holder.txViewMaximo.setText(posicionTO.ptoMaximo);
//			holder.txViewDiferencia.setText(posicionTO.getDiferencia());
			holder.txViewPuntos.setText(posicionTO.puntosSugeridos);
			if(posicionTO.codigoVariable.compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
			{
				holder.btnFotoExito.setText(R.string.btnExitoText);
			}
				    	
			String fecha = posicionTO.fechaCompromiso;
			int anio;
			int mes;
			int dia;
			final Calendar c = Calendar.getInstance();
			if (fecha.length() >= 7) {
				anio = Integer.parseInt(fecha.substring(0, 4));
				mes = Integer.parseInt(fecha.substring(4, 6)) - 1;
				dia = Integer.parseInt(fecha.substring(6));
				c.set(anio, mes, dia);
				if (dia >= 30 && mes == 1)
					dia = c.get(Calendar.DAY_OF_MONTH);
				else if (dia >= 30)
					dia = c.get(Calendar.DAY_OF_MONTH);
				holder.txEditFecha.setText(ActivityUtil.pad(dia)+"/"+ActivityUtil.pad(mes+1)+"/"+anio);
			}
			
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

	    		@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = posicionTO.fechaCompromiso;
					   final Calendar c = Calendar.getInstance();
					   if(fecha.length() >= 7)
					      {
					    	  anio =  Integer.parseInt(fecha.substring(0, 4));
					    	  mes  =  Integer.parseInt(fecha.substring(4, 6))-1;
					    	  dia  =  Integer.parseInt(fecha.substring(6));
					    	  c.set(anio, mes, dia);					    	  
					    	  if (dia>=30 && mes == 1)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  else if (dia>=30)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  
					      }else{					    	          
					    	  anio = c.get(Calendar.YEAR);        
					    	  mes = c.get(Calendar.MONTH);        
					    	  dia = c.get(Calendar.DAY_OF_MONTH); 
					      }
					      
					      
					      EfficientAdapter.txEditFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = posicionTO;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes--, dia);
					      p.show();
				}
			});
	    	
			holder.btnFotoInicial.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if((posicionTO.fotoInicial==null)||(posicionTO.fotoInicial.compareTo("")==0)){
						((CompromisoPosicionOpen_Activity)context).takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionTO);
					}else{
						Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
						intent.putExtra(VerFoto_Activity.FILE_NAME, posicionTO.fotoInicial);
						context.startActivity(intent);
				    }						
			     }
			});
			
			
			holder.btnFotoExito.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(posicionTO.codigoVariable.compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
					{
						application.listCompromiso = posicionTO.listCompromisos;
						if(application.listCompromiso == null)
							application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
						Intent intent = new Intent("lindley.desarrolloxcliente.vercompromisosopen");
						context.startActivity(intent);
					}
					else
					{
						Intent intent = new Intent("lindley.desarrolloxcliente.listarfotoexito");
						intent.putExtra(ListarFotoExito_Activity.ID_CLUSTER, cliente.getCluster() );
						context.startActivity(intent);
					}
				}
			});
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView TextViewRpsta;
	    	TextView txViewRed;
	    	TextView txViewMaximo;
	    	TextView txViewPuntos;  	
	    	Button   btnFotoInicial;
	    	Button 	 btnFotoExito;
	    	EditText txViewAccComp;
	    	EditText txEditFecha;
	    	ImageButton 	 btnFecha;
	    }
	    
	  }
    
}
