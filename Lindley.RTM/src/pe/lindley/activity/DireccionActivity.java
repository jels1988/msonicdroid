package pe.lindley.activity;

import java.util.List;

import pe.lindley.util.MapOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Intent;
import android.os.Bundle;
import roboguice.activity.RoboMapActivity;
import roboguice.inject.InjectView;

public class DireccionActivity extends RoboMapActivity {
	
	public static final String LATITUD_KEY="LATITUD";
	public static final String LONGITUD_KEY="LONGITUD";
	public static final String DIRECCION_KEY="DIRECCION";
	
	private double longitud;
	private double latitud;
	@InjectView(R.id.mv1) 					MapView 		mapView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.direccioncliente_activity);
		
		
		Intent data = getIntent();
		longitud =  data.getDoubleExtra(LONGITUD_KEY, 0);
		latitud = data.getDoubleExtra(LATITUD_KEY, 0);
		
		int latE6 = (int) (latitud * 1E6);
		int lonE6 = (int) (longitud * 1E6);
		
		GeoPoint punto = new GeoPoint(latE6, lonE6);
		MapController controller = mapView.getController();
		controller.animateTo(punto);
		controller.setCenter(punto);
		controller.setZoom(17);
		
		MapOverlay itemizedGoalOverlay = new MapOverlay(getApplicationContext(), 
														punto,
														R.drawable.marker_red_2);
		
		List<Overlay> listOfOverlays = mapView.getOverlays();
		
		listOfOverlays.add(itemizedGoalOverlay); 
	}



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	class MapOverlay extends  com.google.android.maps.Overlay{
		private GeoPoint punto;
		private int icono;
		
		public MapOverlay(GeoPoint punto, int icono) {
			super();
			this.punto = punto;
			this.icono = icono;
		}
		
		@Override
        public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   
 
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(punto, screenPts);
 
            //---add the marker---
           Bitmap bmp = BitmapFactory.decodeResource(getResources(), icono);            

            canvas.drawBitmap(bmp, 	screenPts.x- bmp.getWidth()/2, 
					screenPts.y - bmp.getHeight(), null);  
            return true;
        }
	@Override
	  public boolean onTap(GeoPoint p, MapView mapView) {
	    // Handle tapping on the overlay here
	    return true;
	  }
	}
	*/
}
