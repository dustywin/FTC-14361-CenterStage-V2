package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;
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

@Autonomous(name = "RightRedLeft")
public class
RightRedLeft extends LinearOpMode {
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d myPose = new Pose2d(16, -63, Math.toRadians(270));
        Robot bot = new Robot(hardwareMap, telemetry);
        int rOffset = -9;

        bot.setInBrake();

        drive.setPoseEstimate(myPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(myPose)
                .addTemporalMarker(0, () -> {
                    bot.setWristPosition(wristState.normal);
                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setClawPosition(clawState.close);
                })

                .lineToLinearHeading(new Pose2d(14, -31, Math.toRadians(0 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(12, -31, Math.toRadians(0 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(54, -38, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, -38, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(47, -71, Math.toRadians(180 + rOffset)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(57, -71, Math.toRadians(180 + rOffset)))

                .addTemporalMarker(3,() -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    //bot.setWristPosition(wristState.sideways);
                })

                .addTemporalMarker(5,() -> {
                    //bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                })

                .addTemporalMarker(6,() -> {
                    //bot.setClawPosition(clawState.open);
                })

                .addTemporalMarker(7,() -> {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .build();

        drive.followTrajectorySequence(trajSeq);
    }
}

