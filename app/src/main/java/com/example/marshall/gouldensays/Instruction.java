package com.example.marshall.gouldensays;

/**
 * Created by Marshall on 10/13/2016.
 */

public class Instruction {

    private int numButtons;

    //Using object of type Integer instead of int because Integer is nullable (We may have instructions that don't correspond to a particular button)
    private Integer button;
    private Game game;

    public Instruction(Game game)
    {
        numButtons = 4;
        this.game = game;
        button = getRandButton();
    }

    public Instruction(Game game, int numButtons)
    {
        this.numButtons = numButtons;
        this.game = game;
        button = getRandButton();
    }


    //compares user input to the instruction that was given at this stage
    public boolean inputMatchesInstruction()
    {
        return true;
    }

    public int getButton()
    {
        return button;
    }

    //Choose a random button
    private int getRandButton()
    {
        int butt = game.getRng().nextInt() % 10;
        if (butt < 0)
            butt *= -1;

        if (butt >= numButtons)
            butt = getRandButton();

        return butt;
    }

}
