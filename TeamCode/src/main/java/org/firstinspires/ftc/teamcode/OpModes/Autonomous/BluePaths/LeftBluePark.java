package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths;

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
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.robotConstants;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "LeftBluePark")

public class LeftBluePark extends LinearOpMode {

    Robot bot;
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;
    @Override
    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //old start
        Pose2d newStart = new Pose2d(25, 50, Math.toRadians(90));
        Pose2d backStart = new Pose2d(25, 50, Math.toRadians(180));


        initCam();


        drive.setPoseEstimate(newStart);





        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(newStart)
                .lineToLinearHeading(new Pose2d(28, 45, Math.toRadians(270)))
                .waitSeconds(.25)
                .lineToLinearHeading(new Pose2d(28, 20, Math.toRadians(270)))
                .waitSeconds(.25) // add outtake here, then another wait .25
                .lineToLinearHeading(new Pose2d(60, 35, Math.toRadians(180)))

                .build();


        Trajectory newToCenterTape = drive.trajectoryBuilder(newStart)
                // .lineToConstantHeading(new Vector2d(25, 20))
                .lineToLinearHeading(new Pose2d(25, 27, Math.toRadians(-90)))

                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
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


        Trajectory toRightTape = drive.trajectoryBuilder(newStart)
                // .lineToConstantHeading(new Vector2d(25, 20))
                .lineToLinearHeading(new Pose2d(22, 24, Math.toRadians(180)))

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

                .lineToConstantHeading(new Vector2d(62,18))
                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                })
                .build();

        Trajectory leaveBackboardFromRight = drive.trajectoryBuilder(toBackboardFromRight.end())

                .lineToConstantHeading(new Vector2d(60,18))
                .addDisplacementMarker(0, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .build();



        Trajectory toBackboardFromCenter = drive.trajectoryBuilder(toCenterTape.end())
                .lineToLinearHeading(new Pose2d(62, 25.5, Math.toRadians(180)))
                .addDisplacementMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);





                })
                .build();



        Trajectory leaveBackBoardfromCenter = drive.trajectoryBuilder(toBackboardFromCenter.end())
                .lineToLinearHeading(new Pose2d(60, 25.5, Math.toRadians(180)))
                .addDisplacementMarker(0, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .build();

//                .addTemporalMarker(1, () -> {
//                    bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//            bot.setIntakeSlideState(intakeSlidesState.STATION);
//            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
//            bot.setVirtualFourBarState(virtualFourBarState.intaking);
//            bot.setClawPosition(clawState.close);
//            bot.setClawState(clawState.close);
//                })

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(newStart)
                .lineToLinearHeading(new Pose2d(25, 40, Math.toRadians(90)))
                .waitSeconds(.25)
                .lineToLinearHeading(new Pose2d(45, 40, Math.toRadians(90)))
                .waitSeconds(.25)
                .lineToLinearHeading(new Pose2d(45, 22, Math.toRadians(180)))
                .waitSeconds(.25)
                // add outtake here, then another wait .25s
                .lineToLinearHeading(new Pose2d(61, 358, Math.toRadians(180)))

                .build();

        TrajectorySequence leftTapeSpline = drive.trajectorySequenceBuilder(newStart)
                // spline
                .lineToLinearHeading(new Pose2d(32, 30, Math.toRadians(120)))
                .waitSeconds(.25)
                .lineToLinearHeading(new Pose2d(20, 40, Math.toRadians(180)))
                .waitSeconds(.25)
                .lineToLinearHeading(new Pose2d(45, 40, Math.toRadians(180)))
                .waitSeconds(.25)
                // add outtake here, then another wait .25s
                .lineToLinearHeading(new Pose2d(61, 28, Math.toRadians(180)))
                // output on board
                .build();

        waitForStart();

        // to save battery
        camera.stopStreaming();


        if(isStopRequested()) return;

        bot.setIntakeSlideState(intakeSlidesState.STATION);
        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);

        drive.followTrajectorySequence(leftTapeSpline);


        /*
        switch (blueDetection.getLocation()) {
            case LEFT:

                drive.followTrajectorySequence(toCenterTape);
                drive.followTrajectory(toBackboardFromCenter);
                break;

            case RIGHT:
                drive.followTrajectory(toRightTape);
                drive.followTrajectory(toBackboardFromRight);
                drive.followTrajectory(leaveBackboardFromRight);

                break;
            case MIDDLE:

                drive.followTrajectory(newToCenterTape);
                drive.followTrajectory(toBackboardFromCenter);
                drive.followTrajectory(leaveBackBoardfromCenter);
                break;

        }

         */


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