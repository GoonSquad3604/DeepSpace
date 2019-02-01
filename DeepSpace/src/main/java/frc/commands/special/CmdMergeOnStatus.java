package frc.commands.special;

import frc.commands.AutonCommand;

public class CmdMergeOnStatus implements AutonCommand
{

    //A command that starts running another command. Once the first command's status is equal to the desired status, it starts the other commands.
    private AutonCommand[] commands;
    private double desiredStatus;
    private boolean runningAll;
    public CmdMergeOnStatus(double desiredStatus,AutonCommand... commands)
    {
        this.commands = commands;
        this.desiredStatus = desiredStatus;
        runningAll = false;
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
        if(runningAll)
        {
            //Runs the tasks of all commands.
            for(AutonCommand cmd : commands)
            {
                if(!cmd.isFinished())
                {
                    cmd.runTask();
                }
            }
        }
        else
        {
            if(!commands[0].isFinished())
            {
                commands[0].runTask();
            }
            //Starts running all commands when the current status equals the desired status to a precision of a tenth.
            if(commands[0].getStatus() > this.desiredStatus-0.1 && commands[0].getStatus() < this.desiredStatus+0.1)
            {
                runningAll = true;
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

}