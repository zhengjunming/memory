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
Status initStack(LinkStack *S); //初始化链栈
Status isEmpty(LinkStack *S);//判断链栈是否为空
Status push(LinkStack *S, ElemType e);//压栈
Status pop(LinkStack *S, ElemType *e);//出栈
Status destroyStack(LinkStack *S);//销毁一个链栈
Status clear(LinkStack *S);

int partition(ElemType a[], int left, int right);// 中路划分

void qSort_Recursion(ElemType a[], int left, int right); // 快排非递归
void insertSort(int a[]); // 插入排序
void qSort(int a[], int left, int right); // 快排
void mergeSort(int a[], int left, int right); // 归并排序
void Merge(int a[], int left, int mid, int rigth);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/


#endif // SORT_H_INCLUDED
