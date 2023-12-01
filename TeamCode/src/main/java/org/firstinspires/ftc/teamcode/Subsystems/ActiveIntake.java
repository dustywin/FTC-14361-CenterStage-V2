package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.util.robotConstants;

public class ActiveIntake
{
   DcMotorEx activeIntake;
    public ActiveIntake(HardwareMap hardwareMap)
    {
        activeIntake = hardwareMap.get(DcMotorEx.class, "activeIntake");
    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState)
    {
        switch (activeIntakeState)
        {
            case active:
                activeIntake.setPower(robotConstants.activeIntake.active);

                break;
            case activeReverse:
                activeIntake.setPower(robotConstants.activeIntake.reverseActive);

                break;
            case inactive:

                activeIntake.setPower(0);

        }
    }
    public void setActiveIntakePower(double power){
        activeIntake.setPower(power);
    }
}
