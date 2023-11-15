package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "LeftBlueMid")
public class LeftBlueMid extends LinearOpMode
{
    Pose2d myPose = new Pose2d(-63, 12, Math.toRadians(-180));

    Pose2d otherPose = new Pose2d(-50, 10, Math.toRadians(-180));
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);


        Trajectory Traj1 = drive.trajectoryBuilder(myPose)
                .back(15)
                .build();

        Trajectory Traj2 = drive.trajectoryBuilder(Traj1.end())
                .forward(5)
                .build();

        Trajectory Traj3 = drive.trajectoryBuilder(Traj2.end())
                .splineTo(new Vector2d(-36, 42), Math.toRadians(0))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(Traj1);
        drive.followTrajectory(Traj2);
        drive.followTrajectory(Traj3);
    }
}