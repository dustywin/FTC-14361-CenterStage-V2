package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.slowDownState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import org.firstinspires.ftc.teamcode.util.robotConstants.intakeSlide;

import org.firstinspires.ftc.teamcode.util.robotConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;
    private int intakeSlideCountAdd = 0;
    private int intakeSlideCountSubstract = 0;

    @Override
    public void init() {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime() + " seconds.");
        telemetry.update();

        bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
        bot.setVirtualFourBarState(virtualFourBarState.init);

        // bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
        // bot.setIntakeSlideState(intakeSlidesState.STATION);

        bot.setClawPosition(clawState.close);
        bot.setClawState(clawState.close);

        bot.setLeftClawState(clawState.leftClose);
        bot.setRightClawState(clawState.leftClose);

        bot.setDrone();

        bot.setSlowDownState(slowDownState.FULL);
    }

    // ---------------------------- LOOPING ---------------------------- //

    @Override
    public void loop() {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
//        telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition());
//        telemetry.addLine("Intake Slide Count " + intakeSlideCountAdd);
//        telemetry.addLine("Intake Slide Subtract Count " + intakeSlideCountSubstract);

        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B: init / " + bot.virtualFourBar.getvirtualFourBarExtensionState());
        telemetry.addLine("Right Claw Position: " + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position: " + bot.claw.getLeftClawPosition());
        telemetry.addLine("Right V4B Position: " + bot.virtualFourBar.getV4bRightPosition() + " ticks.");
        telemetry.addLine("Right V4B Position: " + (1 - bot.virtualFourBar.getV4bRightPosition() / 360) + " decimal.");
        telemetry.addLine("Left V4B Position: " + bot.virtualFourBar.getV4BLeftPosition() + " ticks.");

        telemetry.addLine("Left V4B Position: " + (1 - bot.virtualFourBar.getV4BLeftPosition() / 360) + " decimal.");
        //telemetry.addLine("Intake Slide Encoder Tick Count " + intakeSlideCountSubstract);
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();


        // ---------------------------- DRIVER CODE ---------------------------- //

        if (driver.wasJustPressed(GamepadKeys.Button.START)) {
            bot.driveTrain.resetIMU();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
            bot.setSlowDownState(slowDownState.FULL);
            bot.driveTrain.setFullPower();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
            bot.setSlowDownState(slowDownState.SLOW);
            bot.driveTrain.setSlowDownMotorPower();
        }

//        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
//            if (bot.getvirtualFourBarState().equals(virtualFourBarState.intaking)) {
//                bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//                bot.setIntakeSlideState(intakeSlidesState.STATION);
//            } else {
//                bot.setIntakeSlideState(intakeSlidesState.HIGHIN);
//                bot.setIntakeSlidePosition(intakeSlidesState.HIGHIN, extensionState.extending);
//            }
//
//        }
//
//        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
//            if (bot.getvirtualFourBarState().equals(virtualFourBarState.intaking)) {
//                bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//                bot.setIntakeSlideState(intakeSlidesState.STATION);
//            } else {
//                bot.setIntakeSlideState(intakeSlidesState.MEDIUMIN);
//                bot.setIntakeSlidePosition(intakeSlidesState.MEDIUMIN, extensionState.extending);
//            }
//        }
//
//        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
//            bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//            bot.setIntakeSlideState(intakeSlidesState.STATION);
//        }
//
        if (driver.wasJustPressed(GamepadKeys.Button.A)) {
            if (bot.getActiveIntakeState() != null && (bot.getActiveIntakeState().equals(activeIntakeState.active))) {
                bot.setActiveIntakePosition(activeIntakeState.inactive);
                bot.setActiveIntakeState(activeIntakeState.inactive);
            } else {
                bot.setActiveIntakePosition(activeIntakeState.active);
                bot.setActiveIntakeState(activeIntakeState.active);
            }
        }

        if (driver.wasJustPressed(GamepadKeys.Button.Y)) {
            if (bot.getActiveIntakeState() != null && bot.getActiveIntakeState().equals(activeIntakeState.activeReverse)) {
                bot.setActiveIntakePosition(activeIntakeState.inactive);
                bot.setActiveIntakeState(activeIntakeState.inactive);
            } else {
                bot.setActiveIntakePosition(activeIntakeState.activeReverse);
                bot.setActiveIntakeState(activeIntakeState.activeReverse);
            }
        }
//
//        if (driver.wasJustPressed(GamepadKeys.Button.X)) {
//            bot.intakeSlide.forceThatJawn();
//        }
//
//        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
//            intakeSlideCountSubstract -= 5;
//            bot.intakeSlide.setPosition(intakeSlide.retracted + intakeSlideCountSubstract);
//        }


        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            bot.launchDrone();
        }

        // --------------------------- OPERATOR CODE --------------------------- //

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
            if (bot.getClawState() != null && bot.getClawState().equals(clawState.open)) {
                bot.setClawPosition(clawState.close);
                bot.setClawState(clawState.close);
            } else {
                bot.setClawPosition(clawState.open);
                bot.setClawState(clawState.open);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
            if (bot.getWristState() != null && bot.getWristState().equals(wristState.downIntaking)) {
                bot.setWristPosition(wristState.downOuttaking);
                bot.setWristState(wristState.downOuttaking);
            } else {
                bot.setWristPosition(wristState.downIntaking);
                bot.setWristState(wristState.downIntaking);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {

            bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.HIGHOUT);


        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
            bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.LOWOUT);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.STATION);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {

            bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.MEDIUMOUT);

        }

