package lindley.desarrolloxcliente.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.to.UpdateSKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionCompromisoProxy;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.inject.Inject;

public class CompromisoPosicionOpenFalse_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
	@InjectExtra(COD_GESTION) String codigoGestion;
	
	public final static String FLAG_FECHA = "fecha_flag";
	@InjectExtra(FLAG_FECHA) static String flagFecha;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
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
	
	@InjectView(R.id.btnGuardar) Button btnGuardar;
	@InjectView(R.id.btnCerrar) Button btnCerrar;
	
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
        
        if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
	    {
        	btnGuardar.setVisibility(View.GONE);
	    }
        else
        {
//        	btnCerrar.setVisibility(View.GONE);
        	btnCerrar.setText(cancelar);
        }
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
				application.posicionAdapter =new CompromisoPosicionOpen_Activity.EfficientAdapter(this, posiciones); 
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
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
    
    public void btnCerrar_click(View view)
    {
//    	processAsync(ACCION_CERRAR);
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
				openAdapterVacio = true;
			}
			if(application.posicionAdapter == null || application.posicionAdapter.posiciones.isEmpty())
			{				
				application.posicionAdapter = new CompromisoPosicionOpen_Activity.EfficientAdapter(this, new ArrayList<PosicionCompromisoTO>());
				posicionAdapterVacio = true;
			}
			if(application.presentacionAdapter == null || application.presentacionAdapter.detalles.isEmpty())
			{
				application.presentacionAdapter = new CompromisoPresentacionOpen_Activity.EfficientAdapter(this, new ArrayList<PresentacionCompromisoTO>());
				presentacionAdapterVacio = true;
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
		List<UpdatePosicionTO> listUpdatePosicionTO = new ArrayList<UpdatePosicionTO>();
   		for(PosicionCompromisoTO posicion : application.posicionAdapter.posiciones)
		{
   			UpdatePosicionTO update = new UpdatePosicionTO();
   			update.accionCompromiso = posicion.getAccionCompromiso();
   			if(update.accionCompromiso.equalsIgnoreCase("")) update.accionCompromiso = " ";
   			update.codigoRegistro = codigoGestion; 
   			update.codigoVariable = posicion.getCodigoVariable();
   			update.confirmacion = posicion.getConfirmacion();
   			update.fechaCompromiso = posicion.getFechaCompromiso();
   			if(application.listCompromiso == null)
   				application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
      		update.listCompromisos = application.listCompromiso;
   			update.tipoAgrupacion = TIPO_POSICION;
   			update.fotoInicial = posicion.getFotoInicial();
   			update.fotoFinal = posicion.getFotoFinal();
   			listUpdatePosicionTO.add(update);
		}
   		
   		List<UpdatePresentacionTO> listUpdatePresentacionTO = new ArrayList<UpdatePresentacionTO>();
		for(PresentacionCompromisoTO presentacion : application.presentacionAdapter.detalles)
		{
			UpdatePresentacionTO update = new UpdatePresentacionTO();
			update.codigoRegistro = codigoGestion;
			update.tipoAgrupacion = TIPO_PRESENTACION;
			update.codigoVariable = presentacion.getCodigoVariable();
			update.confirmacion = presentacion.getConfirmacion();
			update.fechaCompromiso = presentacion.getFechaCompromiso();
			List<UpdateSKUPresentacionTO> skucompromisos = new ArrayList<UpdateSKUPresentacionTO>();
			for(SKUPresentacionCompromisoTO skupresentacionCompromisoTO :  presentacion.getListaSKU())
			{
				UpdateSKUPresentacionTO updateSKUPresentacionTO = new UpdateSKUPresentacionTO();
				updateSKUPresentacionTO.codigoSKU = skupresentacionCompromisoTO.getCodigoSKU();
				updateSKUPresentacionTO.compromiso = skupresentacionCompromisoTO.getCompromiso();
				if(updateSKUPresentacionTO.compromiso.equalsIgnoreCase(" ")) updateSKUPresentacionTO.compromiso = NO;
				updateSKUPresentacionTO.confirmacion = skupresentacionCompromisoTO.getConfirmacion();
				if(updateSKUPresentacionTO.confirmacion.equalsIgnoreCase(" ")) updateSKUPresentacionTO.confirmacion = NO;
				skucompromisos.add(updateSKUPresentacionTO);
			}
			update.listaSKU = skucompromisos;    			
			listUpdatePresentacionTO.add(update);
		}
				
       	if(accion == ACCION_CERRAR)
       	{       		
    		cerrarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
    		cerrarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
    		cerrarCompromisoProxy.setCompromisos(application.openAdapter.detalles);
       		cerrarCompromisoProxy.setCodigoCabecera(codigoGestion);
       		UsuarioTO user = application.getUsuarioTO();
    		cerrarCompromisoProxy.codigoUsuario = user.getCodigoSap();
       		cerrarCompromisoProxy.execute();
       	}
       	else if(accion == ACCION_ACTUALIZAR)
       	{    		
    		actualizarCompromisoProxy.listaPosicionCompromiso = listUpdatePosicionTO;
    		actualizarCompromisoProxy.listaPresentacionCompromiso = listUpdatePresentacionTO;
       		actualizarCompromisoProxy.setCompromisos(application.openAdapter.detalles);
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
			this.posicionTO.setFotoInicial(file_name);
			fotoBLL.save(file_name);
		}
		else
		{
			this.posicionTO.setFotoFinal(file_name);
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

					EfficientAdapter.txEditFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
		    	  if(EfficientAdapter.compromisoTO!=null){
		    		  EfficientAdapter.compromisoTO.setFechaCompromiso(String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth)) );
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
				
//				viewHolder.txViewVariable = (TextView) view.findViewById(R.id.txViewVariable);
				viewHolder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
				viewHolder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);
