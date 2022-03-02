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
    public DcMotor driveR, driveL, actuator;
    public Servo claw, rotateClaw;
    public CRServo carousel;
    public RevColorSensorV3 colorSens;

    @Override
    public void init()
    {
        driveR = hardwareMap.get(DcMotor.class, "driveR");
        driveL = hardwareMap.get(DcMotor.class, "driveL");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        claw = hardwareMap.get(Servo.class, "claw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        carousel = hardwareMap.get(CRServo.class, "carousel");
        colorSens = hardwareMap.get(RevColorSensorV3.class, "colorSens");

        driveR.setDirection(DcMotor.Direction.FORWARD);
        driveL.setDirection(DcMotor.Direction.REVERSE);
        actuator.setDirection(DcMotor.Direction.FORWARD);
        actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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
        if (gamepad1.a) //open claw
        {
            claw.setPosition(0.75);
        }
        else if (gamepad1.b) //close claw
        {
            claw.setPosition(1.0);
        }

        //claw rotation
        if (gamepad1.x) //rotate up
        {
            rotateClaw.setPosition(0.0);
        }
        else if (gamepad1.y) //rotate down
        {
            rotateClaw.setPosition(0.325);
        }

        //actuator (move claw)
        if (gamepad1.dpad_right && actuator.getCurrentPosition() < 5500) //raise
        {
            actuator.setTargetPosition(5500);
            actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            actuator.setPower(0.75);
        }
        else if (gamepad1.dpad_left && actuator.getCurrentPosition() > 0) //lower
        {
            actuator.setTargetPosition(0);
            actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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