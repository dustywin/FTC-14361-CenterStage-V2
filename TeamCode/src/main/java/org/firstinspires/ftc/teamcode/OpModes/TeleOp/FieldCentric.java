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

        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
        bot.setIntakeSlideState(intakeSlidesState.STATION);

        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);

        bot.setLeftClawState(clawState.leftOpen);
        bot.setRightClawState(clawState.rightOpen);

        bot.setDrone();

        bot.setSlowDownState(slowDownState.FULL);
    }

    // ---------------------------- LOOPING ---------------------------- //

    @Override
    public void loop()
    {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
        telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition());
        telemetry.addLine("Intake Slide Count " + intakeSlideCountAdd );
        telemetry.addLine("Intake Slide Subtract Count " + intakeSlideCountSubstract );

        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B: init / " + bot.virtualFourBar.getvirtualFourBarExtensionState());
        telemetry.addLine("Right Claw Position: " + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position: " + bot.claw.getLeftClawPosition());
        telemetry.addLine("Servo Power" + bot.drone.getInfo());


        telemetry.addLine("Intake Slide Encoder Tick Count " + intakeSlideCountSubstract);
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

        if(driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
        {
                if(bot.getvirtualFourBarState().equals(virtualFourBarState.intaking)){
                    bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
                    bot.setIntakeSlideState(intakeSlidesState.STATION);
                }
                else{
            bot.setIntakeSlideState(intakeSlidesState.HIGHIN);
            bot.setIntakeSlidePosition(intakeSlidesState.HIGHIN, extensionState.extending);
            }
        }

        if(driver.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON))
        {
            if(bot.getvirtualFourBarState().equals(virtualFourBarState.intaking)){
                bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
                bot.setIntakeSlideState(intakeSlidesState.STATION);
            }
            else {
                bot.setIntakeSlideState(intakeSlidesState.MEDIUMIN);
                bot.setIntakeSlidePosition(intakeSlidesState.MEDIUMIN, extensionState.extending);
            }
        }

        if(driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
        {
            bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
            bot.setIntakeSlideState(intakeSlidesState.STATION);
        }

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

        if (driver.wasJustPressed(GamepadKeys.Button.X))
        {
            bot.intakeSlide.forceThatJawn();
        }

        if(driver.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON))
        {
            intakeSlideCountSubstract -= 5;
            bot.intakeSlide.setPosition(intakeSlide.retracted + intakeSlideCountSubstract);
        }


        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.launchDrone();
        }

        // --------------------------- OPERATOR CODE --------------------------- //

        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON))
        {
            if(bot.getClawState() != null && bot.getClawState().equals(clawState.open))
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

        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON))
        {
            if(bot.getWristState() != null && bot.getWristState().equals(wristState.normal))
            {
                bot.setWristPosition(wristState.sideways);
                bot.setWristState(wristState.sideways);
            }
            else
            {
                bot.setWristPosition(wristState.normal);
                bot.setWristState(wristState.normal);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_UP))
        {

                bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extending);
                bot.setOuttakeSlideState(outtakeSlidesState.HIGHOUT);


        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.LOWOUT);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
            bot.setOuttakeSlideState(outtakeSlidesState.STATION);
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT))
        {

                bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
                bot.setOuttakeSlideState(outtakeSlidesState.MEDIUMOUT);

        }

        if(operator.wasJustPressed(GamepadKeys.Button.B)) {

                intakeSlideCountAdd += 5;
                bot.intakeSlide.setPosition(intakeSlide.retracted + intakeSlideCountAdd);

        }
//        if(operator.getRightY() > .2 || operator.getRightY() < -.2){
//            bot.outtakeSlide.setPosition((int)(Math.abs(operator.getRightY() * 1600)));
//            bot.setOuttakeSlideState(outtakeSlidesState.MEDIUMOUT);
//        }

          /*  if (bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.outtaking))
            {
                bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.init);

                if (bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                {
                    bot.setWristState(wristState.normal);
                    bot.setWristPosition(wristState.normal);
                }

                bot.setWristState(wristState.sideways);
                bot.setWristPosition(wristState.sideways);

            }
            else if(bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.intaking))
            {
                bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.init);

                if(bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                {
                    bot.setWristState(wristState.normal);
                    bot.setWristPosition(wristState.normal);
                }

                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);

            }
            else if(bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.init))
            {
                if(bot.getvirtualFourBarState() != null && (bot.getClawState().equals(clawState.close) || bot.getClawState().equals(clawState.rightClose) || bot.getClawState().equals(clawState.leftClose)))
                {
                    bot.setWristState(wristState.sideways);
                    bot.setWristPosition(wristState.sideways);

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);
                }
                else
                {
                    bot.setWristState(wristState.normal);
                    bot.setWristPosition(wristState.normal);

                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.intaking);
                }
            }
            else
            {
                bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarExtensionState(virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.init);

                if (bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                {
                    bot.setWristState(wristState.normal);
                    bot.setWristPosition(wristState.normal);
                }

                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);
            }
        }*/

        if(operator.wasJustPressed(GamepadKeys.Button.Y))
        {
            bot.setWristState(wristState.sideways);
            bot.setWristPosition(wristState.sideways);

            bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.outtaking);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.A))
        {
            if((bot.getvirtualFourBarState() != null && bot.getvirtualFourBarState().equals(virtualFourBarState.init))) {
                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);
            }

            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.intaking);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.X))
        {
         //   bot.setWristState(wristState.normal);
         //   bot.setWristPosition(wristState.normal);

            if (bot.getvirtualFourBarState() != null && bot.getvirtualFourBarState().equals(virtualFourBarState.outtaking))
            {
                bot.setWristState(wristState.sideways);
                bot.setWristPosition(wristState.sideways);
            }

            bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.init);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
        {
            if(bot.getLeftClawState() != null && bot.getLeftClawState().equals(clawState.leftOpen))
            {
                bot.setCloseLeftClawPosition();
                bot.setLeftClawState(clawState.leftClose);
            }
            else
            {
                bot.setOpenLeftClawPosition();
                bot.setLeftClawState(clawState.leftOpen);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
        {
            if(bot.getRightClawState() != null && bot.getRightClawState().equals(clawState.rightOpen))
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
    }
}