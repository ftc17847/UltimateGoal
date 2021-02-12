package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Gooder Controls", group = "TeleOp")

public class MotorLaunchSpeed extends LinearOpMode {


    //Odometry odometry = new Odometry();

    double MP = .5; //Movement Power
    double IP = .4; //Intake Power
    double CP = .3; //conveyor Power
    double LP = 30 * 23*26 / 60; //Launcher velocity (rpm)
    double WP = .3;
    boolean a = false;
    boolean b = false;
    float c = 0;
    boolean d = false;
    float e = 0;
    boolean f = false;

    double p1, p2, p3, p4;

    @Override
    public void runOpMode() {

        Hardware hardware = new Hardware(this);

        waitForStart();

        while(opModeIsActive()) {


            //I think that the x or y direction of each switch might have to be made negative
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

            p1 = (LSY1 + LSX1 + RSX1);
            p2 = (LSY1 + LSX1 - RSX1);
            p3 = (LSY1 - LSX1 + RSX1);
            p4 = (LSY1 - LSX1 - RSX1);
            //hardware.Wobble.setPower(LSY2 * WP);

            if (p1 > 1) {p1 = 1;}
            if (p1 < -1) {p1 = -1;}
            if (p2 > 1) {p1 = 1;}
            if (p2 < -1) {p1 = -1;}
            if (p3 > 1) {p1 = 1;}
            if (p3 < -1) {p1 = -1;}
            if (p4 > 1) {p1 = 1;}
            if (p4 < -1) {p1 = -1;}

            p1 = p1 * MP;
            p2 = p2 * MP;
            p3 = p3 * MP;
            p4 = p4 * MP;

            hardware.PowerControl(p1, p2, p3, p4);


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

            if (f != AB2 && AB2) {
                LP = LP + 5;
            }
            else if (d != BB2 && BB2) {
                LP = LP - 5;
            }
            if (MP > 1) {
                MP = 1;
            }
            else if (MP <= 0) {
                MP = .1;
            }

            f = AB2;
            d = BB2;

            if (RT1 > .1 && RT1 != e) {
                MP = MP + .1;
            }
            else if (LT1 > .1 && LT1 != c) {
                MP = MP - .1;
            }

            RT1 = e;
            LT1 = c;

            telemetry.addData("want speed", LP);
            telemetry.addData("have speed", hardware.L.getVelocity());
            telemetry.addData("motor power", MP);
            telemetry.update();

        }


    }

}
