package com.example.meepmeeptesting.LeftBlue;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class CenterTapeLB {
  public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                                //pushing onto center tape
                                .lineToConstantHeading(new Vector2d(15, 34))
                                .waitSeconds(.5)
                                //going to the middle of all of the tape
                                .lineToConstantHeading(new Vector2d(15, 45))
                                .waitSeconds(.5)

                                //going to backboard
                                .lineToLinearHeading(new Pose2d(40, 45, Math.toRadians(180)))
                                .waitSeconds(.5)


                                .lineToConstantHeading(new Vector2d(40, 35))
                                .waitSeconds(.5)

                                .lineToConstantHeading(new Vector2d(49, 35))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(40, 35))


                                .lineToConstantHeading(new Vector2d(40, 59))
                                //finish park
                                .lineToConstantHeading(new Vector2d(57, 59))

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
