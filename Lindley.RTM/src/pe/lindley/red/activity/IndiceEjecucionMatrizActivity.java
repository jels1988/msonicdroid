package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import com.google.inject.Inject;
import pe.lindley.activity.R;
import pe.lindley.red.to.IndiceEjecucionMatrizTO;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionMatrizProxy;
import pe.lindley.util.ActivityGroupBase;
import pe.lindley.util.ArrayUtil;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;

public class IndiceEjecucionMatrizActivity extends ActivityGroupBase {
	
	public static boolean llamarWS=false;
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	
	public static final String PERIODO_KEY="periodo";
	
	
	@Inject ConsultarIndiceEjecucionMatrizProxy consultarIndiceEjecucionMatrizProxy;
	@InjectResource(R.string.custom_barras_lila) String color_Lila;
	@InjectResource(R.string.custom_barras_verde) String color_Verde;
	@InjectResource(R.string.custom_barras_azul_marino) String color_azul_marino;
	@InjectResource(R.string.custom_barras_mostaza) String color_mostaza;
	@InjectResource(R.string.custom_barras_naranja) String color_naranja;
	@InjectResource(R.string.red_ejecucion_anio) String red_ejecucion_anio;
	
	private String cliente;
	private String periodo;
	


	@Override
	protected void process() {
		
		consultarIndiceEjecucionMatrizProxy.setPeriodo(this.periodo);
		consultarIndiceEjecucionMatrizProxy.execute();
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
	    renderer.setLegendHeight(50);
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
		if(!consultarIndiceEjecucionMatrizProxy.isExito()){
			if(consultarIndiceEjecucionMatrizProxy.getResponse().getStatus()!=0){
				showToast("Error consultando informaci—n, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarIndiceEjecucionMatrizProxy.getResponse().getData()==null){
			finish();
			return;
		}
		
		String[] titles = new String[] { "EJE", "INV","POS","PRE" };
		
		List<IndiceEjecucionMatrizTO> data = consultarIndiceEjecucionMatrizProxy.getResponse().getData();
		
		int index=0;
		List<double[]> values = new ArrayList<double[]>();
		
		int size = data.size();
		double[] eje = new double[size];
		double[] inv = new double[size];
		double[] pos = new double[size];
		double[] pre = new double[size];
		String[] tiposCliente = new String[size];
		
		for (IndiceEjecucionMatrizTO indiceEjecucionMatrizTO : data) {
			eje[index]=ArrayUtil.round(indiceEjecucionMatrizTO.getEjecucion(), 0);
			inv[index]=ArrayUtil.round(indiceEjecucionMatrizTO.getInventario(), 0);
			pos[index]=ArrayUtil.round(indiceEjecucionMatrizTO.getPosicion(), 0);
			pre[index]=ArrayUtil.round(indiceEjecucionMatrizTO.getPresentacion(), 0);
			
			
			String tipoCliente =indiceEjecucionMatrizTO.getTipoCliente().toUpperCase();
			if ("SOCIO ESTRATEGICO".compareTo(tipoCliente)==0){
				tiposCliente[index]="SE";
			}else if ("LIDER OBJETIVO".compareTo(tipoCliente)==0){
				tiposCliente[index]="LO";
			}else if ("SOCIO OBJETIVO".compareTo(tipoCliente)==0){
				tiposCliente[index]="SO";
			}else if ("CLIENTE TRANSACCIONAL".compareTo(tipoCliente)==0){
				tiposCliente[index]="CT";
			}else{
				tiposCliente[index]="";
			}
			 
			index++;
		}
		
	    values.add(eje);
	    values.add(inv);
	    values.add(pos);
	    values.add(pre);
	    
	    double[] maximos = new double[]{ArrayUtil.getMaxValue(eje),
	    								ArrayUtil.getMaxValue(inv),
	    								ArrayUtil.getMaxValue(pos),
	    								ArrayUtil.getMaxValue(pre)};
	    
	    double max = ArrayUtil.getMaxValue(maximos);
	    
	    int[] colors = new int[] { Color.parseColor(color_Lila), 
	    							Color.parseColor(color_Verde),	
	    							Color.parseColor(color_azul_marino),	
	    							Color.parseColor(color_mostaza)};
	    
	    String titulo_grafico = String.format(red_ejecucion_anio, cliente);
	    
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
	    setChartSettings(renderer, titulo_grafico, "Tipo Cliente", "", 0,5, 0,max * 1.05, 
	    			Color.parseColor(color_Lila), 
	    			Color.parseColor(color_Verde));
	    
	    renderer.setXLabels(0);
	    
	    int indexanio=1;
	    
	    for (String tipo : tiposCliente) {
	    	renderer.addTextLabel(indexanio++, tipo);
		}

	    
	    renderer.setYLabels(10);
	    renderer.setDisplayChartValues(true);
	    renderer.setXLabelsAlign(Align.CENTER);
	    renderer.setYLabelsAlign(Align.LEFT);
	    // renderer.setPanEnabled(false);
	    // renderer.setZoomEnabled(false);
	    renderer.setZoomRate(1.1f);
	    renderer.setBarSpacing(0.1);
	    
	    Intent i = (ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.STACKED));
	    
	    
	   View view = getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
	   this.setContentView(view);

	   
	    //return (ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.STACKED));

	      
	  }
	
}
