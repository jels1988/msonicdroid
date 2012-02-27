package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.red.to.InventarioPrecioSugeridoTO;
import pe.lindley.red.ws.service.ConsultarInventarioPrecioSugeridoProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
import com.steema.teechart.themes.ThemesList;


public class InventarioPrecioSugeridoActivity extends ActivityBase {

	public static boolean llamarWS=false;
	private TChart chart;
	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
		
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject ConsultarInventarioPrecioSugeridoProxy precioSugeridoProxy;
	
	List<InventarioPrecioSugeridoTO> detalle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_chartview);
		//mActionBar.setTitle(R.string.consultar_encuesta_title);
	    //mActionBar.setHomeLogo(R.drawable.header_logo);
	    //mActionBar.setSubTitle(cliente_descripcion);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(true);
		
		ThemesList.applyTheme(chart.getChart(), 1);
		/*if(!llamarWS){
			processAsync();
		}else{
			selectSerie(detalle);	
		}*/
		processAsync();
	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		fecha_encuesta = fecha_encuesta.replace("/","");
		precioSugeridoProxy.setAnioMes(fecha_encuesta);
		precioSugeridoProxy.setCodigoCliente(cliente_codigo);
		precioSugeridoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = precioSugeridoProxy.isExito();
		
		if (isExito) {
			int status = precioSugeridoProxy.getResponse().getStatus();
			if (status == 0) {		
				llamarWS=true;
				detalle = precioSugeridoProxy.getResponse().getData();							
				selectSerie(detalle);				
			}
			else
			{
				showToast(precioSugeridoProxy.getResponse().getDescripcion());
				finish();
			}
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub	
		super.processError();
		llamarWS=false;
		showToast(error_generico_process);
	}
	
	public void selectSerie(List<InventarioPrecioSugeridoTO> detalle) {
		
		chart.removeAllSeries();
					
		ArrayList<String> resultados = new ArrayList<String>();
		ArrayList<String> marcas = new ArrayList<String>();
		for (InventarioPrecioSugeridoTO precioSugeridoTO : detalle) {
			
			String marca = precioSugeridoTO.getMarca().toUpperCase();
			if(!marcas.contains(marca)){
				marcas.add(marca.toUpperCase());
			}
			
			String resultado = precioSugeridoTO.getResultado().toUpperCase();
			if(!resultados.contains(resultado)){
				resultados.add(resultado);
			}
		}
		
		int sizeMar = marcas.size();
		StringList sLabel = new StringList(sizeMar);
		int pos = 0;
		for (String resultado : resultados) {
		
						
			double[] valores = new double[sizeMar];
			
				for(String marca : marcas){
				for (InventarioPrecioSugeridoTO precioSugeridoTO : detalle) {
					if(precioSugeridoTO.getResultado().toUpperCase().compareTo(resultado)==0){
						if(marca.compareTo(precioSugeridoTO.getMarca().toUpperCase())==0){
							valores[marcas.indexOf(marca)]=precioSugeridoTO.getCantidad();
						}
					}
				}
				sLabel.add(pos, marca);pos++;
			}
			
			Series bar = new Bar(chart.getChart());
			bar.setTitle(resultado);
			bar.setShowInLegend(true);				
			bar.add(valores);		
			//bar.setLabels(sLabel);
		}
		 
		

		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		
		chart.getHeader().setText("Cumple Precio Sugerido x SKU");
		chart.getHeader().getFont().setSize(20);		    
	}
		


}
