package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RightTape {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                                        //pushing onto right tape
                                        .lineToConstantHeading(new Vector2d(15, 33))
                                        //pushing onto right tape and turning
                                        .lineToLinearHeading(new Pose2d(15, 32, Math.toRadians(180)))
                                        //backing up
                                        .lineToConstantHeading(new Vector2d(12, 32))
                                        //moving to center to move out of way
                                        .lineToConstantHeading(new Vector2d(12, 10))
                                        //going to backboard
                                        .lineToConstantHeading(new Vector2d(49, 25))
                                        //going to park
                                        .lineToConstantHeading(new Vector2d(48, 61))
                                        //finish park
                                        .lineToConstantHeading(new Vector2d(55, 61))



//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
                                        .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
