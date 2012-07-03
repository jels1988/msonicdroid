package net.msonic.lib.sherlock;

import java.util.HashMap;
import java.util.Map;

import net.msonic.lib.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockExpandableListActivity;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Provider;

import roboguice.RoboGuice;
import roboguice.activity.event.OnActivityResultEvent;
import roboguice.activity.event.OnConfigurationChangedEvent;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnNewIntentEvent;
import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnRestartEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentViewListener;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

public class ExpandableListActivityBase extends SherlockExpandableListActivity implements RoboContext{
	protected ProgressDialog dialogWait;
	protected String error_not_network_message; 
	protected String wait_message;
	protected String error_generico_process;
	@Inject  protected Provider<Context> contextProvider;
	
	protected EventManager eventManager;
    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>,Object>();
    
	protected void inicializarRecursos(){
		error_not_network_message = getString(R.string.error_not_network_message);
		wait_message = getString(R.string.wait_message);
		error_generico_process = getString(R.string.error_generico_process);
	}
	
	protected boolean executeAsyncPre(int accion){
		return true;
	}
	
	protected void executeAsyncPost(int accion){
	}
	
	protected void process(int accion){
		
	}
	
	protected void processOk(int accion){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	protected void processError(int accion){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	final Handler handlerIntOK = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            processOk(msg.what);
        }
    };
    
    
    final Handler handlerIntError = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	processError(msg.what);
        }
    };
    protected final void processAsync(final int accion){
		
		
		if(!executeAsyncPre(accion)) return;
		
		dialogWait = new ProgressDialog(this);
		dialogWait.setIndeterminate(true);
		dialogWait.setMessage(wait_message);
		dialogWait.show();
		
			    
		Thread  thread = new Thread (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					process(accion);
					
					handlerIntOK.sendEmptyMessage(accion);
					
				}catch (Exception e) {
					// TODO: handle exception
					handlerIntError.sendEmptyMessage(accion);
				}
			}
		});
		thread.start();
	}
	


	protected boolean executeAsyncPre(){
		return true;
	}
	
	protected void executeAsyncPost(){
	}
	
	protected void process(){
		
	}
	
	protected void processOk(){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	protected void processError(){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	final Handler handlerOK = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            processOk();
        }
    };
    
    final Handler handlerError = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	processError();
        }
    };
    
    

    
	protected final void processAsync(){
		
		
		if(!isNetworkAvailable()){
			showToast(error_not_network_message);
			return;
		}
		
		if(!executeAsyncPre()) return;
		
		dialogWait = new ProgressDialog(this);
		dialogWait.setIndeterminate(true);
		dialogWait.setMessage(wait_message);
		dialogWait.show();
		
			    
		Thread  thread = new Thread (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					process();
					
					handlerOK.sendEmptyMessage(0);
					
					/*
					if (dialogWait.isShowing())
						dialogWait.dismiss(); */  
					
				}catch (Exception e) {
					// TODO: handle exception
					handlerError.sendEmptyMessage(0);
				}
			}
		});
		thread.start();
	}
	
	public void showToast(CharSequence message){
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
		toast.show();
	}
	
	public boolean isNetworkAvailable() {
		   Context context = getApplicationContext();
		   ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		   if (connectivity == null) {
		      return false;
		   } else {
		      NetworkInfo[] info = connectivity.getAllNetworkInfo();
		      if (info != null) {
		         for (int i = 0; i < info.length; i++) {
		            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
		               return true;
		            }
		         }
		      }
		   }
		   return false;
		}

	@Inject
    ContentViewListener ignored; // BUG find a better place to put this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RoboInjector injector = RoboGuice.getInjector(this);
        eventManager = injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(savedInstanceState);
        eventManager.fire(new OnCreateEvent(savedInstanceState));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        eventManager.fire(new OnRestartEvent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventManager.fire(new OnStartEvent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventManager.fire(new OnResumeEvent());
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventManager.fire(new OnPauseEvent());
    }

    @Override
    protected void onNewIntent( Intent intent ) {
        super.onNewIntent(intent);
        eventManager.fire(new OnNewIntentEvent());
    }

    @Override
    protected void onStop() {
        try {
            eventManager.fire(new OnStopEvent());
        } finally {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            eventManager.fire(new OnDestroyEvent());
        } finally {
            try {
                RoboGuice.destroyInjector(this);
            } finally {
                super.onDestroy();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        final Configuration currentConfig = getResources().getConfiguration();
        super.onConfigurationChanged(newConfig);
        eventManager.fire(new OnConfigurationChangedEvent(currentConfig, newConfig));
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        RoboGuice.getInjector(this).injectViewMembers(this);
        eventManager.fire(new OnContentChangedEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eventManager.fire(new OnActivityResultEvent(requestCode, resultCode, data));
    }

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }
}
