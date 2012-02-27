package pe.lindley.util;

import java.util.List;

import pe.lindley.mmil.activity.CdaActivity;
import pe.lindley.mmil.to.RegionTO;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RegionOverlay extends Overlay {

	
	private RegionTO regionTO;
	
	private Paint paint;
    private GeoPoint initialPoint;
    private Point oldPoint, newPoint, point;
    private Context context;
    /**
     * Coordenas a utilizar para dibujar un poligon en el mapa.
     */
    private List<GeoPoint> coordinates;

    /**
     * Contador de coordenadas
     */
    private int coordinatesCount;

    /**
     * @param coordinates
     *            coordenadas a utilizar para dibujar un poligono sobre el mapa.
     */
    public RegionOverlay(Context context,RegionTO regionTO,List<GeoPoint> coordinates, int r, int g, int b)
    {
    		this.regionTO = regionTO;
    		this.context = context;
            this.coordinates = coordinates;
            this.coordinatesCount = coordinates.size();
            this.initialPoint = coordinates.get(0);

            oldPoint = new Point(0, 0);
            newPoint = new Point(0, 0);
            point = new Point(0, 0);
            paint = new Paint();
            paint.setARGB(255, r, g, b);
    }

    /**
     * @see com.google.android.maps.Overlay#draw(android.graphics.Canvas,
     *      com.google.android.maps.MapView, boolean)
     */
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
            mapView.getProjection().toPixels(initialPoint, point);

            int xoff = 0;
            int yoff = 0;
            int oldx = point.x;
            int oldy = point.y;
            int newx = oldx + xoff;
            int newy = oldy + yoff;

            paint.setAntiAlias(false);

            for (int i = 0; i < coordinatesCount - 1; i++)
            {
                    // Se define el color y grosor de la linea
                    paint.setStrokeWidth(6);

                    // Se transforma los GeoPoints a pixeles
                    mapView.getProjection().toPixels(coordinates.get(i), oldPoint);
                    oldx = oldPoint.x;
                    oldy = oldPoint.y;

                    mapView.getProjection().toPixels(coordinates.get(i + 1), newPoint);
                    newx = newPoint.x;
                    newy = newPoint.y;

                    // Se dibuja el segmento en el mapa
                    canvas.drawLine(oldx, oldy, newx, newy, paint);
            }
    }

    
	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		// TODO Auto-generated method stub
		

	
		
		
		
		return false;
	}
	
	Object obj = new Object();
	
	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {
		
		
		
		switch(e.getAction())
		{
		  case MotionEvent.ACTION_DOWN:
			  synchronized (obj) {
					if(!CdaActivity.CARGADO){
						
						CdaActivity.CARGADO=true;
							Intent intent = new Intent(context,CdaActivity.class);
							intent.putExtra("codigo_region", regionTO.getCodigo());
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							 context.startActivity(intent);
					}
				}
		    break;
		  case MotionEvent.ACTION_MOVE:
		    
		    break;
		  case MotionEvent.ACTION_UP:
		    
		    break;
		}
		
		return false;
	}
    
	
	
	
	
    
}
