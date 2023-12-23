package com.example.meepmeeptesting.RightBlue;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class RightTapeRB {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 61, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(-46, 43))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(-46, 50))
                                .lineToConstantHeading(new Vector2d(-34, 50))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(-34, 11.5))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(15, 11.5))
                                .waitSeconds(.5)
                                .lineToLinearHeading(new Pose2d(45, 11.5, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(45, 28))
                                .lineToConstantHeading(new Vector2d(49, 28))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(45, 28))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(45, 11.5))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(59, 11.5))
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
