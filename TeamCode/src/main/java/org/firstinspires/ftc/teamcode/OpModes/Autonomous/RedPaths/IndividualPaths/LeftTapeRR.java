package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths.IndividualPaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LeftTapeRR")
public class LeftTapeRR extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(15, -61, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                //Moving behind the left tape
                .lineToConstantHeading(new Vector2d(24, -55))
                .waitSeconds(1)
                //Moving onto the left tape
                .lineToConstantHeading(new Vector2d(24, -43))
                .waitSeconds(2)
                //Moving back behind the left tape
                .lineToConstantHeading(new Vector2d(24, -55))
                .waitSeconds(1)
                //Moving towards backboard zone
                .lineToConstantHeading(new Vector2d(36, -55))
                .waitSeconds(1)
                //Moving to backboard
                .lineToLinearHeading(new Pose2d(51, -28, Math.toRadians(180)))
                .waitSeconds(.5)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(48.5, -28))
                .waitSeconds(1)
                //Moving towards park position
                .lineToConstantHeading(new Vector2d(40, -28))
                .waitSeconds(1)
                //Line up to park position
                .lineToLinearHeading(new Pose2d(40, -57, Math.toRadians(270)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(46, -57))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toLeftTape);
    }
}