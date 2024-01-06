package org.firstinspires.ftc.teamcode.Subsystems;


import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.autoDropLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.autoDropRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingDownLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingDownRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingRight;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.armState;

public class Arm
{
    private ServoEx leftArm, rightArm;
    AnalogInput rightAnalogInput, leftAnalogInput;
    public armExtensionState armExtension;
    double minAngle = 0, maxAngle= 360;

    public Arm(HardwareMap hardwareMap)
    {
        rightArm = new SimpleServo(hardwareMap, "rightVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
        leftArm = new SimpleServo(hardwareMap, "leftVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
        rightAnalogInput = hardwareMap.get(AnalogInput.class, "rightAnalogInput");
        leftAnalogInput = hardwareMap.get(AnalogInput.class, "leftAnalogInput");

        leftArm.setInverted(true);
    }

    public void setArmPosition(armState armState, armExtensionState armExtensionState)
    {
        switch(armState)
        {
            case outtaking:
                leftArm.setPosition(outtakingLeft);
                rightArm.setPosition(outtakingRight);

                armExtension = armExtensionState.station;
                break;
            case intaking:
                leftArm.setPosition(intakingLeft);
                rightArm.setPosition(intakingRight);

                armExtension = armExtensionState.station;
                break;
            case outtakingDown:
                leftArm.setPosition(outtakingDownLeft);
                rightArm.setPosition(outtakingDownRight);

                armExtension = armExtensionState.station;
                break;
            case autoDrop:
                leftArm.setPosition(autoDropLeft);
                rightArm.setPosition(autoDropRight);

                armExtension = armExtensionState.station;
                break;
            default:
                leftArm.setPosition(initLeft);
                rightArm.setPosition(initRight);
        }
    }

    public armExtensionState getArmExtensionState()
    {
        return armExtension;
    }

    public boolean armExtending(ServoEx arm, double target)
    {
        double marginOfError = Math.abs(arm.getPosition() + 5 - target);

        if(marginOfError > .05)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double getRightArmPosition()
    {
        double position = rightAnalogInput.getVoltage() / 3.3 * 360;
        return position;
    }

    public double getLeftArmPosition()
    {
        double position = leftAnalogInput.getVoltage() / 3.3 * 360;
        return position;
    }
}