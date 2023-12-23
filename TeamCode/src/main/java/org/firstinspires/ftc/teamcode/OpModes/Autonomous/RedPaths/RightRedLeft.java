package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@Autonomous(name = "RightRedLeft")

public class RightRedLeft extends LinearOpMode {
    Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d newStart = new Pose2d(-35, -60, 270);
        drive.setPoseEstimate(newStart);

        TrajectorySequence startToFinish = drive.trajectorySequenceBuilder(newStart)

                .lineToConstantHeading(new Vector2d(15, -33))
                .lineToLinearHeading(new Pose2d(15, -32, Math.toRadians(0)))

                .build();

        waitForStart();

        drive.followTrajectorySequence(startToFinish);


    }
}
