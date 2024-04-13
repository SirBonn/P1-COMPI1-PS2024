package srbn.Lexer;

import srbn.Parser.ParserSym;
import java.util.ArrayList;
import srbn.Domain.ErrorP;
import java_cup.runtime.*;
import java.io.*;

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

//ID = ([a-zA-ZÀ-ÿ\u00f1\u00d1|\d|_|-|]+)
//OPENACTIONTAG = "<accion nombre= \""
//OPENPARAMTAG = "<parametro nombre= \""
//OPENLABELTAG = "<etiqueta nombre= \""
//OPENATTRIBUTETAG = "<atributo nombr = \""
//OPENVALUETAG = "<valor>"

//OPENCLASSPARAM = "<parametro nombre=\"clase\">"

//openTags
//OPENACTIONSTAG = "<acciones>"
OPENLABELSTAG = "<etiquetas>"
OPENATTRIBUTESTAG = "<atributos>"
OPENPARAMSTAG = "<parametros>"

//closeTags
CLOSEACTIONTAG = "</accion>"
CLOSEPARAMTAG = "</parametro>"
CLOSELABELTAG = "</etiqueta>"
CLOSEVALUETAG = "</valor>"
CLOSELABELSTAG = "</etiquetas>"
CLOSEATTRIBUTETAG = "</atributo>"
CLOSEATTRIBUTESTAG = "</atributos>"
CLOSEPARAMSTAG = "</parametros>"
CLOSEACTIONSTAG = "</acciones>"

ID = ("["[^\"\n\r]*"]")
DBLEQUOTES  = "\""
EQUAL       = "="
MINORQ      = "<"
MAYORQ      = ">"
DIAGONAL    = "/"
CLSQRBRCKT  = "]"
OPNSQRBRCKT = "["
DATE        = \"[0-9]{4}-[0-9]{2}-[0-9]{2}\"
HEXCODE     = "#[0-9a-fA-F]{6}"

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
{CLOSEACTIONTAG}                {return symbol(ParserSym.CLOSEACTIONTAG, yytext());}
{CLOSEPARAMTAG}                 {return symbol(ParserSym.CLOSEPARAMTAG, yytext());}
{CLOSELABELTAG}                 {return symbol(ParserSym.CLOSELABELTAG, yytext());}
{CLOSEATTRIBUTETAG}             {return symbol(ParserSym.CLOSEATTRIBUTETAG, yytext());}
{CLOSEVALUETAG}                 {return symbol(ParserSym.CLOSEVALUETAG, yytext());}
{CLOSELABELSTAG}                {return symbol(ParserSym.CLOSELABELSTAG, yytext());}
{CLOSEATTRIBUTESTAG}            {return symbol(ParserSym.CLOSEATTRIBUTESTAG, yytext());}
{CLOSEPARAMSTAG}                {return symbol(ParserSym.CLOSEPARAMSTAG, yytext());}
{CLOSEACTIONSTAG}               {return symbol(ParserSym.CLOSEACTIONSTAG, yytext());}
//{OPENACTIONTAG}                 {return symbol(ParserSym.OPENACTIONTAG);}
//{OPENPARAMTAG}                  {return symbol(ParserSym.OPENPARAMTAG);}
//{OPENATTRIBUTETAG}              {return symbol(ParserSym.OPENATTRIBUTETAG);}
//{OPENLABELTAG}                  {return symbol(ParserSym.OPENLABELTAG);}
//{OPENVALUETAG}                  {return symbol(ParserSym.OPENVALUETAG);}
{OPENLABELSTAG}                 {return symbol(ParserSym.OPENLABELSTAG, yytext());}
{OPENATTRIBUTESTAG}             {return symbol(ParserSym.OPENATTRIBUTESTAG, yytext());}
{OPENPARAMSTAG}                 {return symbol(ParserSym.OPENPARAMSTAG, yytext());}



//reservadas
(<acciones>)                       {return symbol(ParserSym.ACTIONS, yytext());}
//(parametros)                     {return symbol(ParserSym.PARAMS, yytext());}
(<parametro)                      {return symbol(ParserSym.PARAM, yytext());}
(nombre=\")                        {return symbol(ParserSym.NAME, yytext());}
//(etiqueta)                       {return symbol(ParserSym.LABEL, yytext());}
//(etiquetas)                      {return symbol(ParserSym.LABELS, yytext());}
//(atributos)                      {return symbol(ParserSym.ATTRIBUTES, yytext());}
(<atributo)                       {return symbol(ParserSym.ATTRIBUTE, yytext());}
//(valor)                          {return symbol(ParserSym.VALUE, yytext());}
(<accion)                         {return symbol(ParserSym.ACTION, yytext());}

//tipos de acciones
(NUEVO_SITIO_WEB)                {return symbol(ParserSym.NEW_SITE, yytext());}
(BORRAR_SITIO_WEB)               {return symbol(ParserSym.DELETE_SITE, yytext());}
(NUEVA_PAGINA)                   {return symbol(ParserSym.NEW_PAGE, yytext());}
(BORRAR_PAGINA)                  {return symbol(ParserSym.DELETE_PAGE, yytext());}
(MODIFICAR_PAGINA)               {return symbol(ParserSym.MODIFY_PAGE, yytext());}
(AGREAGAR_COMPONENTE)            {return symbol(ParserSym.ADD_COMPONENT, yytext());}
(MODIFICAR_COMPONENTE)           {return symbol(ParserSym.MODIFY_COMPONENT, yytext());}
//tipos de paramteros
(ID)                             {return symbol(ParserSym.ID_PARAM, yytext());}
(TITULO)                         {return symbol(ParserSym.TITTLE, yytext());}
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
(CENTRAR)                        {return symbol(ParserSym.CENTER_LOC, yytext());}
(IZQUIERDA)                      {return symbol(ParserSym.LEFT_LOC, yytext());}
(DERECHA)                        {return symbol(ParserSym.RIGHT_LOC, yytext());}
(JUSTIFICAR)                     {return symbol(ParserSym.JUSTIFY_LOC, yytext());}


{HEXCODE}                        {return symbol(ParserSym.COLOR_VALUE, yytext());}
{DATE}                           {return symbol(ParserSym.DATE_VALUE, yytext());}
{DBLEQUOTES}                     {return symbol(ParserSym.DBLEQUOTES, yytext());}
//{EQUAL}                          {return symbol(ParserSym.EQUAL, yytext());}
{MINORQ}                         {return symbol(ParserSym.MINORQ, yytext());}
{MAYORQ}                         {return symbol(ParserSym.MAYORQ, yytext());}
{DIAGONAL}                       {return symbol(ParserSym.DIAGONAL, yytext());}
{CLSQRBRCKT}                     {return symbol(ParserSym.CLSQRBRCKT, yytext());}
{OPNSQRBRCKT}                    {return symbol(ParserSym.OPENSQRBRCKT, yytext());}
{DIGIT}+                         {return symbol(ParserSym.NUMBER_VALUE,yytext());}
{ID}                             {return symbol(ParserSym.ID_VALUE, yytext());}
{WHITESPCS}                      {/*Ignore*/}
[^]                              {addError(yytext());}
