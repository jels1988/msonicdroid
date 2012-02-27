package pe.lindley.profit.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import pe.lindley.profit.to.HistoryDetalleTO;
import pe.lindley.util.ArrayUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class ProfitHistoryComparativoActivity extends Activity {
	public static final int CAJAS_FISICAS_ACUMULADAS=0;
	public static final int CAJAS_UNITARIAS_ACUMULADAS=1;
	public static final int IMPORTE_FACTURADO=2;
	public static final int IMPORTE_UTILIDAD=3;
	
	private ArrayList<HistoryDetalleTO> detalle;

	public ArrayList<HistoryDetalleTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<HistoryDetalleTO> detalle) {
		this.detalle = detalle;
	}
	
	private int año;
	
	public int getAño() {
		return año;
	}

	public int getCampo() {
		return campo;
	}

	public void setCampo(int campo) {
		this.campo = campo;
	}

	public void setAño(int año) {
		this.año = año;
	}
	
	private int campo;
	
	private String titulo;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Intent execute(Context context) {
		
		String[] titles = new String[] { String.valueOf(this.año-1), String.valueOf(this.año) };
		
		int añoAnterior=this.año-1;
		int añoActual=this.año;
		
		double[] anioAnterior = new double[12]; 
		double[] anioActual = new double[12];
		
		for (HistoryDetalleTO data : detalle) {
			if(data.getAnio()==añoActual){
				
				switch (campo){
					case CAJAS_FISICAS_ACUMULADAS:
						anioActual[data.getMes()-1] = data.getCajasFacturadas();
						break;
					case CAJAS_UNITARIAS_ACUMULADAS:
						anioActual[data.getMes()-1] = data.getCajasUnitarias();
						break;
					case IMPORTE_FACTURADO:
						anioActual[data.getMes()-1] = data.getImporteFacturado();
						break;
					case IMPORTE_UTILIDAD:
						anioActual[data.getMes()-1] = data.getUtilidad();
						break;
				}
				
				
				
				
				
			}
			
			if(data.getAnio()==añoAnterior){
				switch (campo){
				case CAJAS_FISICAS_ACUMULADAS:
					anioAnterior[data.getMes()-1] = data.getCajasFacturadas();
					break;
				case CAJAS_UNITARIAS_ACUMULADAS:
					anioAnterior[data.getMes()-1] = data.getCajasUnitarias();
					break;
				case IMPORTE_FACTURADO:
					anioAnterior[data.getMes()-1] = data.getImporteFacturado();
					break;
				case IMPORTE_UTILIDAD:
					anioAnterior[data.getMes()-1] = data.getUtilidad();
					break;
			}
		}
	}
		
		
		
		List<double[]> values = new ArrayList<double[]>();
		values.add(anioAnterior);
		values.add(anioActual);
		    
		int[] colors = new int[] { Color.parseColor("#6d1e7e"), Color.BLUE };
		
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		renderer.setOrientation(Orientation.HORIZONTAL);
		

		double maxActual = ArrayUtil.getMaxValue(anioActual); 
		double maxAnterior = ArrayUtil.getMaxValue(anioAnterior);
		
		if(maxActual>maxAnterior)
			maxAnterior=maxActual;
		
		/*double minActual = ArrayUtil.getMinValue(anioActual);
		double minAnterior = ArrayUtil.getMinValue(anioAnterior);
		
		
		if(minActual<minAnterior)
			minAnterior=minActual;
		*/
		renderer.setZoomEnabled(false, false);
	    setChartSettings(renderer, titulo, "Meses", "", 0.5,
	        12.5, 0, maxAnterior * 1.05, Color.parseColor("#6d1e7e"), Color.GREEN);
	    
	    renderer.setXLabels(1);
	    renderer.setYLabels(10);
	    renderer.addTextLabel(1, "EN");
	    renderer.addTextLabel(2, "FE");
	    renderer.addTextLabel(3, "MA");
	    renderer.addTextLabel(4, "AB");
	    renderer.addTextLabel(5, "MA");
	    renderer.addTextLabel(6, "JN");
	    renderer.addTextLabel(7, "JL");
	    renderer.addTextLabel(8, "AG");
	    renderer.addTextLabel(9, "SP");
	    renderer.addTextLabel(10, "OC");
	    renderer.addTextLabel(11, "NV");
	    renderer.addTextLabel(12, "DC");
	    renderer.setDisplayChartValues(true);
	    
	    
	    return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.DEFAULT);
	    
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
		    
		    renderer.setAxesColor(axesColor);
		    renderer.setLabelsColor(labelsColor);
		    

		  }
	  
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    
	    renderer.setShowLegend(true);
	    
	    renderer.setAxisTitleTextSize(20);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(20);
	    renderer.setLegendTextSize(20);
	    renderer.setLegendHeight(50);
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
	
}
