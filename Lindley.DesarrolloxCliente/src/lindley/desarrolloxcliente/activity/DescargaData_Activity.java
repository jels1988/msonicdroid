package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectResource;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.to.upload.ProcesoInfoTO;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProductoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAccionesTradeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarAceleradorProxy;
import lindley.desarrolloxcliente.ws.service.DescargarArticuloCanjeProxy;
import lindley.desarrolloxcliente.ws.service.DescargarClienteProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionOportunidadProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarEvaluacionSKUProxy;
import lindley.desarrolloxcliente.ws.service.DescargarMotivoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPosicionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPresentacionProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProductosProxy;
import lindley.desarrolloxcliente.ws.service.DescargarOportunidadesProxy;
import lindley.desarrolloxcliente.ws.service.DescargarProfitProxy;
import lindley.desarrolloxcliente.ws.service.DescargarPuntoProxy;
import lindley.desarrolloxcliente.ws.service.DescargarSkuProxy;
import lindley.desarrolloxcliente.ws.service.DescargarTipoActivoProxy;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;

import net.msonic.lib.MessageBox;
import net.msonic.lib.sherlock.ListActivityBase;

public class DescargaData_Activity extends ListActivityBase {

	public final static int DIFERENCIA=100;
	
	public static final int DESCARGAR_PRODUCTO=0;
	public static final int DESCARGAR_OPORTUNIDAD=1;
	public static final int DESCARGAR_SKU=2;
	public static final int DESCARGAR_ACCIONESTRADE=3;
	public static final int DESCARGAR_ACCIONESTRADEPRODUCTO=4;
	public static final int DESCARGAR_CLIENTE=5;
	public static final int DESCARGAR_POSICION=6;
	public static final int DESCARGAR_PRESENTACION=7;
	public static final int DESCARGAR_PUNTO=8;
	public static final int DESCARGAR_EVALUACION=9;
	public static final int DESCARGAR_EVALUACION_OPORTUNIDAD=10;
	public static final int DESCARGAR_EVALUACION_POSICION=11;
	public static final int DESCARGAR_EVALUACION_PRESENTACION=12;
	public static final int DESCARGAR_EVALUACION_SKU=13;
	public static final int DESCARGAR_EVALUACION_POSICION_COMPROMISO=14;
	public static final int DESCARGAR_PROFIT=15;
	public static final int DESCARGAR_MOTIVO=16;
	public static final int DESCARGAR_ACELERADOR=17;
	public static final int DESCARGAR_ARTICULO_CANJE=18;
	public static final int DESCARGAR_TIPO_ACTIVO=19;
	
	public static final int GUARDAR_PRODUCTO=DESCARGAR_PRODUCTO+DIFERENCIA;
	public static final int GUARDAR_OPORTUNIDAD=DESCARGAR_OPORTUNIDAD+DIFERENCIA;
	public static final int GUARDAR_SKU=DESCARGAR_SKU+DIFERENCIA;
	public static final int GUARDAR_ACCIONESTRADE=DESCARGAR_ACCIONESTRADE+DIFERENCIA;
	public static final int GUARDAR_ACCIONESTRADEPRODUCTO=DESCARGAR_ACCIONESTRADEPRODUCTO+DIFERENCIA;
	public static final int GUARDAR_CLIENTE=DESCARGAR_CLIENTE+DIFERENCIA;
	public static final int GUARDAR_POSICION=DESCARGAR_POSICION+DIFERENCIA;
	public static final int GUARDAR_PRESENTACION=DESCARGAR_PRESENTACION+DIFERENCIA;
	public static final int GUARDAR_PUNTO=DESCARGAR_PUNTO+DIFERENCIA;
	public static final int GUARDAR_EVALUACION=DESCARGAR_EVALUACION+DIFERENCIA;
	public static final int GUARDAR_EVALUACION_OPORTUNIDAD=DESCARGAR_EVALUACION_OPORTUNIDAD+DIFERENCIA;
	public static final int GUARDAR_EVALUACION_POSICION=DESCARGAR_EVALUACION_POSICION+DIFERENCIA;
	public static final int GUARDAR_EVALUACION_PRESENTACION=DESCARGAR_EVALUACION_PRESENTACION+DIFERENCIA;
	public static final int GUARDAR_EVALUACION_SKU=DESCARGAR_EVALUACION_SKU+DIFERENCIA;
	public static final int GUARDAR_EVALUACION_POSICION_COMPROMISO=DESCARGAR_EVALUACION_POSICION_COMPROMISO+DIFERENCIA;
	public static final int GUARDAR_PROFIT=DESCARGAR_PROFIT+DIFERENCIA;
	public static final int GUARDAR_MOTIVO=DESCARGAR_MOTIVO+DIFERENCIA;
	public static final int GUARDAR_ACELERADOR=DESCARGAR_ACELERADOR+DIFERENCIA;
	public static final int GUARDAR_ARTICULO_CANJE=DESCARGAR_ARTICULO_CANJE+DIFERENCIA;
	public static final int GUARDAR_TIPO_ACTIVO=DESCARGAR_TIPO_ACTIVO+DIFERENCIA;
	
	@Inject DescargarProductosProxy descagarProductosProxy;
	@Inject DescargarOportunidadesProxy descargarOportunidadesProxy;
	@Inject DescargarSkuProxy descargarSkuProxy;
	@Inject DescargarAccionesTradeProxy descargarAccionesTradeProxy;
	@Inject DescargarAccionesTradeProductoProxy descargarAccionesTradeProductoProxy;
	@Inject DescargarClienteProxy descargarClienteProxy;
	@Inject DescargarPosicionProxy descargarPosicionProxy;
	@Inject DescargarPresentacionProxy descargarPresentacionProxy;
	@Inject DescargarPuntoProxy descargarPuntoProxy;
	@Inject DescargarEvaluacionProxy descargarEvaluacionProxy;
	@Inject DescargarEvaluacionOportunidadProxy descargarEvaluacionOportunidadProxy;
	@Inject DescargarEvaluacionPosicionProxy descargarEvaluacionPosicionProxy;
	@Inject DescargarEvaluacionPresentacionProxy descargarEvaluacionPresentacionProxy;
	@Inject DescargarEvaluacionSKUProxy descargarEvaluacionSKUProxy;
	@Inject DescargarEvaluacionPosicionCompromisoProxy descargarEvaluacionPosicionCompromisoProxy;
	@Inject DescargarProfitProxy descargarProfitProxy;
	@Inject DescargarAceleradorProxy descargarAceleradorProxy;
	@Inject DescargarMotivoProxy descargarMotivoProxy;
	@Inject DescargarArticuloCanjeProxy descargarArticuloCanjeProxy;
	@Inject DescargarTipoActivoProxy descargarTipoActivoProxy;
	
