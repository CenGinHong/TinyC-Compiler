//定义变量符号表的信息
public class VarInfo {

	private String type;
	private String name;
	private int column;
	private int line;
	private int loc;
	/**
	 * 数组变量的大小
	 */
	private Integer size;

	private Boolean global;


	public VarInfo(String type, String name){
		super();
		this.type = type;
		this.name = name;
	}

	public VarInfo(String type, Token token){
		super();
		this.type = type;
		this.name = token.image;
		this.line = token.endLine;
		this.column = token.endColumn;
	}

	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setLine(int line){
		this.line = line;
	}
	
	public int getLine(){
		return this.line;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public int getColum(){
		return this.column;
	}

	@Override
	public String toString(){
		return ":\t(" + this.type + ",\t" + this.name + ",\t" + this.line + ",\t" + this.column + "\t)\n";
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Boolean getGlobal() {
		return global;
	}

	public void setGlobal(Boolean global) {
		this.global = global;
	}
}
