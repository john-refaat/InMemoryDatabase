grammar DataBase;

statement: addStatement | updateStatement | deleteStatement | printStatement | printallStatement |quitStatement ;
addStatement: 'add' WHITESPACE empId DASH empName DASH designation DASH salary ;
updateStatement: 'update' WHITESPACE empId DASH PROPERTY DASH value ;
deleteStatement: 'del' WHITESPACE empId ;
printStatement: 'print' WHITESPACE empId ;
printallStatement: 'printall' WHITESPACE ORDER ;
quitStatement: 'quit' ;

value: empName | DESIGNATION | salary ;

PROPERTY: (('NAME') | ('DESIG') | ('SALARY')) ;
ORDER: (('ASC') | ('DESC')) ;

empId: NUM ;
salary: NUM ;

empName: ALPHA ;
designation: alphaWhiteSpace ;

alphaWhiteSpace: ALPHA ((WHITESPACE)+ ALPHA)* ;

ALPHA: [a-zA-Z]+ ;
NUM: [0-9]+ ;

WHITESPACE : (' ' | '\t' | '\n' | '\r' | '\f') ;
DASH: '-' ;