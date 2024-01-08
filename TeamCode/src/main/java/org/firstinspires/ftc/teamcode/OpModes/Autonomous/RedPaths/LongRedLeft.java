package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;

@Autonomous(name = "LongRedLeft")
public class LongRedLeft extends LinearOpMode {
    Robot bot;
    Pose2d startPose = new Pose2d(-38, -61, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);

        drive.setPoseEstimate(startPose);

        TrajectorySequence toLeftTape = drive.trajectorySequenceBuilder(startPose)
                //Initialization
                .addDisplacementMarker(() -> {
                    bot.setWristPosition(wristState.init);
                    bot.setWristState(wristState.init);
                    bot.setArmState(armState.init);
                    bot.setArmPosition(armState.init, armExtensionState.extending);
                })
                .waitSeconds(1)
                //Moving arm and wrist to scoring position
                .lineToConstantHeading(new Vector2d(-46, -60))

                .addDisplacementMarker(() -> {
                    bot.setWristPosition(wristState.outtaking);
                    bot.setWristState(wristState.outtaking);
                })

                .addDisplacementMarker(15, () -> {
                    bot.setArmPosition(armState.autoDrop, armExtensionState.extending);
                    bot.setArmState(armState.autoDrop);
                })
                .waitSeconds(1)
                //Moving to left tape
                .lineToConstantHeading(new Vector2d(-46, -44))

                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.leftClose);
                    bot.setClawState(clawState.leftClose);
                })

                .waitSeconds(1)
                //Lining up with the gate
                .lineToConstantHeading(new Vector2d(-35, -44))
                .lineToConstantHeading(new Vector2d(-35, -10))
                .turn(Math.toRadians(-90))
                //Passing through the gate
                .lineToConstantHeading(new Vector2d(39, -8))
                //Moving arm and slides to scoring position
                .addDisplacementMarker(() -> {
                    bot.outtakeSlide.setPosition(300);

                    bot.setArmPosition(armState.outtaking, armExtensionState.extending);
                    bot.setArmState(armState.outtaking);
                })

                .waitSeconds(.5)
                //go to backboard
                .lineToConstantHeading(new Vector2d(48, -8))
                .lineToConstantHeading(new Vector2d(56, -28))
                //Scoring
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.rightClose);
                    bot.setClawState(clawState.rightClose);
                })

                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(48, -28))
                .lineToConstantHeading(new Vector2d(48, -16))
                .lineToConstantHeading(new Vector2d(54, -16))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toLeftTape);

        bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
        bot.setOuttakeSlideState(outtakeSlidesState.STATION);
        bot.setArmState(armState.intaking);
        bot.setArmPosition(armState.intaking, armExtensionState.extending);
        bot.setWristPosition(wristState.intaking);
        bot.setWristState(wristState.intaking);
        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);
    }
}

