package com.example.meepmeeptesting.LeftRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class LeftTapeLR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -61, Math.toRadians(270)))
                                //Moving onto left tape
                                .lineToConstantHeading(new Vector2d(-50, -61))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(-50, -41.5))
                                .waitSeconds(.5)
                                //Lining up with the gate
                                .lineToConstantHeading(new Vector2d(-35, -41.5))
                                .lineToConstantHeading(new Vector2d(-35, -14.5))

                                .lineToConstantHeading(new Vector2d(43, -14.5))
                                .turn(Math.toRadians(-90 - 2))
                                .waitSeconds(.5)
                                //go through gate

                                //go to backboard
                                .lineToConstantHeading(new Vector2d(43, -38))

                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }

}
