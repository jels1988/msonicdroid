package pe.lindley.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class DemoActivity extends ActivityBase {

	
	public static final String TABLA_TIPO_LINEA_MARCA = "02";
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		TableLayout lst = (TableLayout)findViewById(R.id.lst);
		//final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		//lst.setListAdapter(application.getAdapterParametrosProfit(TABLA_TIPO_LINEA_MARCA, "" ));

		
			
			for(int filas=0;filas<=10;filas++){
				// Create a TableRow and give it an ID
				TableRow row = new TableRow(this);
				for(int columnas=0;columnas<=10;columnas++)
				{
					
		            	

		            // Create a TextView to house the name of the province
		            TextView labelTV = new TextView(this);
		            labelTV.setText("asfsa");
		            labelTV.setLayoutParams(new LayoutParams(
		                    LayoutParams.FILL_PARENT,
		                    LayoutParams.WRAP_CONTENT));
		            row.addView(labelTV);
		            
		        
					
				}
				lst.addView(row, new TableLayout.LayoutParams(
	                    LayoutParams.FILL_PARENT,
	                    LayoutParams.WRAP_CONTENT));
			}
		    

		
			
			
			
	
		
		
	}

	
	
}
