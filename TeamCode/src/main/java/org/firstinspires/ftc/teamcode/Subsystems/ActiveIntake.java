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
                activeIntake.setVelocity(5);
                break;
            case activeReverse:
                activeIntake.setPower(robotConstants.activeIntake.reverseActive);
                activeIntake.setVelocity(-5);
                break;
            case inactive:
                activeIntake.setVelocity(0);
                activeIntake.setPower(0);

        }
    }
}
