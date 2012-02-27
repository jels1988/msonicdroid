package pe.lindley.mmil.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.mmil.to.CdaTO;
import pe.lindley.mmil.to.CoordenadaTO;
import pe.lindley.mmil.ws.service.ListarCdaProxy;
import pe.lindley.util.CdaOverlay;
import pe.lindley.util.MapActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class CdaActivity extends MapActivityBase {
	
	public static boolean CARGADO=false;
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		CARGADO=false;
		super.finish();
	}

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ListarCdaProxy listarCdaProxy;
	@InjectView(R.id.mv1) MapView 		mapView;
	@InjectExtra("codigo_region") String codigo_region;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CARGADO=true;
		processAsync();
		
		setContentView(R.layout.mmil_pais_activity);
		
		 mActionBar.setTitle(R.string.mmil_cdas);
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		
		MapController controller = mapView.getController();
		controller.setZoom(7);
		controller.animateTo(new GeoPoint((int) (-12.1037315 * 1E6), (int) (-77.0185931 * 1E6)));
		
	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		listarCdaProxy.setCodigoRegion("01");
		listarCdaProxy.execute();
	}


	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		if(!listarCdaProxy.isExito()){
			processError();
			return ;
		}
		
		int status = listarCdaProxy.getResponse().getStatus();

		if (status != 0) {
			showToast(listarCdaProxy.getResponse().getDescripcion());
			return;
		}
		
		int r = 0;
        int g = 0;
        int b = 0;
        
        List<CdaTO> cdas = listarCdaProxy.getResponse().getCdas();
        for (CdaTO cdaTO : cdas) {
		
        	r = cdaTO.getR();
			g = cdaTO.getG(); 
			b = cdaTO.getB();
			
			List<GeoPoint> regionCoordinates = new ArrayList<GeoPoint>();
			
			List<CoordenadaTO> coordenas = cdaTO.getCoordenada();
			
			for (CoordenadaTO coordenadaTO : coordenas) {
				int lat = (int)coordenadaTO.getLatitud() * 1000000;
				int lng = (int)coordenadaTO.getLongitud() * 1000000;
				regionCoordinates.add(new GeoPoint(lat,lng));
			}
			
			CdaOverlay x = new CdaOverlay(getApplicationContext(),cdaTO, regionCoordinates, r, g, b);
			
			
			mapView.getOverlays().add(x);
			
		}
        
        mapView.postInvalidate();
		super.processOk();
	}

	@Override
	protected void processError() {
		String message;
		if(listarCdaProxy.getResponse()!=null){
			String error = listarCdaProxy.getResponse().getDescripcion();
			message = error;
		}else{
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}



}
