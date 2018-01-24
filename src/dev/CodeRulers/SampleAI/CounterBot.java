/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.CodeRulers.SampleAI;

import dev.CodeRulers.ruler.*;
import dev.CodeRulers.entity.Castle;
import dev.CodeRulers.entity.Knight;
import dev.CodeRulers.entity.Peasant;
import dev.CodeRulers.game.CodeRulers;

import dev.CodeRulers.world.World;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Kaiyi Zhu
 */
public class CounterBot extends AbstractRuler {

    Random r = new Random();

    @Override
    public void initialize() {
        profileURL = ("https://i.ytimg.com/vi/6lYNF3flmFs/hqdefault.jpg");
        setColor(new Color(139, 69, 19));
    }

    @Override
    public void orderSubjects() {
        //Move peasants in random order
        for (int i = 0; i < 10000; i++) {
            for (Peasant peasant : this.getPeasants()) {
                int randomPeasantMove = r.nextInt(2);
                if (randomPeasantMove == 1) {
                    int peasantDir = r.nextInt(8) + 1;
                    this.move(peasant, peasantDir);
                } else {
                    //Does nothing so the peasant stays in his original land
                }
            }
        }
        for (Castle castle : this.getCastles()) {
            castle.createKnights();
        }

        for (Knight k : this.getKnights()) {
            if (this.getCastles().length == 0) {

                for (int dirC = 1; dirC < 9; dirC++) {
                    capture(k, dirC);
                }
            } else {
                move(k, 0);
            }
        }

    }

    @Override
    public String getSchoolName() {
        return "Noes Da Wae";
    }

    @Override
    public String getRulerName() {
        return "MemeBot";
    }

}
