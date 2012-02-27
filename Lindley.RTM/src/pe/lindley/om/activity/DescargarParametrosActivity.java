package pe.lindley.om.activity;


import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.om.negocio.ParametroBLL;
import pe.lindley.om.negocio.RolBLL;
import pe.lindley.om.to.ParametroTO;
import pe.lindley.om.to.RolTO;
import pe.lindley.om.ws.service.DescargarClienteProxy;
import pe.lindley.om.ws.service.DescargarParametrosProxy;
import pe.lindley.om.ws.service.DescargarRolesProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;


public class DescargarParametrosActivity extends ActivityBase {
	
	private UsuarioTO usuario = null;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject DescargarParametrosProxy descargarParametrosProxy;
	@Inject DescargarRolesProxy descargarRolesProxy;
	@Inject DescargarClienteProxy descargarClienteProxy;
	
	@Inject ParametroBLL parametroBLL;
	@Inject RolBLL rolBLL;
	
	@InjectResource(R.string.descargarparametros_activity_title) 	String 	descargar_parametros_title;
	@InjectResource(R.string.descargarparametros_activity_message) 	String 	descargarparametros_activity_message;
	@InjectResource(R.string.descargarparametros_activity_error) 	String 	descargarparametros_activity_error;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.descargarparametros_activity);
		

		mActionBar.setTitle(R.string.descargarparametros_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
        RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuario = application.getUsuarioTO();
		
	}
	
	@Override
	protected void process() {
		String codigoDeposito = usuario.getCodigoDeposito();
		String tipoRol = usuario.getRol();
		
		descargarParametrosProxy.setCodigoAlmancen(codigoDeposito);
		descargarParametrosProxy.execute();
		
		List<ParametroTO> parametros =	descargarParametrosProxy.getResponse().getParametros();
		if(parametros!=null){
			parametroBLL.save(parametros);
		}
		
		descargarRolesProxy.setCodigoDeposito(codigoDeposito);
		descargarRolesProxy.setTipoRol(tipoRol);
		descargarRolesProxy.execute();
		
		List<RolTO> roles = descargarRolesProxy.getResponse().getRoles();
		if(roles!=null){
			rolBLL.save(roles);
		}
		
	}
	
	@Override
	protected void processOk() {
		
		boolean isExitoParametros = descargarParametrosProxy.isExito();
		int status = -1;
		if (!isExitoParametros) {
			
			status = descargarParametrosProxy.getResponse().getStatus();
			String message;
			
			if (status != 0) {
				message = descargarParametrosProxy.getResponse().getDescripcion();
				super.processError();
				showToast(message);
				return;
			}else{
				super.processOk();
				return;
			}
		}
		
		boolean isExitoRoles = descargarRolesProxy.isExito();
		
		if (!isExitoRoles) {
			
			status = descargarRolesProxy.getResponse().getStatus();
			String message;
			if (status != 0) {
				message = descargarRolesProxy.getResponse().getDescripcion();
				super.processError();
				showToast(message);
				return;
			}else{
				super.processOk();
				return;
			}
		}
		
		super.processOk();
		final Context context = this;
		
		MessageBox.showSimpleDialog(context, descargar_parametros_title, descargarparametros_activity_message, "Aceptar", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		
	}
	

	@Override
	protected void processError() {
		boolean isExitoParametros = descargarParametrosProxy.isExito();
		boolean isExitoRoles = descargarRolesProxy.isExito();
		
		String message;
		
		final Context context = this;
		
		if(!isExitoParametros){
			if(descargarParametrosProxy.getResponse()!=null){
				String error = descargarParametrosProxy.getResponse().getDescripcion();
				message = error;
			}else{
				message = error_generico_process;
			}
			
			super.processError();
			//showToast(message);
			
			
			
			MessageBox.showSimpleDialog(context, descargar_parametros_title, message, "Aceptar", null);
			
			return;
		}
		
		if(!isExitoRoles){
			if(descargarRolesProxy.getResponse()!=null){
				String error = descargarRolesProxy.getResponse().getDescripcion();
				message = error;
			}else{
				message = error_generico_process;
			}
			
			super.processError();
			MessageBox.showSimpleDialog(context, descargar_parametros_title, message, "Aceptar", null);
			return;
		}
	}
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view){
		processAsync();
	}

}
