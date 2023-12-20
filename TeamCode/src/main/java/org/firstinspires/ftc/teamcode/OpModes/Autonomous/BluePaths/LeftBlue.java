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

@Autonomous(name = "LeftBlue")

public class LeftBlue extends LinearOpMode {

    Robot bot;
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;

    @Override
    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(15, 61, Math.toRadians(90)))
        bot.setInBrake();

        initCam();

        drive.setPoseEstimate(startPose);

        // ---------------------------- toLeftTape ---------------------------- //

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(15, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(22.5, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(20.5, 43))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(22.5, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 55))
                .waitSeconds(.5)
                .lineToLinearHeading(new Pose2d(40, 41, Math.toRadians(180)))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(48.5, 41))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 41))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 59))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(58, 59))
                .waitSeconds(.5)

                .build();

        // ---------------------------- toCenterTape ---------------------------- //

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(startPose)
        drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                .lineToConstantHeading(new Vector2d(15, 36))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(15, 45))
                .waitSeconds(.5)
                .lineToLinearHeading(new Pose2d(40, 45, Math.toRadians(180)))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 35))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(49, 35))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 35))
                .lineToConstantHeading(new Vector2d(40, 59))
                .lineToConstantHeading(new Vector2d(58, 59))

                .build();

        // ---------------------------- toRightTape ---------------------------- //

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(12, 31, Math.toRadians(0)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(49, 36, Math.toRadians(180)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, 36, Math.toRadians(180)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, 60, Math.toRadians(180)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(60, 60, Math.toRadians(180)))

                .build();

        // ---------------------------- Camera ---------------------------- //

        waitForStart();

        camera.stopStreaming();

        if (isStopRequested()) return;

        bot.setIntakeSlideState(intakeSlidesState.STATION);
        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);


        switch (blueDetection.getLocation())
        {
            case LEFT:
                drive.setPoseEstimate(startPose);

                drive.followTrajectorySequence(toLeftTape);

                break;
            case RIGHT:
                drive.setPoseEstimate(startPose);

                drive.followTrajectorySequence(toRightTape);

                break;
            case MIDDLE:
                drive.setPoseEstimate(startPose);

                drive.followTrajectorySequence(toCenterTape);

                break;
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
}