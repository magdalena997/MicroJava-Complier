// generated with ast extension for cup
// version 0.8
// 22/5/2020 23:11:35


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclsM extends ClassDecl {

    private String name;
    private ListVarDecl ListVarDecl;
    private MethodDecList MethodDecList;

    public ClassDeclsM (String name, ListVarDecl ListVarDecl, MethodDecList MethodDecList) {
        this.name=name;
        this.ListVarDecl=ListVarDecl;
        if(ListVarDecl!=null) ListVarDecl.setParent(this);
        this.MethodDecList=MethodDecList;
        if(MethodDecList!=null) MethodDecList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public ListVarDecl getListVarDecl() {
        return ListVarDecl;
    }

    public void setListVarDecl(ListVarDecl ListVarDecl) {
        this.ListVarDecl=ListVarDecl;
    }

    public MethodDecList getMethodDecList() {
        return MethodDecList;
    }

    public void setMethodDecList(MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListVarDecl!=null) ListVarDecl.accept(visitor);
        if(MethodDecList!=null) MethodDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListVarDecl!=null) ListVarDecl.traverseTopDown(visitor);
        if(MethodDecList!=null) MethodDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListVarDecl!=null) ListVarDecl.traverseBottomUp(visitor);
        if(MethodDecList!=null) MethodDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclsM(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(ListVarDecl!=null)
            buffer.append(ListVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecList!=null)
            buffer.append(MethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclsM]");
        return buffer.toString();
    }
}
