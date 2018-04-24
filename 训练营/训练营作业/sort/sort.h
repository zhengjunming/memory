/**************************************************************
*	Multi-Include-Prevent Section
**************************************************************/
#ifndef SORT_H_INCLUDED
#define SORT_H_INCLUDED
/**************************************************************
*	Include File Section
**************************************************************/
#include<stdio.h>
#include<stdlib.h>
#include <conio.h>
#include <time.h>

/**************************************************************
*	Macro Define Section
**************************************************************/
#define min(A,B) (A<B) ? A : B
#define SWAP(x,y) {int t; t = x; x = y; y = t;}
#define push2(A,B) push(&S,B); push(&S,A);
#define OVERFLOW -1
#define MAX = 100;
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
}LinkStack;

/**************************************************************
*	Prototype Declare Section
**************************************************************/
Status initStack(LinkStack *S); //��ʼ����ջ
Status isEmpty(LinkStack *S);//�ж���ջ�Ƿ�Ϊ��
Status push(LinkStack *S, ElemType e);//ѹջ
Status pop(LinkStack *S, ElemType *e);//��ջ
Status destroyStack(LinkStack *S);//����һ����ջ
Status clear(LinkStack *S);

int partition(ElemType a[], int left, int right);// ��·����

void qSort_Recursion(ElemType a[], int left, int right); // ���ŷǵݹ�
void insertSort(int a[]); // ��������
void qSort(int a[], int left, int right); // ����
void mergeSort(int a[], int left, int right); // �鲢����
void Merge(int a[], int left, int mid, int rigth);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/


#endif // SORT_H_INCLUDED
