package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import roboguice.inject.InjectExtra;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.activity.CompromisoOpen_Activity.EfficientAdapter;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import net.msonic.lib.sherlock.ActivityBaseFragment;

public class EvaluacionTabs_Activity extends ActivityBaseFragment {

	
	public final static String TAG = EvaluacionTabs_Activity.class.getName();
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	public final static String ORIGEN_REGISTRO = "origen_reg";
	
    @InjectExtra(CODIGO_REGISTRO)public String codigoRegistro;
    @InjectExtra(ORIGEN_REGISTRO)public String origen;
	
	private TabHost mTabHost;
    private TabManager mTabManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 

        
        
        setContentView(R.layout.frameng_tabs);
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabManager.addTab(mTabHost.newTabSpec("Inventario").setIndicator("Inventario"),Compromisos_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec("Posicion").setIndicator("Posici—n"),Posicion_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec("Presentacion").setIndicator("Presentaci—n"),Presentacion_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec("Combos").setIndicator("Combos"),Combos_Activity.class,getIntent().getExtras());
        
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }
    
    
   
	   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		   
		   MenuInflater menuInflater = new MenuInflater(this);
		   menuInflater.inflate(R.menu.evaluacion_tabs_menu, menu);
		   
		return super.onCreateOptionsMenu(menu);
	}

	   @Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			// TODO Auto-generated method stub
			if(item.getItemId()==R.id.mnuGrabar){
				
				MyApplication application = (MyApplication)getApplicationContext();
				EvaluacionTO evaluacion = application.evaluacion;
				
				
				String msg = "";
				
				
				
				if(evaluacion.oportunidades == null || evaluacion.oportunidades.isEmpty())
				{				
					application.openAdapter = new EfficientAdapter(getApplicationContext(), new ArrayList<CompromisoTO>());				
					/*openAdapterVacio = true;
					if(openAdapterVacio)
					{*/
					
						msg = getString(R.string.evaluacion_msg_error_inventario);
						showToast(msg);
						return false;
					//}
				}
				
				for(OportunidadTO comp : evaluacion.oportunidades)
				{
					if(Integer.parseInt(comp.sovi)<=0 || Integer.parseInt(comp.soviActual)<=0)
					{
						msg = getString(R.string.evaluacion_msg_error_sovi);
						showToast(msg);
						return false;
					}
				}
				
				if(evaluacion.posiciones == null || evaluacion.posiciones.isEmpty())
				{				
					msg = getString(R.string.evaluacion_msg_error_posicion);
					showToast(msg);
					return false; 
				}
				
				if(evaluacion.presentaciones == null || evaluacion.presentaciones.isEmpty())
				{				
					msg = getString(R.string.evaluacion_msg_error_presentacion);
					showToast(msg);
					return false; 
				}
				


				Log.d(TAG, "validar evaluaci—n");
				
				
			}
			return super.onMenuItemSelected(featureId, item);
		}
    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentActivity mActivity;
        private final TabHost mTabHost;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerId = containerId;
            mTabHost.setOnTabChangedListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }

            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        @Override
        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
                        ft.detach(mLastTab.fragment);
                    }
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
                        ft.attach(newTab.fragment);
                    }
                }

                mLastTab = newTab;
                ft.commit();
                mActivity.getSupportFragmentManager().executePendingTransactions();
            }
        }
    }
    
}
