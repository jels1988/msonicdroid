package pe.lavisa.tomadorpedidos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginActivity extends net.msonic.lib.sherlock.ActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.login_activity);
        
        
        setTitle(R.string.login_activity_title);
        
        
    }
    
    public void btnIngresar_onclick(View v){
    	Intent intent = new Intent(this,ResumenActivity.class);
    	startActivity(intent);
    }
    
    

    
}
