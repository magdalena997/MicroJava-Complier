// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class SinglVarDecl extends VarDecList {

    private VarDecListP VarDecListP;

    public SinglVarDecl (VarDecListP VarDecListP) {
        this.VarDecListP=VarDecListP;
        if(VarDecListP!=null) VarDecListP.setParent(this);
    }

    public VarDecListP getVarDecListP() {
        return VarDecListP;
    }

    public void setVarDecListP(VarDecListP VarDecListP) {
        this.VarDecListP=VarDecListP;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDecListP!=null) VarDecListP.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDecListP!=null) VarDecListP.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDecListP!=null) VarDecListP.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SinglVarDecl(\n");

        if(VarDecListP!=null)
            buffer.append(VarDecListP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SinglVarDecl]");
        return buffer.toString();
    }
}
