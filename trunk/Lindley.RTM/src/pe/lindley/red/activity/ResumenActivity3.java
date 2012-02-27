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

public class ResumenActivity3 extends TabActivity {

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
		
		setContentView(pe.lindley.activity.R.layout.red_resumen1_activity);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		fecha_encuesta = intent.getStringExtra(FECHA_ENCUESTA_KEY);

		setupTabHost();

		
		setupTab(new TextView(this));

	}

	private void setupTab(final View view) {
		View tabview = createTabView(mTabHost.getContext(), "Mix Package");
		SoviMixPackageActivity.llamarWS=false;
		
		Intent intent = new Intent(this,SoviMixPackageActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(SoviMixPackageActivity.PERIODO_KEY, fecha_encuesta);
		intent.putExtra(SoviMixPackageActivity.CLIENTE_CODIGO_KEY, cliente_codigo);
		intent.putExtra(SoviMixPackageActivity.CLIENTE_DESCRIPCION_KEY, cliente_descripcion);

		TabSpec setContent = mTabHost.newTabSpec("Sovi_Mix_Package").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
		
		
		SoviMixPuntoContactoActivity.llamarWS=false;
		tabview = createTabView(mTabHost.getContext(), "Mix SOVI POC");
		SoviMixPuntoContactoActivity.llamarWS=false;
		intent = new Intent(this,SoviMixPuntoContactoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(SoviMixPuntoContactoActivity.PERIODO_KEY, fecha_encuesta);
		intent.putExtra(SoviMixPuntoContactoActivity.CLIENTE_CODIGO_KEY, cliente_codigo);
		intent.putExtra(SoviMixPuntoContactoActivity.CLIENTE_DESCRIPCION_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("Mix_SOVI_POC").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
		
		SoviCategoriaActivity.llamarWS=false;
		tabview = createTabView(mTabHost.getContext(), "% SOVI Multicategoria");
		SoviCategoriaActivity.llamarWS=false;
		intent = new Intent(this,SoviCategoriaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(SoviCategoriaActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(SoviCategoriaActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(SoviCategoriaActivity.CLIENTE_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("% SOVI Multicategoria").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}

