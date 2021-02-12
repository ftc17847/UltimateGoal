package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "DriveTime", group = "Test")

public class AutoTimeDrive extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();

    //Odometry odometry = new Odometry();

    //I think that the x or y direction of each switch might have to be made negative

    int a = 1;
    boolean b = false;
    int c = 1;
    boolean d = false;
    int e = 1;
    boolean f = false;

    double p1, p2, p3, p4;

    @Override
    public void runOpMode() {
        double MP = 1; //Movement Power
        double IP = 1; //Intake Power
        double CP = 1; //conveyor Power
        double LP = 1; //Launcher Power
        double p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        Hardware hardware = new Hardware(this);
        runtime.reset();

        waitForStart();

        while(opModeIsActive()) {

            float LSX1 = gamepad1.left_stick_x;
            float LSY1 = gamepad1.left_stick_y;
            float RSX1 = gamepad1.right_stick_x;
            float RSY1 = gamepad1.right_stick_y;
            boolean AB1 = gamepad1.a;
            boolean BB1 = gamepad1.b;
            boolean XB1 = gamepad1.x;
            boolean YB1 = gamepad1.y;
            float LT1 = gamepad1.left_trigger;
            float RT1 = gamepad1.right_trigger;
            boolean AB2 = gamepad2.a;
            boolean BB2 = gamepad2.b;
            boolean XB2 = gamepad2.x;
            boolean YB2 = gamepad2.y;

            double p = p1;

            if (LSY1 > .2) {

                p1 = .5;
                p2 = .5;
                p3 = .5;
                p4 = .5;

            }

            else if (LSY1 < .2) {

                p1 = -.5;
                p2 = -.5;
                p3 = -.5;
                p4 = -.5;

            }

            else if (XB1) {

                p1 = .5;
                p2 = .5;
                p3 = -.5;
                p4 = -.5;

            }

            else if (BB1) {

                p1 = -.5;
                p2 = -.5;
                p3 = .5;
                p4 = .5;

            }

            else if (LT1 > .3) {

                p1 = -.5;
                p2 = .5;
                p3 = -.5;
                p4 = .5;

            }

            else if (RT1 > .3) {

                p1 = .5;
                p2 = -.5;
                p3 = .5;
                p4 = -.5;

            }

            if (p != p1) {

                if (p1 == 0) {
                    telemetry.addData("Time", getRuntime());
                    telemetry.update();
                }
                runtime.reset();

            }

            hardware.PowerControl(p1, p2, p3, p4);

        }


    }

}
