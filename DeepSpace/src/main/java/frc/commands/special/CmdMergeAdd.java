package frc.commands.special;

import java.util.ArrayList;
import frc.commands.AutonCommand;

public class CmdMergeAdd implements AutonCommand
{

    //A command that runs several commands at the same time.
    private ArrayList<AutonCommand> commands;
    
    public CmdMergeAdd(AutonCommand... iCommands)
    {
        commands = new ArrayList<>();
        for(AutonCommand cmd : iCommands)
        {
            commands.add(cmd);
        }

    }
    
    @Override
    public boolean isFinished() {
        //Will return true if ALL of the merged commands are true. Else, returns false.
        boolean finished = true;
        for(AutonCommand cmd : commands)
        {
            if(!cmd.isFinished())
            {
                finished = false;   
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

    //Adds command to merge while other command is running
    public void addCommand(AutonCommand cmd)
    {
        commands.add(cmd);
        commands.get(commands.size() - 1).init();
    }

}