package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import pe.lindley.activity.R;
import pe.lindley.red.to.SoviPuntoContactoTO;
import pe.lindley.red.ws.service.ConsultarSoviPuntoContactoProxy;
import pe.lindley.util.ActivityGroupBase;
import pe.lindley.util.ArrayUtil;
import roboguice.inject.InjectResource;

public class SoviMixPuntoContactoActivity extends ActivityGroupBase {

	public static boolean llamarWS=false;
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String PERIODO_KEY="periodo";
	
	
	
	@Inject ConsultarSoviPuntoContactoProxy consultarSoviPuntoContactoProxy;
	@InjectResource(R.string.custom_barras_lila) String color_Lila;
	@InjectResource(R.string.custom_barras_verde) String color_Verde;
	@InjectResource(R.string.custom_barras_azul_marino) String color_azul_marino;
	@InjectResource(R.string.custom_barras_mostaza) String color_mostaza;
	@InjectResource(R.string.custom_barras_naranja) String color_naranja;
	@InjectResource(R.string.custom_barras_plomo) String color_plomo;
	@InjectResource(R.string.custom_barras_verde_agua) String color_verde_agua;
	@InjectResource(R.string.red_ejecucion_anio) String red_ejecucion_anio;
	
	private String cliente;
	private String periodo;
	private String codigoCliente;


	@Override
	protected void process() {
		
		consultarSoviPuntoContactoProxy.setCodigoCliente(codigoCliente);
		consultarSoviPuntoContactoProxy.setPeriodo(this.periodo);
		consultarSoviPuntoContactoProxy.execute();
	}
	
	
	@Override
	protected void processOk() {
		super.processOk();
		execute(getApplicationContext());
		llamarWS=true;
	}
	
	@Override
	protected void processError() {
		super.processOk();
		llamarWS=false;
	}
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		cliente = intent.getStringExtra(CLIENTE_DESCRIPCION_KEY);
		periodo = intent.getStringExtra(PERIODO_KEY);
		codigoCliente = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		
		periodo = periodo.replace("/","").substring(0,6);
		
