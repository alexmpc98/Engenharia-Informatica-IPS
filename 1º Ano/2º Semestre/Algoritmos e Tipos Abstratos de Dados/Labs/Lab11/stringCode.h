#pragma once

typedef char String[256];

typedef struct stringCode {
String code; 
} StringCode;

StringCode stringCodeCreate(char *str) ;

void stringCodePrint(StringCode str) ;

