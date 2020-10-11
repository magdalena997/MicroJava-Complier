// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class SignalCDecls extends ConstDeclList {

    private ConstDeclP ConstDeclP;

    public SignalCDecls (ConstDeclP ConstDeclP) {
        this.ConstDeclP=ConstDeclP;
        if(ConstDeclP!=null) ConstDeclP.setParent(this);
    }

    public ConstDeclP getConstDeclP() {
        return ConstDeclP;
    }

    public void setConstDeclP(ConstDeclP ConstDeclP) {
        this.ConstDeclP=ConstDeclP;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclP!=null) ConstDeclP.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclP!=null) ConstDeclP.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclP!=null) ConstDeclP.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SignalCDecls(\n");

        if(ConstDeclP!=null)
            buffer.append(ConstDeclP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SignalCDecls]");
        return buffer.toString();
    }
}
