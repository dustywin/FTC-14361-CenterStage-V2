package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;



public class Robot {
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    public Wrist wrist;
    public Claw claw;
    public VirtualFourBar virtualFourBar;

    public intakeSlidesState intakeSlidesState;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public clawState clawState, leftClawState, rightClawState;
    public virtualFourBarState virtualFourBarState;
    public virtualFourBarExtensionState virtualFourBarExtensionState;
    public extensionState extensionState;
    public ActiveIntake activeIntake;
    public activeIntakeState activeIntakeState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new VirtualFourBar(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
    }

    public void setIntakeSlidePosition(intakeSlidesState intakeSlidesState, extensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeSlidesState);
    }
    public intakeSlidesState getintakeSlideState()
    {
        return intakeSlidesState;
    }

    public void setintakeSlideState(intakeSlidesState intakeSlidesState){
        this.intakeSlidesState = intakeSlidesState;
    }
    public double getIntakeSlidePosition(){

        return intakeSlide.getIntakeSlidePosition();
    }

    public void setOuttakeSlidePosition(outtakeSlidesState outtakeSlidesState, extensionState extensionState)
    {
        outtakeSlide.setOuttakeSlidePosition(extensionState,outtakeSlidesState);
    }

    public outtakeSlidesState getOuttakeState()
    {
        return outtakeSlidesState;
    }

    public void setOuttakeSlidesState(outtakeSlidesState outtakeSlidesState)
    {
        this.outtakeSlidesState = outtakeSlidesState;
    }

    public void setextensionState(extensionState extensionState)
    {
        this.extensionState = extensionState;
    }

    public extensionState getextensionState()
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

    public void setwristState(wristState wristState)
    {
        this.wristState = wristState;
    }

    public wristState getwristState()
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

    public virtualFourBarState getVirtualFourBarState()
    {
        return virtualFourBarState;
    }

    public virtualFourBarExtensionState getVirtualFourBarextensionState()
    {
        return virtualFourBarExtensionState;
    }

    public void setVirtualFourBarextensionState(virtualFourBarExtensionState virtualFourBarExtensionState)
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

    public void setLeftClawState(clawState leftClawState)
    {
        this.leftClawState = leftClawState;
    }

    public void setRightClawState(clawState rightClawState)
    {
        this.rightClawState = rightClawState;
    }

    public clawState getClawState()
    {
        return clawState;
    }

    public clawState getLeftClawState()
    {
        return leftClawState;
    }

    public clawState getRightClawState()
    {
        return rightClawState;
    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState)
    {
        activeIntake.setActiveIntakePosition(activeIntakeState);
    }

    public activeIntakeState getactiveIntakeState()
    {
        return activeIntakeState;
    }

    public void setactiveIntakeState(activeIntakeState activeIntakeState)
    {
        this.activeIntakeState = activeIntakeState;
    }



}



