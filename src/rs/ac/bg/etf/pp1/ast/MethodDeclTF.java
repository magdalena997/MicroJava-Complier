// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclTF extends MethodDecl {

    private MethodDeclType MethodDeclType;
    private FormPars FormPars;
    private ListVarDecl ListVarDecl;
    private StatementList StatementList;

    public MethodDeclTF (MethodDeclType MethodDeclType, FormPars FormPars, ListVarDecl ListVarDecl, StatementList StatementList) {
        this.MethodDeclType=MethodDeclType;
        if(MethodDeclType!=null) MethodDeclType.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.ListVarDecl=ListVarDecl;
        if(ListVarDecl!=null) ListVarDecl.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodDeclType getMethodDeclType() {
        return MethodDeclType;
    }

    public void setMethodDeclType(MethodDeclType MethodDeclType) {
        this.MethodDeclType=MethodDeclType;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public ListVarDecl getListVarDecl() {
        return ListVarDecl;
    }

    public void setListVarDecl(ListVarDecl ListVarDecl) {
        this.ListVarDecl=ListVarDecl;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclType!=null) MethodDeclType.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(ListVarDecl!=null) ListVarDecl.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclType!=null) MethodDeclType.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(ListVarDecl!=null) ListVarDecl.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclType!=null) MethodDeclType.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(ListVarDecl!=null) ListVarDecl.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclTF(\n");

        if(MethodDeclType!=null)
            buffer.append(MethodDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListVarDecl!=null)
            buffer.append(ListVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclTF]");
        return buffer.toString();
    }
}
