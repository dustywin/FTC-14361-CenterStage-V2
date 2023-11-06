package org.firstinspires.ftc.teamcode.Utilities;

public class RobotConstants
{
    public static class OuttakeSlide
    {
        public static double P = 0.0;
        public static double I = 0.0;
        public static double D = 0.0;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;

        public static int HIGHLEFT = 1850;
        public static int HIGHRIGHT = 1850;

        public static int MEDIUMLEFT = 1500;
        public static int MEDIUMRIGHT = 1500;

        public static int LOWLEFT = 1000;
        public static int LOWRIGHT = 1000;

        public static int GROUNDLEFT = 0;
        public static int GROUNDRIGHT = 0;
    }

    public static class IntakeSlide
    {
        public static double P = 0.5;
        public static double I = 0.5;
        public static double D = 0.5;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;


        public static int HIGHEXTENSION = 700;
        public static int MEDIUMEXTENSION = 380;


//        public static int fullExtension = -3600;
//        public static int mediumExtension = -1200;
//        public static int shortExtension = -500;

        // first comp length below

        public static int LOWEXTENSION = 0;
        public static int RETRACTED = -2;
    }

    public static class Claw
    {
        public static double intakeAuto = 0.0;
        public static double intakeTeleOp = 0.0;

        public static double leftClose = .5;
        public static double rightClose = .8;
        public static double leftOpen = .6;
        public static double rightOpen = .9;

        public static double outWristRotation = 0.0;
        public static double sideWristRotation = 0.0;
    }

    public static class Arm
    {
        public static double outtake = 0.0;
        public static double intake = 0.0;
    }

    public static class ActiveIntake
    {
        public static int active = 1;
        public static int reverseActive = -1;
    }

    public static class Drone
    {
        public static double launchPosition = 0.0;
        public static  double loadedPosition = 0.0;
    }

    public static class Climb
    {
        public static int climbPosition = 0;
    }

    public static class Wrist
    {
        public static double wristSideways = .33;
        public static double wristNormal = .67;
    }

    public static class VirtualFourBar
    {
        public static double intakingLeft = .48;
        public static double intakingRight = .45;

        public static double outtakingRight = .1;

        public static double outtakingLeft = .13;

        public static double initLeft = .43;
        public static double initRight = .4;
    }
}