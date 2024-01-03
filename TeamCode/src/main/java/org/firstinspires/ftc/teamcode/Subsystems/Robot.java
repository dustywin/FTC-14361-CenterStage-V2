package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.slowDownState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;



public class Robot {
    //public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum driveTrain;
    public Wrist wrist;
    public Claw claw;
    public VirtualFourBar virtualFourBar;
    public Drone drone;
    public intakeSlidesState intakeSlidesState;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public clawState clawState, leftclawState, rightclawState;
    public virtualFourBarState virtualFourBarState;
    public virtualFourBarExtensionState virtualFourBarExtensionState;
    public extensionState extensionState;
    public ActiveIntake activeIntake;
    public activeIntakeState activeIntakeState;
    public slowDownState slowDownState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new VirtualFourBar(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        //intakeSlide = new IntakeSlide(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
        drone = new Drone(hardwareMap);
    }

//    public void setIntakeSlidePosition(intakeSlidesState intakeSlidesState, extensionState extensionState)
//    {
//        intakeSlide.setPosition(extensionState,intakeSlidesState);
//    }
//    public void setIntakeSlidePosition(int pos)
//    {
//        intakeSlide.setPosition(pos);
//    }
//    public intakeSlidesState getIntakeSlideState()
//    {
//        return intakeSlidesState;
//    }
//
//    public void setIntakeSlideState(intakeSlidesState intakeSlidesState)
//    {
//        this.intakeSlidesState = intakeSlidesState;
//    }
//
//    public double getIntakeSlidePosition()
//    {
//        return intakeSlide.getIntakeSlidePosition();
//    }
//
    public void setOuttakeSlidePosition(outtakeSlidesState outtakeSlidesState, extensionState extensionState)
    {
        outtakeSlide.setOuttakeSlidePosition(extensionState,outtakeSlidesState);
    }

    public outtakeSlidesState getOuttakeState()
    {
        return outtakeSlidesState;
    }

    public void setOuttakeSlideState(outtakeSlidesState outtakeSlidesState)
    {
        this.outtakeSlidesState = outtakeSlidesState;
    }

    public void setExtensionState(extensionState extensionState)

    {
        this.extensionState = extensionState;
    }

    public extensionState getExtensionState()

    {
        return extensionState;
    }

    public double getOuttakeLeftSlidePosition()
    {
        return outtakeSlide.getLeftOuttakeSlideMotorPosition();
    }

    public double getOuttakeRightSlidePosition()
    {
        return outtakeSlide.getRightOuttakeSlideMotorPosition();
    }

    public void setWristPosition(wristState wristState)
    {
        wrist.setWristPosition(wristState);
    }

    public void setWristState(wristState wristState)
    {
        this.wristState = wristState;
    }

    public wristState getWristState()
    {
        return wristState;
    }

    public double getWristPosition()
    {
        return wrist.getWristPosition();
    }

    public void setVirtualFourBarPosition(virtualFourBarState virtualFourBarState, virtualFourBarExtensionState virtualFourBarExtensionState)
    {
        virtualFourBar.setVirtualFourBarPosition(virtualFourBarState, virtualFourBarExtensionState);
    }

    public void setVirtualFourBarState(virtualFourBarState virtualFourBarState)
    {
        this.virtualFourBarState = virtualFourBarState;
    }

    public virtualFourBarState getvirtualFourBarState()
    {
        return virtualFourBarState;
    }

    public virtualFourBarExtensionState getvirtualFourBarExtensionState()
    {
        return virtualFourBarExtensionState;
    }

    public void setVirtualFourBarExtensionState(virtualFourBarExtensionState virtualFourBarExtensionState)
    {
        this.virtualFourBarExtensionState = virtualFourBarExtensionState;
    }

    public void setClawPosition(clawState clawState)
    {
        claw.setClawPosition(clawState);
    }

    public void setOpenLeftClawPosition()
    {
        claw.leftOpen();
    }

    public void setOpenRightClawPosition()
    {
        claw.rightOpen();
    }

    public void setCloseLeftClawPosition()
    {
        claw.leftClose();
    }

    public void setCloseRightClawPosition()
    {
        claw.rightClose();
    }

    public void setClawState(clawState clawState)
    {
        this.clawState = clawState;
    }

    public void setLeftClawState(clawState leftclawState)
    {
        this.leftclawState = leftclawState;
    }

    public void setRightClawState(clawState rightclawState)
    {
        this.rightclawState = rightclawState;
    }

    public clawState getClawState()
    {
        return clawState;
    }

    public clawState getLeftClawState()
    {
        return leftclawState;
    }

    public clawState getRightClawState()
    {
        return rightclawState;
    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState)
    {
        activeIntake.setActiveIntakePosition(activeIntakeState);
    }

    public activeIntakeState getActiveIntakeState()
    {
        return activeIntakeState;
    }

    public void setActiveIntakeState(activeIntakeState activeIntakeState)
    {
        this.activeIntakeState = activeIntakeState;
    }

    public slowDownState getSlowDownState()
    {
        return slowDownState;
    }

    public void setSlowDownState(slowDownState slowDownState)
    {
        this.slowDownState = slowDownState;
    }

    public void setDrone()
    {
        drone.resetDrone();
    }

    public void launchDrone()
    {
        drone.launch();
    }

//    public void setInBrake()
//    {
//        intakeSlide.setBrakeMode();
//    }
}



