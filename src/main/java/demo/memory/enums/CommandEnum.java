package demo.memory.enums;

public enum CommandEnum {
	ADD("add"), UPDATE("update"), DELETE("del"), PRINT("print"), PRINTALL("printall"), QUIT("quit");
	private String command;
	private CommandEnum(String command) {
		this.command = command;
	}
	public String getCommand(){
		return command;
	}
	public static CommandEnum getEnum(String command) {
		for(CommandEnum e: values()) {
			if(e.getCommand().equals(command)) {
				return e;
			}
		}
		return null;
	}
}
