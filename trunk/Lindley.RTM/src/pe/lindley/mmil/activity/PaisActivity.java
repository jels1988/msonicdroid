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
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.inject.Inject;

import roboguice.inject.InjectView;


public class PaisActivity extends MapActivityBase {

	@Inject ListarRegionProxy listarRegionProxy;
	
	@InjectView(R.id.mv1) 	MapView 		mapView;
	@InjectView(R.id.zoom) 	LinearLayout zoomLayout;
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		listarRegionProxy.setPais("01");
		listarRegionProxy.execute();
	}





	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		int r = 255;
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
			
			RegionOverlay x = new RegionOverlay(this,regionTO,regionCoordinates, r, g, b);
			
			
			mapView.getOverlays().add(x);
			
		}
		
		mapView.postInvalidate();
		super.processOk();
	}





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

/*		 mapView = (MapView) findViewById(R.id.mv1);    
		 mapView.displayZoomControls(true);
	        mapView.setBuiltInZoomControls(true);*/
		processAsync();
		
		setContentView(R.layout.mmil_pais_activity);
		View zoomView = mapView.getZoomControls(); 
		zoomLayout.addView(zoomView, 
	            new LinearLayout.LayoutParams(
	                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 
	                android.view.ViewGroup.LayoutParams.WRAP_CONTENT)); 
	        mapView.displayZoomControls(true);
	        mapView.setBuiltInZoomControls(true);
	        
		
		
		MapController controller = mapView.getController();
		controller.setZoom(7);
		controller.animateTo(new GeoPoint((int) (-12.1037315 * 1E6), (int) (-77.0185931 * 1E6)));
		
		
	}


	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        MapController mc = mapView.getController(); 
        switch (keyCode) 
        {
            case KeyEvent.KEYCODE_3:
                mc.zoomIn();
                break;
            case KeyEvent.KEYCODE_1:
                mc.zoomOut();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }    



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
