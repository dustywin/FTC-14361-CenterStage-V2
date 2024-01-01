package org.firstinspires.ftc.teamcode.OpModes.Autonomous.RedPaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.HSVRedDetection;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "RightRedLeft")
public class RightRedLeft extends LinearOpMode{
    Robot bot;
    OpenCvCamera camera;
    HSVRedDetection redDetection;
    String webcamName;

    public void runOpMode() {

        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d start = new Pose2d(9, -61, Math.toRadians(270));
        drive.setPoseEstimate(start);
        //initCam();

        TrajectorySequence everything = drive.trajectorySequenceBuilder(start)
                .waitSeconds(1)

                .addDisplacementMarker(() -> {
                    bot.setVirtualFourBarState(virtualFourBarState.init);
                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                })
                .lineToConstantHeading(new Vector2d(17, -61))
                .addDisplacementMarker( () -> {
                    bot.setWristPosition(wristState.downOuttaking);
                    bot.setWristState(wristState.downOuttaking);
                })

                .lineToLinearHeading(new Pose2d(17, -30, Math.toRadians(0)))
                .addDisplacementMarker(15, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.autoDrop, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.autoDrop);

                })
                .waitSeconds(2)
                //Moving onto right tape
                .lineToConstantHeading(new Vector2d(9, -30))
                .addDisplacementMarker(() -> {
                    bot.setClawPosition(clawState.leftClose);
                    bot.setClawState(clawState.leftClose);
                })
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(15, -34))

                .waitSeconds(1)



                .build();
        waitForStart();
        if(isStopRequested()) return;

        //     bot.intakeSlide.setPosition(50);
        bot.setVirtualFourBarState(virtualFourBarState.intaking);
        bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
        bot.setWristPosition(wristState.downIntaking);
        bot.setWristState(wristState.downIntaking);
        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);


        drive.followTrajectorySequence(everything);


    }

}
