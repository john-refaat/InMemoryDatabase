package demo.memory.enums;

public enum OrderEnum {
	ASC(1), DESC(-1);
	
	private Integer factor;
	
	private OrderEnum(int i){
		this.factor = i;
	}
	
	public int getFactor() {
		return factor;
	}
}
