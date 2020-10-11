// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class AbstractClassDeclsA extends AbstractClassDecl {

    private String abstractName;
    private ListVarDecl ListVarDecl;
    private AbstractList AbstractList;

    public AbstractClassDeclsA (String abstractName, ListVarDecl ListVarDecl, AbstractList AbstractList) {
        this.abstractName=abstractName;
        this.ListVarDecl=ListVarDecl;
        if(ListVarDecl!=null) ListVarDecl.setParent(this);
        this.AbstractList=AbstractList;
        if(AbstractList!=null) AbstractList.setParent(this);
    }

    public String getAbstractName() {
        return abstractName;
    }

    public void setAbstractName(String abstractName) {
        this.abstractName=abstractName;
    }

    public ListVarDecl getListVarDecl() {
        return ListVarDecl;
    }

    public void setListVarDecl(ListVarDecl ListVarDecl) {
        this.ListVarDecl=ListVarDecl;
    }

    public AbstractList getAbstractList() {
        return AbstractList;
    }

    public void setAbstractList(AbstractList AbstractList) {
        this.AbstractList=AbstractList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListVarDecl!=null) ListVarDecl.accept(visitor);
        if(AbstractList!=null) AbstractList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListVarDecl!=null) ListVarDecl.traverseTopDown(visitor);
        if(AbstractList!=null) AbstractList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListVarDecl!=null) ListVarDecl.traverseBottomUp(visitor);
        if(AbstractList!=null) AbstractList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractClassDeclsA(\n");

        buffer.append(" "+tab+abstractName);
        buffer.append("\n");

        if(ListVarDecl!=null)
            buffer.append(ListVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbstractList!=null)
            buffer.append(AbstractList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractClassDeclsA]");
        return buffer.toString();
    }
}
