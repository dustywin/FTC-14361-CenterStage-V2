package com.example.meepmeeptesting.LeftBlue;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class LeftTapeLB {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)

                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                                        .lineToConstantHeading(new Vector2d(15, 55))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(22.5, 55))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(20.5, 43))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(22.5, 55))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(40, 55))
                                        .waitSeconds(.5)
                                        .lineToLinearHeading(new Pose2d(40, 41, Math.toRadians(180)))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(48.5, 41))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(40, 41))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(40, 59))
                                        .waitSeconds(.5)
                                        .lineToConstantHeading(new Vector2d(58, 59))
                                        .waitSeconds(.5)

                                        .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

