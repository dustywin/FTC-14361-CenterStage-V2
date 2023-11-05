package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;

public class Claw {
    private ServoEx leftHand, rightHand;
    //private Servo leftHand, rightHand;

    public Claw(HardwareMap hardwareMap)
    {
        leftHand = new SimpleServo(hardwareMap, "leftHand",0, 360, AngleUnit.DEGREES);
        rightHand = new SimpleServo(hardwareMap, "rightHand",0, 360, AngleUnit.DEGREES);
        leftHand.setInverted(true);
        rightHand.setInverted(true);
        //leftHand = hardwareMap.servo.get("leftHand");
      //  rightHand = hardwareMap.servo.get("rightHand");
    }

    public void leftOpen()
    {
        leftHand.setPosition(RobotConstants.Claw.leftOpen);
    }

    public void rightOpen()
    {
        rightHand.setPosition(RobotConstants.Claw.rightOpen);
    }

    public void open()
    {
        leftHand.setPosition(RobotConstants.Claw.leftOpen);
        rightHand.setPosition(RobotConstants.Claw.rightOpen);
    }

    public void leftClose()
    {
        leftHand.setPosition(RobotConstants.Claw.leftClose);
    }

    public void rightClose()
    {
        rightHand.setPosition(RobotConstants.Claw.rightClose);
    }

    public void close()
    {
        leftHand.setPosition(RobotConstants.Claw.leftClose);
        rightHand.setPosition(RobotConstants.Claw.rightClose);
    }

    public double getLeftHandPosition()
    {
        return leftHand.getPosition();
    }

    public double getRightHandPosition()
    {
        return rightHand.getPosition();
    }


    public void setClawPosition(clawState clawState){
        switch(clawState){
            case open:
                open();
                break;
            case close:
                close();
                break;
            case leftClose:
                leftClose();
                break;
            case rightClose:
                rightClose();
                break;
            case leftOpen:
                leftOpen();
                break;
            case rightOpen:
                rightOpen();
                break;
            default:
                close();
        }

    }
    public double getRightClawPosition(){
        return rightHand.getPosition();
    }
    public double getLeftClawPosition(){
        return leftHand.getPosition();
    }
}