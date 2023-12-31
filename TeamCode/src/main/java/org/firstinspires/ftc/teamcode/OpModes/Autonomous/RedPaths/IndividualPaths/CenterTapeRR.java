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

@Autonomous(name = "CenterTapeRR")
public class CenterTapeRR extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(9.5, -61, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toCenterTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto center tape
                .lineToConstantHeading(new Vector2d(13, -35))
                .waitSeconds(1.5)
                //Moving away from center tape
                .lineToConstantHeading(new Vector2d(13, -45))
                .waitSeconds(1)
                //going to backboard
                .lineToLinearHeading(new Pose2d(51, -33, Math.toRadians(180)))
                .waitSeconds(1)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, -33))
                .waitSeconds(1)
                //Moving towards park position
                .lineToLinearHeading(new Pose2d(40, -57, Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(46, -57))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toCenterTape);
    }
}
