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

@Autonomous(name = "RightTapeLB")
public class RightTapeLB extends LinearOpMode
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

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(startPose)
                //Moving behind right tape
                .lineToLinearHeading(new Pose2d(15, 31, Math.toRadians(0)))
                .waitSeconds(.5)
                //Moving onto right tape
                .lineToConstantHeading(new Vector2d(11, 31))
                .waitSeconds(2)
                //going to backboard
                .lineToLinearHeading(new Pose2d(53, 31, Math.toRadians(180)))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, 31))
                .waitSeconds(.5)
                //Moving towards park position
                .lineToLinearHeading(new Pose2d(40, 57, Math.toRadians(270)))
                .waitSeconds(.5)
                //Parking
                .lineToConstantHeading(new Vector2d(49, 57))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toRightTape);
    }
}
