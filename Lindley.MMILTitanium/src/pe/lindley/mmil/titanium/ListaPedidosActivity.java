package pe.lindley.mmil.titanium;

import java.util.List;

import pe.lindley.mmil.titanium.to.MapVendedorTO;
import pe.lindley.mmil.titanium.ws.service.ListarMapVendedorProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import net.msonic.lib.MapActivityBase;

public class ListaPedidosActivity extends MapActivityBase {
	
	public static final String CODIGO_CDA_KEY = "CODIGO_CDA";
	public static final String CODIGO_VENDEDOR_KEY = "CODIGO_VENDEDOR";
	public static final String NOMBRE_VENDEDOR_KEY = "NOMBRE_VENDEDOR";
	
	@Inject ListarMapVendedorProxy listarMapVendedorProxy;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectExtra(CODIGO_CDA_KEY) String codigoCDA;
	@InjectExtra(CODIGO_VENDEDOR_KEY) String codigoVendedor;
	@InjectExtra(NOMBRE_VENDEDOR_KEY) String nombreVendedor;
	@InjectView(R.id.mv1) MapView mapView;
	 
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.listapedidos_activity);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.lista_pedidos_activity_title);
	        mActionBar.setSubTitle(nombreVendedor);
	        
	        
	        GeoPoint punto = new GeoPoint((int) (-12.1037315 * 1E6), (int) (-77.0185931 * 1E6));
			MapController controller = mapView.getController();
			controller.animateTo(punto);
			controller.setCenter(punto);
			controller.setZoom(12);
			mapView.setBuiltInZoomControls(true);
			
			processAsync();
	        
	    }
	 
	 @Override
	protected void process() {
		 listarMapVendedorProxy.codigoDeposito=codigoCDA;
		 listarMapVendedorProxy.codigoVendedor=codigoVendedor;
		 listarMapVendedorProxy.execute();
	 }
	
	 
		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			boolean isExito = listarMapVendedorProxy.isExito();

			if (isExito) {
				
				int minLat = Integer.MAX_VALUE;
			    int minLong = Integer.MAX_VALUE;
			    int maxLat = Integer.MIN_VALUE;
			    int maxLong = Integer.MIN_VALUE;
			    
			    int status = listarMapVendedorProxy.getResponse().getStatus();
			    
			    if (status == 0) {
			    	List<MapVendedorTO> vendedores = listarMapVendedorProxy.getResponse().listaMapVendedor;
					List<Overlay> mapOverlays = mapView.getOverlays();
					
					if(vendedores!=null){
						for (MapVendedorTO mapVendedorTO : vendedores) {
							VendedorMapaOverLay punto = new VendedorMapaOverLay(this,
																				mapView,
																				mapVendedorTO,
																				Double.parseDouble(mapVendedorTO.latitudCliente+"") , 
																				Double.parseDouble(mapVendedorTO.longitudCliente+""));
							
							minLat = Math.min(punto.point.getLatitudeE6(), minLat);
					        minLong = Math.min(punto.point.getLongitudeE6(), minLong);
					        maxLat = Math.max(punto.point.getLatitudeE6(), maxLat);
					        maxLong = Math.max(punto.point.getLongitudeE6(), maxLong);	
							mapOverlays.add(punto);
						}
						
						MapController controller = mapView.getController();
						
						controller.zoomToSpan(Math.abs(minLat - maxLat), Math.abs(minLong - maxLong));
						controller.animateTo(new GeoPoint((maxLat + minLat) / 2, (maxLong + minLong) / 2));
			    }
			}
				mapView.invalidate();	
			}
			super.processOk();
		}
		
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private class VendedorMapaOverLay extends Overlay{
		private Point scrnPoint;
		private GeoPoint point;
		private MapVendedorTO vendedorTO;
		private Context ctx;
		private Bitmap marker = null;
		
		private int xCenterOffset = 0;
		private int yCenterOffset = 0;
		
		public VendedorMapaOverLay(Context ctx,MapView m,MapVendedorTO vendedorTO,double latitud,double longitud) {
		  super();
		  this.point = new GeoPoint((int)(latitud * 1E6), (int)(longitud * 1E6));
		  
		  
		  this.vendedorTO = vendedorTO;
		  this.ctx = ctx;		  

		  xCenterOffset = 0;//mapViewWidth/2;
		  yCenterOffset = 0;//mapViewHeight/2;
		  
		}
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		        super.draw(canvas, mapView, shadow);
		 
		    //se traduce el punto geolocalizado a un punto en la pantalla */
		        
		        scrnPoint = mapView.getProjection().toPixels(this.point, null);
		 
		    //se construye un bitmap a partir de la imagen
		        if(vendedorTO.tipoRegistro.compareTo("PED")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_verde);
		        }else  if(vendedorTO.tipoRegistro.compareTo("NV")==0 || vendedorTO.tipoRegistro.compareTo("NVF")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_rojo);
		        }else  if(vendedorTO.tipoRegistro.compareTo("PEN")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_amarrillo);
		        }else{
		        	marker = null;//BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_null);
		        }
		        
		        
		       // se dibuja la imagen del marker
		        if(marker!=null){
		        	canvas.drawBitmap(marker, scrnPoint.x - marker.getWidth() / 2, scrnPoint.y - marker.getHeight() / 2, null);
		        }
		        
		        return true;
		    }
		
		
		
		
		
		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			// TODO Auto-generated method stub
			 boolean tapped = tapOnMe(arg0, arg1);
			 
			if(tapped){
				StringBuffer sb = new StringBuffer();
				String title="";
				
				
				if(vendedorTO.tipoRegistro.compareTo("PED")==0){
					title = "Pedido";
					
					
					sb.append("Cod. Cliente:" );
					sb.append(vendedorTO.codigoCliente + "\n");
					
					sb.append("Nombre:" );
					sb.append(vendedorTO.nombre + "\n");
					
					sb.append("Direcci—n:" );
					sb.append(vendedorTO.direccion + "\n");
					
					sb.append("Fecha Registro:" );
					sb.append(vendedorTO.fechaReg + "\n");
					
					
					sb.append("Imp. Total:" );
					sb.append(String.valueOf(vendedorTO.montoPedido) + "\n");
					
					sb.append("Pedido Referencia:" );
					sb.append(vendedorTO.codigoPedido + "\n");
					
					sb.append("òltimo Registro:" );
					sb.append(vendedorTO.ultimoPedido + "\n");
					
					sb.append("Cantidad Pedidos:" );
					sb.append(String.valueOf(vendedorTO.cantidadPedido) + "\n");
					
					sb.append("Total Cajas F’sicas:" );
					sb.append(vendedorTO.cajaFisicas + "\n");
					
					sb.append("Total Cajas Unitarias:" );
					sb.append(vendedorTO.cajaUnitaria + "\n");
					
					sb.append("Tiempo en Pedidos:" );
					sb.append(vendedorTO.tiempo + "\n");
					
					
		        }else  if(vendedorTO.tipoRegistro.compareTo("NV")==0){
		        	title = "Pedido No Efectuado";
		        	
		        	sb.append("Fecha Registro:" );
					sb.append(vendedorTO.fechaReg + "\n");
					
		        	sb.append("Nombre:" );
					sb.append(vendedorTO.nombre + "\n");
					
					sb.append("Direcci—n:" );
					sb.append(vendedorTO.direccion + "\n");
					
					sb.append("Motivo:" );
					sb.append(vendedorTO.numeroPedido + "\n");
					
					
					
		        }else  if(vendedorTO.tipoRegistro.compareTo("PEN")==0){
		        	
		        	title = "Pedido Pendiente";
		        	
		        	sb.append("Nombre:" );
					sb.append(vendedorTO.nombre + "\n");
					
					sb.append("Dirección:" );
					sb.append(vendedorTO.direccion + "\n");
					
		        }
				
				
				
				
				net.msonic.lib.MessageBox.showSimpleDialog(ctx, title, sb.toString(), "Aceptar", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
	
			
			return true;
			}
			return false;
		}
		
		
		protected boolean tapOnMe(GeoPoint p, MapView m){

			final Projection pr = m.getProjection();
			int minX = xCenterOffset + scrnPoint.x;
			int minY = yCenterOffset + scrnPoint.y;
			if(marker!=null){
				int maxX = minX + marker.getWidth();
				int maxY = minY + marker.getHeight();
	
				Point pt = pr.toPixels(p, null);
	
				if(pt.x >= minX && pt.y >= minY &&
				pt.x <= maxX && pt.y <= maxY){
					return true;
				}
			}

			return false;
			}
	}
}
