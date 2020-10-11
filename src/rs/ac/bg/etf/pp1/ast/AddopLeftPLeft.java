// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class AddopLeftPLeft extends Addop {

    private AddopLeft AddopLeft;

    public AddopLeftPLeft (AddopLeft AddopLeft) {
        this.AddopLeft=AddopLeft;
        if(AddopLeft!=null) AddopLeft.setParent(this);
    }

    public AddopLeft getAddopLeft() {
        return AddopLeft;
    }

    public void setAddopLeft(AddopLeft AddopLeft) {
        this.AddopLeft=AddopLeft;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddopLeft!=null) AddopLeft.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopLeft!=null) AddopLeft.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopLeft!=null) AddopLeft.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddopLeftPLeft(\n");

        if(AddopLeft!=null)
            buffer.append(AddopLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddopLeftPLeft]");
        return buffer.toString();
    }
}
