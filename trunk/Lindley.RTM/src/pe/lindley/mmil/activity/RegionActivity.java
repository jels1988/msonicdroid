package pe.lindley.mmil.activity;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.mmil.to.CoordenadaTO;
import pe.lindley.mmil.to.RegionTO;
import pe.lindley.mmil.ws.service.ListarRegionProxy;
import pe.lindley.util.MapActivityBase;
import pe.lindley.util.RegionOverlay;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.inject.Inject;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

public class RegionActivity extends MapActivityBase {

	@Inject ListarRegionProxy listarRegionProxy;
	
	@InjectView(R.id.mv1) 	MapView 		mapView;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		listarRegionProxy.setPais("01");
		listarRegionProxy.execute();
	}





	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		if(!listarRegionProxy.isExito()){
			processError();
			return ;
		}
		
		int status = listarRegionProxy.getResponse().getStatus();

		if (status != 0) {
			showToast(listarRegionProxy.getResponse().getDescripcion());
			return;
		}
		
		int r = 0;
        int g = 0;
        int b = 0;
        
		List<RegionTO> regiones = listarRegionProxy.getResponse().getRegiones();
		for (RegionTO regionTO : regiones) {
			
			r = regionTO.getR();
			g = regionTO.getG(); 
			b = regionTO.getB();
			
			List<GeoPoint> regionCoordinates = new ArrayList<GeoPoint>();
			
			List<CoordenadaTO> coordenas = regionTO.getCoordenada();
			for (CoordenadaTO coordenadaTO : coordenas) {
				int lat = (int)coordenadaTO.getLatitud() * 1000000;
				int lng = (int)coordenadaTO.getLongitud() * 1000000;
				regionCoordinates.add(new GeoPoint(lat,lng));
			}
			
			
			RegionOverlay x = new RegionOverlay(getApplicationContext(),regionTO, regionCoordinates, r, g, b);
			
			
			mapView.getOverlays().add(x);
			
		}
		
		mapView.postInvalidate();
		super.processOk();
	}





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
			
		processAsync();
		
		setContentView(R.layout.mmil_pais_activity);
		 mActionBar.setTitle(R.string.mmil_regiones);
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		
		
		
		MapController controller = mapView.getController();
		controller.setZoom(7);
		controller.animateTo(new GeoPoint((int) (-12.1037315 * 1E6), (int) (-77.0185931 * 1E6)));
		
		
	}


	
	



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
