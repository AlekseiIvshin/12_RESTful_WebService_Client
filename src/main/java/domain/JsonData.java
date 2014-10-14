package domain;

public class JsonData<T> {
	private T data;
	private JsonExceptionData exception;
	

	public T getData() {
		return data;
	}

	public JsonExceptionData getException() {
		return exception;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setException(JsonExceptionData exception) {
		this.exception = exception;
	}
}
