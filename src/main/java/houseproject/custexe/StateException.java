package houseproject.custexe;

public class StateException extends RuntimeException
{
	private String message;
	
	public StateException(String message)
	{
		this.message=message;
	}
	public String getErrorMsg()
	{
		return message;
	}

}
