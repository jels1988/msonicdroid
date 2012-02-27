package pe.lindley.om.activity;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import pe.lindley.activity.R;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.om.ws.service.EnviarOrdenesProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class EnviarOrdenesOMActivity extends ActivityBase {
	
	@Inject OrdenTrabajoBLL ordenTrabajoBLL;
	@Inject EnviarOrdenesProxy enviarOrdenesProxy;
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	public static final String ID_ORDEN = "orden_id";
	public static final String TIPO_ACCION = "tipo_accion";
	
	public static final int ACCION_ENVIAR_TODOS = 1;   
    public static final int ACCION_ENVIAR_UNO = 2;
    
	private static int codigo_orden = 0;
	private static int accion = 0;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		
		accion = intent.getIntExtra(TIPO_ACCION, 0);
		if(accion == ACCION_ENVIAR_UNO)
		{			
			codigo_orden = intent.getIntExtra(ID_ORDEN, 0);
		}
		setContentView(R.layout.enviarordenesom_activity);
		
		 mActionBar.setTitle(R.string.enviarordenesom_activity_title);
	     mActionBar.setHomeLogo(R.drawable.header_logo);
	     
		processAsync();
	}

	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ENVIAR_TODOS)
		{
			ArrayList<OrdenTrabajoTO> ordenes = ordenTrabajoBLL.listAllWithEventos();
			enviarOrdenesProxy.setOrdenes(ordenes);
			enviarOrdenesProxy.execute();
		}
		else if(accion == ACCION_ENVIAR_UNO)
		{
			System.out.println(codigo_orden);
			ArrayList<OrdenTrabajoTO> ordenes = new ArrayList<OrdenTrabajoTO>();
			OrdenTrabajoTO orden = ordenTrabajoBLL.listWithEventos(codigo_orden);
			ordenes.add(orden);			
			enviarOrdenesProxy.setOrdenes(ordenes);
			enviarOrdenesProxy.execute();
		}
		super.process(accion);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ENVIAR_TODOS)
		{
			boolean isExito = enviarOrdenesProxy.isExito();
			
			int status = -1;
			if (isExito) {
				status = enviarOrdenesProxy.getResponse().getStatus();
				if (status == 0) {
					ordenTrabajoBLL.deleteAllPendientesWithEventos();
					super.processOk(accion);
					finish();
					return;
				}
			}else{
				processError();
				return;
			}
		}
		else if(accion == ACCION_ENVIAR_UNO)
		{
			boolean isExito = enviarOrdenesProxy.isExito();
			
			int status = -1;
			if (isExito) {
				status = enviarOrdenesProxy.getResponse().getStatus();
				if (status == 0) {
					ordenTrabajoBLL.deletePendientesWithEventos(codigo_orden);
					super.processOk(accion);
					finish();
					return;
				}
			}else{
				processError(accion);
				return;
			}
		}
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		showToast("Error enviando ordenes, click en aceptar para volver intentar.");
		super.processError(accion);
	}

	/*@Override
	protected void process() {
		// TODO Auto-generated method stub
		ArrayList<OrdenTrabajoTO> ordenes = ordenTrabajoBLL.listAllWithEventos();
		enviarOrdenesProxy.setOrdenes(ordenes);
		enviarOrdenesProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		boolean isExito = enviarOrdenesProxy.isExito();
		
		int status = -1;
		if (isExito) {
			status = enviarOrdenesProxy.getResponse().getStatus();
			if (status == 0) {
				ordenTrabajoBLL.deleteAllPendientesWithEventos();
				super.processOk();
				finish();
				return;
			}
		}else{
			processError();
			return;
		}
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		showToast("Error enviando ordenes, click en aceptar para volver intentar.");
		super.processError();
	}
	*/
	public void btnCancelar_onclick(View view){
		finish();
	}
	
	public void btnAceptar_onclick(View view){
		processAsync(accion);
	}

	
	
}
