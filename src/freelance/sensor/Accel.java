package freelance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class Accel{
	
	MySensorManager mSensorManager;
	int SensorType = Sensor.TYPE_ACCELEROMETER;
	private float[] values;
	
	public Accel(Context ctx) {
		
		mSensorManager = MySensorManager.getInstance(ctx, SensorType);
		mSensorManager.registerSensorListener(SensorType);
		mSensorManager.setOnAccelEventListener(new OnEventListener() {
			
			@Override
			public void onEvent(SensorEvent e) {
				
				if(e.sensor.getType() == SensorType)
				{
					values = e.values;
				}
			}
		});
	}

	public float[] getValues() {
		return values;
	}
}
