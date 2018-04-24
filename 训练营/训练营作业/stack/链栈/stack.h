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
typedef struct StackNode {
    ElemType data;
    struct StackNode *next;
}StackNode, *LinkStackPtr;

typedef struct LinkStack {
    LinkStackPtr top;
    int count;
}LinkStack;
/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack(LinkStack *S); //初始化链栈
Status isEmpty(LinkStack *S);//判断链栈是否为空
Status push(LinkStack *S, ElemType e);//压栈
Status pop(LinkStack *S, ElemType *e);//出栈
Status getTop(LinkStack *S, ElemType *e);//获取栈底元素
Status destroyStack(LinkStack *S);//销毁一个链栈
Status stackLength(LinkStack *S);//栈的长度
Status clear(LinkStack *S);//清除一个链栈
void printStack(LinkStack *S);//打印一个链栈
void select();//菜单选择
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
