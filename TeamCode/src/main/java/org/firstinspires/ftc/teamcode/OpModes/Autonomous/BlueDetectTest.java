package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetection;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "BlueDetect Test")

public class BlueDetectTest {
    OpenCvCamera camera;
    HSVBlueDetection blueDetection;
    String webcamName;
}
