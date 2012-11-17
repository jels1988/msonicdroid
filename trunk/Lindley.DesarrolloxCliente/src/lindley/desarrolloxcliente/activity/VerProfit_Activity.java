package lindley.desarrolloxcliente.activity;

import java.util.List;

import net.msonic.lib.sherlock.ActivityBase;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.download.ProfitTO;
import lindley.desarrolloxcliente.ws.service.ConsultarProfitProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.MultiBars;

public class VerProfit_Activity extends ActivityBase {

	@Inject
	ConsultarProfitProxy consultarProfitProxy;
	private TChart chart;
	
	List<ProfitTO> detalle;

	public static final String ANIO = "pfanio";
	public static final String MES = "pfmes";
	public static final String CLIENTE = "pfcliente";
	public static final String ARTICULO = "pcarticulo";
	public static final String NOMBRE_ARTICULO = "nomarticulo";
	
	@InjectExtra(ANIO) String anio;
	@InjectExtra(MES) String mes;
	@InjectExtra(CLIENTE) String codigoCliente;
	@InjectExtra(ARTICULO) String codigoArticulo;
	@InjectExtra(NOMBRE_ARTICULO) String nombre_articulo;
	
	@Inject EvaluacionBLL evaluacionBLL;
	
	private ClienteTO cliente;
	private MyApplication application;

	@InjectResource(R.string.cajas_fisicas) String cajasFisicas;
	@InjectResource(R.string.margen_actual) String margenActual;
	@InjectResource(R.string.cajas_faltantes) String cajasFaltantes;
	@InjectResource(R.string.margen_faltante) String margenFaltante;

	
	@InjectView(R.id.linearLayoutTchart) LinearLayout linearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.profitchartview_activity);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		
		setTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		chart = new TChart(this);
		linearLayout.addView(chart);
		
		/*
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(true);
		*/
		//chart.getAxes().getLeft().getLabels().setValueFormat("#,##0.00;(#,##0.00)");
		//selectTheme(1);
		
		
		
		
		processAsync();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	/*
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
	}*/

	@Override
	protected void process()  throws Exception{
		// TODO Auto-generated method stub}
		
		detalle = evaluacionBLL.consultarProfit(anio, mes, codigoCliente, codigoArticulo);
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		selectSerie();
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	public void selectSerie() {

		chart.removeAllSeries();

		/*ArrayList<String> indicadores = new ArrayList<String>();
		ArrayList<String> variables = new ArrayList<String>();
		for (ProfitTO profitTO : detalle) {

			String nombreIndicadores = profitTO.indicador.toUpperCase();
			if (!indicadores.contains(nombreIndicadores)) {
				indicadores.add(nombreIndicadores.toUpperCase());
			}

			variables.add(cajasFisicas);
			variables.add(margenActual);
			variables.add(cajasFaltantes);
			variables.add(margenFaltante);
		}*/

		
		
		Bar3D bar = new Bar3D(chart.getChart());
		//bar.setMultiBar(MultiBars.STACKED);
		bar.setMarksLocation(MarksLocation.Center);
		bar.setMarksOnBar(true);
		bar.setMarksLocation(MarksLocation.End);
		bar.getMarks().setStyle(MarksStyle.VALUE);
		bar.getMarks().setTransparent(true);
		bar.getMarks().getFont().setColor(Color.white);
		bar.getMarks().getFont().setSize(13);
		bar.setColor(Color.fromCode("#FF0000"));
		bar.setTitle(cajasFisicas);
		
	       
	    Bar3D bar2 = new Bar3D(chart.getChart());
		//bar2.setMultiBar(MultiBars.STACKED);
		bar2.setMarksLocation(MarksLocation.Start);
		bar2.setMarksOnBar(true);
		bar2.setMarksLocation(MarksLocation.Center);
		bar2.getMarks().setStyle(MarksStyle.VALUE);
		bar2.getMarks().setTransparent(true);
		bar2.getMarks().getFont().setColor(Color.white);
		bar2.getMarks().getFont().setSize(13);
		bar2.setColor(Color.fromCode("#6d1e7e"));
		bar2.setTitle(margenActual);
		
		Bar3D bar3 = new Bar3D(chart.getChart());
		//bar3.setMultiBar(MultiBars.STACKED);
		bar3.setMarksLocation(MarksLocation.Start);
		bar3.setMarksOnBar(true);
		bar3.setMarksLocation(MarksLocation.Center);
		bar3.getMarks().setStyle(MarksStyle.VALUE);
		bar3.getMarks().setTransparent(true);
		bar3.getMarks().getFont().setColor(Color.white);
		bar3.getMarks().getFont().setSize(13);
		bar3.setColor(Color.fromCode("#6d1e7e"));
		bar3.setTitle(cajasFaltantes);
		
		Bar3D bar4 = new Bar3D(chart.getChart());
		//bar4.setMultiBar(MultiBars.STACKED);
		bar4.setMarksLocation(MarksLocation.Start);
		bar4.setMarksOnBar(true);
		bar4.setMarksLocation(MarksLocation.Center);
		bar4.getMarks().setStyle(MarksStyle.VALUE);
		bar4.getMarks().setTransparent(true);
		bar4.getMarks().getFont().setColor(Color.white);
		bar4.getMarks().getFont().setSize(13);
		bar4.setColor(Color.fromCode("#6d1e7e"));
		bar4.setTitle(margenFaltante);
		
		
		

		for (ProfitTO profitTO : detalle) {

				bar.add( profitTO.cajasFisica);
				bar2.add( profitTO.margenActual);
				bar3.add( profitTO.cajasFisicasFaltante);
				bar4.add( profitTO.margenFaltante);
		}
		


	      chart.getAspect().setView3D(true);
	      
	      chart.getPanel().setColor(Color.WHITE);
	      chart.getWalls().setVisible(false);
	      chart.getAxes().getLeft().getLabels().getFont().setColor(Color.BLACK);
	      chart.getAxes().getBottom().getLabels().getFont().setColor(Color.BLACK);
	      chart.getHeader().getFont().setColor(Color.BLACK);
	      chart.getHeader().getFont().setSize(15);
	      chart.getHeader().setAlignment(StringAlignment.NEAR);
	      chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
	      chart.getLegend().getFont().setColor(Color.BLACK);
	      chart.getLegend().setTransparent(true);
	      chart.getAxes().getLeft().setIncrement(10);
	      chart.getAxes().getBottom().getGrid().setVisible(true);
	      
	      
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		chart.getHeader().setVisible(false);
		chart.getHeader().getFont().setSize(20);
		
		
		/*Series bar = new Bar(chart.getChart());
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
		
		//chart.getFooter().getFont().setSize(20);*/
	}

}