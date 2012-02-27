package pe.lindley.om.activity;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.UsuarioTO;
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
import roboguice.inject.InjectView;

public class NuevaOrdenOMActivity extends ActivityBase {

	
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_RAZONSOCIAL_KEY="cliente_descripcion";
	
	
	private String cliente_codigo = null;
	private String cliente_descripcion = null;
	private ClienteTO clienteTO;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.cboTipoContacto) 	Spinner 	cboTipoContacto;
	@InjectView(R.id.cboTipoOrden) 		Spinner 	cboTipoOrden;
	@InjectView(R.id.cboSubTipoOrden) 	Spinner 	cboSubTipoOrden;
	@InjectView(R.id.cboMotivo) 		Spinner 	cboMotivo;
	@InjectView(R.id.cboPrioridad) 		Spinner 	cboPrioridad;
	@InjectView(R.id.cboEstado) 		Spinner 	cboEstado;
	@InjectView(R.id.cboAsignadoa) 		Spinner 	cboAsignadoa;
	@InjectView(R.id.txtObservacion) 	EditText 	txtObservacion;
	
	@Inject ClienteBLL clienteBLL;
	@Inject RolBLL rolBLL;
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.nuevaordenom_activity);
		
		mActionBar.setTitle(R.string.nuevaordenom_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_RAZONSOCIAL_KEY);
		clienteTO = clienteBLL.list(cliente_codigo);
		mActionBar.setSubTitle(cliente_descripcion);
		
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
	      
		cboTipoContacto.setAdapter(application.getTipoContactoAdapter());
		cboPrioridad.setAdapter(application.getTipoPrioridadAdapter());
		cboEstado.setAdapter(application.getEstadoAdapter());
		
		cboTipoOrden.setAdapter(application.getTipoOrdenAdapter());
		cboTipoOrden.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				// TODO Auto-generated method stub
				ParametroTO parametroTO = (ParametroTO)arg0.getItemAtPosition(arg2);
				String codigoOrden = parametroTO.getId().substring(0,1);
				cboSubTipoOrden.setAdapter(application.getSubTipoOrdenAdapter(codigoOrden));
				cboMotivo.setAdapter(application.getMotivoOrdenAdapter(codigoOrden));
				
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
		
	}
	
	
	
	public void btnGuardar_click(View view){
		OrdenTrabajoTO ordenTrabajoTO = new OrdenTrabajoTO();
		
        ParametroTO tipoOrden = ((ParametroTO)cboTipoOrden.getSelectedItem());
        ordenTrabajoTO.setTpOrd(tipoOrden.getId());
        ordenTrabajoTO.setDsTpO(tipoOrden.getDescripcion());
        
		ParametroTO tipoContacto = ((ParametroTO)cboTipoContacto.getSelectedItem());
		ordenTrabajoTO.setTpCon(tipoContacto.getId());
		ordenTrabajoTO.setDsTpC(tipoContacto.getDescripcion());
		
		ParametroTO motivo = ((ParametroTO)cboMotivo.getSelectedItem());
		ordenTrabajoTO.setMtOrd(motivo.getId().substring(2,4));
		ordenTrabajoTO.setDsMtO(motivo.getDescripcion());
		
		ParametroTO subtipo = ((ParametroTO)cboSubTipoOrden.getSelectedItem());
		ordenTrabajoTO.setStOrd(subtipo.getId().substring(1,2));
		ordenTrabajoTO.setDsStO(subtipo.getDescripcion());
		
		ParametroTO prioridad = ((ParametroTO)cboPrioridad.getSelectedItem());
		ordenTrabajoTO.setCdPri(prioridad.getId());
		ordenTrabajoTO.setDsPri(prioridad.getDescripcion());
		
		ordenTrabajoTO.setCdCli(cliente_codigo);
		
		ordenTrabajoTO.setDsObs(txtObservacion.getText().toString());
		
		
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		
		
		//ESTADO
		ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.CREADO);
		ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.CREADO_DES);
        
		CharSequence fecha = android.text.format.DateFormat.format("yyyyMMdd",new java.util.Date());
		CharSequence hora = android.text.format.DateFormat.format("kkmmss",new java.util.Date());
		
		ordenTrabajoTO.setFeCre(fecha.toString());
		ordenTrabajoTO.setHrCre(hora.toString());
		
		ordenTrabajoTO.setFeMod(ordenTrabajoTO.getFeCre());
		ordenTrabajoTO.setHrMod(ordenTrabajoTO.getHrCre());
		ordenTrabajoTO.setCdStA(0);
		
		//INICIALIZAMOS LA ORDEN
		UsuarioTO usuarioCreador = application.getUsuarioTO();
		ordenTrabajoTO.setTpRCr(usuarioCreador.getRol());
		ordenTrabajoTO.setUsRCr(usuarioCreador.getCodigoSap());
		ordenTrabajoTO.setDsUsC(usuarioCreador.getNombres());
		
		ordenTrabajoTO.setTpReA(usuarioCreador.getRol());
		ordenTrabajoTO.setUsReA(usuarioCreador.getCodigoSap());
		ordenTrabajoTO.setDsUsA(usuarioCreador.getNombres());
		
		ordenTrabajoTO.setTpReU(usuarioCreador.getRol());
		ordenTrabajoTO.setUsReU(usuarioCreador.getCodigoSap());
		ordenTrabajoTO.setDsUsU(usuarioCreador.getNombres());
		
		
		//CREAR UN EVENTO POR DEFECTO
		
		EventoTO eventoTO = new EventoTO();
		eventoTO.setTpEve(ordenTrabajoTO.getTpOrd());
		eventoTO.setDsTpE(ordenTrabajoTO.getDsTpO());
		eventoTO.setTpMot(ordenTrabajoTO.getMtOrd());
		eventoTO.setDsTpM(ordenTrabajoTO.getDsMtO());
		
		eventoTO.setTpRCr(usuarioCreador.getRol());
		eventoTO.setUsRCr(usuarioCreador.getCodigoSap());
		eventoTO.setDsUsC(usuarioCreador.getNombres());
		
		eventoTO.setFeCre(ordenTrabajoTO.getFeCre());
		eventoTO.setHrCre(ordenTrabajoTO.getHrCre());
		
		
		
		eventoTO.setTpReA(usuarioCreador.getRol());
		eventoTO.setUsReA(usuarioCreador.getCodigoSap());
		eventoTO.setDsUsA(usuarioCreador.getNombres());
		
		eventoTO.setTpReU(usuarioCreador.getRol());
		eventoTO.setUsReU(usuarioCreador.getCodigoSap());
		eventoTO.setDsUsU(usuarioCreador.getNombres());

		eventoTO.setFeMod(ordenTrabajoTO.getFeCre());
		eventoTO.setHrMod(ordenTrabajoTO.getHrCre());
		
		eventoTO.setDsObs(ordenTrabajoTO.getDsObs());
		
		ordenTrabajoTO.getEventos().add(eventoTO);
		
		RolTO rolTO = ((RolTO)cboAsignadoa.getSelectedItem());
		
		if(rolTO.getAsignacionId()!=0){
			
			
			ordenTrabajoTO.setTpReA(rolTO.getTipoSub());
			ordenTrabajoTO.setUsReA(rolTO.getCodigoSub());
			ordenTrabajoTO.setDsUsA(rolTO.getNombres());
            
			
			
			eventoTO.setTpReA(rolTO.getTipoSub());
			eventoTO.setUsReA(rolTO.getCodigoSub());
			eventoTO.setDsUsA(rolTO.getNombres());
            
			
		
			
			//eventoTO.setUsReA(usuarioCreador.getCodigoSap());
			//eventoTO.setDsUsA(usuarioCreador.getNombres());
			
			EventoTO eventoAdicionalTO = new EventoTO();
			
			ordenTrabajoTO.setEsOrd(OrdenTrabajoBLL.PROCESO);
			ordenTrabajoTO.setDsEsO(OrdenTrabajoBLL.PROCESO_DES);
			
			//ordenTrabajoTO.setTpReA(rolTO.getTipoRol());
			//ordenTrabajoTO.setUsReA(rolTO.getCodigoSub()); //getCodigoSap());
			//ordenTrabajoTO.setDsUsA(rolTO.getNombres());
			
			eventoAdicionalTO.setTpEve(ordenTrabajoTO.getTpOrd());
			eventoAdicionalTO.setDsTpE(ordenTrabajoTO.getDsTpO());
			
			eventoAdicionalTO.setTpMot(OrdenTrabajoBLL.EVENTO_ASIGNADO);
            eventoAdicionalTO.setDsTpM(OrdenTrabajoBLL.EVENTO_ASIGNADO_DES);
            
            eventoAdicionalTO.setTpRCr(ordenTrabajoTO.getTpRCr());
            eventoAdicionalTO.setUsRCr(ordenTrabajoTO.getUsRCr());
            eventoAdicionalTO.setDsUsC(ordenTrabajoTO.getDsUsC());
            
            
            
            eventoAdicionalTO.setTpReA(rolTO.getTipoSub());
            eventoAdicionalTO.setUsReA(rolTO.getCodigoSub());
            eventoAdicionalTO.setDsUsA(rolTO.getNombres());
    		
            eventoAdicionalTO.setTpReU(usuarioCreador.getRol());
            eventoAdicionalTO.setUsReU(usuarioCreador.getCodigoSap());
            eventoAdicionalTO.setDsUsU(usuarioCreador.getNombres());
            
            eventoAdicionalTO.setDsObs(ordenTrabajoTO.getDsObs());
            
            eventoAdicionalTO.setFeCre(fecha.toString());
            eventoAdicionalTO.setHrCre(hora.toString());
            eventoAdicionalTO.setFeMod(fecha.toString());
            eventoAdicionalTO.setHrMod(hora.toString());
            
            ordenTrabajoTO.getEventos().add(eventoAdicionalTO);
            
		}
		
		ordenTrabajoBLL.save(ordenTrabajoTO);
		finish();
	}
	
}
