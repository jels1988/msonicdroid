package pe.lindley.exmedia.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.exmedia.to.FileTO;
import pe.lindley.exmedia.ws.service.ConsultarFilesProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ListarFilesMultiActivity extends ListActivityBase {

	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@InjectView(R.id.txtNombre) EditText txtNombre;
	@Inject ConsultarFilesProxy consultarFilesProxy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listarfilesmulti_activity);

		mActionBar.setTitle(R.string.listar_files_multimedia_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

	}

	@Override
	protected void process() {
		consultarFilesProxy.setFileName(txtNombre.getText().toString());
		consultarFilesProxy.execute();
	}

	@Override
	protected void processOk() {
		boolean isExito = consultarFilesProxy.isExito();

		if (isExito) {
			int status = consultarFilesProxy.getResponse().getStatus();
			if (status == 0) {
				List<FileTO> files = consultarFilesProxy.getResponse().getFiles();
				EfficientAdapter adapter = new EfficientAdapter(this, files);
				setListAdapter(adapter);
				super.processOk();
			} else {
				processError();
			}

		} else {
			processError();
		}

	}

	@Override
	protected void processError() {
		String message;
		if (consultarFilesProxy.getResponse() != null) {
			message = consultarFilesProxy.getResponse().getDescripcion();
		} else {
			message = error_generico_process;
		}
		super.processError();
		showToast(message);
	}

	public void btnbuscar_onclick(View view) {
		processAsync();
	}

	public class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private Context context;
		private List<FileTO> files;

		public EfficientAdapter(Context context, List<FileTO> files) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.files = files;
			this.context = context;
		}

		/**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		public View getView(final int position, View convertView,ViewGroup parent) {
			FileTO file = (FileTO)getItem(position);
			ViewHolder holder;

		      if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.listarfilesmulti_content, null);

			    holder = new ViewHolder();
			    holder.txtNombre = (TextView) convertView.findViewById(R.id.txtFileName);
			      
			      
		        convertView.setTag(holder);
		      } else {
		        // Get the ViewHolder back to get fast access to the TextView
		        // and the ImageView.
		        holder = (ViewHolder) convertView.getTag();
		      }
		      
		      
		      holder.txtNombre.setText(Html.fromHtml("<a href='#'>" + file.getFileName()  + "</a>"));
		      holder.txtNombre.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FileTO file = (FileTO)getItem(position);
					// TODO Auto-generated method stub
					
					String url = file.getUrl();
					
					if(file.getTipo()==1){
						Intent intent = new Intent(context,ViewImageActivity.class);
						intent.putExtra(ViewImageActivity.URL_ARCHIVO, url);
						startActivity(intent);
					}else{
						
						
						Intent intent = new Intent(context,ViewVideoActivity.class);
						intent.putExtra(ViewVideoActivity.URL_ARCHIVO, url);
						intent.putExtra(ViewVideoActivity.NAME_ARCHIVO, file.getFileName());
						startActivity(intent);
						
						
					}
					
					
				}
			});
		      
		      return convertView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(this.files==null){
				return 0;
			}else{
				return this.files.size();
			}
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return this.files.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	static class ViewHolder {
		TextView txtNombre;

	}
}