package lindley.desarrolloxcliente.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import com.thira.examples.actionbar.widget.ActionBar;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import lindley.desarrolloxcliente.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import net.msonic.lib.ActivityBase;

public class VerFoto_Activity extends ActivityBase {

	public static final String FILE_NAME = "FILE_NAME";

	@InjectView(R.id.actionBar)	ActionBar mActionBar;
	@InjectExtra(FILE_NAME)		String fileName;
	@InjectView(R.id.imgFoto)	ImageView imgVer;
	private Bitmap bitmap;
	private Bitmap bitmapTemp1;
	private Bitmap bitmapTemp2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verfoto_activity);
		mActionBar.setTitle(R.string.ver_foto_activity_title);
		processAsync();
	}

	@Override
	protected void process() {

		// TODO Auto-generated method stub
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());

		FileInputStream is = null;
		BufferedInputStream bis = null;

		try {
			is = new FileInputStream(new File(path, fileName));
			bis = new BufferedInputStream(is);

			BitmapFactory.Options bfo = new BitmapFactory.Options();
			bfo.inScaled = true;
			bfo.inDensity = DisplayMetrics.DENSITY_MEDIUM;
			Rect padding = new Rect();
			bfo.inTargetDensity = imgVer.getResources().getDisplayMetrics().densityDpi;

			bitmapTemp1 = BitmapFactory.decodeStream(bis, padding, bfo);

			int width = bitmapTemp1.getWidth();
			int height = bitmapTemp1.getHeight();
			int newWidth = imgVer.getWidth();
			int newHeight = imgVer.getHeight();

			// calculate the scale - in this case = 0.4f
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;

			// createa matrix for the manipulation
			Matrix matrix = new Matrix();
			// resize the bit map
			matrix.postScale(scaleWidth, scaleHeight);
			// rotate the Bitmap
			matrix.postRotate(90);

			// recreate the new Bitmap
			bitmapTemp2 = Bitmap.createBitmap(bitmapTemp1, 0, 0, width, height,
					matrix, true);
			bitmap = Bitmap.createScaledBitmap(bitmapTemp2, imgVer.getWidth(),
					imgVer.getHeight(), true);

			// bitmap = Bitmap.createScaledBitmap(bitmapTemp,imgVer.getWidth(),
			// imgVer.getHeight(), true);
			// imgVer.setImageBitmap(bitmap);
		} catch (Exception e) {
			// Try to recover
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
			}
		}

		super.process();
	}

	@Override
	protected void processOk() {
		imgVer.setImageBitmap(bitmap);
		super.processOk();
	}
}
