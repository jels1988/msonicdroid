package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.MyApplication;
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

public class CompromisoPrincipalOpen_Resumen extends TabActivity {

	public final static String CODIGO_REGISTRO = "codigo_reg";
	public final static String FLAG_FECHA = "fecha_flag";
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	private TabHost mTabHost;
	public String codigoRegistro;
	public String flagFecha;
	
	public static MyApplication application;
	
	private void setupTabHost() {		
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		application = (MyApplication)getApplicationContext();
		application.listInventarioCompromiso = null;
		application.listPosicionCompromiso = null;
		application.listPresentacionCompromiso = null;
		setContentView(R.layout.compromisoprincipal_activity);
		Intent intent = this.getIntent();
		codigoRegistro = intent.getStringExtra(CODIGO_REGISTRO);
		flagFecha = intent.getStringExtra(FLAG_FECHA);
		setupTabHost();
		//mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_bg_selector);		
		setupTab(new TextView(this));
	}
	
	private void setupTab(final View view) {
		System.out.println("oepn .... "+codigoRegistro);
		View tabview = createTabView(mTabHost.getContext(), "INVENTARIO");
		Intent oportunidad = new Intent(this, CompromisoOpen_Activity.class);
		oportunidad.putExtra(CompromisoOpen_Activity.CODIGO_REGISTRO, codigoRegistro);
		oportunidad.putExtra(CompromisoOpen_Activity.FLAG_FECHA, flagFecha);
		TabSpec setContent = mTabHost.newTabSpec("INVENTARIO").setIndicator(tabview).setContent(oportunidad);
		mTabHost.addTab(setContent);
		
		tabview = createTabView(mTabHost.getContext(), "POSICION");
		Intent posicion = new Intent(this, CompromisoPosicionOpen_Activity.class);
		posicion.putExtra(CompromisoPosicionOpen_Activity.COD_GESTION, codigoRegistro);
		posicion.putExtra(CompromisoPosicionOpen_Activity.FLAG_FECHA, flagFecha);
		setContent = mTabHost.newTabSpec("POSICION").setIndicator(tabview).setContent(posicion);
		mTabHost.addTab(setContent);
		
		tabview = createTabView(mTabHost.getContext(), "PRESENTACION");
		Intent presentacion = new Intent(this, CompromisoPresentacionOpen_Activity.class);
		presentacion.putExtra(CompromisoPresentacionOpen_Activity.COD_GESTION, codigoRegistro);
		presentacion.putExtra(CompromisoPresentacionOpen_Activity.FLAG_FECHA, flagFecha);
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

