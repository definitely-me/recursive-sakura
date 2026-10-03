import tree.*;
import core.Drawing;
import core.DrawingFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        Random r = new Random();
        ArrayList<Drawing> drawings = new ArrayList<>();
        long configBackSeed = r.nextLong();
        long configFrontSeed = r.nextLong();
        System.out.println("Back " + configBackSeed + " Top " + configFrontSeed);

        drawings.add(new SakuraFlower(100, 100).setScale(2));
        drawings.add(new SakuraLeaf(170, 130).setScale(3));
        drawings.add(new BranchingFractalDrawing(1980 / 2, 1100, TreeConfigurations.configBack,6415375597118579117L).setScale(1));
        drawings.add(new BranchingFractalDrawing(1980 / 2, 1100, TreeConfigurations.configFront, 2551853727649545126L).setScale(1));
        DrawingFrame frame = new DrawingFrame(drawings);

    }
}
//Back 6415375597118579117 Top 2551853727649545126
//Back -2239699638918661034 Top -832931558287383396
//Back 4126536209826639282 Top -632623681048946501