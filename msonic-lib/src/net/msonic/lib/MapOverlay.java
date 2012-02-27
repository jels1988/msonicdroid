package net.msonic.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class MapOverlay extends com.google.android.maps.Overlay {
	
	
	private GeoPoint punto;
	private int icono;
	private Context context;
	
	public MapOverlay(Context context,GeoPoint punto, int icono) {
		super();
		this.punto = punto;
		this.icono = icono;
		this.context = context;
	}
	
	@Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point screenPts = new Point();
        mapView.getProjection().toPixels(punto, screenPts);
        
        //---add the marker---
       Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), icono);            

        canvas.drawBitmap(bmp, 	screenPts.x- bmp.getWidth()/2, 
				screenPts.y - bmp.getHeight(),null);  
        return true;
    }
}