		if(!llamarWS){
			processAsync();
		}else{
			execute(getApplicationContext());
		}
	}
	 

	protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
		      String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
		      int labelsColor) {
		  
		  
		    renderer.setChartTitle(title);
		    renderer.setXTitle(xTitle);
		    renderer.setYTitle(yTitle);
		    renderer.setXAxisMin(xMin);
		    renderer.setXAxisMax(xMax);
		    renderer.setYAxisMin(yMin);
		    renderer.setYAxisMax(yMax);
		    
		    renderer.setYLabelsAlign(Align.CENTER);
			renderer.setShowGrid(true);
			renderer.setShowLegend(true);
			renderer.setAxesColor(axesColor);
			renderer.setLabelsColor(labelsColor);
		    

		  }
	 
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    
	    renderer.setShowLegend(true);
	    renderer.setLegendHeight(80);
	    renderer.setAxisTitleTextSize(20);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(20);
	    renderer.setMargins(new int[] {25,15,0,5});
	    
	    int length = colors.length;
	    for (int i = 0; i < length; i++) {
	      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	      r.setColor(colors[i]);
	      renderer.addSeriesRenderer(r);
	      renderer.setChartValuesTextSize(20);
	      
	    }
	    return renderer;
	  }
	
	
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	   
	    int length = titles.length;
	    for (int i = 0; i < length; i++) {
	      CategorySeries series = new CategorySeries(titles[i]);
	      double[] v = values.get(i);
	      int seriesLength = v.length;
	      for (int k = 0; k < seriesLength; k++) {
	        series.add(v[k]);
	      }
	      dataset.addSeries(series.toXYSeries());
	    }
	    return dataset;
	  }
	
	public void execute(Context context) {
		if(!consultarSoviPuntoContactoProxy.isExito()){
			if(consultarSoviPuntoContactoProxy.getResponse().getStatus()!=0){
				showToast("Error consultando informaci—n, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarSoviPuntoContactoProxy.getResponse().getData()==null){
			finish();
		}
		
		
		int[] colorsBase = new int[] { 
				Color.BLUE, 
				Color.GREEN, 
				Color.MAGENTA, 
				Color.BLACK, 
				Color.CYAN,
				Color.RED,
				Color.DKGRAY,
				Color.LTGRAY,
				Color.parseColor(color_plomo),
				Color.parseColor(color_verde_agua),
				Color.parseColor(color_Lila), 
				Color.parseColor(color_Verde),	
				};
		
		List<SoviPuntoContactoTO> data = consultarSoviPuntoContactoProxy.getResponse().getData();
		
		List<double[]> values = new ArrayList<double[]>();
		
		
		ArrayList<String> empresas = new ArrayList<String>();
		ArrayList<String> tipoContactos = new ArrayList<String>();
		for (SoviPuntoContactoTO soviPuntoContactoTO : data) {
			
			String nombreEmpresa = soviPuntoContactoTO.getCompania().toUpperCase();
			if(!empresas.contains(nombreEmpresa)){
				empresas.add(nombreEmpresa.toUpperCase());
			}
			
			String tipoContacto = soviPuntoContactoTO.getTipoPuntoContacto().toUpperCase();
			if(!tipoContactos.contains(tipoContacto)){
				tipoContactos.add(tipoContacto.toUpperCase());
			}
		}
		
		
		
		int[] colors = new int[empresas.size()];
		int indexColor=0;
		int tipoContactosSize = tipoContactos.size();
		for (String nombre : empresas) {
			
			double[] valores = new double[tipoContactosSize];
			for(String tipoContacto:tipoContactos){
				for (SoviPuntoContactoTO soviPuntoContactoTO : data) {
					if(soviPuntoContactoTO.getCompania().toUpperCase().compareTo(nombre)==0){
						if(tipoContacto.compareTo(soviPuntoContactoTO.getTipoPuntoContacto().toUpperCase())==0){
							valores[tipoContactos.indexOf(tipoContacto)]=soviPuntoContactoTO.getCarasVisibles();
						}
					}
				}
			}
		
			
			
			values.add(valores);	
			
			if(indexColor==colorsBase.length) indexColor=0;
			colors[indexColor]=colorsBase[indexColor++];
		}
		
		
		String[] titles = tipoContactos.toArray(new String[tipoContactos.size()]);
		
			
			
			
		int maxInt=0;
		double[] maximos = new double[values.size()];
		for (double[] valores : values) {
			maximos[maxInt++]=ArrayUtil.getMaxValue(valores);
		}
		

		double max = ArrayUtil.getMaxValue(maximos);
	    
	    
	    String titulo_grafico = String.format(red_ejecucion_anio, cliente);
	    
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
	    
	    setChartSettings(renderer, titulo_grafico, "", "", 0,3, 0,max * 1.05, 
	    			Color.parseColor(color_Lila), 
	    			Color.parseColor(color_Verde));
	    
	    renderer.setXLabels(2);
	    
	    int indexanio=1;
	    
	    
	    for (String title : titles) {
	    	renderer.addTextLabel(indexanio++, title);
		}

	    
	    renderer.setYLabels(10);
	    renderer.setDisplayChartValues(true);
	    renderer.setXLabelsAlign(Align.LEFT);
	    renderer.setYLabelsAlign(Align.LEFT);
	    // renderer.setPanEnabled(false);
	    // renderer.setZoomEnabled(false);
	    renderer.setZoomRate(1.1f);
	    renderer.setBarSpacing(0.5);
	    
	    String[] companias = empresas.toArray(new String[empresas.size()]);
	    Intent i = (ChartFactory.getBarChartIntent(context, buildBarDataset(companias, values), renderer,Type.STACKED));
	    
	    
	   View view = getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
	   this.setContentView(view);

	   
	    //return (ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.STACKED));

	      
	  }
	 
}