	@Inject DescargaBLL descargaBLL;
	@Inject UploadBLL uploadBLL;
	@Inject PeriodoTO periodoTO;
	private String TAG = DescargaData_Activity.class.getSimpleName();
	@Inject SharedPreferences prefs;
	
	
	@InjectResource(R.string.downloaddata_activity_sincronizar_inicio) 			String downloaddata_activity_sincronizar_inicio;
	@InjectResource(R.string.downloaddata_activity_sincronizar_descargando) 	String downloaddata_activity_sincronizar_descargando;
	@InjectResource(R.string.downloaddata_activity_sincronizar_guardando) 		String downloaddata_activity_sincronizar_guardando;
	@InjectResource(R.string.downloaddata_activity_sincronizar_error) 			String downloaddata_activity_sincronizar_error;
	@InjectResource(R.string.downloaddata_activity_sincronizar_ok) 				String downloaddata_activity_sincronizar_ok;
	
	
	List<ProcesoInfoTO> lista =null;
	EfficientAdapter adap = null;
	private  MyApplication application;
	private UsuarioTO usuarioTO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		application = (MyApplication)getApplicationContext();
		usuarioTO = application.usuario;
		
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.descargadata_activity);
		
		
		long cantidadEvaluacionesPendientes = uploadBLL.getCantidadEvaluaciones();
		
		if(cantidadEvaluacionesPendientes<=0){
			boolean isConectadoInternet = isNetworkAvailable();
	    	if(!isConectadoInternet){
	    		
	    		setSupportProgressBarIndeterminateVisibility(false);
				MessageBox.showSimpleDialog(this, "Confirmaci—n", 
						"Verificar conexi—n de Internet.", "Ok", new android.content.DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						//finish();
					}
					
				});
				
	    		return;
	    	}
	    	Log.d(TAG, String.format("CONECTADO INTERNET %s",isConectadoInternet));
			
			
			setSupportProgressBarIndeterminateVisibility(true);
			lista = new ArrayList<ProcesoInfoTO>();
			
			processAsync();
			
			//adap = new EfficientAdapter(this,lista);
			//setListAdapter(adap);
			
			
			/*
			processAsync(DESCARGAR_ARTICULO_CANJE);
			processAsync(DESCARGAR_CLIENTE);
			processAsync(DESCARGAR_ACELERADOR);
			processAsync(DESCARGAR_MOTIVO);
			processAsync(DESCARGAR_PRODUCTO);
			processAsync(DESCARGAR_OPORTUNIDAD);
			processAsync(DESCARGAR_SKU);
			processAsync(DESCARGAR_ACCIONESTRADE);
			processAsync(DESCARGAR_ACCIONESTRADEPRODUCTO);
			
			processAsync(DESCARGAR_POSICION);
			processAsync(DESCARGAR_PRESENTACION);
			processAsync(DESCARGAR_PUNTO);
			processAsync(DESCARGAR_EVALUACION);
			*/
			
			
		}else{
			setSupportProgressBarIndeterminateVisibility(false);
			MessageBox.showSimpleDialog(this, "Confirmaci—n", "Tiene evaluaciones pendientes, debe enviar sus evaluaciones.", "Ok", new android.content.DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
				
			});
		}
		
	
		
	}
	
	
    
	/*
	private int contadorProcesos=0;
	private synchronized void addContadorProcesos(){
		contadorProcesos++;
		Log.i("up", String.valueOf(contadorProcesos));
		if(contadorProcesos>0){
			setSupportProgressBarIndeterminateVisibility(true);
		}
	}
	
	private synchronized void removeContadorProcesos(){
		contadorProcesos--;
		Log.i("dow", String.valueOf(contadorProcesos));
		if(contadorProcesos==0){
			setSupportProgressBarIndeterminateVisibility(false);
			savePreferencia(ConstantesApp.DESCARGA_REALIZADA,usuarioTO.codigoRuta);
			finish();
		}
	}*/
	
	
