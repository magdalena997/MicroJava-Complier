// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:34


package rs.ac.bg.etf.pp1.ast;

public class ListProgs extends Lists {

    private Lists Lists;
    private ListProg ListProg;

    public ListProgs (Lists Lists, ListProg ListProg) {
        this.Lists=Lists;
        if(Lists!=null) Lists.setParent(this);
        this.ListProg=ListProg;
        if(ListProg!=null) ListProg.setParent(this);
    }

    public Lists getLists() {
        return Lists;
    }

    public void setLists(Lists Lists) {
        this.Lists=Lists;
    }

    public ListProg getListProg() {
        return ListProg;
    }

    public void setListProg(ListProg ListProg) {
        this.ListProg=ListProg;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Lists!=null) Lists.accept(visitor);
        if(ListProg!=null) ListProg.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Lists!=null) Lists.traverseTopDown(visitor);
        if(ListProg!=null) ListProg.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Lists!=null) Lists.traverseBottomUp(visitor);
        if(ListProg!=null) ListProg.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListProgs(\n");

        if(Lists!=null)
            buffer.append(Lists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListProg!=null)
            buffer.append(ListProg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListProgs]");
        return buffer.toString();
    }
}
