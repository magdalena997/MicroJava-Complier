// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclPChar extends ConstDeclP {

    private String NameConstC;
    private Character charC;

    public ConstDeclPChar (String NameConstC, Character charC) {
        this.NameConstC=NameConstC;
        this.charC=charC;
    }

    public String getNameConstC() {
        return NameConstC;
    }

    public void setNameConstC(String NameConstC) {
        this.NameConstC=NameConstC;
    }

    public Character getCharC() {
        return charC;
    }

    public void setCharC(Character charC) {
        this.charC=charC;
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
        buffer.append("ConstDeclPChar(\n");

        buffer.append(" "+tab+NameConstC);
        buffer.append("\n");

        buffer.append(" "+tab+charC);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclPChar]");
        return buffer.toString();
    }
}
