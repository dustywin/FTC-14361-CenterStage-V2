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
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -61, Math.toRadians(270)))
                                //Moving onto left tape
                                .lineToConstantHeading(new Vector2d(-45.5, -41.5))
                                .waitSeconds(2)
                                //Moving behind left tape
                                .lineToConstantHeading(new Vector2d(-45.5, -50))
                                .waitSeconds(.5)
                                //Moving behind center tape
                                .lineToConstantHeading(new Vector2d(-34, -50))
                                .waitSeconds(.5)
                                //Lining up with the gate
                                .lineToConstantHeading(new Vector2d(-34, -10.5))
                                .waitSeconds(.5)
                                //Passing through gate
                                .lineToLinearHeading(new Pose2d(45, -10.5,Math.toRadians(180)))
                                .waitSeconds(.5)
                                //Lining up with the left side of the backboard
                                .lineToConstantHeading(new Vector2d(45, -28))
                                .waitSeconds(.5)
                                //Moving to backboard
                                .lineToConstantHeading(new Vector2d(51, -28))
                                .waitSeconds(2)
                                //Moving away from backboard
                                .lineToConstantHeading(new Vector2d(45, -28))
                                .waitSeconds(.1)
                                //Lining up with parking position
                                .lineToLinearHeading(new Pose2d(45, -11.5, Math.toRadians(90)))
                                .waitSeconds(1)
                                //Parking
                                .lineToConstantHeading(new Vector2d(59, -11.5))
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
