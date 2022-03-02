package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous
public class DylanEncoderTesting extends LinearOpMode
{
    private DcMotor actuator;

    public void runOpMode() throws InterruptedException
    {
        actuator = hardwareMap.get(DcMotor.class, "actuator");

        //resetEncoders();


        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder value Actuator", actuator.getCurrentPosition());
        telemetry.update();

        waitForStart();

        telemetry.addData("Test", "Start");
        actuator.setTargetPosition(3000);
        initEncoders();
        actuator.setPower(-1.0);
        while(actuator.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", actuator.getCurrentPosition());
        }


        actuator.setTargetPosition(0);
        initEncoders();
        actuator.setPower(1.0);
        while(actuator.isBusy() && opModeIsActive()) {
            telemetry.addData("Encoder value FRONT", actuator.getCurrentPosition());
        }
        telemetry.addData("Encoder value FRONT", actuator.getCurrentPosition());
    }

    private void initEncoders()
    {
        actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}