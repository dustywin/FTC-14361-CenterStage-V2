//package org.firstinspires.ftc.teamcode.Subsystems;
//
//import com.arcrobotics.ftclib.hardware.ServoEx;
//import com.arcrobotics.ftclib.hardware.SimpleServo;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Commands.linkageState;
//import org.firstinspires.ftc.teamcode.util.robotConstants;
//
//public class Linkage
//{
//    private ServoEx linkageServo;
//    double minAngle = 0, maxAngle= 360;
//
//    public Linkage(HardwareMap hardwareMap)
//    {
//        linkageServo = new SimpleServo(hardwareMap, "wristServo", minAngle, maxAngle, AngleUnit.DEGREES);
//    }
//
//    public void setLinkagePosition(linkageState linkageState)
//    {
//        switch(linkageState)
//        {
//            case HIGH:
//                linkageServo.setPosition(robotConstants.linkage.highPosition);
//                break;
//            case MEDIUM:
//                linkageServo.setPosition(robotConstants.linkage.mediumPosition);
//                break;
//            case LOW:
//                linkageServo.setPosition(robotConstants.linkage.lowPosition);
//                break;
//            default:
//                linkageServo.setPosition(robotConstants.linkage.lowPosition);
//        }
//    }
//
//    public double getLinkagePosition()
//    {
//        return linkageServo.getPosition();
//    }
//}
