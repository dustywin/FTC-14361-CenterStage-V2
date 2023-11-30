package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths;
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

@Autonomous(name = "RightBlueMid")
public class RightBlueMid extends LinearOpMode
{
    Robot bot;
    Pose2d myPose = new Pose2d(-36, 63, Math.toRadians(90));

    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(myPose);

        Trajectory pushPixel = drive.trajectoryBuilder(myPose)
                .addDisplacementMarker(0, () -> {
                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);
                })
                .back(31)
                .build();

        Trajectory backUp = drive.trajectoryBuilder(pushPixel.end())
                .forward(6)
                .build();

        Trajectory moveFromTape = drive.trajectoryBuilder(backUp.end())
                .strafeLeft(20)
                .build();

        Trajectory behindGate1 = drive.trajectoryBuilder(moveFromTape.end())
                .back(30)
                .build();

        Trajectory behindGate2 = drive.trajectoryBuilder(behindGate1.end())
                .lineToLinearHeading(new Pose2d(-36, 6, Math.toRadians(180)))
                .build();

        Trajectory passThroughGate1 = drive.trajectoryBuilder(behindGate2.end())
                .back(60)
                //lineToLinearHeading strafe dist. = 80
                .build();

        Trajectory passThroughGate2 = drive.trajectoryBuilder(behindGate2.end())
                .strafeLeft(29)
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(passThroughGate2.end())
                .lineToLinearHeading(new Pose2d(57, 29, Math.toRadians(180)))
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
//                .addTemporalMarker(0.5,() -> {
//                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
//                })
                .forward(5)
                .build();

        Trajectory towardsPark = drive.trajectoryBuilder(moveFromBackBoard.end())
                .strafeLeft(24)
                .build();

        Trajectory park = drive.trajectoryBuilder(towardsPark.end())
                .back(16)
                .build();

        waitForStart();

        if(isStopRequested())
        {
            return;
        }

        drive.followTrajectory(pushPixel);
        drive.followTrajectory(backUp);
        drive.followTrajectory(moveFromTape);
        drive.followTrajectory(behindGate1);
        drive.followTrajectory(behindGate2);
        drive.followTrajectory(passThroughGate1);
        drive.followTrajectory(passThroughGate2);
        drive.followTrajectory(toBackBoard);
        drive.followTrajectory(moveFromBackBoard);
        drive.followTrajectory(towardsPark);
        drive.followTrajectory(park);
    }
}