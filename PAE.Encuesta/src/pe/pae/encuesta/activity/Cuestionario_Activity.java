package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.to.PreguntaTO;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
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
	@InjectExtra("ENCUESTAID") 	int 				encuestaId;
	@InjectExtra("PRODUCTO") 	String 				producto;
	
	
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
	        
	        /*
	        
	        
	        PreguntaTO p1=new PreguntaTO();
	        p1.pregunta="Pregunta 1";
	        p1.preguntaId=1;
	        p1.tipoPregunta=3;
	        
	        OpcionTO op1=new OpcionTO();
	        op1.opcionId=1;
	        op1.descripcion="SI";
	        
	        OpcionTO op2=new OpcionTO();
	        op2.opcionId=2;
	        op2.descripcion="NO";
	        
	        OpcionTO op3=new OpcionTO();
	        op3.opcionId=2;
	        op3.descripcion="otro valor";
	        
	        
	        OpcionTO op4=new OpcionTO();
	        op4.opcionId=2;
	        op4.descripcion="xxxx";
	        
	        
	        OpcionTO op5=new OpcionTO();
	        op5.opcionId=2;
	        op5.descripcion="tttt";
	        
	        
	        
	        p1.opciones.add(op1);
	        p1.opciones.add(op2);
	        p1.opciones.add(op3);
	        p1.opciones.add(op4);
	        p1.opciones.add(op5);
	        
	        
	        PreguntaTO p2=new PreguntaTO();
	        p2.pregunta="Pregunta 2 pres de casfsffsdfsda";
	        p2.preguntaId=2;
	        p2.tipoPregunta=4;
	        
	        OpcionTO op21=new OpcionTO();
	        op21.opcionId=1;
	        op21.descripcion="1";
	        
	        OpcionTO op22=new OpcionTO();
	        op22.opcionId=2;
	        op22.descripcion="2";
	        
	        OpcionTO op23=new OpcionTO();
	        op23.opcionId=3;
	        op23.descripcion="3";
	        
	        
	        OpcionTO op24 =new OpcionTO();
	        op24.opcionId=2;
	        op24.descripcion="2";
	        
	        
	        OpcionTO op25=new OpcionTO();
	        op25.opcionId=2;
	        op25.descripcion="2";

	        OpcionTO op26=new OpcionTO();
	        op26.opcionId=2;
	        op26.descripcion="2";

	        
	        OpcionTO op27=new OpcionTO();
	        op27.opcionId=2;
	        op27.descripcion="2";

	        

	        OpcionTO op28=new OpcionTO();
	        op28.opcionId=2;
	        op28.descripcion="234";
	        
	        
	        p2.opciones.add(op21);
	        p2.opciones.add(op22);
	        p2.opciones.add(op23);
	        p2.opciones.add(op24);
	        p2.opciones.add(op25);
	        p2.opciones.add(op27);
	        p2.opciones.add(op26);
	        p2.opciones.add(op28);
	        
	        preguntas.add(p1);
	        preguntas.add(p2);
	        
	        cargarPregunta();
	        */
	        
	        
	        
	        
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
				//ListView lstOpciones1 =  (ListView)viewPreguntaActual.findViewById(R.id.lstOpciones);
				SingleOpcion_Adapter opciones1 = new SingleOpcion_Adapter(this, preguntaTO.opciones);	
				//lstOpciones1.setAdapter(opciones1);
				//lstOpciones1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				
				
				GridView gr = (GridView)viewPreguntaActual.findViewById(R.id.gridview);
				gr.setAdapter(opciones1);
				break;
			case 4:
				//viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_3, lnCuestionario,false);
				//ListView lstOpciones2 =  (ListView)viewPreguntaActual.findViewById(R.id.lstOpciones);
				viewPreguntaActual = layoutInflater.inflate(R.layout.pregunta_3, lnCuestionario,false);
				GridView gr2 = (GridView)viewPreguntaActual.findViewById(R.id.gridview);
				MultipleOpcion_Adapter opciones2 = new MultipleOpcion_Adapter(this, preguntaTO.opciones);
				gr2.setAdapter(opciones2);
				
				//lstOpciones2.setAdapter(opciones2);
				//lstOpciones2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				break;
			default:
				break;
			}
	        
	        
	        TextView lblPregunta = (TextView)viewPreguntaActual.findViewById(R.id.lblPregunta);
	        lblPregunta.setText(preguntaTO.pregunta);
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
