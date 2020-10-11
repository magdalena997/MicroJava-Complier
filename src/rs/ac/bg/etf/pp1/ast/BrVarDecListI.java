// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class BrVarDecListI extends VarDecListP {

    private String varIdenList;

    public BrVarDecListI (String varIdenList) {
        this.varIdenList=varIdenList;
    }

    public String getVarIdenList() {
        return varIdenList;
    }

    public void setVarIdenList(String varIdenList) {
        this.varIdenList=varIdenList;
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
        buffer.append("BrVarDecListI(\n");

        buffer.append(" "+tab+varIdenList);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BrVarDecListI]");
        return buffer.toString();
    }
}
