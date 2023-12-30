package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths.IndividualPaths;
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

@Autonomous(name = "CenterTapeRB")
public class CenterTapeRB extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(-35, 61, Math.toRadians(90));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto center tape
                .lineToConstantHeading(new Vector2d(-35, 35))
                .waitSeconds(2)
                //Moving behind center tape
                .lineToConstantHeading(new Vector2d(-35, 44))
                .waitSeconds(.5)
                //Moving away from center tape
                .lineToLinearHeading(new Pose2d(-51, 44, Math.toRadians(180)))
                .waitSeconds(.5)
                //Moving behind gate
                .lineToConstantHeading(new Vector2d(-51, 11.5))
                .waitSeconds(.5)
                //Passing through gate
                .lineToConstantHeading(new Vector2d(40, 11.5))
                .waitSeconds(.5)
                //Lining up with the center of the backboard
                .lineToConstantHeading(new Vector2d(40, 34))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(53, 34))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, 34))
                .waitSeconds(.5)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(40, 14,Math.toRadians(270)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, 14))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toCenterTape);
    }
}