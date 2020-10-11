// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclPNums extends ConstDeclP {

    private String NameConstN;
    private Integer numC;

    public ConstDeclPNums (String NameConstN, Integer numC) {
        this.NameConstN=NameConstN;
        this.numC=numC;
    }

    public String getNameConstN() {
        return NameConstN;
    }

    public void setNameConstN(String NameConstN) {
        this.NameConstN=NameConstN;
    }

    public Integer getNumC() {
        return numC;
    }

    public void setNumC(Integer numC) {
        this.numC=numC;
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
        buffer.append("ConstDeclPNums(\n");

        buffer.append(" "+tab+NameConstN);
        buffer.append("\n");

        buffer.append(" "+tab+numC);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclPNums]");
        return buffer.toString();
    }
}
