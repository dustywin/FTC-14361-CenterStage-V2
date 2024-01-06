package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.slowDownState;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;
//  private int intakeSlideCountAdd = 0;
//  private int intakeSlideCountSubstract = 0;

    @Override
    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime() + " seconds.");
        telemetry.update();

        bot.setArmPosition(armState.init, armExtensionState.extending);
        bot.setArmState(armState.init);

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
//      telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition());
//      telemetry.addLine("Intake Slide Count " + intakeSlideCountAdd);
//      telemetry.addLine("Intake Slide Subtract Count " + intakeSlideCountSubstract);

        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B: init / " + bot.arm.getArmExtensionState());
        telemetry.addLine("Right Claw Position: " + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position: " + bot.claw.getLeftClawPosition());
        telemetry.addLine("Right Arm Position: " + bot.arm.getRightArmPosition() + " ticks.");
        telemetry.addLine("Right Arm Decimal Position: " + (1 - bot.arm.getRightArmPosition() / 360) + " decimal.");
        telemetry.addLine("Left Arm Position: " + bot.arm.getLeftArmPosition() + " ticks.");
        telemetry.addLine("Left Arm Decimal Position: " + (1 - bot.arm.getLeftArmPosition() / 360) + " decimal.");
//      telemetry.addLine("Intake Slide Encoder Tick Count " + intakeSlideCountSubstract);
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        // ---------------------------- DRIVER CODE ---------------------------- //

        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1)
        {
            bot.setSlowDownState(slowDownState.FULL);
            bot.driveTrain.setFullPower();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1)
        {
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
        if (driver.wasJustPressed(GamepadKeys.Button.A))
        {
            if (bot.getActiveIntakeState() != null && (bot.getActiveIntakeState().equals(activeIntakeState.active)))
            {
                bot.setActiveIntakePosition(activeIntakeState.inactive);
                bot.setActiveIntakeState(activeIntakeState.inactive);
            }
            else
            {
                bot.setActiveIntakePosition(activeIntakeState.active);
                bot.setActiveIntakeState(activeIntakeState.active);
            }
        }

        if (driver.wasJustPressed(GamepadKeys.Button.Y))
        {
            if (bot.getActiveIntakeState() != null && bot.getActiveIntakeState().equals(activeIntakeState.activeReverse))
            {
                bot.setActiveIntakePosition(activeIntakeState.inactive);
                bot.setActiveIntakeState(activeIntakeState.inactive);
            }
            else
            {
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


        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.launchDrone();
        }

        // --------------------------- OPERATOR CODE --------------------------- //

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON))
        {
            if (bot.getClawState() != null && bot.getClawState().equals(clawState.open))
            {
                bot.setClawPosition(clawState.close);
                bot.setClawState(clawState.close);
            }
            else
            {
                bot.setClawPosition(clawState.open);
                bot.setClawState(clawState.open);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON))
        {
            if (bot.getWristState() != null && bot.getWristState().equals(wristState.intaking))
            {
                bot.setWristPosition(wristState.outtaking);
                bot.setWristState(wristState.outtaking);
            }
            else
            {
                bot.setWristPosition(wristState.intaking);
                bot.setWristState(wristState.intaking);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.HIGHOUT);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.LOWOUT);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.STATION);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.MEDIUMOUT);
        }

//        if (operator.wasJustPressed(GamepadKeys.Button.B)) {
//            intakeSlideCountAdd += 5;
//            bot.intakeSlide.setPosition(intakeSlide.retracted + intakeSlideCountAdd);
//        }

        if (operator.wasJustPressed(GamepadKeys.Button.Y))
        {
            bot.setWristState(wristState.outtaking);
            bot.setWristPosition(wristState.outtaking);

            bot.setArmPosition(armState.outtaking, armExtensionState.extending);
            bot.setArmState(armState.outtaking);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.A))
        {
            if ((bot.getArmState() != null && bot.getArmState().equals(armState.init)))
            {
                bot.setWristState(wristState.intaking);
                bot.setWristPosition(wristState.intaking);
            }

            bot.setWristState(wristState.intaking);
            bot.setWristPosition(wristState.intaking);

            bot.setArmPosition(armState.intaking, armExtensionState.extending);
            bot.setArmState(armState.intaking);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.X))
        {
            //   bot.setWristState(wristState.normal);
            //   bot.setWristPosition(wristState.normal);

            if (bot.getArmState() != null && bot.getArmState().equals(armState.outtaking))
            {
                bot.setWristState(wristState.intaking);
                bot.setWristPosition(wristState.intaking);
            }

            bot.setWristState(wristState.init);
            bot.setWristPosition(wristState.init);

            bot.setArmPosition(armState.init, armExtensionState.extending);
            bot.setArmState(armState.init);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
        {
            if (bot.getLeftClawState() != null && bot.getLeftClawState().equals(clawState.leftOpen))
            {
                bot.setCloseLeftClawPosition();
                bot.setLeftClawState(clawState.leftClose);
            } else {
                bot.setOpenLeftClawPosition();
                bot.setLeftClawState(clawState.leftOpen);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
        {
            if (bot.getRightClawState() != null && bot.getRightClawState().equals(clawState.rightOpen))
            {
                bot.setCloseRightClawPosition();
                bot.setRightClawState(clawState.rightClose);
            }
            else
            {
                bot.setOpenRightClawPosition();
                bot.setRightClawState(clawState.rightOpen);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.START))
        {
            if (bot.getArmState() != null && bot.getArmState().equals(armState.outtakingDown)) {
                bot.setArmPosition(armState.autoDrop, armExtensionState.extending);
                bot.setArmState(armState.autoDrop);
            }
            else
            {
                bot.setArmPosition(armState.outtakingDown, armExtensionState.extending);
                bot.setArmState(armState.outtakingDown);
            }

        }
        if(operator.wasJustPressed(GamepadKeys.Button.BACK))
        {
            bot.setArmPosition(armState.outtakingDown, armExtensionState.extending);
            bot.setArmState(armState.outtakingDown);
        }
    }
}