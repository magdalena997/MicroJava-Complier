package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

//import rs.ac.bg.etf.pp1.Code;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	

	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(PrintStmt printS) {
		if(printS.getExpr().struct == Tab.intType ){
			Code.loadConst(5);
			Code.put(Code.print);
		}else{ 
			if(printS.getExpr().struct == Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
			}
			else {
				Code.loadConst(5);
				Code.put(Code.print);
			}
		}
	}
	
	public void visit(PrintStmtC printS) {
		Code.loadConst(printS.getN2());
		if(printS.getExpr().struct == Tab.intType){
			Code.put(Code.print);
		}else{
			if(printS.getExpr().struct == Tab.charType)
			Code.put(Code.bprint);
			else 
				Code.put(Code.print);
		}
	}
	
	public void visit(Reads readS) {
		if(readS.getDesignator().obj.getType() == Tab.intType){
			Code.put(Code.read);
			Code.store(readS.getDesignator().obj);
		}else{
			Code.put(Code.bread);
			Code.store(readS.getDesignator().obj);
		}
	}
	
	public void visit(FactorNumConst numConst) {
		Obj con = Tab.insert(Obj.Con, "$", numConst.struct);
		con.setLevel(0);
		con.setAdr(numConst.getN1());
		
		Code.load(con);
	}
	
	public void visit(FactorCharConst charConst) {
		Obj con = Tab.insert(Obj.Con, "$", charConst.struct);
		con.setLevel(0);
		con.setAdr(charConst.getC1());
		
		Code.load(con);
	}
	
	public void visit(FactorBoolConst boolConst) {
		Obj con = Tab.insert(Obj.Con, "$", boolConst.struct);
		con.setLevel(0);
		con.setAdr(boolConst.getB1().equals("true") ? 1 : 0);
		
		Code.load(con);
}
	
	public void visit(MethodDeclTypeT typeMeth) {
		typeMeth.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = typeMeth.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDeclVoid voidMeth) {
		
		if("main".equalsIgnoreCase(voidMeth.getMetName())){
			mainPc = Code.pc;
		}
		voidMeth.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = voidMeth.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDeclTF methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStatementss designStatA) {
		Code.store(designStatA.getDesignator().obj);
	}
	
	public void visit(DesignatorAddop desAddop) {
		Obj obj = desAddop.getDesignator().obj;
		 
		if(desAddop.getDesignator().obj.getKind() != Obj.Elem) {
		 Code.load(obj);
		 Code.put(Code.dup_x1);
		 Code.put(Code.pop);
		}
		 
		if(desAddop.getAddopRight() instanceof AddopADD)
		Code.put(Code.add);
		else 
			Code.put(Code.sub);
		
	//	Code.put(Code.dup);
		Code.store(obj);
	//	Code.put(Code.pop);
	}
	
	public void visit(DesignatorMulop desMulop) {
		Obj obj = desMulop.getDesignator().obj;
		

		if(desMulop.getDesignator().obj.getKind() != Obj.Elem) {
			 Code.load(obj);
			 Code.put(Code.dup_x1);
			 Code.put(Code.pop);
			}

		 
		 if(desMulop.getMulopRight() instanceof MulopRightMUL)
			Code.put(Code.mul);
		else 
			if(desMulop.getMulopRight() instanceof MulopRightDIV)
				Code.put(Code.div);
			else 
				Code.put(Code.rem);
		
	//	Code.put(Code.dup);
		Code.store(obj);
	//	Code.put(Code.pop);
	}
	
	 public void visit(ExprA desAddop) {
		Obj obj = desAddop.getDesignator().obj;
			 
		if(desAddop.getDesignator().obj.getKind() != Obj.Elem) {
		 Code.load(obj);
		 Code.put(Code.dup_x1);
		 Code.put(Code.pop);
		}
			 
		if(desAddop.getAddopRight() instanceof AddopADD)
		Code.put(Code.add);
		else 
			Code.put(Code.sub);
			
		if(desAddop.getDesignator().obj.getKind() != Obj.Elem) 
			Code.put(Code.dup);
			else
			Code.put(Code.dup_x2);
		Code.store(obj);
	//	Code.put(Code.pop);
	 }
	 
	 public void visit(TermMulop desAddop) {
		Obj obj = desAddop.getDesignator().obj;
			 
		if(desAddop.getDesignator().obj.getKind() != Obj.Elem) {
		 Code.load(obj);
		 Code.put(Code.dup_x1);
		 Code.put(Code.pop);
		}
			 
		 if(desAddop.getMulopRight() instanceof MulopRightMUL)
			Code.put(Code.mul);
		else 
			if(desAddop.getMulopRight() instanceof MulopRightDIV)
				Code.put(Code.div);
			else 
				Code.put(Code.rem);
		
			
		if(desAddop.getDesignator().obj.getKind() != Obj.Elem) 
			Code.put(Code.dup);
			else
			Code.put(Code.dup_x2);
		Code.store(obj);
	//	Code.put(Code.pop);
	 }
	
 /*	//???
	// Factor = Designator
	public void visit(DesignatorFactor des) {
		Code.load(des.getDesignator().obj);
	}
	
	// Designator = Designator [Expr]
	public void visit(DesignE des) {
		Code.load(des.getDesignator().obj);

	}
 */
 	//Designator = Ident
 
	public void visit(DesignatorIdent identDesignator) {
		SyntaxNode parent = identDesignator.getParent();
	//	if(parent.getClass() == DesignatorAddop.class)
	//		System.out.print("Ispisi roditelja" +  parent.getClass().getName());
		
	//	System.out.print("Ispisi roditelja" +  parent.getParent().getParent().getParent().getClass().getName());
		
		if(DesignatorStatementss.class != parent.getClass() && FuncCall.class != parent.getClass()  && 
				DesignatorStatementsL.class != parent.getClass() && DesignatorStatementsI.class != parent.getClass() &&
				DesignatorStatementsD.class != parent.getClass() && DesignatorAddop.class != parent.getClass()  && DesignatorMulop.class != parent.getClass() &&
				ExprA.class != parent.getClass() && TermMulop.class != parent.getClass()){
			Code.load(identDesignator.obj);
	} 

	}
	// Designator = Designator []
	public void visit(DesignE identDesignator) {
		SyntaxNode parent = identDesignator.getParent();
		if(parent.getClass() == DesignatorAddop.class || parent.getClass() == DesignatorMulop.class) {
		System.out.print("Ispisi roditelja" +  parent.getClass().getName());
		Code.put(Code.dup2);
		Code.load(identDesignator.obj);
		}
		if( parent.getClass() == DesignatorStatementsI.class || parent.getClass() == DesignatorStatementsD.class) {
			Code.put(Code.dup2);
		}
		if(parent.getClass() == ExprA.class || parent.getClass() == TermMulop.class) {
		System.out.print("Ispisi roditelja" +  parent.getClass().getName());
		Code.put(Code.dup2);
		Code.load(identDesignator.obj);
		}
		if(DesignatorStatementss.class != parent.getClass() && FuncCall.class != parent.getClass() &&
				DesignatorStatementsL.class != parent.getClass() && DesignatorStatementsI.class != parent.getClass() && 
						DesignatorStatementsD.class != parent.getClass() && DesignatorAddop.class != parent.getClass() && DesignatorMulop.class != parent.getClass() &&
						ExprA.class != parent.getClass() && TermMulop.class != parent.getClass()){
			Code.load(identDesignator.obj);
	}
	}  
	
	
	public void visit(FuncCall fcall) {
		Obj functionObj = fcall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
	}
	
	//ovo ne znam sa parametrima
	public void visit(DesignatorActPars fcall) {
		Obj functionObj = fcall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
	}
	
	public void visit(DesignatorStatementsL fcall) {
		Obj functionObj = fcall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(fcall.getDesignator().obj.getType() != Tab.noType){
			Code.put(Code.pop);
		}
	}
	
	//ovo ne znam sa parametrima
	public void visit(DesignatorStatementsA fcall) {
		Obj functionObj = fcall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(fcall.getDesignator().obj.getType() != Tab.noType){
			Code.put(Code.pop);
		}
	}
	
	//Statement = Return expr ;
	public void visit(ReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	//Statement = Return ;
	public void visit(ReturnNoExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	//Expr = -Term
	public void visit(ExprMA minusExpr) {
		Code.put(Code.neg);
	}
	
	//Expr = Expr Addop Term
	public void visit(ExprAddop addopExpr) {
		AddopLeft addopE = addopExpr.getAddopLeft();
		
		if(addopE instanceof AddopPLUS) 
				Code.put(Code.add); //+
		else 
				Code.put(Code.sub); //-
		
	}
	
	//Term = Term Mulop Factor
	public void visit(TermM mulopTerm) {
		MulopLeft mulopT = mulopTerm.getMulopLeft();
		
			if(mulopT instanceof MulopMUL)
				Code.put(Code.mul);
			else 
				if(mulopT instanceof MulopDIV)
					Code.put(Code.div);
				else 
					Code.put(Code.rem);
		

	}
	
	 public void visit(FactorNewExpr factorNew) {
	        Code.put(Code.newarray);
	        if ( factorNew.getType().struct == Tab.charType ) 
				Code.put(0); 
	        else 
				Code.put(1);
	 }
	
	 public void visit(DesignatorStatementsI desInc) {
		 Obj inc = desInc.getDesignator().obj;
		 Code.load(inc);
		 Code.loadConst(1);
		 Code.put(Code.add);
		 Code.store(inc);
	 }
	 
	 public void visit(DesignatorStatementsD desDec) {
		 Obj dec = desDec.getDesignator().obj;
		 Code.load(dec);
		 Code.loadConst(-1);
		 Code.put(Code.add);
		 Code.store(dec);
	 }
	 

	
}
