package com.example.meepmeeptesting.RightRed;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class CenterTapeRR {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(15, -61, Math.toRadians(270)))
                                .addDisplacementMarker(0, () -> {

                                })
                                .lineToConstantHeading(new Vector2d(15, -38.75))
                                .addDisplacementMarker(5, () -> {

                                })


                                .addDisplacementMarker(15, () -> {

                                })

                                .addDisplacementMarker(22, () -> {

                                })
                                .waitSeconds(2)
                                .lineToLinearHeading(new Pose2d(60, -40, Math.toRadians(180)))
                                .waitSeconds(1)
                                .addDisplacementMarker(23, () -> {

                                })
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(48, -36))

                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(48, -58))
                                .waitSeconds(.5)
                                .lineToConstantHeading(new Vector2d(65, -58))



                                .build()

                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }

}
