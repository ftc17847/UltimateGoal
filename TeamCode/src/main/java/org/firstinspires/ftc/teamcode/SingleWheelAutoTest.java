package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "SingleWheel", group = "Autonomous")
public class SingleWheelAutoTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware(this);

        r.startVision();

        waitForStart();
        runtime.reset();

        telemetry.addData("RingAmount: ", r.ringAmount());
        telemetry.update();


        pleaseMove(37.68, 18, 0);

        telemetry.addData("runtime", getRuntime());

        Thread.sleep(5000);

        pleaseMove(37.68, -18, 0);

        telemetry.addData("runtime", getRuntime() - 5);

        Thread.sleep(10000);

    }

    public void pleaseMove(double distance, double rpm, int turnstrafe) throws InterruptedException {


        DcMotorEx DM1 = hardwareMap.get(DcMotorEx.class, "DM1");
        DcMotorEx DM2 = hardwareMap.get(DcMotorEx.class, "DM2");
        DcMotorEx DM3 = hardwareMap.get(DcMotorEx.class, "DM3");
        DcMotorEx DM4 = hardwareMap.get(DcMotorEx.class, "DM4");

        DM1.setDirection(DcMotorEx.Direction.REVERSE);
        DM3.setDirection(DcMotorEx.Direction.REVERSE);

        DM1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DM2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DM3.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DM4.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        double speed = rpm * 280 / 60;
        int ticks = (int)(distance / (4 * Math.PI)) * 280;


        DM1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DM2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DM3.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DM4.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        DM1.setTargetPosition(ticks);
        DM2.setTargetPosition(ticks);
        DM3.setTargetPosition(ticks);
        DM4.setTargetPosition(ticks);

        DM1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        DM2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        DM3.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        DM4.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (turnstrafe == 0) {
            DM1.setVelocity(speed);
        }

        while(DM1.isBusy()) {

            telemetry.addData("pos", DM1.getCurrentPosition());
            telemetry.update();

        }
        DM1.setVelocity(0);
        DM2.setVelocity(0);
        DM3.setVelocity(0);
        DM4.setVelocity(0);

        telemetry.addLine("cool!");

        Thread.sleep(500);

    }

}
