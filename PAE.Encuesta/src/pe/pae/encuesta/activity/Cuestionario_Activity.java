package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.to.PreguntaTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class Cuestionario_Activity extends ActivityBase {

	
	@InjectView(R.id.actionBar)  		ActionBar 		mActionBar;
	@InjectView(R.id.lnCuestionario)  	LinearLayout 	lnCuestionario;
	//@InjectExtra("ENCUESTAID") 
	int 				encuestaId=1;
	//@InjectExtra("PRODUCTO") 	
	String 				producto="1";
	
	
	@Inject 					EncuestaBLL 		encuestaBLL;
	
	
	private int index=0;
	private View viewPreguntaActual;
	private ArrayList<PreguntaTO> preguntas;
	
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		preguntas = encuestaBLL.listarPreguntas(encuestaId);
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
	        
	        
	        setContentView(R.layout.cuestionario_activity);
	        mActionBar.setTitle(R.string.cuestionario_activity_title);
	        mActionBar.setSubTitle(producto);
	        
	        processAsync();
	        
	        
	    }
	   
	   
	   public void cargarPregunta(){
		   
		   PreguntaTO preguntaTO = preguntas.get(index);
		   LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        switch (preguntaTO.tipoPregunta) {
			case 1:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_1, lnCuestionario,false);
				break;
			case 2:
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_2, lnCuestionario,false);
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
		   lnCuestionario.removeViewAt(lnCuestionario.getChildCount()-1);
		   index++;
		   cargarPregunta();
	   }
	   public void btnAnterior_onclick(View v){
		   lnCuestionario.removeViewAt(lnCuestionario.getChildCount()-1);
		   index--;
		   cargarPregunta();
	   }
}
