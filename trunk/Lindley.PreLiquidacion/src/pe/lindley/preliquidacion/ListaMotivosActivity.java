package pe.lindley.preliquidacion;

import java.util.ArrayList;

import com.google.inject.Inject;

import pe.lindley.preliquidacion.negocio.MotivoBLL;
import pe.lindley.preliquidacion.to.MotivoTO;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.msonic.lib.ListActivityBase;

public class ListaMotivosActivity extends ListActivityBase {

	@Inject MotivoBLL motivoBLL;
	private ArrayList<MotivoTO> motivos;
	private int motivoIndex=-1;
	public static final String MOTIVO_SELECCIONADO="motivo_seleccionada";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		inicializarRecursos();
		setContentView(R.layout.listamotivos_activity);
		processAsync();
	}
	
	
	public void btnAceptar_onclick(View view){
		Intent intent= getIntent();
		
		MotivoTO motivo = motivos.get(motivoIndex);
		
		intent.putExtra(MOTIVO_SELECCIONADO, motivo.getCodigo());
		
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void btnCancelar_onclick(View view){
		setResult(RESULT_CANCELED);
		finish();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		motivoIndex=position;
		
	}
	
	
	@Override
	protected void process() {
		motivos = motivoBLL.listarMotivos();
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		String[] values = new String[motivos.size()];
		int index=0;
		
		for (MotivoTO motivo : motivos) {
			values[index++]=motivo.getDescripcion();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, values);
		setListAdapter(adapter);
		
		ListView listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		
		super.processOk();
	}
	
	@Override
	protected void processError(){
		super.processError();
	}

}
