package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Blue Warehouse")
public class ENCODER_AUTO_BW extends LinearOpMode
{
    //hardware declaration
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

        //move forward, ease off wall
        resetEncoders();
        driveR.setTargetPosition(-150);
        driveL.setTargetPosition(-150);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //turn right, face carousel
        resetEncoders();
        driveR.setTargetPosition(265);
        driveL.setTargetPosition(-265);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(-0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //move forward, line up with alliance shipping hub
        resetEncoders();
        driveR.setTargetPosition(-600);
        driveL.setTargetPosition(-600);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //turn left, face alliance shipping hub
        resetEncoders();
        driveR.setTargetPosition(-260);
        driveL.setTargetPosition(260);
        initEncoders();
        driveR.setPower(-0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //move forward, lower claw, meet alliance shipping hub
        rotateClaw.setPosition(0.40);
        resetEncoders();
        driveR.setTargetPosition(-400);
        driveL.setTargetPosition(-400);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //move forward, into alliance shipping hub
        resetEncoders();
        driveR.setTargetPosition(-350);
        driveL.setTargetPosition(-350);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //drop cargo, level 1
        claw.setPosition(0.5);

        //move backward, reset claw, to wall
        resetEncoders();
        driveR.setTargetPosition(800);
        driveL.setTargetPosition(800);
        initEncoders();
        driveR.setPower(-0.10);
        driveL.setPower(-0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }
        rotateClaw.setPosition(0.0);
        claw.setPosition(1.0);

        //turn left, face warehouse
        resetEncoders();
        driveR.setTargetPosition(-250);
        driveL.setTargetPosition(250);
        initEncoders();
        driveR.setPower(-0.10);
        driveL.setPower(0.10);
        while(driveR.isBusy() && opModeIsActive()) {
            telemetry.addData("driveR Position Check:", driveR.getCurrentPosition());
        }

        //move forward, into warehouse
        resetEncoders();
        driveR.setTargetPosition(-2500);
        driveL.setTargetPosition(-2500);
        initEncoders();
        driveR.setPower(0.10);
        driveL.setPower(0.10);
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