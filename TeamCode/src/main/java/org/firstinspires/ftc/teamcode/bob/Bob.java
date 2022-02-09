package org.firstinspires.ftc.teamcode.bob;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@SuppressWarnings({"unused"})
public class Bob extends OpMode
{
    private DcMotor driveR, driveL;
    private CRServo carousel;

    @Override
    public void init()
    {
        driveR = hardwareMap.get(DcMotor.class, "driveR");
        driveL = hardwareMap.get(DcMotor.class, "driveL");
        carousel = hardwareMap.get(CRServo.class, "carousel");

        driveR.setDirection(DcMotor.Direction.FORWARD);
        driveL.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void loop()
    {

        driveL.setPower(-gamepad1.left_stick_y);
        driveR.setPower(gamepad1.right_stick_y);

        if (gamepad1.right_bumper) //clockwise
        {
            carousel.setPower(1.0);
        }
        else if (gamepad1.left_bumper) //counter-clockwise
        {
            carousel.setPower(-1.0);
        }
        else //turn off power
        {
            carousel.setPower(0.0);
        }
    }
}
