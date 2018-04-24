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
Status initStack(LinkStack *S); //��ʼ����ջ
Status isEmpty(LinkStack *S);//�ж���ջ�Ƿ�Ϊ��
Status push(LinkStack *S, ElemType e);//ѹջ
Status pop(LinkStack *S, ElemType *e);//��ջ
Status getTop(LinkStack *S, ElemType *e);//��ȡջ��Ԫ��
Status destroyStack(LinkStack *S);//����һ����ջ
Status stackLength(LinkStack *S);//ջ�ĳ���
Status clear(LinkStack *S);//���һ����ջ
void printStack(LinkStack *S);//��ӡһ����ջ
void select();//�˵�ѡ��
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
