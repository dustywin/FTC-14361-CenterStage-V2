package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LeftRedMid")
public class LeftRedMid extends LinearOpMode {
    public Robot bot;
    Pose2d myPose = new Pose2d(-36, -63, Math.toRadians(90));

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);

        Trajectory pushPixel = drive.trajectoryBuilder(myPose)
                .addTemporalMarker(0.1, () -> {
                    bot.setClawPosition(clawState.close);
                    bot.setWristPosition(wristState.normal);
                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                })
                .back(32)
                .build();

        Trajectory backUp = drive.trajectoryBuilder(pushPixel.end())
                .forward(6)
                .build();

        Trajectory moveFromTape = drive.trajectoryBuilder(backUp.end())
                .strafeRight(20)
                .build();

        Trajectory behindGate = drive.trajectoryBuilder(moveFromTape.end())
                .back(27)
                .build();

        Trajectory passThroughGate = drive.trajectoryBuilder(behindGate.end())
                .strafeLeft(70)
                //lineToLinearHeading dist. = 80
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(passThroughGate.end())
                .splineTo(new Vector2d(48, -36), Math.toRadians(180))
                //.lineToLinearHeading(new Pose2d(48, 36, Math.toRadians(180)))
                .addTemporalMarker(0.5, () -> {
                    bot.setWristPosition(wristState.sideways);
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                })
                .addTemporalMarker(2, () -> {
                    bot.setWristPosition(wristState.normal);
                    bot.setClawPosition(clawState.open);
                })
                .build();

        Trajectory moveFromBackBoard = drive.trajectoryBuilder(toBackBoard.end())
                .addTemporalMarker(0.5, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .forward(5)
                .build();

        Trajectory towardsPark = drive.trajectoryBuilder(moveFromBackBoard.end())
                .strafeRight(10)
                .build();

        Trajectory park = drive.trajectoryBuilder(towardsPark.end())
                .back(15)
                .build();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        drive.followTrajectory(pushPixel);
        drive.followTrajectory(backUp);
        drive.followTrajectory(moveFromTape);
        drive.followTrajectory(behindGate);
        drive.followTrajectory(passThroughGate);
        drive.followTrajectory(toBackBoard);
        drive.followTrajectory(moveFromBackBoard);
        drive.followTrajectory(towardsPark);
        drive.followTrajectory(park);

    }
}