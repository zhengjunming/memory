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
int n;/*��㳤��*/
ptr_Node create(int *arr, int n);/*������̬����*/
Status delete_a(ptr_Node head, int index, int *data);/*ɾ�����*/
Status insert(ptr_Node head, ptr_Node node, int index);/*������*/
int search(ptr_Node head, int data);/*���ҽ����Ϣ*/
Status edit(ptr_Node head, int index, int *data);/*�޸�*/
void destroy(ptr_Node head);/*�ͷ�����*/
void print(Node *);/*��ӡ����*/
Status sort(ptr_Node head);/*����*/

#endif // B_H_INCLUDED
