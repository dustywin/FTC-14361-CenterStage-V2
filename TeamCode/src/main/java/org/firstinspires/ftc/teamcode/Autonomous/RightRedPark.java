package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "RightRedPark")
public class RightRedPark extends LinearOpMode {
    Robot bot;
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;
    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d start = new Pose2d(10,72,180);

        initCam();

        drive.setPoseEstimate(start);

        Trajectory toMiddleOfTape = drive.trajectoryBuilder(start)
                .lineToConstantHeading(new Vector2d(26, 96))
//                .addDisplacementMarker(0, () -> {
//
//                  
//
//                    bot.setClawPosition(clawState.close);
//                    bot.setClawState(clawState.close);
//
//
//
//                })
                .addDisplacementMarker(10, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);

                })
//                .addTemporalMarker(1, () -> {
//                    bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//            bot.setIntakeSlideState(intakeSlidesState.STATION);
//            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
//            bot.setVirtualFourBarState(virtualFourBarState.intaking);
//            bot.setClawPosition(clawState.close);
//            bot.setClawState(clawState.close);
//                })
                .build();
        Trajectory dropOnCenterTape = drive.trajectoryBuilder(toMiddleOfTape.end())
                .lineToConstantHeading(new Vector2d(-40, -72))
                .build();
        Trajectory dropOnRightTape = drive.trajectoryBuilder(toMiddleOfTape.end())
                .lineToConstantHeading(new Vector2d(-5,-36))
                .build();

        Trajectory toLeftTape= drive.trajectoryBuilder(toMiddleOfTape.end())
                .lineToConstantHeading(new Vector2d(-15, -36))
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(toMiddleOfTape.end())

                .lineToLinearHeading(new Pose2d(50, 85 , Math.toRadians(270)))


                .addDisplacementMarker(0, () -> {
                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);




                })

                .addDisplacementMarker(10, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.setOuttakeSlideState(outtakeSlidesState.AUTOLOWOUT);
                    bot.setOuttakeSlidePosition(outtakeSlidesState.AUTOLOWOUT, extensionState.extending);





                })
//            //    .addDisplacementMarker(35, () -> {
//                    bot.setClawPosition(clawState.open);
//                    bot.setClawState(clawState.open);
//
//
//
//
//                })

                .build();
        Trajectory leaveBackBoard = drive.trajectoryBuilder(toBackBoard.end())
                .lineToConstantHeading(new Vector2d(-32,-100))
                .addDisplacementMarker(0, () -> {

                    bot.setClawPosition(clawState.open);
                    bot.setClawState(clawState.open);



                })
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


        if(isStopRequested()) return;


        drive.followTrajectory(toMiddleOfTape);

        //drive.followTrajectory(dropOnCenterTape);

        drive.followTrajectory(toBackBoard);


        //drive.followTrajectory(leaveBackBoard);
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

