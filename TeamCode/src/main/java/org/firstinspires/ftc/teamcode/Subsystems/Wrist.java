package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.util.robotConstants;


public class Wrist
{
    private ServoEx servo;
    double minAngle = 0, maxAngle= 360;
    public Wrist(HardwareMap hardwareMap)
    {

        servo = new SimpleServo(hardwareMap, "wristServo", minAngle, maxAngle, AngleUnit.DEGREES);
    }
    public void setWristPosition(wristState wristState)
    {
        switch(wristState)
        {
            case downIntaking:
                servo.setPosition(robotConstants.Wrist.downIntaking);
                break;
            case downOuttaking:
                servo.setPosition(robotConstants.Wrist.downOuttaking);
                break;
            default:
                servo.setPosition(robotConstants.Wrist.downIntaking);
        }
    }
    public double getWristPosition()
    {
        return servo.getPosition();
    }

}
