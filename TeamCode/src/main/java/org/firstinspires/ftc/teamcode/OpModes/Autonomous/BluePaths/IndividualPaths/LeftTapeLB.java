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

@Autonomous(name = "LeftTapeLB")
public class LeftTapeLB extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(15, 61, Math.toRadians(90));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        //bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                //Moving away from wall
                .lineToConstantHeading(new Vector2d(15, 55))
                .waitSeconds(.5)
                //Moving behind the left tape
                .lineToConstantHeading(new Vector2d(22, 55))
                .waitSeconds(.5)
                //Moving onto the left tape
                .lineToConstantHeading(new Vector2d(22, 41))
                .waitSeconds(2)
                //Moving back behind the left tape
                .lineToConstantHeading(new Vector2d(22, 55))
                .waitSeconds(.5)
                //Moving towards backboard zone
                .lineToConstantHeading(new Vector2d(36, 55))
                .waitSeconds(1)
                //Moving to backboard
                .lineToLinearHeading(new Pose2d(53, 40, Math.toRadians(180)))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(48.5, 40))
                .waitSeconds(.5)
                //Moving towards park position
                .lineToConstantHeading(new Vector2d(40, 40))
                .waitSeconds(1)
                //Line up to park position
                .lineToLinearHeading(new Pose2d(40, 57, Math.toRadians(270)))
                .waitSeconds(.5)
                //Parking
                .lineToConstantHeading(new Vector2d(49, 57))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toLeftTape);
    }
}
