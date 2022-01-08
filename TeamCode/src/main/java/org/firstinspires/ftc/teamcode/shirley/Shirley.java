package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

@TeleOp
@SuppressWarnings({"unused"})
public class Shirley extends OpMode
{
    private DcMotor frontDrive, backDrive, raiseClaw;
    private Servo rotateClaw, claw;
    private CRServo carousel;

    @Override
    public void init()
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        raiseClaw = hardwareMap.get(DcMotor.class, "raiseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        carousel = hardwareMap.get(CRServo.class, "carousel");

        frontDrive.setDirection(DcMotor.Direction.FORWARD);
        backDrive.setDirection(DcMotor.Direction.REVERSE);
        raiseClaw.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        //driving
        frontDrive.setPower(gamepad1.left_stick_y);
        backDrive.setPower(gamepad1.right_stick_y);

        //claw rotation
        if (gamepad1.a) //tilt down
        {
            rotateClaw.setPosition(0.5);
        }
        else if (gamepad1.b) //tilt up
        {
            rotateClaw.setPosition(1.0);
        }

        //claw grabbing
        if (gamepad1.x) //open claw
        {
            claw.setPosition(0.5);
        }
        else if (gamepad1.y) //close claw
        {
            claw.setPosition(1.0);
        }

        //raise and lower claw
        if (gamepad1.dpad_up) //raise
        {
            raiseClaw.setPower(1.0);
        }
        else if (gamepad1.dpad_down) //lower
        {
            raiseClaw.setPower(-1.0);
        }
        else //turn off power
        {
            raiseClaw.setPower(0.0);
        }

        //carousel spinning
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

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}