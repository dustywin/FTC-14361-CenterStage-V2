package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.util.robotConstants;


public class Wrist
{
    private ServoEx wristServo;
    double minAngle = 0, maxAngle= 360;
    public Wrist(HardwareMap hardwareMap)
    {
        wristServo = new SimpleServo(hardwareMap, "wristServo", minAngle, maxAngle, AngleUnit.DEGREES);
    }

    public void setWristPosition(wristState wristState)
    {
        switch(wristState)
        {
            case intaking:
                wristServo.setPosition(robotConstants.Wrist.intaking);
                break;
            case init:
                wristServo.setPosition(robotConstants.Wrist.init);
                break;
            case outtaking:
                wristServo.setPosition(robotConstants.Wrist.outtaking);
                break;
            default:
                wristServo.setPosition(robotConstants.Wrist.intaking);
        }
    }
    public double getWristPosition()
    {
        return wristServo.getPosition();
    }

}