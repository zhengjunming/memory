#ifndef A_H_INCLUDED
#define A_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#define LEN sizeof(struct TNode)
typedef struct TNode {
    int data;
    struct TNode *next;
    struct TNode *pre;
}TNode, *ptr_TNode;
typedef enum Status
{
    SUCCESS, ERROR
}Status;
Status delete_T(ptr_TNode head, int index, int *data);/*ɾ�����*/
Status insert_T(ptr_TNode head, ptr_TNode node, int index);/*������*/
ptr_TNode create();/*��������*/
void destroy(ptr_TNode head);/*�ͷ�����*/
void print(ptr_TNode head);/*��ӡ����*/

#endif // A_H_INCLUDED
