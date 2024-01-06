package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.HSVRedDetection;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "RightRedMid")
public class RightRedMid extends LinearOpMode {
    Robot bot;
    OpenCvCamera camera;
    HSVRedDetection redDetection;
    String webcamName;

    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d start = new Pose2d(15, -61, Math.toRadians(270));
        drive.setPoseEstimate(start);
        //initCam();

        TrajectorySequence everything = drive.trajectorySequenceBuilder(start)
                .waitSeconds(1)
                .addDisplacementMarker(0, () -> {

                })
                .addDisplacementMarker(5, () -> {
                    bot.setArmState(armState.init);
                    bot.setArmPosition(armState.init, armExtensionState.extending);
                })
                .addDisplacementMarker(15, () -> {
                    bot.setArmPosition(armState.autoDrop, armExtensionState.extending);
            bot.setArmState(armState.autoDrop);

                    bot.setWristPosition(wristState.downOuttaking);
                    bot.setWristState(wristState.downOuttaking);
                })
                .addDisplacementMarker(22, () -> {
                    bot.setClawPosition(clawState.leftClose);
                    bot.setClawState(clawState.leftClose);                })

//                .addDisplacementMarker(23, () -> {
//                    bot.setVirtualFourBarPosition(virtualFourBarState.autoDrop, virtualFourBarExtensionState.extending);
//                    bot.setVirtualFourBarState(virtualFourBarState.autoDrop);
//                })
//

                .waitSeconds(2)
                .lineToConstantHeading(new Vector2d(15, -38.75))
                .waitSeconds(1)
//                .lineToLinearHeading(new Pose2d(62, -36, Math.toRadians(180)))
////                .addDisplacementMarker(45, () -> {
////                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
////                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);
////                })
////                .addDisplacementMarker(67, () -> {
////                    bot.setClawPosition(clawState.rightOpen);
////                    bot.setClawState(clawState.rightOpen);
////                })
//                .waitSeconds(.5)
//                .lineToConstantHeading(new Vector2d(48, -36))
//                .waitSeconds(.5)
//                .lineToConstantHeading(new Vector2d(48, -58))
//                .waitSeconds(.5)
//                .lineToConstantHeading(new Vector2d(65, -58))
//
//
//
                .build();
        waitForStart();
        if(isStopRequested()) return;

   //     bot.intakeSlide.setPosition(50);
        bot.setArmState(armState.intaking);
        bot.setArmPosition(armState.intaking, armExtensionState.extending);
        bot.setWristPosition(wristState.downIntaking);
        bot.setWristState(wristState.downIntaking);
        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);


        drive.followTrajectorySequence(everything);


        }
    }






