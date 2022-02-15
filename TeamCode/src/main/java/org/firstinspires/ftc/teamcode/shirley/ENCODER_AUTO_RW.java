package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Red Warehouse")
public class ENCODER_AUTO_RW extends LinearOpMode
{
    //hardware
    private DcMotor driveR, driveL, slide, actuator;
    private Servo claw, rotateClaw;
    private CRServo carousel;
    private RevColorSensorV3 colorSens;

    public void runOpMode() throws InterruptedException
    {
        //hardware assignment
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
        slide.setDirection(DcMotor.Direction.FORWARD);
        actuator.setDirection(DcMotor.Direction.FORWARD);

        //initialization
        claw.setPosition(1.0);
        rotateClaw.setPosition(0.0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        //play

        //turn right
        resetEncoders();
        driveR.setTargetPosition(220);
        driveL.setTargetPosition(-220);
        initEncoders();
        driveR.setPower(0.5);
        driveL.setPower(-0.5);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //drive forward into warehouse
        resetEncoders();
        driveR.setTargetPosition(-2500);
        driveL.setTargetPosition(-2500);
        initEncoders();
        driveR.setPower(-0.25);
        driveL.setPower(-0.25);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        resetEncoders();
    }

    //method to reset the drive-wheel encoders
    private void resetEncoders()
    {
        driveR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //initialize encoders
    private void initEncoders()
    {
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}