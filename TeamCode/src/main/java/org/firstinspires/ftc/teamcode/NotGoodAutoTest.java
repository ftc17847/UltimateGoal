package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "NotGoodAutoTest", group = "Autonomous")
public class NotGoodAutoTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware(this);

        //Odometry o = new Odometry();

        r.startVision();

        waitForStart();
        runtime.reset();

        telemetry.addData("RingAmount: ", r.ringAmount());
        telemetry.update();


        if (r.ringAmount() == 0) {
            pleaseMove(60, 60, 0);
            r.Grab.setPosition(180);
            Thread.sleep(500);
        }
        else if (r.ringAmount() == 1) {
            pleaseMove(72, 60, 0);
            pleaseMove(24, 60, -2);
            r.Grab.setPosition(180);
            Thread.sleep(500);
            pleaseMove(24, -60, 0);
        }
        else if (r.ringAmount() == 4) {
            pleaseMove(120, 60, 0);
            r.Grab.setPosition(180);
            Thread.sleep(500);
            pleaseMove(48, -60, 0);
        }

    }

    public void pleaseMove(double distance, double rpm, int turnstrafe) throws InterruptedException {


        DcMotorEx DM1 = hardwareMap.get(DcMotorEx.class, "DM1");
        DcMotorEx DM2 = hardwareMap.get(DcMotorEx.class, "DM2");
        DcMotorEx DM3 = hardwareMap.get(DcMotorEx.class, "DM3");
        DcMotorEx DM4 = hardwareMap.get(DcMotorEx.class, "DM4");

        DM1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        double speed = rpm * 280 / 60;
        int ticks = (int)(distance / (4 * Math.PI)) * 280;


        DM1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DM2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DM3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DM4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            DM1.setTargetPosition(ticks);
            DM2.setTargetPosition(ticks);
            DM3.setTargetPosition(ticks);
            DM4.setTargetPosition(ticks);

        DM1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DM2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DM3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DM4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (turnstrafe == 0) {
            DM1.setVelocity(speed);
            DM2.setVelocity(speed);
            DM3.setVelocity(speed);
            DM4.setVelocity(speed);
        }
        if (turnstrafe == 1) {
            DM1.setVelocity(speed);
            DM2.setVelocity(-speed);
            DM3.setVelocity(speed);
            DM4.setVelocity(-speed);
        }
        if (turnstrafe == -1) {
            DM1.setVelocity(-speed);
            DM2.setVelocity(speed);
            DM3.setVelocity(-speed);
            DM4.setVelocity(speed);
        }
        if (turnstrafe == -2) {
            DM1.setVelocity(-speed);
            DM2.setVelocity(-speed);
            DM3.setVelocity(speed);
            DM4.setVelocity(speed);
        }
        if (turnstrafe == 2) {
            DM1.setVelocity(speed);
            DM2.setVelocity(speed);
            DM3.setVelocity(-speed);
            DM4.setVelocity(-speed);
        }
        while(DM1.isBusy()) {

            telemetry.addLine("hi :)");
            telemetry.update();

        }
        DM1.setVelocity(0);
        DM2.setVelocity(0);
        DM3.setVelocity(0);
        DM4.setVelocity(0);

        Thread.sleep(500);

    }

}
