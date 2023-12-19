package org.firstinspires.ftc.teamcode.OpModes.Autonomous.MeepMeepTraj;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@Autonomous(name = "RightRedRight")

public class RightRedRight extends LinearOpMode {Robot bot;
    public TrajectorySequence fullPath;
    @Override
    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d newStart = new Pose2d(-35, -60, 270);
        drive.setPoseEstimate(newStart);


        TrajectorySequence startToFinish = drive.trajectorySequenceBuilder(newStart)
                .waitSeconds(.5)

                .build();

        fullPath = startToFinish;

        waitForStart();

        drive.followTrajectorySequence(startToFinish);


    }
    public TrajectorySequence getRightRedRight(){
        return fullPath;
    }
}
