package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "BadAutoTest", group = "Autonomous")
public class BadAutoTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware(this);

        r.StopRing.setPosition(100);

        //Odometry o = new Odometry();

        r.DM1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.DM2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.DM3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.DM4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        r.startVision();

        waitForStart();
        runtime.reset();

        telemetry.addData("RingAmount: ", r.ringAmount());
        telemetry.update();

        pleaseMove(1, .5, -2);
        pleaseMove(1, .5, -1);

        if (r.ringAmount() == 0) {
            pleaseMove(1, .5, 0);
            r.StopRing.setPosition(100);


            //r.Grab.setPosition(180);
        }
        else if (r.ringAmount() == 1) {
            pleaseMove(1, .5, -2);
            pleaseMove(1, .5, 0);
            //r.Grab.setPosition(180);
            r.StopRing.setPosition(100);
            pleaseMove(1, -.5, 0);
        }
        else if (r.ringAmount() == 4) {
            pleaseMove(1, .5, 0);
            //r.Grab.setPosition(180);
            r.StopRing.setPosition(100);
            pleaseMove(1, -.5, 0);
        }

    }

    public void pleaseMove(long time, double speed, int turnstrafe) throws InterruptedException {

        Hardware r = new Hardware(this);

        if (turnstrafe == 0) {
            r.PowerControl(speed, speed, speed, speed);
        }
        if (turnstrafe == 1) {
            r.PowerControl(speed, speed, -speed, -speed);
        }
        if (turnstrafe == -1) {
            r.PowerControl(-speed, -speed, speed, speed);
        }
        if (turnstrafe == -2) {
            r.PowerControl(speed, -speed, speed, -speed);
        }
        if (turnstrafe == 2) {
            r.PowerControl(-speed, speed, -speed, speed);
        }
        Thread.sleep((time * 1000));
        r.PowerControl(0, 0, 0, 0);

    }

}
