package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Starter Stack Detection", group="1")
public class TestCamera extends LinearOpMode
{
    RingDetector ringDetector = null;

    @Override
    public void runOpMode()
    {
        ringDetector =  RingDetector.init(hardwareMap, "WEBCAM", true);
        waitForStart();

        while (opModeIsActive())
        {
            if (ringDetector != null) {
                telemetry.addData("Analysis", ringDetector.getAnalysis());
                telemetry.addData("Position", ringDetector.getPosition());
                telemetry.update();
            } else {
                telemetry.addData("Analysis","No Ring Detector configured");
            }

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }
}