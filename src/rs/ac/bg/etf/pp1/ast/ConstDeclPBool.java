// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclPBool extends ConstDeclP {

    private String NameConstB;
    private String boolC;

    public ConstDeclPBool (String NameConstB, String boolC) {
        this.NameConstB=NameConstB;
        this.boolC=boolC;
    }

    public String getNameConstB() {
        return NameConstB;
    }

    public void setNameConstB(String NameConstB) {
        this.NameConstB=NameConstB;
    }

    public String getBoolC() {
        return boolC;
    }

    public void setBoolC(String boolC) {
        this.boolC=boolC;
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
        buffer.append("ConstDeclPBool(\n");

        buffer.append(" "+tab+NameConstB);
        buffer.append("\n");

        buffer.append(" "+tab+boolC);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclPBool]");
        return buffer.toString();
    }
}
