package pe.lindley.om.activity;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.to.EventoTO;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.om.ws.service.DescargarEventosOMProxy;
import pe.lindley.om.ws.service.DescargarOrdenesOMProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DescargarOrdenesOMActivity extends ActivityBase {

	private UsuarioTO usuarioTO = null;
	
	@Inject DescargarOrdenesOMProxy descargarOrdenesOMProxy;
	@Inject DescargarEventosOMProxy descargarEventosOMProxy;
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	 
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	

	@InjectResource(R.string.descargarordenesom_activity_title) 	String 	descargarordenesom_activity_title;
	@InjectResource(R.string.descargarordenesom_activity_message) 	String 	descargarordenesom_activity_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.descargarordenesom_activity);
		
		mActionBar.setTitle(R.string.descargarordenesom_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuarioTO =  application.getUsuarioTO();
		
	
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		descargarOrdenesOMProxy.setCodigoSap(usuarioTO.getCodigoSap());
		descargarOrdenesOMProxy.execute();
		
		descargarEventosOMProxy.setCodigoSap(usuarioTO.getCodigoSap());
		descargarEventosOMProxy.execute();
		super.process();
	}
	
	@Override
	protected void processOk() {
		
		
		boolean isExitoOrdenes = descargarOrdenesOMProxy.isExito();
		int status = -1;
		if (!isExitoOrdenes) {
		
			status = descargarOrdenesOMProxy.getResponse().getStatus();
			String message;
			
			if (status != 0) {
				message = descargarOrdenesOMProxy.getResponse().getDescripcion();
				super.processError();
				showToast(message);
				return;
			}else{
				super.processOk();
				return;
			}
			
		}
		
		boolean isExitoEventos = descargarEventosOMProxy.isExito();
		if (!isExitoEventos) {
		
			status = descargarEventosOMProxy.getResponse().getStatus();
			String message;
			
			if (status != 0) {
				message = descargarEventosOMProxy.getResponse().getDescripcion();
				super.processError();
				showToast(message);
				return;
			}else{
				super.processOk();
				return;
			}
			
		}
		
		List<OrdenTrabajoTO> ordenTrabajoTO = descargarOrdenesOMProxy.getResponse().getOrdenes();
		ordenTrabajoBLL.save(ordenTrabajoTO);
		
		List<EventoTO> eventos = descargarEventosOMProxy.getResponse().getEventos();
		ordenTrabajoBLL.saveEventos(eventos);
		
		super.processOk();
		
		final Context context = this;
		
		MessageBox.showSimpleDialog(context, descargarordenesom_activity_title, descargarordenesom_activity_message, "Aceptar", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view){
		processAsync();
	}

	  
}
