package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Test", group = "Test")
public class TestTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware();

        r.startVision();

        waitForStart();
        runtime.reset();


        Thread.sleep(1000);

        telemetry.addData("Hello", "There");
        telemetry.update();
        Thread.sleep(5000);
        telemetry.addData("Please", "Work");
        telemetry.update();
        Thread.sleep(5000);


    }
}