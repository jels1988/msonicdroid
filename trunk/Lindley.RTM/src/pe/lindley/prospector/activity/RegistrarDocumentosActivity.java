package pe.lindley.prospector.activity;


import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.prospector.activity.ConsultarClienteActivity.EfficientAdapter;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.DocumentoTO;
import pe.lindley.util.ListActivityBase;

public class RegistrarDocumentosActivity extends ListActivityBase {

	@Inject ClienteBLL clienteBLL;
	private ArrayList<DocumentoTO> documentos;
	private EfficientAdapter adap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_cliente_documentos_activity);
		
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		documentos = clienteBLL.listarDocumentos(1);
	}

	@Override
	protected void processOk() {
		adap = new EfficientAdapter(this, documentos);
		setListAdapter(adap);
		super.processOk();
	}
	 public static class EfficientAdapter extends ArrayAdapter<DocumentoTO> {

		 static class ViewHolder {
			protected ImageView imgObligatorio;
			protected TextView txtDocumento;
			protected Button btnTomarFoto;
			protected Button btnVerFoto;
		}
		
		private Activity context;
		private List<DocumentoTO> documentos;
		
		public EfficientAdapter(Activity context,List<DocumentoTO> documentos){
			super(context, R.layout.registro_cliente_documentos_content, documentos);
			this.context=context;
			this.documentos=documentos;
		}
		

		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.registro_cliente_documentos_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.imgObligatorio = (ImageView) view.findViewById(R.id.imgObligatorio);
				viewHolder.txtDocumento = (TextView) view.findViewById(R.id.txtDocumento);
				view.setTag(viewHolder);
				viewHolder.imgObligatorio.setTag(documentos.get(position));
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).imgObligatorio.setTag(documentos.get(position));
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			DocumentoTO documentoTO = documentos.get(position);
			
			holder.txtDocumento.setText(documentoTO.getDescripcion());
			
			if(documentoTO.getObligatorio()==1){
				holder.imgObligatorio.setImageResource(R.drawable.icon_rojo);				
			}else{
				holder.imgObligatorio.setImageResource(R.drawable.icon_verde);
			}
			
			return view;
		}
		
		
	}
	
}



