import com.theeyetribe.clientsdk.data.GazeData;

import java.io.IOException;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class TETSimple {
	
	private static Robot robot;
	static int gx;
	static int gy;
	static int hx;
	static int hy;
	static String time_now;
	
	public static void main(String[] args)  throws 
	 IOException {
		try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
		System.out.println("Hi eyetribe!");
		final GazeManager gm = GazeManager.getInstance();
        boolean success = gm.activate();
        
        
        gm.addGazeListener(new IGazeListener() {

			@Override
			public void onGazeUpdate(GazeData gazeData) {
				time_now = gazeData.timeStampString;
				gx = (int) gazeData.rightEye.smoothedCoordinates.x;
				gy = (int) gazeData.leftEye.smoothedCoordinates.y;
				hx = (int) gazeData.rightEye.smoothedCoordinates.x;
				hy = (int) gazeData.rightEye.smoothedCoordinates.y;
				System.out.println(gx+ " , " + gy + " --- "+ hx + " , "+hy+", time:"+time_now);
				robot.mouseMove((int) gazeData.smoothedCoordinates.x, (int) gazeData.smoothedCoordinates.y);

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