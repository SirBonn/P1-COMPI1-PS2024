package srbn.Lexer;

import srbn.Parser.ParserSym;
import java.util.ArrayList;
import srbn.Domain.Errors.ErrorP;
import java_cup.runtime.*;

%%

//JFlex Config
%class Lexer
%unicode
%line
%column
%caseless
%cup
%public

//JFlex Macros

WHITESPCS   = ([\s\t\r\n]+)
DIGIT       = [0-9]

//openTags
OPENLABELSTAG = "<etiquetas>"
OPENATTRIBUTESTAG = "<atributos>"
OPENPARAMSTAG = "<parametros>"

//closeTags
CLOSEACTIONTAG = "</accion>"
CLOSEPARAMTAG = "</parametro>"
CLOSELABELTAG = "/>"
CLOSELABELSTAG = "</etiquetas>"
CLOSEATTRIBUTETAG = "</atributo>"
CLOSEATTRIBUTESTAG = "</atributos>"
CLOSEPARAMSTAG = "</parametros>"
CLOSEACTIONSTAG = "</acciones>"
TITULOID = "[TITULO]" | "TITULO"
CENTER_LOC = "[CENTRAR]"
LEFT_LOC = "[IZQUIERDA]"
RIGHT_LOC = "[DERECHA]"
JUSTIFY_LOC = "[JUSTIFICAR]"

