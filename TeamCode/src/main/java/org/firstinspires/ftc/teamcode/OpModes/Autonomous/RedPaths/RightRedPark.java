package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.robotConstants;
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
    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d start = new Pose2d(-10,-72,0);
        //old start
        Pose2d newStart = new Pose2d(25,-50,180);

        initCam();


        drive.setPoseEstimate(newStart);

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(newStart)
                .lineToConstantHeading(new Vector2d(25, 23))

                .addTemporalMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.intaking);

                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                })
                .addTemporalMarker(1, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);


                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })
                .lineToConstantHeading(new Vector2d(25, 27))
                .addTemporalMarker(2, () -> {

                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })

                .lineToLinearHeading(new Pose2d(62, 25.5, Math.toRadians(180)))
                .addTemporalMarker(3, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.outtakeSlide.setPosition(150);

                })

                .lineToConstantHeading(new Vector2d(60, 25.5))
                .addTemporalMarker(3.5, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .build();




        Trajectory newToCenterTape = drive.trajectoryBuilder(newStart)
                .lineToConstantHeading(new Vector2d(25, -27))

                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.intaking);

                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                })
             .addDisplacementMarker(21, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);

                    bot.activeIntake.setActiveIntakePower(.2);

                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })
                .build();






        Trajectory toBackboardFromCenter = drive.trajectoryBuilder(newToCenterTape.end())
                .lineToLinearHeading(new Pose2d(62, -25.5, Math.toRadians(180)))
                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);





                })
                .build();



        Trajectory leaveBackBoardfromCenter = drive.trajectoryBuilder(toBackboardFromCenter.end())
                .lineToLinearHeading(new Pose2d(60, -25.5, Math.toRadians(180)))
                .addDisplacementMarker(0, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .build();

        Trajectory toRightTape = drive.trajectoryBuilder(newStart)
                // .lineToConstantHeading(new Vector2d(25, 20))
                .lineToLinearHeading(new Pose2d(22, -24, Math.toRadians(180)))

                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.intaking);

                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);


                })
                .addDisplacementMarker(22, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);

                    bot.activeIntake.setActiveIntakePower(.2);

                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })
                .build();

        Trajectory toBackboardFromRight = drive.trajectoryBuilder(toRightTape.end())

                .lineToConstantHeading(new Vector2d(62,-18))
                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                })
                .build();

        Trajectory leaveBackboardFromRight = drive.trajectoryBuilder(toBackboardFromRight.end())

                .lineToConstantHeading(new Vector2d(60,-18))
                .addDisplacementMarker(0, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .build();



        waitForStart();

    /* to get detection
        switch (blueDetection.getLocation()) {
            case LEFT:
                // ...
                break;
            case RIGHT:

                break;
            case MIDDLE:

        }
         */

        // to save battery
        camera.stopStreaming();




        bot.setIntakeSlideState(intakeSlidesState.STATION);
        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);

        switch (blueDetection.getLocation()) {
            case LEFT:

                break;

            case RIGHT:

               drive.followTrajectorySequence(toCenterTape);

                break;
            case MIDDLE:
                drive.followTrajectory(newToCenterTape);
                drive.followTrajectory(toBackboardFromCenter);
                drive.followTrajectory(leaveBackBoardfromCenter);

                break;

        }


        // to save battery

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
OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;

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

}