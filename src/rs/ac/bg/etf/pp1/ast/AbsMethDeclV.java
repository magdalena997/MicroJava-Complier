// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class AbsMethDeclV extends AbstractMethodDecl {

    private String i;
    private FormPars FormPars;

    public AbsMethDeclV (String i, FormPars FormPars) {
        this.i=i;
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i=i;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsMethDeclV(\n");

        buffer.append(" "+tab+i);
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsMethDeclV]");
        return buffer.toString();
    }
}
