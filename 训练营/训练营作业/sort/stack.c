#include "sort.h"


Status initStack(LinkStack *S) {
    S->top = NULL;
    return OK;
}

Status isEmpty(LinkStack *S) {
    return (NULL == S->top ? TRUE : FALSE);
}

Status push(LinkStack *S, ElemType e) {
    StackNode *p = (StackNode *)malloc(sizeof(StackNode));
    if (NULL == p) {
        return OVERFLOW;
    }
    p->data = e;
    p->next = S->top;//���½��*p������ջͷ��
    S->top = p;
    return OK;
}

Status pop(LinkStack *S, ElemType *e) {
    if (isEmpty(S))
        return ERROR;  //����
    StackNode *p = S->top;//����ջ��ָ��
    *e = p->data;  //����ջ���������
    S->top = p->next;  //��ջ����������ժ��
    free(p);
    return OK;
}

Status destroyStack(LinkStack *S) {
    if (isEmpty(S)) {
        free(S);
        return OK;
    }
    clear(S);
    free(S);
    return OK;
}

Status clear(LinkStack *S) {
    while (pop(S, NULL) != ERROR);
    return OK;
}
