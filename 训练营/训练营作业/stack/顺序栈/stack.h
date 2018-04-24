
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
    ElemType *elem; //�洢�ռ�
    int top; //����ջ��ָ��
    int size; //ջ������С
    //int increment; //�������ӵ�����
}SqStack;

/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack(SqStack *S, int size);//ջ��ʼ��
Status push(SqStack *S, ElemType e);//ѹջ
Status pop(SqStack *S, ElemType *e);//��ջ
Status getTop(SqStack *S, ElemType *e);//��ȡջ��Ԫ��
Status isEmptyStack(SqStack *S);//�ж��Ƿ�Ϊ��ջ
Status destroyStack(SqStack *S);//����ջ
void select();//�˵�ѡ��
void printStack(SqStack *S);//��ӡջ
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
