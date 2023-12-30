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

@Autonomous(name = "RightTapeRB")
public class RightTapeRB extends LinearOpMode
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

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto right tape
                .lineToConstantHeading(new Vector2d(-45.5, 41.5))
                .waitSeconds(2)
                //Moving behind right tape
                .lineToConstantHeading(new Vector2d(-45.5, 50))
                .waitSeconds(.5)
                //Moving behind center tape
                .lineToConstantHeading(new Vector2d(-34, 50))
                .waitSeconds(.5)
                //Lining up with the gate
                .lineToConstantHeading(new Vector2d(-34, 10.5))
                .waitSeconds(.5)
                //Turn
                .lineToLinearHeading(new Pose2d(-32, 10.5,Math.toRadians(180)))
                .waitSeconds(.5)
                //Passing through gate
                .lineToConstantHeading(new Vector2d(43, 10.5))
                .waitSeconds(.5)
                //Lining up with the right side of the backboard
                .lineToConstantHeading(new Vector2d(43, 29))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(53, 29))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(43, 29))
                .waitSeconds(.1)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(43, 14, Math.toRadians(270)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, 14))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toRightTape);
    }
}
