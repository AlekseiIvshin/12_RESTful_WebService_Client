package reports;

public class ReportException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6646780954349913287L;

	public ReportException(Throwable e){
		super(e);
	}

	public ReportException(String message) {
		super(message);
	}
}
