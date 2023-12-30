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

@Autonomous(name = "LeftTapeLR")
public class LeftTapeLR extends LinearOpMode
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
                //Turn
                .lineToLinearHeading(new Pose2d(-32, -10.5,Math.toRadians(180)))
                .waitSeconds(.5)
                //Passing through gate
                .lineToConstantHeading(new Vector2d(43, -10.5))
                .waitSeconds(.5)
                //Lining up with the left side of the backboard
                .lineToConstantHeading(new Vector2d(43, -28))
                .waitSeconds(.5)
                //Moving to backboard
                .lineToConstantHeading(new Vector2d(51, -28))
                .waitSeconds(2)
                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(43, -28))
                .waitSeconds(.1)
                //Lining up with parking position
                .lineToLinearHeading(new Pose2d(43, -11.5, Math.toRadians(90)))
                .waitSeconds(1)
                //Parking
                .lineToConstantHeading(new Vector2d(59, -11.5))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toLeftTape);
    }
}
