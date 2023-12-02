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
import org.firstinspires.ftc.teamcode.Subsystems.RedDetection;
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
    RedDetection redDetection;
    String webcamName;
    @Override
    public void runOpMode() {
        int rOffset = -10;

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d myPose = new Pose2d(8, -63, Math.toRadians(270));


        Pose2d newStart = new Pose2d(25,-50,270);

        initCam();


        drive.setPoseEstimate(newStart);
        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(newStart)
                .lineToConstantHeading(new Vector2d(25, -21))

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

                .lineToConstantHeading(new Vector2d(25, -30))

                .addTemporalMarker(2, () -> {
                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })

                .lineToLinearHeading(new Pose2d(62, -25.5, Math.toRadians(180)))
                .addTemporalMarker(3, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                })
                .waitSeconds(.5)

                .lineToConstantHeading(new Vector2d(60.5, -25.5))
                .addTemporalMarker(3.9, () -> {

                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);

                })
                .waitSeconds(.25)

                .lineToConstantHeading(new Vector2d(55, -25.5))

                .lineToConstantHeading(new Vector2d(60, -52))

                .lineToConstantHeading(new Vector2d(68, -52))

                .addTemporalMarker(4.5,() -> {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })

                .build();

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(myPose)
                .addTemporalMarker(0, () -> {
                    bot.setWristPosition(wristState.normal);
                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setClawPosition(clawState.close);
                })

                .lineToLinearHeading(new Pose2d(14, -33, Math.toRadians(0 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(10, -33, Math.toRadians(0 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(52, -50, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, -50, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, -78, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(57, -78, Math.toRadians(180 + rOffset)))

                .addTemporalMarker(3,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setWristPosition(wristState.sideways);
                })

                .addTemporalMarker(5,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                })
                .waitSeconds(.5)
                .addTemporalMarker(6.5,() -> {
                    bot.setClawPosition(clawState.open);
                })
               

                .addTemporalMarker(7,() -> {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .build();

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(newStart)
                .lineToConstantHeading(new Vector2d(25, -25.5))


                .addTemporalMarker(0, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.intaking);

                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                })

                .addTemporalMarker(3.5, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);


                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })

                .lineToConstantHeading(new Vector2d(30, -25.5))
                .addTemporalMarker(5.5, () -> {

                    bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);
                })

                .lineToConstantHeading(new Vector2d(25, -25.5))

                .lineToConstantHeading(new Vector2d(25, -30.5))

                .lineToConstantHeading(new Vector2d(35, -30.5))

                .lineToConstantHeading(new Vector2d(62, -30.5))
                .lineToConstantHeading(new Vector2d(61, -30.5))

                .addTemporalMarker(8, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);

                })
                .waitSeconds(.5)


                .addTemporalMarker(8.9, () -> {


                    bot.setClawState(clawState.open);
                    bot.setClawPosition(clawState.open);


                })
                .waitSeconds(1)


                .lineToConstantHeading(new Vector2d(55, -52))

                .lineToConstantHeading(new Vector2d(68, -52))

                .addTemporalMarker(8.7,() -> {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .waitSeconds(1)

                .build();






        waitForStart();


        camera.stopStreaming();




        bot.setIntakeSlideState(intakeSlidesState.STATION);
        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);

        switch (redDetection.getLocation()) {
            case LEFT:
                drive.setPoseEstimate(myPose);
                drive.followTrajectorySequence(toLeftTape);
                break;

            case RIGHT:
                drive.setPoseEstimate(newStart);
               drive.followTrajectorySequence(toRightTape);

                break;
            case MIDDLE:

                drive.setPoseEstimate(newStart);
                drive.followTrajectorySequence(toCenterTape);
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
        redDetection = new RedDetection(telemetry);

        // yeah what this does is it gets the thing which uses the thing so we can get the thing
        /*
        (fr tho idk what pipeline does, but from what I gathered,
         we basically passthrough our detection into the camera
         and we feed the streaming camera frames into our Detection algorithm)
         */
        camera.setPipeline(redDetection);

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