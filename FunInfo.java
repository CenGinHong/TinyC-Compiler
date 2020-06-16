import java.util.List;

//定义变量符号表的信息
public class FunInfo {


	private String returnType;
	private String name;
	private int column;
	private int line;
	private List<VarInfo> params;
	private int loc;

	public FunInfo(String returnType, String name){
		super();
		this.returnType = returnType;
		this.name = name;
	}

	public FunInfo(String returnType, Token token, List<VarInfo> params){
		super();
		this.returnType = returnType;
		this.name = token.image;
		this.line = token.endLine;
		this.column = token.endColumn;
		this.params = params;
	}

	public FunInfo(String returnType, Token token){
		super();
		this.returnType = returnType;
		this.name = token.image;
		this.line = token.endLine;
		this.column = token.endColumn;
	}
	
	public String getReturnType(){
		return this.returnType;
	}
	
	public void setReturnType(String returnType){
		this.returnType = returnType;
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
		return ":\t(" + this.returnType + ",\t" + this.name + ",\t" + this.line + ",\t" + this.column + "\t)\n";
	}

	public List<VarInfo> getParams() {
		return params;
	}

	public void setParams(List<VarInfo> params) {
		this.params = params;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}
}
