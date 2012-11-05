package lindley.desarrolloxcliente.activity;

import java.util.HashMap;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.inject.Inject;

import roboguice.inject.InjectExtra;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;
import net.msonic.lib.sherlock.ActivityBaseFragment;

public class EvaluacionTabs_Activity extends ActivityBaseFragment {

	
	public final static String TAG = EvaluacionTabs_Activity.class.getName();
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	public final static String ORIGEN_REGISTRO = "origen_reg";
	
	private final static String TAB_INVENTARIO="Inventario";
	private final static String TAB_POSICION="Posicion";
	private final static String TAB_PRESENTACION="Presentacion";
	private final static String TAB_COMBOS="Combos";
	
	private final static int ACCION_GUARDAR_EVALUACION=0;
	
   @InjectExtra(CODIGO_REGISTRO)public String codigoRegistro;
   @InjectExtra(ORIGEN_REGISTRO)public String origen;
    
	@Inject EvaluacionBLL evaluacionBLL;
	
	private TabHost mTabHost;
    private TabManager mTabManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inicializarRecursos();
        
        Compromisos_Activity.VISTA_CARGADA=0;
        Posicion_Activity.VISTA_CARGADA=0;
        Presentacion_Activity.VISTA_CARGADA=0;
        Combos_Activity.VISTA_CARGADA=0;
        
        
        setContentView(R.layout.frameng_tabs);
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabManager.addTab(mTabHost.newTabSpec(TAB_INVENTARIO).setIndicator("Inventario"),Compromisos_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec(TAB_POSICION).setIndicator("Posici�n"),Posicion_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec(TAB_PRESENTACION).setIndicator("Presentaci�n"),Presentacion_Activity.class,getIntent().getExtras());
        mTabManager.addTab(mTabHost.newTabSpec(TAB_COMBOS).setIndicator("Combos"),Combos_Activity.class,getIntent().getExtras());
        
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
	protected void process(int accion) throws Exception {
		// TODO Auto-generated method stub
		   
		if(ACCION_GUARDAR_EVALUACION==accion){
			MyApplication application = (MyApplication)getApplicationContext();
			EvaluacionTO evaluacion = application.evaluacion;
			evaluacionBLL.save(evaluacion);
		}
		super.processOk();
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(ACCION_GUARDAR_EVALUACION==accion){
			String msg = getString(R.string.evaluacion_msg_grabar_ok);
			showToast(msg);
			finish();
		}
		
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		if(ACCION_GUARDAR_EVALUACION==accion){
			String msg = getString(R.string.evaluacion_msg_grabar_error);
			showToast(msg);
		}
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
					mTabHost.setCurrentTabByTag(TAB_INVENTARIO);
					msg = getString(R.string.evaluacion_msg_error_inventario);
					showToast(msg);
					return false;
				}
				
				for(OportunidadTO comp : evaluacion.oportunidades)
				{
					if(comp.soviActual==null || comp.soviActual.trim().compareTo("")==0 || Integer.parseInt(comp.sovi)<=0 || Integer.parseInt(comp.soviActual)<=0)
					{
						mTabHost.setCurrentTabByTag(TAB_INVENTARIO);
						msg = getString(R.string.evaluacion_msg_error_sovi);
						showToast(msg);
						return false;
					}
					
					if((comp.fechaOportunidad==null) || (comp.fechaOportunidad.compareTo("")==0)){
						mTabHost.setCurrentTabByTag(TAB_INVENTARIO);
						msg = getString(R.string.evaluacion_msg_error_fecha);
						showToast(msg);
						return false;
					}
				}
				
				if(evaluacion.posiciones == null || evaluacion.posiciones.isEmpty() || (Posicion_Activity.VISTA_CARGADA==0))
				{				
					mTabHost.setCurrentTabByTag(TAB_POSICION);
					msg = getString(R.string.evaluacion_msg_error_posicion);
					showToast(msg);
					return false; 
				}
				
				for(PosicionCompromisoTO posicion : evaluacion.posiciones)
				{
					if((posicion.fechaCompromiso==null) || (posicion.fechaCompromiso.compareTo("")==0)){
						mTabHost.setCurrentTabByTag(TAB_POSICION);
						msg = getString(R.string.evaluacion_msg_error_fecha);
						showToast(msg);
						return false;
					}
				}
				
				
				if(evaluacion.presentaciones == null || evaluacion.presentaciones.isEmpty() || (Presentacion_Activity.VISTA_CARGADA==0))
				{				
					mTabHost.setCurrentTabByTag(TAB_PRESENTACION);
					msg = getString(R.string.evaluacion_msg_error_presentacion);
					showToast(msg);
					return false; 
				}
				
				if(Combos_Activity.VISTA_CARGADA==0){
					mTabHost.setCurrentTabByTag(TAB_COMBOS);
					msg = getString(R.string.evaluacion_msg_error_combos);
					showToast(msg);
					return false; 
				}
				
				processAsync(ACCION_GUARDAR_EVALUACION);
				
			}else if(item.getItemId()==R.id.mnuCancelar){
				finish();
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