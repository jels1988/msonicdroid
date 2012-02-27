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

public class ResumenActivity5 extends TabActivity {

	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
	
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
		View tabview = createTabView(mTabHost.getContext(), "Puntos Contacto");		
		Intent intent = new Intent(this,InventarioPtoContactoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(InventarioPtoContactoActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(InventarioPtoContactoActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(InventarioPtoContactoActivity.CLIENTE_KEY, cliente_descripcion);

		TabSpec setContent = mTabHost.newTabSpec("Puntos Contacto").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
		//----
				
		tabview = createTabView(mTabHost.getContext(), "Material POP");
		intent = new Intent(this,ComunicacionActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(ComunicacionActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(ComunicacionActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(ComunicacionActivity.CLIENTE_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("Material POP").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
		//----		
		
		tabview = createTabView(mTabHost.getContext(), "Preguntas Adicionales");
		intent = new Intent(this,PreguntaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(PreguntaActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(PreguntaActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(PreguntaActivity.CLIENTE_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("Preguntas Adicionales").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		
		//----		
	
		/*
		tabview = createTabView(mTabHost.getContext(), "Preguntas Varias");
		intent = new Intent(this,GeneradorActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		intent.putExtra(GeneradorActivity.FECHA_ENCUESTA_KEY, fecha_encuesta);
		intent.putExtra(GeneradorActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		intent.putExtra(GeneradorActivity.CLIENTE_KEY, cliente_descripcion);
		
		setContent = mTabHost.newTabSpec("Preguntas Varias").setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
		*/
		
	}
	
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}
