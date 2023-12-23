package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

/*
******* NOTE *******
This detection is used for the FTC 2023-2024 CENTERSTAGE Challenge by the CPHS Robolobos (14361)

This code is heavily inspired and guided by team Wolf Corp Robotics 12525 (I love these people)

here's the vid that step by step saved my sanity & explains how this version of HSV color detection works:
https://www.youtube.com/watch?v=JO7dqzJi8lw&ab_channel=FTCteamWolfCorpRobotics12525

I've added some comments abt stuff- Good luck!
 */
public class HSVBlueDetection extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        RIGHT,
        MIDDLE
    }

    // lowkey i barely know what volatiles is- just google it
    private volatile Location location = Location.RIGHT;

    /*
    These create the rectangles that your TSE should be in.
    See what the camera does by using the camera str    eam in the menu of the driver station WHILE the bot is active
    Adjust the camera or the boxes so your TSE is inside it

     */
    static final Rect LEFT_ROI = new Rect(
            new Point(40, 135),
            new Point(100, 175));
    static final Rect MIDDLE_ROI = new Rect(
            new Point(220, 138),
            new Point(280, 178));
    static double PERCENT_COLOR_THRESHOLD = 0.35;

    public HSVBlueDetection(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {

        // changes the frame captured by the camera from RGB to HSV
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV); // Imgproc.COLOR_RGB2HSV & Imgproc.COLOR_RGB2HSV_FULL are the same; FULL is the scale 0-360 & normal is 0-180

        /*
        HSV = [ HUE (color), SATURATION (grey), VALUE (brightness) ]
        HSV has a low end & high end of the spectrum, and here we set the range of color we want

        how do i know what values to put in ???
        use this handy dandy link i found (please do not click on any links/ads, it is super sketchy)
        https://www.tydac.ch/color/
         */

        // *** don't forget to divide the values by 2 if you use Imgproc.COLOR_RBG2HSV

        // in this case, we using dark blue to light blue
        Scalar lowHSV = new Scalar(100, 150, 0);
        Scalar highHSV = new Scalar(128, 255, 255);


        // this shows us the stuff in our range (in this case blue)
        // mat is the matrix, and we define our
        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat middle = mat.submat(MIDDLE_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;

        left.release();
        middle.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(middle).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(middleValue * 100) + "%");

        boolean tseLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean tseMiddle = middleValue > PERCENT_COLOR_THRESHOLD;


        /*
        THIS IS DIFFERENT FROM THE VIDEO!!
        in the video, the detection system is made to find normal stones because they're yellow
        and easy to detect, and depending on where the normal stones are, it knows where the skystone is

        this altered version I've made is for Centerstage, and our TSE colors are either red/blue;
        Which means it's much simpler for us that we only need to detect the color red/blue

        That's why his code is 'reversed'- he's not actually detecting the Skystone- just the normal ones
         */
        if (tseLeft) {
            location = Location.LEFT;
            telemetry.addData("TSE Location: ", "LEFT");
        }
        else if(tseMiddle) {
            location = Location.MIDDLE;
            telemetry.addData("TSE Location: ", "MIDDLE");
        }
        else {
            location = Location.RIGHT;
            telemetry.addData("TSE not detected; Location: ", "RIGHT");
        }

        telemetry.update();


        // change the img back to RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        // below will make the boxes Red if no TSE exists, and Green if it does within the rectangle box
        Scalar noTSE = new Scalar(255, 0, 0);
        Scalar tseDetected = new Scalar(0, 255, 0);

        // depending on where the TSEe is, or where it isn't, the color of the rectangle will change
        Imgproc.rectangle(mat, LEFT_ROI, location == Location.LEFT? tseDetected:noTSE);
        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.MIDDLE? tseDetected:noTSE);

        return mat;
    }

    public Location getLocation() {
        return location;
    }
}