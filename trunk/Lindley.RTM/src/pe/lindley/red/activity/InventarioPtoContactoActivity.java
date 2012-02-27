package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;
import pe.lindley.activity.R;
import pe.lindley.red.to.InventarioPuntoContactoTO;
import pe.lindley.red.ws.service.ConsultarInventarioPuntoContactoProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
//import android.widget.TableRow.LayoutParams;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class InventarioPtoContactoActivity extends ActivityBase{

	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
	
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject ConsultarInventarioPuntoContactoProxy puntoContactoProxy;
	//private EfficientAdapter adap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(pe.lindley.activity.R.layout.red_ptocontacto_activity);
		mActionBar.setTitle(R.string.red_pto_contacto_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
	    mActionBar.setSubTitle(cliente_descripcion);
		processAsync();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		fecha_encuesta = fecha_encuesta.replace("/","");
		puntoContactoProxy.setAnioMes(fecha_encuesta);		
		puntoContactoProxy.setCodigoCliente(cliente_codigo);
		puntoContactoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = puntoContactoProxy.isExito();
		if (isExito) {
			int status = puntoContactoProxy.getResponse().getStatus();
			if(status==0){
				List<InventarioPuntoContactoTO> ptosContacto = puntoContactoProxy.getResponse().getData();
								
				//-----------------------------------
				
				ArrayList<String[]> dataFinal = new ArrayList<String[]>();
				
				ArrayList<String> tipoPuntos = new ArrayList<String>();
				ArrayList<String> propietarios = new ArrayList<String>();
				for (InventarioPuntoContactoTO punto : ptosContacto) {
					
					String nombrePunto = punto.getTipoPuntoContacto().toUpperCase();
					if(!tipoPuntos.contains(nombrePunto)){
						tipoPuntos.add(nombrePunto.toUpperCase());
					}
					
					String propietario = punto.getPropietario().toUpperCase();
					if(!propietarios.contains(propietario)){
						propietarios.add(propietario);
					}
				}
				int sizeTipoPto = tipoPuntos.size();	
				String[] tipoPuntosTemp = new String[sizeTipoPto+1];				
				//for (String tipoPunto : tipoPuntos) {
				tipoPuntosTemp[0] = "---";
				for(int i=0; i<sizeTipoPto; i++)
				{
					tipoPuntosTemp[i+1] = tipoPuntos.get(i);
				}
							
				dataFinal.add(tipoPuntosTemp);
				
				
				
				for (String propietario : propietarios) {				
					String[] valores = new String[sizeTipoPto+1];		
					for(int i=0; i<sizeTipoPto+1; i++)
					{
						valores[i] = "0";
					}
					valores[0] = propietario;
					for(String tipoPunto : tipoPuntos){
						for (InventarioPuntoContactoTO puntoContactoTO : ptosContacto) {
							if(puntoContactoTO.getTipoPuntoContacto().toUpperCase().compareTo(tipoPunto)==0){
								if(propietario.compareTo(puntoContactoTO.getPropietario().toUpperCase())==0){
									valores[tipoPuntos.indexOf(tipoPunto)+1]=puntoContactoTO.getCantidad() + "";									
								}
							}
						}
					}
					dataFinal.add(valores);					
				}
				
				
				//---------------------
				
				TableLayout table = (TableLayout) findViewById(R.id.tablePtoContacto);
				for(String[] sr : dataFinal)
				{
					TableRow tableRow = new TableRow(this);
					LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					tableRow.setLayoutParams(lp);
					
					for(String value : sr)
					{
						TextView text = new TextView(this);
						text.setGravity(Gravity.CENTER);
						text.setWidth(200);						
						LayoutParams lp2 = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
						text.setLayoutParams(lp2);
						text.setText(value);			
						tableRow.addView(text);
					}
					table.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));					
				}
				
			}
			else
			{
				showToast(puntoContactoProxy.getResponse().getDescripcion());
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
}