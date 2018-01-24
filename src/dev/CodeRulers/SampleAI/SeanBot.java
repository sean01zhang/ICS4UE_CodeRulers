/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.CodeRulers.SampleAI;

import dev.CodeRulers.entity.Castle;
import dev.CodeRulers.entity.Knight;
import dev.CodeRulers.entity.Peasant;
import dev.CodeRulers.game.CodeRulers;
import dev.CodeRulers.ruler.AbstractRuler;
import dev.CodeRulers.world.World;
import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

/**
 * I am using this class for graphical testing. I also added a game-breaking bug
 *
 * @author Sean Zhang
 */
public class SeanBot extends AbstractRuler {

    //The direction in which peasants claim land and knights attack
    int dir;

    @Override
    public void orderSubjects() {
        Random r = new Random();

        for (Castle castle : this.getCastles()) {

            if (this.getOtherCastles().length != 0 && World.getLandCount(rulerID) > 130) {
                if (CodeRulers.getTurnCount() % 2 == 0) {
                    castle.createKnights();
                } else {
                    castle.createPeasants();
                }

            } else {

                castle.createPeasants();
            }

        }

        if (this.getOtherCastles().length == 0) {

            for (Knight k : this.getKnights()) {
                if (this.getOtherKnights().length != 0) {
                    for (int dirC = 1; dirC < 9; dirC++) {
                        capture(k, dirC);
                    }
                    try {
                        move(k, k.getDirectionTo(this.getOtherKnights()[0].getX(), this.getOtherKnights()[0].getY()));
                    } catch (Exception e) {

                    }
                }
            }

            for (Knight k : this.getKnights()) {
                if (this.getOtherPeasants().length != 0) {
                    move(k, k.getDirectionTo(this.getOtherPeasants()[0].getX(), this.getOtherPeasants()[0].getY()));
                }
            }
        } else {

            for (int k = 0; k < this.getKnights().length && k < 10; k++) {

                int[] distances = new int[World.getAllCastles().length];
                for (int i = 0; i < World.getAllCastles().length; i++) {
                    distances[i] = this.getKnights()[k].getDistanceTo(World.getAllCastles()[i].getX(), World.getAllCastles()[i].getY());
                }
                for (int dirC = 1; dirC < 9; dirC++) {
                    capture(this.getKnights()[k], dirC);
                }

                int smallestCastle = 0;
                int smallestD = 9999999;

                for (int i = 0; i < distances.length; i++) {
                    if (smallestD > distances[i]) {
                        smallestD = distances[i];
                        smallestCastle = i;
                    }
                }
                move(this.getKnights()[k], this.getKnights()[k].getDirectionTo(World.getAllCastles()[smallestCastle].getX(), World.getAllCastles()[smallestCastle].getY()));
            }

            for (int k = 20; k < this.getKnights().length && k < 40; k++) {
                int[] distances = new int[this.getOtherCastles().length];
                for (int i = 0; i < this.getOtherCastles().length; i++) {
                    distances[i] = this.getKnights()[k].getDistanceTo(this.getOtherCastles()[i].getX(), this.getOtherCastles()[i].getY());
                }
                for (int dirC = 1; dirC < 9; dirC++) {
                    capture(this.getKnights()[k], dirC);
                }

                int smallestCastle = 0;
                int smallestD = 9999999;

                for (int i = 0; i < distances.length; i++) {
                    if (smallestD > distances[i]) {
                        smallestD = distances[i];
                        smallestCastle = i;
                    }
                }

                if (this.getOtherCastles().length != 0 && k < this.getKnights().length) {
                    try {
                        move(this.getKnights()[k], this.getKnights()[k].getDirectionTo(this.getOtherCastles()[smallestCastle].getX(), this.getOtherCastles()[smallestCastle].getY()));
                    } catch (Exception e) {

                    }
                }

            }
            for (Knight k : this.getKnights()) {
                if (this.getCastles().length != 0) {
                    move(k, k.getDirectionTo(this.getCastles()[0].getX(), this.getCastles()[0].getY()));
                }
            }

        }

        //===================
        //capture and move
        if (World.getLandCount(rulerID) > 3500) {
            for (Peasant peasant : this.getPeasants()) {
            //peasant.move(findDir(peasant.getClosestUnownedTile(peasant)[0], peasant.getClosestUnownedTile(peasant)[1]));

                //Search for uncaptured tile around the peasant
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        //Stop searching if the Land Tile is outside of the bounds
                        if (!(peasant.getX() + x <= 63 && peasant.getX() + x >= 0 && peasant.getY() + y >= 0 && peasant.getY() + y <= 63)) {
                            break;
                        }

                        //If the land owner is not ours, then move onto it
                        if (World.getLandOwner(peasant.getX() + x, peasant.getY() + y) != rulerID) {
                            this.move(peasant, findDir(x, y));
                        }
                    }
                }

                //If the peasant can still move, then move towards the bottom right
                if (peasant.hasAction()) {
                    this.move(peasant, (int) (Math.random() * 8));
                }
            }
        } else {
            //get the list of my peasants
            Peasant[] myP = getPeasants();
            //if the turn number is a multiple of 25
            if (CodeRulers.getTurnCount() % 25 == 0) {
                //pick a new direction to move in
                chooseDir();
                //if it is the turn before the 25th
            } else if (CodeRulers.getTurnCount() % 25 == 24) {
                //move one space north
                dir = 1;
            }
            //for every one of my peasants
            for (Peasant p : myP) {
                //move them in that direction
                move(p, dir);
            }

        }
    }

    private void chooseDir() {
        //get the list of peasants from the ruler
        Peasant[] myPeasants = getPeasants();
        //represents the net position of the peasants, + is to the right, - to the left
        int netX = 0;
        //represents the net position of peasants, + is below, - is above
        int netY = 0;
        //for every one of my peasant
        for (Peasant p : myPeasants) {
            //if they are to the right
            if (p.getX() >= 32) {
                //increment netX
                netX++;
            } else {
                //decrement netX
                netX--;
            }
            //if they are to the bottom
            if (p.getY() >= 32) {
                //increment netY
                netY++;
                //otherwise
            } else {
                //decrement netY
                netY--;
            }
        }
        //if the peasants are to the left
        if (netX <= 0) {
            //set their direction to the right
            dir = 3;
            //otherwise
        } else {
            //set their direction to the left
            dir = 7;
        }
        //if the peasants are below
        if (netY >= 0) {
            //move them diagonally upwards
            if (dir == 7) {
                dir = 8;
            }
            if (dir == 3) {
                dir = 2;
            }
            //if the peasants are above
        } else if (netY < 0) {
            //move them diagonally downwards
            if (dir == 7) {
                dir = 6;
            }
            if (dir == 3) {
                dir = 4;
            }
        }
    }

    private int findDir(int x, int y) {
        //Calculates the direction of the tile
        double angle = Math.toDegrees(Math.asin(-y / Math.sqrt(x * x + y * y))) + 360;

        if (x < 0) {
            angle = 180 + 360 - angle;
        }
        angle %= 360;

        return (11 - (int) Math.round(angle / 45)) % 8;
    }

    @Override
    public void initialize() {
        //Message telling the user that this object has been intialized.
        System.out.println("Ruler Sean Zhang been initialized");

        //this sets the profileURL of this AI. All you have to do is to provide
        //an internet link to the image.
        profileURL = ("https://yrdsb.edsby.com/core/nodedl/4692550-13?nodepic=true&field=file&xds=fileThumbnail&size=220,220");

        //this is the preferred color for my AI. This color will be the main 
        //color scheme displayed in the GUI ` for this AI.
        setColor(new Color(139, 91, 183, 178));
        setColor(Color.orange);
    }

    @Override
    public String getSchoolName() {
        return "Newmarket High School";
    }

    @Override
    public String getRulerName() {
        return "Sean Zhang";
    }

}
