package frc.auton;

public class TestCommand implements AutonCommand
{

    /*This command is just a blank command. As such, this is the only comment. 
    Comments about the command interface are in the command interface.*/
    @Override
    public boolean isFinished() 
    {
        return false;
    }
    @Override
    public void runTask() 
    {
        
    }
    @Override
    public double getStatus() 
    {
        return 0;
    }
}