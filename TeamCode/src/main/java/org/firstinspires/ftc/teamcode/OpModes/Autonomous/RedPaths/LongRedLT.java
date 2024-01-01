package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LongRedLT")
public class LongRedLT extends LinearOpMode
{

    Robot bot;
    Pose2d startPose = new Pose2d(-33, -61, Math.toRadians(270));
    private double angleOffset = 2;

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        bot.setInBrake();

        drive.setPoseEstimate(startPose);

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                //Moving onto left tape
                .addDisplacementMarker(() -> {
                    bot.setVirtualFourBarState(virtualFourBarState.init);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(-50, -61))
                .addDisplacementMarker( () -> {
                    bot.setWristPosition(wristState.downOuttaking);
                    bot.setWristState(wristState.downOuttaking);
                })
                .addDisplacementMarker(15, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.autoDrop, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.autoDrop);

                })

                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(-50, -44))
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.leftClose);
                    bot.setClawState(clawState.leftClose);
                })

                .waitSeconds(1)
                //Lining up with the gate
                .lineToConstantHeading(new Vector2d(-33, -44))
                .lineToConstantHeading(new Vector2d(-33, -14))
                .turn(Math.toRadians(-90 - angleOffset))
                .lineToConstantHeading(new Vector2d(39, -14))

                .addDisplacementMarker( () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.outtakeSlide.setPosition(300);
                })

                .waitSeconds(.5)
                //go through gate

                //go to backboard
                .lineToConstantHeading(new Vector2d(41, -30))
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.rightClose);
                    bot.setClawState(clawState.rightClose);
                })
                .lineToConstantHeading(new Vector2d(39, -30))


                .build();

                //Turn
//                .lineToLinearHeading(new Pose2d(43, -10.5,Math.toRadians(180)))
//                .waitSeconds(.5)




        waitForStart();


        if (isStopRequested()) return;
        bot.setVirtualFourBarState(virtualFourBarState.intaking);
        bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
        bot.setWristPosition(wristState.downIntaking);
        bot.setWristState(wristState.downIntaking);
        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);

            drive.followTrajectorySequence(toLeftTape);
    }
}
