package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.util.robotConstants;

public class OuttakeSlide
{
    DcMotorEx rightouttakeSlide, leftouttakeSlide;
    private final int countsPerRev = 384;
    double power = .9;

    public OuttakeSlide(HardwareMap hardwareMap) {
        rightouttakeSlide = hardwareMap.get(DcMotorEx.class, "rightOuttakeSlide");
        leftouttakeSlide = hardwareMap.get(DcMotorEx.class, "leftOuttakeSlide");

        rightouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightouttakeSlide.setTargetPositionTolerance(5);
        leftouttakeSlide.setTargetPositionTolerance(5);
    }

    public void setOuttakeSlidePosition(extensionState extensionState, outtakeSlidesState outtakeSlidesState)
    {
        switch(extensionState)
        {
            case retracted:
                break;
            case extending:
            {
                switch (outtakeSlidesState)
                {
                    case MOSTHIGHOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MOSTHIGHLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MOSTHIGHRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case HIGHOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.HIGHLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.HIGHRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case MEDIUMOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MEDIUMLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MEDIUMRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case LOWOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.LOWLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.LOWRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case AUTOLOWOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.AUTOLOWLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.AUTOLOWRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case STATION:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.GROUNDLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.GROUNDRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                }
            }

            case extended:
                break;
        }
    }
    public double getLeftOuttakeSlideMotorPosition()
    {
        return leftouttakeSlide.getCurrentPosition();
    }

    public double getRightOuttakeSlideMotorPosition()
    {
        return rightouttakeSlide.getCurrentPosition();
    }

    public void setPosition(int pos) {
        leftouttakeSlide.setTargetPosition(-pos);
        rightouttakeSlide.setTargetPosition(pos);

        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftouttakeSlide.setPower(power);
        rightouttakeSlide.setPower(power);
    }

    public void resetZero()
    {
        leftouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}