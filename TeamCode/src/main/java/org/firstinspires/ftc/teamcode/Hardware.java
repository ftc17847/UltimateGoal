package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;



public class Hardware {

    //drive motors
    public DcMotor DM1;
    public DcMotor DM2;
    public DcMotor DM3;
    public DcMotor DM4;

    //odometry encoders

    public DcMotor En1;
    public DcMotor En2;
    public DcMotor En3;

    //Launcher motor
    public DcMotor L;

    //Intake motor
    public DcMotor I;

    //Internal conveyor motor
    public DcMotor C;

    //Wobble Grabber Motor

    public DcMotor Wobble;

    //if we need (cr)servos
    //public CRServo ;

    public Servo StopRing;
    public Servo Grab;

    VisionPipeline vision = new VisionPipeline();

    HardwareMap hardwareMap;

    private double[] GlobalPos;

    public Hardware(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        init();
    }

    public Hardware() {

    }

    public void init() {

/*
        DM1 = hardwareMap.get(DcMotor.class, "DM1");
        DM2 = hardwareMap.get(DcMotor.class, "DM2");
        DM3 = hardwareMap.get(DcMotor.class, "DM3");
        DM4 = hardwareMap.get(DcMotor.class, "DM4");




        En1 = hardwareMap.get(DcMotor.class, "En1");
        En2 = hardwareMap.get(DcMotor.class, "En2");
        En3 = hardwareMap.get(DcMotor.class, "En3");

        L = hardwareMap.get(DcMotor.class, "L");

        I = hardwareMap.get(DcMotor.class, "I");

        C = hardwareMap.get(DcMotor.class, "C");

        Wobble = hardwareMap.get(DcMotor.class, "Wobble");



        //name = this.hardwareMap.get(CRServo.class, "name");

        StopRing = hardwareMap.get(Servo.class, "StopRing");
        Grab = hardwareMap.get(Servo.class, "Grab");
        StopRing.setPosition(0);
        Grab.setPosition(0);

        DM1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DM2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DM3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DM4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        DM2.setDirection(DcMotor.Direction.REVERSE);
        DM4.setDirection(DcMotor.Direction.REVERSE);


        En1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        En2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        En3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //En1.setDirection(DcMotor.Direction.REVERSE);
        //En2.setDirection(DcMotor.Direction.REVERSE);
        //En3.setDirection(DcMotor.Direction.REVERSE);

        Wobble.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        L.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        L.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        I.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        C.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DM1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DM4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        L.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        I.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        C.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Wobble.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



 */

    }

    public void PowerControl(double p1, double p2, double p3, double p4) {

        DM1.setPower(p1);
        DM2.setPower(p2);
        DM3.setPower(p3);
        DM4.setPower(p4);

    }

    public double[] getGlobalPos() {


        while (GlobalPos[2] >= 360) {

            GlobalPos[2] = GlobalPos[2] - 360;

        }


        return GlobalPos;

    }

    public void setGlobalPos(double x, double y, double a) {

        GlobalPos[0] = x;
        GlobalPos[1] = y;
        GlobalPos[2] = a;

    }

    public void startVision(){


        final OpenCvCamera webcam;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        //the one above is for webcam and the one below is for phone back camera
        webcam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        webcam.setPipeline(vision);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
        });
    }

    public int ringAmount() {

        int a = vision.ringAmount();

        return a;

    }

}