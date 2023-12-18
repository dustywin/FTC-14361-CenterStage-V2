package org.firstinspires.ftc.teamcode.OpModes.Autonomous.MeepMeepTraj;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LeftRedLeft")
public class LeftRedLeft extends LinearOpMode {
    Robot bot;
    public TrajectorySequence fullPath;
    @Override
    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d newStart = new Pose2d(-35, -60, 270);
        drive.setPoseEstimate(newStart);


         TrajectorySequence startToFinish = drive.trajectorySequenceBuilder(newStart)
                .lineToConstantHeading(new Vector2d(-47, -43))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(-47, -50))
                .lineToConstantHeading(new Vector2d(-34, -50))
                .waitSeconds(.5)
                .lineToLinearHeading(new Pose2d(-34, -12, Math.toRadians(180)))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(37, -12))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(37, -29))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(49.5, -29))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(37, -29))
                .lineToConstantHeading(new Vector2d(37, -12))
                .waitSeconds(.5)
                .lineToConstantHeading(new Vector2d(57, -12))

                .waitSeconds(.5)

                .build();


        waitForStart();

        fullPath = startToFinish;
        drive.followTrajectorySequence(startToFinish);


    }

    // i have no idea if this will work, if it does though we're chilling
    public TrajectorySequence getLeftRedLeftTraj(){
        return fullPath;
    }

}