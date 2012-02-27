package pe.lindley.prospector.activity;

import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.util.MapOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import roboguice.activity.RoboMapActivity;
import roboguice.inject.InjectView;

public class SeleccionarDireccionActivity extends RoboMapActivity {
	
	public static final int SELECCIONAR_CLIENTE=0;
	
	
	public static final String LATITUD_KEY="LATITUD";
	public static final String LONGITUD_KEY="LONGITUD";
	public static final String DIRECCION_KEY="DIRECCION";
	
	public static final String LATITUD_SELECCIONADA_KEY="LATITUD_SELECCIONADA";
	public static final String LONGITUD_SELECCIONADA_KEY="LONGITUD_SELECCIONADA";
	
	private double longitudSeleccionada;
	private double latitudSeleccionada;
	double longitud = 0;
	double latitud = 0;
	@InjectView(R.id.mv1) 					MapView 		mapView;
	
	OnTouchListener touch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return onTouchDemo(v,event);
		}
	};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionardireccion_activity);
        
        Intent data = getIntent();
		longitud =  data.getDoubleExtra(LONGITUD_KEY, 0);
		latitud = data.getDoubleExtra(LATITUD_KEY, 0);
		
		
		int latE6 = (int) (latitud * 1E6);
		int lonE6 = (int) (longitud * 1E6);
		
		GeoPoint punto = new GeoPoint(latE6, lonE6);
		MapController controller = mapView.getController();
		controller.animateTo(punto);
		controller.setCenter(punto);
		controller.setZoom(20);
		
		MapOverlay itemizedGoalOverlay = new MapOverlay(getApplicationContext(), 
														punto,
														R.drawable.marker_red_2);
		
		List<Overlay> listOfOverlays = mapView.getOverlays();
		
		listOfOverlays.add(itemizedGoalOverlay);
		
		if (data.getDoubleExtra(LONGITUD_SELECCIONADA_KEY, 0)!=0){
			longitudSeleccionada =  data.getDoubleExtra(LONGITUD_SELECCIONADA_KEY, 0);
			latitudSeleccionada = data.getDoubleExtra(LATITUD_SELECCIONADA_KEY, 0);
			
			
			latE6 = (int) (latitudSeleccionada * 1E6);
			lonE6 = (int) (longitudSeleccionada * 1E6);
			
			punto = new GeoPoint(latE6, lonE6);
			
			MapOverlay itemizedGoalOverlay2 = new MapOverlay(getApplicationContext(), 
					punto,
					R.drawable.marker_green_2);
			
			listOfOverlays.add(itemizedGoalOverlay2);
		}
		
		mapView.setOnTouchListener(touch); 
		
		mapView.invalidate();
		
		
    }
	
	public boolean onTouchDemo(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN){
			
			GeoPoint pGoal = mapView.getProjection().fromPixels((int) event.getX(),(int) event.getY());
			
			latitudSeleccionada = pGoal.getLatitudeE6() /1E6;
			longitudSeleccionada = pGoal.getLongitudeE6() /1E6;
			
			MapOverlay itemizedGoalOverlay = new MapOverlay(getApplicationContext(),
															pGoal,
															R.drawable.marker_green_2);
			
			List<Overlay> listOfOverlays = mapView.getOverlays();
	        if(listOfOverlays.size()>1){
	        	listOfOverlays.remove(1);
	        }
	        
	        MapController controller = mapView.getController();
			controller.animateTo(pGoal);
			
			
			listOfOverlays.add(itemizedGoalOverlay); 
		}
		return super.onTouchEvent(event);
	}
	
	public void btnAceptar_onclick(View view){
		Intent intent= getIntent();
		intent.putExtra(LATITUD_SELECCIONADA_KEY, latitudSeleccionada);
		intent.putExtra(LONGITUD_SELECCIONADA_KEY, longitudSeleccionada);
		setResult(RESULT_OK, intent);
		finish();
	}
	public void btnCancelar_onclick(View view){
		setResult(RESULT_CANCELED);
		finish();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