//        if (operator.wasJustPressed(GamepadKeys.Button.B)) {
//            intakeSlideCountAdd += 5;
//            bot.intakeSlide.setPosition(intakeSlide.retracted + intakeSlideCountAdd);
//        }

        if (operator.wasJustPressed(GamepadKeys.Button.Y)) {
            bot.setWristState(wristState.downOuttaking);
            bot.setWristPosition(wristState.downOuttaking);

            bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.outtaking);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.A)) {
            if ((bot.getvirtualFourBarState() != null && bot.getvirtualFourBarState().equals(virtualFourBarState.init))) {
                bot.setWristState(wristState.downIntaking);
                bot.setWristPosition(wristState.downIntaking);
            }

            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.intaking);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.X)) {
            //   bot.setWristState(wristState.normal);
            //   bot.setWristPosition(wristState.normal);


            if (bot.getvirtualFourBarState() != null && bot.getvirtualFourBarState().equals(virtualFourBarState.outtaking)) {
                bot.setWristState(wristState.downIntaking);
                bot.setWristPosition(wristState.downIntaking);
            }


            bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.init);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            if (bot.getLeftClawState() != null && bot.getLeftClawState().equals(clawState.leftOpen)) {
                bot.setCloseLeftClawPosition();
                bot.setLeftClawState(clawState.leftClose);
            } else {
                bot.setOpenLeftClawPosition();
                bot.setLeftClawState(clawState.leftOpen);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
            if (bot.getRightClawState() != null && bot.getRightClawState().equals(clawState.rightOpen)) {
                bot.setCloseRightClawPosition();
                bot.setRightClawState(clawState.rightClose);
            } else {
                bot.setOpenRightClawPosition();
                bot.setRightClawState(clawState.rightOpen);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.START)) {
            if (bot.getvirtualFourBarState() != null && bot.getvirtualFourBarState().equals(virtualFourBarState.outtakingDown)) {
                bot.setVirtualFourBarPosition(virtualFourBarState.autoDrop, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.autoDrop);
            } else {
                bot.setVirtualFourBarPosition(virtualFourBarState.outtakingDown, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.outtakingDown);
            }

        }
        if(operator.wasJustPressed(GamepadKeys.Button.BACK))
            bot.setVirtualFourBarPosition(virtualFourBarState.outtakingDown, virtualFourBarExtensionState.extending);
        bot.setVirtualFourBarState(virtualFourBarState.outtakingDown);
    }
}