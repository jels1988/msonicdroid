package lindley.desarrolloxcliente.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.ProfitTO;
import lindley.desarrolloxcliente.ws.service.ConsultarProfitProxy;
import net.msonic.lib.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
import com.steema.teechart.themes.ThemesList;
import com.thira.examples.actionbar.widget.ActionBar;

public class VerProfit_Activity extends ActivityBase {

	@Inject
	ConsultarProfitProxy consultarProfitProxy;
	private TChart chart;
	List<ProfitTO> detalle;
	@InjectView(R.id.actionBar)
	ActionBar mActionBar;

	public static final String ANIO = "pfanio";
	@InjectExtra(ANIO)
	String pf_anio;
	public static final String MES = "pfmes";
	@InjectExtra(MES)
	String pf_mes;
	public static final String CLIENTE = "pfcliente";
	@InjectExtra(CLIENTE)
	String pf_cliente;
	public static final String ARTICULO = "pcarticulo";
	@InjectExtra(ARTICULO)
	String pf_articulo;
	public static final String NOMBRE_ARTICULO = "nomarticulo";
	@InjectExtra(NOMBRE_ARTICULO)
	String nombre_articulo;
	private ClienteTO cliente;
	private MyApplication application;

	@InjectResource(R.string.cajas_fisicas)
	String cajasFisicas;
	@InjectResource(R.string.margen_actual)
	String margenActual;
	@InjectResource(R.string.cajas_faltantes)
	String cajasFaltantes;
	@InjectResource(R.string.margen_faltante)
	String margenFaltante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profitchartview_activity);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		mActionBar.setTitle(cliente.getCodigo() + " - " + cliente.getNombre());
		mActionBar.setHomeLogo(R.drawable.header_logo);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(true);
		
		//chart.getAxes().getLeft().getLabels().setValueFormat("#,##0.00;(#,##0.00)");
		
		
		selectTheme(1);
		processAsync();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	public void selectTheme(int themeSelection) {

		switch (themeSelection) {
		case 0: // Opera Theme Selected
			ThemesList.applyTheme(chart.getChart(), 0);
			break;
		case 1: // Black is back Theme Selected
			ThemesList.applyTheme(chart.getChart(), 1);
			break;
		case 2: // Default Theme Selected
			ThemesList.applyTheme(chart.getChart(), 2);
			break;
		case 3: // Excel Theme Selected
			ThemesList.applyTheme(chart.getChart(), 3);
			break;
		case 4: // Classic Theme Selected
			ThemesList.applyTheme(chart.getChart(), 4);
			break;
		case 5: // XP Theme Selected
			ThemesList.applyTheme(chart.getChart(), 5);
			break;
		case 6: // Web Theme Selected
			ThemesList.applyTheme(chart.getChart(), 6);
			break;
		case 7: // Business Theme Selected
			ThemesList.applyTheme(chart.getChart(), 7);
			break;
		case 8: // BlueSky Theme Selected
			ThemesList.applyTheme(chart.getChart(), 8);
			break;
		case 9: // Grayscale Theme Selected
			ThemesList.applyTheme(chart.getChart(), 9);
			break;

		}
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub}
		consultarProfitProxy.anio = this.pf_anio;
		consultarProfitProxy.mes = this.pf_mes;
		consultarProfitProxy.codigoArticulo = this.pf_articulo;
		consultarProfitProxy.codigoCliente = this.pf_cliente;
		consultarProfitProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarProfitProxy.isExito();

		if (isExito) {
			int status = consultarProfitProxy.getResponse().getStatus();
			if (status == 0) {
				detalle = consultarProfitProxy.getResponse().ListaProfit;
				selectSerie(detalle);
			} else {
				showToast(consultarProfitProxy.getResponse().getDescripcion());
				finish();
			}
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	public void selectSerie(List<ProfitTO> detalle) {

		chart.removeAllSeries();

		ArrayList<String> indicadores = new ArrayList<String>();
		ArrayList<String> variables = new ArrayList<String>();
		for (ProfitTO profitTO : detalle) {

			String nombreIndicadores = profitTO.nombreIndicador.toUpperCase();
			if (!indicadores.contains(nombreIndicadores)) {
				indicadores.add(nombreIndicadores.toUpperCase());
			}

			variables.add(cajasFisicas);
			variables.add(margenActual);
			variables.add(cajasFaltantes);
			variables.add(margenFaltante);
		}

		int sizeIndicadores = indicadores.size();
		StringList sLabel = new StringList(sizeIndicadores);
		int pos = 0;

		double[] varCajaFisica = new double[sizeIndicadores];
		double[] varMargenActual = new double[sizeIndicadores];
		double[] varCajaFaltante = new double[sizeIndicadores];
		double[] varMargenFaltante = new double[sizeIndicadores];
		
		for (String nombre : indicadores) {
			for (ProfitTO profitTO : detalle) {

				if (nombre.compareTo(profitTO.nombreIndicador.toUpperCase()) == 0) {

					/*
					BigDecimal bd = new BigDecimal(profitTO.cajasFisica * 100);
					BigDecimal rounded = bd.setScale(0,BigDecimal.ROUND_HALF_UP);
					varCajaFisica[indicadores.indexOf(nombre)] = rounded.doubleValue();

					bd = new BigDecimal(profitTO.margenActual * 100);
					rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
					varMargenActual[indicadores.indexOf(nombre)] = rounded.doubleValue();

					bd = new BigDecimal(profitTO.cajasFisicasFaltante * 100);
					rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
					varCajaFaltante[indicadores.indexOf(nombre)] = rounded.doubleValue();

					bd = new BigDecimal(profitTO.margenFaltante * 100);
					rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
					varMargenFaltante[indicadores.indexOf(nombre)] = rounded.doubleValue();
					*/
					
					varCajaFisica[indicadores.indexOf(nombre)] = profitTO.cajasFisica;
					varMargenActual[indicadores.indexOf(nombre)] = profitTO.margenActual;
					varCajaFaltante[indicadores.indexOf(nombre)] = profitTO.cajasFisicasFaltante;
					varMargenFaltante[indicadores.indexOf(nombre)] = profitTO.margenFaltante;
					
				}
			}
			sLabel.add(pos, nombre);
			pos++;
		}

		Series bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(0).toString());
		bar.setShowInLegend(true);
		bar.add(varCajaFisica);
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		chart.addSeries(bar);

		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(1).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varMargenActual);
		
		chart.addSeries(bar);
		
		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(2).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varCajaFaltante);
		chart.addSeries(bar);

		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(3).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varMargenFaltante);
		chart.addSeries(bar);

		
		bar = new Bar(chart.getChart());
		bar.setTitle("");
		bar.setShowInLegend(false);
		bar.setLabels(sLabel);
		
		bar.add();
		bar.add();
		bar.add();
		bar.add();

		chart.addSeries(bar);
		
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		chart.getLegend().getFont().setSize(15);
		chart.getAxes().getLeft().setIncrement(10);
		
		chart.getHeader().setText("Profit Story x SKU - " + nombre_articulo);
		chart.getHeader().getFont().setSize(20);
		
		//chart.getFooter().getFont().setSize(20);
	}

}