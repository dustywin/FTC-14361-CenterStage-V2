package com.example.meepmeeptesting.RightRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class LeftTapeRR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(9, -61, Math.toRadians(270)))

                                .lineToConstantHeading(new Vector2d(20, -61))
                                ///Moving behind right tape
                                .lineToLinearHeading(new Pose2d(20, -38, Math.toRadians(0)))
                                .waitSeconds(.5)
                                //Moving onto right tape
                                .lineToConstantHeading(new Vector2d(9, -38))
                                .waitSeconds(2)
                                //going to backboard
                                .lineToLinearHeading(new Pose2d(54, -29, Math.toRadians(180)))
                                .waitSeconds(2)
                                //Moving away from backboard
                                .lineToConstantHeading(new Vector2d(40, -29))
                                .waitSeconds(.5)
                                //Moving towards park position
                                .lineToLinearHeading(new Pose2d(40, -57, Math.toRadians(90)))
                                .waitSeconds(.5)
                                //Parking
                                .lineToConstantHeading(new Vector2d(49, -57))

                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }

}
