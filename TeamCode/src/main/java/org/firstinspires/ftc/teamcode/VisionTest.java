package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "VisionTest", group = "Test")
public class VisionTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware(this);

        r.startVision();

        waitForStart();
        runtime.reset();


        Thread.sleep(3000);
        telemetry.addData("RingAmount: ", r.ringAmount());
        telemetry.update();
        Thread.sleep(5000);



    }
}