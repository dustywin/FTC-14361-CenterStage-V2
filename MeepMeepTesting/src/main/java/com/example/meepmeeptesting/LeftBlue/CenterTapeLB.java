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

                                .lineToConstantHeading(new Vector2d(15, 31))
                                .addDisplacementMarker(0, () -> {
//                                    bot.setVirtualFourBarPosition(virtualFourBarState.intaking,virtualFourBarExtensionState.extending);
//                                    bot.setVirtualFourBarState(virtualFourBarState.intaking);
//
//                                    bot.setClawPosition(clawState.close);
//                                    bot.setClawState(clawState.close);
                                })
                                .waitSeconds(.25)

                                .addDisplacementMarker(15, () -> {
//                                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
//                                    bot.setVirtualFourBarState(virtualFourBarState.init);
//
//                                    bot.setWristPosition(wristState.sideways);
//                                    bot.setWristState(wristState.sideways);
                                })
                                .waitSeconds(.5)

                                //going to the middle of all of the tape
                                .lineToConstantHeading(new Vector2d(15, 33))
                                //going to backboard
                                .addDisplacementMarker(40, () -> {
//                                 bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
//                                  bot.setVirtualFourBarState(virtualFourBarState.outtaking);
//
//                                   bot.setOuttakeSlidePosition(outtakeSlidesState.LOWOUT, extensionState.extending);
                                })
                                .waitSeconds(.5)
                                .addDisplacementMarker(65, () -> {
//                                   bot.setClawState(clawState.open);
//                                   bot.setClawPosition(clawState.open);
                                })

                                .lineToLinearHeading(new Pose2d(49, 36, Math.toRadians(180)))
                                .waitSeconds(1)
                                //going to park
                                .lineToConstantHeading(new Vector2d(48, 61))
                                //finish park
                                .lineToConstantHeading(new Vector2d(55, 61))

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
