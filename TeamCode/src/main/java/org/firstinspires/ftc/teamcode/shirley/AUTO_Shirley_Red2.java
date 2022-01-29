package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Shirley Red Warehouse")
@SuppressWarnings({"unused"})
public class AUTO_Shirley_Red2 extends LinearOpMode
{
    private DcMotor frontDrive, backDrive;
    private Servo rotateClaw, claw;
    private CRServo carousel;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        carousel = hardwareMap.get(CRServo.class, "carousel");

        frontDrive.setDirection(DcMotor.Direction.REVERSE);
        backDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initialize claw
        rotateClaw.setPosition(0.75);
        claw.setPosition(1.0);

        waitForStart();

        //turn towards warehouse
        frontDrive.setPower(1.0);
        backDrive.setPower(-1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.35)//0.45
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //drive "completely in" to storage unit
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1.6)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);
    }
}
