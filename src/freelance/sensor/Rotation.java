package freelance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class Rotation{
	
	MySensorManager mSensorManager;
	int SensorType = Sensor.TYPE_ROTATION_VECTOR;
	private float[] values;
	
	public Rotation(Context ctx) {
		
		mSensorManager = MySensorManager.getInstance(ctx, SensorType);
		mSensorManager.registerSensorListener(SensorType);
		mSensorManager.setOnRotatEventListener(new OnEventListener() {
			
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
