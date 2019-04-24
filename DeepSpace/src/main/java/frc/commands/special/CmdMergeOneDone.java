package frc.commands.special;

import frc.commands.AutonCommand;

public class CmdMergeOneDone implements AutonCommand
{

    //A command that runs several commands at the same time.
    private AutonCommand[] commands;
    
    public CmdMergeOneDone(AutonCommand... iCommands)
    {
        commands = iCommands;
    }
    
    @Override
    public boolean isFinished() {
        //Will return true if ONE of the merged commands are true. Else, returns false.
        boolean finished = false;
        for(AutonCommand cmd : commands)
        {
            if(cmd.isFinished())
            {
                finished = true;   
            }
        }
        return finished;
    }

    @Override
    public void runTask() {
        //Runs the tasks of all commands.
        for(AutonCommand cmd : commands)
        {
            if(!cmd.isFinished())
            {
                cmd.runTask();
            }
        }

    }

    @Override
    public double getStatus() {
        for(AutonCommand cmd : commands)
        {
            if(cmd.getStatus() != 0)
            {
                return cmd.getStatus(); //Returns the first actual status it finds.
            }
        }
        return 0;   //Else return 0.
    }

    @Override
    public void init() {
        //Runs init for all commands.
        for(AutonCommand cmd : commands)
        {
            cmd.init();
        }
    }
    //Ends all merged commands.
    @Override
    public void end()
    {
        for(AutonCommand cmd : commands)
        {
            cmd.end();
        }
    }

}