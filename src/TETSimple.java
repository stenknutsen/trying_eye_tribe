import com.theeyetribe.clientsdk.data.GazeData;

import java.security.Timestamp;

import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;
import com.theeyetribe.clientsdk.data.CalibrationResult;
import com.theeyetribe.clientsdk.ICalibrationProcessHandler;

public class TETSimple {
	static int gx;
	static int gy;
	static int hx;
	static int hy;
	static String time_now;
	
	public static void main(String[] args) {
		System.out.println("Hi eyetribe!");
		final GazeManager gm = GazeManager.getInstance();
        boolean success = gm.activate();
        
        
        gm.addGazeListener(new IGazeListener() {

			@Override
			public void onGazeUpdate(GazeData gazeData) {
				time_now = gazeData.timeStampString;
				gx = (int) gazeData.leftEye.smoothedCoordinates.x;
				gy = (int) gazeData.leftEye.smoothedCoordinates.y;
				hx = (int) gazeData.rightEye.smoothedCoordinates.x;
				hy = (int) gazeData.rightEye.smoothedCoordinates.y;
				System.out.println(gx+ " , " + gy + " --- "+ hx + " , "+hy+", time:"+time_now);

			}
		});
        
   
        
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                gm.removeGazeListener(null);
                gm.deactivate();
            }
        });

	}

}