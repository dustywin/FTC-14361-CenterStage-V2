package com.example.meepmeeptesting.LeftBlue;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class LeftTapeLB {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)

                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(15, 61, Math.toRadians(90)))
                                        .lineToConstantHeading(new Vector2d(25, 25.5))


                                        .addDisplacementMarker(0, () -> {
//
//                                            bot.setVirtualFourBarPosition(virtualFourBarState.intaking,virtualFourBarExtensionState.extending);
//                                            bot.setVirtualFourBarState(virtualFourBarState.intaking);
//
//                                            bot.setClawPosition(clawState.close);
//                                            bot.setClawState(clawState.close);
                                        })

                                        .addDisplacementMarker(15, () -> {
//                                            bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
//                                            bot.setVirtualFourBarState(virtualFourBarState.init);
//
//
//                                            bot.setWristPosition(wristState.sideways);
//                                            bot.setWristState(wristState.sideways);
                                        })

                                        .lineToConstantHeading(new Vector2d(30, 25.5))
                                        .addDisplacementMarker(60, () -> {
//                                 bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
//                                  bot.setVirtualFourBarState(virtualFourBarState.outtaking);
//
//                                   bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                                        })

                                        .lineToConstantHeading(new Vector2d(25, 25.5))

                                        .lineToConstantHeading(new Vector2d(25, 30.5))

                                        .lineToConstantHeading(new Vector2d(35, 30.5))

                                        .lineToConstantHeading(new Vector2d(62, 30.5))
                                        .lineToConstantHeading(new Vector2d(61, 30.5))


                                        .waitSeconds(.25)


                                        .addDisplacementMarker(90, () -> {

//
//                                            bot.setClawState(clawState.open);
//                                            bot.setClawPosition(clawState.open);
//

                                        })
                                        .waitSeconds(1)


                                        .lineToConstantHeading(new Vector2d(55, 52))

                                        .lineToConstantHeading(new Vector2d(68, 52))

                                        .addDisplacementMarker(105,() -> {
//                                            bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
//                                            bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
                                        })
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

