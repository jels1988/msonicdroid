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
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
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
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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

public class CompromisoPosicionOpenFalse_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	private static final int ACCION_CERRAR = 1;
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
        setContentView(R.layout.consultarposicioncompromisoopenfalse_activity);        
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		
        processAsync();         
    }
    
    @Override
   	protected void process() {
    	consultarPosicionProxy.setCodigoCliente(cliente.codigo);
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
				application.posicionFalseAdapter =new CompromisoPosicionOpenFalse_Activity.EfficientAdapter(this, posiciones); 
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
				setListAdapter(application.posicionFalseAdapter);
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
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	MessageBox.showConfirmDialog(this, confirm_cancelar_title, confirm_cancelar_message, confirm_cancelar_yes, new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//finish();
				
				Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
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
    	application.posicionFalseAdapter = null;
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
			this.posicionTO.fotoFinal = file_name;
			fotoBLL.save(file_name);
		}
		else
		{
			this.posicionTO.fotoFinal = file_name;
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
			super(context, R.layout.consultarposicioncompromisoopenfalse_content, posiciones);
			this.context=context;
			this.posiciones=posiciones;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		
			
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.consultarposicioncompromisoopenfalse_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.TextViewRpsta = (TextView) view.findViewById(R.id.TextViewRpsta);
				viewHolder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
				
				viewHolder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
				viewHolder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
				viewHolder.btnFotoFinal = (Button) view.findViewById(R.id.btnFotoFinal);
				
				viewHolder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
				viewHolder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);
				viewHolder.txViewAccComp = (TextView) view.findViewById(R.id.txViewAccComp);
				viewHolder.txViewFecComp = (TextView) view.findViewById(R.id.txViewFecComp);				
				viewHolder.chkCumplio = (CheckBox) view.findViewById(R.id.chkCumplio);
								
				viewHolder.txViewAccComp.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.txViewAccComp.getTag();
						compromiso.observacion = viewHolder.txViewAccComp.getText().toString();
					}
				});
				
				viewHolder.chkCumplio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.chkCumplio.getTag();
						if(isChecked){							
							compromiso.cumplio = "S";
						}else{
							compromiso.cumplio = "N";
						}
					}
				});
				
				view.setTag(viewHolder);
				viewHolder.txViewAccComp.setTag(this.posiciones.get(position));
				viewHolder.chkCumplio.setTag(this.posiciones.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewAccComp.setTag(this.posiciones.get(position));
				((ViewHolder) view.getTag()).chkCumplio.setTag(this.posiciones.get(position));
			}
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			final PosicionCompromisoTO posicionTO = posiciones.get(position);
			
			if(posicionTO.respuesta.equals("S"))
		    	  holder.TextViewRpsta.setText("SI");
		      else
		    	  holder.TextViewRpsta.setText("NO");
//			
			holder.txViewAccComp.setText(posicionTO.observacion);
			holder.txViewRed.setText(posicionTO.red);
			holder.txViewMaximo.setText(posicionTO.ptoMaximo);
			holder.txViewPuntos.setText(posicionTO.puntosSugeridos);
			if(posicionTO.codigoVariable.compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
			{
				holder.btnFotoExito.setText(R.string.btnExitoText);
			}
				   
			int mYear, mMonth, mDay;
			String fecha = posicionTO.fechaCompromiso;
			if (fecha.length() > 7) {
				mYear = Integer.parseInt(fecha.substring(0, 4));
				mMonth = Integer.parseInt(fecha.substring(4, 6));
				mDay = Integer.parseInt(fecha.substring(6));

				holder.txViewFecComp.setText(ActivityUtil.pad(mDay) + "/" + ActivityUtil.pad(mMonth) + "/"
						+ (mYear));
			} else {

				holder.txViewFecComp.setText("");
			}

			if(posicionTO.cumplio.equals("S"))
			{
				holder.chkCumplio.setChecked(true);
				posicionTO.cumplio = "S";
			}
			else
			{
				holder.chkCumplio.setChecked(false);
				posicionTO.cumplio = "N";
			}
							
			holder.btnFotoInicial.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!posicionTO.fotoInicial.equals(""))
						{
							Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
							intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.fotoInicial);
							intent.putExtra(WebViewVerFoto_Activity.TITULO_FOTO, "Foto Inicial Compromiso.");
							
							
							context.startActivity(intent);
						}
						else
						{
							MessageBox.showSimpleDialog(context, "Error", "No existe foto registrada.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
								}
							});	
						}
					
				}
			});
			
			holder.btnFotoFinal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if((posicionTO.fotoFinal==null)||(posicionTO.fotoFinal.compareTo("")==0)){
						((CompromisoPosicionOpenFalse_Activity)context).takePhoto(TAKE_PHOTO_FINAL_CODE, posicionTO);
					}else{
						
						/*
						Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
						intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.fotoFinal);
						context.startActivity(intent);	
						*/
						
						
						MessageBox.showConfirmDialog(context, "Confirmacion", "ÀDesea reemplazar la foto?", "Si",
								new android.content.DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub	
								/*Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
								intent.putExtra(VerFoto_Activity.FILE_NAME, posicionTO.fotoInicial);
								context.startActivity(intent);*/
								
								//((CompromisoPosicionOpen_Activity)context).takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionTO);
								((CompromisoPosicionOpenFalse_Activity)context).takePhoto(TAKE_PHOTO_FINAL_CODE, posicionTO);
							}
							
						}, "No", new android.content.DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								
								Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
								intent.putExtra(VerFoto_Activity.FILE_NAME, posicionTO.fotoFinal);
								context.startActivity(intent);
								
							}
							
						});  
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
							Intent intent = new Intent("lindley.desarrolloxcliente.vercompromisosclose");
							context.startActivity(intent);
						
					}
					else
					{
						Intent intent = new Intent("lindley.desarrolloxcliente.listarfotoexito");
						intent.putExtra(ListarFotoExito_Activity.ID_CLUSTER, cliente.cluster );
						context.startActivity(intent);
					}
				}
			});
			
			return view;
		}

	    static class ViewHolder {   
	    	TextView TextViewRpsta;
	    	TextView txViewPuntos;
	    	Button   btnFotoInicial;
	    	Button 	 btnFotoExito;
	    	Button 	 btnFotoFinal;	    	
	    	TextView txViewRed;
	    	TextView txViewMaximo;	 
	    	TextView txViewAccComp;	    	
	    	TextView txViewFecComp;  	
	    	CheckBox chkCumplio;
	    }
	    
	  }
    
}
