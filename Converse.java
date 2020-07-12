import java.io.*;

public class Converse implements Serializable {
	private String statement="";
	private String response="";
	private int frequency=0;
	private static final long serialVersionUID = 1L;

	public Converse () {
	}

	public Converse( String statement0, String response0, int frequency0 ) {
		statement = statement0;
		response = response0;
		frequency = frequency0;
	}
	
	public String getStatement() {
		return statement;
	}
	public String getResponse() {
		return response;
	}
	public int getFreq() {
		return frequency;
	}
	public void increaseFreq() {
		frequency++;
	} 

}