package pe.lindley.activity;

import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.prospector.activity.ConsultarClienteActivity;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class MenuInicioActivity extends ActivityBase {

	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.imgProspector)  	ImageView 	imgProspector;
	@InjectView(R.id.imgOm)  			ImageView 	imgOm;
	
	@InjectView(R.id.txtPropspector)  	TextView 	txtPropspector;
	@InjectView(R.id.txtOM)  			TextView 	txtOM;
	
	@InjectView(R.id.imgRed)  			ImageView 	imgRED;
	@InjectView(R.id.txtRed)  			TextView 	txtRED;
	
	@InjectView(R.id.imgMultimedia)  			ImageView 	imgMultimedia;
	@InjectView(R.id.txtMultimedia)  			TextView 	txtMultimedia;
	
	
	
	@InjectView(R.id.imgFicha) ImageView imgFicha;
	@InjectView(R.id.txtFicha) TextView txtFicha;
	
	
	@InjectView(R.id.imgMMil) ImageView imgMMIL;
	@InjectView(R.id.txtMil) TextView txtMMIL;
	
	@InjectView(R.id.imgVentaCero) ImageView imgVentaCero;
	@InjectView(R.id.txtVentaCero) TextView txtVentaCero;
	
	
	@InjectView(R.id.imgSacc) ImageView imgSacc;
	@InjectView(R.id.txtSACC) TextView txtSACC;
	

	@InjectView(R.id.imgPtoInteres)	ImageView imgPtoInteres;
	@InjectView(R.id.txtPtoInteres)	TextView txtPtoInteres;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.menuinicio_activity);
		mActionBar.setTitle(R.string.app_name);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
	    
	   
	   
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		UsuarioTO usuarioTO =  application.getUsuarioTO();
		
		boolean permisoProspector = usuarioTO.getModuloPermisos().containsKey("3"); //PROPESCTOR
		boolean permisoOM = usuarioTO.getModuloPermisos().containsKey("4"); //PROPESCTOR
		boolean permisoRED = usuarioTO.getModuloPermisos().containsKey("5"); //PROPESCTOR
		boolean permisoMultimedia = usuarioTO.getModuloPermisos().containsKey("6"); //PROPESCTOR
		boolean permisoMMIL = usuarioTO.getModuloPermisos().containsKey("7"); //MMIL
		boolean permisoFICHA = usuarioTO.getModuloPermisos().containsKey("8"); //FICHA
		boolean permisoVENTACERO = usuarioTO.getModuloPermisos().containsKey("9"); //VENTA CERO
		boolean permisoSACC = usuarioTO.getModuloPermisos().containsKey("10"); //VENTA CERO
		boolean puntosInteres = usuarioTO.getModuloPermisos().containsKey("11"); //VENTA CERO
		
		
		
		if(!permisoProspector){
			imgProspector.setVisibility(View.GONE);
			txtPropspector.setVisibility(View.GONE);
		}
		
		if(!permisoOM){
			imgOm.setVisibility(View.GONE);
			txtOM.setVisibility(View.GONE);
		}
		
		if(!permisoRED){
			imgRED.setVisibility(View.GONE);
			txtRED.setVisibility(View.GONE);
		}
		
		if(!permisoMultimedia){
			imgMultimedia.setVisibility(View.GONE);
			txtMultimedia.setVisibility(View.GONE);
		}
		
		if(!permisoMMIL){
			imgMMIL.setVisibility(View.GONE);
			txtMMIL.setVisibility(View.GONE);
		}
		
		
		if(!permisoFICHA){
			imgFicha.setVisibility(View.GONE);
			txtFicha.setVisibility(View.GONE);
		}
		
		if(!permisoVENTACERO){
			imgVentaCero.setVisibility(View.GONE);
			txtVentaCero.setVisibility(View.GONE);
		}
		
		
		if(!permisoSACC){
			imgSacc.setVisibility(View.GONE);
			txtSACC.setVisibility(View.GONE);
		}
		
		if(!puntosInteres){
			 
		    imgPtoInteres.setVisibility(View.GONE);
			txtPtoInteres.setVisibility(View.GONE);
			
			
		}
		
		
	}
	
	public void btnDesarrollador_onclick(View view){
		Intent intent = new Intent(this,ConsultarClienteActivity.class);
		startActivity(intent);
	}
	
	public void btnOm_onclick(View view){
		Intent intent = new Intent(this,pe.lindley.om.activity.ConsultarClienteOMActivity.class);
		startActivity(intent);
	}
	
	public void btnRed_onclick(View view){
		Intent intent = new Intent(this,pe.lindley.red.activity.ConsultarClienteActivity.class);
		startActivity(intent);
	}
	
	public void btnMultimedia_onclick(View view){
		Intent intent = new Intent(this,pe.lindley.exmedia.activity.ListarFilesMultiActivity.class);
		startActivity(intent);
	}
	
	
	public  void btnFicha_onclick(View view){
		Intent intent = new Intent(this,pe.lindley.ficha.activity.ConsultarClienteActivity.class);
		startActivity(intent);
	}
	
	public void btnMMil_onclick(View view){
		Intent intent = new Intent(this,pe.lindley.mmil.activity.MMILPaisActivity.class);
		startActivity(intent);
	}

	public void btnVentaCero_onclick(View view){
		
		
		Intent intent = new Intent(this,pe.lindley.ventacero.activity.VentaCeroActivity.class);
		//Intent intent = new Intent(this,pe.lindley.ventacero.activity.DescargarParametrosActivity.class);
		startActivity(intent);
	}
	
	public void btnSacc_onclick(View view){		
		Intent intent = new Intent(this,pe.lindley.sacc.activity.ConsultarClienteActivity.class);
		startActivity(intent);
	}
	
	public void btnPuntosInteres_onclick(View view) {
		Intent intent = new Intent(this,pe.lindley.puntointeres.activity.ConsultarClienteActivity.class);
		startActivity(intent);
	}

}
