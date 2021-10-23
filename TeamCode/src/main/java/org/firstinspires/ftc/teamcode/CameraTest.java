package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Disabled
@TeleOp
@SuppressWarnings({"unused"})
public class CameraTest extends OpMode
{
    static final int CAMERA_WIDTH = 640;
    static final int CAMERA_HEIGHT = 360;

    NerdsPipeline detector = new NerdsPipeline();
    OpenCvCamera camera;

    //bounds for colors            Y      Cr     Cb       (keep Y)
    Scalar lowerBound = new Scalar(000.0, 128.0, 000.0);
    Scalar upperBound = new Scalar(255.0, 160.0, 096.0);

    @Override
    public void init()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        camera.setPipeline(detector);
        camera.openCameraDevice();
        camera.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
    }

    @Override
    public void loop()
    {

    }
}
