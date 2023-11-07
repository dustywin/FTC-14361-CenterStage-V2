package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;

    @Override
    public void init() {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime() + " seconds.");
        telemetry.update();

        bot.setvirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
        bot.setvirtualFourBarState(virtualFourBarState.init);

        bot.setintakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
        bot.setintakeSlideState(intakeSlidesState.STATION);


        bot.setClawPosition(clawState.open);
        bot.setclawState(clawState.open);

        bot.setLeftclawState(clawState.leftOpen);
        bot.setRightclawState(clawState.rightOpen);
    }

    // ---------------------------- LOOPING ---------------------------- //

    @Override
        public void loop() {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
        telemetry.addLine("Intake Slide Position" + bot.getintakeSlidePosition());
        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B" + bot.virtualFourBar.getvirtualFourBarExtensionState());
        telemetry.addLine("Right Claw Position" + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position" + bot.claw.getLeftClawPosition());
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
            bot.driveTrain.setSlowDownMotorPower();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
            bot.driveTrain.setFullPower();
            {

                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                    bot.setintakeSlideState(intakeSlidesState.HIGHIN);
                    bot.setintakeSlidePosition(intakeSlidesState.HIGHIN, extensionState.extending);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                    bot.setintakeSlideState(intakeSlidesState.MEDIUMIN);
                    bot.setintakeSlidePosition(intakeSlidesState.MEDIUMIN, extensionState.extending);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                    bot.setintakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
                    bot.setintakeSlideState(intakeSlidesState.STATION);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.X))
                {
                    bot.intakeSlide.forceThatJawn();
                }

                if (driver.wasJustPressed(GamepadKeys.Button.A)) {
                    if (bot.getactiveIntakeState() != null && (bot.getactiveIntakeState().equals(activeIntakeState.active))) {
                        bot.setActiveIntakePosition(activeIntakeState.inactive);
                        bot.setactiveIntakeState(activeIntakeState.inactive);
                    } else {
                        bot.setActiveIntakePosition(activeIntakeState.active);
                        bot.setactiveIntakeState(activeIntakeState.active);
                    }
                }
                if (driver.wasJustPressed(GamepadKeys.Button.Y)) {
                    if (bot.getactiveIntakeState() != null && bot.getactiveIntakeState().equals(activeIntakeState.activeReverse)) {
                        bot.setActiveIntakePosition(activeIntakeState.inactive);
                        bot.setactiveIntakeState(activeIntakeState.inactive);
                    } else {
                        bot.setActiveIntakePosition(activeIntakeState.activeReverse);
                        bot.setactiveIntakeState(activeIntakeState.activeReverse);
                    }
                }

                // --------------------------- OPERATOR CODE --------------------------- //

                if (operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
                    if (bot.getclawState() != null && bot.getclawState().equals(clawState.open)) {
                        bot.setClawPosition(clawState.close);
                        bot.setclawState(clawState.close);
                    } else {
                        bot.setClawPosition(clawState.open);
                        bot.setclawState(clawState.open);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
                    if (bot.getWristState() != null && bot.getWristState().equals(wristState.normal)) {
                        bot.setWristPosition(wristState.sideways);
                        bot.setWristState(wristState.sideways);
                    } else {
                        bot.setWristPosition(wristState.normal);
                        bot.setWristState(wristState.normal);
                    }
                }
                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
                    if (bot.getWristState() != null && bot.getWristState().equals(wristState.normal)) {
                        bot.setWristPosition(wristState.sideways);
                        bot.setWristState(wristState.sideways);
                    } else {
                        bot.setWristPosition(wristState.normal);
                        bot.setWristState(wristState.normal);
                    }
                }
                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extended);
                    bot.setOuttakeSlideState(outtakeSlidesState.HIGHOUT);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
                    bot.setOuttakeSlideState(outtakeSlidesState.MEDIUMOUT);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                    bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                    bot.setOuttakeSlideState(outtakeSlidesState.STATION);
                }

     /*           if (operator.wasJustPressed(GamepadKeys.Button.A)) {
                    if (bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.outtaking)) {
                        bot.setvirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                        bot.setvirtualFourBarState(virtualFourBarState.init);

                        if (bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending)) {
                            bot.setWristState(wristState.normal);
                            bot.setWristPosition(wristState.normal);
                        }

                        bot.setWristState(wristState.sideways);
                        bot.setWristPosition(wristState.sideways);
                    } else if (bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.intaking)) {
                        bot.setvirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                        bot.setvirtualFourBarState(virtualFourBarState.outtaking);

                        if (bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending)) {
                            bot.setWristState(wristState.normal);
                            bot.setWristPosition(wristState.normal);
                        }

                        bot.setWristState(wristState.sideways);
                        bot.setWristPosition(wristState.sideways);
                    } else if (bot.virtualFourBarState != null && bot.getvirtualFourBarState().equals(virtualFourBarState.init)) {
                        bot.setWristState(wristState.normal);
                        bot.setWristPosition(wristState.normal);

                        bot.setvirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                        bot.setvirtualFourBarState(virtualFourBarState.intaking);
                    } else {
                        bot.setvirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                        bot.setvirtualFourBarExtensionState(virtualFourBarExtensionState.extending);
                        bot.setvirtualFourBarState(virtualFourBarState.init);

                        if (bot.getvirtualFourBarExtensionState() != null && bot.getvirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending)) {
                            bot.setWristState(wristState.normal);
                            bot.setWristPosition(wristState.normal);
                        }

                        bot.setWristState(wristState.sideways);
                        bot.setWristPosition(wristState.sideways);
                    }
                }
*/
                if (operator.wasJustPressed(GamepadKeys.Button.X)) {
                    bot.setvirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                    bot.setvirtualFourBarState(virtualFourBarState.init);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.Y)) {
                    bot.setWristState(wristState.sideways);
                    bot.setWristPosition(wristState.sideways);


                    bot.setvirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                    bot.setvirtualFourBarState(virtualFourBarState.outtaking);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.A)) {
                    bot.setWristState(wristState.normal);
                    bot.setWristPosition(wristState.normal);


                    bot.setvirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                    bot.setvirtualFourBarState(virtualFourBarState.intaking);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                    if (bot.getLeftclawState() != null && bot.getLeftclawState().equals(clawState.leftOpen)) {
                        bot.setOpenLeftClawPosition();
                        bot.setLeftclawState(clawState.leftClose);
                    } else {
                        bot.setOpenLeftClawPosition();
                        bot.setLeftclawState(clawState.leftClose);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
                    if (bot.getRightclawState() != null && bot.getRightclawState().equals(clawState.rightOpen)) {
                        bot.setOpenRightClawPosition();
                        bot.setRightclawState(clawState.rightClose);
                    } else {
                        bot.setOpenRightClawPosition();
                        bot.setRightclawState(clawState.rightOpen);
                    }
                }
            }
        }
    }
}

