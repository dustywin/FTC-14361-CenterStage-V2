package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "RedDetect Test")

public class RedDetectTest extends LinearOpMode {
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;

    public void runOpMode() {

    }
    waitForStart();

    camera.stopStreaming();
}