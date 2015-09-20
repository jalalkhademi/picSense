package freelance.pic.sense;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import freelance.sensor.Accel;
import freelance.sensor.Gyro;
import freelance.sensor.Orient;
import freelance.sensor.Rotation;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity {

	Context ctx;

	MyCameraPic mCameraPic;
	SurfaceView preview;
	Spinner picSizeSelector;
	private SpinAdapter adapter;
	
	public Accel accel;
	private Gyro gyro;
	private Rotation rotation;
	private Orient orientation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ctx = getApplicationContext();
		
		preview = (SurfaceView) findViewById(R.id.preview);
		mCameraPic = new MyCameraPic(preview);
		mCameraPic.onResume();
		
		picSizeSelector = (Spinner) findViewById(R.id.resSelector);
		adapter = new SpinAdapter(getApplicationContext(),
	            android.R.layout.simple_spinner_item,
	            mCameraPic.getPictureSizes());
		picSizeSelector.setAdapter(adapter);
		
		picSizeSelector.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mCameraPic.setPictureSize(adapter.getItem(position));
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.main, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.camera) {
			mCameraPic.takePic();
			addToCsv();
		}
		return super.onOptionsItemSelected(item);
	}

	public void onResume() {
		super.onResume();
		accel = new Accel(ctx);
		gyro = new Gyro(ctx);
		rotation = new Rotation(ctx);
		orientation= new Orient(ctx);
	}

	private String floatToString(float[] input) {
		String ans = "";
		for (int i = 0; i < input.length; i++) {
			if (i != 0)
				ans += "|";
			ans += String.valueOf(input[i]);
		}
		return ans;
	}

	@Override
	protected void onPause() {
		mCameraPic.onPause();
		super.onPause();
	}

	private void addToCsv() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());

		String csv = timeStamp + ", " + floatToString(accel.getValues()) + ", "
				+ floatToString(gyro.getValues()) + ", "
				+ floatToString(orientation.getValues()) + ", "
				+ floatToString(rotation.getValues());

		try {
			File root = Environment.getExternalStorageDirectory();
			File gpxfile = new File(root, "cSv");
			String header = "";
			if(!gpxfile.exists())
			{
				header = "TimeStamp, Accel, Orientation, Rotation\n";
			}
			FileWriter writer = new FileWriter(gpxfile, true);

			writer.append(header);
			writer.append(csv);
			writer.append('\n');

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
