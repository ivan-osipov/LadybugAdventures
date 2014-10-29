package technologyOfProgramming.zvenigorodskyTask.entities;

public enum ComandType {
	MOVE, JUMP, PUSH, CYCLE, OCCUPY;
	private String parameter;
	
	public void setParameter(String parameter){
		this.parameter = parameter;
	}
	public String getParameter(){
		return parameter;
	}
}
