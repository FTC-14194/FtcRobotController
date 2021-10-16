package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

@TeleOp
@SuppressWarnings({"unused"})
public class Shirley extends OpMode
{
    private DcMotor frontDrive, backDrive;
    private Servo rotateClaw, claw;

    @Override
    public void init()
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");

        frontDrive.setDirection(DcMotor.Direction.FORWARD);
        backDrive.setDirection(DcMotor.Direction.REVERSE);

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
        if (gamepad1.a)
        {
            rotateClaw.setPosition(0.25);
        }
        else if (gamepad1.b)
        {
            rotateClaw.setPosition(0.5);
        }

        //claw grabbing
        if (gamepad1.x)
        {
            claw.setPosition(0.7);
        }
        else if (gamepad1.y)
        {
            claw.setPosition(1.0);
        }

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}