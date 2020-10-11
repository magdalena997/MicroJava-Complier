
package rs.ac.bg.etf.pp1;
import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"break"   { return new_symbol(sym.BREAK, yytext());}
"class"   { return new_symbol(sym.CLASS, yytext());}
"abstract"   { return new_symbol(sym.ABSTRACT, yytext());}
"else"   { return new_symbol(sym.ELSE, yytext());}
"const"   { return new_symbol(sym.CONST, yytext());}
"if"   { return new_symbol(sym.IF, yytext());}
"new"   { return new_symbol(sym.NEW, yytext());}
"print"   { return new_symbol(sym.PRINT, yytext());}
"read"   { return new_symbol(sym.READ, yytext());}
"return"   { return new_symbol(sym.RETURN, yytext());}
"void"   { return new_symbol(sym.VOID, yytext());}
"for"   { return new_symbol(sym.FOR, yytext());}
"extends"   { return new_symbol(sym.EXTENDS, yytext());}
"continue"   { return new_symbol(sym.CONTINUE, yytext());}


"+"   { return new_symbol(sym.PLUS, yytext());}
"-"   { return new_symbol(sym.MINUS, yytext());}
"*"   { return new_symbol(sym.MULTIPLICATION, yytext());}
"/"   { return new_symbol(sym.DIVISION, yytext());}
"%"   { return new_symbol(sym.PERCENT, yytext());}
"=="   { return new_symbol(sym.EQUALTO, yytext());}
"!="   { return new_symbol(sym.NOTEQUAL, yytext());}
">"   { return new_symbol(sym.GREATER, yytext());}
">="   { return new_symbol(sym.GREQUAL, yytext());}
"<"   { return new_symbol(sym.LESS, yytext());}
"<="   { return new_symbol(sym.LESSEQUAL, yytext());}
"&&"   { return new_symbol(sym.ANDLOGICAL, yytext());}
"||"   { return new_symbol(sym.ORLOGICAL, yytext());}
"="   { return new_symbol(sym.EQUAL, yytext());}
"++"   { return new_symbol(sym.INCREMENT, yytext());}
"--"   { return new_symbol(sym.DECREMENT, yytext());}
";"   { return new_symbol(sym.SEMI, yytext());}
","   { return new_symbol(sym.COMMA, yytext());}
"."   { return new_symbol(sym.POINT, yytext());}
"("   { return new_symbol(sym.LPAREN, yytext());}
")"   { return new_symbol(sym.RPAREN, yytext());}
"["   { return new_symbol(sym.LSQUAREB, yytext());}
"]"   { return new_symbol(sym.RSQUAREB, yytext());}
"{"   { return new_symbol(sym.LCURLYB, yytext());}
"}"   { return new_symbol(sym.RCURLYB, yytext());}
"+="   { return new_symbol(sym.ADD, yytext());}
"-="   { return new_symbol(sym.SUB, yytext());}
"*="   { return new_symbol(sym.MUL, yytext());}
"/="   { return new_symbol(sym.DIV, yytext());}
"%="   { return new_symbol(sym.MOD, yytext());}

<YYINITIAL> "//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

"true"|"false" { return new_symbol(sym.BOOLCONST, yytext() ); }
[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
"'"."'" {return new_symbol (sym.CHARCONST, new Character (yytext().charAt(1))); }

([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }



. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1) + " na poziciji " + yychar) ; }
