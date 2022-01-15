package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Shirley Red Storage Unit BASE")
@SuppressWarnings({"unused"})
public class AUTO_Shirley_Red1 extends LinearOpMode
{
    private DcMotor frontDrive, backDrive, raiseClaw;
    private Servo rotateClaw, claw;
    private CRServo carousel;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        raiseClaw = hardwareMap.get(DcMotor.class, "raiseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        carousel = hardwareMap.get(CRServo.class, "carousel");

        frontDrive.setDirection(DcMotor.Direction.REVERSE);
        backDrive.setDirection(DcMotor.Direction.FORWARD);
        raiseClaw.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        rotateClaw.setPosition(1.0);
        claw.setPosition(1.0);

        //turn towards storage unit
        frontDrive.setPower(-0.5);
        backDrive.setPower(0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.1)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //line up with wall
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1.6)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //angle to line up with carousel
        frontDrive.setPower(0.5);
        backDrive.setPower(-0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.50)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //drive back to meet carousel
        frontDrive.setPower(-0.5);
        backDrive.setPower(-0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1.30)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //spin the carousel
        carousel.setPower(-1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 10.00)
        {
            telemetry.update();
        }
        carousel.setPower(0.0);

        //drive "completely in" to storage unit
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.6)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);
    }
}
