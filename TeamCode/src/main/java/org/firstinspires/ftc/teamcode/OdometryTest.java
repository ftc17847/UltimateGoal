package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Odometry Auto Test", group = "Test")
public class OdometryTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Hardware r = new Hardware();

        Odometry o = new Odometry();

        r.setGlobalPos(12, 12, 0);

        o.moveTo(24, 12, 0);

        o.moveTo(12, 24, 0);

        o.moveTo(0, 12, 0);

        o.moveTo(12, 0, 0);

        o.moveTo(12, 12, 0);

        o.moveTo(12, 12, 90);

        o.moveTo(12, 12, 270);

        o.moveTo(12, 12, 180);

        o.moveTo(12, 12, 0);

    }
}