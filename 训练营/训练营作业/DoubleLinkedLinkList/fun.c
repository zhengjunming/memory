#include "a.h"

//extern void print(ptr_TNode head);/*´òÓ¡Á´±í*/
ptr_TNode create(int *arr, int n) {
    if (n == 0 || arr == NULL)
        return NULL;
    int i;
    ptr_TNode head, p = NULL;
    head = p = (TNode *)malloc(sizeof(LEN));
    head->data = n;
    for (i = 0; i < n; i++) {
        p->next = (TNode *)malloc(sizeof(LEN));
        p->next->pre = p;
        p = p->next;
        p->data = *(arr + i);
    }
    p->next = NULL;
    head->pre = NULL;
    return head;
}

Status insert_T(ptr_TNode head, ptr_TNode node, int index) {
    if(head == NULL || node == NULL || index < 0 || index > head->data)
        return ERROR;
    ptr_TNode p = head;
    if (index == head->data) {
        while (index--)
            p = p->next;
        node->pre = p->pre;
        p->next = node;
        node->next = NULL;
        head->data++;
        print(head);
        return SUCCESS;
    }
    while (index--)
        p = p->next;
    node->next = p->next;
    p->next->pre  = node;
    p->next = node;
    node->pre = p;
    head->data++;
    print(head);
    return SUCCESS;
}

Status delete_T(ptr_TNode head, int index, int *data) {
    if (head == NULL || index < 0 || index >= head->data)
        return ERROR;
    ptr_TNode p1 = head->next, p2;
    int i = 0;
    if (index == 0) {
        head->next = p1->next;
        p1->next->pre = head->pre;
        p1->next = p1->pre = NULL;
        *data = p1->data;
        free(p1);
        head->data--;
        print(head);
        return SUCCESS;
    } else if (index == head->data - 1) {
        while (++i < index)
            p1 = p1->next;
        p2 = p1->next;
        p1->next = NULL;
        p2->pre = NULL;
        *data = p2->data;
        free(p2);
        head->data--;
        print(head);
        return SUCCESS;
    }
    while (++i < index)
        p1 = p1->next;
    p2 = p1->next;
    p1->next = p2->next;
    p2->next->pre = p1->pre;
    *data = p2->data;
    p2->next = NULL;
    p2->pre = NULL;
    free(p2);
    head->data--;
    print(head);
    return SUCCESS;
}
void print(ptr_TNode head) {
    ptr_TNode p = head;
    int count = 0;
    while (p != NULL) {
        count++;
        if (p == head)
            printf("\nNow,These %d records are :\n", p->data);
        else
            printf("%d ", p->data);
        if (count % 10 == 0)
            printf("\n");
        p = p->next;
    }
    printf("\n");
}

void destroy(ptr_TNode head) {
    ptr_TNode p;
    while (head != NULL) {
        p = head;
        head = head->next;
        free(p);
    }
}
