#ifndef B_H_INCLUDED
#define B_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#define LEN sizeof(struct Node)

typedef struct Node {
    int data;
    struct Node *next;
}Node, *ptr_Node;

typedef enum Status {
    SUCCESS, ERROR
}Status;
int n;/*结点长度*/
ptr_Node create(int *arr, int n);/*创建动态链表*/
Status delete_a(ptr_Node head, int index, int *data);/*删除结点*/
Status insert(ptr_Node head, ptr_Node node, int index);/*插入结点*/
int search(ptr_Node head, int data);/*查找结点信息*/
Status edit(ptr_Node head, int index, int *data);/*修改*/
void destroy(ptr_Node head);/*释放链表*/
void print(Node *);/*打印链表*/
Status sort(ptr_Node head);/*排序*/

#endif // B_H_INCLUDED
