
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Odometry {

    public void updateLocation() {


        double wheelDiameter = 2.3622; //inches
        double ticksPerRotation = 2048; //if this doesn't work then it's probably 8192
        double distancePerRotation = wheelDiameter * Math.PI;
        double de1,de2,de3;
        double d1 = 1; // Distance From Center
        double d2 = 1;
        double d3 = 1;
        double cx, cy, ca;
        Hardware hardware = new Hardware();
        double[] gp = hardware.getGlobalPos();
        double x = gp[0];
        double y = gp[1];
        double a = gp[2];
        double e1 = hardware.En1.getCurrentPosition();
        double e2 = hardware.En2.getCurrentPosition();
        double e3 = hardware.En3.getCurrentPosition();

        //convert to distance

        de1 = distancePerRotation / (ticksPerRotation / e1);
        de2 = distancePerRotation / (ticksPerRotation / e2);
        de3 = distancePerRotation / (ticksPerRotation / e3);


        //find angle

        ca = (de1 - de2) / (d1 + d2);


        //it might happen

        if (ca == 0) {

            cx = de3;
            cy = de2;

        }

        //update global positions

        else {

            cx = 2 * Math.sin(ca / 2) * ((de3 / ca) + d3);
            cy = 2 * Math.sin(ca / 2) * ((de2 / ca) + d2);

        }

        double z = Math.hypot(cx, cy);

        a = a + Math.toDegrees(ca/2);
        x = x + (z * Math.cos(Math.toRadians(a)));
        y = y + (z * Math.sin(Math.toRadians(a)));

        hardware.setGlobalPos(x, y, a);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //im hoping that this sets the

        hardware.En1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.En2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.En3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hardware.En1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hardware.En2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hardware.En3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




    }

    public void moveTo(double x, double y, double a) {

        Hardware hardware = new Hardware();
        double[] arePos = hardware.getGlobalPos();

        double[] wantPos = new double[3];

        wantPos[0] = x;
        wantPos[1] = y;
        wantPos[2] = a;

        double moveAngle1;
        double moveAngle = 0;



        while (arePos != wantPos) {

            double dx = wantPos[0] - arePos[0];
            double dy = wantPos[1] - arePos[1];

            if (dx != 0 && dy != 0) {

                moveAngle1 = Math.toDegrees(Math.atan(Math.abs(dy) / Math.abs(dx)));

                if (dx > 0 && dy > 0) {

                    moveAngle = 90 - moveAngle1;

                }

                else if (dx > 0 && dy < 0) {

                    moveAngle = 90 + moveAngle1;

                }

                else if (dx < 0 && dy > 0) {

                    moveAngle = 270 + moveAngle1;

                }

                else if (dx < 0 && dy < 0) {

                    moveAngle = 270 - moveAngle1;

                }

            }

            else if (dx == 0 && dy > 0) {

                moveAngle = 0;

            }

            else if (dx == 0 && dy < 0) {

                moveAngle = 180;

            }

            else if (dy == 0 && dx > 0) {

                moveAngle = 90;


            }

            else if (dy == 0 && dx < 0) {

                moveAngle = 270;


            }

            boolean turnDirection = false;

            if (((Math.abs(arePos[2] - moveAngle) > 180) && (arePos[2] > moveAngle)) ||
                    ((Math.abs(arePos[2] - moveAngle) < 180) && (arePos[2] < moveAngle))) {

                turnDirection = true;

            }

            if (arePos[2] != moveAngle && turnDirection) {


                hardware.PowerControl(0.1, 0.1, -0.1, -0.1);

            }

            else if (arePos[2] != moveAngle && !turnDirection) {

                hardware.PowerControl(-0.1, -0.1, 0.1, 0.1);

            }

            if (arePos[2] == moveAngle && (arePos[0] != wantPos[0] || arePos[1] != wantPos[1])) {

                hardware.PowerControl(0.1, 0.1, 0.1, 0.1);

            }

            boolean turnDirection2 = false;

            if (((Math.abs(arePos[2] - wantPos[2]) > 180) && (arePos[2] > wantPos[2])) ||
                    ((Math.abs(arePos[2] - wantPos[2]) < 180) && (arePos[2] < wantPos[2]))) {

                turnDirection2 = true;

            }

            if (arePos[0] == wantPos[0] && arePos[1] == wantPos[1] && arePos[2] != wantPos[2]) {

                if (turnDirection2) {

                    hardware.PowerControl(0.1, 0.1, -0.1, -0.1);

                }

                else {

                    hardware.PowerControl(-0.1, -0.1, 0.1, 0.1);

                }

            }

            arePos = hardware.getGlobalPos();

            updateLocation();

        }

        hardware.PowerControl(0, 0, 0, 0);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
