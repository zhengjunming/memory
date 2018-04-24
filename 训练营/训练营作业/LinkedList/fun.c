#include "b.h"

/*创建链表*/
ptr_Node create(int *arr, int n) {
    if (n <= 0)
        return NULL;
    Node *head;
    Node *p1, *p2;
    int i = 0;
    if ((p1 = p2 = (Node *)malloc(LEN)) == NULL)
        return NULL;
    p1->data = *(arr + i);
    head = NULL;
    while (i < n) {
        i++;
        if (i == 1)
            head = p1;
        else
            p2->next = p1;
        p2 = p1;
        if ((p1 = (Node *)malloc(LEN)) == NULL)
            return NULL;
        p1->data = *(arr + i);
    }
    p2->next = NULL;
    return head;
}
/*删除链表结点*/
Status delete_a(ptr_Node head, int index, int *data) {
    if (head == NULL || index < 0 || index >= n)
        return ERROR;
    Node *p1, *p2;
    int i = 0;
    p1 = head;
    if (index == 0) { /*这段if的代码有问题，还没找出来*/
        head = head->next;
        n--;
        *data = p1->data;
        p1->next = NULL;
        free(p1);
        //print(head);
        return SUCCESS;
    }
    while (++i < index)
        p1 = p1->next;
    p2 = p1->next;
    p1->next = p2->next;
    *data = p2->data;
    n--;
    free(p2);
    //print(head);
    return SUCCESS;
}

/*插入结点*/
Status insert(ptr_Node head, ptr_Node node, int index) {
    int i = 0;
    Node *p;
    if (index < 0 || index > n)
        return ERROR;
    p = head;
    if (index == 0) {
        head = node;
        node->next = p;
        n++;
        //print(head);
        return SUCCESS;
    }
    while (++i < index)
        p = p->next;
    node->next = p->next;
    p->next = node;
    n++;
    //print(head);
    return SUCCESS;
}

/*查找*/
int search(ptr_Node head, int data) {
    Node *p = head;
    int k = 0;
    while (p) {
        if (data == p->data)
            return k;
        p = p->next;
        k++;
    }
    return -1;
}
/*修改*/
Status edit(ptr_Node head, int index, int *data) {
    if (head == NULL || index < 0 || index >= n)
        return ERROR;
    int temp;
    int i = 0;
    ptr_Node p = head;
    if (index == 0) {
        temp = *data;
        *data = p->data;
        p->data = temp;
        print(head);
        return SUCCESS;
    }
    while (++i <= index)
        p = p->next;
    temp = *data;
    *data = p->data;
    p->data = temp;
    print(head);
    return SUCCESS;
}
/*排序*/
Status sort(ptr_Node head) {
    if (head == NULL || head->next == NULL)
        return ERROR;
    Node *p1, *p2, *p3;
    p1 = (Node *)malloc(LEN);
    p1->next = head;
    head = p1;
   /* Node *temp;
    while (p1->next != NULL) {
        p2 = p1->next;
        p3 = p1;
        while (p2->next != NULL) {
            if (p2->next->data < p3->next->data)
                p3=p2;
            p2=p2->next;
        }
        if (p3 != p1) {
            temp = p3->next;
            p3->next = temp->next;
            temp->next = p1->next;
            p1->next = temp;
        }
        p1 = p1->next;
    }
    p1 = head;
    head = head->next;
    free(p1);
    print(head);
    return SUCCESS;*/
    p1 = head->next;
    p2 = NULL, p3 = NULL;
    /* 插入排序 */
    head->next = NULL;
    while (p1) {
        p3 = p1->next;
        p2 = head;
        while (p2->next) {
            if (p2->next->data > p1->data)
                break;
            p2 = p2->next;
        }
        /* 将p1结点插入链表 */
        p1->next = p2->next;
        p2->next = p1;
        p1 = p3;
    }
    p1 = head;
    head = head->next;
    free(p1);
    print(head);
    return SUCCESS;
}

/*打印链表*/
void print(Node *head) {
    Node *p;
    printf("\nNow,These %d records are :\n", n);
    p = head;
    if (head != NULL)
        do {
            printf("%d ", p->data);
            p = p->next;
        } while (p != NULL);
    printf("\n");
}
/*释放链表*/
void destroy(ptr_Node head) {
    Node *p;
    while (head != NULL) {
        p = head;
        head = head->next;
        free(p);
    }
}
