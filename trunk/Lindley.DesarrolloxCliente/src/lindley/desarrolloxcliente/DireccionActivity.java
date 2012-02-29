package lindley.desarrolloxcliente;

import java.util.List;

import net.msonic.lib.MapOverlay;
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
}
