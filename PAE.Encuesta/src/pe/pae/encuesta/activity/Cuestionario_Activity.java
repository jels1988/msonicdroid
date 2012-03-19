package pe.pae.encuesta.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.to.PreguntaTO;
import pe.pae.encuesta.to.RespuestaTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class Cuestionario_Activity extends ActivityBase {

	
	@InjectView(R.id.actionBar)  		ActionBar 		mActionBar;
	@InjectView(R.id.lnCuestionario)  	LinearLayout 	lnCuestionario;
	@InjectExtra("ENCUESTAID") 	int 				encuestaId;
	@InjectExtra("PRODUCTOID") 	int 				productoId;
	@InjectExtra("PRODUCTO") 	String 				producto;
	
	
	@Inject 					EncuestaBLL 		encuestaBLL;
	
	
	private int index=0;
	private View viewPreguntaActual;
	private ArrayList<PreguntaTO> preguntas;
	private RespuestaTO respuestaTO=null;
	MiApp app = null;
	private static final int TAKE_PHOTO_CODE = 1;
	private  String file_name="";
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		
		respuestaTO = encuestaBLL.listarPreguntas(app.tiendaId, productoId, encuestaId);
		preguntas = respuestaTO.preguntas;
		
		super.process();
	}
	
	
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		cargarPregunta();
		super.processOk();
	}




	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        inicializarRecursos();
	        
	        setContentView(R.layout.cuestionario_activity);
	        mActionBar.setTitle(R.string.cuestionario_activity_title);
	        mActionBar.setSubTitle(producto);
	        app=(MiApp)getApplication();
	        
	        respuestaTO = new RespuestaTO();
	        
	        
	        processAsync();
	        
	        
	    }
	   
	   
	   public void cargarPregunta(){
		   
		   PreguntaTO preguntaTO = preguntas.get(index);
		   LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        switch (preguntaTO.tipoPregunta) {
			case 1:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_1, lnCuestionario,false);
				
				TextView txtNumero= (TextView)viewPreguntaActual.findViewById(R.id.txtNumero);
				txtNumero.setText(preguntaTO.respuesta_1);
				
				txtNumero.addTextChangedListener(new TextWatcher() {
					
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						preguntas.get(index).respuesta_1 = s.toString();
					}
				});
				
				break;
			case 2:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_2, lnCuestionario,false);
				TextView txtTexto = (TextView)viewPreguntaActual.findViewById(R.id.txtTexto);
				txtTexto.setText(preguntaTO.respuesta_1);
				txtTexto.addTextChangedListener(new TextWatcher() {
					
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						preguntas.get(index).respuesta_1 = s.toString();
					}
				});
				break;
			case 3:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_3, lnCuestionario,false);
				SingleOpcion_Adapter opciones1 = new SingleOpcion_Adapter(this, preguntaTO.opciones);	
				GridView gr = (GridView)viewPreguntaActual.findViewById(R.id.gridview);
				gr.setAdapter(opciones1);
				break;
			case 4:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_3, lnCuestionario,false);
				GridView gr2 = (GridView)viewPreguntaActual.findViewById(R.id.gridview);
				MultipleOpcion_Adapter opciones2 = new MultipleOpcion_Adapter(this, preguntaTO.opciones);
				gr2.setAdapter(opciones2);
				break;
			default:
				break;
			}
	        
	        
	       //PREGUNTA
	        TextView lblPregunta = (TextView)viewPreguntaActual.findViewById(R.id.lblPregunta);
	        lblPregunta.setText(preguntaTO.pregunta);
	        
	        //COMENTARIO
	        TextView txtComentario= (TextView)viewPreguntaActual.findViewById(R.id.txtComentario);
	        if(preguntaTO.comentario.trim().compareTo("")==0){
	        	txtComentario.setVisibility(View.GONE);
	        }else{
	        	txtComentario.setVisibility(View.VISIBLE);
	        	txtComentario.setText(preguntaTO.comentario);
	        }
	        
	        //OBSERVACION
	        LinearLayout lnlObservacion = (LinearLayout)viewPreguntaActual.findViewById(R.id.lnlObservacion);
	        
	        if(preguntaTO.tieneObservacion==PreguntaTO.TIENE_OBSERVACION_SI){
	        	
	        	final EditText txtObservacion= (EditText)viewPreguntaActual.findViewById(R.id.txtObservacion);
	        	txtObservacion.setText(preguntaTO.observacion);
	        	
	        	txtObservacion.addTextChangedListener(new TextWatcher() {
					
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						preguntas.get(index).observacion=s.toString();
					}
				});
	        	
	        	lnlObservacion.setVisibility(View.VISIBLE);
	        }else{
	        	lnlObservacion.setVisibility(View.GONE);
	        }
	        
	        //FOTO
	        LinearLayout lnlFoto = (LinearLayout)viewPreguntaActual.findViewById(R.id.lnlFoto);
	        if(preguntaTO.tieneFoto==PreguntaTO.TIENE_FOTO_SI){
	        	lnlFoto.setVisibility(View.VISIBLE);
	        }else{
	        	lnlFoto.setVisibility(View.GONE);
	        }
	        
	        
			lnCuestionario.addView(viewPreguntaActual);
	        
	   }
	   public void btnSiguiente_onclick(View v){
		   if(preguntas==null) return;
		
		   
		   lnCuestionario.removeViewAt(lnCuestionario.getChildCount()-1);
		   index++;
		   
		   int size = preguntas.size();
		   if(index>=size) index=0;
			   
		   cargarPregunta();
	   }
	   public void btnAnterior_onclick(View v){
		   if(preguntas==null) return;
		   
		  
		   lnCuestionario.removeViewAt(lnCuestionario.getChildCount()-1);
		   index--;
		   if(index==-1)index=preguntas.size()-1;
		   
		   cargarPregunta();
	   }
	   
	   public void btnSave_onclick(View v){
		   encuestaBLL.guardarEncuestaRespuesta(respuestaTO);
		   
	   }
	   
	   public void takePhoto(){
		   
		   Random randInt = new Random();
		   int numero = randInt.nextInt(32000);
		   
	    	file_name = String.format("%d_%d.jpg",numero,  System.currentTimeMillis());
	    	
	    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
	    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
	    	startActivityForResult(intent, TAKE_PHOTO_CODE);
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
	    				//adap.notifyDataSetChanged();
	    				processAsync();
	    				break;
	    			}
	    		}

	    }
	    
	   
	  }
	   
	   public void savePhoto(){
			/*documentoTO.setNombreArchivo(file_name);
			documentoTO.setEsLocal(DocumentoTO.LOCAL);
			clienteBLL.guardarDocumento(clienteId, documentoTO);*/
		}
	   
	   public void btnVerFoto_onclick(View v){
		   Log.d("Cuestionario_Activity", "pruebas");
	   }
	   
	   public void btnTomarFoto_onclick(View v){
		   takePhoto();
	   }
}
