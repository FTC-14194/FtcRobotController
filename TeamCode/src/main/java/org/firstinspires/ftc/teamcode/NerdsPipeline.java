package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class NerdsPipeline extends OpenCvPipeline
{
    /*
     * 1 = scoring level 1
     * 2 = scoring level 2
     * 3 = scoring level 3
     */
    private int logicPos = 0;

    @Override
    public Mat processFrame(Mat input)
    {
        //create an organic version of the Mat
        Mat preview = new Mat();
        Imgproc.cvtColor(input, preview, Imgproc.COLOR_RGB2HSV);

        //create HSV values for the low and high end of what is considered to be yellow
        Scalar lowHSV = new Scalar(20, 100, 100);
        Scalar highHSV = new Scalar(30, 255, 255);

        return preview;
    }

    public int logicPos()
    {
        return logicPos;
    }
}
