// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class FORS extends Matched {

    private DesignatorStatements DesignatorStatements;
    private Conditions Conditions;
    private DesignatorStatements DesignatorStatements1;

    public FORS (DesignatorStatements DesignatorStatements, Conditions Conditions, DesignatorStatements DesignatorStatements1) {
        this.DesignatorStatements=DesignatorStatements;
        if(DesignatorStatements!=null) DesignatorStatements.setParent(this);
        this.Conditions=Conditions;
        if(Conditions!=null) Conditions.setParent(this);
        this.DesignatorStatements1=DesignatorStatements1;
        if(DesignatorStatements1!=null) DesignatorStatements1.setParent(this);
    }

    public DesignatorStatements getDesignatorStatements() {
        return DesignatorStatements;
    }

    public void setDesignatorStatements(DesignatorStatements DesignatorStatements) {
        this.DesignatorStatements=DesignatorStatements;
    }

    public Conditions getConditions() {
        return Conditions;
    }

    public void setConditions(Conditions Conditions) {
        this.Conditions=Conditions;
    }

    public DesignatorStatements getDesignatorStatements1() {
        return DesignatorStatements1;
    }

    public void setDesignatorStatements1(DesignatorStatements DesignatorStatements1) {
        this.DesignatorStatements1=DesignatorStatements1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatements!=null) DesignatorStatements.accept(visitor);
        if(Conditions!=null) Conditions.accept(visitor);
        if(DesignatorStatements1!=null) DesignatorStatements1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatements!=null) DesignatorStatements.traverseTopDown(visitor);
        if(Conditions!=null) Conditions.traverseTopDown(visitor);
        if(DesignatorStatements1!=null) DesignatorStatements1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatements!=null) DesignatorStatements.traverseBottomUp(visitor);
        if(Conditions!=null) Conditions.traverseBottomUp(visitor);
        if(DesignatorStatements1!=null) DesignatorStatements1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FORS(\n");

        if(DesignatorStatements!=null)
            buffer.append(DesignatorStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Conditions!=null)
            buffer.append(Conditions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatements1!=null)
            buffer.append(DesignatorStatements1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FORS]");
        return buffer.toString();
    }
}
