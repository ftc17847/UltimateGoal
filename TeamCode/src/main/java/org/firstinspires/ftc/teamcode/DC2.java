package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Bad Controls", group = "TeleOp")

public class DC2 extends LinearOpMode {

    //dometry odometry = new Odometry();
    double MP = .5; //Movement Power Multiplier
    double IP = .3; //Intake Power
    double CP = .3; //conveyor Power
    double LP = 50 * 140 / 60; //Launcher velocity (rpm)
    double WP = .3;
    //I think that the x or y direction of each switch might have to be made negative

    double p1, p2, p3, p4;

    @Override
    public void runOpMode() {

        Hardware hardware = new Hardware(this);

        waitForStart();

        while(opModeIsActive()) {


            float LSX1 = gamepad1.left_stick_x;
            float LSY1 = gamepad1.left_stick_y;
            float RSX1 = gamepad1.right_stick_x;
            float RSY1 = gamepad1.right_stick_y;
            float LSY2 = gamepad2.left_stick_y;
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
            float LT2 = gamepad2.left_trigger;
            float RT2 = gamepad2.right_trigger;

            hardware.PowerControl((LSY1 + LT1 - RT1) * MP, (RSY1 + LT1 - RT1) * MP, (LSY1 - LT1 + RT1) * MP, (RSY1 - LT1 + RT1) * MP);
            //hardware.Wobble.setPower(LSY2 * WP);

            /*
            if (AB1) {

                odometry.moveTo(12, 12, 0);

            }
             */

            if (RT2 > .3) {
                hardware.C.setPower(CP);
                hardware.I.setPower(IP);
            }
            else {
                hardware.C.setPower(0);
                hardware.I.setPower(0);
            }


            if (LT2 > .3) {
                hardware.L.setVelocity(-LP);
                hardware.StopRing.setPosition(180);
            }
            else {
                hardware.L.setVelocity(0);
                hardware.StopRing.setPosition(0);
            }

            /*if (AB2) {

                hardware.Grab.setPosition(180);

            }

            else {

                hardware.Grab.setPosition(0);

            }

             */




        }
    }
}