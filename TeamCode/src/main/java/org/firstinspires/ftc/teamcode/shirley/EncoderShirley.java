package org.firstinspires.ftc.teamcode.shirley;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

@Disabled
@TeleOp
@SuppressWarnings({"unused"})
public class EncoderShirley extends OpMode
{
    private DcMotor frontDrive, backDrive, raiseClaw;
    private Servo rotateClaw, claw;
    private CRServo carousel;
    private RevColorSensorV3 colorSens;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double distance = 0.0;
    private boolean flag = false;

    @Override
    public void init()
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");
        raiseClaw = hardwareMap.get(DcMotor.class, "raiseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        claw = hardwareMap.get(Servo.class, "claw");
        carousel = hardwareMap.get(CRServo.class, "carousel");
        colorSens = hardwareMap.get(RevColorSensorV3.class, "colorSens");

        frontDrive.setDirection(DcMotor.Direction.FORWARD);
        backDrive.setDirection(DcMotor.Direction.REVERSE);
        raiseClaw.setDirection(DcMotor.Direction.FORWARD);

        rotateClaw.setPosition(0.75);
        claw.setPosition(1.0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        //variables for color sensor
        red = colorSens.red();
        green = colorSens.green();
        blue = colorSens.blue();
        distance = colorSens.getDistance(DistanceUnit.CM);

        if (13.5 < distance && distance < 15.3)
        {
            if (30 < red && red < 60 && 50 < green && green < 90 && 30 < blue && blue < 60)
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
        }
        else
        {
            flag = false;
        }


        //driving
        frontDrive.setPower(gamepad1.left_stick_y);
        backDrive.setPower(gamepad1.right_stick_y);

        //claw rotation
        if (gamepad1.a) //tilt up
        {
            rotateClaw.setPosition(0.75);
        }
        else if (gamepad1.b) //tilt down
        {
            rotateClaw.setPosition(0.425);
        }

        //claw grabbing
        if (gamepad1.x) //open claw
        {
            claw.setPosition(0.5);
        }
        else if (gamepad1.y) //close claw
        {
            claw.setPosition(1.0);
        }

        //raise and lower claw
        if (gamepad1.dpad_up) //raise
        {
            raiseClaw.setPower(1.0);
        }
        else if (gamepad1.dpad_down) //lower
        {
            raiseClaw.setPower(-1.0);
        }
        else //turn off power
        {
            raiseClaw.setPower(0.0);
        }

        //carousel spinning
        if (gamepad1.right_bumper) //clockwise
        {
            carousel.setPower(1.0);
        }
        else if (gamepad1.left_bumper) //counter-clockwise
        {
            carousel.setPower(-1.0);
        }
        else //turn off power
        {
            carousel.setPower(0.0);
        }

        telemetry.addData("Status", "Running");
        telemetry.addData("Red: ", red);
        telemetry.addData("Green: ", green);
        telemetry.addData("Blue: ", blue);
        telemetry.addData("Distance: ", distance);
        telemetry.addData("Flag: ", flag);
        telemetry.update();
    }
}