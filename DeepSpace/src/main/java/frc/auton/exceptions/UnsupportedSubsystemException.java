package frc.auton.exceptions;

public class UnsupportedSubsystemException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public UnsupportedSubsystemException(Object subsystem)
    {
        super();
        if(subsystem != null)
        {
            System.err.println("UNKNOWN SUBSYSTEM " + subsystem.getClass().getName());
        }
    }
}
