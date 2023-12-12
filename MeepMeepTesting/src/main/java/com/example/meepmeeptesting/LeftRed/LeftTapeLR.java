package com.example.meepmeeptesting.LeftRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class LeftTapeLR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -60, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-47, -43))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(-47, -50))
                                .lineToConstantHeading(new Vector2d(-34, -50))
                                .waitSeconds(.5)
                                .lineToLinearHeading(new Pose2d(-34, -12, Math.toRadians(180)))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(37, -12))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(37, -29))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(49.5, -29))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(37, -29))
                                .lineToConstantHeading(new Vector2d(37, -12))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(57, -12))


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
