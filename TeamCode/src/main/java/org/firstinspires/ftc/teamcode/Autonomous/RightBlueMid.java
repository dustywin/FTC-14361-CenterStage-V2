package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "RightBlueMid")
public class RightBlueMid extends LinearOpMode
{
    public Robot bot;
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;
    Pose2d myPose = new Pose2d(-36, 63, Math.toRadians(90));
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);

        initCam();

        Trajectory pushPixel = drive.trajectoryBuilder(myPose)
                .addTemporalMarker(0.1, () -> {
                    bot.setClawPosition(clawState.close);
                    bot.setWristPosition(wristState.normal);
                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                })
                .back(32)
                .build();

        Trajectory backUp = drive.trajectoryBuilder(pushPixel.end())
                .forward(6)
                .build();

        Trajectory moveFromTape = drive.trajectoryBuilder(backUp.end())
                .strafeLeft(20)
                .build();

        Trajectory behindGate = drive.trajectoryBuilder(moveFromTape.end())
                .back(27)
                .build();

        Trajectory passThroughGate = drive.trajectoryBuilder(behindGate.end())
                .strafeRight(70)
                //lineToLinearHeading dist. = 80
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(passThroughGate.end())
                .splineTo(new Vector2d(48, 36), Math.toRadians(180))
                //.lineToLinearHeading(new Pose2d(48, 36, Math.toRadians(180)))
                .addTemporalMarker(0.5, () -> {
                    bot.setWristPosition(wristState.sideways);
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                })
                .addTemporalMarker(2,() -> {
                    bot.setWristPosition(wristState.normal);
                    bot.setClawPosition(clawState.open);
                })
                .build();

        Trajectory moveFromBackBoard = drive.trajectoryBuilder(toBackBoard.end())
                .addTemporalMarker(0.5,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .forward(5)
                .build();

        Trajectory towardsPark = drive.trajectoryBuilder(moveFromBackBoard.end())
                .strafeLeft(10)
                .build();

        Trajectory park = drive.trajectoryBuilder(towardsPark.end())
                .back(15)
                .build();

        waitForStart();

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
         */

        // to save battery
        camera.stopStreaming();


        if(isStopRequested())
        {
            return;
        }

        drive.followTrajectory(pushPixel);
        drive.followTrajectory(backUp);
        drive.followTrajectory(moveFromTape);
        drive.followTrajectory(behindGate);
        drive.followTrajectory(passThroughGate);
        drive.followTrajectory(toBackBoard);
        drive.followTrajectory(moveFromBackBoard);
        drive.followTrajectory(towardsPark);
        drive.followTrajectory(park);

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
}