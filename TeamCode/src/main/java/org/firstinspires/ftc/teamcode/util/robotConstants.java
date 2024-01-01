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


        public static int MOSTHIGHLEFT = -1150;
        public static int MOSTHIGHRIGHT = 1150;
        public static int HIGH = 1400;
        public static int MEDIUM = 900;
        public static int LOW = 450;
        //left is negative
        //right is positive



        public static int HIGHLEFT = -1200;
        public static int HIGHRIGHT = 1200;
        //sometimes the encoder values go negative so you might have to change those
        //here its the left one being negative

        public static int MEDIUMLEFT = -750;
        public static int MEDIUMRIGHT = 750;

        public static int LOWLEFT = -450;
        public static int LOWRIGHT = 450;


        public static int AUTOLOWLEFT = -100;
        public static int AUTOLOWRIGHT = 100;
        public static int GROUNDLEFT = 0;
        public static int GROUNDRIGHT = 0;
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

    public static class Arm
    {
        public static double outtake = 0.0;
        public static double intake = 0.0;
    }

    public static class activeIntake
    {

        public static double active = -.7;
        public static double reverseActive = .7;

        public static double autoActive = 0.34;
        public static double autoReverseActive = -0.34;



    }

    public static class Climb
    {
        public static int climbPosition = 0;
    }

    public static class Wrist
    {
        public static double downOuttaking = .81;
        public static double downIntaking = .145;

        // scale is from .33 (0) to .67 (1) so there no overshoot at all

        // side: .33
        // norm: .67
    }

    public static class virtualFourBar
    {
        public static double intakingLeft = 0.01;
        //.486
        //.588
        public static double intakingRight = .75;

        public static double outtakingLeft = .49;
        public static double outtakingRight = .29;

        public static double initLeft = .14;
        //.45
        //.55
        public static double initRight = .59;

        public static double outtakingDownLeft = .62;
        public static double outtakingDownRight = .08;

        public static double autoDropLeft = .57;
        public static double autoDropRight = .15;




        public static double intakingLeftAuton = 0.5;
        public static double intakingRightAuton = 0.6;




        // right: .58
        // left: .41

        /*
        Robot is EVEN at both sides at:

        L: .64
        R: .45
         */
    }
}