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

@Autonomous(name = "LongRedMid")
public class LongRedMid extends LinearOpMode
{
    Robot bot;
    Pose2d startPose = new Pose2d(-38, -61, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);

        drive.setPoseEstimate(startPose);

        TrajectorySequence toMidTape = drive.trajectorySequenceBuilder(startPose)
                //Initialization
                .addDisplacementMarker(() -> {
                    bot.setWristPosition(wristState.init);
                    bot.setWristState(wristState.init);
                    bot.setArmState(armState.init);
                    bot.setArmPosition(armState.init, armExtensionState.extending);
                })
                .waitSeconds(1)
                //Moving arm and wrist to scoring position
                .lineToConstantHeading(new Vector2d(-35, -60))

                .addDisplacementMarker( () -> {
                    bot.setWristPosition(wristState.outtaking);
                    bot.setWristState(wristState.outtaking);
                })

                .addDisplacementMarker(15, () -> {
                    bot.setArmPosition(armState.autoDrop, armExtensionState.extending);
                    bot.setArmState(armState.autoDrop);

                })
                .waitSeconds(.5)
                //Moving onto middle tape and scoring
                .lineToConstantHeading(new Vector2d(-35, -35))
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.leftClose);
                    bot.setClawState(clawState.leftClose);
                })

                .waitSeconds(.5)
                //Lining up with the gate
                .lineToConstantHeading(new Vector2d(-35, -40))
                .lineToConstantHeading(new Vector2d(-48, -40))
                .lineToConstantHeading(new Vector2d(-48, -10))
                .turn(Math.toRadians(-90))
                //Going through gate
                .lineToConstantHeading(new Vector2d(39, -8))
                //Moving arm and slides to scoring position
                .addDisplacementMarker( () -> {
                    bot.outtakeSlide.setPosition(300);

                    bot.setArmPosition(armState.outtaking, armExtensionState.extending);
                    bot.setArmState(armState.outtaking);
                })

                .waitSeconds(.5)
                //Go to backboard
                .lineToConstantHeading(new Vector2d(56, -32))
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.rightClose);
                    bot.setClawState(clawState.rightClose);
                })

                //Moving away from backboard
                .lineToConstantHeading(new Vector2d(48, -32))
                //Parking
                .lineToConstantHeading(new Vector2d(48, -16))
                .lineToConstantHeading(new Vector2d(54, -16))

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(toMidTape);

        bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
        bot.setOuttakeSlideState(outtakeSlidesState.STATION);
        bot.setArmState(armState.intaking);
        bot.setArmPosition(armState.intaking, armExtensionState.extending);
        bot.setWristPosition(wristState.intaking);
        bot.setWristState(wristState.intaking);
        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);

        drive.followTrajectorySequence(toMidTape);
    }
}
