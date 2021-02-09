package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AutoTest", group = "Autonomous")
public class AutoTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware(this);

        Odometry o = new Odometry();

        r.startVision();

        waitForStart();
        runtime.reset();

        telemetry.addData("RingAmount", r.ringAmount());
        telemetry.update();


        /*
         * im doing this as if the robots center is 7 inches away from
         * the red line that it starts on and the y is just 0 but the
         * robot is 18 inches long i dont know what im doing
         */
        r.setGlobalPos(40.5, 0, 0);

        if (r.ringAmount() == 0) {
            o.moveTo(84, 75, 180);
        }

        else if (r.ringAmount() == 1) {

            o.moveTo(60, 99, 180);

        }

        else if (r.ringAmount() == 4) {

            o.moveTo(84, 112, 180);

        }

        o.moveTo(36, 75, 0);

    }
}