ID = ("["[^\"\n\r]*"]")
TAGID = (\"[^\"\n\r]*\")

DBLEQUOTES  = "\""
EQUAL       = "="
MAYORQ      = ">"
COMMA       = ","
SEMICOLON   = ";"

DATE        = ("["[0-9]{4}-[0-9]{2}-[0-9]{2}"]")
HEXCODE     = ("[#"[0-9a-fA-F]{6}"]")

%{
    StringBuffer sb = new StringBuffer();
    ArrayList<ErrorP> errors = new ArrayList<ErrorP>();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void addError(String message) {
        errors.add(new ErrorP(yyline+1, yycolumn+1, "No se reconoce el simbolo: ", 0, message ));
      }
%}

%eofval{
	return new Symbol(ParserSym.EOF);
%eofval}

%%

//tags
{CLOSEACTIONTAG}                 {return symbol(ParserSym.CLOSEACTIONTAG, yytext());}
{CLOSEPARAMTAG}                  {return symbol(ParserSym.CLOSEPARAMTAG, yytext());}
{CLOSELABELTAG}                  {return symbol(ParserSym.CLOSELABELTAG, yytext());}
{CLOSELABELSTAG}                 {return symbol(ParserSym.CLOSELABELSTAG, yytext());}
{CLOSEPARAMSTAG}                 {return symbol(ParserSym.CLOSEPARAMSTAG, yytext());}
{CLOSEACTIONSTAG}                {return symbol(ParserSym.CLOSEACTIONSTAG, yytext());}
{OPENLABELSTAG}                  {return symbol(ParserSym.OPENLABELSTAG, yytext());}
{OPENPARAMSTAG}                  {return symbol(ParserSym.OPENPARAMSTAG, yytext());}
{CLOSEATTRIBUTESTAG}             {return symbol(ParserSym.CLOSEATTRIBUTESTAG, yytext());}
{CLOSEATTRIBUTETAG}              {return symbol(ParserSym.CLOSEATTRIBUTETAG, yytext());}
{OPENATTRIBUTESTAG}              {return symbol(ParserSym.OPENATTRIBUTESTAG, yytext());}


//reservadas
(<acciones>)                     {return symbol(ParserSym.ACTIONS, yytext());}
(<parametro)                     {return symbol(ParserSym.PARAM, yytext());}
(nombre=\")                      {return symbol(ParserSym.NAME, yytext());}
(<etiqueta)                      {return symbol(ParserSym.OPENLABELTAG, yytext());}
(<atributo)                      {return symbol(ParserSym.ATTRIBUTE, yytext());}
(<accion)                        {return symbol(ParserSym.ACTION, yytext());}
//consultas
(CONSULTAR)                      {return symbol(ParserSym.CONSULT, yytext());}
(VISITAS_PAGINA)                 {return symbol(ParserSym.VISITS_PAGE, yytext());}
(VISITAS_SITIO)                  {return symbol(ParserSym.VISITS_SITE, yytext());}
(PAGINAS_POPULARES)              {return symbol(ParserSym.POPULAR_PAGES, yytext());}
(COMPONENTE)                     {return symbol(ParserSym.COMPONENT, yytext());}
(TODO)                           {return symbol(ParserSym.ALL, yytext());}
//tipos de acciones
(NUEVO_SITIO_WEB)                {return symbol(ParserSym.NEW_SITE, yytext());}
(BORRAR_SITIO_WEB)               {return symbol(ParserSym.DELETE_SITE, yytext());}
(NUEVA_PAGINA)                   {return symbol(ParserSym.NEW_PAGE, yytext());}
(BORRAR_PAGINA)                  {return symbol(ParserSym.DELETE_PAGE, yytext());}
(MODIFICAR_PAGINA)               {return symbol(ParserSym.MODIFY_PAGE, yytext());}
(AGREGAR_COMPONENTE)             {return symbol(ParserSym.ADD_COMPONENT, yytext());}
(MODIFICAR_COMPONENTE)           {return symbol(ParserSym.MODIFY_COMPONENT, yytext());}
(BORRAR_COMPONENTE)              {return symbol(ParserSym.DELETE_COMPONENT, yytext());}
//tipos de paramteros
(ID)                             {return symbol(ParserSym.ID_PARAM, yytext());}
{TITULOID}                       {return symbol(ParserSym.TITTLE, yytext());}
(SITIO)                          {return symbol(ParserSym.SITE, yytext());}
(PAGINA)                         {return symbol(ParserSym.PAGE, yytext());}
(CLASE)                          {return symbol(ParserSym.CLASSTYPE, yytext());}
(USUARIO_CREACION)               {return symbol(ParserSym.USER_CREATION, yytext());}
(FECHA_CREACION)                 {return symbol(ParserSym.DATE_CREATION, yytext());}
(USUARIO_MODIFICACION)           {return symbol(ParserSym.USER_MODIFICATION, yytext());}
(FECHA_MODIFICACION)             {return symbol(ParserSym.DATE_MODIFICATION, yytext());}
(PADRE)                          {return symbol(ParserSym.PARENT, yytext());}
//clases de componentes
(PARRAFO)                        {return symbol(ParserSym.PARAGRAPH, yytext());}
(IMAGEN)                         {return symbol(ParserSym.IMAGE, yytext());}
(VIDEO)                          {return symbol(ParserSym.VIDEO, yytext());}
(MENU)                           {return symbol(ParserSym.MENU, yytext());}
//ATRIBUTOS
(TEXTO)                          {return symbol(ParserSym.TEXT, yytext());}
(ALINEACION)                     {return symbol(ParserSym.ALIGNMENT, yytext());}
(COLOR)                          {return symbol(ParserSym.COLOR, yytext());}
(ORIGEN)                         {return symbol(ParserSym.SOURCE, yytext());}
(ALTURA)                         {return symbol(ParserSym.HEIGHT, yytext());}
(ANCHO)                          {return symbol(ParserSym.WIDTH, yytext());}
//ALINEACIONES
{CENTER_LOC}                     {return symbol(ParserSym.CENTER_LOC, yytext());}
{LEFT_LOC}                       {return symbol(ParserSym.LEFT_LOC, yytext());}
{RIGHT_LOC}                      {return symbol(ParserSym.RIGHT_LOC, yytext());}
{JUSTIFY_LOC}                    {return symbol(ParserSym.JUSTIFY_LOC, yytext());}


{HEXCODE}                        {return symbol(ParserSym.COLOR_VALUE, yytext());}
{DATE}                           {return symbol(ParserSym.DATE_VALUE, yytext());}
{DBLEQUOTES}                     {return symbol(ParserSym.DBLEQUOTES, yytext());}
{SEMICOLON}                      {return symbol(ParserSym.SEMICOLON, yytext());}
{COMMA}                          {return symbol(ParserSym.COMMA, yytext());}
{MAYORQ}                         {return symbol(ParserSym.MAYORQ, yytext());}
{DIGIT}+                         {return symbol(ParserSym.NUMBER_VALUE,yytext());}
{TAGID}                          {return symbol(ParserSym.TAGID, yytext());}
{ID}                             {return symbol(ParserSym.ID_VALUE, yytext());}
{WHITESPCS}                      {/*Ignore*/}
[^]                              {addError(yytext());}
