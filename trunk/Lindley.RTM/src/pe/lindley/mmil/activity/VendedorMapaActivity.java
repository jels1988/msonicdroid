package pe.lindley.mmil.activity;

import java.util.List;
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
import pe.lindley.activity.R;
import pe.lindley.mmil.to.MapVendedorTO;
import pe.lindley.util.MapActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectView;

public class VendedorMapaActivity extends MapActivityBase {
	
	@Inject pe.lindley.mmil.ws.service.ListarMapVendedorProxy listarMapVendedorProxy;
	public static final String CODIGO_CDA= "CODIGO_CDA";
	public static final String CODIGO_VENDEDOR= "CODIGO_VENDEDOR";
	public static final String NOMBRE_VENDEDOR= "NOMBRE_VENDEDOR";
	
	MapView mapView = null;
	
	private String codigo_cda;
	private String nombre_vendedor;
	private int codigo_vendedor;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		codigo_cda = getIntent().getStringExtra(CODIGO_CDA);
		codigo_vendedor = Integer.parseInt(getIntent().getStringExtra(CODIGO_VENDEDOR));
		nombre_vendedor = getIntent().getStringExtra(NOMBRE_VENDEDOR);		
		
		setContentView(R.layout.mmil_vendedormapa_activity);
		mapView = (MapView)findViewById(R.id.mv1);
		
		mActionBar.setTitle(R.string.mostrar_vendedor_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(nombre_vendedor);
		 
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
		// TODO Auto-generated method stub
		listarMapVendedorProxy.setCodigoDeposito(codigo_cda);
		listarMapVendedorProxy.setCodigoVendedor(codigo_vendedor);
		listarMapVendedorProxy.execute();
		super.process();
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
				List<MapVendedorTO> vendedores = listarMapVendedorProxy.getResponse().getListaMapVendedor();
				List<Overlay> mapOverlays = mapView.getOverlays();
				
				if(vendedores!=null){
					for (MapVendedorTO mapVendedorTO : vendedores) {
						VendedorMapaOverLay punto = new VendedorMapaOverLay(this,
																			mapView,
																			mapVendedorTO,
																			Double.parseDouble(mapVendedorTO.getLatitudCliente()+"") , 
																			Double.parseDouble(mapVendedorTO.getLongitudCliente()+""));
						
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
	

	public class VendedorMapaOverLay extends Overlay{

		private Point scrnPoint;
		private GeoPoint point;
		private MapVendedorTO vendedorTO;
		private Context ctx;
		private Bitmap marker = null;
        
		
		//private int mapViewWidth = 0;
		//private int mapViewHeight = 0;
		private int xCenterOffset = 0;
		private int yCenterOffset = 0;
		
		/* El constructor recibe el punto donde se dibujar‡ el marker */
		public VendedorMapaOverLay(Context ctx,MapView m,MapVendedorTO vendedorTO,double latitud,double longitud) {
		  super();
		  this.point = new GeoPoint((int)(latitud * 1E6), (int)(longitud * 1E6));
		  
		  
		  this.vendedorTO = vendedorTO;
		  this.ctx = ctx;		  
		  
		  //mapViewHeight = m.getHeight();
		  //mapViewWidth  = m.getWidth();

		  xCenterOffset = 0;//mapViewWidth/2;
		  yCenterOffset = 0;//mapViewHeight/2;
		  
		}
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		        super.draw(canvas, mapView, shadow);
		 
		    //se traduce el punto geolocalizado a un punto en la pantalla */
		        
		        scrnPoint = mapView.getProjection().toPixels(this.point, null);
		 
		    //se construye un bitmap a partir de la imagen
		        
		        if(vendedorTO.getTipoRegistro().compareTo("PED")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_verde);
		        }else  if(vendedorTO.getTipoRegistro().compareTo("NV")==0 || vendedorTO.getTipoRegistro().compareTo("NVF")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_rojo);
		        }else  if(vendedorTO.getTipoRegistro().compareTo("PEN")==0){
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_amarrillo);
		        }else{
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_null);
		        }
		        
		        
		       // se dibuja la imagen del marker
		        canvas.drawBitmap(marker, scrnPoint.x - marker.getWidth() / 2, scrnPoint.y - marker.getHeight() / 2, null);
		 
		        return true;
		    }

		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			// TODO Auto-generated method stub
			 boolean tapped = tapOnMe(arg0, arg1);
			 
			if(tapped){
				StringBuffer sb = new StringBuffer();
				String title="";
				
				
				if(vendedorTO.getTipoRegistro().compareTo("PED")==0){
					title = "Pedido";
					
					
					sb.append("Cod. Cliente:" );
					sb.append(vendedorTO.getCodigoCliente() + "\n");
					
					sb.append("Nombre:" );
					sb.append(vendedorTO.getNombre() + "\n");
					
					sb.append("Dirección:" );
					sb.append(vendedorTO.getDireccion() + "\n");
					
					sb.append("Fecha Registro:" );
					sb.append(vendedorTO.getFechaReg() + "\n");
					
					
					sb.append("Imp. Total:" );
					sb.append(String.valueOf(vendedorTO.getMontoPedido()) + "\n");
					
					sb.append("Pedido Referencia:" );
					sb.append(vendedorTO.getCodigoPedido() + "\n");
					
					sb.append("Último Registro:" );
					sb.append(vendedorTO.getUltimoPedido() + "\n");
					
					sb.append("Cantidad Pedidos:" );
					sb.append(String.valueOf(vendedorTO.getCantidadPedido()) + "\n");
					
					sb.append("Total Cajas Físicas:" );
					sb.append(vendedorTO.getCajaFisicas() + "\n");
					
					sb.append("Total Cajas Unitarias:" );
					sb.append(vendedorTO.getCajaUnitaria() + "\n");
					
					sb.append("Tiempo en Pedidos:" );
					sb.append(vendedorTO.getTiempo() + "\n");
					
					
		        }else  if(vendedorTO.getTipoRegistro().compareTo("NV")==0){
		        	title = "Pedido No Efectuado";
		        	
		        	sb.append("Fecha Registro:" );
					sb.append(vendedorTO.getFechaReg() + "\n");
					
		        	sb.append("Nombre:" );
					sb.append(vendedorTO.getNombre() + "\n");
					
					sb.append("Dirección:" );
					sb.append(vendedorTO.getDireccion() + "\n");
					
					sb.append("Motivo:" );
					sb.append(vendedorTO.getNumeroPedido() + "\n");
					
					
					
		        }else  if(vendedorTO.getTipoRegistro().compareTo("PEN")==0){
		        	
		        	title = "Pedido Pendiente";
		        	
		        	sb.append("Nombre:" );
					sb.append(vendedorTO.getNombre() + "\n");
					
					sb.append("Dirección:" );
					sb.append(vendedorTO.getDireccion() + "\n");
					
		        }
				
				
				
				
				MessageBox.showSimpleDialog(ctx, title, sb.toString(), "Aceptar", new OnClickListener() {
					
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
			int maxX = minX + marker.getWidth();
			int maxY = minY + marker.getHeight();

			Point pt = pr.toPixels(p, null);

			if(pt.x >= minX && pt.y >= minY &&
			pt.x <= maxX && pt.y <= maxY){
			return true;
			}

			return false;
			}
		
		
	}
}


