package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

public class intakeSlide {
    DcMotorEx intakeSlideMotor;
    private final int countsPerRev = 384;
    double power = .7;

    public intakeSlide(HardwareMap hardwareMap) {
        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeSlideMotor.setTargetPositionTolerance(5);

        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPosition(extensionState extensionState, intakeSlidesState inExtendState)
    {
        switch(extensionState){
            case retracted:
                break;
            case extending:
                switch(inExtendState){
                    case HIGHIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.highExtension);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                    case MEDIUMIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.mediumExtension);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                    case STATION:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.retracted);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                }
            case extended:
                break;
        }
    }

    public double getIntakeSlidePosition(){
        double position = intakeSlideMotor.getCurrentPosition();
        return position;
    }

    public void setMaxPower()
    {
        power = 1;
    }

    public void setNormalPower()
    {
        power = .7;
    }

    public void forceThatJawn()
    {
        intakeSlideMotor.setTargetPosition(100);
        setMaxPower();
        intakeSlideMotor.setTargetPosition(-4);
        setNormalPower();
    }
}