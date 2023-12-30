package com.example.meepmeeptesting.RightRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class RightTapeRR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(9.5, -61, Math.toRadians(270)))
                                //Moving away from wall
                                .lineToConstantHeading(new Vector2d(9.5, -55))
                                .waitSeconds(1)
                                //Moving behind the right tape
                                .lineToConstantHeading(new Vector2d(24, -55))
                                .waitSeconds(1)
                                //Moving onto the right tape
                                .lineToConstantHeading(new Vector2d(24, -43))
                                .waitSeconds(2)
                                //Moving back behind the right tape
                                .lineToConstantHeading(new Vector2d(24, -55))
                                .waitSeconds(1)
                                //Moving towards backboard zone
                                .lineToConstantHeading(new Vector2d(36, -55))
                                .waitSeconds(1)
                                //Moving to backboard
                                .lineToLinearHeading(new Pose2d(51, -40, Math.toRadians(180)))
                                .waitSeconds(.5)
                                //Moving away from backboard
                                .lineToConstantHeading(new Vector2d(48.5, -40))
                                .waitSeconds(1)
                                //Moving towards park position
                                .lineToConstantHeading(new Vector2d(40, -40))
                                .waitSeconds(1)
                                //Line up to park position
                                .lineToLinearHeading(new Pose2d(40, -57, Math.toRadians(90)))
                                .waitSeconds(1)
                                //Parking
                                .lineToConstantHeading(new Vector2d(46, -57))

                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }

}
