package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingRight;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.robotConstants;

@Autonomous(name="HardCode RightBlue park")

public class RightBlueHardcodePark extends LinearOpMode{


        /*
        *** NOTE ***
        THIS CODE IS MADE TO PARK LEFT WHEN WE ARE
        LEFT BLUE ALLIANCE, AND THATS IT
        (this is hardcoded and created without Roadrunner)
         */
        private DcMotorEx frontRight, frontLeft, backRight, backLeft,intakeSlideMotor;;

        double mult = 1.0;
        double batteryVoltage;
        String voltageCategory;
    private ServoEx leftVirtualFourBar, rightVirtualFourBar, leftHand, rightHand;
    private Servo wristServo;
    double minAngle = 0, maxAngle= 360;

        @Override
        public void runOpMode() throws InterruptedException {

            frontLeft = hardwareMap.get(DcMotorEx.class, "leftFront");
            backLeft = hardwareMap.get(DcMotorEx.class, "leftRear");
            backRight = hardwareMap.get(DcMotorEx.class, "rightRear");
            frontRight = hardwareMap.get(DcMotorEx.class, "rightFront");

            intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

            intakeSlideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            rightVirtualFourBar = new SimpleServo(hardwareMap, "rightVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
            leftVirtualFourBar = new SimpleServo(hardwareMap, "leftVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);

            wristServo = hardwareMap.get(Servo.class, "wristServo");

            leftHand = new SimpleServo(hardwareMap, "leftHand",0, 360, AngleUnit.DEGREES);
            rightHand = new SimpleServo(hardwareMap, "rightHand",0, 360, AngleUnit.DEGREES);
            leftHand.setInverted(true);
            rightHand.setInverted(true);

            frontRight.setDirection(DcMotorEx.Direction.REVERSE);
            backRight.setDirection(DcMotorEx.Direction.REVERSE);

            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        // this will initialize all of our encoders- not useful
            // for this class
//        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
//
//        for (LynxModule hub : allHubs) {
//            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
//        }


            // i dunno if this is even a real thing i can do
            while(!opModeIsActive()) {
                // lil bit of experimental code (if this works i am suoper cool)
                voltageTelem();
            }


            waitForStart();

            while(opModeIsActive()){

                retractSlide(1);

                setWristNormal(1);
                setV4bIntaking(1);



                closeClaw(2);

                setV4BInit(1);
                setWristSideways(1);

                setV4BOuttaking(1);
                setWristNormal(1);

              openClaw(1);

                break;

            }

        }

        // drive forward
        private void forward(double mult, double sec){
            frontRight.setPower(mult);
            frontLeft.setPower(mult); //mult changes the speed the motors go. Slower is more consistent
            backRight.setPower(mult);
            backLeft.setPower(mult);
            runFor(sec); //runs for this amount of time
        /* Mecanum forward (+ means forward, - means backwards)
         + +
         + +
         + +
        */
        }

        private void backwards(double mult, double sec) {
            frontRight.setPower(mult * -1);
            frontLeft.setPower(mult * -1);
            backRight.setPower(mult * -1);
            backLeft.setPower(mult * -1);
            runFor(sec);
        }
        // drive left
        private void strafeLeft(double mult, double sec) {
            frontRight.setPower(mult * 1);
            frontLeft.setPower(mult * -1);
            backRight.setPower(mult * -1);
            backLeft.setPower(mult * 1);
            runFor(sec);
        }

        // drive back

        // drive right
        private void strafeRight(double mult, double sec) {
            frontRight.setPower(mult * -1);
            frontLeft.setPower(mult * 1);
            backRight.setPower(mult * 1);
            backLeft.setPower(mult * -1);
            runFor(sec);
        }

        private void runFor(double sec){
            //sleeps for given time, so the program can run. FTC sleep means keep doing what you are doing, not stop everything
            sleep((long) (1000 * sec));

        }
        public double getBatteryVoltage() {
            double result = Double.POSITIVE_INFINITY;
            for (VoltageSensor sensor : hardwareMap.voltageSensor) {
                double voltage = sensor.getVoltage();
                if (voltage > 0) {
                    result = Math.min(result, voltage);
                }
            }
            return result;
        }

        /*
        The method below, when called, should:
        get the current voltage & filter it into a category,
        store the category in a String
        place it in the right Switch Case
        which will change the speed multiplier
        and make consistent speed across all voltages
        (in theory)
         */
        public void voltageTelem(){

            batteryVoltage = getBatteryVoltage();
            boolean voltNotFound = false;

            if (batteryVoltage >= 14) {
                voltageCategory = "Above 14V";

            } else if (batteryVoltage >= 13) {
                voltageCategory = "13-14V";

            } else if (batteryVoltage >= 12) {
                voltageCategory = "12-13V";

            } else if (batteryVoltage >= 11) {
                voltageCategory = "11-12V";

            } else {

                voltageCategory = "Below 11V";
            }

            switch (voltageCategory) {
                case "Above 14V":
                    mult = 0.8;
                    break;
                case "13-14V":
                    mult = 0.85;
                    break;
                case "12-13V":
                    mult = 0.9;
                    break;
                case "11-12V":
                    mult = 1;
                    break;
                default:
                    telemetry.addLine("Cannot obtain voltage / Voltage too weak");
                    voltNotFound = true;
            }

            if(!voltNotFound){
                telemetry.addData("Current battery voltage: ", batteryVoltage);
                telemetry.addData("Current speed multiplier: ", mult);
            }

            telemetry.update();
        }
        private void closeClaw(double sec){
            leftHand.setPosition(robotConstants.Claw.leftClose);
            rightHand.setPosition(robotConstants.Claw.rightClose);
            runFor(sec);
        }

        private void openClaw(double sec){
            leftHand.setPosition(robotConstants.Claw.leftOpen);
            rightHand.setPosition(robotConstants.Claw.rightOpen);
            runFor(sec);
        }

        private void openLeftClaw(double sec){
            leftHand.setPosition(robotConstants.Claw.leftOpen);
            runFor(sec);
        }
        private void openRightClaw(double sec){
            rightHand.setPosition(robotConstants.Claw.rightOpen);
            runFor(sec);
        }



        private void setV4BInit(double sec){
            leftVirtualFourBar.setPosition(initLeft);
            rightVirtualFourBar.setPosition(initRight);
            runFor(sec);
        }

        private void setV4bIntaking(double sec){
            leftVirtualFourBar.setPosition(intakingLeft);
            rightVirtualFourBar.setPosition(intakingRight);
            runFor(sec);
        }

        private void setV4BOuttaking(double sec){
            leftVirtualFourBar.setPosition(outtakingLeft);
            rightVirtualFourBar.setPosition(outtakingRight);
            runFor(sec);
        }

        private void setWristNormal(double sec){
            wristServo.setPosition(robotConstants.Wrist.wristNormal);
            runFor(sec);
        }

        private void setWristSideways(double sec){
            wristServo.setPosition(robotConstants.Wrist.wristSideways);
            runFor(sec);
        }

        public void retractSlide(double sec){
            intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.retracted);

            intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            intakeSlideMotor.setPower(.5);

            runFor(sec);
        }


    }


