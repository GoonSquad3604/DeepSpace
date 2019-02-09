package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.vision.Limelight;

public class CmdChangePipeline implements AutonCommand
{

    private Limelight limelight;
    public Number pipeline;
    public CmdChangePipeline(Number iPipeline, Limelight iLimelight)
    {
        limelight = iLimelight;
        pipeline = iPipeline;
    }
	@Override
	public boolean isFinished() {
        limelight.setPipeline(pipeline);
		return true;
	}

	@Override
	public void runTask() {
		
	}

	@Override
	public double getStatus() {
		return 0;
	}

	@Override
	public void init() {
		
	}

}

