
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
#include <conio.h>

/**************************************************************
*	Macro Define Section
**************************************************************/
#define OVERFLOW -1
#define ERROR 0
#define FALSE 0
#define TRUE 1
#define OK 1

/**************************************************************
*	Struct Define Section
**************************************************************/
typedef int ElemType;
typedef int Status;
typedef struct SqStack {
    ElemType *elem; //存储空间
    int top; //用于栈顶指针
    int size; //栈的最大大小
    //int increment; //扩容增加的容量
}SqStack;

/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack(SqStack *S, int size);//栈初始化
Status push(SqStack *S, ElemType e);//压栈
Status pop(SqStack *S, ElemType *e);//出栈
Status getTop(SqStack *S, ElemType *e);//获取栈顶元素
Status isEmptyStack(SqStack *S);//判断是否为空栈
Status destroyStack(SqStack *S);//销毁栈
void select();//菜单选择
void printStack(SqStack *S);//打印栈
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
