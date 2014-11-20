package ua.cn.alexeenkogapon.arkanoid;

import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager msensorManager;
	//private Sensor mOrientation;

	public static float xy_angle;
	public static float xz_angle;
	public static float zy_angle;
	
	public static float[] rotationMatrix;     //������� ��������
	public static float[] accelData;           //������ � �������������
	public static float[] magnetData;       //������ ������������� �������
	public static float[] OrientationData; //������� ��������� � ������������
	
	//private TextView xyView;
	//private TextView xzView;
	//private TextView zyView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // �������� �������� ��������
	   // mOrientation = msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION); // �������� ������ ���������
	    
	    msensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
	    rotationMatrix = new float[16];
	    accelData = new float[3];
	    magnetData = new float[3];
	    OrientationData = new float[3];
	    
		setContentView(new DrawView(this));
		
		//mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // �������� �������� ��������
	   // mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION); // �������� ������ ���������
	    
	   // xyView = (TextView) findViewById(R.id.xyValue);  //
	   // xzView = (TextView) findViewById(R.id.xzValue);  // ���� ��������� ���� ��� ������ ���������
	   // zyView = (TextView) findViewById(R.id.zyValue);  //

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
    protected void onResume() {
    	super.onResume();
    	msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
    	msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        msensorManager.unregisterListener(this);
    }
	
	private void loadNewSensorData(SensorEvent event) {
        final int type = event.sensor.getType(); //���������� ��� �������
        if (type == Sensor.TYPE_ACCELEROMETER) { //���� ������������
                accelData = event.values.clone();
        }
       
        if (type == Sensor.TYPE_MAGNETIC_FIELD) { //���� ������������ ������
                magnetData = event.values.clone();
        }
    }
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		//xy_angle = event.values[0]; //��������� XY
	    //xz_angle = event.values[1]; //��������� XZ
	    //zy_angle = event.values[2]; //��������� ZY
	    
	    loadNewSensorData(event); // �������� ������ � �������
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData); //�������� ������� ��������
        SensorManager.getOrientation(rotationMatrix, OrientationData); //�������� ������ ���������� ���������� � ������������
        
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
