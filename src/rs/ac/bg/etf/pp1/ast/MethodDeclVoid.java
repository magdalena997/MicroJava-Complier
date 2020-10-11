// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclVoid extends MethodDeclType {

    private String metName;

    public MethodDeclVoid (String metName) {
        this.metName=metName;
    }

    public String getMetName() {
        return metName;
    }

    public void setMetName(String metName) {
        this.metName=metName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclVoid(\n");

        buffer.append(" "+tab+metName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclVoid]");
        return buffer.toString();
    }
}
