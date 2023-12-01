package org.firstinspires.ftc.teamcode.OpModes.Autonomous.MainAuto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths.LeftRedLeft;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths.LeftRedMid;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class MainRedLeft extends LinearOpMode  {
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;

    LeftRedLeft leftPath;
    LeftRedMid midPath;
    @Override
    public void runOpMode() throws InterruptedException {
        initCam();

        waitForStart();

        if (isStopRequested()) {
            return;
        }


        switch (blueDetection.getLocation()) {

            case LEFT:
                camera.stopStreaming();
                //leftPath.();
                break;

            case RIGHT:
                camera.stopStreaming();
                break;

            case MIDDLE:
                camera.stopStreaming();
                midPath.leftRedMidExecute();
        }


    }

    private void initCam() {

        //This line retrieves the resource identifier for the camera monitor view. The camera monitor view is typically used to display the camera feed
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        webcamName = "Webcam 1";

        // This line creates a webcam instance using the OpenCvCameraFactor with the webcam name (webcamName) and the camera monitor view ID.
        // The camera instance is stored in the camera variable that we can use later
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);

        // initializing our Detection class (details on how it works at the top)
        blueDetection = new HSVBlueDetection(telemetry);

        // yeah what this does is it gets the thing which uses the thing so we can get the thing
        /*
        (fr tho idk what pipeline does, but from what I gathered,
         we basically passthrough our detection into the camera
         and we feed the streaming camera frames into our Detection algorithm)
         */
        camera.setPipeline(blueDetection);

        /*
        this starts the camera streaming, with 2 possible combinations
        it starts streaming at a chosen res, or if something goes wrong it throws an error
         */
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.showFpsMeterOnViewport(true);
                camera.startStreaming(320, 240, OpenCvCameraRotation.SENSOR_NATIVE);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Unspecified Error Occurred; Camera Opening");
            }
        });
    }
/*

    /* to get detection
        switch (blueDetection.getLocation()) {
            case LEFT:
                // ...
                break;
            case RIGHT:
                // ...
                break;
            case MIDDLE:
                // ...
        }

        camera.stopStreaming();

    // to save battery

 */

    /*

 waitForStart();

        if (isStopRequested()) {
            return;
        }
  }
 */
}