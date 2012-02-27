package pe.lindley.red.activity;

import pe.lindley.activity.R;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class ResumenActivity extends TabActivity {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY="fecha_encuesta";
	
	public static String cliente_codigo;
	public static String cliente_descripcion;
	public static String fecha_encuesta;
	
	private TabHost mTabHost;
	
	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(pe.lindley.activity.R.layout.red_resumen0_activity);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		fecha_encuesta = intent.getStringExtra(FECHA_ENCUESTA_KEY);

		setupTabHost();
		//mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_bg_selector);		
		setupTab(new TextView(this));
	}

	private void setupTab(final View view) {
		View tabview = createTabView(mTabHost.getContext(), "FICHA RED");
		Intent fichaCliente = new Intent(this,FichaClienteActivity.class);
		fichaCliente.putExtra(FichaClienteActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		fichaCliente.putExtra(FichaClienteActivity.CLIENTE_KEY, cliente_descripcion);
		fichaCliente.putExtra(FichaClienteActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		
		TabSpec setContent = mTabHost.newTabSpec("FICHA_RED").setIndicator(tabview).setContent(fichaCliente);
		mTabHost.addTab(setContent);
		
		
		tabview = createTabView(mTabHost.getContext(), "SKU PROPIETARIO");
		Intent sKUIntent = new Intent(this,SKUsActivity.class);
		sKUIntent.putExtra(FichaClienteActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		sKUIntent.putExtra(FichaClienteActivity.CLIENTE_KEY, cliente_descripcion);
		sKUIntent.putExtra(FichaClienteActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		
		setContent = mTabHost.newTabSpec("SKU_PROPIETARIO").setIndicator(tabview).setContent(sKUIntent);
		mTabHost.addTab(setContent);
		
		//tabview = createTabView(mTabHost.getContext(), "FOTO");
		
		//IndiceEjecucionAnioActivity.llamarWS=false;
		//CoreBrandsMixActivity.llamarWS=false;
		//Intent intent = new Intent(this,IndiceEjecucionAnioActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//Intent intentFoto = new Intent(this,FotoActivity.class);
		/*
		intent.putExtra(CoreBrandsMixActivity.PERIODO_KEY, fecha_encuesta);
		intent.putExtra(CoreBrandsMixActivity.CLIENTE_CODIGO_KEY, cliente_codigo);
		intent.putExtra(CoreBrandsMixActivity.CLIENTE_DESCRIPCION_KEY, cliente_descripcion);
		*/
		//setContent = mTabHost.newTabSpec("FOTO").setIndicator(tabview).setContent(intentFoto);
		//mTabHost.addTab(setContent);
		
		
		tabview = createTabView(mTabHost.getContext(), "Orden Trabajo");
		Intent intent = new Intent(this,OrdenTrabajoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(OrdenTrabajoActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(OrdenTrabajoActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(OrdenTrabajoActivity.CLIENTE_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("Orden Trabajo").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);	
		
	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}
