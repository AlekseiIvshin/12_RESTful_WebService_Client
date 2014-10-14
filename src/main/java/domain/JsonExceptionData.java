package domain;

public class JsonExceptionData {

	private int status;
	private String exceptionClass;
	private String exceptionMessage;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