private int contadorProcesos=0;
	
	private synchronized void addContadorProcesos(){
		contadorProcesos++;
		Log.i("up", String.valueOf(contadorProcesos));
		if(contadorProcesos>0){
			setSupportProgressBarIndeterminateVisibility(true);
		}
	}
	
	private synchronized void removeContadorProcesos(){
		contadorProcesos--;
		Log.i("dow", String.valueOf(contadorProcesos));
		if(contadorProcesos==0){
			setSupportProgressBarIndeterminateVisibility(false);
			
			int contador=0;
			
			for (ProcesoInfoTO proceso : lista) {
				if(proceso.isExito)
				contador++;
			}
			
			if(contador==lista.size()){
				setSupportProgressBarIndeterminateVisibility(false);
				savePreferencia(ConstantesApp.DESCARGA_REALIZADA,usuarioTO.codigoRuta);
				finish();
			}
		}
	}

	
	  
		

	
	
	@Override
	protected void process() throws Exception {
		// TODO Auto-generated method stub
		
		
		ProcesoInfoTO p0 = new ProcesoInfoTO();
		p0.processId=DESCARGAR_PRODUCTO;		
		p0.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Producto");
		p0.estado=ProcesoInfoTO.ESTADO_INICIO;
		lista.add(p0);
		
		
		ProcesoInfoTO p1 = new ProcesoInfoTO();
		p1.processId=DESCARGAR_OPORTUNIDAD;
		p1.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Oportunidades");
		p1.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p1);
		
		ProcesoInfoTO p2 = new ProcesoInfoTO();
		p2.processId=DESCARGAR_SKU;
		p2.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Skus");
		p2.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p2);
		
		ProcesoInfoTO p3 = new ProcesoInfoTO();
		p3.processId=DESCARGAR_ACCIONESTRADE;
		p3.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Acciones trade");
		p3.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p3);
		
		ProcesoInfoTO p4 = new ProcesoInfoTO();
		p4.processId=DESCARGAR_ACCIONESTRADEPRODUCTO;
		p4.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Acciones Trade Producto");
		p4.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p4);
		
		ProcesoInfoTO p5 = new ProcesoInfoTO();
		p5.processId=DESCARGAR_CLIENTE;
		p5.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Cliente");
		p5.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p5);
		

		ProcesoInfoTO p6 = new ProcesoInfoTO();
		p6.processId=DESCARGAR_POSICION;
		p6.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Posici—n");
		p6.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p6);
		
		ProcesoInfoTO p7 = new ProcesoInfoTO();
		p7.processId=DESCARGAR_PRESENTACION;
		p7.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Presentaci—n");
		p7.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p7);
		
		ProcesoInfoTO p8 = new ProcesoInfoTO();
		p8.processId=DESCARGAR_PUNTO;
		p8.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Puntos");
		p8.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p8);
		
		ProcesoInfoTO p9 = new ProcesoInfoTO();
		p9.processId=DESCARGAR_EVALUACION;
		p9.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaciones");
		p9.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p9);
		
		
		ProcesoInfoTO p15 = new ProcesoInfoTO();
		p15.processId=DESCARGAR_MOTIVO;
		p15.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Motivos");
		p15.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p15);
		
		ProcesoInfoTO p16 = new ProcesoInfoTO();
		p16.processId=DESCARGAR_ACELERADOR;
		p16.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Acelerador");
		p16.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p16);
		
		ProcesoInfoTO p17 = new ProcesoInfoTO();
		p17.processId=DESCARGAR_ARTICULO_CANJE;
		p17.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Articulos Canje");
		p17.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p17);
		
		
		
		ProcesoInfoTO p18 = new ProcesoInfoTO();
		p18.processId=DESCARGAR_TIPO_ACTIVO;
		p18.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Tipo Activo");
		p18.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p18);
		
		adap = new EfficientAdapter(this,lista);
	}
	
	
	/*
	@Override
	protected boolean executeAsyncPre(int accion) {
		// TODO Auto-generated method stub
		
		switch (accion) {
			case DESCARGAR_PRODUCTO:
				ProcesoInfoTO p0 = processById(DESCARGAR_PRODUCTO);
				p0.processId=DESCARGAR_PRODUCTO;
				p0.descripcion="Descargando Producto";
				p0.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p0)){
					lista.add(p0);
				}
				
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_PRODUCTO:
				ProcesoInfoTO p20 = processById(GUARDAR_PRODUCTO-20);
				p20.processId=DESCARGAR_PRODUCTO;
				p20.descripcion="Guardando Producto";
				p20.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_OPORTUNIDAD:
				ProcesoInfoTO p1 = processById(DESCARGAR_OPORTUNIDAD);
				p1.processId=DESCARGAR_OPORTUNIDAD;
				p1.descripcion="Descargando Oportunidades";
				p1.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p1)){
					lista.add(p1);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_OPORTUNIDAD:
				ProcesoInfoTO p21 = processById(GUARDAR_OPORTUNIDAD-20);
				p21.processId=DESCARGAR_OPORTUNIDAD;
				p21.descripcion="Guardando Oportunidades";
				p21.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_SKU:
				ProcesoInfoTO p2 = processById(DESCARGAR_SKU);
				p2.processId=DESCARGAR_SKU;
				p2.descripcion="Descargando SKUs";
				p2.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p2)){
					lista.add(p2);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_SKU:
				ProcesoInfoTO p22 = processById(GUARDAR_SKU-20);
				p22.processId=DESCARGAR_SKU;
				p22.descripcion="Guardando SKUs";
				p22.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_ACCIONESTRADE:
				ProcesoInfoTO p3 = processById(DESCARGAR_ACCIONESTRADE);
				p3.processId=DESCARGAR_ACCIONESTRADE;
				p3.descripcion="Descargando Acciones Trade";
				p3.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p3)){
					lista.add(p3);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_ACCIONESTRADE:
				ProcesoInfoTO p23 = processById(GUARDAR_ACCIONESTRADE-20);
				p23.processId=DESCARGAR_ACCIONESTRADE;
				p23.descripcion="Guardando Acciones Trade";
				p23.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_ACCIONESTRADEPRODUCTO:
				ProcesoInfoTO p4 = processById(DESCARGAR_ACCIONESTRADEPRODUCTO);
				p4.processId=DESCARGAR_ACCIONESTRADEPRODUCTO;
				p4.descripcion="Descargando Acciones Trade Producto";
				p4.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p4)){
					lista.add(p4);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_ACCIONESTRADEPRODUCTO:
				ProcesoInfoTO p24 = processById(GUARDAR_ACCIONESTRADEPRODUCTO-20);
				p24.processId=DESCARGAR_ACCIONESTRADEPRODUCTO;
				p24.descripcion="Guardando Acciones Trade Producto";
				p24.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_CLIENTE:
				ProcesoInfoTO p5 = processById(DESCARGAR_CLIENTE);
				p5.processId=DESCARGAR_CLIENTE;
				p5.descripcion="Descargando Clientes";
				p5.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p5)){
					lista.add(p5);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_CLIENTE:
				ProcesoInfoTO p25 = processById(GUARDAR_CLIENTE-20);
				p25.processId=DESCARGAR_CLIENTE;
				p25.descripcion="Guardando Clientes";
				p25.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_POSICION:
				ProcesoInfoTO p6 = processById(DESCARGAR_POSICION);
				p6.processId=DESCARGAR_POSICION;
				p6.descripcion="Descargando Posici—n";
				p6.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p6)){
					lista.add(p6);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_POSICION:
				ProcesoInfoTO p26 = processById(GUARDAR_POSICION-20);
				p26.processId=DESCARGAR_POSICION;
				p26.descripcion="Guardando Posici—n";
				p26.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_PRESENTACION:
				ProcesoInfoTO p7 = processById(DESCARGAR_PRESENTACION);
				p7.processId=DESCARGAR_PRESENTACION;
				p7.descripcion="Descargando Presentaci—n";
				p7.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p7)){
					lista.add(p7);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_PRESENTACION:
				ProcesoInfoTO p27 = processById(GUARDAR_PRESENTACION-20);
				p27.processId=DESCARGAR_PRESENTACION;
				p27.descripcion="Guardando Presentaci—n";
				p27.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_PUNTO:
				ProcesoInfoTO p8 = processById(DESCARGAR_PUNTO);
				p8.processId=DESCARGAR_PUNTO;
				p8.descripcion="Descargando Puntos";
				p8.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p8)){
					lista.add(p8);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_PUNTO:
				ProcesoInfoTO p28 = processById(GUARDAR_PUNTO-20);
				p28.processId=DESCARGAR_PUNTO;
				p28.descripcion="Guardando Puntos";
				p28.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_EVALUACION:
				ProcesoInfoTO p9 = processById(DESCARGAR_EVALUACION);
				p9.processId=DESCARGAR_EVALUACION;
				p9.descripcion="Descargando Evaluaciones";
				p9.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p9)){
					lista.add(p9);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION:
				ProcesoInfoTO p29 = processById(GUARDAR_EVALUACION-20);
				p29.processId=DESCARGAR_EVALUACION;
				p29.descripcion="Guardando Evaluaciones";
				p29.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_EVALUACION_OPORTUNIDAD:
				ProcesoInfoTO p10 = processById(DESCARGAR_EVALUACION_OPORTUNIDAD);
				p10.processId=DESCARGAR_EVALUACION_OPORTUNIDAD;
				p10.descripcion="Descargando Evaluaciones Oportunidades";
				p10.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p10)){
					lista.add(p10);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION_OPORTUNIDAD:
				ProcesoInfoTO p30 = processById(GUARDAR_EVALUACION_OPORTUNIDAD-20);
				p30.processId=DESCARGAR_EVALUACION_OPORTUNIDAD;
				p30.descripcion="Guardando Evaluaciones Oportunidades";
				p30.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_EVALUACION_POSICION:
				ProcesoInfoTO p11 = processById(DESCARGAR_EVALUACION_POSICION);
				p11.processId=DESCARGAR_EVALUACION_POSICION;
				p11.descripcion="Descargando Evaluaciones Posiciones";
				p11.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p11)){
					lista.add(p11);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION_POSICION:
				ProcesoInfoTO p31 = processById(GUARDAR_EVALUACION_POSICION-20);
				p31.processId=DESCARGAR_EVALUACION_POSICION;
				p31.descripcion="Guardando Evaluaciones Posiciones";
				p31.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_EVALUACION_POSICION_COMPROMISO:
				ProcesoInfoTO p12 = processById(DESCARGAR_EVALUACION_POSICION_COMPROMISO);
				p12.processId=DESCARGAR_EVALUACION_POSICION_COMPROMISO;
				p12.descripcion="Descargando Evaluaciones Posiciones Compromiso";
				p12.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p12)){
					lista.add(p12);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION_POSICION_COMPROMISO:
				ProcesoInfoTO p32 = processById(GUARDAR_EVALUACION_POSICION_COMPROMISO-20);
				p32.processId=DESCARGAR_EVALUACION_POSICION_COMPROMISO;
				p32.descripcion="Guardando Evaluaciones Posiciones Compromiso";
				p32.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
				
			
			case DESCARGAR_EVALUACION_PRESENTACION:
				ProcesoInfoTO p13 = processById(DESCARGAR_EVALUACION_PRESENTACION);
				p13.processId=DESCARGAR_EVALUACION_PRESENTACION;
				p13.descripcion="Descargando Evaluaciones Presentaciones";
				p13.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p13)){
					lista.add(p13);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION_PRESENTACION:
				ProcesoInfoTO p33 = processById(GUARDAR_EVALUACION_PRESENTACION-20);
				p33.processId=DESCARGAR_EVALUACION_PRESENTACION;
				p33.descripcion="Guardando Evaluaciones Presentaciones";
				p33.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
				
			case DESCARGAR_EVALUACION_SKU:
				ProcesoInfoTO p14 = processById(DESCARGAR_EVALUACION_SKU);
				p14.processId=DESCARGAR_EVALUACION_SKU;
				p14.descripcion="Descargando Evaluaciones SKUs";
				p14.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p14)){
					lista.add(p14);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_EVALUACION_SKU:
				ProcesoInfoTO p34 = processById(GUARDAR_EVALUACION_SKU-20);
				p34.processId=DESCARGAR_EVALUACION_SKU;
				p34.descripcion="Guardando Evaluaciones SKUs";
				p34.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_PROFIT:
				ProcesoInfoTO p15 = processById(DESCARGAR_PROFIT);
				p15.processId=DESCARGAR_PROFIT;
				p15.descripcion="Descargando Profit";
				p15.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p15)){
					lista.add(p15);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_PROFIT:
				ProcesoInfoTO p35 = processById(GUARDAR_PROFIT-20);
				p35.processId=DESCARGAR_PROFIT;
				p35.descripcion="Guardando Profit";
				p35.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_MOTIVO:
				ProcesoInfoTO p16 = processById(DESCARGAR_MOTIVO);
				p16.processId=DESCARGAR_MOTIVO;
				p16.descripcion="Descargando Motivos";
				p16.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p16)){
					lista.add(p16);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_MOTIVO:
				ProcesoInfoTO p36 = processById(GUARDAR_MOTIVO-20);
				p36.processId=DESCARGAR_MOTIVO;
				p36.descripcion="Guardando Motivos";
				p36.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_ACELERADOR:
				ProcesoInfoTO p17 = processById(DESCARGAR_ACELERADOR);
				p17.processId=DESCARGAR_ACELERADOR;
				p17.descripcion="Descargando Acelerador";
				p17.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p17)){
					lista.add(p17);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_ACELERADOR:
				ProcesoInfoTO p37 = processById(GUARDAR_ACELERADOR-20);
				p37.processId=DESCARGAR_ACELERADOR;
				p37.descripcion="Guardando Aceleradores";
				p37.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
			case DESCARGAR_ARTICULO_CANJE:
				ProcesoInfoTO p18 = processById(DESCARGAR_ARTICULO_CANJE);
				p18.processId=DESCARGAR_ARTICULO_CANJE;
				p18.descripcion="Descargando Articulos Canje";
				p18.estado=ProcesoInfoTO.ESTADO_DESCARGA;
				if(!lista.contains(p18)){
					lista.add(p18);
				}
				adap.notifyDataSetChanged();
				break;
			case GUARDAR_ARTICULO_CANJE:
				ProcesoInfoTO p38 = processById(GUARDAR_ACELERADOR-20);
				p38.processId=DESCARGAR_ARTICULO_CANJE;
				p38.descripcion="Guardando Articulos Canje";
				p38.estado=ProcesoInfoTO.ESTADO_DB;
				adap.notifyDataSetChanged();
				break;
		}
		
		
		addContadorProcesos();
		
		return super.executeAsyncPre(accion);
	}
	*/
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		
		setListAdapter(adap);		

		for (ProcesoInfoTO proceso : lista) {
			processAsync(proceso.processId);
		}
		
		ProcesoInfoTO p18 = new ProcesoInfoTO();
		p18.processId=DESCARGAR_EVALUACION_OPORTUNIDAD;
		p18.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaci—n Oportunidad");
		p18.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p18);
		
		ProcesoInfoTO p19 = new ProcesoInfoTO();
		p19.processId=DESCARGAR_EVALUACION_POSICION;
		p19.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaci—n Posici—n");
		p19.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p19);
		
		ProcesoInfoTO p20 = new ProcesoInfoTO();
		p20.processId=DESCARGAR_EVALUACION_POSICION_COMPROMISO;
		p20.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaci—n Posici—n Compromiso");
		p20.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p20);
		
		ProcesoInfoTO p21 = new ProcesoInfoTO();
		p21.processId=DESCARGAR_EVALUACION_PRESENTACION;
		p21.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaci—n Posici—n Presentaci—n");
		p21.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p21);
		
		ProcesoInfoTO p22 = new ProcesoInfoTO();
		p22.processId=DESCARGAR_EVALUACION_SKU;
		p22.descripcion=String.format(downloaddata_activity_sincronizar_inicio, "Evaluaci—n Skus");
		p22.estado=ProcesoInfoTO.ESTADO_DESCARGA;
		lista.add(p22);
		
		super.processOk();
	}
	
	
	
	public synchronized ProcesoInfoTO processById(int processId){
		ProcesoInfoTO processSeleccionado=null;
		for (ProcesoInfoTO process : lista) {
			if(process.processId==processId || process.processId==processId-DIFERENCIA){
				processSeleccionado = process;
				break;
			}
		}
		
		/*
		if(processSeleccionado==null){
			processSeleccionado = new ProcesoInfoTO();
			processSeleccionado.processId=processId;
		}*/
		
		return processSeleccionado;
	}
	
	
	@Override
	protected boolean executeAsyncPre(int accion) {
		ProcesoInfoTO proceso = processById(accion);	
		
		
		switch (accion) {
		case DESCARGAR_TIPO_ACTIVO:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Tipo Activo");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			break;
		case GUARDAR_TIPO_ACTIVO:			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Tipo Activo");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			break;
		case DESCARGAR_PRODUCTO:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Producto");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			break;
		case GUARDAR_PRODUCTO:			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Producto");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_OPORTUNIDAD:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Oportunidades");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			break;
		case GUARDAR_OPORTUNIDAD:			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Oportunidades");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_SKU:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "SKUs");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_SKU:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "SKUs");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_ACCIONESTRADE:
			
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Acciones Trade");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			
			break;
		case GUARDAR_ACCIONESTRADE:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Acciones Trade");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_ACCIONESTRADEPRODUCTO:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Acciones Trade Producto");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			

			break;
		case GUARDAR_ACCIONESTRADEPRODUCTO:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Acciones Trade Producto");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			

			break;
		case DESCARGAR_CLIENTE:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Clientes");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_CLIENTE:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Clientes");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
	
			break;
		case DESCARGAR_POSICION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Posici—n");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			
			break;
			
			
		case GUARDAR_POSICION:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Posici—n");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_PRESENTACION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Presentaci—n");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
		
			break;
		case GUARDAR_PRESENTACION:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Presentaci—n");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			break;
		case DESCARGAR_PUNTO:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Puntos");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_PUNTO:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Puntos");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;

			break;
		case DESCARGAR_EVALUACION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;

			break;
		case GUARDAR_EVALUACION:
			
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;

			
			
			break;
		case DESCARGAR_EVALUACION_OPORTUNIDAD:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Oportunidades");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_EVALUACION_OPORTUNIDAD:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Oportunidades");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			
			break;
		case DESCARGAR_EVALUACION_POSICION:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Posiciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_EVALUACION_POSICION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Posiciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_EVALUACION_POSICION_COMPROMISO:

			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Posiciones Compromiso");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_EVALUACION_POSICION_COMPROMISO:
			
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Posiciones Compromiso");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
			
		
		case DESCARGAR_EVALUACION_PRESENTACION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Presentaciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			
			break;
		case GUARDAR_EVALUACION_PRESENTACION:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Presentaciones");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
			
		case DESCARGAR_EVALUACION_SKU:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones SKUs");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			

			break;
		case GUARDAR_EVALUACION_SKU:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones SKUs");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		
		case DESCARGAR_MOTIVO:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Motivos");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
	
			break;
		case GUARDAR_MOTIVO:

			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Motivos");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_ACELERADOR:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Evaluaciones Acelerador");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_ACELERADOR:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Evaluaciones Aceleradores");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
		case DESCARGAR_ARTICULO_CANJE:
			
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_descargando, "Articulos Canje");
			proceso.estado=ProcesoInfoTO.ESTADO_DESCARGA;
			
			break;
		case GUARDAR_ARTICULO_CANJE:
			proceso.descripcion=String.format(downloaddata_activity_sincronizar_guardando, "Articulos Canje");
			proceso.estado=ProcesoInfoTO.ESTADO_DB;
			
			break;
	}
		
		
		
		adap.notifyDataSetChanged();
		addContadorProcesos();
		return true;
	}
	
	@Override
	protected void process(int accion) throws Exception {
		// TODO Auto-generated method stub
		
		
		switch (accion) {
		case DESCARGAR_TIPO_ACTIVO:
			descargarTipoActivoProxy.execute();
			break;
		case DESCARGAR_PRODUCTO:
			descagarProductosProxy.execute();
			break;
		case DESCARGAR_OPORTUNIDAD:
			
			descargarOportunidadesProxy.execute();
			break;
		case DESCARGAR_SKU:
			
			descargarSkuProxy.execute();
			break;
		case DESCARGAR_ACCIONESTRADE:
			
			descargarAccionesTradeProxy.execute();
			break;
		case DESCARGAR_ACCIONESTRADEPRODUCTO:
			
			descargarAccionesTradeProductoProxy.execute();
			break;
		case DESCARGAR_CLIENTE:
			
			descargarClienteProxy.execute();
			break;
		case DESCARGAR_POSICION:
			
			descargarPosicionProxy.execute();
			break;
		case DESCARGAR_PRESENTACION:
			
			descargarPresentacionProxy.execute();
			break;
		
		case DESCARGAR_PUNTO:
			
			descargarPuntoProxy.execute();
			break;
		case DESCARGAR_EVALUACION:
			
			descargarEvaluacionProxy.execute();
			break;
		case DESCARGAR_EVALUACION_OPORTUNIDAD:
			
			descargarEvaluacionOportunidadProxy.execute();
			break;
		case DESCARGAR_EVALUACION_POSICION:
			
			descargarEvaluacionPosicionProxy.execute();
			break;
				
		case DESCARGAR_EVALUACION_PRESENTACION:
			
			descargarEvaluacionPresentacionProxy.execute();
			break;
			
		case DESCARGAR_EVALUACION_SKU:
				
				descargarEvaluacionSKUProxy.execute();
				break;
				
		case DESCARGAR_EVALUACION_POSICION_COMPROMISO:
				descargarEvaluacionPosicionCompromisoProxy.execute();
				break;
		case DESCARGAR_PROFIT:
			descargarProfitProxy.execute();
			break;
		
		case DESCARGAR_MOTIVO:
			descargarMotivoProxy.execute();
			break;
			
		case DESCARGAR_ACELERADOR:
			descargarAceleradorProxy.execute();
			break;
		
		case DESCARGAR_ARTICULO_CANJE:
			descargarArticuloCanjeProxy.execute();
			break;
		case GUARDAR_TIPO_ACTIVO:
			
			descargaBLL.saveTipoActivo(descargarTipoActivoProxy.getResponse().tipos);
			break;
		case GUARDAR_PRODUCTO:
			
			descargaBLL.saveProducto(descagarProductosProxy.getResponse().productos);
			break;
		case GUARDAR_OPORTUNIDAD:
			
			descargaBLL.saveOportunidad(descargarOportunidadesProxy.getResponse().oportunidades);
			break;
		
		case GUARDAR_SKU:
			
			descargaBLL.saveSku(descargarSkuProxy.getResponse().skus);
			break;
		case GUARDAR_ACCIONESTRADE:
			
			descargaBLL.saveAccionTrade(descargarAccionesTradeProxy.getResponse().acciones);
			break;
		case GUARDAR_ACCIONESTRADEPRODUCTO:
			
			descargaBLL.saveAccionTradeProducto(descargarAccionesTradeProductoProxy.getResponse().accionesProducto);
			break;
		case GUARDAR_CLIENTE:
			
			descargaBLL.saveCliente(descargarClienteProxy.getResponse().clientes);
			break;
		case GUARDAR_POSICION:
			
			descargaBLL.savePosicion(descargarPosicionProxy.getResponse().posiciones);
			break;
		case GUARDAR_PRESENTACION:
			
			descargaBLL.savePresentacion(descargarPresentacionProxy.getResponse().presentaciones);
			break;
		case GUARDAR_PUNTO:
			
			descargaBLL.savePunto(descargarPuntoProxy.getResponse().puntos);
			break;
		case GUARDAR_EVALUACION:
			
			descargaBLL.saveEvaluacion(descargarEvaluacionProxy.getResponse().evaluaciones);
			break;
		case GUARDAR_EVALUACION_OPORTUNIDAD:
			
			descargaBLL.saveEvaluacionOportunidad(descargarEvaluacionOportunidadProxy.getResponse().oportunidades);
			break;
		case GUARDAR_EVALUACION_POSICION:
			
			descargaBLL.saveEvaluacionPosicion(descargarEvaluacionPosicionProxy.getResponse().posiciones);
			break;
		case GUARDAR_EVALUACION_PRESENTACION:
			
			descargaBLL.saveEvaluacionPresentacion(descargarEvaluacionPresentacionProxy.getResponse().presentaciones);
			break;
		case GUARDAR_EVALUACION_SKU:
			
			descargaBLL.saveEvaluacionSkus(descargarEvaluacionSKUProxy.getResponse().skus);
			break;
		case GUARDAR_EVALUACION_POSICION_COMPROMISO:
			descargaBLL.saveEvaluacionPosicionCompromiso(descargarEvaluacionPosicionCompromisoProxy.getResponse().compromisos);
			break;
		case GUARDAR_PROFIT:
			descargaBLL.saveProfit(descargarProfitProxy.getResponse().profit);
			break;
			
		case GUARDAR_ACELERADOR:
			descargaBLL.saveAcelerador(descargarAceleradorProxy.getResponse().aceleradores);
			break;
			
		case GUARDAR_MOTIVO:
			descargaBLL.saveMotivo(descargarMotivoProxy.getResponse().motivos);
			break;
		case GUARDAR_ARTICULO_CANJE:
			descargaBLL.saveArticulo(descargarArticuloCanjeProxy.getResponse().articulo);
			break;
		default:
			break;
		}
		
	}

	@Override
	protected void processOk(int accion) {
		Log.i("processOk - accion:", String.valueOf(accion));		
		
		switch (accion) {
			case DESCARGAR_PRODUCTO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descagarProductosProxy.isExito();
				if(descagarProductosProxy.getResponse()!=null){
					int status = descagarProductosProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Producto descargados";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion=String.format(downloaddata_activity_sincronizar_error, "Producto");
						p1.isExito=false;
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					}
				}else{
					p1.descripcion=String.format(downloaddata_activity_sincronizar_error, "Producto");
					p1.isExito=false;
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
				}
				break;
			}
			case GUARDAR_PRODUCTO:
			{
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Producto");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				
				break;
			}
			
			case DESCARGAR_OPORTUNIDAD:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarOportunidadesProxy.isExito();
				if(descargarOportunidadesProxy.getResponse()!=null){
					int status = descargarOportunidadesProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Oportunidades Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion=String.format(downloaddata_activity_sincronizar_error, "oportunidades");
						p1.isExito=false;
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					}
				}else{
					p1.descripcion=String.format(downloaddata_activity_sincronizar_error, "oportunidades");
					p1.isExito=false;
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
				}
				
				break;
			}
			case GUARDAR_OPORTUNIDAD:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Oportunidades");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				break;
			}
			
			case DESCARGAR_SKU:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarSkuProxy.isExito();
				if(descargarSkuProxy.getResponse()!=null){
					int status = descargarSkuProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="SKUs Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion="Error descargando SKUs";
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.isExito=false;
					}
				}else{
					p1.descripcion="Error descargando SKUs";
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_SKU:
			{
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "SKUs");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
	
				break;
			}
			
			case DESCARGAR_ACCIONESTRADE:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarAccionesTradeProxy.isExito();
				if(descargarAccionesTradeProxy.getResponse()!=null){
					int status = descargarAccionesTradeProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Acciones Trade Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Acciones Trade";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Acciones Trade";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_ACCIONESTRADE:
			{
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Acciones Trade");
				p1.estado=ProcesoInfoTO.ESTADO_OK;

				break;
			}
			
			case DESCARGAR_ACCIONESTRADEPRODUCTO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarAccionesTradeProductoProxy.isExito();
				if(descargarAccionesTradeProductoProxy.getResponse()!=null){
					int status = descargarAccionesTradeProductoProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Producto Acciones Trade Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Producto Acciones Trade";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Producto Acciones Trade";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_ACCIONESTRADEPRODUCTO:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Producto Acciones Trade");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
		
				break;
			}
			
			case DESCARGAR_CLIENTE:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarClienteProxy.isExito();
				if(descargarClienteProxy.getResponse()!=null){
					int status = descargarClienteProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Clientes Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Clientes";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Clientes";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_CLIENTE:
			{
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Cliente");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				break;
			}
			
			case DESCARGAR_POSICION:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarPosicionProxy.isExito();
				if(descargarPosicionProxy.getResponse()!=null){
					int status = descargarPosicionProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Posici—n Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion="Error descargando Posici—n";
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Posici—n";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_POSICION:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Posici—n");
				p1.estado=ProcesoInfoTO.ESTADO_OK;

				break;
			}
			
			case DESCARGAR_PRESENTACION:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarPresentacionProxy.isExito();
				if(descargarPresentacionProxy.getResponse()!=null){
					int status = descargarPresentacionProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Presentaci—n Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Presentaci—n";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Presentaci—n";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_PRESENTACION:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Presentaci—n");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				break;
			}
			
			
			case DESCARGAR_PUNTO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarPuntoProxy.isExito();
				if(descargarPuntoProxy.getResponse()!=null){
					int status = descargarPuntoProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Puntos Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Puntos";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Puntos";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_PUNTO:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Puntos");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
		
				break;
			}
			
			
			case DESCARGAR_EVALUACION:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionProxy.isExito();
				if(descargarEvaluacionProxy.getResponse()!=null){
					int status = descargarEvaluacionProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
						
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Evaluaciones";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Evaluaciones";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_EVALUACION:
			{
				
				processAsync(DESCARGAR_EVALUACION_OPORTUNIDAD);
				processAsync(DESCARGAR_EVALUACION_POSICION);
				processAsync(DESCARGAR_EVALUACION_POSICION_COMPROMISO);
				processAsync(DESCARGAR_EVALUACION_PRESENTACION);
				processAsync(DESCARGAR_EVALUACION_SKU);
				
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				
				break;
			}
			

			case DESCARGAR_EVALUACION_OPORTUNIDAD:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionOportunidadProxy.isExito();
				if(descargarEvaluacionOportunidadProxy.getResponse()!=null){
					int status = descargarEvaluacionOportunidadProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones Descargadas Oportunidad";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion="Error descargando Evaluaciones Oportunidad";
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Evaluaciones Oportunidad";
					p1.isExito=false;
				}
				adap.notifyDataSetChanged();
				break;
			}
			case GUARDAR_EVALUACION_OPORTUNIDAD:
			{
				
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones Oportunidad");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
		
				break;
			}
			
			
			
			
			case DESCARGAR_EVALUACION_POSICION:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionPosicionProxy.isExito();
				if(descargarEvaluacionPosicionProxy.getResponse()!=null){
					int status = descargarEvaluacionPosicionProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones Posici—n Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion="Error descargando Evaluaciones Posici—n";
						p1.isExito=false;
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					}
				}else{
					p1.descripcion="Error descargando Evaluaciones Posici—n";
					p1.isExito=false;
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
				}
				
				break;
			}
			case GUARDAR_EVALUACION_POSICION:
			{

				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones Posici—n");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
			
				break;
			}
			
			case DESCARGAR_EVALUACION_POSICION_COMPROMISO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionPosicionCompromisoProxy.isExito();
				if(descargarEvaluacionPosicionCompromisoProxy.getResponse()!=null){
					int status = descargarEvaluacionPosicionCompromisoProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones Posici—n Compromiso Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Evaluaciones Posici—n Compromiso";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Evaluaciones Posici—n Compromiso";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_EVALUACION_POSICION_COMPROMISO:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones Posici—n Compromiso");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				break;
			}
			
			case DESCARGAR_EVALUACION_PRESENTACION:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionPresentacionProxy.isExito();
				if(descargarEvaluacionPresentacionProxy.getResponse()!=null){
					int status = descargarEvaluacionPresentacionProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones Presentaci—n Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.descripcion="Error descargando Evaluaciones Posici—n Compromiso";
						p1.isExito=false;
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Evaluaciones Posici—n Compromiso";
					p1.isExito=false;
				}
				adap.notifyDataSetChanged();
				break;
			}
			case GUARDAR_EVALUACION_PRESENTACION:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones Presentaci—n");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				
				break;
			}
			
			case DESCARGAR_EVALUACION_SKU:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarEvaluacionSKUProxy.isExito();
				if(descargarEvaluacionSKUProxy.getResponse()!=null){
					int status = descargarEvaluacionSKUProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Evaluaciones SKUs Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Evaluaciones SKUs";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Evaluaciones SKUs";
					p1.isExito=false;
				}
				adap.notifyDataSetChanged();
				break;
			}
			case GUARDAR_EVALUACION_SKU:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Evaluaciones SKUs");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
		
				break;
			}
			
			/*case DESCARGAR_PROFIT:{
				ProcesoInfoTO p1 = processById(DESCARGAR_PROFIT);
				boolean isExito = descargarProfitProxy.isExito();
				if(descargarProfitProxy.getResponse()!=null){
					int status = descargarProfitProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Profit Descargadas";
						p1.isExito=true;
						processAsync(GUARDAR_PROFIT);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Profit";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Profit";
					p1.isExito=false;
				}
				adap.notifyDataSetChanged();
				break;
			}
			case GUARDAR_PROFIT:
			{
				ProcesoInfoTO p1 = processById(GUARDAR_PROFIT-20);
				p1.descripcion="Profit guardado";
				p1.isExito=true;
				p1.estado = ProcesoInfoTO.ESTADO_OK;
				adap.notifyDataSetChanged();
				break;
			}*/
			
			case DESCARGAR_ACELERADOR:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarAceleradorProxy.isExito();
				if(descargarAceleradorProxy.getResponse()!=null){
					int status = descargarAceleradorProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Aceleradores Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Aceleradores";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Aceleradores";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_ACELERADOR:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Aceleradores SKUs");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
		
				break;
			}
			
			case DESCARGAR_MOTIVO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarMotivoProxy.isExito();
				if(descargarMotivoProxy.getResponse()!=null){
					int status = descargarMotivoProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Motivos Descargadas";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Motivos";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Motivos";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_MOTIVO:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Motivos SKUs");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				
				break;
			}
			
			
			case DESCARGAR_ARTICULO_CANJE:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarArticuloCanjeProxy.isExito();
				if(descargarArticuloCanjeProxy.getResponse()!=null){
					int status = descargarArticuloCanjeProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Articulos Canje Descargado";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Articulos Canje";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Articulos Canje";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_ARTICULO_CANJE:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Articulos Canje");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				break;
			}
			
			
			case DESCARGAR_TIPO_ACTIVO:{
				ProcesoInfoTO p1 = processById(accion);
				boolean isExito = descargarTipoActivoProxy.isExito();
				if(descargarTipoActivoProxy.getResponse()!=null){
					int status = descargarTipoActivoProxy.getResponse().getStatus();
					if ((isExito) && (status == 0)) {
						p1.descripcion="Tipo Activo Descargado";
						p1.isExito=true;
						processAsync(p1.processId+DIFERENCIA);
					}else{
						p1.estado = ProcesoInfoTO.ESTADO_ERROR;
						p1.descripcion="Error descargando Tipo Activo";
						p1.isExito=false;
					}
				}else{
					p1.estado = ProcesoInfoTO.ESTADO_ERROR;
					p1.descripcion="Error descargando Tipo Activo";
					p1.isExito=false;
				}
				
				break;
			}
			case GUARDAR_TIPO_ACTIVO:
			{
				ProcesoInfoTO p1 = processById(accion);
				p1.descripcion=String.format(downloaddata_activity_sincronizar_ok, "Tipo Activo");
				p1.estado=ProcesoInfoTO.ESTADO_OK;
				
				break;
			}
			
		}
		
		removeContadorProcesos();
		adap.notifyDataSetChanged();
		Log.i("processOk", String.valueOf(accion));
		
		/*
		if(accion==DESCARGAR_PRODUCTO){
			removeContadorProcesos();
			boolean isExito = descagarProductosProxy.isExito();
			String message="";
			
			if(descagarProductosProxy.getResponse()!=null){
				int status = descagarProductosProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PRODUCTO);
						showToast("DESCARGO PRODUCTOS");
					}else{
						message = descagarProductosProxy.getResponse().getDescripcion();
						Log.d(TAG,message);
						showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_OPORTUNIDAD){
			removeContadorProcesos();
			boolean isExito = descargarOportunidadesProxy.isExito();
			String message="";
			
			if(descargarOportunidadesProxy.getResponse()!=null){
				int status = descargarOportunidadesProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_OPORTUNIDAD);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarOportunidadesProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_SKU){
			removeContadorProcesos();
			boolean isExito = descargarSkuProxy.isExito();
			String message="";
			
			if(descargarSkuProxy.getResponse()!=null){
				int status = descargarSkuProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_SKU);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarSkuProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_ACCIONESTRADE){
			removeContadorProcesos();
			boolean isExito = descargarAccionesTradeProxy.isExito();
			String message="";
			
			if(descargarAccionesTradeProxy.getResponse()!=null){
				int status = descargarAccionesTradeProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_ACCIONESTRADE);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarAccionesTradeProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_ACCIONESTRADEPRODUCTO){
			removeContadorProcesos();
			boolean isExito = descargarAccionesTradeProductoProxy.isExito();
			String message="";
			
			if(descargarAccionesTradeProductoProxy.getResponse()!=null){
				int status = descargarAccionesTradeProductoProxy.getResponse().getStatus();
				
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_ACCIONESTRADEPRODUCTO);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarAccionesTradeProductoProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
						
						//super.processOk(DESCARGAR_PRODUCTO);
						//showToast(message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_CLIENTE){
			removeContadorProcesos();
			boolean isExito = descargarClienteProxy.isExito();
			String message="";
			
			
			
			if(descargarClienteProxy.getResponse()!=null){
				int status = descargarClienteProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_CLIENTE);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarClienteProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_POSICION){
			removeContadorProcesos();
			boolean isExito = descargarPosicionProxy.isExito();
			String message="";
			
			
			
			if(descargarPosicionProxy.getResponse()!=null){
				int status = descargarPosicionProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_POSICION);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPosicionProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
			
		}else if(accion==DESCARGAR_PRESENTACION){
			removeContadorProcesos();
			boolean isExito = descargarPresentacionProxy.isExito();
			String message="";
			
			
			
			if(descargarPresentacionProxy.getResponse()!=null){
				int status = descargarPresentacionProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PRESENTACION);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPresentacionProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_PUNTO){
			removeContadorProcesos();
			boolean isExito = descargarPuntoProxy.isExito();
			String message="";
			
			
			
			if(descargarPuntoProxy.getResponse()!=null){
				int status = descargarPuntoProxy.getResponse().getStatus();
				if (isExito) {
					if (status == 0) {
						
						processAsync(GUARDAR_PUNTO);
						//showToast("DESCARGO OPORTUNIDADES");
					}else{
						message = descargarPuntoProxy.getResponse().getDescripcion();
							
						Log.d(TAG,message);
					}
				}else{
					message=error_generico_process;
					super.processOk();
					showToast(message);
				}
			}
		}else if(accion==DESCARGAR_EVALUACION){
				removeContadorProcesos();
				processAsync(GUARDAR_EVALUACION);
		}else if(accion==DESCARGAR_EVALUACION_OPORTUNIDAD){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_OPORTUNIDAD);
		}else if(accion==DESCARGAR_EVALUACION_POSICION){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_POSICION);
		}else if(accion==DESCARGAR_EVALUACION_PRESENTACION){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_PRESENTACION);
		}else if(accion==DESCARGAR_EVALUACION_SKU){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_SKU);
		}else if(accion==DESCARGAR_EVALUACION_POSICION_COMPROMISO){
			removeContadorProcesos();
			processAsync(GUARDAR_EVALUACION_POSICION_COMPROMISO);
		}else if(accion==GUARDAR_PRODUCTO){
			removeContadorProcesos();
			showToast("PRODUCTOS GUARDAR");
		}else if(accion==GUARDAR_OPORTUNIDAD){
			removeContadorProcesos();
			showToast("OPORTUNIDAD GUARDAR");
		}else if(accion==GUARDAR_SKU){
			removeContadorProcesos();
			showToast("sku GUARDAR");
		}else if(accion==GUARDAR_ACCIONESTRADE){
			removeContadorProcesos();
			showToast("guardar GUARDAR_ACCIONESTRADE");
		}else if(accion==GUARDAR_ACCIONESTRADEPRODUCTO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_ACCIONESTRADEPRODUCTO");
		}else if(accion==GUARDAR_CLIENTE){
			removeContadorProcesos();
			showToast("guardar GUARDAR_CLIENTE");
		}else if(accion==GUARDAR_POSICION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_POSICION");
		}else if(accion==GUARDAR_PRESENTACION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_PRESENTACION");
		}else if(accion==GUARDAR_PUNTO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_PUNTO");
		}else if(accion==GUARDAR_EVALUACION){
			removeContadorProcesos();
			processAsync(DESCARGAR_EVALUACION_SKU);
			processAsync(DESCARGAR_EVALUACION_OPORTUNIDAD);
			processAsync(DESCARGAR_EVALUACION_POSICION);
			processAsync(DESCARGAR_EVALUACION_PRESENTACION);
			processAsync(DESCARGAR_EVALUACION_POSICION_COMPROMISO);
			showToast("guardar GUARDAR_EVALUACION");
		}else if(accion==GUARDAR_EVALUACION_OPORTUNIDAD){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION");
		}else if(accion==GUARDAR_EVALUACION_POSICION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_POSICION");
		}else if(accion==GUARDAR_EVALUACION_PRESENTACION){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_PRESENTACION");
		}else if(accion==GUARDAR_EVALUACION_SKU){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_SKU");
		}else if(accion==GUARDAR_EVALUACION_POSICION_COMPROMISO){
			removeContadorProcesos();
			showToast("guardar GUARDAR_EVALUACION_POSICION_COMPROMISO");
		}*/
		
		
		
		
	}
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		
		removeContadorProcesos();
		Log.i("processError - accion:", String.valueOf(accion));
		ProcesoInfoTO proceso = processById(accion);
		proceso.estado=ProcesoInfoTO.ESTADO_ERROR;
		proceso.isExito=false;
		
		Log.d(TAG, String.format("Error descarga, Accion = ,: %s",accion));
		
		switch (accion) {
			case DESCARGAR_PRODUCTO:{
				proceso.descripcion="Error descargando producto";
				break;
			}
			case GUARDAR_PRODUCTO:{
				proceso.descripcion="Error guardando producto";
				break;
			}
			case DESCARGAR_OPORTUNIDAD:{
				proceso.descripcion="Error descargando oportunidades";
				break;
			}
			case GUARDAR_OPORTUNIDAD:{
				
				proceso.descripcion="Error guardando oportunidades";
				
				break;
			}
			case DESCARGAR_SKU:{
				proceso.descripcion="Error descargando SKUs";
				break;
			}
			case GUARDAR_SKU:{
				
				proceso.descripcion="Error guardando SKUs";
				
				break;
			}
			
			case DESCARGAR_ACCIONESTRADE:{
				
				proceso.descripcion="Error descargando Acciones Trade";
				
				break;
			}
			case GUARDAR_ACCIONESTRADE:{
				
				proceso.descripcion="Error guardando Acciones Trade";
				
				break;
			}
			
			case DESCARGAR_CLIENTE:{
				
				proceso.descripcion="Error descargando Clientes";
				
				break;
			}
			case GUARDAR_CLIENTE:{
				
				proceso.descripcion="Error guardando Clientes";
				
				break;
			}
			
			case DESCARGAR_POSICION:{
				
				proceso.descripcion="Error descargando Posici—n";
				
				break;
			}
			case GUARDAR_POSICION:{
				
				proceso.descripcion="Error guardando Posici—n";
				
				break;
			}
			
			
			case DESCARGAR_PRESENTACION:{
				
				proceso.descripcion="Error descargando Presentaci—n";
				
				break;
			}
			case GUARDAR_PRESENTACION:{
				
				proceso.descripcion="Error guardando Presentaci—n";
				
				break;
			}
			case DESCARGAR_PUNTO:{
				
				proceso.descripcion="Error descargando Punto";
				
				break;
			}
			case GUARDAR_PUNTO:{
				
				proceso.descripcion="Error guardando Punto";
				
				break;
			}
			
			case DESCARGAR_EVALUACION:{
				
				proceso.descripcion="Error descargando Evaluaciones";
				
				break;
			}
			
			case DESCARGAR_PROFIT:{
				
				proceso.descripcion="Error descargando profit";
				
				break;
			}
			
			case DESCARGAR_MOTIVO:{
				
				proceso.descripcion="Error descargando motivos";
				
				break;
			}
			
			case DESCARGAR_ACELERADOR:{
				
				proceso.descripcion="Error descargando aceleradores";
			
				break;
			}
			
			case GUARDAR_EVALUACION:{

				proceso.descripcion="Error guardando Evaluaciones";

				break;
			}
			
			case DESCARGAR_ARTICULO_CANJE:{
				
				proceso.descripcion="Error descargando articulos canje";
				
				break;
			}
			
			case GUARDAR_ARTICULO_CANJE:{
				
				proceso.descripcion="Error guardando articulos canje";
				
				break;
			}
			
			
			case DESCARGAR_TIPO_ACTIVO:{
				
				proceso.descripcion="Error descargando tipo activo";
				
				break;
			}
			
			case GUARDAR_TIPO_ACTIVO:{
				
				proceso.descripcion="Error guardando tipo activo";
				
				break;
			}
			
			
		}
		
		
		adap.notifyDataSetChanged();
		Log.i("processError", String.valueOf(accion));
		
		savePreferencia(ConstantesApp.DESCARGA_NO_REALIZADA,usuarioTO.codigoRuta);
		super.processError();
	}
	
	private void savePreferencia(int value,String ruta){
		
		Log.d(TAG, String.format("ConstantesApp.DESCARGA_KEY,: %s",value));
		SharedPreferences.Editor Ed=prefs.edit();
		Ed.putInt(ConstantesApp.DESCARGA_KEY, value);	
		Ed.putString(ConstantesApp.RUTA_KEY, ruta);	
		Ed.commit();
	}
	
	 public static class EfficientAdapter extends ArrayAdapter<ProcesoInfoTO>{
		 private final List<ProcesoInfoTO> detalle;
		 private final DescargaData_Activity context;
		 
		 public EfficientAdapter(DescargaData_Activity context,List<ProcesoInfoTO> detalle){
				super(context, R.layout.descargadata_content, detalle);
				this.detalle = detalle;
				this.context = context;
			}
		 
		 
		 
		 public int getCount() {
				// TODO Auto-generated method stub
				if (detalle == null) {
					return 0;
				} else {
					return detalle.size();
				}
			}
			
			public ProcesoInfoTO getItem(int position) {
				// TODO Auto-generated method stub
				return this.detalle.get(position);
			}
			
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
			public View getView(final int position, View convertView, ViewGroup parent) {
				View view = null;	
				
				if (convertView == null) {
					
					LayoutInflater inflator = context.getLayoutInflater();
					view = inflator.inflate(R.layout.descargadata_content, null);
					
					final ViewHolder holder = new ViewHolder();
					
					holder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
					holder.imgEstado = (ImageView) view.findViewById(R.id.imgEstado);
					holder.btnProcesar = (ImageButton) view.findViewById(R.id.btnProcesar);
					
					holder.btnProcesar.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ProcesoInfoTO procesoInfoTO = (ProcesoInfoTO)holder.txtDescripcion.getTag();
							if(procesoInfoTO.processId<=DESCARGAR_EVALUACION_POSICION_COMPROMISO){
								context.processAsync(procesoInfoTO.processId);
							}
						}
					});
					view.setTag(holder);
					
					
			    	holder.txtDescripcion.setTag(this.detalle.get(position));
			    	
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtDescripcion.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				ProcesoInfoTO procesoInfoTO = getItem(position);
				holder.txtDescripcion.setText(procesoInfoTO.descripcion);
				switch (procesoInfoTO.estado) {
				case ProcesoInfoTO.ESTADO_INICIO:
					holder.imgEstado.setImageResource(R.drawable.icon_inicio);
					break;
				case ProcesoInfoTO.ESTADO_DESCARGA:
					holder.imgEstado.setImageResource(R.drawable.icon_rojo);
					break;
				case ProcesoInfoTO.ESTADO_DB:
					holder.imgEstado.setImageResource(R.drawable.icon_amarrillo);
					break;
				case ProcesoInfoTO.ESTADO_OK:
					holder.imgEstado.setImageResource(R.drawable.icon_ok);
					break;
				case ProcesoInfoTO.ESTADO_ERROR:
						holder.imgEstado.setImageResource(R.drawable.icon_error);
					break;
				}
				
				
				if(procesoInfoTO.processId<=DESCARGAR_EVALUACION_POSICION_COMPROMISO){
					if(procesoInfoTO.estado==ProcesoInfoTO.ESTADO_ERROR){
						holder.btnProcesar.setVisibility(View.VISIBLE);
					}else{
						holder.btnProcesar.setVisibility(View.INVISIBLE);
					}
				}else{
					holder.btnProcesar.setVisibility(View.INVISIBLE);
				}
				return view;
				
			}
			
			static class ViewHolder {
				TextView txtDescripcion;
				ImageView imgEstado;
				ImageButton btnProcesar;
			}
				
			
	 }
	
}
