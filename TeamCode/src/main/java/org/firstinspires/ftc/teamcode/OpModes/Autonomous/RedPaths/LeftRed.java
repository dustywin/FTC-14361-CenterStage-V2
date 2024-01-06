package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
//import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Subsystems.HSVRedDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "LeftRed")

public class LeftRed extends LinearOpMode {
    Robot bot;
    OpenCvCamera camera;
    HSVRedDetection redDetection;
    String webcamName;

    @Override
    public void runOpMode()
    {
        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-38, -61, Math.toRadians(270));
        //bot.setInBrake();

        initCam();

        drive.setPoseEstimate(startPose);

        // ---------------------------- toLeftTape ---------------------------- //

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto left tape
                .lineToConstantHeading(new Vector2d(-45.5, -41.5))
                .waitSeconds(2)
                //Moving behind left tape
                .lineToConstantHeading(new Vector2d(-45.5, -50))
                .waitSeconds(.5)
                //Moving behind center tape
                .lineToConstantHeading(new Vector2d(-34, -50))
                .waitSeconds(.5)
                //Lining up with the gate
                .lineToConstantHeading(new Vector2d(-34, -10.5))
                .waitSeconds(.5)
                //Passing through gate
                .lineToLinearHeading(new Pose2d(45, -10.5,Math.toRadians(180)))
                .waitSeconds(.5)
                //Lining up with the left side of the backboard
                .lineToConstantHeading(new Vector2d(45, -28))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(51, -28))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(45, -28))
                .waitSeconds(.1)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(45, -11.5, Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, -11.5))
                .waitSeconds(.5)

                .build();

        // ---------------------------- toCenterTape ---------------------------- //

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto center tape
                .lineToConstantHeading(new Vector2d(-35, -35))
                .waitSeconds(2)
                //Moving behind center tape
                .lineToConstantHeading(new Vector2d(-35, -44))
                .waitSeconds(.5)
                //Moving away from center tape
                .lineToLinearHeading(new Pose2d(-53.5, -44, Math.toRadians(180)))
                .waitSeconds(.5)
                //Moving behind gate
                .lineToConstantHeading(new Vector2d(-53.5, -11.5))
                .waitSeconds(.5)
                //Passing through gate
                .lineToConstantHeading(new Vector2d(40, -11.5))
                .waitSeconds(.5)
                //Lining up with the center of the backboard
                .lineToConstantHeading(new Vector2d(40, -34))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(51, -34))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, -34))
                .waitSeconds(.5)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(40, -12.5,Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, -12.5))

                .build();

        // ---------------------------- toRightTape ---------------------------- //

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(startPose)
                //going to right tape
                .lineToLinearHeading(new Pose2d(-35,-30, Math.toRadians(180)))
                .waitSeconds(2)
                //Moving away from right tape
                .lineToConstantHeading(new Vector2d(-43, -30))
                .waitSeconds(.5)
                //Moving behind gate
                .lineToConstantHeading(new Vector2d(-43, -11.5))
                .waitSeconds(.5)
                //Passing through gate
                .lineToConstantHeading(new Vector2d(40, -11.5))
                .waitSeconds(.5)
                //Lining up with the right side of the backboard
                .lineToConstantHeading(new Vector2d(40, -40.5))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(51, -40.5))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, -40.5))
                .waitSeconds(.5)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(40, -11.5, Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, -11.5))

                .build();

        // ---------------------------- Camera ---------------------------- //

        waitForStart();

        camera.stopStreaming();

        if (isStopRequested()) return;


        switch (redDetection.getLocation())
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
        redDetection = new HSVRedDetection(telemetry);

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
}