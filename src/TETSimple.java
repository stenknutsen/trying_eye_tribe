import com.theeyetribe.clientsdk.data.GazeData;
import java.io.IOException;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;


public class TETSimple {
	
	public TETSimple(){
		
	}
	
	//private static Robot robot;
	static int gx;
	static int gy;
	static int hx;
	static int hy;
	static String time_now;
	static int eyex;
	static int eyey;
	
	public static void main(String[] args)  throws 
	 IOException {
		/*try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }*/
		
		System.out.println("Hi eyetribe!");
		gazeMangement();
		
	}
	
	public static void gazeMangement() {
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
				eyex = (int) gazeData.smoothedCoordinates.x;
				eyey = (int) gazeData.smoothedCoordinates.y;
				System.out.println(gx+ " , " + gy + " --- "+ hx + " , "+hy+", time:"+time_now+", Mouse is here ("+eyex+", "+eyey+")");
				closeApplication(gazeData);
				//robot.mouseMove(eyex, eyey);

			}
			
			private void closeApplication(GazeData gazeData) {
	            if ((int) gazeData.smoothedCoordinates.x<0) {
	            	stopTracker();
	            }
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
	static void stopTracker(){
		System.out.println("Tracking complete.");
        System.exit(0);
		
	}
}