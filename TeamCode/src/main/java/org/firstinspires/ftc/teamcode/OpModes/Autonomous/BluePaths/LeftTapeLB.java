package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths;
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
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(15, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(22.5, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(22.5, 40))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(22.5, 55))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(40, 55))
                .waitSeconds(.5)
                .lineToLinearHeading(new Pose2d(51, 35, Math.toRadians(180)))
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

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toLeftTape);
    }
}
