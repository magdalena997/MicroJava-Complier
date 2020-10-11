// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class AbstractListMethods extends AbstractList {

    private AbstractList AbstractList;
    private AbstractListP AbstractListP;

    public AbstractListMethods (AbstractList AbstractList, AbstractListP AbstractListP) {
        this.AbstractList=AbstractList;
        if(AbstractList!=null) AbstractList.setParent(this);
        this.AbstractListP=AbstractListP;
        if(AbstractListP!=null) AbstractListP.setParent(this);
    }

    public AbstractList getAbstractList() {
        return AbstractList;
    }

    public void setAbstractList(AbstractList AbstractList) {
        this.AbstractList=AbstractList;
    }

    public AbstractListP getAbstractListP() {
        return AbstractListP;
    }

    public void setAbstractListP(AbstractListP AbstractListP) {
        this.AbstractListP=AbstractListP;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbstractList!=null) AbstractList.accept(visitor);
        if(AbstractListP!=null) AbstractListP.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbstractList!=null) AbstractList.traverseTopDown(visitor);
        if(AbstractListP!=null) AbstractListP.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbstractList!=null) AbstractList.traverseBottomUp(visitor);
        if(AbstractListP!=null) AbstractListP.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractListMethods(\n");

        if(AbstractList!=null)
            buffer.append(AbstractList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbstractListP!=null)
            buffer.append(AbstractListP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractListMethods]");
        return buffer.toString();
    }
}
