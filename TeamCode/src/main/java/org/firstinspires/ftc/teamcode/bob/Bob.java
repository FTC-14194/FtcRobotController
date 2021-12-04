package org.firstinspires.ftc.teamcode.bob;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@SuppressWarnings({"unused"})
public class Bob extends OpMode
{
    private DcMotor driveRF, driveRB, driveLF, driveLB;
    private Servo leftArm, rightArm;

    @Override
    public void init()
    {
        driveRF = hardwareMap.get(DcMotor.class, "driveRF");
        driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        driveLB = hardwareMap.get(DcMotor.class, "driveLB");
        leftArm= hardwareMap.get(Servo.class, "leftArm");
        rightArm = hardwareMap.get(Servo.class, "rightArm");

        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);
        leftArm.setDirection(Servo.Direction.REVERSE);
        rightArm.setDirection(Servo.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void loop()
    {
        //power values for drive train
        double xPow, yPow, rxPow;
        xPow = gamepad1.left_stick_x;
        yPow = gamepad1.left_stick_y;
        rxPow = gamepad1.right_stick_x;

        //give power to drive train (mecanum wheel movement)
        driveRF.setPower(yPow - xPow -rxPow);
        driveRB.setPower(yPow + xPow - rxPow);
        driveLF.setPower(yPow + xPow + rxPow);
        driveLB.setPower(yPow - xPow + rxPow);

        //maneuver front arms to sift through cargo
        leftArm.setPosition(gamepad1.left_trigger/2);
        rightArm.setPosition(gamepad1.right_trigger/2);
    }
}
