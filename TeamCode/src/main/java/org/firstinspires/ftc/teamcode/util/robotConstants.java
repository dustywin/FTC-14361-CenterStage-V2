package org.firstinspires.ftc.teamcode.util;

public class robotConstants
{
    public static class outtakeSlide
    {
        public static double P = 0.0;
        public static double I = 0.0;
        public static double D = 0.0;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;

        public static int MOSTHIGHLEFT = -1285;
        public static int MOSTHIGHRIGHT = 1285;

        public static int HIGHLEFT = -1210;
        public static int HIGHRIGHT = 1210;

        public static int MEDIUMLEFT = -900;
        public static int MEDIUMRIGHT = 900;

        public static int LOWLEFT = -450;
        public static int LOWRIGHT = 450;

        public static int AUTOLOWLEFT = -100;
        public static int AUTOLOWRIGHT = 100;

        public static int GROUNDLEFT = -57;
        public static int GROUNDRIGHT = 57;
    }

    public static class intakeSlide
    {
        public static double P = 0.5;
        public static double I = 0.5;
        public static double D = 0.5;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;

        public static int highExtension = 715;
        public static int mediumExtension = 230;
        public static int retracted = 0;
    }

    public static class Claw
    {
        public static double intakeAuto = 0.0;
        public static double intakeTeleOp = 0.0;

        public static double leftClose = .94;
        public static double rightClose = .35;
        public static double leftOpen = .77;
        public static double rightOpen = .47;
        public static double autoLeftClose = .65;
        public static double autoRightClose = .63;
        public static double rightCloseOnePixel = .58;
    }

    public static class activeIntake
    {

        public static double active = -.9;
        public static double reverseActive = .9;

        public static double autoActive = 0.34;
        public static double autoReverseActive = -0.34;
    }

    public static class Climb
    {
        public static int climbPosition = 0;
    }

    public static class Wrist
    {
        public static double outtaking = .81;
        public static double intaking = .145;
        public static double init = .100;
    }

    public static class virtualFourBar
    {
        public static double intakingLeft = 0.01;

        public static double intakingRight = .75;

        public static double outtakingLeft = .47;
        public static double outtakingRight = .31;

        public static double initLeft = .14;

        public static double initRight = .59;

        public static double outtakingDownLeft = .62;
        public static double outtakingDownRight = .08;

        public static double autoDropLeft = .57;
        public static double autoDropRight = .15;

        public static double intakingLeftAuton = 0.5;
        public static double intakingRightAuton = 0.6;
    }

    public static class linkage
    {
        public static double highPosition = 0;
        public static double mediumPosition = 0;
        public static double lowPosition = 0;

    }
}