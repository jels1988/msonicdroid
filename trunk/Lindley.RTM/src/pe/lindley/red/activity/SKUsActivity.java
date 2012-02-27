package pe.lindley.red.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.red.to.SKUPropietarioTO;
import pe.lindley.red.ws.service.ConsultarSKUPropietarioProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class SKUsActivity extends ListActivityBase {

	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ConsultarSKUPropietarioProxy consultarSKUPropietarioProxy;
	
	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY="fecha_encuesta";
	
	public static String cliente_codigo;
	public static String cliente_descripcion;
	public static String fecha_encuesta;
	
	private EfficientAdapter adap;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_sku_activity);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		fecha_encuesta = intent.getStringExtra(FECHA_ENCUESTA_KEY);
	
		
		mActionBar.setTitle(R.string.red_sku_cliente_title);
		mActionBar.setSubTitle(cliente_descripcion);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		processAsync();
		
	}
	@Override
	protected void process() {
		consultarSKUPropietarioProxy.setCodigoCliente(cliente_codigo);
		consultarSKUPropietarioProxy.setFechaEncuesta(fecha_encuesta.replace("/", ""));
		consultarSKUPropietarioProxy.execute();
		
	}
	
	@Override
	protected void processOk() {
		boolean isExito = consultarSKUPropietarioProxy.isExito();
		if (isExito) {
			int status = consultarSKUPropietarioProxy.getResponse().getStatus();
			if(status==0){
				List<SKUPropietarioTO> skus = consultarSKUPropietarioProxy.getResponse().getSkus();
				adap = new EfficientAdapter(this, skus);
				setListAdapter(adap);
			}
		}
		super.processOk();
	}
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
	
	public static class EfficientAdapter extends BaseAdapter implements
	Filterable {
private LayoutInflater mInflater;
private List<SKUPropietarioTO> skus;

public EfficientAdapter(Context context, List<SKUPropietarioTO> skus) {
	// Cache the LayoutInflate to avoid asking for a new one each time.
	mInflater = LayoutInflater.from(context);
	this.skus = skus;
}

public EfficientAdapter(Context context) {
	// Cache the LayoutInflate to avoid asking for a new one each time.
	mInflater = LayoutInflater.from(context);
	
}

/**
 * Make a view to hold each row.
 * 
 * @see android.widget.ListAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 */
public View getView(final int position, View convertView,ViewGroup parent) {
	
	
	SKUPropietarioTO skuPropietario = (SKUPropietarioTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(R.layout.red_sku_content, null);

		// Creates a ViewHolder and store references to the two children
		// views
		// we want to bind data to.
		holder = new ViewHolder();
		holder.txtProducto = (TextView) convertView.findViewById(R.id.txtProducto);
		holder.txtSustituto = (TextView) convertView.findViewById(R.id.txtSustituto);
		
		convertView.setTag(holder);
	} else {
		// Get the ViewHolder back to get fast access to the TextView
		// and the ImageView.
		holder = (ViewHolder) convertView.getTag();
	}

	int colorPos = position % 2;
	if(colorPos==0){
		convertView.setBackgroundResource(R.color.custom_theme_color_amarillo_alterno);
	}
	
	
	holder.txtProducto.setText(skuPropietario.getProducto());
	holder.txtSustituto.setText(skuPropietario.getSustituto());
	
	
	if(skuPropietario.getEstado()==1){
		
		holder.txtProducto.setBackgroundResource(R.color.custom_theme_color_verde);
	}
	
	if(skuPropietario.getStaSus()==1){
		
		holder.txtProducto.setBackgroundResource(R.color.custom_theme_color_verde);
		holder.txtSustituto.setBackgroundResource(R.color.custom_theme_color_verde);
	}
	
	return convertView;
}

static class ViewHolder {
	TextView txtProducto;
	TextView txtSustituto;

}

@Override
public Filter getFilter() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	// return data.length;
	if (skus == null) {
		return 0;
	} else {
		return skus.size();
	}
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return skus.get(position);
}

}

}

