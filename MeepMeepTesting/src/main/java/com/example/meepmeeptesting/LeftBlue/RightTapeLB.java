package com.example.meepmeeptesting.LeftBlue;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RightTapeLB {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                                        .lineToLinearHeading(new Pose2d(12, 31, Math.toRadians(0)))
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(49, 36, Math.toRadians(180)))
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(47, 36, Math.toRadians(180)))
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(47, 60, Math.toRadians(180)))
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(60, 60, Math.toRadians(180)))

                                        .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
