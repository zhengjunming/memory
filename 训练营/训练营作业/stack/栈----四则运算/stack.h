/**************************************************************
*	Multi-Include-Prevent Section
**************************************************************/
#ifndef __CONTROL_H
#define __CONTROL_H


/**************************************************************
*	Debug switch Section
**************************************************************/


/**************************************************************
*	Include File Section
**************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>
#include <math.h>
#include <ctype.h>
/**************************************************************
*	Macro Define Section
**************************************************************/
//#define OVERFLOW -1
#define ERROR 0
#define FALSE 0
#define TRUE 1
#define OK 1

/**************************************************************
*	Struct Define Section
**************************************************************/
typedef char ElemType;
typedef int Status;
typedef struct SqStack_c {
    ElemType *elem; //存储空间
    int top; //用于栈顶指针
    int size; //栈的最大大小
}SqStack_c;
typedef struct SqStack_f {
    float *elem;
    int top;
    int size;
}SqStack_f;


/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack_c(SqStack_c *S, int size);//栈初始化
Status initStack_f(SqStack_f *S, int size);//栈初始化
Status push_c(SqStack_c *S, ElemType e);//压栈
Status push_f(SqStack_f *S, float e);
void pop_c(SqStack_c *S, ElemType *e);//出栈
void pop_f(SqStack_f *S, float *e);
void getTop_c(SqStack_c *S, ElemType *e);//获取栈顶元素
void getTop_f(SqStack_f *S, float *e);
void destroyStack_c(SqStack_c *S);//销毁栈
void destroyStack_f(SqStack_f *S);
Status priority(char a, char b);//计算优先级
void midToPost(char *a);//转为后缀表达式
void qiuZhi(SqStack_f *S, char *a);//计算后缀表达式
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
