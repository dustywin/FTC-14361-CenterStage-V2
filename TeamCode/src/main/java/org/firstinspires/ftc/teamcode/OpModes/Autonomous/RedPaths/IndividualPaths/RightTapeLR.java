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

@Autonomous(name = "RightTapeLR")
public class RightTapeLR extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(-38, -61, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toRightTape = drive.trajectorySequenceBuilder(startPose)
                //Moving behind right tape
                .lineToLinearHeading(new Pose2d(-38,-30, Math.toRadians(180)))
                .waitSeconds(.5)
                //Moving onto right tape
                .lineToConstantHeading(new Vector2d(-35, -30))
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
                .lineToConstantHeading(new Vector2d(53, -40.5))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(40, -40.5))
                .waitSeconds(.5)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(40, -14, Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, -14))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toRightTape);
    }
}