//Meet #1 @ Legacy HS 11/20/2021
package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous(name = "Shirley Blue Storage Unit")
@SuppressWarnings({"unused"})
public class AUTO_Shirley_Blue_LEGACY extends LinearOpMode
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

        waitForStart();

        //turn towards storage unit
        frontDrive.setPower(0.5);
        backDrive.setPower(-0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.35)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);

        //line up with wall
        frontDrive.setPower(1.0);
        backDrive.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1.45)//1.55
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //angle to line up with carousel
        frontDrive.setPower(-0.5);
        backDrive.setPower(0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.25)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //drive back to meet carousel
        frontDrive.setPower(-0.5);
        backDrive.setPower(-0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 2.55)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //backwards adjustments
        frontDrive.setPower(-0.1);
        backDrive.setPower(-0.1);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.50)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

        //spin the carousel
        carousel.setPower(1.0);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 10.00)
        {
            telemetry.update();
        }
        carousel.setPower(0.0);

        //turn to line up with storage unit
        frontDrive.setPower(0.5);
        backDrive.setPower(-0.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 0.4)
        {
            telemetry.update();
        }
        frontDrive.setPower(0.0);
        backDrive.setPower(0.0);

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
