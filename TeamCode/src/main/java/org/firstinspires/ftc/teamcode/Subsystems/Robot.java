package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.armExtensionState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.linkageState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.slowDownState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.util.robotConstants;

public class Robot {
    //public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum driveTrain;
    public Wrist wrist;
    public Claw claw;
    public Arm arm;
    public Drone drone;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public clawState clawState, leftclawState, rightclawState;
    public armState armState;
    public armExtensionState armExtensionState;
    public extensionState extensionState;
    public ActiveIntake activeIntake;
    public activeIntakeState activeIntakeState;
    public slowDownState slowDownState;
    public Linkage linkage;
    public linkageState linkageState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        arm = new Arm(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        //intakeSlide = new IntakeSlide(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
        drone = new Drone(hardwareMap);
    }

    // ---------------------------- IntakeSlide ---------------------------- //

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

//    public void setExtensionState(extensionState extensionState)
//    {
//        this.extensionState = extensionState;
//    }
//
//    public extensionState getExtensionState()
//
//    {
//        return extensionState;
//    }

//    public void setInBrake()
//    {
//        intakeSlide.setBrakeMode();
//    }

    // ---------------------------- OuttakeSlide ---------------------------- //

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

    public double getOuttakeLeftSlidePosition()
    {
        return outtakeSlide.getLeftOuttakeSlideMotorPosition();
    }

    public double getOuttakeRightSlidePosition()
    {
        return outtakeSlide.getRightOuttakeSlideMotorPosition();
    }

    // ---------------------------- Wrist ---------------------------- //

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

    // ---------------------------- Arm ---------------------------- //

    public void setArmPosition(armState armState, armExtensionState armExtensionState)
    {
        arm.setArmPosition(armState, armExtensionState);
    }

    public void setArmState(armState armState)
    {
        this.armState = armState;
    }

    public armState getArmState()
    {
        return armState;
    }

    public armExtensionState getArmExtensionState()
    {
        return armExtensionState;
    }

    public void setArmExtensionState(armExtensionState armExtensionState)
    {
        this.armExtensionState = armExtensionState;
    }

    // ---------------------------- Claw ---------------------------- //

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

    // ---------------------------- ActiveIntake ---------------------------- //

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

    // ---------------------------- SlowDown ---------------------------- //

    public slowDownState getSlowDownState()
    {
        return slowDownState;
    }

    public void setSlowDownState(slowDownState slowDownState)
    {
        this.slowDownState = slowDownState;
    }

    // ---------------------------- Drone ---------------------------- //

    public void setDrone()
    {
        drone.resetDrone();
    }

    public void launchDrone()
    {
        drone.launch();
    }

    // ---------------------------- Linkage ---------------------------- //

    public void setHighLinkagePosition(linkageState linkageState)
    {
        linkage.setLinkagePosition(linkageState);
    }

    public void setLinkageState(linkageState linkageState)
    {
        this.linkageState = linkageState;
    }

    public linkageState getLinkageState()
    {
        return this.linkageState;
    }

    public void getLinkagePosition()
    {
        linkage.getLinkagePosition();
    }
}



