package org.firstinspires.ftc.teamcode.OpModes.Autonomous.BluePaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LeftBlueRight")
public class LeftBlueRight extends LinearOpMode {
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d myPose = new Pose2d(16, 63, Math.toRadians(90));
        Robot bot = new Robot(hardwareMap, telemetry);

        bot.setInBrake();

        drive.setPoseEstimate(myPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(myPose)
                .addTemporalMarker(0, () -> {
                    bot.setWristPosition(wristState.normal);
                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setClawPosition(clawState.close);
                })

                .lineToLinearHeading(new Pose2d(12, 31, Math.toRadians(0)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(10, 31, Math.toRadians(0)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(55, 36, Math.toRadians(180)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, 36, Math.toRadians(180)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, 66, Math.toRadians(180)))
                .lineToLinearHeading(new Pose2d(62, 66, Math.toRadians(180)))


                .addTemporalMarker(3,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setWristPosition(wristState.sideways);
                })

                .addTemporalMarker(5,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                })

                .addTemporalMarker(6,() -> {
                    bot.setClawPosition(clawState.open);
                })

                .addTemporalMarker(7,() -> {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .build();

        drive.followTrajectorySequence(trajSeq);
    }
}


