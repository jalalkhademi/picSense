package freelance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class Gyro{
	
	MySensorManager mSensorManager;
	int SensorType = Sensor.TYPE_GYROSCOPE;
	private float[] values;
	
	public Gyro(Context ctx) {
		
		mSensorManager = MySensorManager.getInstance(ctx, SensorType);
		mSensorManager.registerSensorListener(SensorType);
		mSensorManager.setOnGyroEventListener(new OnEventListener() {
			
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
