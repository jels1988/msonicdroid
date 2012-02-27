package pe.lindley.om.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.om.adapter.ParametroTOAdapter;
import pe.lindley.om.adapter.RolTOAdapter;
import pe.lindley.om.negocio.ClienteBLL;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.negocio.RolBLL;
import pe.lindley.om.to.ClienteTO;
import pe.lindley.om.to.EventoTO;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.om.to.ParametroTO;
import pe.lindley.om.to.RolTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class RegistrarEventoOMActivity extends ActivityBase {
	
	public static final String ORDEN_TRABAJO_ID = "ORDEN_TRABAJO_ID";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_RAZONSOCIAL_KEY="cliente_descripcion";
	
	@Inject ClienteBLL clienteBLL;
	@Inject RolBLL rolBLL;
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	
	private String cliente_codigo = null;
	private String cliente_descripcion = null;
	
	private long ordenTrabajoId;
	private OrdenTrabajoTO ordenTrabajoTO=null;
	private ClienteTO clienteTO;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.txtNroOrden)		TextView txtNroOrden;
	@InjectView(R.id.cboTipoOrden) 		Spinner 	cboTipoOrden;
	@InjectView(R.id.cboSubTipoOrden) 	Spinner 	cboSubTipoOrden;
	@InjectView(R.id.cboMotivo) 		Spinner 	cboMotivo;
	@InjectView(R.id.cboAsignadoa) 		Spinner 	cboAsignadoa;
	@InjectView(R.id.txtObservacion) 	EditText 	txtObservacion;
	@InjectView(R.id.txtCreadoPor)		TextView txtCreadoPor;

	@InjectResource(R.string.registrareventoomactivity_title) 						String  guardar_dialog_title;
	@InjectResource(R.string.nuevaordenom_activity_guardar_confirm_dialog) 			String  guardar_dialog_ok;
	@InjectResource(R.string.nuevaordenom_activity_guardar_confirm_dialog_no) 		String 	guardar_no;
	@InjectResource(R.string.nuevaordenom_activity_guardar_confirm_dialog_si) 		String 	guardar_si;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.registrareventoom_activity);
		
	
		
		Intent intent = this.getIntent();
		ordenTrabajoId = intent.getLongExtra(ORDEN_TRABAJO_ID, 0);
		cliente_codigo = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_RAZONSOCIAL_KEY);
		clienteTO = clienteBLL.list(cliente_codigo);
		
		
		 mActionBar.setTitle(R.string.registrareventoomactivity_title);
	     mActionBar.setHomeLogo(R.drawable.header_logo);
	     mActionBar.setSubTitle(cliente_descripcion);
	       
	
		
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();

		ordenTrabajoTO = ordenTrabajoBLL.list(ordenTrabajoId);
		
		cboMotivo.setAdapter(application.getMotivoEventosAdapter());
		cboTipoOrden.setAdapter(application.getTipoOrdenAdapter());
		cboTipoOrden.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				// TODO Auto-generated method stub
				ParametroTO parametroTO = (ParametroTO)arg0.getItemAtPosition(arg2);
				String codigoOrden = parametroTO.getId().substring(0,1);
				cboSubTipoOrden.setAdapter(application.getSubTipoOrdenAdapter(codigoOrden));
				
				
				String rol = application.getUsuarioTO().getRol();
				
				ArrayList<RolTO> roles = rolBLL.list(clienteTO, rol, codigoOrden);
				
				if(roles.size()<=0){
					roles = new ArrayList<RolTO>();
					RolTO rolTO = new RolTO();
					rolTO.setNombres("--Seleccionar--");
					rolTO.setAsignacionId(0);
					roles.add(rolTO);
				}
				
				RolTOAdapter rolTOAdapter = new RolTOAdapter(application, roles);
				cboAsignadoa.setAdapter(rolTOAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		txtNroOrden.setText(String.valueOf(ordenTrabajoTO.getNuOrd()));
		
		ParametroTOAdapter tipoOrdenAdapter = (ParametroTOAdapter)cboTipoOrden.getAdapter();
		int posicion = tipoOrdenAdapter.findByValue(ordenTrabajoTO.getTpOrd());
		cboTipoOrden.setSelection(posicion,true);
		
		ParametroTOAdapter subTipoOrdenAdapter = (ParametroTOAdapter)cboSubTipoOrden.getAdapter();
		posicion = subTipoOrdenAdapter.findByValue(ordenTrabajoTO.getStOrd());
		cboSubTipoOrden.setSelection(posicion,true);
		
		ParametroTOAdapter motivoAdapter = (ParametroTOAdapter)cboMotivo.getAdapter();
		posicion = motivoAdapter.findByValue(ordenTrabajoTO.getMtOrd());
		cboMotivo.setSelection(posicion,true);
		
		txtCreadoPor.setText(ordenTrabajoTO.getDsUsC());
	
		cboTipoOrden.setEnabled(false);
		cboSubTipoOrden.setEnabled(false);
		
	}
	
	
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		ordenTrabajoBLL.update(ordenTrabajoTO);
		
		
	}


	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		super.processOk();
		finish();
		
	}


	public void btnGuardar_click(View view){
		if(null!=ordenTrabajoTO){
			
			CharSequence fecha = android.text.format.DateFormat.format("yyyyMMdd",new java.util.Date());
			CharSequence hora = android.text.format.DateFormat.format("kkmmss",new java.util.Date());
			
			ordenTrabajoTO.setFeMod(String.valueOf(fecha));
			ordenTrabajoTO.setHrMod(String.valueOf(hora));
			
			ParametroTO motivo = (ParametroTO)cboMotivo.getSelectedItem();
			String codigoMotivo = motivo.getId();
			
			
			EventoTO eventoTO = new EventoTO();
			
			eventoTO.setTpEve(ordenTrabajoTO.getTpOrd());
			eventoTO.setDsTpE(ordenTrabajoTO.getDsTpO());
			
			eventoTO.setTpMot(codigoMotivo);
			eventoTO.setDsTpM(motivo.getDescripcion());
			eventoTO.setDsObs(txtObservacion.getText().toString());
			
			
			eventoTO.setTpRCr(ordenTrabajoTO.getTpRCr());
			eventoTO.setUsRCr(ordenTrabajoTO.getUsRCr());
			eventoTO.setDsUsC(ordenTrabajoTO.getDsUsC());
			
			eventoTO.setTpReA(ordenTrabajoTO.getTpReA());
			eventoTO.setUsReA(ordenTrabajoTO.getUsReA());
			eventoTO.setDsUsA(ordenTrabajoTO.getDsUsA());
			
			eventoTO.setTpReU(ordenTrabajoTO.getTpReU());
			eventoTO.setUsReU(ordenTrabajoTO.getUsReU());
			eventoTO.setDsUsU(ordenTrabajoTO.getDsUsU());
			
			eventoTO.setFeCre(String.valueOf(fecha));
		    eventoTO.setHrCre(String.valueOf(hora));
		    eventoTO.setFeMod(String.valueOf(fecha));
		    eventoTO.setHrMod(String.valueOf(hora));
			
			if(codigoMotivo.equalsIgnoreCase("0301")){
				ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.CERRADO);
				ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.CERRADO_DES);
				eventoTO.setTpMot(OrdenTrabajoBLL.EVENTO_EJECUTADO);
			}else if(codigoMotivo.equalsIgnoreCase("0305")){
				ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.CERRADO);
				ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.CERRADO_DES);
				eventoTO.setTpMot(OrdenTrabajoBLL.EVENTO_ANULAD0);
			}else if(codigoMotivo.equalsIgnoreCase("0303")){
				ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.PROCESO);
				ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.PROCESO_DES);
				eventoTO.setTpMot(OrdenTrabajoBLL.EVENTO_RECHAZADO);
			}else if(codigoMotivo.equalsIgnoreCase("0302")){
				ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.PROCESO);
				ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.PROCESO_DES);
				eventoTO.setTpMot(OrdenTrabajoBLL.EVENTO_ASIGNADO);
			}else if(codigoMotivo.equalsIgnoreCase("0304")){
				ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.PROCESO);
				ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.PROCESO_DES);
				eventoTO.setTpMot(OrdenTrabajoBLL.EVENTO_REPLANIFICADO);
			}
			
			
			RolTO rolTO = ((RolTO)cboAsignadoa.getSelectedItem());
			if(rolTO.getAsignacionId()!=0){
				ordenTrabajoTO.setTpReU(ordenTrabajoTO.getTpReA());
			    ordenTrabajoTO.setUsReU(ordenTrabajoTO.getUsReA());
			    ordenTrabajoTO.setDsUsU(ordenTrabajoTO.getDsUsA());
			    
			    ordenTrabajoTO.setTpReA(rolTO.getTipoRol());
			    ordenTrabajoTO.setUsReA(rolTO.getCodigoSap());
                ordenTrabajoTO.setDsUsA(rolTO.getNombres());


			}
			
			ordenTrabajoTO.getEventos().add(eventoTO);
			
			final Context context = this;
			
			MessageBox.showConfirmDialog(context, 
					guardar_dialog_title, 
					guardar_dialog_ok, 
					guardar_si,new OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							
							processAsync();
							
						}
					} , 
					guardar_no, new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
		}
	}


	

}
