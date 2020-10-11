// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:34


package rs.ac.bg.etf.pp1.ast;

public class NoListProgs extends Lists {

    public NoListProgs () {
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
        buffer.append("NoListProgs(\n");

        buffer.append(tab);
        buffer.append(") [NoListProgs]");
        return buffer.toString();
    }
}
