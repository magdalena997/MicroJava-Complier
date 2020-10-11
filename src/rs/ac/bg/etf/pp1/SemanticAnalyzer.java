package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import sun.management.counter.Variability;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	
	boolean errorDetected = false;
	
	Struct currType = null;
	
	Obj currentMethod = null;
	
	boolean returnFound = false;
	
	boolean mainParams = false;
	boolean proveraConst = false;
	
	public static final Struct boolType = new Struct(5);
	
	Logger log = Logger.getLogger(getClass());
	int nVars;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
    
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) {
    	if(!(Tab.find("main").getType() == Tab.noType && Tab.find("main").getKind() == Obj.Meth))
    		report_error("Nije definisana funkcija void funkcija main", null);
    	
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    
	public void visit(VarDecListI varDeclIdent){
		varDeclCount++;
	// 	Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct);
		if (Tab.currentScope.findSymbol(varDeclIdent.getVarIdent()) != null) {
			report_error("Na liniji: " + varDeclIdent.getLine() + " Semanticka greska: promenljiva " + varDeclIdent.getVarIdent() + " vec postoji u ovom opsegu", varDeclIdent );
			
		}
		Obj varNode = Tab.insert(Obj.Var, varDeclIdent.getVarIdent(), currType);
		report_info("Var: " + varDeclIdent.getVarIdent() + " tip " + varNode.getKind() , varDeclIdent);
	}
	
	public void visit(BrVarDecListI varDeclIdentBr) {
		varDeclCount++;
		if (Tab.currentScope.findSymbol(varDeclIdentBr.getVarIdenList()) != null) {
			report_error("Na liniji: " + varDeclIdentBr.getLine() + " Semanticka greska: promenljiva niz " + varDeclIdentBr.getVarIdenList() + " vec postoji u ovom opsegu", varDeclIdentBr );	
		}
		
		Obj varNode = Tab.insert(Obj.Var, varDeclIdentBr.getVarIdenList(), new Struct(Struct.Array, currType));
		report_info("Var: " + varDeclIdentBr.getVarIdenList() + " tip " + varNode.getKind() , varDeclIdentBr);
	}
	
	
    public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		if((type.getTypeName()).equals("bool")) {
    			type.struct = boolType;
    			currType = boolType;
    		} else {
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    		currType = Tab.noType; }
    		
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    		currType = type.struct;
    	}
    }
    
    public void visit(ConstDeclPNums constNum) { 
    	if (currType.equals(Tab.intType)) {
    	log.info("Prepoznata numericka konstanta " + constNum.getNameConstN() + " na liniji " + constNum.getLine() );
    	if (Tab.currentScope.findSymbol(constNum.getNameConstN()) != null) {
    		report_error("Na liniji: " + constNum.getLine() + " Semanticka greska: konstanta " + constNum.getNameConstN() + " vec postoji u ovom opsegu", constNum );
    	}
    	Obj constant = Tab.insert(Obj.Con, constNum.getNameConstN(), currType);
    	constant.setAdr(constNum.getNumC());
    	} else {
    		report_error("Greska: nekompatibline konstante " + constNum.getNameConstN() , constNum);
    	}
    	
    }
    
    public void visit(ConstDeclPChar constChar) {
    	if (currType.equals(Tab.charType)) {
    	log.info("Prepoznata char konstanta " + constChar.getNameConstC() + " na liniji " + constChar.getLine() );
    	if (Tab.currentScope.findSymbol(constChar.getNameConstC()) != null) {
    		report_error("Na liniji: " + constChar.getLine() + " Semanticka greska: konstanta " + constChar.getNameConstC() + " vec postoji u ovom opsegu", constChar );
    	}
    	Obj constant = Tab.insert(Obj.Con, constChar.getNameConstC(), currType);
    	constant.setAdr(constChar.getCharC());
    	} else {
    		report_error("Greska: nekompatibline konstante " + constChar.getNameConstC() , constChar);
    	}
    	
    	
    }
    public void visit(ConstDeclPBool constBool) {
    	if (currType.equals(boolType)) {
    	log.info("Prepoznata bool konstanta " + constBool.getNameConstB() + " na liniji " + constBool.getLine() );
    	if (Tab.currentScope.findSymbol(constBool.getNameConstB()) != null) {
    		report_error("Na liniji: " + constBool.getLine() + " Semanticka greska: konstanta " + constBool.getNameConstB() + " vec postoji u ovom opsegu", constBool );
    	}
    	Obj constant = Tab.insert(Obj.Con, constBool.getNameConstB(), currType);
    	constant.setAdr(!constBool.getBoolC().equals("true") ? 0 : 1);
    	} else {
    		report_error("Greska: nekompatibline konstante " + constBool.getNameConstB() , constBool);
    	}
    	
    }
    
    public void visit(FormalParamDeclsB formPars) {
    	if(currentMethod.getName().equals("main")) {
    		report_error("Na liniji: " + formPars.getLine() + " greska, metoda main ne treba da ima formalne parametre", formPars);
    	}
    }
    
    public void visit(FormalParamDeclB formPars) {
    	if(currentMethod.getName().equals("main")) {
    		report_error("Na liniji: " + formPars.getLine() + " greska, metoda main ne treba da ima formalne parametre", formPars);
    	}
    }
    
    public void visit(MethodDeclTypeT methodType) {
    	if (Tab.currentScope.findSymbol(methodType.getMetName()) != null) {
    		report_error("Na liniji: " + methodType.getLine() + " Semanticka greska: funkcija " + methodType.getMetName() + " vec postoji u ovom opsegu", methodType );
    		currentMethod = new Obj(Obj.Meth, methodType.getMetName(), methodType.getType().struct);
    	} else {
    		if(!methodType.getMetName().equals("main")) { 
      	currentMethod = Tab.insert(Obj.Meth, methodType.getMetName(), methodType.getType().struct);
      	methodType.obj = currentMethod;

		report_info("Obradjuje se funkcija " + methodType.getMetName() + " ciji je tip " + methodType.getType().getTypeName(), methodType);
    	} 
    	else {
    		report_error("Funkcija main mora biti deklarisana kao void", methodType );
    		currentMethod = new Obj(Obj.Meth, methodType.getMetName(), methodType.getType().struct);
    	}
    	}
    	Tab.openScope();
    }
    
    public void visit(MethodDeclVoid methodVoid) {
    	if (Tab.currentScope.findSymbol(methodVoid.getMetName()) != null) {
    		report_error("Na liniji: " + methodVoid.getLine() + " Semanticka greska: funkcija " + methodVoid.getMetName() + " vec postoji u ovom opsegu", methodVoid );
    		currentMethod = new Obj(Obj.Meth, methodVoid.getMetName(), Tab.noType);
    	} 
      	currentMethod = Tab.insert(Obj.Meth, methodVoid.getMetName(), Tab.noType);
      	methodVoid.obj = currentMethod;
    
    	Tab.openScope();
		report_info("Obradjuje se funkcija " + methodVoid.getMetName(), methodVoid);
    }
    
    public void visit(MethodDeclTF method) {
    	if(!returnFound && currentMethod.getType() != Tab.noType){
			report_error("Semanticka greska na liniji " + method.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
    	}
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
    	returnFound = false;
    	currentMethod = null;
    }
    
    //kada se designator koristi kao promenljiva u nekom izrazu
    public void visit(DesignatorIdent designatorIdent) {
    	Obj obj = Tab.find(designatorIdent.getDesignatorName());
    	if(obj == Tab.noObj) {
    		report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getDesignatorName()+" nije deklarisano! ", null);
    		designatorIdent.obj = Tab.noObj;
    	}
    	
    	designatorIdent.obj = obj;
    	report_info("Objekat je " + designatorIdent.getDesignatorName() + " tipa  " + designatorIdent.obj.getKind(), designatorIdent);
    }
    
    //Designator = Ident [Expr]
    public void visit(DesignE designatorIdent) {
    	Obj obj = Tab.find(designatorIdent.getDesignator().obj.getName());
    	if(obj == Tab.noObj) {
    		report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getDesignator().obj.getName()+" nije deklarisano! ", null);
    		designatorIdent.obj = Tab.noObj;
    	} else {
    		if(obj.getType().getKind() != Struct.Array) {
    			report_error("Semanticka greska " + " : ime "+designatorIdent.getDesignator().obj.getName()+" nije niz! ", designatorIdent);
    			designatorIdent.obj = Tab.noObj;
    		} else {
    			if(designatorIdent.getExpr().struct != Tab.intType) {
    				report_error("Semanticka greska " + " : tip expr "+designatorIdent.getDesignator().obj.getName()+" nije int! " + "vec je " + designatorIdent.getExpr().struct, designatorIdent);
    				designatorIdent.obj = Tab.noObj;
    			} else {
    				designatorIdent.obj = new Obj (Obj.Elem,"Elem", obj.getType().getElemType());
    			//	visitor.visitObjNode(designatorIdent.getDesignator().obj);
    			}
    		}
    	}
    	
    	report_info("Objekat je element niza " + designatorIdent.getDesignator().obj.getName() + " tipa niz " + designatorIdent.obj.getKind(), designatorIdent);
    }
    
    // Factor = Designator ()
    public void visit(FuncCall funcCall){
    	Obj func = funcCall.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
    		if(Tab.noType == func.getType()){
				report_error("Semanticka greska " + func.getName() + " ne moze se koristiti u izrazima jer nema povratnu vrednost ", funcCall);
    		} else {
				report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
				funcCall.struct = func.getType();
    		}
    		}else{
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
    	}
    }
    
    //Term = Factor
    public void visit(TermF factorTerm) {
    	factorTerm.struct = factorTerm.getFactor().struct;
    }
    
    //Term = Term Mulop Factor
    public void visit(TermM factorTerm) {
    	Struct te = factorTerm.getTerm().struct;
    	Struct t = factorTerm.getFactor().struct;
    	if(te.equals(t) && te == Tab.intType)
    		factorTerm.struct = te;
    	else {
			report_error("Greska na liniji " + factorTerm.getLine() + " : nekompatibilni tipovi u izrazu za mnozenje."
					+ te.getKind(), null);
			factorTerm.struct = Tab.noType;
    	}
    		
    }
    
    public void visit(TermMulop mulFactor) {
    	if(mulFactor.getDesignator().obj.getKind() != Obj.Var ) {
    		if(mulFactor.getDesignator().obj.getKind() != Obj.Elem) {
    			if(mulFactor.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + mulFactor.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			mulFactor.struct = Tab.noType;
    			}
    			else {
        			if(mulFactor.getFactor().struct.assignableTo(mulFactor.getDesignator().obj.getType()) && mulFactor.getFactor().struct == Tab.intType) {
        				mulFactor.struct = Tab.intType;
        			}
        			else {
        				report_error("Greska na liniji " + mulFactor.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
        				mulFactor.struct = Tab.noType;
        			}
    			}
    		} else {
    			if(mulFactor.getFactor().struct.assignableTo(mulFactor.getDesignator().obj.getType()) && mulFactor.getFactor().struct == Tab.intType)	{
    				mulFactor.struct = Tab.intType;
    			} else {
        			report_error("Greska na liniji " + mulFactor.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
        			mulFactor.struct = Tab.noType;
    			}
    		}
    	} else {
    		if(mulFactor.getFactor().struct.assignableTo(mulFactor.getDesignator().obj.getType()) && mulFactor.getFactor().struct == Tab.intType) {
    			mulFactor.struct = Tab.intType;
    		} else {
    			report_error("Greska na liniji " + mulFactor.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    			mulFactor.struct = Tab.noType;
    		}
    	}
    }
    
    //Expr = - Term
    public void visit(ExprMA minusTerm) {
    	Struct t = minusTerm.getTerm().struct;
    	if(t == Tab.intType) 
    		minusTerm.struct = t;
    	else {
			report_error("Greska na liniji " + minusTerm.getLine() + " mora biti tipa int", null);
			minusTerm.struct = Tab.noType;
    	}
    }
    
    //Expr = Term
    public void visit(Exprs termExpr) {
    	termExpr.struct = termExpr.getTerm().struct;
    }
    
    public void visit(ExprAddop addExpr) {
    	Struct te = addExpr.getExpr().struct;
    	Struct t = addExpr.getTerm().struct;
    	if(te.equals(t) && te == Tab.intType)
    		addExpr.struct = te;
    	else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za mnozenje."
					+ te.getKind(), null);
			addExpr.struct = Tab.noType;
    	}
    }
    
    //Expr = Designator:te Addop Term:t
    public void visit(ExprA addExpr) {
    	if(addExpr.getDesignator().obj.getKind() != Obj.Var ) {
    		if(addExpr.getDesignator().obj.getKind() != Obj.Elem) {
    			if(addExpr.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + addExpr.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			addExpr.struct = Tab.noType;
    			}
    			else {
        			if(addExpr.getTerm().struct.assignableTo(addExpr.getDesignator().obj.getType()) && addExpr.getTerm().struct == Tab.intType) {
        				addExpr.struct = Tab.intType;
        			}
        			else {
        				report_error("Greska na liniji " + addExpr.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
            			addExpr.struct = Tab.noType;
        			}
    			}
    		} else {
    			if(addExpr.getTerm().struct.assignableTo(addExpr.getDesignator().obj.getType()) && addExpr.getTerm().struct == Tab.intType)	{
    				addExpr.struct = Tab.intType;
    			} else {
        			report_error("Greska na liniji " + addExpr.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
        			addExpr.struct = Tab.noType;
    			}
    		}
    	} else {
    		if(addExpr.getTerm().struct.assignableTo(addExpr.getDesignator().obj.getType()) && addExpr.getTerm().struct == Tab.intType) {
    			addExpr.struct = Tab.intType;
    		} else {
    			report_error("Greska na liniji " + addExpr.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    			addExpr.struct = Tab.noType;
    		}
    	}
		
    }

    
    //Factor = NumConst
    public void visit(FactorNumConst numConst) {
  /*  	if(numConst.getParent().getParent().getParent().getClass() == ExprA.class) {
    	report_info("numConst " + numConst.getParent().getParent().getParent().getClass(), numConst);
    	proveraConst = true;
    	}
    	if(numConst.getParent().getParent().getParent().getClass() == TermM.class) {
    //	report_info("numConst " + numConst.getParent().getParent().getClass(), numConst);
    	proveraConst = true;
    	} */
    	numConst.struct = Tab.intType;
    }
    
    //Factor = charConst
    public void visit(FactorCharConst charConst) {
    	charConst.struct = Tab.charType;
    }
    
    //Factor = boolConst
    public void visit(FactorBoolConst boolConst) {
    	boolConst.struct = SemanticAnalyzer.boolType;
    }
    
    //Factor = designator
    public void visit(DesignatorFactor designator) {
    	designator.struct = designator.getDesignator().obj.getType();
    }
    
    //Factor = (Expr)
    public void visit(FactorExpr expr) {
    	expr.struct = expr.getExpr().struct;
    	
    }
    
    //Factor = new Type [Expr]
    public void visit(FactorNewExpr exprB) {
    	if (Tab.find(exprB.getType().getTypeName()) != Tab.noObj) {
    	Struct e = exprB.getExpr().struct;
    	if(e != Tab.intType) {
			report_error("Semanticka greska na liniji " + exprB.getLine() + " tip unutar zagrada mora biti int", null);
			exprB.struct = Tab.noType;
    	} else 
    		exprB.struct = new Struct(Struct.Array, exprB.getType().struct);
    	} else {
    		report_error("Semanticka greska na liniji " + exprB.getLine() + " tip ne postoji u tabeli", null);
    	}
    }
    
    //Factor = new Type
    public void visit(FactorNew exprNew) {
    	exprNew.struct = exprNew.getType().struct;
    }
    
    //Statment = Return Expr;
    public void visit(ReturnExpr returnExpr) {
    	returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	if(!currMethType.compatibleWith(returnExpr.getExpr().struct)){
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
    	}
    }
    
    //Statement = read
    public void visit(Reads readS) {
    	if(readS.getDesignator().obj.getKind() != Obj.Var ) {
    		if(readS.getDesignator().obj.getKind() != Obj.Elem) {
    			if(readS.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + readS.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(readS.getDesignator().obj.getType() != Tab.intType && readS.getDesignator().obj.getType() != Tab.charType &&  
        					readS.getDesignator().obj.getType() != boolType)
            			report_error("Greska na liniji " + readS.getLine() + " : " + "designator nije tipa int, char ili bool! ", null);
    			}
    		} else {
    			if(readS.getDesignator().obj.getType() != Tab.intType && readS.getDesignator().obj.getType() != Tab.charType &&  
    					readS.getDesignator().obj.getType() != boolType)
        			report_error("Greska na liniji " + readS.getLine() + " : " + "designator nije tipa int, char ili bool! ", null);
    		}
    	} else {
			if(readS.getDesignator().obj.getType() != Tab.intType && readS.getDesignator().obj.getType() != Tab.charType &&  
					readS.getDesignator().obj.getType() != boolType)
    			report_error("Greska na liniji " + readS.getLine() + " : " + "designator nije tipa int, char ili bool! ", null);
    	}
    }
    
    //Statement = print bez numconst
    public void visit(PrintStmt printD) {
    	printCallCount++;
   // 	report_info("Print " + printD.getExpr().struct, printD);
    	if(printD.getExpr().struct != Tab.intType && printD.getExpr().struct != Tab.charType && printD.getExpr().struct != boolType)
    		report_error("Greska na liniji " + printD.getLine() + " : " + "expr nije tipa int, char ili bool! ", null);
    }
    
    //Statement = print
    public void visit(PrintStmtC printD) {
    	printCallCount++;
    //	report_info("Print " + printD.getExpr().struct, printD);
    	if(printD.getExpr().struct != Tab.intType && printD.getExpr().struct != Tab.charType && printD.getExpr().struct != boolType)
    		report_error("Greska na liniji " + printD.getLine() + " : " + "expr nije tipa int, char ili bool! ", null);
    }
    
    //DesignStatement = Designator = Expr
    public void visit(DesignatorStatementss assign) {
    	if(assign.getDesignator().obj.getKind() != Obj.Var ) {
    		if(assign.getDesignator().obj.getKind() != Obj.Elem) {
    			if(assign.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + assign.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
            			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    			}
    		} else {
    			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
        			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    		}
    	} else {
    		if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
    			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    	}
    	
    }
    
  //DesignStatement = Designator AddopRight Expr
    public void visit(DesignatorAddop assign) {
    	if(assign.getDesignator().obj.getKind() != Obj.Var ) {
    		if(assign.getDesignator().obj.getKind() != Obj.Elem) {
    			if(assign.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + assign.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
            			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    			}
    		} else {
    			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
        			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    		}
    	} else {
    		
    		if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
    			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    	}
    }
    
  //DesignStatement = Designator MulopRight Expr
    public void visit(DesignatorMulop assign) {
     	if(assign.getDesignator().obj.getKind() != Obj.Var ) {
    		if(assign.getDesignator().obj.getKind() != Obj.Elem) {
    			if(assign.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + assign.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
            			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    			}
    		} else {
    			if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
        			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    		}
    	} else {
    		if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType()))
    			report_error("Greska na liniji " + assign.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
    	}
    }
    
    //DesignStatement = Designator ++;
    public void visit(DesignatorStatementsI incr) {
    	if(incr.getDesignator().obj.getKind() != Obj.Var ) {
    		if(incr.getDesignator().obj.getKind() != Obj.Elem) {
    			if(incr.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + incr.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(incr.getDesignator().obj.getType() != Tab.intType)
            			report_error("Greska na liniji " + incr.getLine() + " : " + "designator nije tipa int! ", null);
    			}
    		} else {
    			if(incr.getDesignator().obj.getType() != Tab.intType)
        			report_error("Greska na liniji " + incr.getLine() + " : " + "designator nije tipa int! ", null);
    		}
    	} else {
			if(incr.getDesignator().obj.getType() != Tab.intType)
    			report_error("Greska na liniji " + incr.getLine() + " : " + "designator nije tipa int! ", null);
    	}
    }
    
    //DesignStatement = Designator --
    public void visit(DesignatorStatementsD decr) {
    	if(decr.getDesignator().obj.getKind() != Obj.Var ) {
    		if(decr.getDesignator().obj.getKind() != Obj.Elem) {
    			if(decr.getDesignator().obj.getKind() != Obj.Fld) {
    			report_error(" Sematicka greska na liniji " + decr.getLine() + " Designator mora biti promenljiva ili element niza", null); 
    			}
    			else {
        			if(decr.getDesignator().obj.getType() != Tab.intType)
            			report_error("Greska na liniji " + decr.getLine() + " : " + "designator nije tipa int! ", null);
    			}
    		} else {
    			if(decr.getDesignator().obj.getType() != Tab.intType)
        			report_error("Greska na liniji " + decr.getLine() + " : " + "designator nije tipa int! ", null);
    		}
    	} else {
			if(decr.getDesignator().obj.getType() != Tab.intType)
    			report_error("Greska na liniji " + decr.getLine() + " : " + "designator nije tipa int! ", null);
    	}
    }
    
    
    
    public boolean passed(){
    	return !errorDetected;
    }
    
    
    
    
}
