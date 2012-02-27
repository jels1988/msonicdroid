package pe.lindley.puntointeres.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.puntointeres.to.PuntoInteresTO;
import pe.lindley.puntointeres.ws.service.EliminarPuntoInteresProxy;
import pe.lindley.puntointeres.ws.service.ObtenerPuntoInteresProxy;
import pe.lindley.util.MapActivityBase;
import pe.lindley.util.MapOverlay;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class PuntosInteresMapActivity extends MapActivityBase {
	
	public static final int ACCION_ELIMINAR = 1;
	
	@InjectView(R.id.mv1) 					MapView 		mapView;
	@Inject ObtenerPuntoInteresProxy 	obtenerPuntoInteresProxy;
	@Inject EliminarPuntoInteresProxy 	eliminarPuntoInteresProxy;
	
	@InjectResource(R.string.punto_eliminar_ok) String punto_eliminar_ok;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	public ClienteResumenTO cliente;
	public UsuarioTO user;
	public static String codigoPunto = "";
	
	OnTouchListener touch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return onTouchDemo(v,event);
		}
	};
	private double longitudSeleccionada;
	private double latitudSeleccionada;
	private static double longitudCliente;
	private static double latitudCliente;
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.puntointeres_menu_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.puntointeresAgregar: {
			showToast("Toque la ubicación en el mapa.");
			mapView.setOnTouchListener(touch);
			return true;
		}
		}
		return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		cliente = application.getClienteTO();
		user = application.getUsuarioTO();
		longitudCliente = cliente.getLongitud();
		latitudCliente = cliente.getLatitud();
		setContentView(R.layout.puntointeres_map_activity);
		
		mActionBar.setTitle(R.string.puntointeres_map_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(cliente.getRazonSocial());
		 
        GeoPoint punto = new GeoPoint((int) (-12.1037315 * 1E6), (int) (-77.0185931 * 1E6));
		MapController controller = mapView.getController();
		controller.animateTo(punto);
		controller.setCenter(punto);
		controller.setZoom(12);
		mapView.setBuiltInZoomControls(true);
		//mapView.setOnTouchListener(touch); 
		mapView.invalidate();
	}
	
	public boolean onTouchDemo(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			long now = System.currentTimeMillis();
			System.out.println(now);
			if(now>5000)
			{
				GeoPoint pGoal = mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY());

				latitudSeleccionada = pGoal.getLatitudeE6() / 1E6;
				longitudSeleccionada = pGoal.getLongitudeE6() / 1E6;

				MapOverlay itemizedGoalOverlay = new MapOverlay(getApplicationContext(), pGoal, R.drawable.marker_green_2);

				final List<Overlay> listOfOverlays = mapView.getOverlays();
				if (listOfOverlays.size() > 1) {
					listOfOverlays.remove(1);
				}

				MapController controller = mapView.getController();
				controller.animateTo(pGoal);

				listOfOverlays.add(itemizedGoalOverlay);

				MessageBox.showConfirmDialog(this, "Nuevo Punto Interes",
						"Desea agregar un punto de interes?", "Agregar",
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								listOfOverlays.remove(listOfOverlays.size() - 1);
								Intent nuevoPuntoInteres = new Intent(PuntosInteresMapActivity.this,NuevoPuntoInteresActivity.class);
								nuevoPuntoInteres.putExtra(NuevoPuntoInteresActivity.LATITUD, latitudSeleccionada);
								nuevoPuntoInteres.putExtra(NuevoPuntoInteresActivity.LONGITUD, longitudSeleccionada);
								startActivity(nuevoPuntoInteres);
							}
						}, "Cancelar", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								listOfOverlays.remove(listOfOverlays.size() - 1);
							}
						});
			}
		}
		mapView.setOnTouchListener(null); 
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		processAsync();
		super.onStart();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		obtenerPuntoInteresProxy.setCodigo(cliente.getCodigoCliente());
		obtenerPuntoInteresProxy.execute();
		super.process();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerPuntoInteresProxy.isExito();

		if (isExito) {
			
			List<Overlay> mapOverlays = mapView.getOverlays();
			
			int minLat = Integer.MAX_VALUE;
		    int minLong = Integer.MAX_VALUE;
		    int maxLat = Integer.MIN_VALUE;
		    int maxLong = Integer.MIN_VALUE;
		    
			int status = obtenerPuntoInteresProxy.getResponse().getStatus();
			if (status == 0) {
				List<PuntoInteresTO> puntosInteres = obtenerPuntoInteresProxy.getResponse().getListarPtosInteres();
				
				
				if(puntosInteres!=null){
					for (PuntoInteresTO puntoInteresTO : puntosInteres) {
						PuntoInteresMapaOverLay punto = new PuntoInteresMapaOverLay(this,
								mapView,
								puntoInteresTO,
								puntoInteresTO.getLatitudDec(), 
								puntoInteresTO.getLongitudDec());
						
						minLat = Math.min(punto.point.getLatitudeE6(), minLat);
				        minLong = Math.min(punto.point.getLongitudeE6(), minLong);
				        maxLat = Math.max(punto.point.getLatitudeE6(), maxLat);
				        maxLong = Math.max(punto.point.getLongitudeE6(), maxLong);	
						mapOverlays.add(punto);
					}
					
					PuntoInteresTO puntoInteresTemp = new PuntoInteresTO();
					puntoInteresTemp.setCodCliente(cliente.getCodigoCliente());
					puntoInteresTemp.setNombre(cliente.getRazonSocial());
					puntoInteresTemp.setDireccion(cliente.getDireccion());
					puntoInteresTemp.setLatitudDec(cliente.getLatitud());
					puntoInteresTemp.setLongitudDec(cliente.getLongitud());
					PuntoInteresMapaOverLay punto = new PuntoInteresMapaOverLay(this,
							mapView,
							puntoInteresTemp,
							puntoInteresTemp.getLatitudDec(), 
							puntoInteresTemp.getLongitudDec());
					
					minLat = Math.min(punto.point.getLatitudeE6(), minLat);
			        minLong = Math.min(punto.point.getLongitudeE6(), minLong);
			        maxLat = Math.max(punto.point.getLatitudeE6(), maxLat);
			        maxLong = Math.max(punto.point.getLongitudeE6(), maxLong);	
					mapOverlays.add(punto);
					
					MapController controller = mapView.getController();
					
					controller.zoomToSpan(Math.abs(minLat - maxLat), Math.abs(minLong - maxLong));
					controller.animateTo(new GeoPoint((maxLat + minLat) / 2, (maxLong + minLong) / 2));		    
				}
				else
				{
					PuntoInteresTO puntoInteresTemp = new PuntoInteresTO();
					puntoInteresTemp.setCodCliente(cliente.getCodigoCliente());
					puntoInteresTemp.setNombre(cliente.getRazonSocial());
					puntoInteresTemp.setDireccion(cliente.getDireccion());
					puntoInteresTemp.setLatitudDec(cliente.getLatitud());
					puntoInteresTemp.setLongitudDec(cliente.getLongitud());
					PuntoInteresMapaOverLay punto = new PuntoInteresMapaOverLay(this,
							mapView,
							puntoInteresTemp,
							puntoInteresTemp.getLatitudDec(), 
							puntoInteresTemp.getLongitudDec());
					
					minLat = Math.min(punto.point.getLatitudeE6(), minLat);
			        minLong = Math.min(punto.point.getLongitudeE6(), minLong);
			        maxLat = Math.max(punto.point.getLatitudeE6(), maxLat);
			        maxLong = Math.max(punto.point.getLongitudeE6(), maxLong);	
					mapOverlays.add(punto);
					
					MapController controller = mapView.getController();
					
					controller.zoomToSpan(Math.abs(minLat - maxLat), Math.abs(minLong - maxLong));
					controller.animateTo(new GeoPoint((maxLat + minLat) / 2, (maxLong + minLong) / 2));
				}
			}
			else
			{
				PuntoInteresTO puntoInteresTemp = new PuntoInteresTO();
				puntoInteresTemp.setCodCliente(cliente.getCodigoCliente());
				puntoInteresTemp.setNombre(cliente.getRazonSocial());
				puntoInteresTemp.setDireccion(cliente.getDireccion());
				puntoInteresTemp.setLatitudDec(cliente.getLatitud());
				puntoInteresTemp.setLongitudDec(cliente.getLongitud());
				PuntoInteresMapaOverLay punto = new PuntoInteresMapaOverLay(this,
						mapView,
						puntoInteresTemp,
						puntoInteresTemp.getLatitudDec(), 
						puntoInteresTemp.getLongitudDec());
				
				minLat = Math.min(punto.point.getLatitudeE6(), minLat);
		        minLong = Math.min(punto.point.getLongitudeE6(), minLong);
		        maxLat = Math.max(punto.point.getLatitudeE6(), maxLat);
		        maxLong = Math.max(punto.point.getLongitudeE6(), maxLong);	
				mapOverlays.add(punto);
				
				MapController controller = mapView.getController();
				
				controller.zoomToSpan(Math.abs(minLat - maxLat), Math.abs(minLong - maxLong));
				controller.animateTo(new GeoPoint((maxLat + minLat) / 2, (maxLong + minLong) / 2));
			}
			mapView.invalidate();	
		}
		super.processOk();
	}

	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ELIMINAR)
		{
			eliminarPuntoInteresProxy.setCodCliente(cliente.getCodigoCliente());
			eliminarPuntoInteresProxy.setUsuario(user.getCodigoSap());
			eliminarPuntoInteresProxy.setCodPunto(codigoPunto);
			eliminarPuntoInteresProxy.execute();
		}
		super.process(accion);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ELIMINAR)
		{
			boolean isExito = eliminarPuntoInteresProxy.isExito();
			
			if (isExito) {
				int status = eliminarPuntoInteresProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(punto_eliminar_ok);
					finish();
				}
				else  {
					showToast(eliminarPuntoInteresProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError();
			}		
		}
		super.processOk(accion);
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ELIMINAR){
			showToast(error_generico_process);
		}
		super.processError(accion);
	}

	
	public class PuntoInteresMapaOverLay extends Overlay{

		private Point scrnPoint;
		private GeoPoint point;
		private PuntoInteresTO puntoInteresTO;
		private Context ctx;
		private Bitmap marker = null;
        
		private int xCenterOffset = 0;
		private int yCenterOffset = 0;
		
		/* El constructor recibe el punto donde se dibujar‡ el marker */
		public PuntoInteresMapaOverLay(Context ctx,MapView m,PuntoInteresTO puntoInteresTO,double latitud,double longitud) {
		  super();
		  this.point = new GeoPoint((int)(latitud * 1E6), (int)(longitud * 1E6));
		  
		  
		  this.puntoInteresTO = puntoInteresTO;
		  this.ctx = ctx;		  

		  xCenterOffset = 0;
		  yCenterOffset = 0;
		  
		}
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		        super.draw(canvas, mapView, shadow);
		 
		    //se traduce el punto geolocalizado a un punto en la pantalla */
		        
		        scrnPoint = mapView.getProjection().toPixels(this.point, null);
		 
		    //se construye un bitmap a partir de la imagen
		        if(cliente.getRazonSocial().equals(puntoInteresTO.getNombre()))
		        {
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.marker_red_2);
		        }
		        else
		        {
		        	marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon_verde);
		        }

		    // se dibuja la imagen del marker
		        canvas.drawBitmap(marker, scrnPoint.x - marker.getWidth() / 2, scrnPoint.y - marker.getHeight() / 2, null);
		 
		        return true;
		    }

		@Override
		public boolean onTap(final GeoPoint arg0, MapView arg1) {
			// TODO Auto-generated method stub
			 boolean tapped = tapOnMe(arg0, arg1);
			if(tapped){
				StringBuffer sb = new StringBuffer();
				String title="";
				if(cliente.getRazonSocial().equals(puntoInteresTO.getNombre()))
				//if(longitudCliente == puntoInteresTO.getLongitudDec() && latitudCliente == puntoInteresTO.getLatitudDec())
		        {
					title = puntoInteresTO.getNombre();
		        	
		        	sb.append("Dirección:" );
					sb.append(puntoInteresTO.getDireccion() + "\n");
					
					MessageBox.showSimpleDialog(ctx, title, sb.toString(), "Confirmar",new OnClickListener() {							
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
								}
							});
		        }
		        //else if(cliente.getCodigoCliente().equals(puntoInteresTO.getCodCliente()))
				else
		        {
		        	title = puntoInteresTO.getNombre();
		        	
		        	sb.append("Dirección:" );
					sb.append(puntoInteresTO.getDireccion() + "\n");
					
		        	sb.append("Ubigeo:" );
					sb.append(puntoInteresTO.getCodUbigeo() + "\n");
					
					sb.append("Descripción:" );
					sb.append(puntoInteresTO.getDescripcion() + "\n");
										
					Builder builder = new AlertDialog.Builder(ctx);
				    builder.setTitle(title);
				    builder
				            .setMessage(sb.toString());
				    builder.setPositiveButton("Actualizar",
				            new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                	Intent actualizarPuntoInteres = new Intent(ctx,ActualizarPuntoInteresActivity.class);
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.LATITUD, puntoInteresTO.getLatitudDec());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.LONGITUD, puntoInteresTO.getLongitudDec());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.CODIGO_PUNTO, puntoInteresTO.getCodPunto());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.CODIGO_GIRO, puntoInteresTO.getCodGiro());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.TIPO_GIRO, puntoInteresTO.getTipoGiro());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.CODIGO_UBIGEO, puntoInteresTO.getCodUbigeo());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.DESCRIPCION, puntoInteresTO.getDescripcion());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.DIRECCION, puntoInteresTO.getDireccion());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.SLATITUD, puntoInteresTO.getLatitud());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.SLONGITUD, puntoInteresTO.getLongitud());
									actualizarPuntoInteres.putExtra(ActualizarPuntoInteresActivity.NOMBRE, puntoInteresTO.getNombre());
									ctx.startActivity(actualizarPuntoInteres);
				                }
				            });

				    builder.setNeutralButton("Eliminar",
				            new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                	codigoPunto = puntoInteresTO.getCodPunto();
				                	processAsync(ACCION_ELIMINAR);
				                }
				            });

				    builder.setNegativeButton("Cancelar",
				            new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                    dialog.cancel();
				                }
				            });
				    
				    builder.show();

		       }
		        				
				return true;
			}	
			/*else
			{
				MessageBox.showConfirmDialog(ctx, 
						"Nuevo Punto Interes", 
						"Desea agregar un punto de interes?", 
						"Agregar",
						new OnClickListener() {							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent nuevoPuntoInteres = new Intent(ctx,NuevoPuntoInteresActivity.class);
								nuevoPuntoInteres.putExtra(NuevoPuntoInteresActivity.LATITUD, arg0.getLatitudeE6()/ 1E6);
								nuevoPuntoInteres.putExtra(NuevoPuntoInteresActivity.LONGITUD, arg0.getLongitudeE6()/ 1E6);
								ctx.startActivity(nuevoPuntoInteres);
							}
						},
						"Cancelar",
						null);
				return true;
			}*/				
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
