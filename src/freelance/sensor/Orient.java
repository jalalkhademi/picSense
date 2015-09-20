package freelance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class Orient{
	
	MySensorManager mSensorManager;
	@SuppressWarnings("deprecation")
	int SensorType = Sensor.TYPE_ORIENTATION;
	private float[] values;
	
	public Orient(Context ctx) {
		
		mSensorManager = MySensorManager.getInstance(ctx, SensorType);
		mSensorManager.registerSensorListener(SensorType);
		mSensorManager.setOnOrientationEventListener(new OnEventListener() {
			
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