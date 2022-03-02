package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous
public class EncoderReset extends LinearOpMode
{
    private DcMotor actuator, driveL, driveR;

    public void runOpMode() throws InterruptedException
    {
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        driveL = hardwareMap.get(DcMotor.class, "driveL");
        driveR = hardwareMap.get(DcMotor.class, "driveR");

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder value Actuator", actuator.getCurrentPosition());
        telemetry.addData("Encoder value Actuator", driveL.getCurrentPosition());
        telemetry.addData("Encoder value Actuator", driveR.getCurrentPosition());
        telemetry.update();

        waitForStart();

        resetEncoders();
    }

    private void resetEncoders()
    {
        actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}