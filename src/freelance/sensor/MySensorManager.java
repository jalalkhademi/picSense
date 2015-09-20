package freelance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MySensorManager implements SensorEventListener{

	private static MySensorManager instance = null;
	private SensorManager sensorManager;
	private SensorEvent event;
	private OnEventListener mOnAccelEventListener;
	private OnEventListener mOnGyroEventListener;
	private OnEventListener mOnRotatEventListener;
	private OnEventListener mOnOrientationEventListener;
	
	private MySensorManager(Context ctx) {
		sensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
	}
	
	public static MySensorManager getInstance(Context ctx , int sensorId)
	{
		if(instance == null)
		{
			instance = new MySensorManager(ctx);
		}
		
		return instance;
	}
	
	public void registerSensorListener(int sensorId)
	{
	    Sensor sensor = sensorManager.getDefaultSensor(sensorId);
	    sensorManager.registerListener(this, sensor , SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void setOnAccelEventListener(OnEventListener listener) {
        mOnAccelEventListener = listener;
    }
	
	public void setOnGyroEventListener(OnEventListener listener) {
        mOnGyroEventListener = listener;
    }
	
	public void setOnRotatEventListener(OnEventListener listener) {
        mOnRotatEventListener = listener;
    }
	
	public void setOnOrientationEventListener(OnEventListener listener) {
		mOnOrientationEventListener = listener;
    }
	
	
	public SensorEvent getEvent() {
		return event;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		mOnAccelEventListener.onEvent(event);
		mOnGyroEventListener.onEvent(event);
		mOnRotatEventListener.onEvent(event);
		mOnOrientationEventListener.onEvent(event);
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
