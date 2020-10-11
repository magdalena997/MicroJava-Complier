package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormalParamDeclB;
import rs.ac.bg.etf.pp1.ast.FormalParamDeclsB;
import rs.ac.bg.etf.pp1.ast.VarDecListI;
import rs.ac.bg.etf.pp1.ast.BrVarDecListI;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;


public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
	
		public void visit(FormalParamDeclsB formParamDecl){
			count++;
		}
		public void visit(FormalParamDeclB formParamDecl){
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarDecListI varDecl){
			count++;
		}
		public void visit(BrVarDecListI varDecl){
			count++;
		}
	}
}
