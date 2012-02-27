package pe.lindley.red.activity;

import java.text.DecimalFormat;
import java.util.List;


import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
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
import pe.lindley.red.to.SoviCoreTO;
import pe.lindley.red.ws.service.ConsultarSoviCoreProxy;
import pe.lindley.util.ActivityGroupBase;
import roboguice.inject.InjectResource;

public class CoreBrandsMixActivity extends ActivityGroupBase {

	public static boolean llamarWS=false;
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	public static final String PERIODO_KEY="periodo";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	
	@Inject ConsultarSoviCoreProxy consultarSoviCoreProxy;
	
	@InjectResource(R.string.custom_barras_lila) String color_Lila;
	@InjectResource(R.string.custom_barras_verde) String color_Verde;
	@InjectResource(R.string.custom_barras_azul_marino) String color_azul_marino;
	@InjectResource(R.string.custom_barras_mostaza) String color_mostaza;
	@InjectResource(R.string.custom_barras_naranja) String color_naranja;
	
	@InjectResource(R.string.custom_barras_plomo) String color_plomo;
	@InjectResource(R.string.custom_barras_verde_agua) String color_verde_agua;
	
	@InjectResource(R.string.red_ejecucion_anio) String red_ejecucion_anio;
	@InjectResource(R.string.red_core_brands_title) String red_core_brands_title;
	
	
	
	private String cliente;
	private String periodo;
	private String codigoCliente;
	private String titulo_grafico;

	@Override
	protected void process() {
		
		consultarSoviCoreProxy.setCodigoCliente(codigoCliente);
		consultarSoviCoreProxy.setPeriodo(this.periodo);
		consultarSoviCoreProxy.execute();
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
			codigoCliente = intent.getStringExtra(CLIENTE_CODIGO_KEY);
			cliente = intent.getStringExtra(CLIENTE_DESCRIPCION_KEY);
			periodo = intent.getStringExtra(PERIODO_KEY);
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

			renderer.setAxesColor(Color.BLACK);
			renderer.setLabelsColor(Color.BLACK);
			renderer.setLegendTextSize(15);
		    

		  }
	 
	
	public void execute(Context context) {
		if(!consultarSoviCoreProxy.isExito()){
			if(consultarSoviCoreProxy.getResponse().getStatus()!=0){
				showToast("Error consultando informaci—n, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarSoviCoreProxy.getResponse().getData()==null){
			finish();
		}
		
		
		List<SoviCoreTO> data = consultarSoviCoreProxy.getResponse().getData();
		
		int index=0;
		int indexColor=0;
		 
		int[] colorsBase = new int[] { 
				Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN,Color.RED,Color.DKGRAY,Color.LTGRAY,
				Color.parseColor(color_plomo),
				Color.parseColor(color_verde_agua),
				Color.parseColor(color_Lila), 
				Color.parseColor(color_Verde),	
				};

		
		int size = data.size();
		double[] carasVisibles = new double[size];
		String[] marcas = new String[size];
		int[] colors = new int[size];
		
		
		for (SoviCoreTO soviCoreTO : data) {
			
			DecimalFormat formatter = new DecimalFormat("## %");
			
			
			
			carasVisibles[index]=soviCoreTO.getCarasVisibles();
			marcas[index] = soviCoreTO.getMarca() + "-" + String.valueOf(soviCoreTO.getCarasVisibles());
			
			
			
			if(indexColor==colorsBase.length) indexColor=0;
			colors[index]=colorsBase[indexColor++];
			
			index++;
		}

	   
	    
	    titulo_grafico = String.format(red_core_brands_title, cliente + "-Periodo:" + periodo);
	
	    DefaultRenderer renderer = buildCategoryRenderer(colors);
	
	    Intent i = (ChartFactory.getPieChartIntent(context, buildCategoryDataset("rrr", carasVisibles,marcas), renderer,"Titulo"));
	    
	    
	   View view = getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
	   this.setContentView(view);

	  }
	
	
	  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		    DefaultRenderer renderer = new DefaultRenderer();
		    
		    renderer.setLabelsTextSize(20);
		  
		    renderer.setChartTitle(titulo_grafico);
		    renderer.setChartTitleTextSize(20);
		    
		    renderer.setLabelsTextSize(20);
		    renderer.setMargins(new int[] {25,15,0,5});
		    renderer.setExternalZoomEnabled(true);
		    renderer.setShowLabels(true);
		    renderer.setFitLegend(true);
		    renderer.setShowAxes(true);
		    renderer.setShowGrid(true);
		    renderer.setZoomEnabled(true);
		    renderer.setZoomRate(1.1f);
		    renderer.setLabelsColor(Color.BLACK);
		    renderer.setLegendHeight(50);
		    renderer.setShowLabels(true);
		    
		    renderer.setMargins(new int[] { 10, 10, 10, 10 });
		    for (int color : colors) {
		      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		     r.setDisplayChartValues(true);
		    
		      r.setColor(color);
		      renderer.addSeriesRenderer(r);
		    }
		    return renderer;
		  }
	  
	  protected CategorySeries buildCategoryDataset(String title, double[] values,String[] titulos) {
		    CategorySeries series = new CategorySeries("RPUASFAS");
		    
		    int k = 0;
		    for (double value : values) {
		      series.add(titulos[k++], value);
		    }

		    return series;
		  }
	 
}
