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
    private DcMotor driveRF, driveRB, driveLF, driveLB, liftRight, liftLeft, intakeLeft, intakeRight;
    private Servo dropIntake;

    @Override
    public void init()
    {
        driveRF = hardwareMap.get(DcMotor.class, "driveRF");
        driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        driveLB = hardwareMap.get(DcMotor.class, "driveLB");
        liftRight = hardwareMap.get(DcMotor.class, "liftRight");
        liftLeft = hardwareMap.get(DcMotor.class, "liftLeft");
        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        dropIntake = hardwareMap.get(Servo.class, "dropIntake");

        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);
        liftRight.setDirection(DcMotor.Direction.FORWARD);
        liftLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);
        dropIntake.setDirection(Servo.Direction.FORWARD);

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

        //power to drive train (mecanum wheel movement)
        driveRF.setPower(yPow - xPow - rxPow);
        driveRB.setPower(yPow + xPow - rxPow);
        driveLF.setPower(yPow + xPow + rxPow);
        driveLB.setPower(yPow - xPow + rxPow);

        //servo to rotate intake up and down
        if(gamepad1.a) //up
        {
           dropIntake.setPosition(0.2);
        }
        else if (gamepad1.b) //down
        {
            dropIntake.setPosition(0.7);
        }

        //power linear slide to raise and lower intake
        if (gamepad1.right_trigger > 0) //expand linear slides
        {
            liftRight.setPower(gamepad1.right_trigger/2);
            liftLeft.setPower(gamepad1.right_trigger/2);
        }
        else if (gamepad1.left_trigger > 0) //contract linear slides
        {
            liftRight.setPower(-gamepad1.left_trigger/2);
            liftLeft.setPower(-gamepad1.left_trigger/2);
        }
        else //supply no power to motors
        {
            liftRight.setPower(0.2);
            liftRight.setPower(0.2);
        }

        //power intake mechanism to grab and release cargo
        if (gamepad1.right_bumper) //take in cargo
        {
            intakeLeft.setPower(1.0);
            intakeRight.setPower(-1.0);
        }
        else if (gamepad1.left_bumper) //release cargo
        {
            intakeLeft.setPower(-1.0);
            intakeRight.setPower(1.0);
        }
        else //supply no power to motor
        {
            intakeLeft.setPower(0.0);
            intakeRight.setPower(0.0);
        }
    }
}
