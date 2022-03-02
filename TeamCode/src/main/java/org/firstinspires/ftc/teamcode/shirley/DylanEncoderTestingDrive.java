package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous
public class DylanEncoderTestingDrive extends LinearOpMode
{
    private DcMotor actuator, driveL, driveR;

    public void runOpMode() throws InterruptedException
    {
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        driveL = hardwareMap.get(DcMotor.class, "driveL");
        driveR = hardwareMap.get(DcMotor.class, "driveR");

        //resetEncoders();
/*
        driveL.setTargetPosition(3000);
        initEncoders();
        driveL.setPower(1);*/
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder value Actuator", actuator.getCurrentPosition());
        telemetry.update();

        waitForStart();

        telemetry.addData("Test", "Start");
        actuator.setTargetPosition(0);
        driveL.setTargetPosition(-1000);
        driveR.setTargetPosition(1000);
        initEncoders();
        driveL.setPower(-0.1);
        driveR.setPower(0.1);
        while(driveL.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", driveL.getCurrentPosition());
            telemetry.addData("Encoder value FRONT", driveR.getCurrentPosition());
        }
/*
        actuator.setTargetPosition(0);
        driveL.setTargetPosition(-3000);
        driveR.setTargetPosition(-3000);
        initEncoders();
        driveL.setPower(-1.0);
        driveR.setPower(-1.0);
        while(actuator.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", driveL.getCurrentPosition());
            telemetry.addData("Encoder value FRONT", driveR.getCurrentPosition());
        }*/
    }

    private void initEncoders()
    {
        actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}