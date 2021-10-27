package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
@SuppressWarnings({"unused"})
public class AUTO_Shirley_Blue1 extends LinearOpMode
{
    private DcMotor frontDrive, backDrive;
    private Servo rotateClaw, claw;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");

        frontDrive.setDirection(DcMotor.Direction.REVERSE);
        backDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        //turn towards storage unit
        frontDrive.setPower(1.0);
        backDrive.setPower(-1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.2)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //drive "completely in" to storage unit
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1.45)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);
    }
}
