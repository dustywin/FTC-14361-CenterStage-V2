package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "RightBlueLeft")
public class RightBlueLeft extends LinearOpMode
{
    Robot bot;
    Pose2d myPose = new Pose2d(-36, 63, Math.toRadians(90));
    public Trajectory ejectPixel, backUp, behindGate, passThroughGate, toBackBoard, moveFromBackBoard, towardsPark, park;
    public SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    @Override
    public void runOpMode()
    {
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(myPose);

        Trajectory ejectPixel = drive.trajectoryBuilder(myPose)
                .addDisplacementMarker(0, () -> {
                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);
                })
                .addDisplacementMarker(39, () -> {
                    bot.setActiveIntakePosition(activeIntakeState.activeReverse);
                })
                .lineToLinearHeading(new Pose2d(-39, 36, Math.toRadians(0)))
                .build();

        Trajectory backUp = drive.trajectoryBuilder(ejectPixel.end())
                .addTemporalMarker(0, () -> {
                    bot.setActiveIntakePosition(activeIntakeState.inactive);
                })
                .back(6)
                .build();

        Trajectory behindGate = drive.trajectoryBuilder(backUp.end())
                .lineToLinearHeading(new Pose2d(-39, 12, Math.toRadians(180)))
                .build();

        Trajectory passThroughGate = drive.trajectoryBuilder(behindGate.end())
                .back(70)
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(passThroughGate.end())
                .lineToLinearHeading(new Pose2d(54, 30, Math.toRadians(180)))
                .addTemporalMarker(0.5, () -> {
                    bot.setWristPosition(wristState.downOuttaking);
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                })
                .addTemporalMarker(2,() -> {
                    bot.setWristPosition(wristState.downOuttaking);
                    bot.setClawPosition(clawState.open);
                })
                .build();

        Trajectory moveFromBackBoard = drive.trajectoryBuilder(toBackBoard.end())
                .addTemporalMarker(0.5,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .forward(5)
                .build();

        Trajectory towardsPark = drive.trajectoryBuilder(moveFromBackBoard.end())
                .strafeLeft(24)
                .build();

        Trajectory park = drive.trajectoryBuilder(towardsPark.end())
                .back(16)
                .build();



    }

    public void rightBlueLeftExecute(){
        drive.followTrajectory(ejectPixel);
        drive.followTrajectory(backUp);
        drive.followTrajectory(behindGate);
        drive.followTrajectory(passThroughGate);
        drive.followTrajectory(toBackBoard);
        drive.followTrajectory(moveFromBackBoard);
        drive.followTrajectory(towardsPark);
        drive.followTrajectory(park);
    }
}