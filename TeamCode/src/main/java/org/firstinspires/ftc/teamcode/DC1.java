package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Good Controls", group = "TeleOp")

public class DC1 extends LinearOpMode {

    Hardware hardware = new Hardware(this);
    Odometry odometry = new Odometry();
    double MP = .5; //Movement Power
    double IP = .3; //Intake Power
    double CP = .3; //conveyor Power
    double LP = .3; //Launcher Power
    double WP = .3;
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
    int a = 1;
    boolean b = false;
    int c = 1;
    boolean d = false;
    int e = 1;
    boolean f = false;

    double p1, p2, p3, p4;

    @Override
    public void runOpMode() {


        waitForStart();

        while(opModeIsActive()) {

            p1 = (LSY1 + LSX1 + RT1 - LT1) * MP;
            p2 = (LSY1 + LSX1 + LT1 - RT1) * MP;
            p3 = (LSY1 - LSX1 + RT1 - LT1) * MP;
            p4 = (LSY1 - LSX1 + LT1 - RT1) * MP;
            hardware.Wobble.setPower(LSY2 * WP);

            //this might not be necessary
            if (p1 > 1) {p1 = 1;}
            if (p1 < -1) {p1 = -1;}
            if (p2 > 1) {p1 = 1;}
            if (p2 < -1) {p1 = -1;}
            if (p3 > 1) {p1 = 1;}
            if (p3 < -1) {p1 = -1;}
            if (p4 > 1) {p1 = 1;}
            if (p4 < -1) {p1 = -1;}

            hardware.PowerControl(p1, p2, p3, p4);

            /*
            if (AB1) {

                odometry.moveTo(12, 12, 0);

            }
             */

            if (RT2 > 0) {
                hardware.C.setPower(CP);
                hardware.I.setPower(IP);
            }
            else if (AB2){
                hardware.C.setPower(0);
                hardware.I.setPower(0);
            }


            if (LT2 > 0) {
                hardware.L.setPower(LP);
                hardware.StopRing.setPosition(180);
            }
            else {
                hardware.L.setPower(0);
                hardware.StopRing.setPosition(0);
            }

            if (AB2) {

                hardware.Grab.setPosition(180);

            }

            else {

                hardware.Grab.setPosition(0);

            }




            odometry.updateLocation();

        }


    }

}