//				viewHolder.txViewDiferencia = (TextView) view.findViewById(R.id.txViewDiferencia);
								
				
				
				viewHolder.btnFotoFinal = (Button) view.findViewById(R.id.btnFotoFinal);
				viewHolder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
				viewHolder.txViewFecha = (TextView) view.findViewById(R.id.txViewFecha);
				
				viewHolder.txViewAccComp = (EditText) view.findViewById(R.id.txViewAccComp);
				viewHolder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);
				
				viewHolder.chkCumplio = (CheckBox) view.findViewById(R.id.chkCumplio);
						    	
				viewHolder.txViewAccComp.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.txViewAccComp.getTag();
						compromiso.setAccionCompromiso(viewHolder.txViewAccComp.getText().toString());
					}
				});
				
				viewHolder.chkCumplio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						PosicionCompromisoTO compromiso = (PosicionCompromisoTO) viewHolder.chkCumplio.getTag();
						if(isChecked){							
							compromiso.setConfirmacion("S");
						}else{
							compromiso.setConfirmacion("N");
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
//			holder.txViewVariable.setText(posicionTO.getDescripcionVariable());
			holder.txViewAccComp.setText(posicionTO.getAccionCompromiso());
			holder.txViewRed.setText(posicionTO.getRed());
			holder.txViewMaximo.setText(posicionTO.getPtoMaximo());
//			holder.txViewDiferencia.setText(posicionTO.getDiferencia());
			holder.txViewPuntos.setText(posicionTO.getPuntosSugeridos());
			if(posicionTO.getCodigoVariable().compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
			{
				holder.btnFotoExito.setText(R.string.btnExitoText);
			}
				    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

	    		@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = posicionTO.getFechaCompromiso();
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
	    	
			int mYear, mMonth, mDay;
			String fecha = posicionTO.getFechaCompromiso();
			if (fecha.length() > 7) {
				mYear = Integer.parseInt(fecha.substring(0, 4));
				mMonth = Integer.parseInt(fecha.substring(4, 6));
				mDay = Integer.parseInt(fecha.substring(6));

				holder.txViewFecha.setText(pad(mDay) + "/" + pad(mMonth) + "/"
						+ pad(mYear));
			} else {

				holder.txViewFecha.setText("");
			}

			if(posicionTO.getConfirmacion().equals("S"))
				holder.chkCumplio.setChecked(true);
			else
				holder.chkCumplio.setChecked(false);				
				
			if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
		      {
		    	  holder.txEditFecha.setVisibility(View.GONE);	    	  
		    	  holder.btnFecha.setVisibility(View.GONE);
		    	  holder.txViewFecha.setVisibility(View.VISIBLE);
		    	  holder.btnFotoFinal.setEnabled(true);
		    	  holder.txViewAccComp.setEnabled(false);
		      }
		      else
		      {
		    	  holder.txEditFecha.setVisibility(View.VISIBLE);	    	  
		    	  holder.btnFecha.setVisibility(View.VISIBLE);
		    	  holder.txViewFecha.setVisibility(View.GONE);		
		    	  holder.btnFotoFinal.setEnabled(false);
		      }
			
			holder.btnFotoInicial.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
					{
						if(!posicionTO.getFotoInicial().equals(""))
						{
							Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
							intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.getFotoInicial());
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
					else
					{
						
						if((posicionTO.getFotoInicial()==null)||(posicionTO.getFotoInicial().compareTo("")==0)){
							
							((CompromisoPosicionOpenFalse_Activity)context).takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionTO);
						}else{
							Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
							intent.putExtra(VerFoto_Activity.FILE_NAME, posicionTO.getFotoInicial());
							context.startActivity(intent);	
						}
						
					}
				}
			});
			
			holder.btnFotoFinal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if((posicionTO.getFotoFinal()==null)||(posicionTO.getFotoFinal().compareTo("")==0)){
						
						((CompromisoPosicionOpenFalse_Activity)context).takePhoto(TAKE_PHOTO_FINAL_CODE, posicionTO);
					}else{
						Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
						intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.getFotoFinal());
						context.startActivity(intent);	
					}
				}
			});
			
			holder.btnFotoExito.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(posicionTO.getCodigoVariable().compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
					{
						if(flagFecha.equals(FLAG_OPEN_FECHA_CERRADA))
						{
							application.listCompromiso = posicionTO.getListCompromisos();
							if(application.listCompromiso == null)
								application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
							Intent intent = new Intent("lindley.desarrolloxcliente.vercompromisosclose");
							context.startActivity(intent);
						}
						else
						{
							application.listCompromiso = posicionTO.getListCompromisos();
							if(application.listCompromiso == null)
								application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
							Intent intent = new Intent("lindley.desarrolloxcliente.vercompromisosopen");
							context.startActivity(intent);
						}
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
//	    	TextView txViewVariable;
	    	TextView txViewRed;
	    	TextView txViewMaximo;
//	    	TextView txViewDiferencia;
	    	TextView txViewPuntos;  	
	    	Button   btnFotoInicial;
	    	Button 	 btnFotoExito;
	    	EditText txViewAccComp;
	    	EditText txEditFecha;
	    	TextView txViewFecha;
	    	ImageButton 	 btnFecha;
	    	Button 	 btnFotoFinal;
	    	CheckBox chkCumplio;
	    }
	    
	  }
    
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

    
}
