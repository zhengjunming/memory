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
    ElemType *elem; //�洢�ռ�
    int top; //����ջ��ָ��
    int size; //ջ������С
}SqStack_c;
typedef struct SqStack_f {
    float *elem;
    int top;
    int size;
}SqStack_f;


/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack_c(SqStack_c *S, int size);//ջ��ʼ��
Status initStack_f(SqStack_f *S, int size);//ջ��ʼ��
Status push_c(SqStack_c *S, ElemType e);//ѹջ
Status push_f(SqStack_f *S, float e);
void pop_c(SqStack_c *S, ElemType *e);//��ջ
void pop_f(SqStack_f *S, float *e);
void getTop_c(SqStack_c *S, ElemType *e);//��ȡջ��Ԫ��
void getTop_f(SqStack_f *S, float *e);
void destroyStack_c(SqStack_c *S);//����ջ
void destroyStack_f(SqStack_f *S);
Status priority(char a, char b);//�������ȼ�
void midToPost(char *a);//תΪ��׺���ʽ
void qiuZhi(SqStack_f *S, char *a);//�����׺���ʽ
/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif
