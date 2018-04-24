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
Status delete_T(ptr_TNode head, int index, int *data);/*删除结点*/
Status insert_T(ptr_TNode head, ptr_TNode node, int index);/*插入结点*/
ptr_TNode create();/*创建链表*/
void destroy(ptr_TNode head);/*释放链表*/
void print(ptr_TNode head);/*打印链表*/

#endif // A_H_INCLUDED
