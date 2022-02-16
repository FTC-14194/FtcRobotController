package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@SuppressWarnings({"unused"})
public class ShirleyBob extends OpMode
{
    public DcMotor driveR, driveL, slide, actuator;
    public Servo claw, rotateClaw;
    public CRServo carousel;
    public RevColorSensorV3 colorSens;

    @Override
    public void init()
    {
        driveR = hardwareMap.get(DcMotor.class, "driveR");
        driveL = hardwareMap.get(DcMotor.class, "driveL");
        slide = hardwareMap.get(DcMotor.class, "slide");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        claw = hardwareMap.get(Servo.class, "claw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        carousel = hardwareMap.get(CRServo.class, "carousel");
        colorSens = hardwareMap.get(RevColorSensorV3.class, "colorSens");

        driveR.setDirection(DcMotor.Direction.FORWARD);
        driveL.setDirection(DcMotor.Direction.REVERSE);
        slide.setDirection(DcMotor.Direction.REVERSE);
        actuator.setDirection(DcMotor.Direction.FORWARD);

        claw.setPosition(1.0);
        rotateClaw.setPosition(0.0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        //drive motors, cut power to 75% for better control/limits
        driveR.setPower(gamepad1.right_stick_y * 0.75);
        driveL.setPower(gamepad1.left_stick_y * 0.75);

        //claw grabbing
        if (gamepad1.y) //open claw
        {
            claw.setPosition(0.5);
        }
        else if (gamepad1.x) //close claw
        {
            claw.setPosition(1.0);
        }

        //claw rotation
        if (gamepad1.b) //tilt up
        {
            rotateClaw.setPosition(0.0);
        }
        else if (gamepad1.a) //tilt down
        {
            rotateClaw.setPosition(0.45);
        }

        //slide (move actuator)
        if (gamepad1.dpad_up) //expand slide
        {
            slide.setPower(0.75);
        }
        else if (gamepad1.dpad_down) //collapse slide
        {
            slide.setPower(-0.75);
        }
        else  //turn off power
        {
            slide.setPower(0.0);
        }

        //actuator (move claw)
        if (gamepad1.dpad_right) //raise
        {
            actuator.setPower(0.75);
        }
        else if (gamepad1.dpad_left) //lower
        {
            actuator.setPower(-0.75);
        }
        else //turn off power
        {
            actuator.setPower(0.0);
        }

        //carousel
        if (gamepad1.right_bumper) //counterclockwise
        {
            carousel.setPower(1.0);
        }
        else if (gamepad1.left_bumper) //clockwise
        {
            carousel.setPower(-1.0);
        }
        else
        {
            carousel.setPower(0.0);
        }

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}