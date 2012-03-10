package pe.lindley.prospector.activity;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.DocumentoTO;
import pe.lindley.util.ListActivityBase;

public class RegistrarDocumentosActivity extends ListActivityBase {
	
	private static final int TAKE_PHOTO_CODE = 1;
	private  String file_name="";
	private DocumentoTO documentoTO;
	
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
	
	public void takePhoto(DocumentoTO documentoTO){
		this.documentoTO=documentoTO;
    	file_name = String.format("%d.jpg", System.currentTimeMillis());
    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
    	startActivityForResult(intent, TAKE_PHOTO_CODE);
    }
	
	public void savePhoto(){
		documentoTO.setNombreArchivo(file_name);
		clienteBLL.guardarDocumento(1, documentoTO);
	}

	
	  private File getTempFile(Context context){
		    
		   final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
		   
		    if(!path.exists()){
		    	path.mkdir();
		    }
		    
		    
		    return new File(path, file_name); 
		    }


	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	    		switch(requestCode){
	    			case TAKE_PHOTO_CODE:{
	    				savePhoto();
	    				break;
	    			}
	    		}

	    }
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
				viewHolder.btnTomarFoto = (Button)view.findViewById(R.id.btnTomarFoto);
				
				viewHolder.btnTomarFoto.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((RegistrarDocumentosActivity)context).takePhoto((DocumentoTO)viewHolder.imgObligatorio.getTag());
					}
				});
				viewHolder.btnVerFoto = (Button)view.findViewById(R.id.btnVerFoto);
				viewHolder.btnVerFoto.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DocumentoTO documento = (DocumentoTO)viewHolder.imgObligatorio.getTag();
						Intent intent = new Intent(context,RegistrarClienteVerDocumentosActivity.class);
						intent.putExtra(RegistrarClienteVerDocumentosActivity.FILE_NAME, documento.getNombreArchivo());
						context.startActivity(intent);
					}
				});
				
				
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



