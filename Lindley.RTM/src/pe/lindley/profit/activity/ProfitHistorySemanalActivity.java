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

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import pe.lindley.profit.to.HistoryDetalleTO;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.ArrayUtil;

public class ProfitHistorySemanalActivity extends ActivityBase {
	public static final int CAJAS_FISICAS_ACUMULADAS = 0;
	public static final int CAJAS_UNITARIAS_ACUMULADAS = 1;
	public static final int IMPORTE_FACTURADO = 2;
	public static final int IMPORTE_UTILIDAD = 3;

	private ArrayList<HistoryDetalleTO> detalle;
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<HistoryDetalleTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<HistoryDetalleTO> detalle) {
		this.detalle = detalle;
	}

	private int anio;

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	private int campo;

	public int getCampo() {
		return campo;
	}

	public void setCampo(int campo) {
		this.campo = campo;
	}

	private int mes;

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Intent execute(Context context) {

		String[] titles = new String[] { String.format("%s %s", this.anio,
				this.mes) };

		double[] valorSemana = new double[5];
		int[] tituloSemana = new int[5];
		
		int index = 0;

		

		for (HistoryDetalleTO data : detalle) {
			if (data.getMes() == this.getMes()) {
				
				tituloSemana[index] = data.getSemana();
				
				switch (campo) {
				case CAJAS_FISICAS_ACUMULADAS:
					valorSemana[index] = data.getCajasFacturadas();
					break;
				case CAJAS_UNITARIAS_ACUMULADAS:
					valorSemana[index] = data.getCajasUnitarias();
					break;
				case IMPORTE_FACTURADO:
					valorSemana[index] = data.getImporteFacturado();
					break;
				case IMPORTE_UTILIDAD:
					valorSemana[index] = data.getUtilidad();
					break;
				}
				index++;
			}
		}
		
		double[] anioActual = new double[index];
		System.arraycopy(valorSemana, 0, anioActual, 0, index);
		

		List<double[]> values = new ArrayList<double[]>();
		values.add(anioActual);

		int[] colors = new int[] { Color.parseColor("#6d1e7e") };

		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		renderer.setOrientation(Orientation.HORIZONTAL);

		double maxActual = ArrayUtil.getMaxValue(anioActual);
		
		int semanaMin = 0;
		int semanaMax = index + 1;

		renderer.setZoomEnabled(false, false);
		
		setChartSettings(renderer, titulo, "Meses", "", semanaMin, semanaMax, 0,
				maxActual * (1.05), Color.parseColor("#6d1e7e"), Color.GREEN);

		renderer.setXLabels(0);
		renderer.setYLabels(10);
		
		for (int i = 0; i < index; i++) {
			renderer.addTextLabel(i + 1 , String.valueOf(tituloSemana[i]));
		}
		
		

		renderer.setDisplayChartValues(true);

		return ChartFactory.getBarChartIntent(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);

	}

	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
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
		renderer.setMargins(new int[] { 25, 15, 0, 5 });

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
			renderer.setChartValuesTextSize(20);

		}
		return renderer;
	}

	protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
			List<double[]> values) {
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
