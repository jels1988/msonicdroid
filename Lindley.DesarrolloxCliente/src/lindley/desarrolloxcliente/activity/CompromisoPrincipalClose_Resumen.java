package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectExtra;
import lindley.desarrolloxcliente.R;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class CompromisoPrincipalClose_Resumen extends TabActivity {
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	private static final String RESPUESTA = "rspta";
	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@InjectExtra(RESPUESTA) String respuesta;
	private TabHost mTabHost;
	
	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.compromisoprincipal_activity);
		
		setupTabHost();
		//mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_bg_selector);		
		setupTab(new TextView(this));
	}
	
	private void setupTab(final View view) {
		View tabview = createTabView(mTabHost.getContext(), "INVENTARIO");
		Intent oportunidad = new Intent(this, CompromisoClose_Activity.class);
		oportunidad.putExtra(CompromisoClose_Activity.CODIGO_REGISTRO, codigoRegistro);		
		TabSpec setContent = mTabHost.newTabSpec("INVENTARIO").setIndicator(tabview).setContent(oportunidad);
		mTabHost.addTab(setContent);
		
		
		tabview = createTabView(mTabHost.getContext(), "POSICION");
		Intent posicion = new Intent(this, CompromisoPosicionClose_Activity.class);
		posicion.putExtra(CompromisoPosicionClose_Activity.COD_GESTION, codigoRegistro);
		posicion.putExtra(CompromisoPosicionClose_Activity.RESPUESTA, respuesta);		
		setContent = mTabHost.newTabSpec("POSICION").setIndicator(tabview).setContent(posicion);
		mTabHost.addTab(setContent);
		
		tabview = createTabView(mTabHost.getContext(), "PRESENTACION");
		Intent presentacion = new Intent(this, CompromisoPresentacionClose_Activity.class);		
		presentacion.putExtra(CompromisoPresentacionClose_Activity.COD_GESTION, codigoRegistro);		
		setContent = mTabHost.newTabSpec("PRESENTACION").setIndicator(tabview).setContent(presentacion);
		mTabHost.addTab(setContent);	
		
	}
	
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_container, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}	

}
