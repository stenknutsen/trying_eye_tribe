import com.theeyetribe.clientsdk.*;
import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;

public class TETSimple {

	public static void main(String[] args) {
		System.out.println("Hi eyetribe!");
		final GazeManager gm = GazeManager.getInstance();
        boolean success = gm.activate();
        
        
        gm.addGazeListener(new IGazeListener() {

			@Override
			public void onGazeUpdate(GazeData gazeData) {

				System.out.println(gazeData.toString());

			}
		});
        
        //TODO: Do awesome gaze control wizardry
        
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



