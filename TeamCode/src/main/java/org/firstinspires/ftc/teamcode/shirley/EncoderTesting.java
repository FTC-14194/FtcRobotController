package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous
public class EncoderTesting extends LinearOpMode
{
    private DcMotor frontDrive, backDrive;
    private Servo rotateClaw, claw;
    private CRServo carousel;

    public void runOpMode() throws InterruptedException
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        carousel = hardwareMap.get(CRServo.class, "carousel");
        claw = hardwareMap.get(Servo.class, "claw");

        frontDrive.setDirection(DcMotor.Direction.REVERSE);
        backDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder value FRONT", frontDrive.getCurrentPosition());
        telemetry.addData("Encoder value BACK", backDrive.getCurrentPosition());
        telemetry.update();

        //initialize claw
        rotateClaw.setPosition(0.75);
        claw.setPosition(1.0);

        resetEncoders();

        waitForStart();

        frontDrive.setTargetPosition(3000);
        backDrive.setTargetPosition(3000);
        initEncoders();
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        while(frontDrive.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", frontDrive.getCurrentPosition());
            telemetry.addData("Encoder value BACK", backDrive.getCurrentPosition());
        }
        resetEncoders();

        frontDrive.setTargetPosition(5000);
        backDrive.setTargetPosition(5000);
        initEncoders();
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        while(frontDrive.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", frontDrive.getCurrentPosition());
            telemetry.addData("Encoder value BACK", backDrive.getCurrentPosition());
        }
        resetEncoders();

        frontDrive.setTargetPosition(-2000);
        backDrive.setTargetPosition(-2000);
        initEncoders();
        frontDrive.setPower(-1.0);
        backDrive.setPower(-1.0);
        while(frontDrive.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", frontDrive.getCurrentPosition());
            telemetry.addData("Encoder value BACK", backDrive.getCurrentPosition());
        }
        resetEncoders();
    }

    private void resetEncoders()
    {
        frontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void initEncoders()
    {
        frontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}