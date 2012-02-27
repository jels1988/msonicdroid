package pe.lindley.prospector.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.adapter.TablaTOAdapter;
import pe.lindley.lanzador.adapter.UbigeoTOAdapter;
import pe.lindley.lanzador.negocio.TablaBLL;
import pe.lindley.lanzador.to.TablaTO;
import pe.lindley.lanzador.to.UbigeoTO;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.ws.service.DatosClienteProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import pe.lindley.util.StringHelper;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class RegistrarClienteActivity extends ActivityBase {
	
	public static final String CLIENTE_CODIGO_KEY="CLIENTE_CODIGO";
	public static final String CLIENTE_ID_KEY="CLIENTE_ID";
	public static final String CLIENTE_REFERENCIA_ID_KEY="CLIENTE_REF_ID";
	public static final String CLIENTE_REFERENCIA_RAZONSOCIAL_KEY="CLIENTE_REF_RAZONSOCIAL";
	
	public static final String CODIGO_CLIENTE_ACCION="ACCION";
	public static final int CLIENTE_CARGAR_FROM_WS=0;
	public static final int CLIENTE_CARGAR_FROM_BD=1;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.txtRazonSocial) 	TextView 	txtRazonSocial;
	@InjectView(R.id.txtRuc) 			TextView 	txtRuc;
	@InjectView(R.id.txtDni) 			TextView 	txtDni;
	@InjectView(R.id.txtRepresentante) 	TextView 	txtRepresentante;	
	@InjectView(R.id.txtTelefono1) 		TextView 	txtTelefono1;
	@InjectView(R.id.txtTelefono2) 		TextView 	txtTelefono2;
	@InjectView(R.id.txtFax) 			TextView 	txtFax;
	@InjectView(R.id.txtEmail) 			TextView 	txtEmail;
	@InjectView(R.id.txtUrl) 			TextView 	txtUrl;
	@InjectView(R.id.txtVendedor) 		TextView 	txtVendedor;
	@InjectView(R.id.cboTamanio) 		Spinner 	cboTamanio;
	@InjectView(R.id.cboSubCanal) 		Spinner 	cboSubCanal;
	@InjectView(R.id.flpRegistrarCliente) 	 	ViewFlipper	viewFlipper;
	
	
	@InjectView(R.id.txtDireccionEntrega) 		TextView 	txtDireccionEntrega;
	@InjectView(R.id.cboDepartamentoEntrega) 	Spinner 	cboDepartamentoEntrega;
	@InjectView(R.id.cboProvinciaEntrega) 		Spinner 	cboProvinciaEntrega;
	@InjectView(R.id.cboDistritoEntrega) 		Spinner 	cboDistritoEntrega;
	@InjectView(R.id.txtDireccionFiscal) 		TextView 	txtDireccionFiscal;
	@InjectView(R.id.cboDepartamentoFiscal) 	Spinner 	cboDepartamentoFiscal;
	@InjectView(R.id.cboProvinciaFiscal) 		Spinner 	cboProvinciaFiscal;
	@InjectView(R.id.cboDistritoFiscal) 		Spinner 	cboDistritoFiscal;
	
	@InjectView(R.id.cboDistribuidor) 			Spinner 	cboDistribuidor;
	@InjectView(R.id.cboDistritoComercial) 		Spinner 	cboDistritoComercial;
	@InjectView(R.id.cboDistritoGeografico) 	Spinner 	cboDistritoGeografico;
	@InjectView(R.id.cboZona) 					Spinner 	cboZona;
	@InjectView(R.id.cboRutaIK) 				Spinner 	cboRutaIK;
	@InjectView(R.id.cboSegmento) 				Spinner 	cboSegmento;
	@InjectView(R.id.cboUbicacion) 				Spinner 	cboUbicacion;
	
	@InjectView(R.id.btnSeleccionarDireccion) 	Button 		btnSeleccionarDireccion;
	
	
	@InjectResource(R.string.registrar_cliente_activity_title) 	String 	registrar_cliente_title;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtrazonsocial_error) 	String 	txtRazonSocial_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtruc_error) 			String 	txtRuc_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtruc_no_valido) 		String 	txtRuc_no_valido;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtdni_error) 			String 	txtDni_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtRucDni_error) 			String 	txtRucDni_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_txtrepresentante_error) 	String 	txtRepresentante_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_subcanal_error) 			String 	txtSubCanal_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_subcanal_dialog_ok) 		String 	txtSubCanal_dialog_ok;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_tamanio_error) 			String 	txtTamanio_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_tamanio_dialog_ok) 		String 	txtTamanio_dialog_ok;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_nuevo_dialog) 	String 	confirm_message_nuevo;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_update_dialog) 	String 	confirm_message_update;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_registrado) 		String 	cliente_registrado_message;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_confirm_actualizado) 		String 	cliente_actualizado_message;
	
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_direccionentrega_error) 			String 	txtDireccionEntrega_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_departamentoentrega_error) 		String 	cboDepartamentoEntrega_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_provinciaentrega_error) 			String 	cboProvinciaEntrega_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_distritoentrega_error) 			String 	cboDistritoEntrega_error;
	
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_direccionfiscal_error) 			String 	txtDireccionFiscal_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_departamentofiscal_error) 		String 	cboDepartamentoFiscal_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_provinciafiscal_error) 			String 	cboProvinciaFiscal_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_distritofiscal_error) 			String 	cboDistritoFiscal_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_distribuidor_error) 				String 	cboDistribuidor_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_distritocomercial_error) 			String 	cboDistritoComercial_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_distritogeografico_error) 		String 	cboDistritoGeografico_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_zona_error) 						String 	cboZona_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_ruta_error) 						String 	cboRuta_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_segmento_error) 					String 	cboSegmento_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_ubicacion_error) 					String 	cboUbicacion_error;
	@InjectResource(R.string.registrar_cliente_activity_datos_basicos_gps_error) 						String 	ubicacion_gps_error;
	
	
	//
	@Inject 						TablaBLL 			tablaBLL;
	@Inject 						ClienteBLL 			clienteBLL;
	@Inject							DatosClienteProxy 	datosClienteProxy;
	
	private ClienteTO clienteTO;
	private ClienteTO clienteBDTO;
	private int accion;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.registrarcliente_activity);
        
       clienteTO = new ClienteTO();
       	
       //
       Intent intent = getIntent();
       String codigoReferencia = intent.getStringExtra(CLIENTE_REFERENCIA_ID_KEY);
       String razonSocialReferencia = intent.getStringExtra(CLIENTE_REFERENCIA_RAZONSOCIAL_KEY);
       
       
       mActionBar.setTitle(R.string.registrar_cliente_activity_title);
       mActionBar.setHomeLogo(R.drawable.header_logo);
       mActionBar.setSubTitle(String.format("%s - %s", codigoReferencia,razonSocialReferencia));
       
       final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
       
  
       cboSubCanal.setAdapter(application.getAdapterSubCanal());
       cboTamanio.setAdapter(application.getAdapterTamanio());
       
       //UBICACIÓN DE CARTERA
       
       
       
       //DIRECCIONES
       cboDepartamentoEntrega.setAdapter(application.spinnerDepartamentos());
       cboDepartamentoEntrega.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			UbigeoTO ubigeoTO = (UbigeoTO) arg0.getItemAtPosition(arg2);
			UbigeoTOAdapter adapterPronvicias  = application.spinnerProvincias(ubigeoTO.getCodigo());
			cboProvinciaEntrega.setAdapter(adapterPronvicias);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			}
       });
       
       cboProvinciaEntrega.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub

			UbigeoTO ubigeoTO = (UbigeoTO) arg0.getItemAtPosition(arg2);
			UbigeoTOAdapter adapterDistritos  = application.spinnerDistrito(ubigeoTO.getCodigo());
			cboDistritoEntrega.setAdapter(adapterDistritos);
		}

		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
       });
       
       cboDepartamentoFiscal.setAdapter(application.spinnerDepartamentos());
       cboDepartamentoFiscal.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
			UbigeoTO ubigeoTO = (UbigeoTO) arg0.getItemAtPosition(arg2);
			UbigeoTOAdapter adapterPronvicias  = application.spinnerProvincias(ubigeoTO.getCodigo());
			cboProvinciaFiscal.setAdapter(adapterPronvicias);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
       });
       
       cboProvinciaFiscal.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			UbigeoTO ubigeoTO = (UbigeoTO) arg0.getItemAtPosition(arg2);
			UbigeoTOAdapter adapterDistritos  = application.spinnerDistrito(ubigeoTO.getCodigo());
			cboDistritoFiscal.setAdapter(adapterDistritos);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
       });
       
       cboDistribuidor.setAdapter(application.getAdapterDistribuidor());
       cboDistritoComercial.setAdapter(application.getAdapterDistritoComercial());
       cboDistritoGeografico.setAdapter(application.getAdapterDistritoGeografico());
       cboZona.setAdapter(application.getAdapterZona());
       cboRutaIK.setAdapter(application.getadapterRutaIK());
       cboSegmento.setAdapter(application.getSegmento());
       cboUbicacion.setAdapter(application.getUbicacion());
       
       Intent data = getIntent();
       accion =  data.getIntExtra(RegistrarClienteActivity.CODIGO_CLIENTE_ACCION, 0);
		
		
       processAsync(accion);
       
       txtRazonSocial.requestFocus();
            
    }
    
    	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	      if (requestCode == SeleccionarDireccionActivity.SELECCIONAR_CLIENTE) {
	    	  if(resultCode==RESULT_OK){
	    		  double latitud = data.getDoubleExtra(SeleccionarDireccionActivity.LATITUD_SELECCIONADA_KEY, 0);
	    		  double longitud = data.getDoubleExtra(SeleccionarDireccionActivity.LONGITUD_SELECCIONADA_KEY, 0);
	    		  
	    		  clienteTO.setLatitud(String.valueOf(latitud));
	    		  clienteTO.setLongitud(String.valueOf(longitud));
	    	  }
	      }   
    	}


    
    public void btnDireccion_onclick(View view){
    	
    	
    	
    	boolean textoValidos=true;
    	
    	if(txtRazonSocial.getText().toString().equalsIgnoreCase("")){
    		txtRazonSocial.setError(txtRazonSocial_error);
    		textoValidos=false;
    	}
    	
    	RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
        
    	String ruc = txtRuc.getText().toString().trim();
    	String dni = txtDni.getText().toString().trim();
    	
    	if(ruc.equalsIgnoreCase("") && dni.equalsIgnoreCase("")){
    		txtRuc.setError(txtRucDni_error);
    		txtDni.setError(txtRucDni_error);
    		textoValidos=false;
    	}else{
    		if(!ruc.equalsIgnoreCase("")){
        		boolean isValid = application.validarRuc(ruc);
        		if(!isValid){
        			txtRuc.setError(txtRuc_no_valido);
        			textoValidos=false;
        		}
        	}
    	}
    	
    	
    	/*
    	if(txtDni.getText().toString().equalsIgnoreCase("")){
    		txtDni.setError(txtDni_error);
    		textoValidos=false;
    	}*/
    	
    	if(txtRepresentante.getText().toString().equalsIgnoreCase("")){
    		txtRepresentante.setError(txtRepresentante_error);
    		textoValidos=false;
    	}
    	
    	
    	
    	if(textoValidos){
    		if(cboSubCanal.getSelectedItemPosition()==0){
    			MessageBox.showSimpleDialog(this,
    										registrar_cliente_title,
    										txtSubCanal_error,
    										txtSubCanal_dialog_ok,
    										new OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													cboSubCanal.performClick();
													
												}
											});
    			return ;
    		}
    		
    		if(cboTamanio.getSelectedItemPosition()==0){
    			
    			MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						txtTamanio_error,
						txtTamanio_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboTamanio.performClick();
							}
						});
    			
    			return ;
    		}
    		
    		
    	
    		clienteTO.setRazonSocial(StringHelper.null2CharSequence(txtRazonSocial.getText()).toString());
        	clienteTO.setRuc(StringHelper.null2CharSequence(txtRuc.getText()).toString());
        	clienteTO.setDni(StringHelper.null2CharSequence(txtDni.getText()).toString());
        	clienteTO.setNombreComercial(StringHelper.null2CharSequence(txtRepresentante.getText()).toString());
        	clienteTO.setTelefono1(StringHelper.null2CharSequence(txtTelefono1.getText()).toString());
        	clienteTO.setTelefono2(StringHelper.null2CharSequence(txtTelefono2.getText()).toString());
        	clienteTO.setFax(StringHelper.null2CharSequence(txtFax.getText()).toString());
        	clienteTO.setEmail(StringHelper.null2CharSequence(txtEmail.getText()).toString());
        	clienteTO.setUrl(StringHelper.null2CharSequence(txtUrl.getText()).toString());
        	clienteTO.setVendedor(StringHelper.null2CharSequence(txtVendedor.getText()).toString());
        	
        	String codSubCanal = ((TablaTO)cboSubCanal.getSelectedItem()).getCodigo();
        	clienteTO.setSubCanal(codSubCanal);
        	
        	String codTamanio = ((TablaTO)cboTamanio.getSelectedItem()).getCodigo();
        	clienteTO.setTamanio(codTamanio);
        	
        	viewFlipper.showNext();
    	}
    	
    	
    	
    	
    }
	  
	public void btnDatosGenerales_click(View view){
		viewFlipper.showPrevious();
	}

	
	public void btnDatosCartera_click(View view){
		boolean textoValidos=true;
		
		if(txtDireccionFiscal.getText().toString().equalsIgnoreCase("")){
			txtDireccionFiscal.setError(txtDireccionFiscal_error);
    		textoValidos=false;
    	}
		
		if(txtDireccionEntrega.getText().toString().equalsIgnoreCase("")){
			txtDireccionEntrega.setError(txtDireccionEntrega_error);
    		textoValidos=false;
    	}
		
		
		
		if(textoValidos){
			//FISCAL
			if(cboDepartamentoFiscal.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboDepartamentoFiscal_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboDepartamentoFiscal.performClick();
								
							}
						});
				return ;
			}
			
			if(cboProvinciaFiscal.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboProvinciaFiscal_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboProvinciaFiscal.performClick();
								
							}
						});
				return ;
			}
			
			if(cboDistritoFiscal.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboDistritoFiscal_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboDistritoFiscal.performClick();
								
							}
						});
				return ;
			}
			//ENTREGA
			if(cboDepartamentoEntrega.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboDepartamentoEntrega_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboDepartamentoEntrega.performClick();
								
							}
						});
				return ;
			}
			
			if(cboProvinciaEntrega.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboProvinciaEntrega_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboProvinciaEntrega.performClick();
								
							}
						});
				return ;
			}
			
			if(cboDistritoEntrega.getSelectedItemPosition()==0){
				
				MessageBox.showSimpleDialog(this,
						registrar_cliente_title,
						cboDistritoEntrega_error,
						txtSubCanal_dialog_ok,
						new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								cboDistritoEntrega.performClick();
								
							}
						});
				return ;
			}	
		
			clienteTO.setDireccionFiscal(StringHelper.null2CharSequence(txtDireccionFiscal.getText()).toString());
			clienteTO.setDireccionEntrega(StringHelper.null2CharSequence(txtDireccionEntrega.getText()).toString());
			
			String ubigeoFiscal = ((UbigeoTO)cboDistritoFiscal.getSelectedItem()).getCodigo();
        	clienteTO.setUbigeoFiscal(ubigeoFiscal);
        	
        	String ubigeoEntrega = ((UbigeoTO)cboDistritoEntrega.getSelectedItem()).getCodigo();
        	clienteTO.setUbigeoEntrega(ubigeoEntrega);
			
			viewFlipper.showNext();
		}
		
		
		
	}
	
	public void btnDatosCarteraPrevius_click(View view){
		viewFlipper.showPrevious();
	}
	
	public void btnSeleccionarDireccion_click(View view){
		
		double latitudRef = Double.parseDouble(clienteTO.getLatitudRef());
    	double longitudRef = Double.parseDouble(clienteTO.getLongitudRef());
    	
    	
    	
    	
    	Intent seleccionarDireccion = new Intent(getApplicationContext(), SeleccionarDireccionActivity.class);
    	seleccionarDireccion.putExtra(SeleccionarDireccionActivity.DIRECCION_KEY, clienteTO.getDireccionEntregaRef());
    	seleccionarDireccion.putExtra(SeleccionarDireccionActivity.LATITUD_KEY,latitudRef );
    	seleccionarDireccion.putExtra(SeleccionarDireccionActivity.LONGITUD_KEY, longitudRef);
    	
    	if(clienteTO.getLatitud()!=null){
	    	double latitud = Double.parseDouble(clienteTO.getLatitud());
	    	double longitud = Double.parseDouble(clienteTO.getLongitud());
	    	
	    	seleccionarDireccion.putExtra(SeleccionarDireccionActivity.LATITUD_SELECCIONADA_KEY,latitud );
	    	seleccionarDireccion.putExtra(SeleccionarDireccionActivity.LONGITUD_SELECCIONADA_KEY, longitud);
    	}
    	startActivityForResult(seleccionarDireccion, SeleccionarDireccionActivity.SELECCIONAR_CLIENTE);
    	
	}
	
	public void btnGuardar_onclick(View view){
		
		if(cboDistribuidor.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboDistribuidor_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboDistribuidor.performClick();
							
						}
					});
			return ;
		}
		
		if(cboDistritoComercial.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboDistritoComercial_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboDistritoComercial.performClick();
							
						}
					});
			return ;
		}
		
		if(cboDistritoGeografico.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboDistritoGeografico_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboDistritoGeografico.performClick();
							
						}
					});
			return ;
		}
		
		if(cboZona.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboZona_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboZona.performClick();
							
						}
					});
			return ;
		}
		

		if(cboRutaIK.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboRuta_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboRutaIK.performClick();
							
						}
					});
			return ;
		}
		
		if(cboSegmento.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboSegmento_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboSegmento.performClick();
							
						}
					});
			return ;
		}
		
		if(cboUbicacion.getSelectedItemPosition()==0){
			
			MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					cboUbicacion_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cboUbicacion.performClick();
							
						}
					});
			return ;
		}
		
		String distribuidor = ((TablaTO)cboDistribuidor.getSelectedItem()).getCodigo();
    	clienteTO.setDistribuidor(distribuidor);
    	
    	String distritoComercial = ((TablaTO)cboDistritoComercial.getSelectedItem()).getCodigo();
    	clienteTO.setDistritoComercial(distritoComercial);
    	
    	String distritoGeografico = ((TablaTO)cboDistritoGeografico.getSelectedItem()).getCodigo();
    	clienteTO.setDistritoGeografico(distritoGeografico);
    	
    	String zona = ((TablaTO)cboZona.getSelectedItem()).getCodigo();
    	clienteTO.setZona(zona);
    	
    	String ruta = ((TablaTO)cboRutaIK.getSelectedItem()).getCodigo();
    	clienteTO.setRutaIK(ruta);
    	
    	String segmento = ((TablaTO)cboSegmento.getSelectedItem()).getCodigo();
    	clienteTO.setSegmento(segmento);
    	
    	String ubicacion = ((TablaTO)cboUbicacion.getSelectedItem()).getCodigo();
    	clienteTO.setUbicacion(ubicacion);
		
    	
    	if( 
    			(clienteTO.getLatitud()==null || clienteTO.getLongitud()==null) ||
    			(clienteTO.getLatitud().equalsIgnoreCase("") || clienteTO.getLongitud().equalsIgnoreCase(""))
    		){
    		
    		MessageBox.showSimpleDialog(this,
					registrar_cliente_title,
					ubicacion_gps_error,
					txtSubCanal_dialog_ok,
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							btnSeleccionarDireccion.performClick();
							
						}
					});
			return ;
    	}
    	
    	final Context context = this;
    	final String messageQuestion,messageConfirm;
    	
    	if(clienteTO.getClienteId()==0){
    		messageQuestion = confirm_message_nuevo;
    		messageConfirm = cliente_registrado_message;
    	}else{
    		messageQuestion = confirm_message_update;
    		messageConfirm = cliente_actualizado_message;
    	}
    	
    	MessageBox.showConfirmDialog(context, 
    								registrar_cliente_title, 
    								messageQuestion, 
    								confirm_si,new OnClickListener() {
    								
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											
											RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
											
											clienteBLL.save(clienteTO , application.getUsuarioTO());
											
											
											MessageBox.showSimpleDialog(context, 
													registrar_cliente_title, 
													messageConfirm, 
													txtTamanio_dialog_ok,
													new OnClickListener() {
														
														@Override
														public void onClick(DialogInterface dialog, int which) {
															// TODO Auto-generated method stub
															terminar(dialog, which);
														}
													});
										}
									} , 
									confirm_no, new OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											
										}
									});
    	
    	
		
	}
	

	public final void terminar(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
		
	}
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		Intent data = getIntent();
		
		
		
		if(accion==CLIENTE_CARGAR_FROM_WS){
			String codigoCliente =  data.getStringExtra(CLIENTE_CODIGO_KEY);
			datosClienteProxy.setCodigo(codigoCliente);
			datosClienteProxy.execute();
		}else if(accion==CLIENTE_CARGAR_FROM_BD){
			int clienteId =  data.getIntExtra(RegistrarClienteActivity.CLIENTE_ID_KEY,0);
			clienteBDTO = clienteBLL.listById(clienteId);
		}
	}
	
	private void cargarDatosCliente(ClienteTO clienteTOSource){
		
		clienteTO = new ClienteTO();
		int posicion=0;
		
		
		clienteTO.setCodigoMotivo(clienteTOSource.getCodigoMotivo());
		clienteTO.setMotivo(clienteTOSource.getMotivo());
		
		
		clienteTO.setClienteId(clienteTOSource.getClienteId());
		clienteTO.setRazonSocial(StringHelper.null2String(clienteTOSource.getRazonSocial()));
		clienteTO.setRuc(StringHelper.null2String(clienteTOSource.getRuc()));
		clienteTO.setDni(StringHelper.null2String(clienteTOSource.getDni()));
		clienteTO.setNombreComercial(StringHelper.null2String(clienteTOSource.getNombreComercial()));
		clienteTO.setTelefono1(StringHelper.null2String(clienteTOSource.getTelefono1()));
		clienteTO.setTelefono2(StringHelper.null2String(clienteTOSource.getTelefono2()));
		clienteTO.setCodigoPredecesor(StringHelper.null2String(clienteTOSource.getCodigoPredecesor()));
		clienteTO.setCodigoSucesor(StringHelper.null2String(clienteTOSource.getCodigoSucesor()));
		clienteTO.setFax(StringHelper.null2String(clienteTOSource.getFax()));
		clienteTO.setSubCanal(StringHelper.null2String(clienteTOSource.getSubCanal()));
		clienteTO.setEmail(StringHelper.null2String(clienteTOSource.getEmail()));
		clienteTO.setUrl(StringHelper.null2String(clienteTOSource.getUrl()));
		clienteTO.setVendedor(StringHelper.null2String(clienteTOSource.getVendedor()));
		clienteTO.setTamanio(StringHelper.null2String(clienteTOSource.getTamanio()));		
		
		clienteTO.setDireccionFiscal(clienteTOSource.getDireccionFiscal());
		clienteTO.setUbigeoFiscal(clienteTOSource.getUbigeoFiscal());
		clienteTO.setDireccionEntrega(clienteTOSource.getDireccionEntrega());
		clienteTO.setUbigeoEntrega(clienteTOSource.getUbigeoEntrega());
		if(clienteTO.getDireccionFiscal()==null || clienteTO.getDireccionFiscal().compareTo("")==0){
			clienteTO.setDireccionFiscal(clienteTOSource.getDireccionEntrega());
			clienteTO.setUbigeoFiscal(clienteTOSource.getUbigeoEntrega());	
		}
		
		
		clienteTO.setDistribuidor(clienteTOSource.getDistribuidor());
		clienteTO.setDistritoComercial(clienteTOSource.getDistritoComercial());
		clienteTO.setDistritoGeografico(clienteTOSource.getDistritoGeografico());
		clienteTO.setZona(clienteTOSource.getZona());
		clienteTO.setRutaIK(clienteTOSource.getRutaIK());
		clienteTO.setSegmento(clienteTOSource.getSegmento());
		clienteTO.setUbicacion(clienteTOSource.getUbicacion());
		clienteTO.setLatitud(clienteTOSource.getLatitud());
		clienteTO.setLongitud(clienteTOSource.getLongitud());
		clienteTO.setCodigoMotivo(clienteTOSource.getCodigoMotivo());
		clienteTO.setObservacion(clienteTOSource.getObservacion());
		clienteTO.setIdRef(clienteTOSource.getIdRef());
		clienteTO.setIdBd(clienteTOSource.getIdBd());
		clienteTO.setIdRef(clienteTOSource.getIdRef());
		clienteTO.setAccion(clienteTOSource.accion);
		
		if(RegistrarClienteActivity.CLIENTE_CARGAR_FROM_BD==accion){
			clienteTO.setCodigo(clienteTOSource.getCodigo());
			clienteTO.setCodigoReferencia(clienteTOSource.codigoReferencia);
		}else if(RegistrarClienteActivity.CLIENTE_CARGAR_FROM_WS==accion){
			clienteTO.setCodigoReferencia(clienteTOSource.codigo);
		}
		
		clienteTO.setLatitudRef(clienteTOSource.getLatitudRef());
		clienteTO.setLongitudRef(clienteTOSource.getLongitudRef());
		clienteTO.setDireccionEntregaRef(clienteTOSource.getDireccionEntregaRef());
		
		String razonSocialRef = clienteTOSource.getRazonSocial();
		if(razonSocialRef==null){
			razonSocialRef = clienteTOSource.getNombreComercial();
		}
		
		clienteTO.setRazonSocialRef(razonSocialRef);
			
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		
		if(RegistrarClienteActivity.CLIENTE_CARGAR_FROM_BD==accion){
			txtRazonSocial.setText(clienteTO.getRazonSocial());
			txtRuc.setText(clienteTO.getRuc());
			txtDni.setText(clienteTO.getDni());
			txtRepresentante.setText(clienteTO.getNombreComercial());
			txtTelefono1.setText(clienteTO.getTelefono1());
			txtTelefono2.setText(clienteTO.getTelefono2());
			txtFax.setText(clienteTO.getFax());
			

			TablaTOAdapter subCanalSpinnerAdapter =  (TablaTOAdapter)cboSubCanal.getAdapter();
			posicion = subCanalSpinnerAdapter.findByValue(StringHelper.null2String(clienteTOSource.getSubCanal()));
			cboSubCanal.setSelection(posicion,true);
			
			txtEmail.setText(clienteTO.getEmail());
			txtUrl.setText(clienteTO.getUrl());
			txtVendedor.setText(clienteTO.getVendedor());
			
			TablaTOAdapter tamanioSpinnerAdapter =  (TablaTOAdapter)cboTamanio.getAdapter();
			posicion = tamanioSpinnerAdapter.findByValue(StringHelper.null2String(clienteTOSource.getTamanio()));
			cboTamanio.setSelection(posicion,true);
			
		}
		
		//DIRECCIONES
		txtDireccionEntrega.setText(StringHelper.null2CharSequence(clienteTO.getDireccionEntrega()));
		txtDireccionFiscal.setText(StringHelper.null2CharSequence(clienteTO.getDireccionFiscal()));	
		String ubigeoEntrega = StringHelper.null2CharSequence(clienteTO.getUbigeoEntrega()).toString();
		String ubigeoFiscal = StringHelper.null2CharSequence(clienteTO.getUbigeoFiscal()).toString();
		
		
		
		if(null!=clienteTO.getUbigeoEntrega()){
			String departamentoEntrega = String.format("%s0000", ubigeoEntrega.substring(0, 2));
			String provinciaEntrega = String.format("%s00", ubigeoEntrega.substring(0, 4));
			String distritoEntrega = String.format("%s", ubigeoEntrega);
			
		 	UbigeoTOAdapter departamentoEntregaSpinnerAdapter =  (UbigeoTOAdapter)cboDepartamentoEntrega.getAdapter();
			posicion =departamentoEntregaSpinnerAdapter.findByValue(departamentoEntrega);
			cboDepartamentoEntrega.setSelection(posicion,true);
			
			UbigeoTOAdapter provinciaEntregaSpinnerAdapter = (UbigeoTOAdapter)cboProvinciaEntrega.getAdapter();
			posicion =provinciaEntregaSpinnerAdapter.findByValue(provinciaEntrega);
			cboProvinciaEntrega.setSelection(posicion,true);
			
			UbigeoTOAdapter distritoEntregaSpinnerAdapter = (UbigeoTOAdapter)cboDistritoEntrega.getAdapter();
			posicion =distritoEntregaSpinnerAdapter.findByValue(distritoEntrega);
			cboDistritoEntrega.setSelection(posicion,true);
		}
		
		
		if(null!=clienteTO.getUbigeoFiscal()){
			String departamentoFiscal = String.format("%s0000", ubigeoFiscal.substring(0, 2));
			String provinciaFiscal = String.format("%s00", ubigeoFiscal.substring(0, 4));
			String distritoFiscal = String.format("%s", ubigeoFiscal);
			
			UbigeoTOAdapter departamentoFiscalSpinnerAdapter = application.spinnerDepartamentos();
			posicion = departamentoFiscalSpinnerAdapter.findByValue(departamentoFiscal);
			cboDepartamentoFiscal.setSelection(posicion,true);
			
			UbigeoTOAdapter provinciaFiscalSpinnerAdapter = (UbigeoTOAdapter)cboProvinciaFiscal.getAdapter();
			posicion = provinciaFiscalSpinnerAdapter.findByValue(provinciaFiscal);
			cboProvinciaFiscal.setSelection(posicion,true);
	
			UbigeoTOAdapter distritoFiscalSpinnerAdapter = (UbigeoTOAdapter)cboDistritoFiscal.getAdapter();
			posicion = distritoFiscalSpinnerAdapter.findByValue(distritoFiscal);
			cboDistritoFiscal.setSelection(posicion,true);
		}
		
		TablaTOAdapter tablaTOSpinnerAdapter = application.getAdapterDistribuidor();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getDistribuidor()));
		cboDistribuidor.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getAdapterDistritoComercial();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getDistritoComercial()));
		cboDistritoComercial.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getAdapterDistritoGeografico();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getDistritoGeografico()));
		cboDistritoGeografico.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getAdapterZona();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getZona()));
		cboZona.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getadapterRutaIK();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getRutaIK()));
		cboRutaIK.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getSegmento();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getSegmento()));
		cboSegmento.setSelection(posicion);
		
		tablaTOSpinnerAdapter = application.getUbicacion();
		posicion =tablaTOSpinnerAdapter.findByValue(StringHelper.null2CharSequence(clienteTO.getUbicacion()));
		cboUbicacion.setSelection(posicion);
		
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion==CLIENTE_CARGAR_FROM_WS){
			boolean isExito = datosClienteProxy.isExito();
			if(isExito){
				int status = datosClienteProxy.getResponse().getStatus();
				
				if (status == 0) {
					ClienteTO clienteTO = datosClienteProxy.getResponse().getCliente();
					clienteTO.setAccion(ClienteTO.ACCION_ALTA);
					cargarDatosCliente(clienteTO);
				}
			}
		}else if(accion ==CLIENTE_CARGAR_FROM_BD){
			if(clienteBDTO!=null){
				cargarDatosCliente(clienteBDTO);
				clienteBDTO=null;
			}else{
				processError(accion);
			}
		}
		
		
		super.processOk(accion);
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		Log.d("processError", String.valueOf(accion));
		super.processError(accion);
	}
}
