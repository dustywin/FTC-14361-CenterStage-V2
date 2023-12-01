package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
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
public class LeftRedMid extends LinearOpMode
{
    Robot bot;
    Pose2d myPose = new Pose2d(-36, -63, Math.toRadians(-90));

    public SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    public Trajectory backUp, behindGate, passThroughGate, toBackBoard, moveFromBackBoard, towardsPark, park, moveFromTape, pushPixel;

    @Override
    public void runOpMode()
    {
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(myPose);

        Trajectory pushPixel = drive.trajectoryBuilder(myPose)
                .addDisplacementMarker(0, () -> {
                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                })
                .addDisplacementMarker(5, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);
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
                //lineToLinearHeading strafe dist. = 80
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(passThroughGate.end())
                .lineToLinearHeading(new Pose2d(54, -30, Math.toRadians(180)))
                .addTemporalMarker(0.5, () -> {
                    bot.setWristPosition(wristState.sideways);
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                })
                .addTemporalMarker(2,() -> {
                    bot.setWristPosition(wristState.normal);
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
                .strafeRight(24)
                .build();

        Trajectory park = drive.trajectoryBuilder(towardsPark.end())
                .back(16)
                .build();

        waitForStart();

        if(isStopRequested())
        {
            return;
        }



    }

    public void leftRedMidExecute(){

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