package com.example.meepmeeptesting.LeftRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class RightTapeLR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -60, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-35, -45))
                                .lineToLinearHeading(new Pose2d(-31, -38, Math.toRadians(230)))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(-42, -45))
                                .lineToLinearHeading(new Pose2d(-42, -11.5, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(34, -11.5))
                                .lineToConstantHeading(new Vector2d(34, -42.5))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(47.5, -42.5))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(34, -43))
                                .lineToConstantHeading(new Vector2d(34, -13))
                                .lineToConstantHeading(new Vector2d(59, -13))
                                .waitSeconds(1)






                                .build()

                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }

}
