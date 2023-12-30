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

@Autonomous(name = "CenterTapeLB")
public class CenterTapeLB extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(15, 61, Math.toRadians(90));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto center tape
                .lineToConstantHeading(new Vector2d(11, 36))
                .waitSeconds(1.5)
                //Moving away from center tape
                .lineToConstantHeading(new Vector2d(11, 45))
                .waitSeconds(1)
                //going to backboard
                .lineToLinearHeading(new Pose2d(51, 35, Math.toRadians(180)))
                .waitSeconds(1)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, 35))
                .waitSeconds(1)
                //Moving towards park position
                .lineToConstantHeading(new Vector2d(40, 57))
                .waitSeconds(1)
                //Parking
                .lineToLinearHeading(new Pose2d(46, 57, Math.toRadians(270)))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toCenterTape);
    }
}
